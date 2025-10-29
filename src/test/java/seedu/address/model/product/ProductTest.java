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

}
