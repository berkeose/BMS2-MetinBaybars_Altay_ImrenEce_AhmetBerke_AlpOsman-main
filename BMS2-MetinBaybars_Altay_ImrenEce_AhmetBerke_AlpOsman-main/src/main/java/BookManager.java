import java.util.List;
import java.util.Scanner;

// Manages booking seat of a bus trip for a user
public class BookManager {
    private final MailService mailService;

    public BookManager(MailService mailService) {
        this.mailService = mailService;
    }

    //Method that books the seat if available and sends the confirmation email to receiver who books the ticket
    public boolean bookSeat(int seatNo, String userId, BusTrip trip) {
        boolean isAvailable = trip.checkSeat(seatNo);
        Scanner scanner = new Scanner(System.in);
        if (isAvailable) {
            trip.assignSeatToUser(seatNo, userId);
            System.out.println("Your seat is successfully booked !!! You Can Check Your Mailbox For Booking Details");
            System.out.println("-----------------------------------------");
            System.out.println("Which payment method do you want to use ?");
            System.out.println("1) Cash");
            System.out.println("2) Credit Card");
            int selection = scanner.nextInt();
            if(selection == 1) {
                System.out.println("Your ticket is 150.00 TL");
            }
            if (selection == 2) {
                System.out.println("Please enter your card number: ");
                int cardNumber = scanner.nextInt();
                System.out.println("Please enter your CVC number: ");
                int CVC = scanner.nextInt();
                System.out.println("Please enter the name on the card: ");
                String name = scanner.next();
                Payment payment = new Payment(cardNumber, CVC, name);
                try {
                    payment.pay();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                mailService.sendMail(User.getInstance().email);
            } catch (Exception e) {
                System.out.println("Error email: " + e);
            }

            return true;
        }
        return false;
    }

    // Changing the seat number of user to a new seat
    public boolean changeSeatNumber(int currentSeatNo, int newSeatNo, String userId, BusTrip trip) {
        // First verify that currentSeatNoBelongs to given user
        String userIdOnCurrentSeat = trip.getUserOnSeat(currentSeatNo);
        if (!userIdOnCurrentSeat.equals(userId)) {
            System.out.println("This seat doesn't belong to you");
            return false;
        }
        // Then check if newSeatNo is available for booking
        if (!trip.checkSeat(newSeatNo)) {
            System.out.println(newSeatNo + " is not available. It is already booked");
            return false;
        }
        trip.removeUserOnSeat(currentSeatNo);
        trip.assignSeatToUser(newSeatNo, userId);
        System.out.println("You have successfully changed your seat");
        try {
            mailService.sendMail(User.getInstance().email);
        } catch (Exception e) {
            System.out.println("Error email: " + e);
        }
        return true;
    }

    public boolean cancelSeatNumber(int seatNo, String userId, BusTrip trip) {
        // First verify that user wants to cancel seatNo he actually owns
        if (!trip.getUserOnSeat(seatNo).equals(userId)) {
            System.out.println("This seat doesn't belong to you");
            return false;
        }
        trip.removeUserOnSeat(seatNo);
        return true;
    }
}
