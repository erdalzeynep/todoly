public class Main {
    public static void main(String[] args) {
        ToDoList td=new ToDoList();

        td.writeToFile();
        td.printWelcome();

        td.processCommand();


    }
}
