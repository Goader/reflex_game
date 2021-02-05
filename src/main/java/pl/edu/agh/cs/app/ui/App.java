package pl.edu.agh.cs.app.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.cs.app.ui.launcher.LauncherStage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Stage launcherStage = new LauncherStage();
        launcherStage.show();
    }
}
