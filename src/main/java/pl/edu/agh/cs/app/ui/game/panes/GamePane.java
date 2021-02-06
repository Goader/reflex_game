package pl.edu.agh.cs.app.ui.game.panes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import pl.edu.agh.cs.app.backend.generators.PointsGenerator;
import pl.edu.agh.cs.app.backend.geometry.Vector2d;

import java.util.List;

// still not sure about inheriting from group or from pane
public class GamePane extends Pane {
    public GamePane(double height, double width) {
        super();
        this.setHeight(height);
        this.setWidth(width);

        // TODO may be changed later simply using CSS file
        this.setStyle("-fx-background-color: white;");

        // TODO this should not be here, should be distributed between LayoutGenerator and GameEngine
        int count = 28;
        int radius = 128 - Math.max(64 * (count - 12) / (32 - 12), 0);
        PointsGenerator pointsGenerator = new PointsGenerator((int) height, (int) width, radius);
        List<Vector2d> points = pointsGenerator.generate(count);

        for (Vector2d point : points) {
            Circle circle = new Circle(point.getX(), point.getY(), radius, Color.DARKORANGE);
            this.getChildren().add(circle);
        }

    }
}
