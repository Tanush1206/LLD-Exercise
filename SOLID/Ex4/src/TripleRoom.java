public class TripleRoom implements PricingComponent{
    @Override
    public Money monthlyPrice() {
        return new Money(12000);
    }

    @Override
    public Money deposit() {
        return new Money(4000);
    }

}
