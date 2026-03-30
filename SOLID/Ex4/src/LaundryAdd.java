public class LaundryAdd implements PricingComponent{

    @Override
    public Money monthlyPrice() {
        return new Money(500);
    }

    @Override
    public Money deposit() {
        return new Money(0);
    }
    
}
