package pl.edu.agh.cs.app.ui.launcher.panes;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import pl.edu.agh.cs.app.ui.game.utils.GameConfiguration;
import pl.edu.agh.cs.app.ui.launcher.panes.settings.GeneralSettingsPane;
import pl.edu.agh.cs.app.ui.launcher.panes.settings.MultiGameSettingsPane;
import pl.edu.agh.cs.app.ui.launcher.panes.settings.SingleGameSettingsPane;
import pl.edu.agh.cs.app.ui.launcher.utils.ModeSelectorState;

public class SettingsPane extends VBox {
    private ModeSelectorState state;

    private final GeneralSettingsPane generals;
    private final SingleGameSettingsPane singles;
    private final MultiGameSettingsPane multies;


    public SettingsPane(int spacing) {
        super(spacing);
        this.setPadding(new Insets(20));

        int insideBoxSpacing = 10;
        generals = new GeneralSettingsPane(spacing, insideBoxSpacing);
        singles = new SingleGameSettingsPane(spacing, insideBoxSpacing);
        multies = new MultiGameSettingsPane(spacing, insideBoxSpacing);

        setSingleGameSettings();
    }

    public void setSingleGameSettings() {
        state = ModeSelectorState.SINGLE;

        this.getChildren().clear();
        this.getChildren().addAll(generals, singles);
    }

    public void setMultiGameSettings() {
        state = ModeSelectorState.MULTI;

        this.getChildren().clear();
        this.getChildren().addAll(generals, multies);
    }

    public GameConfiguration exportConfig() {
        GameConfiguration config = new GameConfiguration();
        if (state.equals(ModeSelectorState.SINGLE)) {
            // export single game settings
        }
        else {
            // export multi game settings
        }
        return config;
    }
}
