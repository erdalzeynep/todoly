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
        Integer enteredCommand = null;
        Scanner tokenizer = new Scanner(System.in);
        if(tokenizer.hasNext()) {
            enteredCommand = tokenizer.nextInt();
        }
        return new Command(commands.getCommandWord(enteredCommand));
    }
}
