package com.ms.email_service.kafka;

import java.time.LocalDateTime;

public record NotificationDTO(
        String message,
        String destination,
        LocalDateTime dateAndTime,
        String userEmail
) {
}
