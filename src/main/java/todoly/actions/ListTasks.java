package todoly.actions;

import todoly.helper.DateHelper;
import todoly.model.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListTasks {

    private static final DateFormat DATE_FORMATTER = new SimpleDateFormat(DateHelper.DATE_FORMAT);

    private List<Task> taskStore;

    public ListTasks(List<Task> taskStore) {
        this.taskStore = taskStore;
    }

    public void showTaskList() {
        System.out.println(">> (1) Show All Tasks (2) By Project");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        if (input == 1) {
            if (taskStore.size() == 0) {
                System.out.println("There is no task to list");
            } else {
                orderByDate(taskStore);
                printList(taskStore);
            }

        } else if (input == 2) {
            List<Task> filteredList;
            System.out.println("Enter the project :");
            String enteredProject = scanner.next();
            orderByDate(taskStore);
            filteredList = taskStore.stream().filter(task -> task.getProject().equals(enteredProject)).collect(Collectors.toList());
            int records = filteredList.size();
            printList(filteredList);
            if (records == 0) {
                System.out.println("There is no task related with the project: " + enteredProject);
            }
        }
    }

    public void orderByDate(List<Task> taskStore) {
        Collections.sort(taskStore);
    }

    public void printList(List<Task> taskStore) {
        System.out.println("===============================================================================================================");
        System.out.printf("%-10s%-40s%-40s%-12s%5s", "ID", "PROJECT", "TITLE", "DUE DATE", "STATUS");
        System.out.println();
        System.out.println("===============================================================================================================");

        taskStore.forEach(task -> {
            String status = task.getIsDone() ? "DONE" : "TODO";
            String dueDate = DATE_FORMATTER.format(task.getDueDate());
            System.out.format("%-10s%-40s%-40s%-12s%5s\n", task.getId(), task.getProject(), task.getTitle(), dueDate, status);
        });
        System.out.println("\n\n");
    }
}
