package pl.edu.agh.cs.app.backend.status;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import pl.edu.agh.cs.app.backend.status.states.PressedStatus;

abstract public class AbstractGameStatus implements IGameStatus {
    protected final BooleanProperty fullScreenProperty;

    protected boolean roundFinished;
    protected boolean gameFinished;

    protected PressedStatus pressed;

    public AbstractGameStatus() {
        fullScreenProperty = new SimpleBooleanProperty(true);
        roundFinished = true;
        gameFinished = false;
        pressed = PressedStatus.NOTPRESSED;
    }

    @Override
    public BooleanProperty getFullScreenProperty() {
        return fullScreenProperty;
    }

    @Override
    public void toggleFullScreen() {
        fullScreenProperty.set(!fullScreenProperty.get());
    }

    @Override
    public void pressed(PressedStatus pressed) {
        this.pressed = pressed;
    }

    @Override
    public boolean isSuccess() {
        return pressed.equals(PressedStatus.SUCCESS);
    }

    @Override
    public boolean isFailure() {
        return pressed.equals(PressedStatus.FAILURE);
    }

    @Override
    public void startRound() {
        roundFinished = false;
        pressed = PressedStatus.NOTPRESSED;
    }

    @Override
    public void endRound() {
        roundFinished = true;
    }

    @Override
    public boolean isRoundFinished() {
        return roundFinished;
    }

    @Override
    public void endGame() {
        gameFinished = true;
    }

    @Override
    public boolean isGameFinished() {
        return gameFinished;
    }


}
