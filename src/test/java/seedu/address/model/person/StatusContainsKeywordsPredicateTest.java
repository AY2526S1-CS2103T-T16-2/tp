package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class StatusContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        StatusContainsKeywordsPredicate firstPredicate = new StatusContainsKeywordsPredicate(firstPredicateKeywordList);
        StatusContainsKeywordsPredicate secondPredicate =
                new StatusContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        StatusContainsKeywordsPredicate firstPredicateCopy =
                new StatusContainsKeywordsPredicate(firstPredicateKeywordList);
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
        // Keyword successful
        StatusContainsKeywordsPredicate predicate = new StatusContainsKeywordsPredicate(
                Collections.singletonList("Successful"));
        assertTrue(predicate.test(new PersonBuilder().withStatus("Successful").build()));

        // Keyword unsuccessful
        predicate = new StatusContainsKeywordsPredicate(
                Collections.singletonList("Unsuccessful"));
        assertTrue(predicate.test(new PersonBuilder().withStatus("Unsuccessful").build()));

        // Keyword uncontacted
        predicate = new StatusContainsKeywordsPredicate(
                Collections.singletonList("Uncontacted"));
        assertTrue(predicate.test(new PersonBuilder().withStatus("Uncontacted").build()));

        // Keyword inprogress
        predicate = new StatusContainsKeywordsPredicate(
                Collections.singletonList("Inprogress"));
        assertTrue(predicate.test(new PersonBuilder().withStatus("Inprogress").build()));

        // Multiple keywords
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("Unsuccessful", "Successful"));
        assertTrue(predicate.test(new PersonBuilder().withStatus("Successful").build()));


        // Mixed-case keywords
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("InPrOGress", "sucCesSfuL"));
        assertTrue(predicate.test(new PersonBuilder().withStatus("Inprogress").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StatusContainsKeywordsPredicate predicate =
                new StatusContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withStatus("Inprogress").build()));

        // Non-matching keyword
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList("Inprogress"));
        assertFalse(predicate.test(new PersonBuilder().withStatus("Successful").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new StatusContainsKeywordsPredicate(Arrays.asList(
                "Alice", "12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("82345678")
                .withEmail("alice@email.com").withAddress("Main Street").withStatus("Unsuccessful").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        StatusContainsKeywordsPredicate predicate = new StatusContainsKeywordsPredicate(keywords);

        String expected = StatusContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
