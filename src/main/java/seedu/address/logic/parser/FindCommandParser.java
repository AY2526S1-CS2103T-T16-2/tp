package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import java.util.ArrayList;
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

        boolean isAnyPrefixPresent = argMultimap.getValue(PREFIX_NAME).isPresent()
                || argMultimap.getValue(PREFIX_COMPANY).isPresent()
                || argMultimap.getValue(PREFIX_STATUS).isPresent()
                || argMultimap.getValue(PREFIX_PRODUCT).isPresent();

        if (!isAnyPrefixPresent || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<Predicate<Person>> predicates = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            predicates.add(new NameContainsKeywordsPredicate(
                    ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_NAME).get())));
        }
        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            predicates.add(new CompanyContainsKeywordsPredicate(
                    ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_COMPANY).get())));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            predicates.add(new StatusContainsKeywordsPredicate(
                    ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_STATUS).get())));
        }
        if (argMultimap.getValue(PREFIX_PRODUCT).isPresent()) {
            predicates.add(new ProductContainsKeywordsPredicate(
                    ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_PRODUCT).get())));
        }
        if (predicates.size() == 1) {
            return new FindCommand(predicates.get(0));
        } else {
            return new FindCommand(new PersonContainsKeywordsPredicate(predicates));
        }
    }
}
