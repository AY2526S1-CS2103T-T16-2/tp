package seedu.address.model.product;

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
        // null product name
        assertThrows(NullPointerException.class, () -> Product.isValidProductName(null));
    }

}
