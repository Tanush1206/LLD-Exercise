public class DeluxeRoom implements PricingComponent {
    @Override
    public Money monthlyPrice() {
        return new Money(16000);
    }

    @Override
    public Money deposit() {
        return new Money(5000);
    }
    
}
