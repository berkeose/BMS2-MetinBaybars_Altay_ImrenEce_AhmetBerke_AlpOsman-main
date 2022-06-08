import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CityTest {

    @Test
    @DisplayName("Two city with same name must be equal")
    void testEqualsPositive() {
        City city1 = new City("istanbul");
        City city2 = new City("istanbul");
        assertEquals(city1, city2);
    }

    @Test
    @DisplayName("Two city with different names must not be equal")
    void testEqualsNegative() {
        City city1 = new City("istanbul");
        City city2 = new City("ankara");
        assertNotEquals(city1, city2);
    }
}
