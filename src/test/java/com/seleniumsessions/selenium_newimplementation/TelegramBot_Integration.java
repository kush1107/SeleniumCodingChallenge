package com.seleniumsessions.selenium_newimplementation;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBot_Integration extends TelegramLongPollingBot {

    private static TelegramBot_Integration botInstance;

    private TelegramBot_Integration() {
        // private constructor to prevent instantiation
    }

    public static synchronized TelegramBot_Integration getInstance() {
        if (botInstance == null) {
            botInstance = new TelegramBot_Integration();
            try {
                TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
                botsApi.registerBot(botInstance);
            } catch (TelegramApiException e) {
                e.printStackTrace();
                throw new RuntimeException("Error initializing Telegram bot", e);
            }
        }
        return botInstance;
    }

    @Override
    public String getBotUsername() {
        return "SeleniumAutomationBot";
    }

    @Override
    public String getBotToken() {
        return "6261106770:AAH22OWyMlV8spDS-2fY5YNoFFJepQmRH7Y";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();

        System.out.println(user.getFirstName() + " wrote " + msg.getText());
    }

    public void sendText(String who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) // Who are we sending a message to
                .text(what).build();    // Message content
        try {
            execute(sm);                // Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e); // Any error will be printed here
        }
    }

    // "@SeleniumTestChat" - chatID (Channel UserName - public )
    // "Hello World!"   - Message to be sent
    // To use this method just use - ClassName.method() as it is static method

    public static void sendTelegramMessage(String chatID_Channel, String messageToSend) {
        TelegramBot_Integration bot = getInstance();
        bot.sendText(chatID_Channel, messageToSend);
    }
}
