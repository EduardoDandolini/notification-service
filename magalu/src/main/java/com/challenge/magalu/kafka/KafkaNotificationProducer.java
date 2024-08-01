package com.challenge.magalu.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class KafkaNotificationProducer {

    private final Logger log = Logger.getLogger(KafkaNotificationProducer.class.getName());
    private final KafkaTemplate<String, NotificationConfirmation> kafkaTemplate;

    public void sendNotification(NotificationConfirmation notificationConfirmation){
        log.info("Sending notification");
        Message<NotificationConfirmation> message = MessageBuilder
                .withPayload(notificationConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "notification")
                .build();
        kafkaTemplate.send(message);
    }
}
