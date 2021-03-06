package pl.edu.agh.cs.app.ui.game.panes;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import pl.edu.agh.cs.app.backend.GameEngine;
import pl.edu.agh.cs.app.backend.status.IGameStatus;
import pl.edu.agh.cs.app.backend.status.MultiGameStatus;
import pl.edu.agh.cs.app.backend.status.SingleGameStatus;
import pl.edu.agh.cs.app.ui.game.panes.status.GeneralStatusPane;
import pl.edu.agh.cs.app.ui.game.panes.status.MultiGameStatusPane;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;
import pl.edu.agh.cs.app.ui.game.panes.status.SingleGameStatusPane;
import pl.edu.agh.cs.app.ui.game.panes.status.TimeCountdownPane;

public class StatusPane extends HBox {
    private final GeneralStatusPane general;
    private final SingleGameStatusPane single;
    private final MultiGameStatusPane multi;
    private final TimeCountdownPane timerPane;

    private final int spacing = 100;
    private final int boxSpacing = 10;

    public StatusPane(GameEngine engine, GameConfiguration config) {
        super();
        this.setSpacing(spacing);
        IGameStatus status = engine.getStatus();

        general = new GeneralStatusPane(spacing, boxSpacing, status);
        this.getChildren().add(general);
        if (config.isMultiMode()) {
            if (!(status instanceof MultiGameStatus)) {
                throw new IllegalStateException("The status object game mode does not match the config game mode");
            }
            multi = new MultiGameStatusPane(spacing, boxSpacing, engine.getMultiGameStatus());
            this.getChildren().add(multi);
            single = null;
        }
        else {
            if (!(status instanceof SingleGameStatus)) {
                throw  new IllegalStateException("The status object game mode does not match the config game mode");
            }
            single = new SingleGameStatusPane(spacing, boxSpacing, engine.getSingleGameStatus());
            this.getChildren().add(single);
            multi = null;
        }

        timerPane = new TimeCountdownPane(status);
        Region blankSpace = new Region();
        HBox.setHgrow(blankSpace, Priority.ALWAYS);

        this.getChildren().addAll(blankSpace, timerPane);
    }
}
