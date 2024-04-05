package ex6;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє меню команд.
 */
public class Menu {
    private static Menu instance;
    private List<Command> commands = new ArrayList<>();

    private Menu() {}

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executeCommands() {
        for (Command command : commands) {
            command.execute();
        }
    }

    public void undoCommands() {
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}