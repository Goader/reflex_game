package pl.edu.agh.cs.app.ui.game.panes.status;

import javafx.beans.binding.StringExpression;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class StatusBox extends VBox {
    // TODO checkout if works
    public StatusBox(int boxSpacing, String labelText, StringExpression binding) {
        super(boxSpacing);
        Label label = new Label(labelText);
        label.setStyle("""
                -fx-font-size: 24px;
                -fx-font-weight: bold;
                -fx-text-fill: #ffffff;
                -fx-effect: dropshadow(gaussian, rgba(255,255,255,0.5), 1,1,1,1);""");
        this.getChildren().add(label);

        Label value = new Label();
        value.setStyle("""
                -fx-font-size: 40px;
                -fx-font-weight: bold;
                -fx-text-fill: #ffffff;
                -fx-effect: dropshadow(gaussian, rgba(255,255,255,0.5), 1,1,1,1);""");
        value.textProperty().bind(binding);
        this.getChildren().add(value);
    }
}
