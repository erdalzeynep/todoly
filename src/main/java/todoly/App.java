package todoly;

import todoly.actions.AddTask;
import todoly.actions.EditTask;
import todoly.actions.ListTasks;
import todoly.helper.Command;
import todoly.helper.CommandWord;
import todoly.helper.Parser;
import todoly.model.Task;

import java.io.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        App td = new App();
        td.startApp();
    }

    private static final String RESOURCE_FILE = "tasklist.ser";
    private Parser parser;
    private ArrayList<Task> taskStore;

    public App() {
        parser = new Parser();
        taskStore = readFromFile();
    }

    public void startApp() {

        printWelcome();
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if (finished == false) {
                printAvailableActions();
            }
        }
        System.out.println("Good bye.");
    }

    public boolean processCommand(Command command) {

        AddTask addTask = new AddTask(taskStore);
        ListTasks listTasks = new ListTasks(taskStore);
        EditTask editTask = new EditTask(taskStore);
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("Unknown command...");
                break;

            case ONE:
                listTasks.showTaskList();
                break;

            case TWO:
                addTask.addTask();
                break;

            case THREE:
                editTask.editTask();
                break;

            case FOUR:
                wantToQuit = true;
                writeToFile();
                break;
        }
        return wantToQuit;
    }

    public void writeToFile() {
        try {
            File file = getResourceFile(RESOURCE_FILE);
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskStore);
            out.close();
            fileOut.close();
        }
        catch (IOException i) {
            i.printStackTrace();
        }
    }

    public ArrayList<Task> readFromFile() {
        ArrayList<Task> taskStore = new ArrayList<>();
        try {
            File file = getResourceFile(RESOURCE_FILE);
            FileInputStream fileIn = new FileInputStream(file);
            if (fileIn.available() > 0) {
                ObjectInputStream in = new ObjectInputStream(fileIn);
                taskStore = (ArrayList<Task>) in.readObject();
                in.close();
            }
            fileIn.close();
        }
        catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        return taskStore;
    }

    public int getCountToDo() {
        int count = 0;
        for (int i = 0; i < taskStore.size(); i++) {
            if (!taskStore.get(i).getIsDone()) {
                count++;
            }
        }
        return count;
    }

    public int getCountDone() {
        int count = 0;
        for (int i = 0; i < taskStore.size(); i++) {
            if (taskStore.get(i).getIsDone()) {
                count++;
            }
        }
        return count;
    }

    public void printAvailableActions() {

        System.out.println("============================================================");
        System.out.println(">> (1) Show Tasks (2) AddTask (3) Edit Task 4) Save and Quit");
        System.out.println("============================================================");
    }

    public void printWelcome() {
        System.out.println("===============================================");
        System.out.println(">> Welcome to ToDoLy");
        System.out.println();
        System.out.println(">> You have " + getCountToDo() + " task TODO and " + getCountDone() + " task DONE!");
        System.out.println();
        System.out.println(">> Pick an option:");
        System.out.println("===============================================");
        System.out.println(">> (1) Show Task List (by date or project)");
        System.out.println(">> (2) AddTask New Task");
        System.out.println(">> (3) Edit Task (update, mark as done, remove)");
        System.out.println(">> (4) Save and Quit");
        System.out.println("===============================================");
    }

    public File getResourceFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file;
    }
}
