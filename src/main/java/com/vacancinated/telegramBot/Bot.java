package com.vacancinated.telegramBot;

import com.vacancinated.telegramBot.commands.service.StartCommand;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Bot extends TelegramLongPollingCommandBot {
    @ConfigProperty(name = "bot.name")
    String BOT_NAME;
    @ConfigProperty(name = "bot.token")
    String BOT_TOKEN;

    public Bot() {
        super();

        register(new StartCommand("start", "Получить chatId"));
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message msg = update.getMessage();
        Long chatId = msg.getChatId();

        sendMsg(chatId.toString(), "Извините, я не понимаю(");
    }
}
