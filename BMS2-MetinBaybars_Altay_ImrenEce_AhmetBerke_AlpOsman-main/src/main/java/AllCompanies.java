import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AllCompanies {

    //Members of class initialized
    public final List<Company> companies;
    private static AllCompanies instance;

    public static AllCompanies getInstance() {
        if (instance == null) {
            instance = new AllCompanies();
        }
        return instance;
    }

    //Adds companies to companies List
    private AllCompanies() {
        companies = new ArrayList<>();
        populateCompanies();
    }

    //Returns all trips as a list to user
    public List<BusTripWithCompany> getAllBusTripsOfAllCompanies() {
        List<BusTripWithCompany> allTrips = new ArrayList<>();
        List<Company> allCompanies = AllCompanies.getInstance().companies;
        for (Company company: allCompanies) {
            List<BusTrip> tripsOfCompany = company.trips;
            for (BusTrip trip: tripsOfCompany) {
                allTrips.add(new BusTripWithCompany(trip, company));
            }
        }
        return allTrips;
    }

    //Create and add new companies to list
    private void populateCompanies() {
        companies.add(new PamukKale());
        companies.add(new KamilKoc());
        companies.add(new Metro());
        companies.add(new Atasoy());
        for (Company company: companies) {
            populateTrips(company);
        }
    }

    //Creates all possibilities for trip date and time
    //Adds these new trips to belonged companies
    private void populateTrips(Company company) {
        List<City> cities = AllCities.getInstance().getCities();
        City istanbul = cities.get(0);
        City ankara = cities.get(1);
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate twoDaysLater = today.plusDays(2);
        LocalDate threeDaysLater = today.plusDays(3);
        LocalTime time1 = LocalTime.of(19, 30);
        LocalTime time2 = LocalTime.of(21, 30);
        LocalTime time3 = LocalTime.of(23, 30);


        LocalDateTime departureDateTime = LocalDateTime.of(today, time1);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), istanbul, ankara, 100.0));
        departureDateTime = LocalDateTime.of(today, time2);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), istanbul, ankara, 120.0));
        departureDateTime = LocalDateTime.of(today, time3);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), istanbul, ankara, 130.0));
        departureDateTime = LocalDateTime.of(today, time1);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), ankara, istanbul, 90.0));
        departureDateTime = LocalDateTime.of(today, time2);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), ankara, istanbul, 100.0));
        departureDateTime = LocalDateTime.of(today, time3);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), ankara, istanbul, 110.0));

        departureDateTime = LocalDateTime.of(tomorrow, time1);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), istanbul, ankara, 90.0));
        departureDateTime = LocalDateTime.of(tomorrow, time2);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), istanbul, ankara, 110.0));
        departureDateTime = LocalDateTime.of(tomorrow, time3);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), istanbul, ankara, 120.0));
        departureDateTime = LocalDateTime.of(tomorrow, time1);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), ankara, istanbul, 100.0));
        departureDateTime = LocalDateTime.of(tomorrow, time2);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), ankara, istanbul, 110.0));
        departureDateTime = LocalDateTime.of(tomorrow, time3);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), ankara, istanbul, 120.0));

        departureDateTime = LocalDateTime.of(twoDaysLater, time1);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), istanbul, ankara, 90.0));
        departureDateTime = LocalDateTime.of(twoDaysLater, time2);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), istanbul, ankara, 110.0));
        departureDateTime = LocalDateTime.of(twoDaysLater, time3);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), istanbul, ankara, 120.0));
        departureDateTime = LocalDateTime.of(twoDaysLater, time1);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), ankara, istanbul, 100.0));
        departureDateTime = LocalDateTime.of(twoDaysLater, time2);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), ankara, istanbul, 110.0));
        departureDateTime = LocalDateTime.of(twoDaysLater, time3);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), ankara, istanbul, 120.0));

        departureDateTime = LocalDateTime.of(threeDaysLater, time1);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), istanbul, ankara, 90.0));
        departureDateTime = LocalDateTime.of(threeDaysLater, time2);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), istanbul, ankara, 110.0));
        departureDateTime = LocalDateTime.of(threeDaysLater, time3);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), istanbul, ankara, 120.0));
        departureDateTime = LocalDateTime.of(threeDaysLater, time1);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), ankara, istanbul, 100.0));
        departureDateTime = LocalDateTime.of(threeDaysLater, time2);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), ankara, istanbul, 110.0));
        departureDateTime = LocalDateTime.of(threeDaysLater, time3);
        company.addTrip(new BusTrip(departureDateTime, departureDateTime.plusHours(5), ankara, istanbul, 120.0));
    }
}
