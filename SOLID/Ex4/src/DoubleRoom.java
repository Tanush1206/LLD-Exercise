public class DoubleRoom implements PricingComponent{
    @Override
    public Money monthlyPrice() {
        return new Money(15000);
    }

    @Override
    public Money deposit() {
        return new Money(5000);
    }
}
