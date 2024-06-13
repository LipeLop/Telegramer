package data;


import fileSearching.FileSearching;
import org.example.IndexingTexts;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/*
 Класс, в котором подготавливается информация о книгах, картинках, цитатах и т.п.
 */
public class MyData {
    public static File[] pictures = new File(Objects.requireNonNull(MyData.class.getClassLoader().getResource("pictures")).getFile()).listFiles();
    public static File[] books = new File(Objects.requireNonNull(MyData.class.getClassLoader().getResource("books")).getFile()).listFiles();
    public static HashMap<String, List<File>> indexing_words;

    public static HashMap<File, File> Book_and_Author = new HashMap<>();

    public static HashMap<File, String> Book_and_SmartWords = new HashMap<>();

    public static HashMap<String, String> PreparedText = new HashMap<>();

    public static void init() {
        FileSearching fileSearching = new FileSearching();
        IndexingTexts indexingTexts = new IndexingTexts();
        indexing_words = indexingTexts.IndexingTextsInMap(books);

        File Scarlet_Sails = fileSearching.findFileByName(books, "Алые паруса.txt");
        Book_and_Author.put(Scarlet_Sails, fileSearching.findFileByName(pictures, "Александр Грин.jpg"));

        Book_and_SmartWords.put(Scarlet_Sails, "\"Но есть не меньшие чудеса: улыбка, веселье, прощение, и – вовремя сказанное, нужное слово. Владеть этим – значит владеть всем.\"");

        File Anna_Karenina = fileSearching.findFileByName(books, "Анна Каренина.txt");
        Book_and_Author.put(Anna_Karenina,
                fileSearching.findFileByName(pictures, "Толстой Лев Николаевич.jpg"));

        Book_and_SmartWords.put(Anna_Karenina, "\"Все счастливые семьи похожи друг на друга, каждая несчастливая семья несчастлива по-своему.\"");


        File Demons = fileSearching.findFileByName(books, "Бесы.txt");
        Book_and_Author.put(Demons,
                fileSearching.findFileByName(pictures, "Достоевский Федор Михайлович.jpg"));

        Book_and_SmartWords.put(Demons, "\"Видно, правда, что вся вторая половина человеческой жизни составляется обыкновенно из одних только накопленных в первую половину привычек.\"");

        File Brothers = fileSearching.findFileByName(books, "Братья Карамазовы.txt");
        Book_and_Author.put(Brothers,
                fileSearching.findFileByName(pictures, "Толстой Лев Николаевич.jpg"));
        Book_and_SmartWords.put(Brothers, "\"В большинстве случаев люди, даже злодеи, гораздо наивнее и простодушнее, чем мы вообще о них заключаем. Да и мы сами тоже.\"");
        File Hero_Our_Time = fileSearching.findFileByName(books, "Герой нашего времени.txt");
        Book_and_Author.put(Hero_Our_Time,
                fileSearching.findFileByName(pictures, "Лермонтов Михаил Юрьевич.jpg"));
        Book_and_SmartWords.put(Hero_Our_Time, "\"Я люблю сомневаться во всем: это расположение ума не мешает решительности характера – напротив, " +
                "что до меня касается, то я всегда смелее иду вперед, когда не знаю, что меня ожидает. " +
                "Ведь хуже смерти ничего не случится – а смерти не минуешь!\"");

        File Garnet_bracelet = fileSearching.findFileByName(books, "Гранатовый браслет.txt");
        Book_and_Author.put(Garnet_bracelet,
                fileSearching.findFileByName(pictures, "Куприн Александр Иванович.jpg"));

        Book_and_SmartWords.put(Garnet_bracelet, "\"Любовь должна быть трагедией. Величайшей тайной в мире! Никакие жизненные удобства, расчеты и компромиссы не должны ее касаться.\"");

        File Stupid = fileSearching.findFileByName(books, "Идиот.txt");

        Book_and_Author.put(Stupid,
                fileSearching.findFileByName(pictures, "Достоевский Федор Михайлович.jpg"));

        Book_and_SmartWords.put(Stupid, "\"дура с сердцем и без ума такая же несчастная дура, как и дура с умом без сердца\"");


        File Daughter = fileSearching.findFileByName(books, "Капитанская дочка.txt");

        Book_and_Author.put(Daughter,
                fileSearching.findFileByName(pictures, "Пушкин Александр Сергеевич.jpg"));

        Book_and_SmartWords.put(Daughter, "\"береги платье снову, а честь смолоду\"");

        File Dead_Souls = fileSearching.findFileByName(books, "Мертвые души.txt");

        Book_and_Author.put(Dead_Souls,
                fileSearching.findFileByName(pictures, "Гоголь Николай Васильевич.jpg"));

        Book_and_SmartWords.put(Dead_Souls, "\"Как ни глупы слова дурака, а иногда бывают они достаточны, чтобы смутить умного человека.\"");

        File Morph = fileSearching.findFileByName(books, "Морфий.txt");

        Book_and_Author.put(Morph,
                fileSearching.findFileByName(pictures, "Булгаков Михаил Афанасьевич.jpg"));

        Book_and_SmartWords.put(Morph, "\"Только Через страдание приходит истина...\"");

        File Bottom = fileSearching.findFileByName(books, "На дне.txt");

        Book_and_Author.put(Bottom,
                fileSearching.findFileByName(pictures, "Максим Горький.jpg"));

        Book_and_SmartWords.put(Bottom, "\"Ты – только пьяный и похож на человека\"");

        File Oblom = fileSearching.findFileByName(books, "Обломов.txt");

        Book_and_Author.put(Oblom,
                fileSearching.findFileByName(pictures, "Гончаров Иван Александрович.jpg"));

        Book_and_SmartWords.put(Oblom, "\"Ученье-то не уйдет, а здоровья не купишь.\"");

        File Fathers_and_Sons = fileSearching.findFileByName(books, "Отцы и дети.txt");

        Book_and_Author.put(Fathers_and_Sons,
                fileSearching.findFileByName(pictures, "Тургенев Иван Сергеевич.jpg"));

        Book_and_SmartWords.put(Fathers_and_Sons, "\"Природа не храм, а мастерская, и человек в ней работник.\"");

        File Crime_and_Punishment = fileSearching.findFileByName(books, "Преступление и наказание.txt");

        Book_and_Author.put(Crime_and_Punishment,
                fileSearching.findFileByName(pictures, "Достоевский Федор Михайлович.jpg"));

        Book_and_SmartWords.put(Crime_and_Punishment, "\"Тварь ли я дрожащая или право имею…\"");

        File Taras = fileSearching.findFileByName(books, "Тарас Бульба.txt");

        Book_and_Author.put(Taras,
                fileSearching.findFileByName(pictures, "Гоголь Николай Васильевич.jpg"));

        Book_and_SmartWords.put(Taras, "\"Терпи, казак, – атаманом будешь!\"");




        PreparedText.put("Приветствие", "Здравствуйте! Этот бот разработан для поиска произведений по названию и по словам!" +
                " Пожалуйста, выберете то, что вам нужно.");

        PreparedText.put("Запрос названия", "Введите, пожалуйста, название книги");

        PreparedText.put("Произведение найдено", "Успешно! Такая книга у нас есть");


        PreparedText.put("Запрос слова", "Введите, пожалуйста, слово");

        PreparedText.put("Слово найдено", "Нашли ваше слово в следующих произведениях: ");

        PreparedText.put("Ожидание", "Ищем, пожалуйста, подождите!");

        PreparedText.put("Не найдено", "Извините, не смогли найти");


        PreparedText.put("Еще?", "Может хотите что-нибудь еще?");

        PreparedText.put("about", "Бот был разработан студентом группы 8К22 Агаров Даниил. Служит для поиска произведений по названию или по слову." +
                " Надеюсь бот вам понравится :)");



    }

}
