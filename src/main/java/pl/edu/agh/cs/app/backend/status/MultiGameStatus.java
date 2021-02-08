package pl.edu.agh.cs.app.backend.status;

import pl.edu.agh.cs.app.backend.data.GameConfiguration;
import pl.edu.agh.cs.app.backend.generators.ComputerChoiceGenerator;
import pl.edu.agh.cs.app.backend.status.states.PressedStatus;

public class MultiGameStatus extends AbstractGameStatus {
    private final ComputerChoiceGenerator computer;

    private PressedStatus pressedComputer;
    private int timeComputerPressed;

    public MultiGameStatus(GameConfiguration config, ComputerChoiceGenerator computer) {
        super(config);
        this.computer = computer;
    }

    @Override
    public void startRound() {
        super.startRound();
        computer.generate();
        timeComputerPressed = computer.getChoiceTime();
        pressedComputer = computer.getPressedResult();
    }

    public PressedStatus getPressedComputer() {
        return pressedComputer;
    }

    public int getTimeComputerPressed() {
        return timeComputerPressed;
    }
}
