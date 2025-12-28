package com.jayant.payment_service.gateway;

public interface PaymentGateway {
    boolean processPayment(Double amount, String userId);
}
