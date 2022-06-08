import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookServiceMenuTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private List<City> cities;

    @BeforeEach
    void setUp() {
        cities = AllCities.getInstance().getCities();

        // Set the console outputs
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));

        // Set the user input option
        String inputData = "1";
        System.setIn(new java.io.ByteArrayInputStream(inputData.getBytes()));
    }

    @AfterEach
    void cleanUp() {
        System.setOut(new PrintStream(originalOut));
        System.setErr(new PrintStream(originalErr));
    }

    @Test
    @DisplayName("All cities should appear on console")
    void testSelectDeparturePoint() {
        BookServiceMenu.selectDeparturePoint();
        for (City city: cities) {
            assertTrue(out.toString().contains(city.name));
        }
    }

    @Test
    @DisplayName("No city should appear more than once")
    void testSelectCityAmong() {
        BookServiceMenu.selectCityAmong(cities, "Test");
        for (City city: cities) {
            assertFalse(StringUtils.countMatches(out.toString(), city.name) > 1);
        }
    }

    @Test
    @DisplayName("Departure point shouldn't appear on arrive points")
    void testDepartureOnSelectArrivePoint() {
        City departureCity = cities.get(0);
        BookServiceMenu.selectArrivePoint(departureCity);
        assertFalse(out.toString().contains(departureCity.name));
    }

    @Test
    @DisplayName("Other cities should appear on arrive point")
    void testSelectArrivePoint() {
        City departureCity = cities.get(0);
        BookServiceMenu.selectArrivePoint(departureCity);
        for (City city: cities) {
            if (city != departureCity) {
                assertTrue(out.toString().contains(city.name));
            }
        }
    }
}
