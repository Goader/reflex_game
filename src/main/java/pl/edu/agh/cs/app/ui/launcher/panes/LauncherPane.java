package pl.edu.agh.cs.app.ui.launcher.panes;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class LauncherPane extends VBox {
    private final HBox modeSelector;  // temporary decision, may be implemented later with ToggleButton and CSS sheet
    private final SettingsPane settings;
    private final LauncherControlsPane controls;
    private final Region blankSpace;

    private final int pxheight;
    private final int pxwidth;

    public LauncherPane(int height, int width) {
        pxheight = height;
        pxwidth = width;

        modeSelector = new HBox(10);
        modeSelector.setMinWidth(pxwidth);
        modeSelector.setMaxWidth(pxwidth);

        settings = new SettingsPane();
        controls = new LauncherControlsPane(pxwidth);

        blankSpace = new Region();
        blankSpace.setMinWidth(pxwidth);
        blankSpace.setMaxWidth(pxwidth);

        temporaryCreation();

        bindBlankSpaceHeight();

        this.getChildren().addAll(modeSelector, settings, blankSpace, controls);
    }

    // change!!!
    public void temporaryCreation() {
        Button single = new Button("Single");
        single.setOnAction(event -> settings.setSingleGameSettings());
        Button multi = new Button("Multi");
        multi.setOnAction(event -> settings.setMultiGameSettings());
        modeSelector.setAlignment(Pos.CENTER);
        int padding = 20;
        modeSelector.setPadding(new Insets(padding));
        modeSelector.getChildren().addAll(single, multi);
    }

    protected void bindBlankSpaceHeight() {
        blankSpace.prefHeightProperty().bind(
            Bindings.subtract(
                pxheight,
                modeSelector.heightProperty().add(settings.heightProperty().add(controls.heightProperty()))
            )
        );
    }

}
