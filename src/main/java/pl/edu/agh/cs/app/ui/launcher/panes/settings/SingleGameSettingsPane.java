package pl.edu.agh.cs.app.ui.launcher.panes.settings;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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

        String baseStyle = """
                -fx-font-size: 16px;
                -fx-font-weight: bold;""";

        Label failsCountValid = new Label();
        Label deltaTimeValid = new Label();

        failsCountField.textProperty().addListener(((observable) -> {
            if (failsCountValidation()) {
                failsCountValid.setStyle(baseStyle + "\n -fx-text-fill: #00aa00");
                failsCountValid.setText("OK");
            }
            else {
                failsCountValid.setStyle(baseStyle + "\n -fx-text-fill: #aa0000");
                failsCountValid.setText("Wrong");
            }
        }));

        deltaTimeField.textProperty().addListener((observable -> {
            if (deltaTimeValidation()) {
                deltaTimeValid.setStyle(baseStyle + "\n -fx-text-fill: #00aa00");
                deltaTimeValid.setText("OK");
            }
            else {
                deltaTimeValid.setStyle(baseStyle + "\n -fx-text-fill: #aa0000");
                deltaTimeValid.setText("Wrong");
            }
        }));

        Region failsCountBlankSpace = new Region();
        Region deltaTimeBlankSpace = new Region();

        HBox.setHgrow(failsCountBlankSpace, Priority.ALWAYS);
        HBox.setHgrow(deltaTimeBlankSpace, Priority.ALWAYS);

        HBox failsCountValidField = new HBox(10, failsCountField, failsCountBlankSpace, failsCountValid);
        HBox deltaTimeValidField = new HBox(10, deltaTimeField, deltaTimeBlankSpace, deltaTimeValid);

        failsCountBox.getChildren().add(new Label("Maximum fails allowed"));
        failsCountBox.getChildren().add(failsCountValidField);

        deltaTimeBox.getChildren().add(new Label("Time in ms by which the general time will be reduced"));
        deltaTimeBox.getChildren().add(deltaTimeValidField);

        setValidation();

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

    private boolean failsCountValidation() {
        String typed = failsCountField.getText();
        boolean valid;
        try {
            int value = Integer.parseInt(typed);
            valid = 0 <= value && value <= 1000;
        } catch (NumberFormatException ex) {
            valid = false;
        }
        return valid;
    }

    private boolean deltaTimeValidation() {
        String typed = deltaTimeField.getText();
        boolean valid;
        try {
            int value = Integer.parseInt(typed);
            valid = 0 <= value && value <= 3600000;
        } catch (NumberFormatException ex) {
            valid = false;
        }
        return valid;
    }

    private void setValidation() {
        failsCountField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                boolean valid = failsCountValidation();

                if (!valid) failsCountField.setText("");
            }
        });

        deltaTimeField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                boolean valid = deltaTimeValidation();

                if (!valid) deltaTimeField.setText("");
            }
        });
    }
}
