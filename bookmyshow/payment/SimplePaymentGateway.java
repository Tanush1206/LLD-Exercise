package bookmyshow.payment;

import bookmyshow.enums.PaymentMode;
import bookmyshow.enums.PaymentStatus;
import bookmyshow.interfaces.PaymentGateway;
import bookmyshow.models.Payment;

/**
 * Stub gateway — always succeeds.
 *
 * DIP: BookingService depends on PaymentGateway (interface), not this class.
 * To swap in Razorpay/Stripe: implement PaymentGateway and inject it.
 */
public class SimplePaymentGateway implements PaymentGateway {

    @Override
    public Payment processPayment(String bookingRef, double amount, PaymentMode mode) {
        // In production: call external payment API here.
        String paymentId = "PAY-" + bookingRef + "-" + System.currentTimeMillis();
        return new Payment(paymentId, amount, mode, PaymentStatus.SUCCESS);
    }
}
