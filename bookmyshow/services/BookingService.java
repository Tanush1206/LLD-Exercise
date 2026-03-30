package bookmyshow.services;

import bookmyshow.enums.BookingStatus;
import bookmyshow.enums.PaymentMode;
import bookmyshow.interfaces.PaymentGateway;
import bookmyshow.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class BookingService {

    private final Map<String, Booking> bookingDB      = new HashMap<>();
    private final ShowService          showService;
    private final PaymentGateway       paymentGateway;
    private final AtomicInteger        counter        = new AtomicInteger(1);

    public BookingService(ShowService showService, PaymentGateway paymentGateway) {
        this.showService    = showService;
        this.paymentGateway = paymentGateway;
    }

    public Booking bookTickets(User user, String showId,
                               List<String> seatIds, PaymentMode mode) {

        Show show = showService.getShow(showId);
        if (show == null) throw new IllegalArgumentException("Show not found: " + showId);

        List<ShowSeat> lockedSeats = new ArrayList<>();

        // Step 1 — atomically lock every requested seat
        for (String seatId : seatIds) {
            ShowSeat ss = show.getShowSeat(seatId);
            if (ss == null || !ss.lock()) {
                lockedSeats.forEach(ShowSeat::release);          // rollback
                throw new IllegalStateException(
                        "Seat " + seatId + " is unavailable.");
            }
            lockedSeats.add(ss);
        }

        // Step 2 — create PENDING booking
        String  bookingId = "BKG-" + counter.getAndIncrement();
        Booking booking   = new Booking(bookingId, user, show, lockedSeats);
        bookingDB.put(bookingId, booking);

        // Step 3 — process payment via injected gateway
        try {
            Payment payment = paymentGateway.processPayment(
                    bookingId, booking.getTotalAmount(), mode);
            booking.confirm(payment);                            // Step 4a
        } catch (Exception e) {
            booking.cancel();                                    // Step 4b
            throw new RuntimeException("Payment failed — booking cancelled.", e);
        }

        return booking;
    }

    /**
     * Cancels a confirmed booking and releases its seats.
     * Refund is initiated via the payment gateway in a real system.
     */
    public void cancelBooking(String bookingId) {
        Booking booking = getBooking(bookingId);
        if (booking.getStatus() != BookingStatus.CONFIRMED) {
            throw new IllegalStateException("Only CONFIRMED bookings can be cancelled.");
        }
        booking.cancel();
        System.out.printf("Refund of %.2f initiated for booking %s%n",
                booking.getTotalAmount(), bookingId);
    }

    public Booking getBooking(String bookingId) {
        Booking booking = bookingDB.get(bookingId);
        if (booking == null) throw new IllegalArgumentException(
                "Booking not found: " + bookingId);
        return booking;
    }
}
