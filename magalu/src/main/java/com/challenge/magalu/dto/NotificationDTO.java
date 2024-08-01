package com.challenge.magalu.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record NotificationDTO(
        @NotBlank
        String message,
        @NotBlank
        LocalDateTime dateAndTime,
        @NotBlank
        String destination,
        @NotBlank
        Long idChannel,
        @NotBlank
        Long idUser
) {
}
