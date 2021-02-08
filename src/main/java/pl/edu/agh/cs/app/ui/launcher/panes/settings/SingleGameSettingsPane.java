package pl.edu.agh.cs.app.ui.launcher.panes.settings;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SingleGameSettingsPane extends VBox {
    private final SettingsBox failsCountBox;
    private final SettingsBox deltaTimeBox;

    private final TextField failsCountField;
    private final TextField deltaTimeField;

    public SingleGameSettingsPane(int spacing, int boxSpacing) {
        super(spacing);

        failsCountField = new TextField();
        deltaTimeField = new TextField();

        failsCountBox = new SettingsBox(boxSpacing);
        deltaTimeBox = new SettingsBox(boxSpacing);

        failsCountBox.getChildren().add(new Label("Maximum fails allowed"));
        failsCountBox.getChildren().add(failsCountField);

        deltaTimeBox.getChildren().add(new Label("Time in ms by which the general one will be reduced"));
        deltaTimeBox.getChildren().add(deltaTimeField);

        this.getChildren().addAll(failsCountBox, deltaTimeBox);
    }

    public int getFailsCount() {
        return Integer.parseInt(failsCountField.getText());
    }

    public int getDeltaTime() {
        return Integer.parseInt(deltaTimeField.getText());
    }

    public void setDefault() {
        failsCountField.setText("3");
        deltaTimeField.setText("50");
    }
}
