import java.util.Scanner;

public class LoginMenu {
    //This function checks user logged in or not.
    // If user logged in, it shows an output. Else, it forces the user to register.
    public static void showLoginMenu() {
        Scanner scanner = new Scanner(System.in);

        if(User.getIsLoggedIn()) {
            System.out.println("User already logged in");
        }else {
            System.out.println("There is no such a user please register: ");
            System.out.println("Enter your TC ID");
            String id = scanner.nextLine();
            System.out.println("Enter Name: ");
            String name = scanner.nextLine();
            System.out.println("Enter Email: ");
            String email = scanner.nextLine();
            System.out.println("Enter Username: ");
            String username = scanner.nextLine();
            System.out.println("Enter Password: ");
            String password = scanner.nextLine();
            User.registerUser(id, name, email, username, password);
        }
    }
}


