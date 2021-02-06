package pl.edu.agh.cs.app.ui.launcher.panes;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import pl.edu.agh.cs.app.backend.utils.GameConfiguration;

import java.util.function.Function;

public class LauncherControlsPane extends HBox {
    private final Button defaultBtn;
    private final Button startBtn;
    private final Button exitBtn;

    private final Region blankSpace;

    private final int padding;

    private final Function<GameConfiguration, Void> playTrigger;
    private final SettingsPane settings;

    public LauncherControlsPane(Function<GameConfiguration, Void> playTrigger, SettingsPane settings) {
        super(20);

        this.playTrigger = playTrigger;
        this.settings = settings;

        padding = 20;
        this.setPadding(new Insets(padding, padding, padding, padding));
        this.setAlignment(Pos.CENTER);

        defaultBtn = new Button("Default configuration");
        startBtn = new Button("Start");
        exitBtn = new Button("Exit");

        defaultBtn.setOnAction(e -> defaultOnClick());
        startBtn.setOnAction(e -> startOnClick());
        exitBtn.setOnAction(e -> exitOnClick());

        blankSpace = new Region();
        HBox.setHgrow(blankSpace, Priority.ALWAYS);

        this.getChildren().addAll(defaultBtn, blankSpace, startBtn, exitBtn);
    }

    // TODO will be changed later to provide the default settings from JSON file
    protected void defaultOnClick() {
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("Feature is not yet written");
        msg.setHeaderText("Setting default settings will be available in the new version. Please type in manually :)");
        msg.show();
    }

    protected void startOnClick() {
        GameConfiguration config;
        try {
            config = settings.exportConfig();
        } catch (NumberFormatException ex) {
            // TODO maybe it is just temporary
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.setTitle("Wrong input");
            String ms = "The data you typed in cannot be interpretated as numbers. Make sure you write them correctly";
            msg.setHeaderText(ms);
            msg.show();
            return;
        }
        playTrigger.apply(config);
    }

    protected void exitOnClick() {
        Platform.exit();
    }
}
