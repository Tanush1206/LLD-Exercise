package bookmyshow.models;


public class ShowSeat {
    private final Seat    seat;
    private final Show    show;
    private       boolean isBooked;
    private       boolean isLocked;
    private       double  price;

    public ShowSeat(Seat seat, Show show) {
        this.seat     = seat;
        this.show     = show;
        this.isBooked = false;
        this.isLocked = false;
    }

    public synchronized boolean lock() {
        if (isBooked || isLocked) return false;
        isLocked = true;
        return true;
    }

    public synchronized void confirm() {
        isBooked = true;
        isLocked = false;
    }

    public synchronized void release() {
        isLocked = false;
        isBooked = false;
    }

    public Seat    getSeat()   { return seat; }
    public Show    getShow()   { return show; }
    public boolean isBooked()  { return isBooked; }
    public boolean isLocked()  { return isLocked; }
    public double  getPrice()  { return price; }
    public void    setPrice(double price) { this.price = price; }
}
