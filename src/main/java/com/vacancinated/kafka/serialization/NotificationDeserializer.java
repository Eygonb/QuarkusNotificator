package com.vacancinated.kafka.serialization;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.vacancinated.notifications.entity.Notification;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class NotificationDeserializer implements Deserializer<Notification> {
    private String encoding;

    public NotificationDeserializer() {
        this.encoding = StandardCharsets.UTF_8.name();
    }

    @Override
    public Notification deserialize(String s, byte[] bytes) {
        try {
            return bytes == null ? null : new Gson().fromJson(new String(bytes, encoding), Notification.class);
        } catch (UnsupportedEncodingException | JsonSyntaxException e) {
            System.err.println("Error when deserializing byte[] to string due to unsupported encoding " + encoding);
            return null;
        }
    }
}
