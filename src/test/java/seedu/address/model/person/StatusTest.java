package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null name
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid name
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only
        assertFalse(Status.isValidStatus("^")); // only non-alphanumeric characters
        assertFalse(Status.isValidStatus("peter*")); // contains non-alphanumeric characters
        assertFalse(Status.isValidStatus("12345")); // numbers only


        // valid name
        assertTrue(Status.isValidStatus("Uncontacted")); // First letter capitalized
        assertTrue(Status.isValidStatus("inprogress")); // all lowercase
        assertTrue(Status.isValidStatus("succEssful")); // Middle letters capitalized
        assertTrue(Status.isValidStatus("UNSUCCESSFUL")); // with capital letters
        assertTrue(Status.isValidStatus("uNcOnTaCtEd")); // alternating capitalization
    }

    @Test
    public void equals() {
        Status status = new Status("uncontacted");

        // same values -> returns true
        assertTrue(status.equals(new Status("uncontacted")));

        // same object -> returns true
        assertTrue(status.equals(status));

        // null -> returns false
        assertFalse(status.equals(null));

        // different types -> returns false
        assertFalse(status.equals(5.0f));

        // different values -> returns false
        assertFalse(status.equals(new Status("inprogress")));

        assert status != null : "Status object should not be null";
    }

}
