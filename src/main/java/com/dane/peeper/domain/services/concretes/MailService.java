package com.dane.peeper.domain.services.concretes;

import com.dane.peeper.domain.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendRegistrationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.email);
        message.setSubject("Peeper registration confirmation");
        message.setText(String.format("Welcome %s %s thank you for joining!", user.firstName, user.lastName));
        this.mailSender.send(message);
    }
}
