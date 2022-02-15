package com.vacancinated.resources;

import com.vacancinated.db.notificationAddresses.NotificationAddressesRepository;
import com.vacancinated.db.notificationAddresses.entity.NotificationAddress;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Path("/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotificationAddressesResource {
    @Inject
    NotificationAddressesRepository repository;

    @POST
    @Transactional
    public Response save(NotificationAddress address) {
        repository.persist(address);
        return Response.created(URI.create("/persons/" + address.getId().toString())).build();
    }

    @GET
    @Path("/{addressId}")
    public NotificationAddress get(@PathParam("addressId") UUID id) {
        return repository.findById(id);
    }

    @PUT
    @Path("/{addressId}")
    @Transactional
    public NotificationAddress update(@PathParam("addressId") UUID id, NotificationAddress address) {
        return repository.findById(id);
    }

    @DELETE
    @Path("/{addressId}")
    @Transactional
    public void delete(@PathParam("addressId") UUID id) {
        repository.deleteById(id);
    }

    @GET
    @Path("/{userId}")
    public List<NotificationAddress> getByUserId(@PathParam("userId") String userId) {
        return repository.findByUserId(userId);
    }

    @DELETE
    @Path("/{userId}")
    @Transactional
    public void deleteByUserId(@PathParam("userId") String userId) {
        repository.deleteByUserId(userId);
    }
}
