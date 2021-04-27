package com.example.notification_app.controller;

import com.example.notification_app.dto.NotificationRequest;
import com.example.notification_app.utils.notifier.BaseNotification;
import com.example.notification_app.utils.notifier.EmailNotifier;
import com.example.notification_app.utils.notifier.Notification;
import com.example.notification_app.utils.notifier.SlackNotifier;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/notification")
public class MainController {

    @PostMapping("/push/email")
    ResponseEntity<Map<String, Object>> pushEmailNotification(@RequestBody NotificationRequest request){
        Map<String, Object> response = new HashMap<>();
        Notification emailNotification = new EmailNotifier(new BaseNotification(request.recipientAddress,
                request.subject, request.content));
        try {
            emailNotification.sendNotification();
            response.put("message", "notification successfully sent to " + request.recipientAddress);
            response.put("code", HttpStatus.OK);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.put("message", "internal server error");
        response.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }


    @PostMapping("/push/slack")
    ResponseEntity<Map<String, Object>> pushSlackNotification(@RequestBody NotificationRequest request){
        Map<String, Object> response = new HashMap<>();
        Notification notifier = new SlackNotifier(new BaseNotification(request.recipientAddress,
                request.subject, request.content));
        try {
            notifier.sendNotification();
            response.put("message", "notification successfully sent to " + request.recipientAddress);
            response.put("code", HttpStatus.OK);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.put("message", "internal server error");
        response.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }



    @PostMapping("/push/slack-with-email")
    ResponseEntity<Map<String, Object>> pushSlackAndEmailNotificaiton(@RequestBody NotificationRequest request){
        Map<String, Object> response = new HashMap<>();
        Notification notifier = new SlackNotifier(new EmailNotifier(new BaseNotification(request.recipientAddress,
                request.subject, request.content)));
        try{
            notifier.sendNotification();
            response.put("message", "notification successfully sent to " + request.recipientAddress);
            response.put("code", HttpStatus.OK);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.put("message", "internal server error");
        response.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }



}
