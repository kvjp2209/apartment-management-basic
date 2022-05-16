package com.example.kvjp.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
