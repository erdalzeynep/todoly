package todoly;

import todoly.actions.*;
import todoly.model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        App td = new App();
        td.startApp();
    }

    private HashMap<String, Action> validActions;
    private static final String RESOURCE_FILE = "tasklist.ser";
    private ArrayList<Task> taskStore;

    private App() {
        taskStore = readFromFile();

        validActions = new HashMap<>();
        validActions.put("1", new ListTasks(taskStore));
        validActions.put("2", new AddTask(taskStore));
        validActions.put("3", new EditTask(taskStore));
    }

    private void startApp() {

        printWelcome();
        boolean finished = false;

        while (!finished) {

            String enteredCommand = null;
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()) {
                enteredCommand = scanner.next();
            }

            finished = processCommand(enteredCommand);
            if (!finished) {
                printAvailableActions();
            }
        }
        System.out.println("Good bye.");
    }

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
            writeToFile();
        }

        return wantToQuit;
    }

    private void writeToFile() {
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

    private ArrayList<Task> readFromFile() {
        ArrayList<Task> taskStore = new ArrayList<>();
        try {
            File file = getResourceFile();
            FileInputStream fileIn = new FileInputStream(file);
            if (fileIn.available() > 0) {
                ObjectInputStream in = new ObjectInputStream(fileIn);
                taskStore = (ArrayList<Task>) in.readObject();
                in.close();
            }
            fileIn.close();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return taskStore;
    }

    private int[] getCount() {
        int[] counts = new int[2];
        int doneCount = 0;
        int toDoCount = 0;
        for (Task t : taskStore) {
            if (t.getIsDoneBoolean()) {
                doneCount++;
            } else {
                toDoCount++;
            }
        }
        counts[0] = toDoCount;
        counts[1] = doneCount;
        return counts;
    }

    private void printAvailableActions() {

        System.out.println("============================================================");
        System.out.println(">> (1) Show Tasks (2) AddTask (3) Edit Task 4) Save and Quit");
        System.out.println("============================================================");
    }

    private void printWelcome() {
        System.out.println("===============================================");
        System.out.println(">> Welcome to ToDoLy");
        System.out.println();
        System.out.println(">> You have " + getCount()[0] + " task TODO and " + getCount()[1] + " task DONE!");
        System.out.println();
        System.out.println(">> Pick an option:");
        System.out.println("===============================================");
        System.out.println(">> (1) Show Task List (by date or project)");
        System.out.println(">> (2) AddTask New Task");
        System.out.println(">> (3) Edit Task (update, mark as done, remove)");
        System.out.println(">> (4) Save and Quit");
        System.out.println("===============================================");
    }

    private File getResourceFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(App.RESOURCE_FILE)).getFile());
    }
}
