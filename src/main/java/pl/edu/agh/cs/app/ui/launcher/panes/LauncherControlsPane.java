package pl.edu.agh.cs.app.ui.launcher.panes;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class LauncherControlsPane extends HBox {
    private final Button defaultBtn;
    private final Button startBtn;
    private final Button exitBtn;

    private final Region blankSpace;

    private final int pxwidth;
    private final int padding;

    public LauncherControlsPane(int width) {
        super(20);
        this.setMinWidth(width);
        this.setMaxWidth(width);

        padding = 20;
        this.setPadding(new Insets(padding));

        pxwidth = width;

        defaultBtn = new Button("Default configuration");
        startBtn = new Button("Start");
        exitBtn = new Button("Exit");

        defaultBtn.setOnAction(e -> defaultOnClick());
        startBtn.setOnAction(e -> startOnClick());
        exitBtn.setOnAction(e -> exitOnClick());

        blankSpace = new Region();

        this.getChildren().addAll(defaultBtn, blankSpace, startBtn, exitBtn);

        bindBlankSpaceWidth();

//        Label label = new Label();
//        label.textProperty().bind(blankSpace.prefWidthProperty().asString());
//        this.getChildren().add(label);
    }

    protected void bindBlankSpaceWidth() {
        double totalSpacing = 20 * Math.max(this.getChildren().size() - 1, 0);
        System.out.println(totalSpacing);
        System.out.println(pxwidth - 2 * padding - totalSpacing);
        blankSpace.prefWidthProperty().bind(
            Bindings.subtract(
                pxwidth - 2 * padding - totalSpacing,
                startBtn.widthProperty().add(exitBtn.widthProperty().add(defaultBtn.widthProperty()))
            )
        );
    }

    protected void defaultOnClick() {

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
