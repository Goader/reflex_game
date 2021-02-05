package pl.edu.agh.cs.app.ui.launcher.panes;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class LauncherControlsPane extends HBox {
    private final Button defaultBtn;
    private final Button startBtn;
    private final Button exitBtn;

    private final Region blankSpace;

    private final int padding;

    public LauncherControlsPane() {
        super(20);

        padding = 20;
        this.setPadding(new Insets(padding, padding, padding, padding));
        this.setAlignment(Pos.CENTER);

        defaultBtn = new Button("Default configuration");
        startBtn = new Button("Start");
        exitBtn = new Button("Exit");

        defaultBtn.setOnAction(e -> defaultOnClick());
        startBtn.setOnAction(e -> startOnClick());
        exitBtn.setOnAction(e -> exitOnClick());

        blankSpace = new Region();
        HBox.setHgrow(blankSpace, Priority.ALWAYS);

        this.getChildren().addAll(defaultBtn, blankSpace, startBtn, exitBtn);
    }


    protected void defaultOnClick() {
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("Feature is not yet written");
        msg.setHeaderText("Setting default settings will be available in the new version. Please type in manually :)");
        msg.show();
    }

    protected void startOnClick() {
        System.out.println(startBtn.widthProperty().get());
        System.out.println(exitBtn.widthProperty().get());
        System.out.println(defaultBtn.widthProperty().get());
    }

    protected void exitOnClick() {
        Platform.exit();
    }
}
