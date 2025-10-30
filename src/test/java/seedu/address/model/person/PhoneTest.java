package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone("123")); // only 3 digits
        assertFalse(Phone.isValidPhone("8888888")); // only 7 digits (edge case)
        assertFalse(Phone.isValidPhone("888888888")); // 9 digits (edge case)
        assertFalse(Phone.isValidPhone("8293 1899")); // contain space
        assertFalse(Phone.isValidPhone("8293123$")); // contain symbol

        // valid phone numbers
        assertTrue(Phone.isValidPhone("33121534")); //starts with 3
        assertTrue(Phone.isValidPhone("63121534")); //starts with 6
        assertTrue(Phone.isValidPhone("83121534")); //starts with 8
        assertTrue(Phone.isValidPhone("93121534")); //starts with 9
    }

    @Test
    public void equals() {
        Phone phone = new Phone("82911928");

        // same values -> returns true
        assertTrue(phone.equals(new Phone("82911928")));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // null -> returns false
        assertFalse(phone.equals(null));

        // different types -> returns false
        assertFalse(phone.equals(5.0f));

        // different values -> returns false
        assertFalse(phone.equals(new Phone("82911927")));
    }
}
