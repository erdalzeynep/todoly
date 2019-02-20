import java.util.List;
import java.util.Scanner;

public class AddTask {
    private List<Task> taskStore;

    public AddTask(List<Task> taskStore){

        this.taskStore = taskStore;
    }

    public void addTask() {
        Task task = new Task();
        System.out.println("Project > ");
        Scanner scanner = new Scanner(System.in);
        task.setProject(scanner.nextLine());

        System.out.println("Title > ");
        scanner = new Scanner(System.in);
        task.setTitle(scanner.nextLine());

        System.out.println("Due Date > ");
        scanner = new Scanner(System.in);
        task.setDueDate(scanner.nextLine());

        task.setStatus("todo");
        Integer lastTaskId = taskStore.get(taskStore.size() - 1).getId();
        task.setId(lastTaskId + 1);
        taskStore.add(task);

        System.out.println("Successfully added");
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
