package bookmyshow.services;

import bookmyshow.interfaces.PricingStrategy;
import bookmyshow.models.Show;
import bookmyshow.models.ShowSeat;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SRP: owns show lifecycle and seat availability.
 * DIP: depends on PricingStrategy interface, not a concrete impl.
 */
public class ShowService {

    private final Map<String, Show> showDB          = new HashMap<>();
    private final PricingStrategy   pricingStrategy;

    public ShowService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    /**
     * Persists a show and eagerly prices every ShowSeat using the injected strategy.
     */
    public Show addShow(Show show) {
        show.getShowSeatMap().values()
                .forEach(ss -> ss.setPrice(pricingStrategy.calculatePrice(ss)));
        showDB.put(show.getShowId(), show);
        return show;
    }

    public void deleteShow(String showId) {
        showDB.remove(showId);
    }

    public Show getShow(String showId) {
        return showDB.get(showId);
    }

    public Collection<Show> getAllShows() {
        return showDB.values();
    }

    /** All shows screening a particular movie. */
    public List<Show> getShowsForMovie(String movieId) {
        return showDB.values().stream()
                .filter(s -> s.getMovie().getMovieId().equals(movieId))
                .collect(Collectors.toList());
    }

    /** Seats that are neither booked nor temporarily locked. */
    public List<ShowSeat> getAvailableSeats(String showId) {
        Show show = showDB.get(showId);
        if (show == null) throw new IllegalArgumentException("Show not found: " + showId);

        return show.getShowSeatMap().values().stream()
                .filter(ss -> !ss.isBooked() && !ss.isLocked())
                .collect(Collectors.toList());
    }
}
