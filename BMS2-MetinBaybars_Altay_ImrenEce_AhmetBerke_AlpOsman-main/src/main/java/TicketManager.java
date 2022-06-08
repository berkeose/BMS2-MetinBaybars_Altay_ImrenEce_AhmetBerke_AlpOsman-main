import java.util.ArrayList;
import java.util.List;

// For managing the tickets of signed-in user
public class TicketManager {
    private final AllCompanies allCompanies;

    public TicketManager(AllCompanies allCompanies) {
        this.allCompanies = allCompanies;
    }

    List<Ticket> getTicketsOfUser(String userId) {
        List<Ticket> ticketsOfUser = new ArrayList<>();
        List<BusTripWithCompany> allBusTrips = allCompanies.getAllBusTripsOfAllCompanies();
        for (BusTripWithCompany busTrip: allBusTrips) {
            List<Integer> seatsOfUser = busTrip.getSeatsOfUser(userId);
            for (Integer seat: seatsOfUser) {
                ticketsOfUser.add(new Ticket(busTrip, seat, userId));
            }
        }
        return ticketsOfUser;
    }
}
