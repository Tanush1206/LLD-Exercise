package bookmyshow.models;

import bookmyshow.enums.ScreenType;
import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final String     screenId;
    private final String     name;
    private final ScreenType screenType;
    private final Theatre    theatre;
    private final List<Seat> seats;

    public Screen(String screenId, String name, ScreenType screenType, Theatre theatre) {
        this.screenId   = screenId;
        this.name       = name;
        this.screenType = screenType;
        this.theatre    = theatre;
        this.seats      = new ArrayList<>();
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public String     getScreenId()   { return screenId; }
    public String     getName()       { return name; }
    public ScreenType getScreenType() { return screenType; }
    public Theatre    getTheatre()    { return theatre; }
    public List<Seat> getSeats()      { return seats; }
}
