package com.vacancinated.notifications.service;

import com.vacancinated.db.notificationAddresses.NotificationAddressesRepository;
import com.vacancinated.db.notificationAddresses.entity.NotificationAddress;
import com.vacancinated.notifications.Notificator;
import com.vacancinated.notifications.entity.Notification;
import com.vacancinated.notifications.entity.NotificationType;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class NotificatorManager {
    private Instance<Notificator> notificators;
    NotificationAddressesRepository notificationAddressesRepository;

    private Map<NotificationType, Notificator> notificatorMap = new HashMap<>();

    NotificatorManager(Instance<Notificator> notificators,
                       NotificationAddressesRepository notificationAddressesRepository) {
        this.notificators = notificators;
        this.notificationAddressesRepository = notificationAddressesRepository;

        for(Notificator n : this.notificators) {
            notificatorMap.put(n.getType(), n);
        }
    }

    public void send(Notification notification) {
        List<NotificationAddress> addresses = notificationAddressesRepository.findByUserId(notification.getUserId());
        for(NotificationAddress address : addresses) {
            Notificator notificator = notificatorMap.get(address.getType());
            if (notificator != null) {
                notificator.sendNotification(address.getAddress(), notification);
            }
        }
    }
}
