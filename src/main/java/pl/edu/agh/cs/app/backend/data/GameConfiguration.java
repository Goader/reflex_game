package pl.edu.agh.cs.app.backend.data;

import pl.edu.agh.cs.app.ui.launcher.utils.ModeSelectorState;

public class GameConfiguration {
    private final ModeSelectorState mode;
    private int elementsCount;
    private int choiceTime;

    // layout configuration
    private int radius;
    private int gameHeight;
    private int gameWidth;

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
        if (elementsCount <= 0 || elementsCount > 32) {
            throw new IllegalArgumentException("Elements count must be in the [1, 32] range");
        }
        this.elementsCount = elementsCount;
        /*
        Radius is calculated the next way:
            for elements count up to 12 - it is 128px
            for elements count from 12 to 32 - it shrinks linearly from 128px to 64px
            for elements count = 32 - it is already 64px
         */
        this.radius = 128 - Math.max((64 * (elementsCount - 12)) / 20, 0);
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

    public int getRadius() {
        return radius;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public void setGameHeight(int gameHeight) {
        this.gameHeight = gameHeight;
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public void setGameWidth(int gameWidth) {
        this.gameWidth = gameWidth;
    }
}
