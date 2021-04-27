package com.example.notification_app.utils.notifier;

import com.example.notification_app.utils.HttpRequestManager;
import org.json.JSONException;
import org.json.JSONObject;

public class SlackNotifier extends NotificationDecorator{
    private static final String SLACK_WEBHOOK_POST_URL =
            "<YOUR SLACK WEBHOOK URL>";

    public SlackNotifier(Notification notification){
        super(notification);
    }

    @Override
    public void sendNotification() throws Exception {
        super.sendNotification();
        sendToSlack(super.getNotificationPayload());
    }

    @Override
    public NotificationPayload getNotificationPayload() {
        return super.getNotificationPayload();
    }

    private void sendToSlack(NotificationPayload notificationPayload) throws JSONException {
        JSONObject slackJSONObject = new JSONObject();
        slackJSONObject.put("text", notificationPayload.getSubject() + "\n" + notificationPayload.getContent());
        HttpRequestManager.getInstance().postUrlConnection(SLACK_WEBHOOK_POST_URL, slackJSONObject);
    }
}
