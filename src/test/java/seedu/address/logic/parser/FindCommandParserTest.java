package seedu.address.logic.parser;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
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

    @Test
    public void parse_invalidArgs_returnsFindStatusCommand() {
        assertParseFailure(parser, PREFIX_STATUS + "success",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser, PREFIX_STATUS + "uncontact",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser, PREFIX_STATUS + "progress",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser, PREFIX_STATUS + "abc",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser, PREFIX_STATUS + "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser, PREFIX_STATUS + "pending done",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void isValidKeywordTest() {
        List<String> uncontactedKeyword = Arrays.asList("uncontacted");
        assertTrue(parser.isValidKeyword(uncontactedKeyword));

        List<String> inprogressKeyword = Arrays.asList("inprogress");
        assertTrue(parser.isValidKeyword(inprogressKeyword));

        List<String> successfulKeyword = Arrays.asList("successful");
        assertTrue(parser.isValidKeyword(successfulKeyword));

        List<String> unsuccessfulKeyword = Arrays.asList("unsuccessful");
        assertTrue(parser.isValidKeyword(unsuccessfulKeyword));
    }

    @Test
    public void parse_invalidStatus_throwsParseException() {
        List<String> invalidKeywords = Arrays.asList("invalidStatus", "notSuccessful", "abc", "");

        assertThrows(ParseException.class, () -> {
            parser.parse("find s/" + String.join(" ", invalidKeywords));
        });
    }

    @Test
    public void parse_duplicateArgs_throwsParseException() {
        // duplicate name prefix
        assertParseFailure(parser, " " + PREFIX_NAME + "Alice " + PREFIX_NAME + "Bob",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // duplicate status prefix
        assertParseFailure(parser, " " + PREFIX_STATUS + "Inprogress " + PREFIX_STATUS + "Successful",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));

        // duplicate company prefix
        assertParseFailure(parser, " " + PREFIX_COMPANY + "Google " + PREFIX_COMPANY + "Microsoft",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_COMPANY));

        // duplicate product prefix
        assertParseFailure(parser, " " + PREFIX_PRODUCT + "Chicken " + PREFIX_PRODUCT + "Beef",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PRODUCT));

        // duplicate name prefix and product prefix
        assertParseFailure(parser, " " + PREFIX_NAME + "Alice " + PREFIX_NAME + "Bob "
                + PREFIX_PRODUCT + "Chicken " + PREFIX_PRODUCT + "Beef",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_PRODUCT));

        // duplicate name prefix, status prefix, and product prefix
        assertParseFailure(parser, " " + PREFIX_NAME + "Alice " + PREFIX_NAME + "Bob "
                + PREFIX_STATUS + "Inprogress " + PREFIX_STATUS + "Successful "
                + PREFIX_PRODUCT + "Chicken " + PREFIX_PRODUCT + "Beef",
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_STATUS, PREFIX_PRODUCT));

        // duplicate name prefix, status prefix, company prefix, and product prefix
        assertParseFailure(parser, " " + PREFIX_NAME + "Alice " + PREFIX_NAME + "Bob "
                + PREFIX_STATUS + "Inprogress " + PREFIX_STATUS + "Successful "
                + PREFIX_COMPANY + "Google " + PREFIX_COMPANY + "Microsoft "
                + PREFIX_PRODUCT + "Chicken " + PREFIX_PRODUCT + "Beef",
                Messages.getErrorMessageForDuplicatePrefixes(
                        PREFIX_NAME, PREFIX_COMPANY, PREFIX_STATUS, PREFIX_PRODUCT));
    }
}
