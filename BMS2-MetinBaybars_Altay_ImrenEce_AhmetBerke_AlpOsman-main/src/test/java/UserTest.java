import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    @Test
    @DisplayName("getIsLoggedIn should return false if user didn't login")
    void testUserIsLoggedInNegative() {
        User user = User.getInstance();
        if (user != null) {
            user.logOut();
        }
        assertFalse(User.getIsLoggedIn());
    }

    @Test
    @DisplayName("getIsLoggedIn should return true if user is logged in")
    void testUserIsLoggedInPositive() {
        User.registerUser("id", "name", "test@gmail.com", "username", "password");
        assertTrue(User.getIsLoggedIn());
    }
}
