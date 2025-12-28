package com.jayant.payment_service.gateway.impl;

import com.jayant.payment_service.gateway.PaymentGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class MockPaymentGateway implements PaymentGateway {
    @Override
    public boolean processPayment(Double amount, String userId) {

        log.info("Connecting to Bank...");

        try {
            Thread.sleep(2000); // 2 seconds delay
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }

        if (amount == 400.00) {
            log.info("Bank Declined: Insufficient Funds");
            return false;
        }

        if (new Random().nextInt(10) == 0) {
            log.info("Bank Timeout: Gateway not responding");
            return false;
        }

        log.info("Bank Approved: Transaction Successful");
        return true;
    }
}
