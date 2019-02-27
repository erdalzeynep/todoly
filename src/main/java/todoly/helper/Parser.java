package todoly.helper;

import java.util.Scanner;
public class Parser
{
    private CommandWords commands;

    public Parser()
    {
        commands = new CommandWords();
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
