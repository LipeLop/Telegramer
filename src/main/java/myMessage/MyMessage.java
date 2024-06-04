package myMessage;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class MyMessage extends SendMessage {

    public MyMessage createMessageWithKeyboard(final long chatId, String text, final InlineKeyboardMarkup keyboardMarkup) {
        MyMessage sendMessage = new MyMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setReplyMarkup(keyboardMarkup);
        return sendMessage;
    }

    public MyMessage createMessage(final long chatId, final String text, String parseMode) {
        MyMessage sendMessage = new MyMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setParseMode(parseMode);
        return sendMessage;
    }

}
