package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CompanyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Company(null));
    }

    @Test
    public void constructor_invalidCompany_throwsIllegalArgumentException() {
        String invalidCompany = "";
        assertThrows(IllegalArgumentException.class, () -> new Company(invalidCompany));
    }

    @Test
    public void isValidCompany() {
        // null company
        assertThrows(NullPointerException.class, () -> Company.isValidCompanyName(null));

        // blank company
        assertFalse(Company.isValidCompanyName(""));
        assertFalse(Company.isValidCompanyName(" "));

        // invalid company
        assertFalse(Company.isValidCompanyName("popular^"));
        assertFalse(Company.isValidCompanyName("popular*"));
        assertFalse(Company.isValidCompanyName("popular!"));
        assertFalse(Company.isValidCompanyName("popular@"));
        assertFalse(Company.isValidCompanyName("popular#"));
        assertFalse(Company.isValidCompanyName("popular$"));
        assertFalse(Company.isValidCompanyName("popular%"));
        assertFalse(Company.isValidCompanyName("popular&"));
        assertFalse(Company.isValidCompanyName("popular("));
        assertFalse(Company.isValidCompanyName("popular("));
        assertFalse(Company.isValidCompanyName("popular)"));
        assertFalse(Company.isValidCompanyName("popular'"));
        assertFalse(Company.isValidCompanyName("popular\""));
        assertFalse(Company.isValidCompanyName("popular:"));
        assertFalse(Company.isValidCompanyName("popular;"));
        assertFalse(Company.isValidCompanyName("popular/"));
        assertFalse(Company.isValidCompanyName("popular?"));
        assertFalse(Company.isValidCompanyName("popular."));
        assertFalse(Company.isValidCompanyName("popular>"));
        assertFalse(Company.isValidCompanyName("popular<"));
        assertFalse(Company.isValidCompanyName("popular,"));
        assertFalse(Company.isValidCompanyName("popular\\"));
        assertFalse(Company.isValidCompanyName("popular|"));
        assertFalse(Company.isValidCompanyName("popular]"));
        assertFalse(Company.isValidCompanyName("popular}"));
        assertFalse(Company.isValidCompanyName("popular["));
        assertFalse(Company.isValidCompanyName("popular{"));
        assertFalse(Company.isValidCompanyName("popular="));
        assertFalse(Company.isValidCompanyName("popular+"));
        assertFalse(Company.isValidCompanyName("popular-"));
        assertFalse(Company.isValidCompanyName("popular_"));
        assertFalse(Company.isValidCompanyName("popular`"));
        assertFalse(Company.isValidCompanyName("popular~"));
        assertFalse(Company.isValidCompanyName(" popular"));

        // valid company
        assertTrue(Company.isValidCompanyName("popular"));
        assertTrue(Company.isValidCompanyName("popular123"));
        assertTrue(Company.isValidCompanyName("popular987"));
        assertTrue(Company.isValidCompanyName("popular123watsons"));
        assertTrue(Company.isValidCompanyName("popularWATSONS"));
        assertTrue(Company.isValidCompanyName("popularWATSONS123"));
        assertTrue(Company.isValidCompanyName("popular "));
    }

    @Test
    public void equals() {
        Company company = new Company("company");
        // same values -> returns true
        assertTrue(company.equals(new Company("company")));

        // same object -> returns true
        assertTrue(company.equals(company));

        // null -> returns false
        assertFalse(company.equals(null));

        // different types -> returns false
        assertFalse(company.equals(5.0f));

        // different values -> returns false
        assertFalse(company.equals(new Company("otherCompany")));
    }
}
