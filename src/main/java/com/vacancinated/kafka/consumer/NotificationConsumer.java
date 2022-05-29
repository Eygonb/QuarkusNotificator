package com.vacancinated.kafka.consumer;

import com.vacancinated.notifications.service.NotificatorManager;
import com.vacancinated.notifications.entity.Notification;
import io.smallrye.reactive.messaging.annotations.Blocking;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class NotificationConsumer {
    @Inject
    NotificatorManager notificatorManager;

    @Incoming("notifications")
    @Blocking
    public void consume(Notification notification) {
        if(notification != null) notificatorManager.send(notification);
    }
}
