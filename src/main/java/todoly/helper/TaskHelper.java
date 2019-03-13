package todoly.helper;

import todoly.model.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class TaskHelper {

    public static String getIsDoneAsString(Task task) {
        return task.getIsDone() ? "DONE" : "TODO";
    }

    /**
     * Reads stored tasks from file and gives a list of task
     */
    public static ArrayList<Task> readTasksFromFile(File file) {
        ArrayList<Task> taskStore = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(file);
            if (fileIn.available() > 0) {
                ObjectInputStream in = new ObjectInputStream(fileIn);
                taskStore = (ArrayList<Task>) in.readObject();
                in.close();
            }
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return taskStore;
    }
}
