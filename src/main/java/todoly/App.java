package todoly;

import todoly.actions.*;
import todoly.helper.TaskHelper;
import todoly.model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class App {

    public static Integer maxID;
    private Scanner scanner;

    public static void main(String[] args) {
        App td = new App();
        td.startApp();
    }

    private HashMap<String, Action> validActions;
    private static final String RESOURCE_FILE = "tasklist.ser";
    public ArrayList<Task> taskStore;

    public App() {
        taskStore = TaskHelper.readTasksFromFile(getResourceFile());

        maxID = taskStore.stream()
                .map(Task::getId)
                .reduce(Integer::max)
                .orElse(0);

    }

    public void startApp() {
        this.scanner = new Scanner(System.in);

        validActions = new HashMap<>();
        validActions.put("1", new ListTasks(taskStore));
        validActions.put("2", new AddTask(taskStore, scanner));
        validActions.put("3", new EditTask(taskStore));

        printMenuOptions();
        boolean finished = false;

        while (!finished) {
            String enteredCommand = null;
            if (scanner.hasNextLine()) {
                enteredCommand = scanner.nextLine();
            }
            finished = processCommand(enteredCommand);

            if (!finished) {
                System.out.println("============================================================");
                System.out.println(">> (1) Show Tasks (2) AddTask (3) Edit Task 4) Save and Quit");
                System.out.println("============================================================");
            }
        }
        System.out.println("Good bye.");
    }

    /**
     * Processes given command.
     */
    private boolean processCommand(String enteredCommand) {

        boolean wantToQuit = false;
        if (!enteredCommand.equals("4")) {
            Action action = validActions.get(enteredCommand);
            if (action != null) {
                action.doAction();
            } else {
                System.out.println("Unknown command");
            }
        } else {
            wantToQuit = true;
            writeTasksToFile();
        }

        return wantToQuit;
    }

    private void writeTasksToFile() {
        try {
            File file = getResourceFile();
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskStore);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Returns task counts by status. Gives an array with two elements.
     * First element is for to-do count, second element is for done count.
     */
    private int[] getTaskCountByStatus() {
        int[] counts = new int[2];
        int doneCount = 0;
        int toDoCount = 0;
        for (Task t : taskStore) {
            if (t.getIsDone()) {
                doneCount++;
            } else {
                toDoCount++;
            }
        }
        counts[0] = toDoCount;
        counts[1] = doneCount;
        return counts;
    }

    private void printMenuOptions() {
        System.out.println("===============================================");
        System.out.println(">> Welcome to ToDoLy");
        System.out.println();
        int[] taskCountByStatus = getTaskCountByStatus();
        System.out.println(">> You have " + taskCountByStatus[0] + " task TODO and " + taskCountByStatus[1] + " task DONE!");
        System.out.println();
        System.out.println(">> Pick an option:");
        System.out.println("===============================================");
        System.out.println(">> (1) Show Task List (by date or project)");
        System.out.println(">> (2) Add New Task");
        System.out.println(">> (3) Edit Task (update, mark as done, remove)");
        System.out.println(">> (4) Save and Quit");
        System.out.println("===============================================");
    }

    private File getResourceFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(App.RESOURCE_FILE)).getFile());
    }
}
