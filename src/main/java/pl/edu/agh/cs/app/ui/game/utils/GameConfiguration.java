package pl.edu.agh.cs.app.ui.game.utils;

import pl.edu.agh.cs.app.ui.launcher.utils.ModeSelectorState;

public class GameConfiguration {
    private final ModeSelectorState mode;
    private int elementsCount;
    private int choiceTime;

    // configuration parameters for single game
    private int failsCount;
    private int deltaTime;

    // configuration parameters for multi game
    private int computerChoiceTime;
    private double computerWinChance;

    public GameConfiguration(ModeSelectorState mode) {
        this.mode = mode;
    }

    public boolean isSingleMode() {
        return mode.equals(ModeSelectorState.SINGLE);
    }

    public boolean isMultiMode() {
        return mode.equals(ModeSelectorState.MULTI);
    }

    public int getElementsCount() {
        return elementsCount;
    }

    public void setElementsCount(int elementsCount) {
        this.elementsCount = elementsCount;
    }

    public int getChoiceTime() {
        return choiceTime;
    }

    public void setChoiceTime(int choiceTime) {
        this.choiceTime = choiceTime;
    }

    public int getFailsCount() {
        if (!isSingleMode()) {
            throw new IllegalStateException("This parameter is used only in single mode, and the config isn't in one");
        }
        return failsCount;
    }

    public void setFailsCount(int failsCount) {
        if (!isSingleMode()) {
            throw new IllegalStateException("This parameter is used only in single mode, and the config isn't in one");
        }
        this.failsCount = failsCount;
    }

    public int getDeltaTime() {
        if (!isSingleMode()) {
            throw new IllegalStateException("This parameter is used only in single mode, and the config isn't in one");
        }
        return deltaTime;
    }

    public void setDeltaTime(int deltaTime) {
        if (!isSingleMode()) {
            throw new IllegalStateException("This parameter is used only in single mode, and the config isn't in one");
        }
        this.deltaTime = deltaTime;
    }

    public int getComputerChoiceTime() {
        if (!isMultiMode()) {
            throw new IllegalStateException("This parameter is used only in multi mode, and the config isn't in one");
        }
        return computerChoiceTime;
    }

    public void setComputerChoiceTime(int computerChoiceTime) {
        if (!isMultiMode()) {
            throw new IllegalStateException("This parameter is used only in multi mode, and the config isn't in one");
        }
        this.computerChoiceTime = computerChoiceTime;
    }

    public double getComputerWinChance() {
        if (!isMultiMode()) {
            throw new IllegalStateException("This parameter is used only in multi mode, and the config isn't in one");
        }
        return computerWinChance;
    }

    public void setComputerWinChance(double computerWinChance) {
        if (!isMultiMode()) {
            throw new IllegalStateException("This parameter is used only in multi mode, and the config isn't in one");
        }
        this.computerWinChance = computerWinChance;
    }
}
