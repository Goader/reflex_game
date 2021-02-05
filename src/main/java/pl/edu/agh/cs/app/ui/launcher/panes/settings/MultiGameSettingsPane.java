package pl.edu.agh.cs.app.ui.launcher.panes.settings;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

        computerTimeBox.getChildren().add(
                new Label("Type in the average time in ms for the computer to make the choice"));
        computerTimeBox.getChildren().add(computerTimeField);

        computerWinChanceBox.getChildren().add(
                new Label("Type in the chance for the computer to make the right choice"));
        computerWinChanceBox.getChildren().add(computerWinChanceField);

        this.getChildren().addAll(computerTimeBox, computerWinChanceBox);
    }

    public int getComputerChoiceTime() {
        return Integer.parseInt(computerTimeField.getText());
    }

    public double getComputerWinChance() {
        return Double.parseDouble(computerWinChanceField.getText());
    }
}
