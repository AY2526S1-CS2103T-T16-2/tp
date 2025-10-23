package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.CompanyContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.ProductContainsKeywordsPredicate;
import seedu.address.model.person.StatusContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindNameCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " " + PREFIX_NAME + "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindStatusCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new StatusContainsKeywordsPredicate(Arrays.asList("Successful")));
        assertParseSuccess(parser, " " + PREFIX_STATUS + "Successful", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_STATUS + " \n \t Successful  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCompanyCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new CompanyContainsKeywordsPredicate(Arrays.asList("Watsons", "Guardian")));
        assertParseSuccess(parser, " " + PREFIX_COMPANY + "Watsons Guardian", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_COMPANY + " \n Watsons \n \t Guardian  \t", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindProductsCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new ProductContainsKeywordsPredicate(Arrays.asList("Fish", "Ball")));
        assertParseSuccess(parser, " " + PREFIX_PRODUCT + "Fish Ball", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_PRODUCT + " \n Fish \n \t Ball  \t", expectedFindCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // input without leading space
        assertParseFailure(parser, PREFIX_NAME + "Alice Bob",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // empty input
        assertParseFailure(parser, "   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // input with extra preamble before prefix
        assertParseFailure(parser, "test" + PREFIX_NAME + "ALICE BOB",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }
}
