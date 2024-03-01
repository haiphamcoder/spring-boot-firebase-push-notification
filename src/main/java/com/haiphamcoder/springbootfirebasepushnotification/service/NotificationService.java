package com.haiphamcoder.springbootfirebasepushnotification.service;

import com.google.firebase.messaging.*;
import com.haiphamcoder.springbootfirebasepushnotification.entity.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
    private final FirebaseMessaging firebaseMessaging;

    /**
     * Send notification to multiple devices
     * @param notice the notice object
     * @return the batch response
     */
    public BatchResponse sendNotification(Notice notice) {
        // Get the registration tokens
        List<String> registrationTokens = notice.getRegistrationTokens();

        // Create the notification
        Notification notification = Notification.builder()
                .setTitle(notice.getTitle())
                .setBody(notice.getContent())
                .setImage(notice.getImage())
                .build();

        // Create the multicast message
        MulticastMessage multicastMessage = MulticastMessage.builder()
                .setNotification(notification)
                .putAllData(notice.getData())
                .addAllTokens(registrationTokens)
                .build();

        // Send the notification
        BatchResponse batchResponse = null;
        try{
            batchResponse = firebaseMessaging.sendMulticast(multicastMessage);
        } catch (Exception e) {
            log.error("Error sending notification: {}", e.getMessage());
        }

        // Log the response if there are failures
        if(batchResponse != null && batchResponse.getFailureCount() > 0){
            List<SendResponse> responses = batchResponse.getResponses();
            List<String> failedTokens = new ArrayList<>();
            for (int i = 0; i < responses.size(); i++) {
                if (!responses.get(i).isSuccessful()) {
                    failedTokens.add(registrationTokens.get(i));
                }
            }
            log.info("List of tokens that caused failures: {}", failedTokens);
        }

        return batchResponse;
    }
}
