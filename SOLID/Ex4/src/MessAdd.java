public class MessAdd implements PricingComponent{

    @Override
    public Money monthlyPrice() {
        return new Money(1000);
    }

    @Override
    public Money deposit() {
        return new Money(0);
    }
    
}
