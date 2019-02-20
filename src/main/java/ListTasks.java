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

    }
}


