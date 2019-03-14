package todoly.actions;

import todoly.App;
import todoly.helper.DateHelper;
import todoly.model.Task;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AddTask implements Action {

    private List<Task> taskStore;
    private final Scanner scanner;

    public AddTask(List<Task> taskStore, Scanner scanner) {
        this.taskStore = taskStore;
        this.scanner = scanner;
    }

    /**
     * Gets Project name, Title and Due date from user, generates an id for task
     * and sets status to-do by default. Creates a task object with these information and adds it to task list.
     */
    @Override
    public void doAction(){
        App.maxID++;

        System.out.println("Project > ");
        String project = scanner.nextLine();

        System.out.println("Title > ");
        String title = scanner.nextLine();

        System.out.println("Due Date (Format should be :" + DateHelper.DATE_FORMAT + ")");
        String inputDueDate = scanner.nextLine();

        Date dueDate = DateHelper.toDate(inputDueDate);
        if (dueDate != null) {
            if (DateHelper.isDatePassed(dueDate)) {
                System.out.println("Operation is not successful, Due Date should not be a passed date");
            } else {
                taskStore.add(new Task(App.maxID, project, title, dueDate, false));
                System.out.println("Successfully added!");
            }
        } else {
            System.out.println("Adding is not successful..");
        }
    }
}
