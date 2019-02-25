import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AddTask {
    private List<Task> taskStore;

    public AddTask(List<Task> taskStore) {

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

        System.out.println("Due Date (Format should be : YYYY/MM/DD)> ");
        scanner = new Scanner(System.in);
        String inputDueDate = scanner.nextLine();
        Date dueDate = null;
        DateFormat dueDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            dueDate = dueDateFormat.parse(inputDueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        task.setDueDate(dueDate);

        task.setIsDone(false);

        if (taskStore.size() == 0) {
            task.setId(1);
        } else {
            Integer lastTaskId = taskStore.get(taskStore.size() - 1).getId();
            task.setId(lastTaskId + 1);
        }

        taskStore.add(task);
        System.out.println("Successfully added");
    }

}
