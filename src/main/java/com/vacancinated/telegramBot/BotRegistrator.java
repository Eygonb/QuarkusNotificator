package com.vacancinated.telegramBot;

import io.quarkus.runtime.StartupEvent;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

public class BotRegistrator {
    @Inject
    Bot bot;

    void onStart(@Observes StartupEvent ev) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
