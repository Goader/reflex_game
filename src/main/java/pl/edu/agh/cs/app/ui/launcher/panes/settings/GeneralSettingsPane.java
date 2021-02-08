package pl.edu.agh.cs.app.ui.launcher.panes.settings;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


public class GeneralSettingsPane extends VBox {
    private final SettingsBox elementsCountBox;
    private final SettingsBox choiceTimeBox;

    private final TextField elementsCountField;
    private final TextField choiceTimeField;

    public GeneralSettingsPane(int spacing, int boxSpacing) {
        super(spacing);

        elementsCountField = new TextField();
        choiceTimeField = new TextField();

        elementsCountBox = new SettingsBox(boxSpacing);
        choiceTimeBox = new SettingsBox(boxSpacing);

        String baseStyle = """
                -fx-font-size: 16px;
                -fx-font-weight: bold;""";

        Label elementsCountValid = new Label();
        Label choiceTimeValid = new Label();

        elementsCountField.textProperty().addListener(((observable) -> {
            if (elementsCountValidation()) {
                elementsCountValid.setStyle(baseStyle + "\n -fx-text-fill: #00aa00");
                elementsCountValid.setText("OK");
            }
            else {
                elementsCountValid.setStyle(baseStyle + "\n -fx-text-fill: #aa0000");
                elementsCountValid.setText("Wrong");
            }
        }));

        choiceTimeField.textProperty().addListener((observable -> {
            if (choiceTimeValidation()) {
                choiceTimeValid.setStyle(baseStyle + "\n -fx-text-fill: #00aa00");
                choiceTimeValid.setText("OK");
            }
            else {
                choiceTimeValid.setStyle(baseStyle + "\n -fx-text-fill: #aa0000");
                choiceTimeValid.setText("Wrong");
            }
        }));

        Region elementsCountBlankSpace = new Region();
        Region choiceTimeBlankSpace = new Region();

        HBox.setHgrow(elementsCountBlankSpace, Priority.ALWAYS);
        HBox.setHgrow(choiceTimeBlankSpace, Priority.ALWAYS);

        HBox elementsCountValidField = new HBox(10, elementsCountField, elementsCountBlankSpace, elementsCountValid);
        HBox choiceTimeValidField = new HBox(10, choiceTimeField, choiceTimeBlankSpace, choiceTimeValid);

        elementsCountBox.getChildren().add(new Label("Count of elements to show each round"));
        elementsCountBox.getChildren().add(elementsCountValidField);

        choiceTimeBox.getChildren().add(new Label("Time in ms (1s = 1000ms) to make a choice"));
        choiceTimeBox.getChildren().add(choiceTimeValidField);

        setValidation();

        this.getChildren().addAll(elementsCountBox, choiceTimeBox);
    }

    public int getElementsCount() {
        return Integer.parseInt(elementsCountField.getText());
    }

    public int getChoiceTime() {
        return Integer.parseInt(choiceTimeField.getText());
    }

    public void setDefault() {
        elementsCountField.setText("36");
        choiceTimeField.setText("2500");
    }

    private boolean elementsCountValidation() {
        String typed = elementsCountField.getText();
        boolean valid;
        try {
            int value = Integer.parseInt(typed);
            valid = 2 <= value && value <= 36;
        } catch (NumberFormatException ex) {
            valid = false;
        }
        return valid;
    }

    private boolean choiceTimeValidation() {
        String typed = choiceTimeField.getText();
        boolean valid;
        try {
            int value = Integer.parseInt(typed);
            valid = 200 <= value && value <= 3600000;
        } catch (NumberFormatException ex) {
            valid = false;
        }
        return valid;
    }

    private void setValidation() {
        elementsCountField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                boolean valid = elementsCountValidation();

                if (!valid) elementsCountField.setText("");
            }
        });

        choiceTimeField.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                boolean valid = choiceTimeValidation();

                if (!valid) choiceTimeField.setText("");
            }
        });
    }
}
