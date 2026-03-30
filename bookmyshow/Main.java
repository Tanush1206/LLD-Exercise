package bookmyshow;

import bookmyshow.enums.PaymentMode;
import bookmyshow.enums.ScreenType;
import bookmyshow.enums.SeatType;
import bookmyshow.facade.BookMyShowFacade;
import bookmyshow.interfaces.IAdminService;
import bookmyshow.interfaces.IUserService;
import bookmyshow.models.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        BookMyShowFacade bms = new BookMyShowFacade();

        // Cast to role-specific interfaces — ISP in action
        IAdminService admin = bms;
        IUserService  user  = bms;

        // ── Admin: seed data ──────────────────────────────────

        City bangalore = new City("CITY-1", "Bangalore");

        Movie movie = new Movie("MOV-1", "Interstellar", "English", 169, "Sci-Fi");
        admin.addMovie(movie);

        Theatre pvr    = new Theatre("THR-1", "PVR Orion", "Rajajinagar", bangalore);
        Screen  screen = new Screen("SCR-1", "Audi 1", ScreenType.IMAX, pvr);

        String[]   rows  = { "A", "B" };
        SeatType[] types = { SeatType.GOLD, SeatType.PLATINUM };
        for (int r = 0; r < rows.length; r++) {
            for (int n = 1; n <= 3; n++) {
                screen.addSeat(new Seat(rows[r] + n, rows[r], n, types[r], screen));
            }
        }
        pvr.addScreen(screen);
        admin.addTheatre(pvr);

        // Sunday evening → dynamic pricing applies (+20% weekend, +15% IMAX)
        LocalDateTime showTime = LocalDateTime.of(2026, 4, 5, 18, 30);
        admin.addShow(new Show("SHOW-1", movie, screen, showTime));

        // ── User: browse ──────────────────────────────────────

        System.out.println("=== Movies in Bangalore ===");
        user.listMoviesInCity("CITY-1")
            .forEach(m -> System.out.println("  " + m.getTitle()));

        System.out.println("\n=== Theatres in Bangalore ===");
        user.listTheatresInCity("CITY-1")
            .forEach(t -> System.out.println("  " + t.getName()));

        System.out.println("\n=== Shows for Interstellar ===");
        user.getShowsForMovie("MOV-1")
            .forEach(s -> System.out.println("  " + s.getShowId() + " @ " + s.getStartTime()));

        System.out.println("\n=== Available seats (SHOW-1) ===");
        user.getAvailableSeats("SHOW-1")
            .forEach(ss -> System.out.printf("  %-4s | %-8s | ₹%.2f%n",
                ss.getSeat().getSeatId(),
                ss.getSeat().getSeatType(),
                ss.getPrice()));

        // ── User: book ────────────────────────────────────────

        User arjun = new User("USR-1", "Arjun", "arjun@mail.com", "9876543210");
        List<String> chosen = Arrays.asList("A1", "A2");

        System.out.println("\n=== Booking A1, A2 ===");
        Booking booking = user.bookTickets(arjun, "SHOW-1", chosen, PaymentMode.UPI);
        System.out.println("  Booking ID : " + booking.getBookingId());
        System.out.println("  Status     : " + booking.getStatus());
        System.out.printf ("  Total      : ₹%.2f%n", booking.getTotalAmount());
        System.out.println("  Payment    : " + booking.getPayment().getMode()
                           + " — " + booking.getPayment().getStatus());

        // ── Double-book guard ─────────────────────────────────

        System.out.println("\n=== Re-booking A1 (should fail) ===");
        try {
            user.bookTickets(arjun, "SHOW-1", List.of("A1"), PaymentMode.CREDIT_CARD);
        } catch (IllegalStateException e) {
            System.out.println("  Caught: " + e.getMessage());
        }

        // ── Cancel ────────────────────────────────────────────

        System.out.println("\n=== Cancelling booking ===");
        user.cancelBooking(booking.getBookingId());
        System.out.println("  Status after cancel: "
                + bms.getAvailableSeats("SHOW-1").size() + " seats now available");
    }
}
