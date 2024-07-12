package formatMyMessage;

/*
    Преобразование сообщения в италику, жирный или цитату
 */
public class FormatOfMessage {

    public String intoItalic(String text){
        return "<i>" + text + "</i>";
    }
    public String intoBig(String text){
        return "*" + text + "*";
    }
    public String intoQuote(String text){
        return "<pre>" + text + "</pre>";
    }


}
