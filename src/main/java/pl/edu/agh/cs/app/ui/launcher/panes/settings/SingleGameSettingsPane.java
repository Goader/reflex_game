package pl.edu.agh.cs.app.ui.launcher.panes.settings;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SingleGameSettingsPane extends VBox {
    private final SettingsBox failsCountBox;
    private final SettingsBox deltaTimeBox;

    private final TextField failsCountField;
    private final TextField gameTypeField;

    public SingleGameSettingsPane(int spacing, int boxSpacing) {
        super(spacing);

        failsCountField = new TextField("10");
        gameTypeField = new TextField("10");

        failsCountBox = new SettingsBox(boxSpacing);
        deltaTimeBox = new SettingsBox(boxSpacing);

        failsCountBox.getChildren().add(new Label("Type in the maximum fails allowed"));
        failsCountBox.getChildren().add(failsCountField);

        deltaTimeBox.getChildren().add(new Label("Type in the time in ms by which the general one will be reduced"));
        deltaTimeBox.getChildren().add(gameTypeField);

        this.getChildren().addAll(failsCountBox, deltaTimeBox);
    }

    public int getFailsCount() {
        return Integer.parseInt(failsCountField.getText());
    }

    public int getDeltaTime() {
        return Integer.parseInt(gameTypeField.getText());
    }
}
