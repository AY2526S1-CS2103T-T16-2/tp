package seedu.address.ui;

import javafx.scene.Scene;

/**
 * Handles applying and switching between Light and Dark themes at runtime.
 */
public class ThemeManager {

    private Theme currentTheme;

    /**
     * Applies the specified theme to the scene.
     * If the same theme is already applied, nothing happens.
     */
    public void apply(Scene scene, Theme theme) {
        String cssPath = switch (theme) {
        case DARK -> "/view/DarkTheme.css";
        case LIGHT -> "/view/LightTheme.css";
        };

        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(cssPath).toExternalForm());
    }

    /**
     * Toggles between light and dark themes.
     */
    public void toggle(Scene scene) {
        if (currentTheme == Theme.LIGHT) {
            apply(scene, Theme.DARK);
        } else {
            apply(scene, Theme.LIGHT);
        }
    }

    /**
     * Returns the currently applied theme.
     */
    public Theme getCurrentTheme() {
        return currentTheme;
    }
}
