public class DefaultDiscountPolicy implements DiscountPolicy {

    @Override
    public double discountAmount(String customerType,
                                 double subtotal,
                                 int lineCount) {
        return DiscountRules.discountAmount(
                customerType,
                subtotal,
                lineCount
        );
    }
}