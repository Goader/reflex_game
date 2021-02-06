package pl.edu.agh.cs.app.backend.icons;

import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import pl.edu.agh.cs.app.backend.geometry.Vector2d;

public class Icon extends Circle {
    public Icon(Image img) {
        super();
    }

    public void relocate(Vector2d vector) {
        super.relocate(vector.getX(), vector.getY());
    }
}
