package ex02_03;
/** ConcreteCreator
* (шаблон проектування
* Factory Method)<br>
* Оголошує метод,
* "фабрикуючий" об'єкти
* @see Viewable
* @see ViewableResult#getView()
*/
public class ViewableResult implements Viewable {
@Override
public View getView() {
return (View) new ViewResult();
}
}