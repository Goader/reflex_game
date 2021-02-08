package pl.edu.agh.cs.app.ui.launcher.panes.settings;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MultiGameSettingsPane extends VBox {
    private final SettingsBox computerTimeBox;
    private final SettingsBox computerWinChanceBox;

    private final TextField computerTimeField;
    private final TextField computerWinChanceField;

    public MultiGameSettingsPane(int spacing, int boxSpacing) {
        super(spacing);

        computerTimeField = new TextField();
        computerWinChanceField = new TextField();

        computerTimeBox = new SettingsBox(boxSpacing);
        computerWinChanceBox = new SettingsBox(boxSpacing);

        String baseStyle = """
                -fx-font-size: 16px;
                -fx-font-weight: bold;""";

        Label computerTimeValid = new Label();
        Label computerWinChanceValid = new Label();

        computerTimeField.textProperty().addListener(((observable) -> {
            if (computerTimeValidation()) {
                computerTimeValid.setStyle(baseStyle + "\n -fx-text-fill: #00aa00");
                computerTimeValid.setText("OK");
            }
            else {
                computerTimeValid.setStyle(baseStyle + "\n -fx-text-fill: #aa0000");
                computerTimeValid.setText("Wrong");
            }
        }));

        computerWinChanceField.textProperty().addListener((observable -> {
            if (computerWinChanceValidation()) {
                computerWinChanceValid.setStyle(baseStyle + "\n -fx-text-fill: #00aa00");
                computerWinChanceValid.setText("OK");
            }
            else {
                computerWinChanceValid.setStyle(baseStyle + "\n -fx-text-fill: #aa0000");
                computerWinChanceValid.setText("Wrong");
            }
        }));

        Region computerTimeBlankSpace = new Region();
        Region computerWinChanceBlankSpace = new Region();

        HBox.setHgrow(computerTimeBlankSpace, Priority.ALWAYS);
        HBox.setHgrow(computerWinChanceBlankSpace, Priority.ALWAYS);

        HBox computerTimeValidField = new HBox(10, computerTimeField, computerTimeBlankSpace, computerTimeValid);
        HBox computerWinChanceValidField = new HBox(10, computerWinChanceField, computerWinChanceBlankSpace, computerWinChanceValid);

        computerTimeBox.getChildren().add(
                new Label("Average choice time in ms for the computer"));
        computerTimeBox.getChildren().add(computerTimeValidField);

        computerWinChanceBox.getChildren().add(
                new Label("Chance for the computer to make the right choice"));
        computerWinChanceBox.getChildren().add(computerWinChanceValidField);

        setValidation();

        this.getChildren().addAll(computerTimeBox, computerWinChanceBox);
    }

    public int getComputerChoiceTime() {
        return Integer.parseInt(computerTimeField.getText());
    }

    public double getComputerWinChance() {
        return Double.parseDouble(computerWinChanceField.getText());
    }

    public void setDefault() {
        computerTimeField.setText("800");
        computerWinChanceField.setText("0.85");
    }

    private boolean computerTimeValidation() {
        String typed = computerTimeField.getText();
        boolean valid;
        try {
            int value = Integer.parseInt(typed);
            valid = 200 <= value && value < 3600000;
        } catch (NumberFormatException ex) {
            valid = false;
        }
        return valid;
    }

    private boolean computerWinChanceValidation() {
        String typed = computerWinChanceField.getText();
        boolean valid;
        try {
            double value = Double.parseDouble(typed);
            valid = 0 <= value && value <= 1;
        } catch (NumberFormatException ex) {
            valid = false;
        }
        return valid;
    }

    private void setValidation() {
        computerTimeField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                boolean valid = computerTimeValidation();

                if (!valid) computerTimeField.setText("");
            }
        });

        computerWinChanceField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                boolean valid = computerWinChanceValidation();

                if (!valid) computerWinChanceField.setText("");
            }
        });
    }
}
