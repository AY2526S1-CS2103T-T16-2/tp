package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list, "
            + "or all people with the specified status.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "or STATUS (uncontacted | inprogress | unsuccessful | successful)\n"
            + "Example: " + COMMAND_WORD + " 1 or " + COMMAND_WORD + " unsuccessful";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_BY_STATUS_SUCCESS = "Deleted all persons with status: %1$s";
    public static final String MESSAGE_NO_MATCHING_STATUS = "No persons found with status: %1$s";

    private final Index targetIndex;
    private final String targetStatus;

    /**
     * Creates a DeleteCommand to delete a person by index.
     *
     * @param targetIndex the index of the person in the list to delete
     */
    public DeleteCommand(Index targetIndex) {
        assert(targetIndex != null) : "Index should not be null.";
        this.targetIndex = targetIndex;
        this.targetStatus = null;
    }

    /**
     * Creates a DeleteCommand to delete all persons with the given status.
     *
     * @param targetStatus the status of persons to delete
     */
    public DeleteCommand(String targetStatus) {
        this.targetIndex = null;
        this.targetStatus = targetStatus.toLowerCase();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex != null) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
            assert(personToDelete != null) : "Index for delete should not be null or out of range.";
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
        }

        List<Person> allPersons = new ArrayList<>(model.getAddressBook().getPersonList());
        List<Person> toDelete = allPersons.stream()
                .filter(p -> p.getStatus().toString().equalsIgnoreCase(targetStatus))
                .toList();

        if (toDelete.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_NO_MATCHING_STATUS, targetStatus));
        }

        toDelete.forEach(model::deletePerson);

        return new CommandResult(String.format(MESSAGE_DELETE_BY_STATUS_SUCCESS, targetStatus));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;

        if (this.targetIndex != null && otherDeleteCommand.targetIndex != null) {
            return this.targetIndex.equals(otherDeleteCommand.targetIndex);
        }

        if (this.targetStatus != null && otherDeleteCommand.targetStatus != null) {
            return this.targetStatus.equals(otherDeleteCommand.targetStatus);
        }

        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
