package seedu.address.model.person;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Composite predicate to test if a Person matches all the contained predicates.
 */
public class PersonContainsKeywordsPredicate implements Predicate<Person> {

    private final List<Predicate<Person>> predicates;

    public PersonContainsKeywordsPredicate(List<Predicate<Person>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public boolean test(Person person) {
        // Return true only if all predicates match this person
        return predicates.stream().allMatch(predicate -> predicate.test(person));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PersonContainsKeywordsPredicate)) {
            return false;
        }
        PersonContainsKeywordsPredicate otherPersonPredicate = (PersonContainsKeywordsPredicate) other;
        return Objects.equals(predicates, otherPersonPredicate.predicates);
    }

    @Override
    public String toString() {
        return "PersonContainsKeywordsPredicate{" + "predicates=" + predicates + '}';
    }
}
