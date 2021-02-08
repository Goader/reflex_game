package pl.edu.agh.cs.app.backend.status;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;
import pl.edu.agh.cs.app.backend.status.states.PressedStatus;
import pl.edu.agh.cs.app.ui.game.panes.status.GameTimer;

abstract public class AbstractGameStatus implements IGameStatus {
    protected final GameConfiguration config;
    protected GameTimer timer;

    protected final BooleanProperty fullScreenProperty;

    protected boolean roundFinished;
    protected boolean gameFinished;
    protected boolean handledChoice;

    protected PressedStatus pressed;
    protected int roundTime;
    protected int timePressed;
    protected long startTime;

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
        // the difference cannot exceed the time set in the config, and it has 'int' constraint
        timePressed = (int) (System.currentTimeMillis() - startTime);
        this.pressed = pressed;
        System.out.println(timePressed);  // TODO this needs to be passed to the statistics, note that pressed != NOTPRESSED
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
    public void measureTime() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void startRound() {
        roundFinished = false;
        handledChoice = false;
        timer.setStartTime();
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

    @Override
    public void runGameTimer() {
        timer.run();
    }

    @Override
    public void setGameTimer(GameTimer timer) {
        this.timer = timer;
    }
}
