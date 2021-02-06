package pl.edu.agh.cs.app.ui.launcher.panes.settings;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class GeneralSettingsPane extends VBox {
    private final SettingsBox elementsCountBox;
    private final SettingsBox choiceTimeBox;

    private final TextField elementsCountField;
    private final TextField choiceTimeField;

    public GeneralSettingsPane(int spacing, int boxSpacing) {
        super(spacing);

        // TODO default values are temporary
        elementsCountField = new TextField("10");
        choiceTimeField = new TextField("5000");

        elementsCountBox = new SettingsBox(boxSpacing);
        choiceTimeBox = new SettingsBox(boxSpacing);

        elementsCountBox.getChildren().add(new Label("Type in the count of elements to show each round"));
        elementsCountBox.getChildren().add(elementsCountField);

        choiceTimeBox.getChildren().add(new Label("Type in the time in ms (1s = 1000ms) to make a choice"));
        choiceTimeBox.getChildren().add(choiceTimeField);

        this.getChildren().addAll(elementsCountBox, choiceTimeBox);
    }

    public int getElementsCount() {
        return Integer.parseInt(elementsCountField.getText());
    }

    public int getChoiceTime() {
        return Integer.parseInt(choiceTimeField.getText());
    }
}
