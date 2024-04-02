package test;

import java.io.IOException;
import ex02_03.ViewResult;

public class test {
    public static void main(String[] args) {
        // Тестуємо ініціалізацію та виведення результатів
        System.out.println("Тестуємо ініціалізацію та виведення результатів:");
        ViewResult result = new ViewResult();
        result.viewShow();

        // Тестуємо збереження результатів у файл та їх відновлення
        System.out.println("\nТестуємо збереження результатів у файл та їх відновлення:");
        try {
            result.viewSave();
            result.viewRestore();
            result.viewShow();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка при збереженні та відновленні результатів: " + e.getMessage());
        }
    }
}
