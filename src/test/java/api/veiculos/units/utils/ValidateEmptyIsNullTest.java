package api.veiculos.units.utils;


import api.veiculos.utils.ValidateEmptyIsNull;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateEmptyIsNullTest {

    @Test
    void testIsEmptyOrNotNull_EmptyString_ReturnsTrue() {
        assertTrue(ValidateEmptyIsNull.isEmptyOrNotNull(""));
    }

    @Test
    void testIsEmptyOrNotNull_BlankString_ReturnsTrue() {
        assertTrue(ValidateEmptyIsNull.isEmptyOrNotNull("   "));
    }

    @Test
    void testIsEmptyOrNotNull_NonEmptyString_ReturnsFalse() {
        assertFalse(ValidateEmptyIsNull.isEmptyOrNotNull("Hello, world!"));
    }
}
