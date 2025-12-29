package com.jayant.notification_service.service.impl;

import com.jayant.notification_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendBookingConfirmation(String toEmail, Long bookingId, String txnId) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("TicketHub Booking Confirmed!");
            message.setText("Hello,\n\n" +
                    "Your booking (ID: " + bookingId + ") was successfully confirmed.\n" +
                    "Transaction ID: " + txnId + "\n\n" +
                    "Enjoy the show!\n" +
                    "The TicketHub Team");
            javaMailSender.send(message);
            log.info("Email sent to {}", toEmail);
        } catch (Exception e) {
            log.error("Failed to send email to {}", toEmail);
        }
    }

    @Override
    public void sendBookingCancellation(String toEmail, Long bookingId, String txnId) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("TicketHub Booking Cancelled!");
            message.setText("Hello,\n\n" +
                    "Your booking (ID: " + bookingId + ") failed.\n" +
                    "Transaction ID: " + txnId + "\n\n" +
                    "L!\n" +
                    "The TicketHub Team");
            javaMailSender.send(message);
            log.info("Email sent to {}", toEmail);
        } catch (Exception e) {
            log.error("Failed to send email to {}", toEmail);
        }
    }
}
