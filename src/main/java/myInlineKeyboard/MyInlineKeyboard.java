package myInlineKeyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/*
    Класс с клавиатурой
 */
public class MyInlineKeyboard extends InlineKeyboardMarkup {

    public MyInlineKeyboard() {
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
    }
}
