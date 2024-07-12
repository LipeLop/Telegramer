package editorFileName;

/*
    Класс для удаления расширения из названия файла
 */
public class EditorFileName {

    public  String removeExtension(String fileName) {
        int extensionIndex = fileName.lastIndexOf('.');
        if (extensionIndex == -1) {
            return fileName;
        } else {
            return fileName.substring(0, extensionIndex);
        }
    }
}

