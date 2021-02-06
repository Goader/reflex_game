package pl.edu.agh.cs.app.ui.game;

import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import pl.edu.agh.cs.app.backend.GameEngine;
import pl.edu.agh.cs.app.backend.status.IGameStatus;
import pl.edu.agh.cs.app.ui.game.panes.MainPane;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;

public class MainStage extends Stage {
    private final int GAME_PIXELS_HEIGHT = 1080;
    private final int GAME_PIXELS_WIDTH = 1920;

    private final MainPane root;
    private final Scene scene;

    public MainStage(GameEngine engine, GameConfiguration config) {
        super();
        this.setTitle("Reflex Game");
        this.setWidth(GAME_PIXELS_WIDTH);
        this.setHeight(GAME_PIXELS_HEIGHT);

        IGameStatus status = engine.getStatus();

        root = new MainPane(GAME_PIXELS_HEIGHT, GAME_PIXELS_WIDTH, engine, config);
        scene = new Scene(root, GAME_PIXELS_WIDTH, GAME_PIXELS_HEIGHT);

        this.setFullScreen(true);
        this.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        status.getFullScreenProperty().addListener((observable, oldValue, newValue) -> this.setFullScreen(newValue));

        this.setScene(scene);
    }
}
