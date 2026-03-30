package bookmyshow.interfaces;

import bookmyshow.models.*;


public interface IAdminService {
    Movie   addMovie(Movie movie);
    Theatre addTheatre(Theatre theatre);
    Show    addShow(Show show);
    void    deleteShow(String showId);
}
