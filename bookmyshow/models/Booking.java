package bookmyshow.models;

import bookmyshow.enums.BookingStatus;
import java.time.LocalDateTime;
import java.util.List;

public class Booking {
    private final String          bookingId;
    private final User            user;
    private final Show            show;
    private final List<ShowSeat>  bookedSeats;
    private final double          totalAmount;
    private final LocalDateTime   bookingTime;
    private       BookingStatus   status;
    private       Payment         payment;

    public Booking(String bookingId, User user, Show show, List<ShowSeat> bookedSeats) {
        this.bookingId   = bookingId;
        this.user        = user;
        this.show        = show;
        this.bookedSeats = bookedSeats;
        this.status      = BookingStatus.PENDING;
        this.bookingTime = LocalDateTime.now();
        this.totalAmount = bookedSeats.stream()
                               .mapToDouble(ShowSeat::getPrice)
                               .sum();
    }

    /** Called by BookingService after payment succeeds. */
    public void confirm(Payment payment) {
        this.payment = payment;
        this.status  = BookingStatus.CONFIRMED;
        bookedSeats.forEach(ShowSeat::confirm);
    }

    /** Called on cancellation — releases all locked/booked seats. */
    public void cancel() {
        this.status = BookingStatus.CANCELLED;
        bookedSeats.forEach(ShowSeat::release);
    }

    public String        getBookingId()   { return bookingId; }
    public User          getUser()        { return user; }
    public Show          getShow()        { return show; }
    public BookingStatus getStatus()      { return status; }
    public double        getTotalAmount() { return totalAmount; }
    public Payment       getPayment()     { return payment; }
    public LocalDateTime getBookingTime() { return bookingTime; }
}
