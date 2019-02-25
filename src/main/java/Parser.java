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
        Integer command1 = null;
        Scanner tokenizer = new Scanner(System.in);
        if(tokenizer.hasNext()) {
            command1 = tokenizer.nextInt();
        }
        return new Command(commands.getCommandWord(command1));
    }
}
