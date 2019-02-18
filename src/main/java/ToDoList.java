import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class ToDoList {


    private ArrayList<Task> tasks;
    Task task = new Task();

    public ToDoList() {

        readFromFile();
    }

    public int getCountToDo() {
        int count = 0;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getStatus().equals("todo")) {
                count++;

            }
        }
        return count;
    }

    public int getCountDone() {
        int count = 0;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getStatus().equals("done")) {
                count++;

            }
        }
        return count;
    }

    public void printWelcome() {

        System.out.println(">> Welcome to ToDoLy");
        System.out.println(">> You have " + getCountToDo() + " tasks todo and " + getCountDone() + " tasks are done!");
        printMessage();
    }


    public void processCommand() {
        Scanner scanner = new Scanner(System.in);
        boolean condition = true;
        while (condition) {
            int commandCode = scanner.nextInt();

            if (commandCode == 1) {
                listTasks();
            } else if (commandCode == 2) {
                addTask();
            } else if (commandCode == 3) {

                System.out.println(">> Pick an option:");
                System.out.println(">> (1) Update)");
                System.out.println(">> (2) Mark as Done");
                System.out.println(">> (3) Remove");


                Scanner scanner2 = new Scanner(System.in);
                int action = scanner2.nextInt();

                System.out.println("Please enter the ID of task");
                Scanner scanner1 = new Scanner(System.in);
                int id = scanner1.nextInt();
                int index = 0;
                for (Task t : tasks) {
                    if (t.getId() == id) {
                        index = tasks.indexOf(t);
                    }
                }


                if (action == 1) {

                } else if (action == 2) {
                    tasks.get(index).setStatus("done");
                    successMessage();
                } else if (action == 3) {
                    tasks.remove(index);
                    successMessage();

                } else {
                    System.out.println("Please enter a valid action code");
                }
                printMessage();

            } else if (commandCode == 4) {
                writeToFile();
                System.out.println("Successfully saved");
                condition = false;

            }
        }
    }

    public void addTask() {
        System.out.println("Project > ");
        Scanner scanner = new Scanner(System.in);
        task.setProject(scanner.nextLine());

        System.out.println("Title > ");
        scanner = new Scanner(System.in);
        task.setTitle(scanner.nextLine());

        System.out.println("Due Date > ");
        scanner = new Scanner(System.in);
        task.setDueDate(scanner.nextLine());


        task.setStatus("todo");
        Integer lastTaskId = tasks.get(tasks.size() - 1).getId();
        task.setId(lastTaskId + 1);
        tasks.add(task);

        System.out.println("Successfully added");
        printMessage();

    }


    public Task removeTask(Task t) {
        this.tasks.remove(t);
        return t;
    }

    public void listTasks() {
        ArrayList<Task> taskList = readFromFile();
        System.out.println("TASK LIST:");
        for (int i = 0; i < taskList.size(); i++) {
            Task currenTask = taskList.get(i);
            System.out.println(currenTask.getId() + " " + currenTask.getProject() + " " + currenTask.getTitle() + " " + currenTask.getDueDate() + " " + currenTask.getStatus());
        }
        printMessage();


    }

    public void writeToFile() {

        try {

            FileWriter fileWriter = new FileWriter("/Users/zeynepdal/IdeaProjects/ToDoList/src/main/java/TaskList");
            for (Task t : tasks) {
                fileWriter.write(t.getId() + " " + t.getProject() + " " + t.getTitle() + " " + t.getDueDate() + " " + t.getStatus());
                fileWriter.write("\n");

            }
            fileWriter.close();

        } catch (IOException e) {
        }
    }

    public ArrayList<Task> readFromFile() {
        this.tasks = new ArrayList<>();

        File file = new File("/Users/zeynepdal/IdeaProjects/ToDoList/src/main/java/TaskList");
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                tasks.add(parseFileLine(fileReader.nextLine()));
            }

        } catch (IOException e) {
            System.out.println("Something went wrong while reading from file");
        }
        return this.tasks;

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
        System.out.println(">> (1) Show Task List (by date or project)");
        System.out.println(">> (2) Add New Task");
        System.out.println(">> (3) Edit Task (update, mark as done, remove)");
        System.out.println(">> (4) Save and Quit");

    }

    public void successMessage() {
        System.out.println("Successfull..");
    }


}
