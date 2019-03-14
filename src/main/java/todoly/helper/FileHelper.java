package todoly.helper;

import java.io.File;
import java.io.IOException;

public class FileHelper {
    public static File getDataFile() throws IOException {
        String OS = (System.getProperty("os.name")).toUpperCase();
        String dataFile;
        if (OS.contains("WIN")) {
            dataFile = System.getenv("AppData");
        } else {
            dataFile = System.getProperty("user.home");
        }

        dataFile += File.separator + ".todoly" + File.separator + "tasklist.ser";

        File file = new File(dataFile);
        file.getParentFile().mkdir();
        file.createNewFile();
        return file;
    }
}
