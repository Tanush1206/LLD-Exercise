import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;

    public HostelFeeCalculator(FakeBookingRepo repo) { this.repo = repo; }

    // OCP violation: switch + add-on branching + printing + persistence.
    public void process(BookingRequest req) {
        RoomStrategy room = getRoomStrategy(req.roomType);

        double base = room.basePrice() ; 
        double add = 0.0 ;
        for(AddOn a : req.addOns){
            add += getAddOnStrategy(a).cost() ; 
        }

        Money monthly = new Money(base+ add) ; 
        Money deposit = new Money(5000.0) ; 

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)) ; 
        repo.save(bookingId, req, monthly, deposit);
    }

    private RoomStrategy getRoomStrategy(int type){
        return switch(type){
            case LegacyRoomTypes.SINGLE -> new SingleRoom() ; 
            case LegacyRoomTypes.DOUBLE -> new DoubleRoom() ;
            case LegacyRoomTypes.TRIPLE -> new TripleRoom() ;
            default -> new DeluxeRoom() ; 
        } ; 
    }

    private AddOnStrategy getAddOnStrategy(AddOn a){
        return switch(a){
            case MESS -> new MessAddOn() ;
            case LAUNDRY -> new LaundryAddOn() ; 
            case GYM -> new GymAddOn() ;
        } ; 
    }
}
