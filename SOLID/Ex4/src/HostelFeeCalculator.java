import java.util.*;

public class HostelFeeCalculator {

    private final FakeBookingRepo repo;
    private final PricingFactory factory;

    public HostelFeeCalculator(FakeBookingRepo repo, PricingFactory factory) {
        this.repo = repo;
        this.factory = factory;
    }

    public void process(BookingRequest req) {

        List<PricingComponent> components = new ArrayList<>();

        // Room pricing (int type)
        components.add(factory.room(req.roomType));

        // Add-ons
        for (AddOn addOn : req.addOns) {
            components.add(factory.addOn(addOn));
        }

        Money monthly = new Money(0.0);
        Money deposit = new Money(0.0);

        for (PricingComponent component : components) {
            monthly = monthly.plus(component.monthlyPrice());
            deposit = deposit.plus(component.deposit());
        }

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }
}