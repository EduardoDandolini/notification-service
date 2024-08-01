package com.challenge.magalu.service;

import com.challenge.magalu.kafka.KafkaNotificationProducer;
import com.challenge.magalu.kafka.NotificationConfirmation;
import com.challenge.magalu.dto.NotificationDTO;
import com.challenge.magalu.entity.Channel;
import com.challenge.magalu.entity.Notification;
import com.challenge.magalu.entity.Status;
import com.challenge.magalu.entity.User;
import com.challenge.magalu.repository.ChannelRepository;
import com.challenge.magalu.repository.NotificationRepository;
import com.challenge.magalu.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final KafkaNotificationProducer kafkaNotificationProducer;

    @Transactional(rollbackFor = Exception.class)
    public Notification sendNotification(NotificationDTO dto) {
        User user = findUserById(dto.idUser());
        Notification notification = notificationRepository.save(Notification.builder()
                .message(dto.message())
                .dateAndTime(dto.dateAndTime())
                .destination(dto.destination())
                .channel(findChannelById(dto.idChannel()))
                .user(user)
                .status(Status.Values.PENDING.toStatus())
                .build()
        );

        sendNotificationWithKafka(new NotificationConfirmation(
                dto.message(),
                dto.destination(),
                dto.dateAndTime(),
                user.getEmail()
        ));

        return notification;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteNotification(Long id) {
        var notification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found with id: " + id));
        notification.setStatus(Status.Values.CANCELED.toStatus());

        notificationRepository.save(notification);
    }

    @Transactional(readOnly = true)
    public Notification findNotificationById(Long id) {
        return notificationRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Notification not found with id: " + id));
    }

    public void cheackNotificationAndSend(LocalDateTime dateAndTime) {
        var notifications = notificationRepository.findNotificationByDateAndTimeBeforeAndStatus(dateAndTime, Status.Values.PENDING.toStatus());
        notifications.forEach(setStatusBySucess());
    }

    private Consumer<Notification> setStatusBySucess() {
        return n -> {
            n.setStatus(Status.Values.SUCCESS.toStatus());
            notificationRepository.save(n);
        };
    }

    private Channel findChannelById(Long id) {
        return channelRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Channel not found with id: " + id));
    }

    private User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not found with id: " + id));
    }

    private void sendNotificationWithKafka(NotificationConfirmation notificationConfirmation) {
        kafkaNotificationProducer.sendNotification(notificationConfirmation);
    }
}
