package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.RemarkCommand.MESSAGE_ARGUMENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Remark;

public class RemarkCommandTest {
    private Model model = new ModelManager();

    @Test
    public void execute() {
        final Remark remark = new Remark("Some remark");

        assertCommandFailure(new RemarkCommand(INDEX_FIRST_PERSON, remark), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_PERSON.getOneBased(), remark));
    }

    @Test
    public void equals() {
        final RemarkCommand standaradCommand = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_ADDRESS_AMY));

        RemarkCommand commandWithSameValues = new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_ADDRESS_AMY));

        // same values => true
        assertTrue(standaradCommand.equals(commandWithSameValues));

        // same object => true
        assertTrue(standaradCommand.equals(standaradCommand));

        // object != null
        assertFalse(standaradCommand.equals(null));

        // different command => not the same
        assertFalse(standaradCommand.equals(new ClearCommand()));

        // different index
        assertFalse(standaradCommand.equals(new RemarkCommand(INDEX_SECOND_PERSON, new Remark(VALID_REMARK_AMY))));

        // different remark
        assertFalse(standaradCommand.equals(new RemarkCommand(INDEX_FIRST_PERSON, new Remark(VALID_REMARK_BOB))));
    }
}
