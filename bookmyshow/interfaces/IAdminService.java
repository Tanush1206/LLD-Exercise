package bookmyshow.interfaces;

import bookmyshow.models.*;

/**
 * ISP fix: exposes only operations an admin client needs.
 * User operations are in IUserService.
 */
public interface IAdminService {
    Movie   addMovie(Movie movie);
    Theatre addTheatre(Theatre theatre);
    Show    addShow(Show show);
    void    deleteShow(String showId);
}
