package pl.edu.agh.cs.app.ui.launcher;

import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.edu.agh.cs.app.ui.game.utils.GameConfiguration;
import pl.edu.agh.cs.app.ui.launcher.panes.LauncherPane;

import java.util.concurrent.Callable;
import java.util.function.Function;

public class LauncherStage extends Stage {
    private final int LAUNCHER_PIXELS_HEIGHT = 800;
    private final int LAUNCHER_PIXELS_WIDTH = 450;

    private final LauncherPane root;
    private final Scene scene;

    public LauncherStage(Function<GameConfiguration, Void> playTrigger) {
        super();
        this.setHeight(LAUNCHER_PIXELS_HEIGHT);
        this.setWidth(LAUNCHER_PIXELS_WIDTH);
        this.setTitle("Reflex Game Launcher");

        root = new LauncherPane(LAUNCHER_PIXELS_HEIGHT, LAUNCHER_PIXELS_WIDTH, playTrigger);
        scene = new Scene(root, LAUNCHER_PIXELS_WIDTH, LAUNCHER_PIXELS_HEIGHT);

        this.setScene(scene);
    }
}
