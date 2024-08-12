package com.ms.email_service.kafka;

import com.ms.email_service.dto.EmailDTO;
import com.ms.email_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailService emailService;
    private Logger log = Logger.getLogger(EmailConsumer.class.getName());

    @KafkaListener(topics = "notification")
    public void consumerNotification(NotificationConfirmation dto){
        log.info("Message received");
        emailService.createEmail(new EmailDTO(dto.message(), dto.destination(), dto.userEmail(), dto.dateAndTime()));
    }
}
