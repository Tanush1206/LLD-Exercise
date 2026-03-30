package com.example.payments ; 

public class SafeCashAdapter implements PaymentGateway {
    private final SafeCashClient client ; 

    public SafeCashAdapter(SafeCashClient client) {
        if(client == null) throw new IllegalArgumentException("Client cannot be null") ;
        this.client = client ; 
    }

    @Override
    public String charge(String customerId, int amountCents) {
        if(customerId == null || customerId.isEmpty()) throw new IllegalArgumentException("Customer ID cannot be null or empty") ;
        SafeCashPayment payment = new SafeCashPayment(amountCents, customerId) ;
        return payment.confirm() ; 
    }
}