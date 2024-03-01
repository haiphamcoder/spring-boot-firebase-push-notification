package com.haiphamcoder.springbootfirebasepushnotification.controller;

import com.google.firebase.messaging.BatchResponse;
import com.haiphamcoder.springbootfirebasepushnotification.entity.Notice;
import com.haiphamcoder.springbootfirebasepushnotification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firebase")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/send-notification")
    public BatchResponse sendNotification(@RequestBody Notice notice){
        return notificationService.sendNotification(notice);
    }
}
