package seedu.address.ui;

/**
 * Enum representing available UI themes.
 */
public enum Theme {
    LIGHT("/view/LightTheme.css"),
    DARK("/view/DarkTheme.css");

    private final String cssPath;

    Theme(String cssPath) {
        this.cssPath = cssPath;
    }

    public String getCssPath() {
        return cssPath;
    }
}
