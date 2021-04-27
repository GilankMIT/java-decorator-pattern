package com.example.notification_app.utils.notifier;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NotificationPayload {
    private String recipientAddress;
    private String subject;
    private String content;
}
