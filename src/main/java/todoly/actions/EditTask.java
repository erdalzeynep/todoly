package todoly.actions;

import todoly.helper.DateHelper;
import todoly.model.Task;

import java.util.*;

public class EditTask implements Action {

    private final Scanner scanner;
    private List<Task> taskStore;
    private List<String> validUpdateCommands;

    public EditTask(ArrayList<Task> taskStore, Scanner scanner) {
        initializeValidUpdateCommandsList();
        this.taskStore = taskStore;
        this.scanner = scanner;
    }

    private void initializeValidUpdateCommandsList() {
        validUpdateCommands = Arrays.asList("1", "2", "3");
    }

    /**
     * Gets update option from user and checks if given option is valid.
     * If given option is valid, gets the task id from user that will be edited and finds related task record and triggers related method.
     * If given option is not valid or task can not be found, gives an appropriate message to the user.
     */
    @Override
    public void doAction() {
        System.out.println(">> Pick an option: (1) Update / (2) Mark as Done / (3) Remove ");
        String action = scanner.nextLine();
        if (!validUpdateCommands.contains(action)) {
            System.out.println("Please enter a valid option");
        } else {
            System.out.println("Please enter the ID of task");
            int id = Integer.valueOf(scanner.nextLine());
            Task task = getTaskById(id);
            if (task != null) {
                if (action.equals("1")) {
                    updateTask(task);
                } else if (action.equals("2")) {
                    markAsDone(task);
                } else if (action.equals("3")) {
                    removeTask(task);
                }
            } else {
                System.out.println("Task does not exist with given id");
            }
        }
    }

    private void updateTask(Task task) {

        System.out.println("Project > ");
        String project = scanner.nextLine();

        System.out.println("Title > ");
        String title = scanner.nextLine();

        System.out.println("Due Date (Format should be :" + DateHelper.DATE_FORMAT + ") > ");
        String inputDueDate = scanner.nextLine();
        Date dueDate = DateHelper.toDate(inputDueDate);

        if (dueDate != null) {
            if (DateHelper.isDatePassed(dueDate)) {
                System.out.println("Operation is not successful, Due Date should not be a passed date");
            } else {
                task.setProject(project);
                task.setTitle(title);
                task.setDueDate(dueDate);
            }
        } else {
            System.out.println("Updating is not successful..");
        }
    }

    private void markAsDone(Task task) {
        task.setIsDone(true);
    }

    private void removeTask(Task task) {
        taskStore.remove(task);
    }

    private Task getTaskById(int id) {
        return taskStore.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }
}
