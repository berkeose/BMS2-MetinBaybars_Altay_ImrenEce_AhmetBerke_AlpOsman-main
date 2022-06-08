import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConfirmationDialogTest {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        // Set the console outputs
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @AfterEach
    void cleanUp() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    @DisplayName("Returns true for input y or Y")
    void testReturnsTrueForY() {
        String[] inputs = {"y", "Y"};
        for (String input: inputs) {
            System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
            boolean result = ConfirmationDialog.show("Message");
            assertTrue(result);
        }
    }

    @Test
    @DisplayName("Returns false for input n or N")
    void testReturnsFalseForN() {
        String[] inputs = {"n", "N"};
        for (String input: inputs) {
            System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
            boolean result = ConfirmationDialog.show("Message");
            assertFalse(result);
        }
    }

    @Test
    @DisplayName("Returns false for random input")
    void testReturnsFalseForRandomInput() {
        String[] inputs = {"hey", "value", "aoetuaoe", "\n", " ", "3"};
        for (String input: inputs) {
            System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
            boolean result = ConfirmationDialog.show("Message");
            assertFalse(result);
        }
    }
}
