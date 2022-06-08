import java.util.Scanner;

public class ConfirmationDialog {
    public static boolean show(String messageToDisplay) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(messageToDisplay);
        System.out.println("Are you sure? [y/N]");
        String result = scanner.nextLine().trim();
        return result.equalsIgnoreCase("y");
    }
}
