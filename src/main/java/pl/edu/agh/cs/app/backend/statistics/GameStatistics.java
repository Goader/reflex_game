package pl.edu.agh.cs.app.backend.statistics;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.LinkedList;

public class GameStatistics {
    private final LinkedList<Integer> reactionTimes;
    private final IntegerProperty averageReactionTime;

    public GameStatistics() {
        reactionTimes = new LinkedList<>();
        averageReactionTime = new SimpleIntegerProperty();
        averageReactionTime.set(0);
    }

    public void addNewReactionTime(int reactionTime) {
        int n = reactionTimes.size();  // n before adding, n + 1 after
        reactionTimes.add(reactionTime);
        final int newAverage = (n * averageReactionTime.get() + reactionTime) / (n + 1);
        Platform.runLater(() -> averageReactionTime.set(newAverage));
    }

    public IntegerProperty averageReactionTimeProperty() {
        return averageReactionTime;
    }

    public void writeStatistics() {
        // TODO implement
    }
}
