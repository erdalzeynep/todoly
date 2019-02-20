import java.util.HashMap;
public class CommandWords
{
    // A mapping between a command word and the CommandWord
    // associated with it.
    private HashMap<Integer, CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();
        validCommands.put(1, CommandWord.ONE);
        validCommands.put(2, CommandWord.TWO);
        validCommands.put(3, CommandWord.THREE);
        validCommands.put(4, CommandWord.FOUR);
    }


    public CommandWord getCommandWord(Integer commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }




}
