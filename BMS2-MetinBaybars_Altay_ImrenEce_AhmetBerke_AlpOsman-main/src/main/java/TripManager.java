import java.time.LocalDate;
import java.util.List;

// This is a helper class to get trips filtered by provided options
public class TripManager {

    // Returns all the bus trips of all companies on specified day, departure and destination points
    List<BusTripWithCompany> getBusTripsByDay(LocalDate day, City departurePoint, City destinationPoint) {
        List<BusTripWithCompany> allTrips = AllCompanies.getInstance().getAllBusTripsOfAllCompanies();
        IPredicate<BusTripWithCompany> predicate = tripWithCompany -> tripWithCompany.trip.departurePoint.equals(departurePoint) && tripWithCompany.trip.destinationPoint.equals(destinationPoint) && DateUtilities.isSameDay(day, tripWithCompany.trip.departureTime.toLocalDate());
        List<BusTripWithCompany> trips = (List<BusTripWithCompany>) CollectionUtilities.filter(allTrips, predicate);
        trips.sort(new BusTripWithCompanyComparator());
        return trips;
    }

}
