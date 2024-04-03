package ex4;
/**
 * Інтерфейс, який визначає метод getView(), який повинен бути реалізований у класах,
 * що реалізують цей інтерфейс. Метод getView() повертає об'єкт класу View.
 */
public interface Viewable {
    /**
     * Метод, який повертає об'єкт класу View.
     *
     * @return об'єкт класу View
     */
    public View getView();
}