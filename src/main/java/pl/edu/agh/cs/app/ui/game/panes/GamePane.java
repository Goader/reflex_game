package pl.edu.agh.cs.app.ui.game.panes;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import pl.edu.agh.cs.app.backend.GameEngine;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;

import java.util.Collection;

public class GamePane extends Pane {

    public GamePane(GameEngine engine, GameConfiguration config) {
        super();
        this.setHeight(config.getGameHeight());
        this.setWidth(config.getGameWidth());

        engine.setGamePane(this);

        this.setStyle("-fx-background-color: white;");  // the color may be changed to the other one or even image
    }

    public void clear() {
        this.getChildren().clear();
    }

    public void add(Node... node) {
        this.getChildren().addAll(node);
    }

    public void addAll(Collection<? extends Node> nodes) {
        this.getChildren().addAll(nodes);
    }
}
