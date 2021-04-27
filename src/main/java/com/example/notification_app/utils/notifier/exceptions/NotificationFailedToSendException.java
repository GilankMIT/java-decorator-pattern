package com.example.notification_app.utils.notifier.exceptions;

public class NotificationFailedToSendException extends Exception{
    private static final String errorMessage = "FAILED TO SEND NOTIFICATION";
    public NotificationFailedToSendException(){
        super(errorMessage);
    }
}
