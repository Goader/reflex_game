package pl.edu.agh.cs.app.ui.launcher.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ModeSelectorPane extends HBox {

    private final SettingsPane settings;

    public ModeSelectorPane(SettingsPane settings) {
        super(10);
        this.settings = settings;
        settings.setSingleGameSettings();

        temporaryCreation();
    }

    // TODO change!!!
    public void temporaryCreation() {
        Button single = new Button("Single");
        single.setOnAction(event -> settings.setSingleGameSettings());
        Button multi = new Button("Multi");
        multi.setOnAction(event -> settings.setMultiGameSettings());
        this.setAlignment(Pos.CENTER);
        int padding = 20;
        this.setPadding(new Insets(padding));
        this.getChildren().addAll(single, multi);
    }
}
