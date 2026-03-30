package bookmyshow.models;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Show {
    private final String                 showId;
    private final Movie                  movie;
    private final Screen                 screen;
    private final LocalDateTime          startTime;

    private final Map<String, ShowSeat> showSeatMap;

    public Show(String showId, Movie movie, Screen screen, LocalDateTime startTime) {
        this.showId      = showId;
        this.movie       = movie;
        this.screen      = screen;
        this.startTime   = startTime;
        this.showSeatMap = new HashMap<>();
        initShowSeats();
    }

    private void initShowSeats() {
        for (Seat seat : screen.getSeats()) {
            showSeatMap.put(seat.getSeatId(), new ShowSeat(seat, this));
        }
    }

    public String               getShowId()      { return showId; }
    public Movie                getMovie()        { return movie; }
    public Screen               getScreen()       { return screen; }
    public LocalDateTime        getStartTime()    { return startTime; }
    public Map<String, ShowSeat> getShowSeatMap() { return showSeatMap; }

    public ShowSeat getShowSeat(String seatId) {
        return showSeatMap.get(seatId);
    }
}
