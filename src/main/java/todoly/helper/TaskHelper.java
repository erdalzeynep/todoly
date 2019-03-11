package todoly.helper;

import todoly.model.Task;

public class TaskHelper {

    public static String getIsDoneAsString(Task task) {
        return task.getIsDone() ? "DONE" : "TODO";
    }
}
