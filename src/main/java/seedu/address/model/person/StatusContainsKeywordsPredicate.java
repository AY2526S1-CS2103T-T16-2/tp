package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

public class StatusContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public StatusContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getStatus().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof StatusContainsKeywordsPredicate)) {
            return false;
        }
        StatusContainsKeywordsPredicate otherStatusPredicate = (StatusContainsKeywordsPredicate) other;
        return keywords.equals(otherStatusPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
