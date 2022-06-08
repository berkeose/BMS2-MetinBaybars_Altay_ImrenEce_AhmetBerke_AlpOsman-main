import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BusTripWithCompanyComparatorTest {
    @Test
    @DisplayName("Trips with same companies gets compared by departure time in ascending")
    void testCompareSameCompaniesPositive() {
        Company company = new KamilKoc();
        LocalDateTime departureTime1 = LocalDateTime.now().plusHours(1);
        // Being sure that departure times are not equal
        LocalDateTime departureTime2 = departureTime1.plusHours(1);
        City departureCity = new City("istanbul");
        City arrivalCity = new City("ankara");
        BusTripWithCompany trip1 = new BusTripWithCompany(
                new BusTrip(
                        departureTime1,
                        departureTime1.plusHours(5),
                        departureCity,
                        arrivalCity,
                        100
                ),
                company
        );
        BusTripWithCompany trip2 = new BusTripWithCompany(
                new BusTrip(
                        departureTime2,
                        departureTime2.plusHours(5),
                        departureCity,
                        arrivalCity,
                        100
                ),
                company
        );
        int comparisonResult = new BusTripWithCompanyComparator().compare(trip1, trip2);
        assertEquals(comparisonResult, departureTime1.compareTo(departureTime2));
    }

    @Test
    @DisplayName("Trips with same companies gets compared by departure time not in descending")
    void testCompareSameCompaniesNegative() {
        Company company = new KamilKoc();
        LocalDateTime departureTime1 = LocalDateTime.now().plusHours(1);
        // Being sure that departure times are not equal
        LocalDateTime departureTime2 = departureTime1.plusHours(1);
        City departureCity = new City("istanbul");
        City arrivalCity = new City("ankara");
        BusTripWithCompany trip1 = new BusTripWithCompany(
                new BusTrip(
                        departureTime1,
                        departureTime1.plusHours(5),
                        departureCity,
                        arrivalCity,
                        100
                ),
                company
        );
        BusTripWithCompany trip2 = new BusTripWithCompany(
                new BusTrip(
                        departureTime2,
                        departureTime2.plusHours(5),
                        departureCity,
                        arrivalCity,
                        100
                ),
                company
        );
        int comparisonResult = new BusTripWithCompanyComparator().compare(trip1, trip2);
        assertNotEquals(comparisonResult, departureTime2.compareTo(departureTime1));
    }
}
