import java.util.Scanner;
public class Parser
{
    private CommandWords commands;
    private Scanner reader;


    public Parser()
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command getCommand()
    {
        String inputLine;
        Integer command1 = null;


        System.out.print("> ");

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            command1 = tokenizer.nextInt();

        }

        return new Command(commands.getCommandWord(command1));
    }



}
