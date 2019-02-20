import java.util.List;

public class ListTasks {
    private List<Task> taskStore;

    public ListTasks(List<Task> taskStore) {

        this.taskStore = taskStore;
    }

    public void showTaskList() {

        System.out.println("TASK LIST:");
        for (int i = 0; i < taskStore.size(); i++) {
            Task currentTask = taskStore.get(i);
            System.out.println(currentTask.getId() + " " + currentTask.getProject() + " " + currentTask.getTitle() + " " + currentTask.getDueDate() + " " + currentTask.getStatus());
        }
        printMessage();
    }

    public void printMessage() {
        System.out.println("\n");
        System.out.println(">> Pick an option:");
        System.out.println(">> (1) Show Task ListTasks (by date or project)");
        System.out.println(">> (2) AddTask New Task");
        System.out.println(">> (3) Edit Task (update, mark as done, remove)");
        System.out.println(">> (4) Save and Quit");
    }
}
