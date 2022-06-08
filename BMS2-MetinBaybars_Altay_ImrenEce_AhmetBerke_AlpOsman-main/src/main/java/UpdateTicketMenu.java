import java.util.Scanner;

public class UpdateTicketMenu {
    static void run(Ticket ticket) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (!ticket.canUpdateTicket()) {
                System.out.println("You can't update that ticket anymore");
                return;
            }
            System.out.println(ticket.getInfoToDisplay());
            System.out.println("Select the option you want for your ticket");
            System.out.println("1) Change Seat");
            System.out.println("2) Cancel Ticket");
            System.out.println("3) Return to previous menu");
            int option = scanner.nextInt();
            switch(option) {
                case 1:
                    boolean isChanged = handleOptionChangeSeat(ticket);
                    if (isChanged) {
                        // Return to previous menu if update happened
                        return;
                    }
                    break;
                case 2:
                    boolean isCancelled = handleOptionCancelTicket(ticket);
                    if (isCancelled) {
                        // Return to previous menu if update happened
                        return;
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Enter a valid option");
            }
        }
    }

    // Starts the process of changing seat number
    // Returns true if seat number changes successfully
    static private boolean handleOptionChangeSeat(Ticket ticket) {
        BookManager manager = new BookManager(new MailService());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select new seat number");
        while (true) {
            ticket.busTrip.trip.displaySeats();
            int seatsCount = ticket.busTrip.trip.seatsCount;
            System.out.println(seatsCount + 1 + ") Previous Menu");
            int selection = scanner.nextInt();
            if (selection <= seatsCount && selection > 0) {
                boolean isChanged = ticket.changeSeatNumber(manager, selection);
                if (isChanged) {
                    return true;
                }
                System.out.println("Changing seat failed. Select another seat or try again");
            } else if (selection == seatsCount + 1) {
                return false;
            } else {
                System.out.println("Enter a valid option");
            }
        }
    }

    // Starts the process of cancelling ticket
    // Returns true if ticket is cancelled
    static private boolean handleOptionCancelTicket(Ticket ticket) {
        boolean isConfirmed = ConfirmationDialog.show("You are about to cancel your ticket. You will be refunded and no longer have this ticket");
        if (isConfirmed) {
            BookManager manager = new BookManager(new MailService());
            boolean result = ticket.cancelTicket(manager);
            if (result) {
                System.out.println("Your ticket is cancelled");
                return true;
            }
        }
        return false;
    }
}
