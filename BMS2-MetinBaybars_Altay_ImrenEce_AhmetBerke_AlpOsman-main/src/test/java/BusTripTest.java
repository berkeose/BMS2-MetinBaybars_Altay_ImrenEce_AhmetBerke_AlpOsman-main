import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BusTripTest {
    private BusTrip busTrip;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        LocalDate today = LocalDate.now();
        LocalTime time1 = LocalTime.of(19, 30);
        LocalDateTime departureDateTime = LocalDateTime.of(today, time1);
        busTrip = new BusTrip(departureDateTime, departureDateTime.plusHours(5), new City("istanbul"), new City("ankara"), 100.0);

        // Set the console outputs
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @AfterEach
    void cleanUp() {
        System.setOut(new PrintStream(originalOut));
        System.setErr(new PrintStream(originalErr));
    }

    @Test
    @DisplayName("All seat numbers should be displayed")
    void testDisplaySeatsAllNumbers() {
        busTrip.displaySeats();
        for (int i = 0; i < busTrip.seatsCount; i++) {
            assertTrue(out.toString().contains(Integer.toString(i + 1)));
        }
    }

    @Test
    @DisplayName("Seat code 0 shouldn't be available")
    void testCheckSeatZero() {
        assertFalse(busTrip.checkSeat(0));
    }

    @Test
    @DisplayName("Seat number greater than seat count isn't available")
    void testCheckSeatGreaterThanCount() {
        assertFalse(busTrip.checkSeat(busTrip.seatsCount + 1));
    }

    @Test
    @DisplayName("Seat number equals to seat count is available")
    void testCheckSeatEqualsCount() {
        assertTrue(busTrip.checkSeat(busTrip.seatsCount));
    }

    @RepeatedTest(5)
    @DisplayName("Selected seat shouldn't be available anymore")
    void testCheckSelectedSeat() {
        Random r = new Random();
        int seatNumber = r.nextInt(busTrip.seatsCount) + 1;
        busTrip.assignSeatToUser(seatNumber, "userId");
        assertFalse(busTrip.checkSeat(seatNumber));
    }

    @RepeatedTest(5)
    @DisplayName("Empty seats should be available")
    void testCheckEmptySeat() {
        Random r = new Random();
        int seatNumber = r.nextInt(busTrip.seatsCount) + 1;
        assertTrue(busTrip.checkSeat(seatNumber));
    }

    @RepeatedTest(5)
    @DisplayName("Removing user from seat makes the seat available")
    void testCheckRemoveUserOnSeat() {
        Random r = new Random();
        int seatNumber = r.nextInt(busTrip.seatsCount) + 1;
        busTrip.assignSeatToUser(seatNumber, "userId");
        busTrip.removeUserOnSeat(seatNumber);
        assertTrue(busTrip.checkSeat(seatNumber));
    }

    @RepeatedTest(5)
    @DisplayName("Assigned seats return user id they are assigned to")
    void testGetUserOnSeatPositive() {
        Random r  = new Random();
        int seatNumber = r.nextInt(busTrip.seatsCount) + 1;
        String userId = "userId" + r.nextInt(1000);
        busTrip.assignSeatToUser(seatNumber, userId);
        String userIdOnSeat = busTrip.getUserOnSeat(seatNumber);
        assertEquals(userIdOnSeat, userId);
    }

    @RepeatedTest(5)
    @DisplayName("Empty seats return null for user id")
    void testGetUserOnSeatNegative() {
        Random r  = new Random();
        int seatNumber = r.nextInt(busTrip.seatsCount) + 1;
        String userId = busTrip.getUserOnSeat(seatNumber);
        assertNull(userId);
    }

    @Test
    @DisplayName("Must return seats assigned to user")
    void testGetSeatsAssignedToUserPositive() {
        List<Integer> seatNumbers = Arrays.asList(5, 13, 26);
        String userId = "userId";
        for (int seatNumber: seatNumbers) {
            busTrip.assignSeatToUser(seatNumber, userId);
        }
        List<Integer> assignedSeats = busTrip.getSeatsAssignedToUser(userId);
        assertTrue(assignedSeats.containsAll(seatNumbers));
    }

    @Test
    @DisplayName("Must not return seats not assigned to user")
    void testGetSeatsAssignedToUserNegative() {
        List<Integer> seatNumbers = Arrays.asList(5, 13, 26);
        String userId = "userId";
        for (int seatNumber: seatNumbers) {
            busTrip.assignSeatToUser(seatNumber, userId);
        }
        List<Integer> assignedSeats = busTrip.getSeatsAssignedToUser(userId);
        assertEquals(seatNumbers.size(), assignedSeats.size());
    }
}
