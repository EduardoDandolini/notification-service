package com.challenge.magalu.controller;

import com.challenge.magalu.dto.NotificationDTO;
import com.challenge.magalu.entity.Notification;
import com.challenge.magalu.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public Notification sendNotification(@RequestBody NotificationDTO request){
        return notificationService.sendNotification(request);
    }

    @DeleteMapping("/cancel/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelNotification(@PathVariable Long id){
        notificationService.deleteNotification(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Notification findNotificationById(@PathVariable Long id){
        return notificationService.findNotificationById(id);
    }
}
