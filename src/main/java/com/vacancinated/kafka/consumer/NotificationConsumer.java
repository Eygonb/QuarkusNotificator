package com.vacancinated.kafka.consumer;

import com.vacancinated.notifications.service.NotificatorManager;
import com.vacancinated.notifications.entity.Notification;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class NotificationConsumer {
    @Inject
    NotificatorManager notificatorManager;

    @Incoming("notifications")
    @Blocking
    public CompletionStage<Void> consume(Message<Notification> msg) {
        if (msg.getPayload() != null) {
            notificatorManager.send(msg.getPayload());
        }
        return msg.ack();
    }
}
