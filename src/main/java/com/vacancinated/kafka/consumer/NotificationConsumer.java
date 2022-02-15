package com.vacancinated.kafka.consumer;

import com.vacancinated.notifications.service.NotificatorManager;
import com.vacancinated.notifications.entity.Notification;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class NotificationConsumer {
    @Inject
    NotificatorManager notificatorManager;

    @Incoming("notifications")
    public void consume(Notification notification) {
        if(notification != null) notificatorManager.send(notification);
    }
}
