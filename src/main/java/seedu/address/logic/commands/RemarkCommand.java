package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";
    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Remark command is not yet implemented.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from remark");
    }
}


