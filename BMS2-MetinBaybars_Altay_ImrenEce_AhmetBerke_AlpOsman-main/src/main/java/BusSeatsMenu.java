import java.util.Scanner;

public class BusSeatsMenu {
    //Shows the selected trips information to user
    static MenuResultAction run(BusTripWithCompany busTripWithCompany) {
        //Member of run method implemented
        Scanner scanner = new Scanner(System.in);
        BookManager manager = new BookManager(new MailService());
        while (true) {
            busTripWithCompany.displayTripInfo();
            System.out.println("Select your seat number or one of the options below: ");
            int seatsCount = busTripWithCompany.trip.seatsCount;
            //To return main or previous menu
            System.out.println(seatsCount + 1 + ") Previous Menu");
            System.out.println(seatsCount + 2 + ") Main Menu");
            int selection = scanner.nextInt();
            //if-check for bus seats
            if (selection <= seatsCount && selection > 0) {
                boolean result = manager.bookSeat(selection, User.getInstance().id, busTripWithCompany.trip);
               if (result) {
                   // TODO we may want to show ticket details here
                   return MenuResultAction.returnToMainMenu;
               }
            } else if (selection == seatsCount + 1) {
                return MenuResultAction.returnToPreviousMenu;
            } else if (selection == seatsCount + 2) {
                return MenuResultAction.returnToMainMenu;
            } else {
                System.out.println("Make a valid selection");
            }
        }
    }
}
