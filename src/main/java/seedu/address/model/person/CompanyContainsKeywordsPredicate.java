package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

public class CompanyContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public CompanyContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getCompany().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof CompanyContainsKeywordsPredicate)) {
            return false;
        }
        CompanyContainsKeywordsPredicate otherCompanyPredicate = (CompanyContainsKeywordsPredicate) other;
        return keywords.equals(otherCompanyPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
