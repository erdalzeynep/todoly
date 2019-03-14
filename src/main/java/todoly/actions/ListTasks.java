package todoly.actions;

import todoly.model.Task;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static todoly.helper.DateHelper.getDueDateAsString;
import static todoly.helper.TaskHelper.getIsDoneAsString;

public class ListTasks implements Action {
    private final Scanner scanner;
    private List<Task> taskStore;


    public ListTasks(List<Task> taskStore, Scanner scanner) {
        this.taskStore = taskStore;
        this.scanner = scanner;
    }

    @Override
    public void doAction() {
        System.out.println(">> (1) Show All Tasks (2) By Project");
        String input = scanner.nextLine();

        if (input.equals("1")) {
            if (taskStore.size() == 0) {
                System.out.println("There is no task to list");
            } else {
                Collections.sort(taskStore);
                printTaskList(taskStore);
            }

        } else if (input.equals("2")) {
            System.out.println("Enter the project :");
            String enteredProject = scanner.nextLine();
            Collections.sort(taskStore);
            List<Task> filteredList = taskStore.stream().filter(task -> task.getProject().equals(enteredProject)).collect(Collectors.toList());
            int records = filteredList.size();
            printTaskList(filteredList);
            if (records == 0) {
                System.out.println("There is no task related with the project: " + enteredProject);
            }
        }
    }

    private void printTaskList(List<Task> taskStore) {
        System.out.println("===============================================================================================================");
        System.out.printf("%-10s%-40s%-40s%-12s%5s", "ID", "PROJECT", "TITLE", "DUE DATE", "STATUS");
        System.out.println();
        System.out.println("===============================================================================================================");

        taskStore.forEach(task -> System.out.format("%-10s%-40s%-40s%-12s%5s\n", task.getId(), task.getProject(), task.getTitle(),
                getDueDateAsString(task), getIsDoneAsString(task)));
        System.out.println("\n\n");
    }
}
