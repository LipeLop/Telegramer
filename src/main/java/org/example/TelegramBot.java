package org.example;

import data.MyData;
import editorFileName.EditorFileName;
import fileSearching.FileSearching;
import formatMyMessage.FormatOfMessage;
import myMessage.MyMessage;
import myPhoto.MyPhoto;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static data.MyData.PreparedText;

public class TelegramBot extends TelegramLongPollingBot {

    private final Properties properties;



    private final StringBuilder currentMessageFromUser = new StringBuilder();

    public TelegramBot() {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return properties.getProperty("bot.name");
    }

    @Override
    public String getBotToken() {
        return properties.getProperty("bot.token");
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            MyMessage creatorOfMessage = new MyMessage();
            Integer messageID = update.getCallbackQuery().getMessage().getMessageId();
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            deleteMessage(messageID, chatId);
            switch (data) {
                case "name" -> {
                    currentMessageFromUser.append("name ");
                    FormatOfMessage format = new FormatOfMessage();
                    MyMessage message = creatorOfMessage.createMessage(chatId, format.intoItalic(PreparedText.get("Запрос названия")), "HTML");
                    sendMessage(message);
                }
                case "word" -> {
                    currentMessageFromUser.append("word ");
                    FormatOfMessage format = new FormatOfMessage();
                    MyMessage message = creatorOfMessage.createMessage(chatId, format.intoItalic(PreparedText.get("Запрос слова")), "HTML");
                    sendMessage(message);
                }
                case "about" -> {
                    MyMessage message = creatorOfMessage.createMessageWithKeyboard(chatId, PreparedText.get("about"), createReplyKeyboardMarkup());
                    sendMessage(message);
                }
            }
        } else if (update.getMessage().hasText()) {
            MyMessage creatorOfMessage = new MyMessage();
            Long chatId = update.getMessage().getChatId();
            currentMessageFromUser.append(update.getMessage().getText());
            if (currentMessageFromUser.toString().equalsIgnoreCase("/start")) {
                MyMessage message = creatorOfMessage.createMessageWithKeyboard(chatId, PreparedText.get("Приветствие"), createReplyKeyboardMarkup());
                sendMessage(message);
            } else if (currentMessageFromUser.toString().startsWith("name ")) {
                String title = currentMessageFromUser.substring(5);
                findTitleOfBook(chatId, title);
            } else if (currentMessageFromUser.toString().startsWith("word ")) {
                String word = currentMessageFromUser.substring(5);
                findWordOfBook(chatId, word);
            }
            clearMessageFromUser();
        }
    }

    private void clearMessageFromUser() {
        currentMessageFromUser.setLength(0);
    }

    private void deleteMessage(final Integer messageId, final Long chatId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setMessageId(messageId);
        deleteMessage.setChatId(chatId);
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    private void findWordOfBook(final Long chatId, final String word) {
        MyMessage creatorOfMessage = new MyMessage();
        FormatOfMessage format = new FormatOfMessage();
        MyMessage message1 = creatorOfMessage.createMessage(chatId, PreparedText.get("Ожидание"), "MARKDOWN");
        sendMessage(message1);
        List<File> foundBooksWithWord = MyData.indexing_words.get(word);
        if (foundBooksWithWord != null) {
            EditorFileName editorFileName = new EditorFileName();
            MyMessage message2 = creatorOfMessage.createMessage(chatId, format.intoItalic(PreparedText.get("Слово найдено")), "HTML");
            sendMessage(message2);
            for (int i = 0; i < foundBooksWithWord.size(); i++) {
                String clearNameOfFile = format.intoBig((i + 1) + "." + " " + editorFileName.removeExtension(foundBooksWithWord.get(i).getName()));
                MyMessage message3 = creatorOfMessage.createMessage(chatId, clearNameOfFile, "MARKDOWN");
                sendMessage(message3);
            }
        } else {
            MyMessage message4 = creatorOfMessage.createMessage(chatId, PreparedText.get("Не найдено"), "MARKDOWN");
            sendMessage(message4);
        }
        MyMessage message5 = creatorOfMessage.createMessageWithKeyboard(chatId, "Еще?", createReplyKeyboardMarkup());
        sendMessage(message5);
    }


    private void findTitleOfBook(final long chatId, final String title) {
        MyMessage creatorOfMessage = new MyMessage();
        FormatOfMessage format = new FormatOfMessage();
        MyMessage message = creatorOfMessage.createMessage(chatId, PreparedText.get("Ожидание"), "MARKDOWN");
        sendMessage(message);
        FileSearching fileSearching = new FileSearching();
        File book = fileSearching.findFileByName(MyData.books, title);
        File cover = fileSearching.findFileByName(MyData.pictures, title);
        if (book != null) {
            MyPhoto myPhoto = new MyPhoto();
            MyMessage message1 = creatorOfMessage.createMessage(chatId, PreparedText.get("Произведение найдено"), "MARKDOWN");
            sendMessage(message1);
            File author = MyData.Book_and_Author.get(book);
            EditorFileName editorFileName = new EditorFileName();
            MyMessage message2 = creatorOfMessage.createMessage(chatId, format.intoBig("Произведение: ") + editorFileName.removeExtension(title), "MARKDOWN");
            sendMessage(message2);
            if (cover != null) {
                SendPhoto photo = myPhoto.createPhoto(chatId, cover);
                sendPhoto(photo);
            }
            MyMessage message3 = creatorOfMessage.createMessage(chatId, format.intoBig("Автор: ") + editorFileName.removeExtension(author.getName()), "MARKDOWN");
            sendMessage(message3);
            SendPhoto photo = myPhoto.createPhoto(chatId, author);
            sendPhoto(photo);
            MyMessage message4 = creatorOfMessage.createMessage(chatId, format.intoBig("Цитата из книги: "), "MARKDOWN");
            sendMessage(message4);
            MyMessage message5 = creatorOfMessage.createMessage(chatId, format.intoQuote(MyData.Book_and_SmartWords.get(book)), "HTML");
            sendMessage(message5);
        } else {
            MyMessage message4 = creatorOfMessage.createMessage(chatId, PreparedText.get("Не найдено"), "MARKDOWN");
            sendMessage(message4);
        }
        MyMessage message5 = creatorOfMessage.createMessageWithKeyboard(chatId, PreparedText.get("Еще?"), createReplyKeyboardMarkup());
        sendMessage(message5);

    }

    private void sendPhoto(SendPhoto sendPhoto) {
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage(MyMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private InlineKeyboardMarkup createReplyKeyboardMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineKeyboardButtons = new ArrayList<>();
        List<InlineKeyboardButton> list1 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton("Найти произведение по названию");
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton("Найти произведение по слову");
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton("О чат-боте");
        inlineKeyboardButton1.setCallbackData("name");
        inlineKeyboardButton2.setCallbackData("word");
        inlineKeyboardButton3.setCallbackData("about");
        list1.add(inlineKeyboardButton1);
        list1.add(inlineKeyboardButton2);
        list1.add(inlineKeyboardButton3);
        inlineKeyboardButtons.add(list1);
        inlineKeyboardMarkup.setKeyboard(inlineKeyboardButtons);
        return inlineKeyboardMarkup;
    }

}
