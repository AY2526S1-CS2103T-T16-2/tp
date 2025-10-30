package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Redoes the last undone operation in the address book.
 */
public class RedoCommand extends Command {
    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo successful.";
    public static final String MESSAGE_NOTHING_TO_REDO = "Nothing to redo.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model == null) {
            throw new CommandException("Model cannot be null.");
        }
        assert(model != null) : "Model should not be null.";
        if (!model.canRedo()) {
            throw new CommandException(MESSAGE_NOTHING_TO_REDO);
        }
        model.redo();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
