package com.kimikevin.web_socket_trial.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void notifyStudents(String message) {
        // Implementation to notify students (e.g., via WebSockets, push notifications, etc.)
        System.out.println("Notification: " + message);
    }
}
