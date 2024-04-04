package ex5;

/**
 * Інтерфейс для реалізації команди.
 */
public interface Command {
    void execute();

    void undo();
}