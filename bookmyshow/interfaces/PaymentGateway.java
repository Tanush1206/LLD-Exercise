package bookmyshow.interfaces;

import bookmyshow.enums.PaymentMode;
import bookmyshow.models.Payment;


public interface PaymentGateway {

    Payment processPayment(String bookingRef, double amount, PaymentMode mode);
}
