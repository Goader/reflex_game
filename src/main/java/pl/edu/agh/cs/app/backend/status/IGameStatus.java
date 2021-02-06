package pl.edu.agh.cs.app.backend.status;

import javafx.beans.property.BooleanProperty;

public interface IGameStatus {
    BooleanProperty getFullScreenProperty();

    void toggleFullScreen();

    boolean isRoundFinished();

    boolean isGameFinished();
}
