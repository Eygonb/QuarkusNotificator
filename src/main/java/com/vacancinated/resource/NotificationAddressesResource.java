package com.vacancinated.resource;

import com.vacancinated.db.notificationAddresses.entity.NotificationAddress;
import com.vacancinated.service.NotificationAddressesService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.UUID;

@Path("/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotificationAddressesResource {
    @Inject
    NotificationAddressesService service;
    @Inject
    JsonWebToken jwt;

    @POST
    @Transactional
    public Response save(NotificationAddress address) {
        if (checkJwt()) {
            address.setUserId(jwt.getClaim("sub"));
            service.add(address);
            return Response.created(URI.create("/addresses/" + address.getId().toString())).entity(address).build();
        }
        return Response.status(401).build();
    }

    @GET
    @Path("/{addressId}")
    public Response get(@PathParam("addressId") UUID id) {
        if (checkJwt()) {
            String userId = jwt.getClaim("sub");
            NotificationAddress address = service.getByIdAndUserId(id, userId);
            if (address != null) {
                return Response.ok().entity(address).build();
            }
            return Response.status(404).build();
        }
        return Response.status(401).build();
    }

    @PUT
    @Path("/{addressId}")
    @Transactional
    public Response update(@PathParam("addressId") UUID id, NotificationAddress address) {
        if (checkJwt()) {
            address.setUserId(jwt.getClaim("sub"));
            NotificationAddress updatedAddress = service.update(id, address);
            return Response.ok().entity(updatedAddress).build();
        }
        return Response.status(401).build();
    }

    @DELETE
    @Path("/{addressId}")
    @Transactional
    public Response delete(@PathParam("addressId") UUID id) {
        if (checkJwt()) {
            String userId = jwt.getClaim("sub");
            if (service.deleteWithUserId(id, userId)) {
                return Response.ok().build();
            }
            return Response.status(404).build();
        }
        return Response.status(401).build();
    }

    private boolean checkJwt() {
        return jwt.containsClaim("sub") && jwt.getClaim("sub") != null;
    }
}
