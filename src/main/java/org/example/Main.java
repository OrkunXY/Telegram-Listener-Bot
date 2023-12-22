package org.example;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

public class Main {
    public static void main(String[] args) {
        // Telegram bot token'ını buraya ekleyin
        String botToken = "your-bot-token";

        // TelegramBot nesnesini oluştur
        TelegramBot bot = new TelegramBot(botToken);

        // Bot'un durumunu kontrol et
        System.out.println("Bot is starting...");

        // Bot mesajları dinlemeye başla
        bot.setUpdatesListener(updates -> {
            // Gelen her güncellemeyi kontrol et
            for (Update update : updates) {
                // Gelen mesajı al
                String messageText = update.message().text();
                long chatId = update.message().chat().id();

                // Gelen mesajı ekrana yazdır
                System.out.println("Received message: " + messageText + " from chat ID: " + chatId);

                // Gelen mesajı aynı kişiye geri gönder
                SendResponse response = bot.execute(new SendMessage(chatId, "You said: " + messageText));

                // Mesaj gönderildi mi kontrol et
                if (response.isOk()) {
                    System.out.println("Message sent successfully");
                } else {
                    System.out.println("Error sending message: " + response.description());
                }
            }

            // Güncelleme işlendi, true dön
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
