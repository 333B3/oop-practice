## Кіндінов Іван 5 Варіант
## Завдання 1
Написати просту консольну програму
```java
package ex01;
import java.util.Random;

public class something {
    private static final int TICKETS_COUNT = 100; 

    public static void Calculate() {
        Random random = new Random();
        int happyTicketsCount = 0;

        for (int i = 0; i < TICKETS_COUNT; i++) {
            int ticketNumber = 100000 + random.nextInt(900000); 
            if (isHappyTicket(ticketNumber)) {
                System.out.println("Щасливий квиток: " + ticketNumber);
                happyTicketsCount++;
            }
        }

        System.out.println("Кількість щасливих квитків: " + happyTicketsCount);
    }
    private static boolean isHappyTicket(int number) {
        int firstPart = number / 1000;
        int secondPart = number % 1000;

        return sumOfDigits(firstPart) == sumOfDigits(secondPart);
    }
    private static int sumOfDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
    public static void main(String[] args) {
        something.Calculate();
    }
}
```
### Результат роботи:
![](screenshot/ex01.png)
## Завдання 2 
Клас для серіалізації/десеріалізації та демонстрації в діалоговому режимі збереження та відновлення стану об'єкта.

```java
package ex02;
import java.io.*;
import java.util.Scanner;

        /**
        * Клас для демонстрації в діалоговому режимі збереження та відновлення стану об'єкта серіалізації/десеріалізації
        */
public class Serialization {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /**
        * @parambaseHeight - записує значення висоти
        */
        System.out.print("Введіть висоту основи трикутника: ");
        int baseHeight = scanner.nextInt();
       
       /**
        *@param rectangleLength - записує значення довжини
        */
        System.out.print("Введіть довжину сторони прямокутника: ");
        int rectangleLength = scanner.nextInt();
        /**
         * 
         * @param countOnes - викликає метод countOnesInBinary класу BinaryCountCalculator, 
         * передаючи йому параметри baseHeight і rectangleLength, і присвоює результат цього виклику змінній countOnes.
         */
        int countOnes = BinaryCountCalculator.countOnesInBinary(baseHeight, rectangleLength);
        scanner.close();
        /**
         * @param result - створює новий об'єкт класу CalculationResult, передаючи йому параметри
         * baseHeight, rectangleLength і countOnes, і присвоює цей об'єкт змінній result.
         */
        CalculationResult result = new CalculationResult(baseHeight, rectangleLength, countOnes);

        /**
        * Серіалізує об'єкт `result` із результатами обчислень у файл "calculation_result.ser".
        * Виводить повідомлення про успішну серіалізацію.
        * за допомогою об'єкта ObjectOutputStream. Це відбувається всередині блоку try-with-resources,
        * що автоматично закриває ObjectOutputStream після завершення виконання блоку.
        * @param result об'єкт, який необхідно серіалізувати
        */
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("calculation_result.ser"))) {
            out.writeObject(result);
            System.out.println("Об'єкт було серіалізовано.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 54-59 виконується зчитування серіалізованого об'єкта з файлу "calculation_result.ser" за допомогою ObjectInputStream.
         * Це відбувається всередині блоку try-with-resources, що автоматично закриває ObjectInputStream після завершення виконання блоку.
         * після завершення виконання блоку. Якщо в процесі зчитування виникає помилка вони відловлюються в блоку catch
         */
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("calculation_result.ser"))) {
            CalculationResult deserializedResult = (CalculationResult) in.readObject();
            System.out.println("Десеріалізований об'єкт: " + deserializedResult);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
```
клас для знаходження рішення задачі.
```java
package ex02;
    /**
     * @BinaryCountCalculator - відповідає за обчислення кількості одиниць у двійковому представленні суми значень.
    */
public class BinaryCountCalculator {
    /**
    * Метод обчислює кількість одиниць у двійковому представленні суми периметрів
    * рівнобедреного трикутника та прямокутника по заданій висоті та довжині сторони основи.
    * @param baseHeight        висота основи трикутника
    * @param rectangleLength   довжина сторони прямокутника
    * @return кількість одиниць у двійковому представленні суми периметрів
    */
    public static int countOnesInBinary(int baseHeight, int rectangleLength) {
        int perimeterTriangle = 2 * (baseHeight + (int) Math.sqrt(Math.pow(baseHeight, 2) + Math.pow(rectangleLength / 2, 2)));
        int perimeterRectangle = 2 * (rectangleLength + baseHeight);
        int sum = perimeterTriangle + perimeterRectangle;
        String binary = Integer.toBinaryString(sum);
        System.out.println("Сума периметрів: " + sum);
        System.out.println("Двійкове значення суми: " + binary);
        int countOnes = 0;
        for (char c : binary.toCharArray()) {
            if (c == '1') {
                countOnes++;
            }
        }
        return countOnes;
    }
}
```

Клас для збереження та відновлення стану об'єкта.

```java
package ex02;

import java.io.Serializable;

    /**
 * Клас, який представляє результати обчислень і є серіалізованим.
 * Зберігає параметри і результати обчислень, а саме висоту основи трикутника,
 * довжину сторони прямокутника та кількість одиниць у двійковому представленні суми периметрів.
 */
public class CalculationResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private int baseHeight;
    private int rectangleLength;
    private int countOnes;

            /**
        * Конструктор класу CalculationResult для ініціалізації параметрів та результатів обчислень.
        *
        * @param baseHeight        висота основи трикутника
        * @param rectangleLength   довжина сторони прямокутника
        * @param countOnes         кількість одиниць у двійковому представленні суми периметрів
        */
    public CalculationResult(int baseHeight, int rectangleLength, int countOnes) {
        this.baseHeight = baseHeight;
        this.rectangleLength = rectangleLength;
        this.countOnes = countOnes;
    }

            /**
        * Повертає висоту основи трикутника.
        *
        * @return висота основи трикутника
        */
    public int getBaseHeight() {
        return baseHeight;
    }
            /**
        * Встановлює висоту основи трикутника.
        *
        * @param baseHeight нове значення висоти основи трикутника
        */
    public void setBaseHeight(int baseHeight) {
        this.baseHeight = baseHeight;
    }
            /**
        * Повертає довжину сторони прямокутника.
        *
        * @return довжина сторони прямокутника
        */
    public int getRectangleLength() {
        return rectangleLength;
    }
            /**
         * Встановлює довжину сторони прямокутника.
         *
         * @param rectangleLength нове значення довжини сторони прямокутника
         */
    public void setRectangleLength(int rectangleLength) {
        this.rectangleLength = rectangleLength;
    }
            /**
        * Повертає кількість одиниць у двійковому представленні суми периметрів.
        *
        * @return кількість одиниць у двійковому представленні суми периметрів
        */
    public int getCountOnes() {
        return countOnes;
    }
        /**
        * Встановлює кількість одиниць у двійковому представленні суми периметрів.
        *
        * @param countOnes нове значення кількості одиниць у двійковому представленні суми периметрів
        */
    public void setCountOnes(int countOnes) {
        this.countOnes = countOnes;
    }

    // Перевизначений метод toString() для зручного виводу інформації про об'єкт
    @Override
    public String toString() {
        return "Початкова висота: " + baseHeight + ", Початкова довжина: " + rectangleLength + ", Кількість одиниць: " + countOnes;
    }
}
```
Клас для тестування

```java 
package test;
import ex02.*;
import java.util.Scanner;

// Клас для тестування
public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Вхідні параметри
        int baseHeight = 5;
        int rectangleLength = 4;

        // Знаходимо кількість одиниць у двійковому поданні суми
        int countOnes = BinaryCountCalculator.countOnesInBinary(baseHeight, rectangleLength);

        // Виведення результатів
        System.out.println("Висота основи трикутника: " + baseHeight);
        System.out.println("Довжина сторони прямокутника: " + rectangleLength);
        System.out.println("Кількість одиниць у двійковому поданні суми: " + countOnes);

        scanner.close();
    }
}
```
### Робота програми:
![](screenshot/1-1.png)

## Завдання 3
ViewResult
```java
package ex02_03;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
        /**
        * Клас `ViewResult` представляє результати обчислень та їх відображення.
        * Цей клас реалізує інтерфейс `Serializable`, щоб можливо було серіалізувати об'єкти цього класу.
        */
public class ViewResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "calculation_result.ser";
    private int baseHeight;
    private int rectangleLength;
    private int countOnes;
    /**
     * Конструктор класу `ViewResult` без параметрів.
     * Користувач вводить висоту основи трикутника та довжину сторони прямокутника з клавіатури.
     * Після введення обчислюється кількість одиниць у двійковому представленні суми периметрів.
     */
    public ViewResult() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть висоту основи трикутника: ");
        this.baseHeight = scanner.nextInt();

        System.out.print("Введіть довжину сторони прямокутника: ");
        this.rectangleLength = scanner.nextInt();

        scanner.close();

        this.countOnes = countOnesInBinary(this.baseHeight, this.rectangleLength);
    }
     /**
    * Конструктор класу `ViewResult` з параметрами.
    *
    * @param baseHeight      висота основи трикутника
    * @param rectangleLength довжина сторони прямокутника
    * @param countOnes       кількість одиниць у двійковому представленні суми периметрів
    */
    public ViewResult(int baseHeight, int rectangleLength, int countOnes) {
        this.baseHeight = baseHeight;
        this.rectangleLength = rectangleLength;
        this.countOnes = countOnes;
    }
     /**
     * Метод для обчислення кількості одиниць у двійковому представленні суми периметрів.
     *
     * @param baseHeight      висота основи трикутника
     * @param rectangleLength довжина сторони прямокутника
     * @return кількість одиниць у двійковому представленні суми периметрів
     */
    public void calculateAndSaveResult(int baseHeight, int rectangleLength) {
        this.baseHeight = baseHeight;
        this.rectangleLength = rectangleLength;
        this.countOnes = countOnesInBinary(baseHeight, rectangleLength);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(this);
            System.out.println("Результати обчислень було збережено у файл " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewShow() {
        viewHeader();
        System.out.printf("Висота основи трикутника: %d%n", baseHeight);
        System.out.printf("Довжина сторони прямокутника: %d%n", rectangleLength);
        System.out.printf("Кількість одиниць у двійковому представленні суми периметрів: %d%n", countOnes);
        viewFooter();
    }

    public void viewSave() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(this);
            System.out.println("Результати обчислень було збережено у файл " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewRestore() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            ViewResult result = (ViewResult) in.readObject();
            this.baseHeight = result.baseHeight;
            this.rectangleLength = result.rectangleLength;
            this.countOnes = result.countOnes;
            System.out.println("Результати обчислень було відновлено з файлу " + FILE_NAME);
        }
    }

    public void viewHeader() {
        System.out.println("=== Результати обчислень ===");
    }

    public void viewFooter() {
        System.out.println("=== Кінець результатів ===");
    }

    public int getBaseHeight() {
        return baseHeight;
    }

    public void setBaseHeight(int baseHeight) {
        this.baseHeight = baseHeight;
    }

    public int getRectangleLength() {
        return rectangleLength;
    }

    public void setRectangleLength(int rectangleLength) {
        this.rectangleLength = rectangleLength;
    }

    public int getCountOnes() {
        return countOnes;
    }

    public void setCountOnes(int countOnes) {
        this.countOnes = countOnes;
    }

    public static int countOnesInBinary(int baseHeight, int rectangleLength) {
        int perimeterTriangle = 2 * (baseHeight + (int) Math.sqrt(Math.pow(baseHeight, 2) + Math.pow(rectangleLength / 2, 2)));
        int perimeterRectangle = 2 * (rectangleLength + baseHeight);
        int sum = perimeterTriangle + perimeterRectangle;
        String binary = Integer.toBinaryString(sum);
        int countOnes = 0;
        for (char c : binary.toCharArray()) {
            if (c == '1') {
                countOnes++;
            }
        }
        return countOnes;
    }
}
```
ViewableResult
```java
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
```
Viewable
```java
package ex02_03;
/**
 * Інтерфейс `Viewable` визначає метод `getView`, який повинен бути реалізований у класах,
 * що реалізують цей інтерфейс.
 */
public interface Viewable {
public View getView();
}
```
View
```java
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
```
Main
```java
package ex02_03;
import java.io.*;
import java.util.Scanner;

        /**
        * Клас для демонстрації в діалоговому режимі збереження та відновлення стану об'єкта серіалізації/десеріалізації
        */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /**
        * @parambaseHeight - записує значення висоти
        */
        System.out.print("Введіть висоту основи трикутника: ");
        int baseHeight = scanner.nextInt();
       
       /**
        *@param rectangleLength - записує значення довжини
        */
        System.out.print("Введіть довжину сторони прямокутника: ");
        int rectangleLength = scanner.nextInt();
        /**
         * 
         * @param countOnes - викликає метод countOnesInBinary класу BinaryCountCalculator, 
         * передаючи йому параметри baseHeight і rectangleLength, і присвоює результат цього виклику змінній countOnes.
         */
        int countOnes = BinaryCountCalculator.countOnesInBinary(baseHeight, rectangleLength);
        scanner.close();
        /**
         * @param result - створює новий об'єкт класу CalculationResult, передаючи йому параметри
         * baseHeight, rectangleLength і countOnes, і присвоює цей об'єкт змінній result.
         */
        CalculationResult result = new CalculationResult(baseHeight, rectangleLength, countOnes);
        
        ViewResult viewResult = new ViewResult();
        viewResult.calculateAndSaveResult(baseHeight, rectangleLength);

        /**
        * Серіалізує об'єкт `result` із результатами обчислень у файл "calculation_result.ser".
        * Виводить повідомлення про успішну серіалізацію.
        * за допомогою об'єкта ObjectOutputStream. Це відбувається всередині блоку try-with-resources,
        * що автоматично закриває ObjectOutputStream після завершення виконання блоку.
        * @param result об'єкт, який необхідно серіалізувати
        */
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("calculation_result.ser"))) {
            out.writeObject(result);
            System.out.println("Об'єкт було серіалізовано.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 54-59 виконується зчитування серіалізованого об'єкта з файлу "calculation_result.ser" за допомогою ObjectInputStream.
         * Це відбувається всередині блоку try-with-resources, що автоматично закриває ObjectInputStream після завершення виконання блоку.
         * після завершення виконання блоку. Якщо в процесі зчитування виникає помилка вони відловлюються в блоку catch
         */
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("calculation_result.ser"))) {
            CalculationResult deserializedResult = (CalculationResult) in.readObject();
            System.out.println("Десеріалізований об'єкт: " + deserializedResult);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
```
CalculationResult
```java
package ex02_03;

import java.io.Serializable;

    /**
 * Клас, який представляє результати обчислень і є серіалізованим.
 * Зберігає параметри і результати обчислень, а саме висоту основи трикутника,
 * довжину сторони прямокутника та кількість одиниць у двійковому представленні суми периметрів.
 */
public class CalculationResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private int baseHeight;
    private int rectangleLength;
    private int countOnes;

            /**
        * Конструктор класу CalculationResult для ініціалізації параметрів та результатів обчислень.
        *
        * @param baseHeight        висота основи трикутника
        * @param rectangleLength   довжина сторони прямокутника
        * @param countOnes         кількість одиниць у двійковому представленні суми периметрів
        */
    public CalculationResult(int baseHeight, int rectangleLength, int countOnes) {
        this.baseHeight = baseHeight;
        this.rectangleLength = rectangleLength;
        this.countOnes = countOnes;
    }

            /**
        * Повертає висоту основи трикутника.
        *
        * @return висота основи трикутника
        */
    public int getBaseHeight() {
        return baseHeight;
    }
            /**
        * Встановлює висоту основи трикутника.
        *
        * @param baseHeight нове значення висоти основи трикутника
        */
    public void setBaseHeight(int baseHeight) {
        this.baseHeight = baseHeight;
    }
            /**
        * Повертає довжину сторони прямокутника.
        *
        * @return довжина сторони прямокутника
        */
    public int getRectangleLength() {
        return rectangleLength;
    }
            /**
         * Встановлює довжину сторони прямокутника.
         *
         * @param rectangleLength нове значення довжини сторони прямокутника
         */
    public void setRectangleLength(int rectangleLength) {
        this.rectangleLength = rectangleLength;
    }
            /**
        * Повертає кількість одиниць у двійковому представленні суми периметрів.
        *
        * @return кількість одиниць у двійковому представленні суми периметрів
        */
    public int getCountOnes() {
        return countOnes;
    }
        /**
        * Встановлює кількість одиниць у двійковому представленні суми периметрів.
        *
        * @param countOnes нове значення кількості одиниць у двійковому представленні суми периметрів
        */
    public void setCountOnes(int countOnes) {
        this.countOnes = countOnes;
    }

    // Перевизначений метод toString() для зручного виводу інформації про об'єкт
    @Override
    public String toString() {
        return "Початкова висота: " + baseHeight + ", Початкова довжина: " + rectangleLength + ", Кількість одиниць: " + countOnes;
    }
}
```
### Приклад роботи програми:
![](screenshot/ex03.png)
### Успішно виконане тестування:
![](screenshot/test03.png)

## Завдання 4

Main

```java
package ex4;

/**
 * Клас `Main` для демонстрації роботи програми.
 */
public class Main {
    /**
     * Головний метод програми.
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        ViewableResult viewableResult = new ViewableResult();
        View view = viewableResult.getView();
        view.viewShow();
    }
}
```

View

```java
package ex4;
/**
 * Інтерфейс, який оголошує методи для відображення заголовку, тіла, підзаголовку та всіх елементів.
 */
public interface View {
    /**
     * Метод для відображення заголовку.
     */
    public void viewHeader();
    /**
     * Метод для відображення тіла.
     */
    public void viewBody();
    /**
     * Метод для відображення підзаголовку.
     */
    public void viewFooter();
    /**
     * Метод для відображення всіх елементів.
     */
    public void viewShow();
}
```

Viewable

```java
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
```

ViewableResult

```java
package ex4;

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
```

ViewTable

```java
package ex4;

import java.util.Scanner;
/**
 * Клас, який представляє результати обчислень та забезпечує їх відображення.
 * Реалізує інтерфейс View для створення відображення результатів у вигляді таблиці.
 */
public class ViewTable implements View {
    private int baseHeight;
    private int rectangleLength;
    private int countOnes;
    
    /**
     * Конструктор класу, який отримує від користувача вхідні дані та обчислює кількість одиниць.
     */
    public ViewTable() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть висоту основи трикутника: ");
        this.baseHeight = scanner.nextInt();

        System.out.print("Введіть довжину сторони прямокутника: ");
        this.rectangleLength = scanner.nextInt();

        scanner.close();

        this.countOnes = countOnesInBinary(this.baseHeight, this.rectangleLength);
    }
;
    @Override
    public void viewHeader() {
        System.out.println();
        System.out.println(" ┌──────────────────────────┬──────────────────────────────┬───────────────────────┐");
        System.out.println(" │ Висота основи трикутника │ Довжина сторони прямокутника │ Кількість одиниць     │");
        System.out.println(" ├──────────────────────────┼──────────────────────────────┼───────────────────────┤");
    }

    @Override
    public void viewBody() {
        System.out.printf(" │ %24d │ %28d │ %21d │%n", baseHeight, rectangleLength, countOnes);
    }

    @Override
    public void viewFooter() {
        System.out.println(" └──────────────────────────┴──────────────────────────────┴───────────────────────┘");
    }

    @Override
    public void viewShow() {
        viewHeader();
        viewBody();
        viewFooter();
    }
    /**
     * Метод для обчислення кількості одиниць у двійковому представленні суми периметрів.
     *
     * @param baseHeight      висота основи трикутника
     * @param rectangleLength довжина сторони прямокутника
     * @return кількість одиниць у двійковому представленні суми периметрів
     */
    public static int countOnesInBinary(int baseHeight, int rectangleLength) {
        int perimeterTriangle = 2 * (baseHeight + (int) Math.sqrt(Math.pow(baseHeight, 2) + Math.pow(rectangleLength / 2, 2)));
        int perimeterRectangle = 2 * (rectangleLength + baseHeight);
        int sum = perimeterTriangle + perimeterRectangle;
        String binary = Integer.toBinaryString(sum);
        int countOnes = 0;
        for (char c : binary.toCharArray()) {
            if (c == '1') {
                countOnes++;
            }
        }
        System.out.print("Двійковий вигляд: " +  binary);
        return countOnes;
        
    }
}
```
### Приклад роботи програми:
![](screenshot/ex4.png)
### Успішно виконане тестування:
![](screenshot/test_ex4.png)