package com.example.notification_app.utils.notifier.dto;

import lombok.Builder;
import lombok.Data;
import org.json.JSONException;
import org.json.JSONObject;

@Builder
@Data
public class EmailServiceSendEmailRequest {
    public String to;
    public String subject;
    public String content;

    public JSONObject toJSONObject(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("to", this.to);
            jsonObject.put("subject", this.subject);
            jsonObject.put("content", this.content);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
