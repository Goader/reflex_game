package pl.edu.agh.cs.app.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.cs.app.backend.GameEngine;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;
import pl.edu.agh.cs.app.ui.game.MainStage;
import pl.edu.agh.cs.app.ui.launcher.LauncherStage;

import java.util.function.Function;

public class App extends Application {
    private LauncherStage launcher;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Function<GameConfiguration, Void> playTrigger = this::play;

        launcher = new LauncherStage(playTrigger);
        launcher.show();
    }

    // using Void to make the creation of Function<Config, Void> = this::play possible
    public Void play(GameConfiguration config) {
        launcher.close();

        GameEngine engine = new GameEngine(config);

        Stage mainStage = new MainStage(engine, config);

        Thread thread = new Thread(engine);
        thread.setDaemon(true);
        thread.start();

        // TODO some code handling the game

        mainStage.show();
        return null;
    }
}
