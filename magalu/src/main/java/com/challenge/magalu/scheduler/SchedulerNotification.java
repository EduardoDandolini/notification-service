package com.challenge.magalu.scheduler;

import com.challenge.magalu.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class SchedulerNotification {

    private final NotificationService service;
    private Logger log = Logger.getLogger(SchedulerNotification.class.getName());

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void checkNotification() {
        log.info("Starting Status Check");
        service.cheackNotificationAndSend(LocalDateTime.now());
    }
}
