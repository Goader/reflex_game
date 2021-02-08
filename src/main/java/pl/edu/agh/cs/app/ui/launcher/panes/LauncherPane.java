package pl.edu.agh.cs.app.ui.launcher.panes;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;

import java.util.function.Function;

public class LauncherPane extends VBox {
    private final HBox modeSelector;
    private final SettingsPane settings;
    private final LauncherControlsPane controls;
    private final Region blankSpace;

    public LauncherPane(int height, int width, Function<GameConfiguration, Void> playTrigger) {

        int betweenSettingsSpacing = 20;
        settings = new SettingsPane(betweenSettingsSpacing);
        controls = new LauncherControlsPane(playTrigger, settings);

        modeSelector = new ModeSelectorPane(settings);

        blankSpace = new Region();
        VBox.setVgrow(blankSpace, Priority.ALWAYS);

        this.getChildren().addAll(modeSelector, settings, blankSpace, controls);
    }
}
