import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

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
                System.out.println("I don't know what you mean...");
                break;

            case ONE:
                listTasks.showTaskList();
                break;

            case TWO:
                addTask.addTask();
                break;

            case THREE:
                listTasks.showTaskList();
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
            FileWriter fileWriter = new FileWriter("/Users/zeynepdal/IdeaProjects/ToDoList/src/main/java/TaskList");
            for (Task t : taskStore) {
                fileWriter.write(t.getId() + " " + t.getProject() + " " + t.getTitle() + " " + t.getDueDate() + " " + t.getStatus());
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
        }
    }

    public ArrayList<Task> readFromFile() {
        ArrayList<Task> taskStore = new ArrayList<>();

        File file = new File("/Users/zeynepdal/IdeaProjects/ToDoList/src/main/java/TaskList");
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (!line.trim().equals("")) {
                    taskStore.add(parseFileLine(line));
                }

            }
        } catch (IOException e) {
            System.out.println("Something went wrong while reading from file");
        }
        return taskStore;
    }

    public Task parseFileLine(String line) {
        Task task = new Task();
        String[] array = line.split(" ");
        task.setId(Integer.valueOf(array[0]));
        task.setProject(array[1]);
        task.setTitle(array[2]);
        task.setDueDate(array[3]);
        task.setStatus(array[4]);
        return task;
    }

    public int getCountToDo() {
        int count = 0;

        for (int i = 0; i < taskStore.size(); i++) {
            if (taskStore.get(i).getStatus().equals("todo")) {
                count++;
            }
        }
        return count;
    }

    public int getCountDone() {
        int count = 0;

        for (int i = 0; i < taskStore.size(); i++) {
            if (taskStore.get(i).getStatus().equals("done")) {
                count++;

            }
        }
        return count;
    }
    public void printMessage() {
        System.out.println("\n");
        System.out.println(">> Pick an option:");
        System.out.println(">> (1) Show Task ListTasks (by date or project)");
        System.out.println(">> (2) AddTask New Task");
        System.out.println(">> (3) Edit Task (update, mark as done, remove)");
        System.out.println(">> (4) Save and Quit");
    }

    public void printWelcome() {

        System.out.println(">> Welcome to ToDoLy");
        System.out.println(">> You have " + getCountToDo() + " taskStore todo and " + getCountDone() + " taskStore are done!");
        printMessage();
    }

}