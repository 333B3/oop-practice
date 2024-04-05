package ex6;

import java.util.Stack;

public class CommandHistory {
    private static CommandHistory instance;
    private final Stack<Command> commands = new Stack<>();

    private CommandHistory() {}

    public static CommandHistory getInstance() {
        if (instance == null) {
            instance = new CommandHistory();
        }
        return instance;
    }

    public void add(Command command) {
        commands.push(command);
    }

    public void undo() {
        if (!commands.isEmpty()) {
            Command lastCommand = commands.pop();
            lastCommand.undo();
        } else {
            System.out.println("Історія команд порожня.");
        }
    }
}
