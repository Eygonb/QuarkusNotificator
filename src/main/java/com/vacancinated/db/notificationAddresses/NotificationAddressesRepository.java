package com.vacancinated.db.notificationAddresses;

import com.vacancinated.db.notificationAddresses.entity.NotificationAddress;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class NotificationAddressesRepository implements PanacheRepositoryBase<NotificationAddress, UUID> {
    public List<NotificationAddress> findByUserId(String userId) {
        return list("user_id", userId);
    }

    public void deleteByUserId(String userId) {
        delete("user_id", userId);
    }

    public NotificationAddress findByIdAndUserId(UUID id, String userId) {
        return find("id = ?1 and user_id = ?2", id, userId).firstResult();
    }
}
