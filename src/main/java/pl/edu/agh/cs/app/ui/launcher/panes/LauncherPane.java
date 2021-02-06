package pl.edu.agh.cs.app.ui.launcher.panes;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import pl.edu.agh.cs.app.backend.utils.GameConfiguration;

import java.util.function.Function;

public class LauncherPane extends VBox {
    private final HBox modeSelector;  // TODO temporary decision, may be implemented later with ToggleButton and CSS sheet
    private final SettingsPane settings;
    private final LauncherControlsPane controls;
    private final Region blankSpace;

    private final int pxheight;
    private final int pxwidth;

    public LauncherPane(int height, int width, Function<GameConfiguration, Void> playTrigger) {
        pxheight = height;
        pxwidth = width;

        int betweenSettingsSpacing = 20;
        settings = new SettingsPane(betweenSettingsSpacing);
        controls = new LauncherControlsPane(playTrigger, settings);

        modeSelector = new ModeSelectorPane(settings);

        blankSpace = new Region();
        VBox.setVgrow(blankSpace, Priority.ALWAYS);

        this.getChildren().addAll(modeSelector, settings, blankSpace, controls);
    }
}
