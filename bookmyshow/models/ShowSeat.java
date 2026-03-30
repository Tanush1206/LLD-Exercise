package bookmyshow.models;

/**
 * Represents one physical seat for a specific show.
 *
 * Concurrency contract:
 *   - lock()    : atomically reserves the seat during checkout.
 *   - confirm() : marks permanently booked after successful payment.
 *   - release() : frees the seat on payment failure or cancellation.
 *
 * This prevents two users from booking the same seat for the same show.
 */
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

    /** Try to lock. Returns false if already booked or locked by another thread. */
    public synchronized boolean lock() {
        if (isBooked || isLocked) return false;
        isLocked = true;
        return true;
    }

    /** Call after successful payment — seat is now permanently taken. */
    public synchronized void confirm() {
        isBooked = true;
        isLocked = false;
    }

    /** Call on payment failure or booking cancellation. */
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
