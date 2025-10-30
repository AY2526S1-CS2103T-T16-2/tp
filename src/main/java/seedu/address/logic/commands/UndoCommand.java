package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undoes the last operation that modified the address book.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo successful.";
    public static final String MESSAGE_NOTHING_TO_UNDO = "Nothing to undo.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert(model != null) : "Model should not be null.";
        if (!model.canUndo()) {
            throw new CommandException(MESSAGE_NOTHING_TO_UNDO);
        }
        model.undo();
        return new CommandResult(MESSAGE_SUCCESS);
    }
} 