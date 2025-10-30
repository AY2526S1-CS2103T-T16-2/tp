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
        if (scene == null || theme == null) {
            return;
        }

        // Prevent reapplying the same theme unnecessarily
        if (currentTheme == theme) {
            return;
        }

        // Clear existing stylesheets
        scene.getStylesheets().clear();

        // Add the selected theme’s stylesheet
        String cssPath = theme.getCssPath();
        String cssUrl = getClass().getResource(cssPath).toExternalForm();
        scene.getStylesheets().add(cssUrl);

        currentTheme = theme;
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