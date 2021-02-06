package pl.edu.agh.cs.app.backend.status;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

abstract public class AbstractGameStatus implements IGameStatus {
    protected final BooleanProperty fullScreenProperty;

    public AbstractGameStatus() {
        fullScreenProperty = new SimpleBooleanProperty(true);
    }

    @Override
    public BooleanProperty getFullScreenProperty() {
        return fullScreenProperty;
    }

    @Override
    public void toggleFullScreen() {
        fullScreenProperty.set(!fullScreenProperty.get());
    }

}
