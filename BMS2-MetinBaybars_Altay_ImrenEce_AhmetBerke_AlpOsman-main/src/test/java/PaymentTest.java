import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    @DisplayName("If credit card credentials are suitable, it should return true")
    void testCheckCardPositive() {
        Payment payment = new Payment(12, 12, "Altay");
        assertTrue(payment.checkCard());
    }
    @Test
    @DisplayName("If credit card credentials are suitable, it should return true")
    void testCheckCardNegative() {
        Payment payment = new Payment(0, 0, null);
        assertFalse(payment.checkCard());
    }

    @Test
    @DisplayName("If checkCard() method returns true, Payment executes the process")
    void testPayPositive() {
        Payment payment = new Payment(12,12,"Altay");
        try {
            assertTrue(payment.pay());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("If checkCard() method returns false, Payment doesn't start the process")
    void testPayNegative() {
        Payment payment = new Payment(0,0,null);
        try {
            assertFalse(payment.pay());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
