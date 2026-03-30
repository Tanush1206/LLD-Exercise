package bookmyshow.models;

import bookmyshow.enums.PaymentMode;
import bookmyshow.enums.PaymentStatus;
import java.time.LocalDateTime;

public class Payment {
    private final String        paymentId;
    private final double        amount;
    private final PaymentMode   mode;
    private final PaymentStatus status;
    private final LocalDateTime paidAt;

    public Payment(String paymentId, double amount, PaymentMode mode, PaymentStatus status) {
        this.paymentId = paymentId;
        this.amount    = amount;
        this.mode      = mode;
        this.status    = status;
        this.paidAt    = LocalDateTime.now();
    }

    public String        getPaymentId() { return paymentId; }
    public double        getAmount()    { return amount; }
    public PaymentMode   getMode()      { return mode; }
    public PaymentStatus getStatus()    { return status; }
    public LocalDateTime getPaidAt()    { return paidAt; }
}
