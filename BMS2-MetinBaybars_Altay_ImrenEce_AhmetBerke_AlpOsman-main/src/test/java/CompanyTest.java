import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CompanyTest {
    @Test
    @DisplayName("Companies with same name are equal")
    void testNameEqualsPositive() {
        Company company1 = new KamilKoc();
        Object company2 = new Company(company1.name) {};
        assertEquals(company1, company2);
    }

    @Test
    @DisplayName("Companies with different name are not equal")
    void testNameEqualsNegative() {
        Company company1 = new Company("name1") {};
        Company company2 = new Company("name2") {};
        assertNotEquals(company1, company2);
    }

    @Test
    @DisplayName("Companies that are from same class are equal")
    void testClassEqualsPositive() {
        Company company1 = new KamilKoc();
        Company company2 = new KamilKoc();
        assertEquals(company1, company2);
    }

    @Test
    @DisplayName("Objects that doesn't extend Company are not same with company")
    void testClassEqualsNegative() {
        Company company = new KamilKoc();
        Object other = new Object();
        assertNotEquals(company, other);
    }
}
