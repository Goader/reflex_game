package pl.edu.agh.cs.app.backend.status;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import pl.edu.agh.cs.app.backend.status.states.PressedStatus;
import pl.edu.agh.cs.app.ui.game.panes.status.GameTimer;

public interface IGameStatus {
    // yep, I know, the methods are in a totally random order :D

    BooleanProperty getFullScreenProperty();

    void toggleFullScreen();

    boolean isPaused();

    void togglePause();

    int getPlayerPoints();

    IntegerProperty getPlayerPointsProperty();

    boolean isChoiceHandled();

    void handleChoice();

    void pressed(PressedStatus pressed);

    boolean isSuccess();

    boolean isFailure();

    IntegerProperty averageReactionProperty();

    int getRoundTime();

    void measureTime();

    void startRound();

    void endRound();

    boolean isRoundFinished();

    boolean isGameFinished();

    void runGameTimer();

    void stopGameTimer();

    void disableGameTimer();

    void setGameTimer(GameTimer timer);

    void writeStatistics();
}
