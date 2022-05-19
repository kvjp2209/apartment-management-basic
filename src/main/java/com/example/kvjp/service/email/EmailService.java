package com.example.kvjp.service.email;

import com.example.kvjp.model.Receivable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {
    @Autowired
    public JavaMailSender emailSender;

    public String sendSimpleEmail(String setTo, String setSubject, String setText) {
        try {
            // Create a Simple MailMessage.
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(setTo);
            message.setSubject(setSubject);
            message.setText(setText);

            // Send Message!
            this.emailSender.send(message);
            return "Email Sent!";
        } catch (Exception e) {
            e.printStackTrace();
            return "sent failed";
        }
    }

}
