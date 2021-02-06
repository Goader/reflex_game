package pl.edu.agh.cs.app.ui.game.panes;

import javafx.scene.Group;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

// still not sure about inheriting from group or from pane
public class GamePane extends Pane {
    public GamePane(double height, double width) {
        super();
        this.setHeight(height);
        this.setWidth(width);

        // may be changed later simply using CSS
        ColorInput ci = new ColorInput(this.getLayoutX(),
                this.getLayoutY(),
                this.getLayoutBounds().getWidth(),
                this.getLayoutBounds().getHeight(),
                Color.WHITE);
        this.setEffect(ci);
    }
}
