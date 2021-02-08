package pl.edu.agh.cs.app.backend.status;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;
import pl.edu.agh.cs.app.backend.generators.ComputerChoiceGenerator;
import pl.edu.agh.cs.app.backend.status.states.PressedStatus;

public class MultiGameStatus extends AbstractGameStatus {
    private final ComputerChoiceGenerator computer;

    private PressedStatus pressedComputer;
    private int timeComputerPressed;
    private final int roundsToPlay;

    private final IntegerProperty playedRounds;
    private final IntegerProperty computerPoints;

    public MultiGameStatus(GameConfiguration config, ComputerChoiceGenerator computer) {
        super(config);
        this.computer = computer;
        computerPoints = new SimpleIntegerProperty();
        playedRounds = new SimpleIntegerProperty();
        computerPoints.set(0);
        playedRounds.set(0);
        roundsToPlay = 10;  // may be included to the launcher and config
    }

    @Override
    public void startRound() {
        super.startRound();
        computer.generate();
        timeComputerPressed = computer.getChoiceTime();
        pressedComputer = computer.getPressedResult();
    }

    @Override
    public void endRound() {
        super.endRound();
        final PressedStatus finalPressed = pressed;
        final PressedStatus finalComputerPressed = pressedComputer;
        Platform.runLater( () -> {
            if (finalPressed.isSuccess()) {
                playerPoints.set(playerPoints.get() + 1);
            }
            else if (finalPressed.isFailure()) playerPoints.set(Math.max(playerPoints.get() - 1, 0));
                // the rest means the computer move was first
            else if (finalComputerPressed.isSuccess()) computerPoints.set(computerPoints.get() + 1);
            else if (finalComputerPressed.isFailure()) computerPoints.set(Math.max(computerPoints.get() - 1, 0));
            // else nobody has pressed anything in the given time, and the points of both players remain as they are
        });

        // the property is increased in the JavaFX Thread, and may be increased too late or too early to determine
        // whether to end the game or not, so we do it sequentially here without risking of getting the wrong sequence
        final int instantPlayedRounds = playedRounds.get() + 1;

        Platform.runLater(() -> playedRounds.set(instantPlayedRounds));

        if (instantPlayedRounds >= roundsToPlay) endGame();
    }

    public int getRoundsToPlay() {
        return roundsToPlay;
    }

    public int getPlayedRounds() {
        return playedRounds.get();
    }

    public IntegerProperty playedRoundsProperty() {
        return playedRounds;
    }

    public int getComputerPoints() {
        return computerPoints.get();
    }

    public IntegerProperty getComputerPointsProperty() {
        return computerPoints;
    }

    public PressedStatus getPressedComputer() {
        return pressedComputer;
    }

    public int getTimeComputerPressed() {
        return timeComputerPressed;
    }
}
