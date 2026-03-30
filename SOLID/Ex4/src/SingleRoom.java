public class SingleRoom implements PricingComponent{
    @Override
    public Money monthlyPrice() {
        return new Money(14000);
    }

    @Override
    public Money deposit() {
        return new Money(3000);
    }
}
