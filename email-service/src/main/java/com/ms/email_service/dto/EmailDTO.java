package com.ms.email_service.dto;

import jnr.constants.platform.Local;

import java.time.LocalDateTime;

public record EmailDTO(
        String message,
        String destination,
        String userEmail,
        LocalDateTime dateAndTime
) {
}
