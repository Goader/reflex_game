package pl.edu.agh.cs.app.ui.game.panes;

import javafx.scene.layout.HBox;
import pl.edu.agh.cs.app.backend.status.IGameStatus;
import pl.edu.agh.cs.app.backend.status.MultiGameStatus;
import pl.edu.agh.cs.app.ui.game.panes.status.GeneralStatusPane;
import pl.edu.agh.cs.app.ui.game.panes.status.MultiGameStatusPane;
import pl.edu.agh.cs.app.backend.utils.GameConfiguration;

public class StatusPane extends HBox {
    private final GeneralStatusPane general;
    private final MultiGameStatusPane multi;

    private final int spacing = 40;
    private final int boxSpacing = 10;

    public StatusPane(IGameStatus status, GameConfiguration config) {
        super();
        this.setSpacing(spacing);

        general = new GeneralStatusPane(spacing, boxSpacing, status);
        this.getChildren().add(general);
        if (config.isMultiMode()) {
            if (!(status instanceof MultiGameStatus)) {
                throw new IllegalStateException("The status object game mode does not match the config game mode");
            }
            multi = new MultiGameStatusPane(spacing, boxSpacing, (MultiGameStatus) status);
            this.getChildren().add(multi);
        }
        else {
            multi = null;
        }
    }
}
