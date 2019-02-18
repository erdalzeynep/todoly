import java.util.Scanner;
public class Command {
    Scanner reader = new Scanner(System.in);
    public String getCommand(){
        System.out.print(">");
        return reader.nextLine();

    }



}
