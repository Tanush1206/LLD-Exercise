public class GymAdd implements PricingComponent {
    @Override
    public Money monthlyPrice() {
        return new Money(300);
    }

    @Override
    public Money deposit() {
        return new Money(0);
    }
    
}
