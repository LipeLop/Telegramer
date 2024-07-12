package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

// Класс, создающий словарь со словами в файлах
public class IndexingTexts {
    public HashMap<String, List<File>> IndexingTextsInMap(File[] files) {
        HashMap<String, List<File>> map = new HashMap<>();
        for (File file : files) {
            try (Scanner scanner = new Scanner(new FileInputStream(file), "UTF-8")) {
                while (scanner.hasNext()) {
                    String word = scanner.next().replaceAll("[^а-яА-ЯёЁa-zA-Z]", "");
                    if (!word.isEmpty()) {
                        if (!map.containsKey(word)) {
                            map.put(word, new ArrayList<>());
                        }
                        List<File> fileList = map.get(word);
                        if (!fileList.contains(file)) {
                            fileList.add(file);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
