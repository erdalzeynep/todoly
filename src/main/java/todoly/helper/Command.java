package todoly.helper;

public class Command {
    private CommandWord commandWord;

    public Command(CommandWord commandWord) {
        this.commandWord = commandWord;

    }

    public CommandWord getCommandWord() {
        return commandWord;
    }
}