import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        //main function properties initialized
        Scanner scanner = new Scanner(System.in);
        //User.registerUser("3357535753", "Test User", "test@gmail.com","Test username ,Test password");

        //Interaction with user starts here
        System.out.println("----Welcome to the Bus Management System----");

        boolean selectedExit = false;
        while (!selectedExit) {
            printMenu();
            int selection = scanner.nextInt();
            switch(selection) {
                case 1:
                    if (!User.getIsLoggedIn()) {
                        LoginMenu.showLoginMenu();
                    }
                    BookServiceMenu.run();
                    break;
                case 2:
                    LoginMenu.showLoginMenu();
                    break;
                case 3:
                    if (!User.getIsLoggedIn()) {
                        LoginMenu.showLoginMenu();
                    }
                    MyTicketsMenu.run();
                    break;
                case 4:
                    selectedExit = true;
                    break;
                default:
                    System.out.println("Enter a valid selection");
            }
        }
    }
    public static void printMenu() {
        //Prints out the main menu
        System.out.println("Select your choice");
        System.out.println("1) Book a ticket");
        System.out.println("2) User Login");
        System.out.println("3) Show my tickets");
        System.out.println("4) Exit");
    }


}
