package pl.edu.agh.cs.app.backend.icons;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import pl.edu.agh.cs.app.backend.geometry.Vector2d;

public class Icon extends Circle {
    private final Image img;

    public Icon(Image img) {
        super();
        this.setFill(new ImagePattern(img));
        this.img = img;
    }

    public Icon copy() {
        Icon copied = new Icon(img);
        copied.setRadius(this.getRadius());
        return copied;
    }

    public void setCenter(Vector2d center) {
        this.setCenterX(center.getX());
        this.setCenterY(center.getY());
    }

    public void relocate(Vector2d vector) {
        this.relocate(vector.getX(), vector.getY());
    }
}
