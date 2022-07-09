package com.vacancinated.notifications.impl;

import com.vacancinated.notifications.entity.NotificationType;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;
import com.vacancinated.notifications.Notificator;
import com.vacancinated.notifications.entity.Notification;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MailNotificator extends Notificator {
    @Inject
    Mailer mailer;

    @Override
    @Blocking
    public void sendNotification(String address, Notification notification) {
        try {
            mailer.send(Mail.withText(address, notification.getName(), notification.getDescription()));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public NotificationType getType() {
        return NotificationType.EMAIL;
    }
}