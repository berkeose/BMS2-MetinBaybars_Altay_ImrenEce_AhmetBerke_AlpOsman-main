import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;

class DateUtilitiesTest {
   @Test
   @DisplayName("If the two dates are the same , it returns true")
    void testIsSameDayPositive (){
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now();
            assertTrue(DateUtilities.isSameDay(date1,date2));

    }
     @Test
   @DisplayName("If the two dates are not the same , it returns false")
    void testIsSameDayNegative (){
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2019,9,12);
            assertFalse(DateUtilities.isSameDay(date1,date2));

    }
}