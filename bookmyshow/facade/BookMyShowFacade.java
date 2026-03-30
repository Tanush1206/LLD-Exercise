package bookmyshow.facade;

import bookmyshow.enums.PaymentMode;
import bookmyshow.interfaces.IAdminService;
import bookmyshow.interfaces.IUserService;
import bookmyshow.interfaces.PaymentGateway;
import bookmyshow.interfaces.PricingStrategy;
import bookmyshow.models.*;
import bookmyshow.payment.SimplePaymentGateway;
import bookmyshow.pricing.BasePricingStrategy;
import bookmyshow.pricing.DynamicPricingStrategy;
import bookmyshow.services.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Single entry-point for both the admin and user layers.
 *
 * ISP  : implements IAdminService and IUserService separately —
 *        admin clients depend only on IAdminService,
 *        user clients depend only on IUserService.
 * DIP  : services are constructed with interfaces injected;
 *        default wiring lives here (swap in tests or config).
 */
public class BookMyShowFacade implements IUserService, IAdminService {

    private final MovieService   movieService;
    private final TheatreService theatreService;
    private final ShowService    showService;
    private final BookingService bookingService;

    /** Default wiring — production defaults. */
    public BookMyShowFacade() {
        PricingStrategy pricing = new DynamicPricingStrategy(new BasePricingStrategy());
        PaymentGateway  gateway = new SimplePaymentGateway();

        this.movieService   = new MovieService();
        this.theatreService = new TheatreService();
        this.showService    = new ShowService(pricing);
        this.bookingService = new BookingService(showService, gateway);
    }

    /** Overloaded constructor for dependency injection (testing / alternate config). */
    public BookMyShowFacade(PricingStrategy pricing, PaymentGateway gateway) {
        this.movieService   = new MovieService();
        this.theatreService = new TheatreService();
        this.showService    = new ShowService(pricing);
        this.bookingService = new BookingService(showService, gateway);
    }

    // ── IAdminService ─────────────────────────────────────────

    @Override
    public Movie addMovie(Movie movie) {
        return movieService.addMovie(movie);
    }

    @Override
    public Theatre addTheatre(Theatre theatre) {
        return theatreService.addTheatre(theatre);
    }

    @Override
    public Show addShow(Show show) {
        return showService.addShow(show);
    }

    @Override
    public void deleteShow(String showId) {
        showService.deleteShow(showId);
    }

    // ── IUserService ──────────────────────────────────────────

    @Override
    public List<Movie> listMoviesInCity(String cityId) {
        return showService.getAllShows().stream()
                .filter(s -> s.getScreen().getTheatre().getCity().getCityId().equals(cityId))
                .map(Show::getMovie)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Theatre> listTheatresInCity(String cityId) {
        return theatreService.listTheatresInCity(cityId);
    }

    @Override
    public List<Show> getShowsForMovie(String movieId) {
        return showService.getShowsForMovie(movieId);
    }

    @Override
    public List<ShowSeat> getAvailableSeats(String showId) {
        return showService.getAvailableSeats(showId);
    }

    @Override
    public Booking bookTickets(User user, String showId,
                               List<String> seatIds, PaymentMode mode) {
        return bookingService.bookTickets(user, showId, seatIds, mode);
    }

    @Override
    public void cancelBooking(String bookingId) {
        bookingService.cancelBooking(bookingId);
    }
}
