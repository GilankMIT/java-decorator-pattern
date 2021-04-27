package com.example.notification_app.utils.notifier;

import com.example.notification_app.utils.HttpRequestManager;
import com.example.notification_app.utils.notifier.dto.EmailServiceSendEmailRequest;

public class EmailNotifier extends NotificationDecorator{
    private final static String EMAIL_SERVICE_URL = "http://localhost:9001/send-email";
    private NotificationPayload payload;

    public EmailNotifier(Notification notification){
        super(notification);
        this.payload = notification.getNotificationPayload();
    }

    @Override
    public void sendNotification() throws Exception {
        super.sendNotification();
        EmailServiceSendEmailRequest sendEmailRequest = EmailServiceSendEmailRequest.builder()
                                                        .to(this.payload.getRecipientAddress())
                                                        .subject(this.payload.getSubject())
                                                        .content(this.payload.getContent()).build();
        String results = HttpRequestManager.getInstance().
                postUrlConnection(EMAIL_SERVICE_URL, sendEmailRequest.toJSONObject());
        System.out.println(results);
    }
}
