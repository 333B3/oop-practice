
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
