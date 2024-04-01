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