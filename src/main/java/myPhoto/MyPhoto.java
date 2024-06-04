package myPhoto;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;

public class MyPhoto {

    public SendPhoto createPhoto(final Long chatId, final File file){

        SendPhoto photo = new SendPhoto();
        photo.setChatId(chatId.toString());
        InputFile inputFile = new InputFile();
        inputFile.setMedia(file);
        photo.setPhoto(inputFile);

        return photo;

    }

}
