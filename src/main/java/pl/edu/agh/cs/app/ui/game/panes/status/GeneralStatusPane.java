package pl.edu.agh.cs.app.ui.game.panes.status;

import javafx.scene.layout.HBox;
import pl.edu.agh.cs.app.backend.status.IGameStatus;

public class GeneralStatusPane extends HBox {

    public GeneralStatusPane(int spacing, int boxSpacing, IGameStatus status) {
        super(spacing);
    }
}
