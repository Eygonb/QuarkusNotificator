package org.acme.notifications;

import org.acme.Main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/tg")
public class TelegramNotification {
    @GET
    public void sendNotification() {
        Main.send(899421726L, "TEST");
    }
}
