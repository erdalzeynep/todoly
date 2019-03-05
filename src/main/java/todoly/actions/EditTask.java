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
        editMessage();
        Scanner scanner1 = new Scanner(System.in);
        int action = scanner1.nextInt();
        System.out.println("Please enter the ID of task");
        Scanner scanner2 = new Scanner(System.in);
        int id = scanner2.nextInt();
        getIndex(id);
        if (action == 1) {
            updateTask(id);
        } else if (action == 2) {
            markAsDone(id);
        } else if (action == 3) {
            removeTask(id);
        } else {
            System.out.println("Please enter a valid action code");
        }
    }

    private void updateTask(int id) {
        Task task = getTaskById(id);

        if (task == null) {
            System.out.println("Task does not exist with given id");
        } else {
            System.out.println("Project > ");
            Scanner scanner1 = new Scanner(System.in);
            String project = scanner1.nextLine();

            System.out.println("Title > ");
            Scanner scanner2 = new Scanner(System.in);
            String title = scanner2.nextLine();

            System.out.println("Due Date (Format should be :" + DateHelper.DATE_FORMAT + ") > ");
            Scanner scanner3 = new Scanner(System.in);
            String inputDueDate = scanner3.nextLine();
            Date dueDate = DateHelper.toDate(inputDueDate);

            if (dueDate != null) {
                if (DateHelper.isDatePassed(dueDate)) {
                    DateHelper.datePassedMessage();
                } else {
                    task.setProject(project);
                    task.setTitle(title);
                    task.setDueDate(dueDate);
                    System.out.println("Update is successful");
                }
            } else {
                System.out.println("Updating is not successful..");
            }
        }
    }

    public void markAsDone(int id) {
        taskStore.get(getIndex(id)).setIsDone(true);
        successMessage();
    }

    public void removeTask(int id) {
        taskStore.remove(getIndex(id));
        successMessage();
    }

    public int getIndex(int id) {
        return taskStore.indexOf(getTaskById(id));
    }

    private Task getTaskById(int id) {
        return taskStore.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public void successMessage() {
        System.out.println("Successfull..");
    }

    public void editMessage() {
        System.out.println(">> Pick an option: (1) Update / (2) Mark as Done / (3) Remove ");
    }
}
