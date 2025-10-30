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

public class UndoCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    }

    @Test
    public void undo_afterAdd_removesPerson() throws Exception {
        model.addPerson(AMY);
        UndoCommand undoCommand = new UndoCommand();
        undoCommand.execute(model);
        assertEquals(expectedModel.getAddressBook(), model.getAddressBook());
    }

    @Test
    public void undo_afterDelete_restoresPerson() throws Exception {
        model.deletePerson(model.getFilteredPersonList().get(0));
        UndoCommand undoCommand = new UndoCommand();
        undoCommand.execute(model);
        assertEquals(expectedModel.getAddressBook(), model.getAddressBook());
    }

    @Test
    public void undo_afterEdit_restoresPrevious() throws Exception {
        model.setPerson(model.getFilteredPersonList().get(0), BOB);
        UndoCommand undoCommand = new UndoCommand();
        undoCommand.execute(model);
        assertEquals(expectedModel.getAddressBook(), model.getAddressBook());
    }

    @Test
    public void undo_nothingToUndo_throws() {
        UndoCommand undoCommand = new UndoCommand();
        assertThrows(CommandException.class, () -> undoCommand.execute(model));
    }
}
