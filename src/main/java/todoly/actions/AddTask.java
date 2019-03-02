package todoly.actions;

import todoly.helper.DateHelper;
import todoly.model.Task;

import java.util.*;

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

        System.out.println("Due Date (Format should be :" + DateHelper.DATE_FORMAT + ")");
        scanner = new Scanner(System.in);
        String inputDueDate = scanner.nextLine();

        Date dueDate = DateHelper.toDate(inputDueDate);
        if (dueDate != null) {
            if (DateHelper.isDatePassed(dueDate)) {
                DateHelper.datePassedMessage();
            } else {
                task.setDueDate(dueDate);
                task.setIsDone(false);

                Integer maxID = taskStore.stream()
                        .map(Task::getId)
                        .reduce(Integer::max)
                        .orElse(0);

                task.setId(maxID + 1);
                taskStore.add(task);
                System.out.println("Successfully added!");
            }
        } else {
            System.out.println("Adding is not successful..");
        }
    }
}
