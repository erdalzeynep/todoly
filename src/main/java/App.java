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

    public void printWelcome() {

        System.out.println(">> Welcome to ToDoLy");
        System.out.println(">> You have " + getCountToDo() + " taskStore todo and " + getCountDone() + " taskStore are done!");
        printMessage();
    }

    private boolean quit(Command command) {

        return true;
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
                editTask();
                break;

            case FOUR:
                wantToQuit = quit(command);
                writeToFile();
                break;
        }
        return wantToQuit;
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

    public void editTask() {
        System.out.println(">> Pick an option:");
        System.out.println(">> (1) Update)");
        System.out.println(">> (2) Mark as Done");
        System.out.println(">> (3) Remove");

        Scanner scanner2 = new Scanner(System.in);
        int action = scanner2.nextInt();

        System.out.println("Please enter the ID of task");
        Scanner scanner1 = new Scanner(System.in);
        int id = scanner1.nextInt();
        getIndex(id);

        if (action == 1) {

        } else if (action == 2) {
            markAsDone(id);
        } else if (action == 3) {
            removeTask(id);

        } else {
            System.out.println("Please enter a valid action code");
        }
        printMessage();
    }

    public void markAsDone(int id) {
        taskStore.get(getIndex(id)).setStatus("done");
        successMessage();
    }

    public void removeTask(int id) {
        taskStore.remove(getIndex(id));
        successMessage();
    }

    public void writeToFile() {
        try {
            FileWriter fileWriter = new FileWriter("/Users/zeynepdal/IdeaProjects/App/src/main/java/TaskList");
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

    public void printMessage() {
        System.out.println("\n");
        System.out.println(">> Pick an option:");
        System.out.println(">> (1) Show Task ListTasks (by date or project)");
        System.out.println(">> (2) AddTask New Task");
        System.out.println(">> (3) Edit Task (update, mark as done, remove)");
        System.out.println(">> (4) Save and Quit");
    }

    public void successMessage() {
        System.out.println("Successfull..");
    }
}