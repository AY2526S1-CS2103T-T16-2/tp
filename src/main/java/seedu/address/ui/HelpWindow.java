package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {

    public static final String USERGUIDE_URL = "https://ay2526s1-cs2103t-t14-2.github.io/tp/UserGuide.html";
    public static final String URL_MESSAGE = "Refer to the user guide for more in-depth guide: " + USERGUIDE_URL;

    public static final String ADD_COMMAND_HELP = "add:\nAdds a person to the address book. "
                + "\nParameters: n/NAME p/PHONE_NUMBER c/COMPANY e/EMAIL a/ADDRESS "
                + "s/STATUS [t/TAG]\n"
                + "Example: add n/John Doe p/98765432 c/Google e/johndoe@gmail.com a/Woodlands s/Contacted";

    public static final String DELETE_COMMAND_HELP = "delete:\nDeletes a person identified by the index number "
                + "used in the displayed person list.\n"
                + "Parameters: INDEX (must be a positive integer)\n"
                + "Example: delete 1";

    public static final String EDIT_COMMAND_HELP = "edit:\nEdits the details of the person identified by the index "
                + "number used in the displayed person list. "
                + " [At least one field to edit must be provided.]\n"
                + "Parameters: INDEX (must be a positive integer) "
                + "[n/NAME] [p/PHONE_NUMBER] [c/COMPANY] [e/EMAIL] [a/ADDRESS] [s/STATUS] [t/TAG]\n"
                + "Example: edit 1 p/91234567";

    public static final String FIND_COMMAND_HELP = "find:\nFinds all persons whose names contain any of "
                + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
                + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
                + "Example: find alice bob charlie";

    public static final String CLEAR_COMMAND_HELP = "clear:\nClears all persons from the address book.\n"
                + "Example: clear";

    public static final String HELP_MESSAGE = "Here are some useful commands:\n\n"
                + ADD_COMMAND_HELP + "\n\n"
                + DELETE_COMMAND_HELP + "\n\n"
                + EDIT_COMMAND_HELP + "\n\n"
                + FIND_COMMAND_HELP + "\n\n"
                + CLEAR_COMMAND_HELP + "\n\n"
                + URL_MESSAGE;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    @FXML
    private Button copyButton;

    @FXML
    private Label helpMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        helpMessage.setText(HELP_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }

}
