package com.example.notification_app.utils.notifier;

public class NotificationDecorator implements Notification{
    Notification notification;

    NotificationDecorator(Notification notification){
        this.notification = notification;
    }

    @Override
    public void sendNotification() throws Exception {
        notification.sendNotification();
    }

    @Override
    public NotificationPayload getNotificationPayload() {
        return notification.getNotificationPayload();
    }
}
