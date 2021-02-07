package pl.edu.agh.cs.app.backend.status;

import javafx.beans.property.BooleanProperty;

public interface IGameStatus {
    BooleanProperty getFullScreenProperty();

    void toggleFullScreen();

    void startRound();

    void endRound();

    boolean isRoundFinished();

    void endGame();

    boolean isGameFinished();
}
