package todoly.actions;
import todoly.model.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ListTasks {
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
            List<Task> filteredList = new ArrayList<>();
            System.out.println("Enter the project :");
            String enteredProject = scanner.next();
            int records = 0;
            orderByDate(taskStore);
            for (Task t : taskStore) {
                if (t.getProject().equals(enteredProject)) {
                    filteredList.add(t);
                    records += 1;
                }
            }
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
        DateFormat dateFormat = new SimpleDateFormat(Task.DATE_FORMAT);
        String dueDate;
        String status;
        System.out.println("===============================================================================================================");
        System.out.printf("%-10s%-40s%-40s%-12s%5s", "ID", "PROJECT", "TITLE", "DUE DATE", "STATUS");
        System.out.println();
        System.out.println("===============================================================================================================");

        for (int i = 0; i < taskStore.size(); i++) {
            if (taskStore.get(i).getIsDone()) {
                status = "DONE";
            } else {
                status = "TODO";
            }
            dueDate = dateFormat.format(taskStore.get(i).getDueDate());
            System.out.format("%-10s%-40s%-40s%-12s%5s",
                    taskStore.get(i).getId().toString(), taskStore.get(i).getProject(), taskStore.get(i).getTitle(), dueDate, status);
            System.out.println();
        }
        System.out.println("\n\n");
    }
}
