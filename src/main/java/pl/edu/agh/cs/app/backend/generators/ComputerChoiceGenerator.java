package pl.edu.agh.cs.app.backend.generators;

import pl.edu.agh.cs.app.backend.data.GameConfiguration;
import pl.edu.agh.cs.app.backend.status.states.PressedStatus;

import java.util.Random;

public class ComputerChoiceGenerator {
    private final GameConfiguration config;
    private final Random random;

    private int choiceTime;
    private PressedStatus pressedResult;

    public ComputerChoiceGenerator(GameConfiguration config) {
        if (!config.isMultiMode()) {
            throw new IllegalArgumentException("Config states the game's mode is SINGLE, not MULTI, which is required");
        }
        this.config = config;
        random = new Random();
    }

    // I really wanted to use exGaussian distribution, but it requires another library and lots of parameters
    // so we gonna make it using just normal distribution and taking the mean out of config
    // btw, exGaussian distribution is often used to describe response time, so it's an ideal match for our problem
    // TODO simulate exGaussian adding the random from exponential distribution to the result
    public void generate() {
        double mean = config.getComputerChoiceTime();
        double std = mean / 4;  // it means that with 4 * std we get more than 0 with a chance of 0.99997
        choiceTime =  (int) Math.max(random.nextGaussian() * std + mean, 50);

        if (choiceTime >= config.getChoiceTime()) pressedResult = PressedStatus.NOTPRESSED;
        else {
            if (random.nextDouble() <= config.getComputerWinChance()) pressedResult = PressedStatus.SUCCESS;
            else pressedResult = PressedStatus.FAILURE;
        }
    }

    public int getChoiceTime() {
        return choiceTime;
    }

    public PressedStatus getPressedResult() {
        return pressedResult;
    }
}
