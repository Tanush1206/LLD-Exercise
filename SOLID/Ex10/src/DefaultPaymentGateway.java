public class DefaultPaymentGateway implements PaymentGateway {
    public String charge(String studentId, double amount) {
        // fake deterministic txn
        return "TXN-9001";
    }
}
