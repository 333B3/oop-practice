package ex6;

/**
 * Інтерфейс для реалізації команди.
 */
public interface Command {
    void execute();

    void undo();
}