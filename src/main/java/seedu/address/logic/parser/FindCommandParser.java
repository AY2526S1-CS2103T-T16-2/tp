package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_EMPTY_FIELDS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.CompanyContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsKeywordsPredicate;
import seedu.address.model.person.ProductContainsKeywordsPredicate;
import seedu.address.model.person.StatusContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * FindCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COMPANY,
                PREFIX_STATUS, PREFIX_PRODUCT);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_COMPANY, PREFIX_STATUS, PREFIX_PRODUCT);

        boolean isAnyPrefixPresent = argMultimap.getValue(PREFIX_NAME).isPresent()
                || argMultimap.getValue(PREFIX_COMPANY).isPresent()
                || argMultimap.getValue(PREFIX_STATUS).isPresent()
                || argMultimap.getValue(PREFIX_PRODUCT).isPresent();

        if (!isAnyPrefixPresent || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<String> emptyFields = new ArrayList<>();
        List<Predicate<Person>> predicates = new ArrayList<>();

        argMultimap.getValue(PREFIX_NAME).ifPresent(value -> {
            List<String> names = ParserUtil.parseKeywords(value);
            if (names.isEmpty()) {
                emptyFields.add("name");
            } else {
                predicates.add(new NameContainsKeywordsPredicate(names));
            }
        });

        argMultimap.getValue(PREFIX_COMPANY).ifPresent(value -> {
            List<String> companies = ParserUtil.parseKeywords(value);
            if (companies.isEmpty()) {
                emptyFields.add("company");
            } else {
                predicates.add(new CompanyContainsKeywordsPredicate(companies));
            }
        });

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            List<String> statusKeywords = ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_STATUS).get());

            boolean allValid = isValidKeyword(statusKeywords);

            if (!allValid) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }

            if (statusKeywords.isEmpty()) {
                emptyFields.add("status");
            } else {
                predicates.add(new StatusContainsKeywordsPredicate(statusKeywords));
            }
        }

        argMultimap.getValue(PREFIX_PRODUCT).ifPresent(value -> {
            List<String> products = ParserUtil.parseKeywords(value);
            if (products.isEmpty()) {
                emptyFields.add("product");
            } else {
                predicates.add(new ProductContainsKeywordsPredicate(products));
            }
        });

        if (!emptyFields.isEmpty()) {
            throw new ParseException(MESSAGE_EMPTY_FIELDS + String.join(", ", emptyFields));
        }
        if (predicates.size() == 1) {
            return new FindCommand(predicates.get(0));
        } else {
            return new FindCommand(new PersonContainsKeywordsPredicate(predicates));
        }
    }

    /**
     * Check if the list of statusKeywords are valid inputs.
     * @param statusKeywords is the list of keyword from user.
     * @return true if all the keywords are valid.
     */
    public boolean isValidKeyword(List<String> statusKeywords) {
        List<String> allowedStatuses = Arrays.asList("uncontacted", "inprogress", "successful", "unsuccessful");
        boolean allValid = true;
        for (String keyword : statusKeywords) {
            if (!allowedStatuses.contains(keyword.toLowerCase())) {
                allValid = false;
                break;
            }
        }

        return allValid;
    }
}
