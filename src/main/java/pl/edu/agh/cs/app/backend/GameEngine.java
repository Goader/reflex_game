package pl.edu.agh.cs.app.backend;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;
import pl.edu.agh.cs.app.backend.status.IGameStatus;
import pl.edu.agh.cs.app.backend.status.MultiGameStatus;
import pl.edu.agh.cs.app.backend.status.SingleGameStatus;
import pl.edu.agh.cs.app.ui.game.panes.GamePane;

public class GameEngine extends Task {
    private final GameConfiguration config;

    private GamePane gamePane;
    private SingleGameStatus singleGameStatus;
    private MultiGameStatus multiGameStatus;

    public GameEngine(GameConfiguration config) {
        super();
        if (config == null) {
            throw new IllegalArgumentException("Configuration object cannot be null");
        }

        this.config = config;
        singleGameStatus = null;
        multiGameStatus = null;

        if (config.isSingleMode()) {
            singleGameStatus = new SingleGameStatus();
        }
        else {
            multiGameStatus = new MultiGameStatus();
        }
    }

    public void setGamePane(GamePane gamePane) {
        this.gamePane = gamePane;
    }

    public IGameStatus getStatus() {
        return config.isSingleMode() ? singleGameStatus : multiGameStatus;
    }

    @Override
    protected Object call() {
        startGame();
        return null;
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Platform.exit();
        }
    }

    public void startGame() {
        if (gamePane == null) {
            throw new IllegalStateException("You cannot start game without linking the game window first");
        }

        startCountdown();
    }

    public void nextRound() {
        startCountdown();
    }

    private void updateCountdown(Label countdown, String text) {
        Platform.runLater(() -> {
            countdown.setText(text);
            gamePane.clear();
            countdown.setLayoutX((config.getGameWidth() - labelWidth(countdown)) / 2);
            countdown.setLayoutY((config.getGameHeight() - labelHeight(countdown)) / 2);
            gamePane.add(countdown);
        });
    }

    private void startCountdown() {
        Label countdown = new Label();
        countdown.setStyle("-fx-font-size: 128px;\n" +
                "-fx-font-weight: bold;\n" +
                "-fx-text-fill: #333333;\n" +
                "-fx-effect: dropshadow(gaussian, rgba(255,255,255,0.5), 1,1,1,1);");

        updateCountdown(countdown, "3");
        sleep(1000);
        updateCountdown(countdown, "2");
        sleep(1000);
        updateCountdown(countdown, "1");
        sleep(1000);
        updateCountdown(countdown, "Go!");
        sleep(1000);

        play();
    }

    private void play() {

    }

    private void success() {

    }

    private void failure() {

    }

    private void endRound() {

    }

    private void endGame() {

    }

    private Text labelSizeAux(Label label) {
        Text text = new Text(label.getText());
        text.setStyle(label.getStyle());
        new Scene(new Group(text));
        text.applyCss();
        return text;
    }

    private double labelWidth(Label label) {
        return labelSizeAux(label).getLayoutBounds().getWidth();
    }

    private double labelHeight(Label label) {
        return labelSizeAux(label).getLayoutBounds().getHeight();
    }
}
