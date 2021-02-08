package pl.edu.agh.cs.app.ui.launcher.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

public class ModeSelectorPane extends HBox {

    private final SettingsPane settings;

    public ModeSelectorPane(SettingsPane settings) {
        super(0);
        this.settings = settings;
        settings.setSingleGameSettings();

        toggleButtonsCreation();
    }

    public void toggleButtonsCreation() {
        ToggleButton single = new ToggleButton("Single");
        ToggleButton multi = new ToggleButton("Multi");

        single.setOnAction(event -> {
            if (single.isSelected()) {
                settings.setSingleGameSettings();
                multi.setSelected(false);
            }
            else {
                single.setSelected(true);
            }
        });
        single.setId("single");
        single.getStyleClass().add("modeSelector");
        single.setMinSize(150, 60);
        single.setMaxSize(150, 60);
        single.setSelected(true);

        multi.setOnAction(event -> {
            if (multi.isSelected()) {
                settings.setMultiGameSettings();
                single.setSelected(false);
            }
            else {
                multi.setSelected(true);
            }
        });
        multi.setId("multi");
        multi.getStyleClass().add("modeSelector");
        multi.setMinSize(150, 60);
        multi.setMaxSize(150, 60);

        this.setAlignment(Pos.CENTER);
        int padding = 20;
        this.setPadding(new Insets(padding));
        this.getChildren().addAll(single, multi);
    }
}
