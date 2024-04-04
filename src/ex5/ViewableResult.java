package ex5;

/**
 * Конкретний створювач (Concrete Creator) у шаблоні проектування Factory Method.
 * Реалізує інтерфейс Viewable, надаючи метод getView(), який повертає новий об'єкт ViewTable.
 */
public class ViewableResult implements Viewable {
    /**
     * Метод, який повертає новий об'єкт класу ViewTable.
     *
     * @return новий об'єкт класу ViewTable
     */
    @Override
    public View getView() {
        return new ViewTable();
    }
}
