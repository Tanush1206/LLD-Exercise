package bookmyshow.models;

import bookmyshow.enums.SeatType;

public class Seat {
    private final String   seatId;
    private final String   rowLabel;
    private final int      seatNumber;
    private final SeatType seatType;
    private final Screen   screen;

    public Seat(String seatId, String rowLabel, int seatNumber,
                SeatType seatType, Screen screen) {
        this.seatId     = seatId;
        this.rowLabel   = rowLabel;
        this.seatNumber = seatNumber;
        this.seatType   = seatType;
        this.screen     = screen;
    }

    public String   getSeatId()     { return seatId; }
    public String   getRowLabel()   { return rowLabel; }
    public int      getSeatNumber() { return seatNumber; }
    public SeatType getSeatType()   { return seatType; }
    public Screen   getScreen()     { return screen; }
}
