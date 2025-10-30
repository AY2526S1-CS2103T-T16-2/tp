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

        // invalid product names
        assertFalse(Product.isValidProductName("")); // Blank product name
        assertFalse(Product.isValidProductName("Blue Chee$e")); // Non-alphanumeric character
        assertFalse(Product.isValidProductName("Chicken/Beef Stock")); // Non-alphanumeric character
        assertFalse(Product.isValidProductName("Bl!e Cheese")); // Non-alphanumeric character
        assertFalse(Product.isValidProductName("2.0-2.5 ml spray")); // Non-alphanumeric character
        assertFalse(Product.isValidProductName("Fish & Chips")); // Non-alphanumeric character

        // valid product names
        assertTrue(Product.isValidProductName("Beef")); // One-worded product
        assertTrue(Product.isValidProductName("Blue Cheese")); // Two-worded product
        assertTrue(Product.isValidProductName("Fish and Chips")); // Three-worded product
        assertTrue(Product.isValidProductName("Blue or Yellow Cheese")); // Four-worded product
    }

}
