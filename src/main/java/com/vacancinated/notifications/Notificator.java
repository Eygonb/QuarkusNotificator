package com.vacancinated.notifications;

import com.vacancinated.notifications.entity.Notification;
import com.vacancinated.notifications.entity.NotificationType;

public abstract class Notificator {
    public abstract void sendNotification(String address, Notification notification);

    public abstract NotificationType getType();
}
