package seedu.address.ui;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Best-effort OS theme detector.
 * Returns Theme.DARK when the OS prefers dark mode; otherwise Theme.LIGHT.
 * Always fails safe to LIGHT.
 */
public final class SystemThemeDetector {
    private SystemThemeDetector() {}

    /**
     * Detects if system is in light or dark mode.
     * @return Theme of current system.
     */
    public static Theme detect() {
        String os = System.getProperty("os.name", "").toLowerCase();
        try {
            if (os.contains("mac")) {
                return detectMac();
            }
            if (os.contains("win")) {
                return detectWindows();
            }
            if (os.contains("linux")) {
                return detectLinux();
            }
        } catch (Exception ignored) {
            // fall through
        }
        return Theme.LIGHT;
    }

    // ---------- macOS ----------
    // Dark mode => `defaults read -g AppleInterfaceStyle` returns "Dark" (exit 0).
    private static Theme detectMac() throws IOException, InterruptedException {
        Process p = new ProcessBuilder("defaults", "read", "-g", "AppleInterfaceStyle")
                .redirectErrorStream(true).start();
        int code = p.waitFor();
        return (code == 0) ? Theme.DARK : Theme.LIGHT;
    }

    // ---------- Windows 10/11 ----------
    // HKCU\...\Personalize\AppsUseLightTheme : 1 = light, 0 = dark
    private static Theme detectWindows() throws IOException, InterruptedException {
        Process p = new ProcessBuilder("reg", "query",
                "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize",
                "/v", "AppsUseLightTheme")
                .redirectErrorStream(true).start();
        String out = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        p.waitFor();
        boolean isLight = out.contains("0x1");
        return isLight ? Theme.LIGHT : Theme.DARK;
    }

    // ---------- Linux (GNOME 42+) ----------
    // org.gnome.desktop.interface color-scheme: 'prefer-dark' or 'default'
    private static Theme detectLinux() throws IOException, InterruptedException {
        Process p = new ProcessBuilder("gsettings", "get",
                "org.gnome.desktop.interface", "color-scheme")
                .redirectErrorStream(true).start();
        String out = new String(p.getInputStream().readAllBytes(), StandardCharsets.UTF_8).toLowerCase();
        p.waitFor();
        if (out.contains("prefer-dark")) {
            return Theme.DARK;
        }

        // Fallback hint: GTK theme name
        String gtk = System.getenv("GTK_THEME");
        if (gtk != null && gtk.toLowerCase().contains("dark")) {
            return Theme.DARK;
        }

        return Theme.LIGHT;
    }
}
