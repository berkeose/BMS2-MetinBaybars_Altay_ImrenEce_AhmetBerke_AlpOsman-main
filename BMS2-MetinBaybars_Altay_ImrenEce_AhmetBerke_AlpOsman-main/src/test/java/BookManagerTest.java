import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class BookManagerTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private BusTrip busTrip;
    private int currentUserSeat;
    private int anotherUserSeat;
    private int emptySeat;
    private BookManager manager;
    private MailService mailService;
    String anotherUserId;

    @BeforeEach
    void setUp() {
        // Set signed-in user
        User.registerUser("id", "name", "test@gmail.com", "username", "password");

        // Set another user id
        anotherUserId = "anotherId";


        // Create a bus trip that departures tomorrow
        City istanbul = new City("istanbul");
        City ankara = new City("ankara");
        LocalDateTime tomorrowNow = LocalDateTime.now().plusDays(1);
        busTrip = new BusTrip(tomorrowNow, tomorrowNow.plusHours(5), istanbul, ankara, 100.0);


        // Select 3 different seat numbers randomly, one to assign signed-in user, one to anotherUser, one as empty
        Random r = new Random();
        currentUserSeat = r.nextInt(busTrip.seatsCount) + 1;
        anotherUserSeat = currentUserSeat;
        while (anotherUserSeat == currentUserSeat) {
            anotherUserSeat = r.nextInt(busTrip.seatsCount) + 1;
        }
        emptySeat = currentUserSeat;
        while (emptySeat == currentUserSeat || emptySeat == anotherUserSeat) {
            emptySeat = r.nextInt(busTrip.seatsCount) + 1;
        }

        // Assign the seats to users
        busTrip.assignSeatToUser(currentUserSeat, User.getInstance().id);
        busTrip.assignSeatToUser(anotherUserSeat, anotherUserId);

        // Mock the mail service and create book manager with it
        mailService = mock(MailService.class);
        manager = new BookManager(mailService);

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
    @DisplayName("Send email for successful books")
    void testSendEmailForSuccessfulBooking() throws Exception {
        manager.bookSeat(emptySeat, User.getInstance().id, busTrip);
        String email = User.getInstance().email;
        verify(mailService, times(1)).sendMail(email);
    }

    @Test
    @DisplayName("Don't send email for failing books")
    void testSendEmailForFailedBooking() throws Exception {
        manager.bookSeat(anotherUserSeat, User.getInstance().id, busTrip);
        String email = User.getInstance().email;
        verify(mailService, times(0)).sendMail(email);

    }

    @Test
    @DisplayName("Return true for successful booking")
    void testBookingResultPositive() {
        boolean result = manager.bookSeat(emptySeat, User.getInstance().id, busTrip);
        assertTrue(result);
        assertEquals(busTrip.getUserOnSeat(emptySeat), User.getInstance().id);
    }

    @Test
    @DisplayName("Return false for failed booking")
    void testBookingResultNegative() {
        boolean result = manager.bookSeat(currentUserSeat, anotherUserId, busTrip);
        assertFalse(result);
        // Assert that currentUserSeat still belongs to current user
        assertEquals(busTrip.getUserOnSeat(currentUserSeat), User.getInstance().id);
    }

    @Test
    @DisplayName("Accept cancelling seats of current user")
    void testCancelSeatNumberPositive() {
        boolean result = manager.cancelSeatNumber(currentUserSeat, User.getInstance().id, busTrip);
        assertTrue(result);
        // Seat should be empty now as manager cancelled it
        assertTrue(busTrip.checkSeat(currentUserSeat));
        assertNull(busTrip.getUserOnSeat(currentUserSeat));
    }

    @Test
    @DisplayName("Reject cancelling seats of other users")
    void testCancelSeatNumberNegative() {
        boolean result = manager.cancelSeatNumber(anotherUserSeat, User.getInstance().id, busTrip);
        assertFalse(result);
        // Seat should still belong to other user
        assertEquals(busTrip.getUserOnSeat(anotherUserSeat), anotherUserId);
    }

    @Test
    @DisplayName("Accept changing seat number to available seat")
    void testChangeSeatNumberResultPositive() {
        boolean result = manager.changeSeatNumber(currentUserSeat, emptySeat, User.getInstance().id, busTrip);
        assertTrue(result);
        // Empty seat should belong to current user now
        assertEquals(User.getInstance().id, busTrip.getUserOnSeat(emptySeat));
        // Previous seat should be empty now
        assertNull(busTrip.getUserOnSeat(currentUserSeat));
        assertTrue(busTrip.checkSeat(currentUserSeat));
    }

    @Test
    @DisplayName("Reject changing seat number to a booked seat")
    void testChangeSeatNumberResultNegative() {
        boolean result = manager.changeSeatNumber(currentUserSeat, anotherUserSeat, User.getInstance().id, busTrip);
        assertFalse(result);
        // New seat should still belong to other user
        assertEquals(busTrip.getUserOnSeat(anotherUserSeat), anotherUserId);
        // Current seat should still belong to current user
        assertEquals(busTrip.getUserOnSeat(currentUserSeat), User.getInstance().id);
    }
}
