import java.util.Scanner;

public class InputHelper {
    private static Scanner scanner = new Scanner(System.in);

    public static double getDouble(String komunikat) {
        System.out.print(komunikat);
        return scanner.nextDouble();
    }

    public static String getString(String komunikat) {
        System.out.print(komunikat);
        scanner.nextLine();
        return scanner.nextLine();
    }

    public static int getInt(String komunikat) {
        System.out.print(komunikat);
        return scanner.nextInt();
    }
}
