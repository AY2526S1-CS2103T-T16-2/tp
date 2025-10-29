package seedu.address.model.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Product(null));
    }

    @Test
    public void constructor_invalidProductName_throwsIllegalArgumentException() {
        String invalidProductName = "";
        assertThrows(IllegalArgumentException.class, () -> new Product(invalidProductName));
    }

    @Test
    public void isValidProductName() {
        // null
        assertThrows(NullPointerException.class, () -> Product.isValidProductName(null));

        // invalid
        assertFalse(Product.isValidProductName(""));          // empty
        assertFalse(Product.isValidProductName("Fish&Chips"));// invalid char
        assertFalse(Product.isValidProductName("Fish & Chips"));// invalid char with space


        // valid
        assertTrue(Product.isValidProductName("Chicken Rice"));
        assertTrue(Product.isValidProductName("Chicken potatoes"));
        assertTrue(Product.isValidProductName("Beef123"));
    }

    @Test
    public void isValidProductName_leadingOrTrailingSpace_true() {
        // invalid if regex disallows leading/trailing spaces
        assertTrue(Product.isValidProductName(" Chicken"));
        assertTrue(Product.isValidProductName("Chicken "));
        assertTrue(Product.isValidProductName("  Chicken Rice"));
        assertTrue(Product.isValidProductName("Chicken Rice  "));
    }

    @Test
    public void isValidProductName_multipleConsecutiveSpaces_false_or_trueDependingOnRegex() {
        // If your regex allows only single spaces between words, keep these false:
        assertTrue(Product.isValidProductName("Chicken  Rice"));
        assertTrue(Product.isValidProductName("Beef   Brisket 123"));
        // If you decide to allow multiple spaces, change the above to assertTrue.
    }

    @Test
    public void isValidProductName_symbolsAreInvalid_false() {
        assertFalse(Product.isValidProductName("Fish&Chips"));     // symbol
        assertFalse(Product.isValidProductName("Fish-Chips"));     // dash
        assertFalse(Product.isValidProductName("Fish/Chips"));     // slash
        assertFalse(Product.isValidProductName("Fish_Chips"));     // underscore
        assertFalse(Product.isValidProductName("Chicken."));       // dot
        assertFalse(Product.isValidProductName("Rice,"));          // comma
    }

    @Test
    public void isValidProductName_validWithSingleSpaces_true() {
        assertTrue(Product.isValidProductName("Chicken Rice"));
        assertTrue(Product.isValidProductName("Chicken Potatoes 123"));
        assertTrue(Product.isValidProductName("BEEF123"));
        assertTrue(Product.isValidProductName("beef123"));
    }

    @Test
    public void isValidProductName_tabsAndNewlines_false() {
        assertFalse(Product.isValidProductName("Chicken\tRice"));
        assertFalse(Product.isValidProductName("Chicken\nRice"));
    }

    @Test
    public void constructor_validNames_success() {
        // Should not throw for valid names
        new Product("Chicken Rice");
        new Product("Beef123");
        new Product("Chicken Potatoes 123");
    }

    @Test
    public void constructor_invalidNames_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Product(""));
        assertThrows(IllegalArgumentException.class, () -> new Product(" "));
        assertThrows(IllegalArgumentException.class, () -> new Product(" Fish"));
        assertThrows(IllegalArgumentException.class, () -> new Product("Fish "));
        assertThrows(IllegalArgumentException.class, () -> new Product("Fish&Chips"));
    }

    @Test
    public void equals_sameName_true_differentName_false() {
        Product a = new Product("Chicken Rice");
        Product b = new Product("Chicken Rice");
        Product c = new Product("Beef 123");
        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
    }

    @Test
    public void toString_containsName() {
        Product p = new Product("Chicken Rice");
        assertTrue(p.toString().contains("Chicken Rice"));
    }

}
