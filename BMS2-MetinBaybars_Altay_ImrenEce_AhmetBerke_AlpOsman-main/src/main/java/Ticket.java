import java.time.LocalDateTime;

// Ticket that is booked by a user
public class Ticket {
    // Bus trip that the ticket belongs to
    final BusTripWithCompany busTrip;
    // Seat number of ticket
    private int seatNumber;

    // User id of the owner of that ticket
    private final String userId;

    public Ticket(BusTripWithCompany busTrip, int seatNumber, String userId) {
        this.busTrip = busTrip;
        this.seatNumber = seatNumber;
        this.userId = userId;
    }

    // Allow editing ticket if at least one hour left to departure
    // Returns true if allowed, otherwise returns false
    public boolean canUpdateTicket() {
        final LocalDateTime currentDate = LocalDateTime.now();
        return busTrip.trip.departureTime.minusHours(1).isAfter(currentDate);
    }

    public String getInfoToDisplay() {
        return "Seat number: " + seatNumber + "\n\t" + busTrip.getInfoToDisplay();
    }

    // Updates the seat number using the provided BookManager
    // If the update of BookManager succeeds, info on this ticket also gets updated
    public boolean changeSeatNumber(BookManager bookManager, int newSeatNumber) {
        boolean isChanged = bookManager.changeSeatNumber(seatNumber, newSeatNumber, userId, busTrip.trip);
        if (isChanged) {
            seatNumber = newSeatNumber;
            return true;
        }
        return false;
    }

    public boolean cancelTicket(BookManager bookManager) {
        return bookManager.cancelSeatNumber(seatNumber, userId, busTrip.trip);
    }
}
