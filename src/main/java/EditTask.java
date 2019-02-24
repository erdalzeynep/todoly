import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;


public class EditTask {

    private List<Task> taskStore;

    public EditTask(ArrayList<Task> taskStore) {
        this.taskStore = taskStore;
    }

    public void editTask() {
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

        System.out.println("Project > ");
        Scanner scanner1 = new Scanner(System.in);
        String project = scanner1.nextLine();
        taskStore.get(getIndex(id)).setProject(project);

        System.out.println("Title > ");
        Scanner scanner2 = new Scanner(System.in);
        String title = scanner2.nextLine();
        taskStore.get(getIndex(id)).setTitle(title);

        System.out.println("Due Date (Format should be : YYYY/MM/DD) > ");
        Scanner scanner3 = new Scanner(System.in);
        String inputDueDate = scanner3.nextLine();
        Date dueDate = null;
        DateFormat dueDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            dueDate = dueDateFormat.parse(inputDueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        taskStore.get(getIndex(id)).setDueDate(dueDate);
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
        int index = 0;
        for (Task t : taskStore) {
            if (t.getId() == id) {
                index = taskStore.indexOf(t);
            }
        }
        return index;
    }

    public void successMessage() {
        System.out.println("Successfull..");
    }

    public void editMessage() {
        System.out.println("==============================================================");
        System.out.println(">> Pick an option: (1) Update / (2) Mark as Done / (3) Remove ");
    }
}