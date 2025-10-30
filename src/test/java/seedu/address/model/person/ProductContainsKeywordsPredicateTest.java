package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ProductContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ProductContainsKeywordsPredicate firstPredicate =
                new ProductContainsKeywordsPredicate(firstPredicateKeywordList);
        ProductContainsKeywordsPredicate secondPredicate =
                new ProductContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ProductContainsKeywordsPredicate firstPredicateCopy =
                new ProductContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        ProductContainsKeywordsPredicate predicate =
                new ProductContainsKeywordsPredicate(Collections.singletonList("Chicken"));
        assertTrue(predicate.test(new PersonBuilder().withProducts("Chicken").build()));

        // Multiple keywords
        predicate = new ProductContainsKeywordsPredicate(Arrays.asList("Chicken", "Beef"));
        assertTrue(predicate.test(new PersonBuilder().withProducts("Chicken", "Beef").build()));

        // Only one matching keyword
        predicate = new ProductContainsKeywordsPredicate(Arrays.asList("Pork", "Beef"));
        assertTrue(predicate.test(new PersonBuilder().withProducts("Chicken", "Beef").build()));

        // Mixed-case keywords
        predicate = new ProductContainsKeywordsPredicate(Arrays.asList("chiCKen", "bEEf"));
        assertTrue(predicate.test(new PersonBuilder().withProducts("Chicken", "Beef").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ProductContainsKeywordsPredicate predicate = new ProductContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withProducts("Chicken").build()));

        // Non-matching keyword
        predicate = new ProductContainsKeywordsPredicate(Arrays.asList("Cow"));
        assertFalse(predicate.test(new PersonBuilder().withName("Beef").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new ProductContainsKeywordsPredicate(
                Arrays.asList("Alice", "12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("82345678")
                .withEmail("alice@email.com").withAddress("Main Street").withProducts("Chicken").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        ProductContainsKeywordsPredicate predicate = new ProductContainsKeywordsPredicate(keywords);

        String expected = ProductContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
