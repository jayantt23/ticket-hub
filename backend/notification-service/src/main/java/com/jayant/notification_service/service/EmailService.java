package com.jayant.notification_service.service;

public interface EmailService {
    void sendBookingConfirmation(String toEmail, Long bookingId, String txnId);
    void sendBookingCancellation(String toEmail, Long bookingId, String txnId);
}
