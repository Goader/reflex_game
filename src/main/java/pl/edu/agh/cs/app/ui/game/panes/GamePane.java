package pl.edu.agh.cs.app.ui.game.panes;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import pl.edu.agh.cs.app.backend.GameEngine;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;

import java.util.Collection;

// still not sure about inheriting from group or from pane
public class GamePane extends Pane {
    private final GameEngine engine;
    private final GameConfiguration config;

    public GamePane(GameEngine engine, GameConfiguration config) {
        super();
        this.setHeight(config.getGameHeight());
        this.setWidth(config.getGameWidth());

        this.engine = engine;
        this.config = config;

        engine.setGamePane(this);

        // TODO may be changed later simply using CSS file
        this.setStyle("-fx-background-color: white;");  // the color may changed to the other one or even image

        // TODO this should not be here, should be distributed between LayoutGenerator and GameEngine
//        LayoutGenerator layout = new LayoutGenerator(config);
//        this.addAll(layout.generate());
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
