package com.treishvaam.finance.service;

import com.treishvaam.finance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(User user) {
        if (user.getEmail() == null || user.getVerificationCode() == null) return;
        String subject = "Verify your account";
        String verificationUrl = "http://yourdomain.com/api/auth/verify?code=" + user.getVerificationCode();
        String text = "Dear " + user.getUsername() + ",\nPlease verify your account by clicking the link: " + verificationUrl;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
