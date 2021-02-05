package pl.edu.agh.cs.app.ui.launcher.panes.settings;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SingleGameSettingsPane extends VBox {
    private final SettingsBox failsCountBox;
    private final SettingsBox gameTypeBox;

    private final TextField failsCountField;
    private final TextField gameTypeField;

    public SingleGameSettingsPane(int spacing, int boxSpacing) {
        super(spacing);

        // gameType is called so because it can be upgraded later to such state, now it is rather DeltaTime

        failsCountField = new TextField();
        gameTypeField = new TextField();

        failsCountBox = new SettingsBox(boxSpacing);
        gameTypeBox = new SettingsBox(boxSpacing);

        failsCountBox.getChildren().add(new Label("Type in the maximum fails allowed"));
        failsCountBox.getChildren().add(failsCountField);

        gameTypeBox.getChildren().add(new Label("Type in the time in ms by which the general one will be reduced"));
        gameTypeBox.getChildren().add(gameTypeField);

        this.getChildren().addAll(failsCountBox, gameTypeBox);
    }

    public int getFailsCount() {
        return Integer.parseInt(failsCountField.getText());
    }

    public int getDeltaTime() {
        return Integer.parseInt(gameTypeField.getText());
    }
}
