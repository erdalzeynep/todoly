import java.util.List;
import java.util.Scanner;

public class ListTasks {
    private List<Task> taskStore;

    public ListTasks(List<Task> taskStore) {
        this.taskStore = taskStore;
    }

    public void showTaskList() {
        System.out.println(">> (1) Show All Tasks (2) By Project (3) By Date");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        if (input == 1) {
            System.out.println("TASK LIST: (ID-PROJECT-TITLE-DUE DATE-STATUS)");
            System.out.println("=============================================");
            if (taskStore.size() == 0) {
                System.out.println("There is no task to list");
            }
            for (int i = 0; i < taskStore.size(); i++) {
                Task currentTask = taskStore.get(i);
                System.out.println(currentTask.toString());
            }

        } else if (input == 2) {
            System.out.println("Enter the project :");
            String enteredProject = scanner.next();
            int records = 0;
            for (Task t : taskStore) {
                if (t.getProject().equals(enteredProject)) {
                    System.out.println(t.toString());
                    records++;
                }
            }
            if (records == 0) {
                System.out.println("There is no task related with the project: " + enteredProject);
            }

        } else if (input == 3) {

        }
    }
}
