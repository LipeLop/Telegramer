package fileSearching;

import java.io.File;

public class FileSearching {

    public File findFileByName(File[] files, String filename) {
        for (File file : files) {
            if (file.getName().contains(filename)) {
                return file;
            }
        }
        return null;
    }

}
