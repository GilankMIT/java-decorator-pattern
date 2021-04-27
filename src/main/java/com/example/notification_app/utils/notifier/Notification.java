package com.example.notification_app.utils.notifier;

public interface Notification {
    void sendNotification() throws Exception;
    NotificationPayload getNotificationPayload();
}
