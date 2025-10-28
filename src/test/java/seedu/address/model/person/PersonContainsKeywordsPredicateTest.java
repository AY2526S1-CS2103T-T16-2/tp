package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonContainsKeywordsPredicateTest {
    private final NameContainsKeywordsPredicate namePredicate =
            new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
    private final StatusContainsKeywordsPredicate statusPredicate =
            new StatusContainsKeywordsPredicate(Collections.singletonList("inprogress"));
    private final CompanyContainsKeywordsPredicate companyPredicate =
            new CompanyContainsKeywordsPredicate(Collections.singletonList("Google"));
    private final ProductContainsKeywordsPredicate productPredicate =
            new ProductContainsKeywordsPredicate(Arrays.asList("Apple", "Pear"));
    @Test
    public void equals() {
        NameContainsKeywordsPredicate namePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        StatusContainsKeywordsPredicate statusPredicate =
                new StatusContainsKeywordsPredicate(Collections.singletonList("Inprogress"));
        List<Predicate<Person>> firstPredicateList = Collections.singletonList(namePredicate);
        List<Predicate<Person>> secondPredicateList = Arrays.asList(namePredicate, statusPredicate);

        PersonContainsKeywordsPredicate firstPredicate =
                new PersonContainsKeywordsPredicate(firstPredicateList);
        PersonContainsKeywordsPredicate secondPredicate =
                new PersonContainsKeywordsPredicate(secondPredicateList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPredicateCopy =
                new PersonContainsKeywordsPredicate(firstPredicateList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personMatchesPredicates_returnsTrue() {
        // Matching namePredicate
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(
                Collections.singletonList(namePredicate));
        assertTrue(predicate.test(
                new PersonBuilder().withName("Alice").build()));

        // Matching namePredicate and statusPredicate
        predicate = new PersonContainsKeywordsPredicate(
                Arrays.asList(namePredicate, statusPredicate));
        assertTrue(predicate.test(
                new PersonBuilder().withName("Alice").withStatus("Inprogress").build()));

        // Matching namePredicate and companyPredicate
        predicate = new PersonContainsKeywordsPredicate(
                Arrays.asList(namePredicate, companyPredicate));
        assertTrue(predicate.test(
                new PersonBuilder().withName("Alice").withCompany("Google").build()));

        // Matching namePredicate and companyPredicate
        predicate = new PersonContainsKeywordsPredicate(
                Arrays.asList(namePredicate, productPredicate));
        assertTrue(predicate.test(
                new PersonBuilder().withName("Alice").withProducts("Apple", "Pear").build()));

        // Matching namePredicate, statusPredicate and companyPredicate
        predicate = new PersonContainsKeywordsPredicate(
                Arrays.asList(namePredicate, statusPredicate, companyPredicate));
        assertTrue(predicate.test(
                new PersonBuilder().withName("Alice").withStatus("Inprogress").withCompany("Google").build()));

        // Matching namePredicate, statusPredicate, companyPredicate and productPredicate
        predicate = new PersonContainsKeywordsPredicate(
                Arrays.asList(namePredicate, statusPredicate, companyPredicate, productPredicate));
        assertTrue(predicate.test(
                new PersonBuilder().withName("Alice").withStatus("Inprogress")
                        .withCompany("Google").withProducts("Apple", "Pear").build()));
    }

    @Test
    public void test_personDoesNotMatchPredicate_returnsFalse() {
        // Non-matching namePredicate
        PersonContainsKeywordsPredicate predicate =
                new PersonContainsKeywordsPredicate(Collections.singletonList(namePredicate));
        assertFalse(predicate.test(
                new PersonBuilder().withName("Bob").build()));

        // Matching namePredicate, non-matching statusPredicate
        predicate = new PersonContainsKeywordsPredicate(
                Arrays.asList(namePredicate, statusPredicate));
        assertFalse(predicate.test(
                new PersonBuilder().withName("Alice").withStatus("Successful").build()));

        // Matching namePredicate, companyPredicate, statusPredicate, but non-matching productPredicate
        predicate = new PersonContainsKeywordsPredicate(
                Arrays.asList(namePredicate, companyPredicate, statusPredicate, productPredicate));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withCompany("Google")
                .withStatus("Inprogress").withProducts("Chicken").build()));
    }

    @Test
    public void toStringMethod() {
        List<Predicate<Person>> predicates = Arrays.asList(
                new NameContainsKeywordsPredicate(Collections.singletonList("Alice")),
                new StatusContainsKeywordsPredicate(Collections.singletonList("Inprogress")));

        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(predicates);

        String expected = "PersonContainsKeywordsPredicate{" + "predicates=" + predicates + "}";
        assertEquals(expected, predicate.toString());
    }
}
