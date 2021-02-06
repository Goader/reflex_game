package pl.edu.agh.cs.app.ui.game.panes;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import pl.edu.agh.cs.app.backend.GameEngine;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;
import pl.edu.agh.cs.app.backend.status.IGameStatus;

public class MainPane extends BorderPane {
    private final StatusPane statusPane;
    private final GamePane gamePane;
    private final ControlsPane controls;

    private final GameEngine engine;
    private final IGameStatus status;
    private final GameConfiguration config;

    private final int padding = 20;

    public MainPane(int height, int width, GameEngine engine, GameConfiguration config) {
        super();
        this.setPadding(new Insets(20));
        this.setMinWidth(width);
        this.setMinHeight(height);
        height -= 2 * padding;
        width -= 2 * padding;

        this.engine = engine;
        this.status = engine.getStatus();
        this.config = config;

        double gameHeight = 0.9 * height;
        double gameWidth = 0.95 * width;

        config.setGameHeight((int) gameHeight);
        config.setGameWidth((int) gameWidth);

        statusPane = new StatusPane(status, config);
        gamePane = new GamePane(engine, config);
        controls = new ControlsPane(engine);

        statusPane.setMinSize(width, height - gameHeight);
        statusPane.setMaxSize(width, height - gameHeight);

        controls.setMinSize(width - gameWidth, gameHeight);
        controls.setMaxSize(width - gameWidth, gameHeight);

        this.setTop(statusPane);
        this.setCenter(gamePane);
        this.setRight(controls);
    }
}
