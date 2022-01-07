package org.acme;

import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.Quarkus;
import org.acme.telegramBot.Bot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

@QuarkusMain
public class Main {
    private static final Map<String, String> getenv = System.getenv();
    private static final Bot bot = new Bot(getenv.get("BOT_NAME"), getenv.get("BOT_TOKEN"));

    public static void main(String ... args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        Quarkus.run(args);
    }

    public static void send(Long chatId, String text) {
        bot.sendMsg(chatId, text);
    }
}