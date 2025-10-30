package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class RedoCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    }

    @Test
    public void redo_afterUndoAdd_restoresPerson() throws Exception {
        model.addPerson(AMY);
        model.undo();
        RedoCommand redoCommand = new RedoCommand();
        redoCommand.execute(model);
        expectedModel.addPerson(AMY);
        assertEquals(expectedModel.getAddressBook(), model.getAddressBook());
    }

    @Test
    public void redo_afterUndoDelete_removesPersonAgain() throws Exception {
        model.deletePerson(model.getFilteredPersonList().get(0));
        model.undo();
        RedoCommand redoCommand = new RedoCommand();
        redoCommand.execute(model);
        expectedModel.deletePerson(expectedModel.getFilteredPersonList().get(0));
        assertEquals(expectedModel.getAddressBook(), model.getAddressBook());
    }

    @Test
    public void redo_afterUndoEdit_appliesEditAgain() throws Exception {
        model.setPerson(model.getFilteredPersonList().get(0), BOB);
        model.undo();
        RedoCommand redoCommand = new RedoCommand();
        redoCommand.execute(model);
        expectedModel.setPerson(expectedModel.getFilteredPersonList().get(0), BOB);
        assertEquals(expectedModel.getAddressBook(), model.getAddressBook());
    }

    @Test
    public void redo_nothingToRedo_throws() {
        RedoCommand redoCommand = new RedoCommand();
        assertThrows(CommandException.class, () -> redoCommand.execute(model));
    }
}
