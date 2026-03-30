package bookmyshow.interfaces;

import bookmyshow.enums.PaymentMode;
import bookmyshow.models.Payment;

/**
 * Abstracts all payment processing behind a single interface.
 *
 * DIP fix: BookingService depends on this interface, never on a
 * concrete gateway (Razorpay, Stripe, UPI stub, etc.).
 * OCP fix:  new gateways are added as new implementations — no
 * changes needed in BookingService.
 */
public interface PaymentGateway {
    /**
     * Process a payment and return a Payment record.
     * Throws RuntimeException if the transaction fails.
     */
    Payment processPayment(String bookingRef, double amount, PaymentMode mode);
}
