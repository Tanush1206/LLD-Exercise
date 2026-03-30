package com.example.payments ; 

public class FastPayAdapter implements PaymentGateway {

    private final FastPayClient client ; 

    public FastPayAdapter(FastPayClient client) {
        if(client == null) throw new IllegalArgumentException("Client cannot be null") ;
        this.client = client ; 
    }

    @Override 
    public String charge(String customerId, int amountCents) {
        if(customerId == null || customerId.isEmpty()) throw new IllegalArgumentException("Customer ID cannot be null or empty") ;
        return client.payNow(customerId, amountCents) ;
    }
}