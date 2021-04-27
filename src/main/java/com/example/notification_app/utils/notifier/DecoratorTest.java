package com.example.notification_app.utils.notifier;

public class DecoratorTest {
    public static void main(String[] args) {
        Notification notification = new SlackNotifier(
                new EmailNotifier(
                        new BaseNotification("itgilangprambudi@gmail.com",
                                "Subject of Notification",
                                "Content")
                )
        );
        
        try {
            notification.sendNotification();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
