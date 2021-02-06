package pl.edu.agh.cs.app.backend.status;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

abstract public class AbstractGameStatus implements IGameStatus {
    protected final BooleanProperty fullScreenProperty;

    protected boolean roundFinished;
    protected boolean gameFinished;

    public AbstractGameStatus() {
        fullScreenProperty = new SimpleBooleanProperty(true);
        roundFinished = true;
        gameFinished = false;
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
    public boolean isRoundFinished() {
        return roundFinished;
    }

    @Override
    public boolean isGameFinished() {
        return gameFinished;
    }


}
