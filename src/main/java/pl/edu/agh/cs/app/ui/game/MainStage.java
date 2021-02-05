package pl.edu.agh.cs.app.ui.game;

import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.agh.cs.app.ui.game.panes.GamePane;
import pl.edu.agh.cs.app.ui.game.utils.GameConfiguration;
import pl.edu.agh.cs.app.ui.launcher.panes.LauncherPane;

public class MainStage extends Stage {
    private final int GAME_PIXELS_HEIGHT = 1008;
    private final int GAME_PIXELS_WIDTH = 1920;

    private final GamePane root;
    private final Scene scene;

    public MainStage(GameConfiguration config) {
        super();
        this.setTitle("Reflex Game");
        this.setWidth(GAME_PIXELS_WIDTH);
        this.setHeight(GAME_PIXELS_HEIGHT);

        root = new GamePane(GAME_PIXELS_HEIGHT, GAME_PIXELS_WIDTH, config);
        scene = new Scene(root, GAME_PIXELS_HEIGHT, GAME_PIXELS_WIDTH);

        this.setScene(scene);
    }
}
