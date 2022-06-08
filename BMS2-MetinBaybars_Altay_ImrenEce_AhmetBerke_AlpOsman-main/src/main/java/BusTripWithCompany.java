import java.util.List;

public class BusTripWithCompany {
        //Attributes of the class BusTripWithCompany
    final BusTrip trip;
    final Company company;
        //Constructor
    public BusTripWithCompany(BusTrip trip, Company company) {
        this.trip = trip;
        this.company = company;
    }
        //Method to display the trip Information with company names
    public void displayTripInfo() {
        System.out.println(company.name);
        System.out.println(DateUtilities.formatDatesAndTimesBetween(trip.departureTime, trip.arriveTime));
        trip.displaySeats();
    }

    // Returns the seat numbers of bus trip that are assigned to user
    List<Integer> getSeatsOfUser(String userId) {
        return trip.getSeatsAssignedToUser(userId);
    }

    public String getInfoToDisplay() {
        return company.name + "\t" + trip.departurePoint + "\t" + trip.destinationPoint + "\t" + DateUtilities.formatDateAsTime(trip.departureTime) + "\t" + DateUtilities.formatDateAsTime(trip.arriveTime);
    }
}
