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
    public static final String THIRTEENSPACER = "             ";
    public static final String FIVESPACER = "     ";
    public static final String ADD_COMMAND_HELP = "ADD:\nUse: add n/NAME p/PHONE_NUMBER c/COMPANY e/EMAIL a/ADDRESS "
                + "s/STATUS [pdt/PRODUCT]...";

    public static final String DELETE_COMMAND_HELP = "DELETE:\n"
                + "Use: delete INDEX (must be a positive integer) OR "
                + "delete STATUS (uncontacted, inprogress, successful, unsuccessful)";

    public static final String EDIT_COMMAND_HELP = "EDIT:\n"
                + "[At least one field to edit must be provided.]\n"
                + "Use: edit INDEX (must be a positive integer) "
                + "[n/NAME] [p/PHONE_NUMBER] [c/COMPANY] [e/EMAIL] [a/ADDRESS] [s/STATUS] [pdt/PRODUCT]...";

    public static final String FIND_COMMAND_HELP = "FIND:\n"
                + "[At least one field to edit must be provided.]\n"
                + "Use: find keyword/KEYWORD [MORE_KEYWORDS]...";

    public static final String LIST_COMMAND_HELP_HEADER = "LIST:";
    public static final String LIST_COMMAND_HELP_USAGE = "Use: list";

    public static final String HELP_COMMAND_HELP_HEADER = "HELP:";
    public static final String HELP_COMMAND_HELP_USAGE = "Use: help";

    public static final String CLEAR_COMMAND_HELP_HEADER = "CLEAR:";
    public static final String CLEAR_COMMAND_HELP_USAGE = "Use: clear";

    public static final String UNDO_COMMAND_HELP_HEADER = "UNDO:";
    public static final String UNDO_COMMAND_HELP_USAGE = "Use: undo";

    public static final String REDO_COMMAND_HELP_HEADER = "REDO:";
    public static final String REDO_COMMAND_HELP_USAGE = "Use: redo";

    public static final String SINGLE_LINE_HEADERS = LIST_COMMAND_HELP_HEADER + THIRTEENSPACER + FIVESPACER
            + HELP_COMMAND_HELP_HEADER + THIRTEENSPACER + FIVESPACER
            + CLEAR_COMMAND_HELP_HEADER + THIRTEENSPACER + FIVESPACER
            + UNDO_COMMAND_HELP_HEADER + THIRTEENSPACER + FIVESPACER
            + REDO_COMMAND_HELP_HEADER;

    public static final String SINGLE_LINE_USE = LIST_COMMAND_HELP_USAGE + THIRTEENSPACER
            + HELP_COMMAND_HELP_USAGE + "           "
            + CLEAR_COMMAND_HELP_USAGE + THIRTEENSPACER + " "
            + UNDO_COMMAND_HELP_USAGE + "            "
            + REDO_COMMAND_HELP_USAGE;

    public static final String HELP_MESSAGE = "Here are all the useful commands:\n\n"
                + ADD_COMMAND_HELP + "\n\n"
                + DELETE_COMMAND_HELP + "\n\n"
                + EDIT_COMMAND_HELP + "\n\n"
                + FIND_COMMAND_HELP + "\n\n"
                + SINGLE_LINE_HEADERS + "\n"
                + SINGLE_LINE_USE + "\n\n"
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

        // Choose between Light and Dark theme
        ThemeManager themes = new ThemeManager();
        Theme theme = SystemThemeDetector.detect();
        themes.applyHelp(getRoot().getScene(), theme);
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
