package com.example.notification_app.utils.notifier;

public class BaseNotification implements Notification{

    private NotificationPayload notificationPayload;

    public BaseNotification(String recipientAddress, String subject, String content){
        this.notificationPayload = NotificationPayload.builder()
                                                    .recipientAddress(recipientAddress)
                                                    .subject(subject)
                                                    .content(content).build();
    }

    @Override
    public void sendNotification() throws Exception {
        System.out.println("Base notification do nothing");
    }

    public NotificationPayload getNotificationPayload(){
        return this.notificationPayload;
    }
}
