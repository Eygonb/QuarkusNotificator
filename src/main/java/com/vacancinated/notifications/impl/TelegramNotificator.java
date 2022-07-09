package com.vacancinated.notifications.impl;

import com.vacancinated.notifications.Notificator;
import com.vacancinated.notifications.entity.Notification;
import com.vacancinated.notifications.entity.NotificationType;
import com.vacancinated.telegramBot.Bot;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class TelegramNotificator extends Notificator {
    @Inject
    Bot bot;

    @Override
    public void sendNotification(String address, Notification notification) {
        StringBuffer message = new StringBuffer();
        message.append("Событие: ");
        message.append(notification.getName());
        message.append("\n");
        message.append(notification.getDescription());
        message.append("\nНачало: ");
        message.append(notification.getStartTime());
        try {
            bot.sendMsg(address, message.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public NotificationType getType() {
        return NotificationType.TELEGRAM;
    }
}