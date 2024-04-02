package ex02_03;

import java.io.IOException;
/** Product
* (Шаблон проектування
* Factory Method)<br>
* Інтерфейс
* об'єктів<br>
* голошує методи
* Відображення об'єктів
* 
* 
*/
public interface View {
/** Відображення заголовку */
public void viewHeader();
/** Відображення основної частини */
public void viewBody();
/** Відображення закінчення */
public void viewFooter();
/** Відображення об'єктів */
public void viewShow();
/** Виконання ініціалізації */
public void viewInit();
/** Збереження даних */
public void viewSave() throws IOException;
/** Завантаження раніше створених даних */
public void viewRestore() throws Exception;
}