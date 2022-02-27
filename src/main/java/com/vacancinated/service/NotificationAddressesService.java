package com.vacancinated.service;

import com.vacancinated.db.notificationAddresses.NotificationAddressesRepository;
import com.vacancinated.db.notificationAddresses.entity.NotificationAddress;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class NotificationAddressesService {
    @Inject
    NotificationAddressesRepository repository;

    public NotificationAddress add(NotificationAddress address) {
        repository.persist(address);
        return repository.findById(address.getId());
    }

    public NotificationAddress getById(UUID id) {
        return repository.findById(id);
    }

    public NotificationAddress getByIdAndUserId(UUID id, String userId) {
        return repository.findByIdAndUserId(id, userId);
    }

    public NotificationAddress update(UUID id, NotificationAddress address) {
        NotificationAddress entity = repository.findById(id);

        entity.setUserId(address.getUserId());
        entity.setAddress(address.getAddress());
        entity.setType(address.getType());

        return entity;
    }

    public boolean delete(UUID id) {
        return repository.deleteById(id);
    }

    public boolean deleteWithUserId(UUID id, String userId) {
        if (getByIdAndUserId(id, userId) != null) {
            return delete(id);
        }
        return false;
    }
}
