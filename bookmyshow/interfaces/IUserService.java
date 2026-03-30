package bookmyshow.interfaces;

import bookmyshow.enums.PaymentMode;
import bookmyshow.models.*;
import java.util.List;


public interface IUserService {
    List<Movie>    listMoviesInCity(String cityId);
    List<Theatre>  listTheatresInCity(String cityId);
    List<Show>     getShowsForMovie(String movieId);
    List<ShowSeat> getAvailableSeats(String showId);
    Booking        bookTickets(User user, String showId,
                               List<String> seatIds, PaymentMode mode);
    void           cancelBooking(String bookingId);
}
