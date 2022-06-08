import java.util.List;
import java.util.Scanner;

public class MyTicketsMenu {
    public static void run() {
        final TicketManager ticketManager = new TicketManager(AllCompanies.getInstance());

        Scanner scanner = new Scanner(System.in);

        final User user = User.getInstance();
        if (user == null) {
            System.out.println("You need to login first");
            return;
        }
        List<Ticket> ticketsOfUser = ticketManager.getTicketsOfUser(user.id);

        while (true) {
            if (ticketsOfUser.isEmpty()) {
                System.out.println("You have no tickets");
            } else {
                System.out.println("Select the ticket you want to update");
            }
            for (int i = 1; i <= ticketsOfUser.size(); i++) {
                System.out.println("-----------------------");
                System.out.println(i + ") " + ticketsOfUser.get(i - 1).getInfoToDisplay());
                System.out.println("-----------------------");
            }
            System.out.println(ticketsOfUser.size() + 1 + ") Return to previous menu");
            int selection = scanner.nextInt();
            if (selection <= ticketsOfUser.size() && selection > 0) {
                // Showing the update ticket menu for selected ticket
                Ticket ticket = ticketsOfUser.get(selection - 1);
                UpdateTicketMenu.run(ticket);

                // Reloading the tickets as some info may be updated
                ticketsOfUser = ticketManager.getTicketsOfUser(user.id);
            } else if (selection == ticketsOfUser.size() + 1) {
                // Go to previous menu
                return;
            } else {
                System.out.println("Enter a valid selection");
            }
        }
    }
}
