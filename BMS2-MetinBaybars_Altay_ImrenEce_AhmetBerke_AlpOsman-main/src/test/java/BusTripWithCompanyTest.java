import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BusTripWithCompanyTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private BusTripWithCompany busTripWithCompany;

    @BeforeEach
    void setUp() {
        busTripWithCompany = AllCompanies.getInstance().getAllBusTripsOfAllCompanies().get(0);

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
    @DisplayName("Displays company name")
    void testDisplayTripInfoCompanyName() {
        busTripWithCompany.displayTripInfo();
        assertTrue(out.toString().contains(busTripWithCompany.company.name));
    }
}
