package todoly.actions;

import todoly.App;
import todoly.helper.DateHelper;
import todoly.model.Task;

import java.util.*;

public class AddTask implements Action {

    private List<Task> taskStore;

    public AddTask(List<Task> taskStore) {
        this.taskStore = taskStore;
    }

    @Override
    public void doAction() {
        App.maxID++;

        System.out.println("Project > ");
        Scanner scanner = new Scanner(System.in);
        String project = scanner.nextLine();

        System.out.println("Title > ");
        String title = scanner.nextLine();

        System.out.println("Due Date (Format should be :" + DateHelper.DATE_FORMAT + ")");
        String inputDueDate = scanner.nextLine();

        Date dueDate = DateHelper.toDate(inputDueDate);
        if (dueDate != null) {
            if (DateHelper.isDatePassed(dueDate)) {
                DateHelper.datePassedMessage();
            } else {
                taskStore.add(new Task(App.maxID, project, title, dueDate, false));
                System.out.println("Successfully added!");
            }
        } else {
            System.out.println("Adding is not successful..");
        }
    }
}
