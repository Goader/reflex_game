package pl.edu.agh.cs.app.ui.game.panes;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import pl.edu.agh.cs.app.backend.generators.LayoutGenerator;
import pl.edu.agh.cs.app.backend.generators.PointsGenerator;
import pl.edu.agh.cs.app.backend.geometry.Vector2d;
import pl.edu.agh.cs.app.backend.utils.GameConfiguration;
import pl.edu.agh.cs.app.backend.utils.IconsLoader;

import java.util.List;

// still not sure about inheriting from group or from pane
public class GamePane extends Pane {
    public GamePane(GameConfiguration config) {
        super();
        this.setHeight(config.getGameHeight());
        this.setWidth(config.getGameWidth());

        // TODO may be changed later simply using CSS file
        this.setStyle("-fx-background-color: white;");  // the color may changed to the other one or even image

        // TODO this should not be here, should be distributed between LayoutGenerator and GameEngine
        LayoutGenerator layout = new LayoutGenerator(config);
        this.getChildren().addAll(layout.generate());
    }
}
