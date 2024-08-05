package com.ms.email_service.service;

import com.ms.email_service.dto.EmailDTO;
import com.ms.email_service.entity.Email;
import com.ms.email_service.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;
    private JavaMailSender javaMailSender;

    public void createEmail(EmailDTO dto) {
        emailRepository.save(
                Email.builder()
                        .id(UUID.randomUUID().toString())
                        .message(dto.message())
                        .destination(dto.destination())
                        .dateSubmission(LocalDateTime.now())
                        .build()
        );

        sendEmail(dto);
    }

    @Async
    private void sendEmail(EmailDTO dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("eduardodasilvadandolini@gmail.com");
        message.setTo(dto.userEmail());
        message.setText(String.format("Your notification has been successfully scheduled " + "Scheduled date: " + dto.dateAndTime()));
        javaMailSender.send(message);
    }
}
