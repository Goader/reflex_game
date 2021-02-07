package pl.edu.agh.cs.app.backend.status;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;
import pl.edu.agh.cs.app.backend.status.states.PressedStatus;

abstract public class AbstractGameStatus implements IGameStatus {
    protected final GameConfiguration config;

    protected final BooleanProperty fullScreenProperty;

    protected boolean roundFinished;
    protected boolean gameFinished;
    protected boolean handledChoice;

    protected PressedStatus pressed;
    protected int roundTime;
    protected int timePressed;

    public AbstractGameStatus(GameConfiguration config) {
        this.config = config;
        fullScreenProperty = new SimpleBooleanProperty(true);
        roundFinished = true;
        gameFinished = false;
        pressed = PressedStatus.NOTPRESSED;
        roundTime = config.getChoiceTime();
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
    public boolean isChoiceHandled() {
        return handledChoice;
    }

    @Override
    public void handleChoice() {
        handledChoice = true;
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
    public int getRoundTime() {
        return roundTime;
    }

    @Override
    public void setPressedTime(int pressedTime) {
        timePressed = pressedTime;
    }

    @Override
    public void startRound() {
        roundFinished = false;
        handledChoice = false;
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
    public boolean isGameFinished() {
        return gameFinished;
    }

    protected void endGame() {
        gameFinished = true;
    }


}
