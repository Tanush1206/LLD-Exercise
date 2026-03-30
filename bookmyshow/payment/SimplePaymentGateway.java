package bookmyshow.payment;

import bookmyshow.enums.PaymentMode;
import bookmyshow.enums.PaymentStatus;
import bookmyshow.interfaces.PaymentGateway;
import bookmyshow.models.Payment;


public class SimplePaymentGateway implements PaymentGateway {

    @Override
    public Payment processPayment(String bookingRef, double amount, PaymentMode mode) {
        String paymentId = "PAY-" + bookingRef + "-" + System.currentTimeMillis();
        return new Payment(paymentId, amount, mode, PaymentStatus.SUCCESS);
    }
}
