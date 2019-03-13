package todoly.actions;

import todoly.helper.DateHelper;
import todoly.model.Task;

import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class EditTask implements Action {

    private List<Task> taskStore;

    public EditTask(ArrayList<Task> taskStore) {
        this.taskStore = taskStore;
    }

    @Override
    public void doAction() {
        System.out.println(">> Pick an option: (1) Update / (2) Mark as Done / (3) Remove ");
        Scanner scanner = new Scanner(System.in);
        int action = scanner.nextInt();

        System.out.println("Please enter the ID of task");
        int id = scanner.nextInt();
        Task task = getTaskById(id);
        if (task != null) {
            if (action == 1) {
                updateTask(task);
            } else if (action == 2) {
                markAsDone(task);
            } else if (action == 3) {
                removeTask(task);
            } else {
                System.out.println("Please enter a valid action code");
            }
        }
        else{
            System.out.println("Task does not exist with given id");
        }
    }

    private void updateTask(Task task) {

            System.out.println("Project > ");
            Scanner scanner = new Scanner(System.in);
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

    public void markAsDone(Task task) {
        task.setIsDone(true);
    }

    public void removeTask(Task task) {
        taskStore.remove(taskStore.indexOf(task));
    }

    private Task getTaskById(int id) {
        return taskStore.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

}

