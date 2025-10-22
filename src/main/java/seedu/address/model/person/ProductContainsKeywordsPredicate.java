package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.product.Product;

/**
 * Tests that a {@code Person}'s {@code Product} matches any of the keywords
 * given.
 */
public class ProductContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public ProductContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Set<Product> products = person.getProducts();
        return keywords.stream()
                .anyMatch(keyword -> products.stream()
                        .anyMatch(product -> product.productName.toLowerCase()
                                .contains(keyword.toLowerCase())));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProductContainsKeywordsPredicate)) {
            return false;
        }

        ProductContainsKeywordsPredicate otherProductContainsKeywordsPredicate =
            (ProductContainsKeywordsPredicate) other;

        return keywords.equals(otherProductContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
