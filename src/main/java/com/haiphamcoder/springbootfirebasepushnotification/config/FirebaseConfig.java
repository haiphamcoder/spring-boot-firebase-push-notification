package com.haiphamcoder.springbootfirebasepushnotification.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class FirebaseConfig {
    private final FirebaseProperties firebaseProperties;

    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        // Load the Firebase credentials from the file
        FileInputStream serviceAccount = new FileInputStream(firebaseProperties.getGoogleCredentials());
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(serviceAccount);

        // Initialize the Firebase app with the credentials
        FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions);

        return FirebaseMessaging.getInstance(app);
    }
}
