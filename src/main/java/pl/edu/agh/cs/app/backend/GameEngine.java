package pl.edu.agh.cs.app.backend;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;
import pl.edu.agh.cs.app.backend.generators.LayoutGenerator;
import pl.edu.agh.cs.app.backend.icons.Icon;
import pl.edu.agh.cs.app.backend.status.IGameStatus;
import pl.edu.agh.cs.app.backend.status.MultiGameStatus;
import pl.edu.agh.cs.app.backend.status.SingleGameStatus;
import pl.edu.agh.cs.app.backend.status.states.PressedStatus;
import pl.edu.agh.cs.app.ui.game.panes.GamePane;

import java.util.List;
import java.util.Random;

public class GameEngine {
    private final GameConfiguration config;

    private GamePane gamePane;
    private IGameStatus status;
    private SingleGameStatus singleGameStatus;
    private MultiGameStatus multiGameStatus;

    private final LayoutGenerator generator;

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
            status = singleGameStatus;
        }
        else {
            multiGameStatus = new MultiGameStatus();
            status = multiGameStatus;
        }

        generator = new LayoutGenerator(config);
    }

    public Task getStartTask() {
        return new Task() {
            @Override
            protected Object call() {
                startGame();
                return null;
            }
        };
    }

    public Task getNextRoundTask() {
        return new Task() {
            @Override
            protected Object call() {
                nextRound();
                return null;
            }
        };
    }

    public Task getPressedTask() {
        return new Task() {
            @Override
            protected Object call() {
                pressed();
                return null;
            }
        };
    }

    public void setGamePane(GamePane gamePane) {
        this.gamePane = gamePane;
    }

    public IGameStatus getStatus() {
        return config.isSingleMode() ? singleGameStatus : multiGameStatus;
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

        nextRound();
    }

    public void nextRound() {
        status.startRound();
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

    private void setMouseClickHandler(Icon icon, PressedStatus pressed) {
        icon.setOnMouseClicked(e -> {
            status.pressed(pressed);
            Thread thread = new Thread(this.getPressedTask());
            thread.setDaemon(true);
            thread.start();
        });
    }

    private void play() {
        List<Icon> icons = generator.generate();
        for (Icon icon : icons) {
            setMouseClickHandler(icon, PressedStatus.FAILURE);
        }
        setMouseClickHandler(icons.get(new Random().nextInt(icons.size())), PressedStatus.SUCCESS);
        Platform.runLater(() -> {
            gamePane.clear();
            gamePane.addAll(icons);
            // TODO add time countdown somewhere
        });
    }

    private void pressed() {
        // TODO will be changed
        if (status.isSuccess()) success();
        else if (status.isFailure()) failure();
        else notPressed();
    }

    private void success() {
        // TODO will be changed
        Platform.runLater(gamePane::clear);
        endRound();
    }

    private void failure() {
        // TODO will be changed
        Platform.runLater(gamePane::clear);
        endRound();
    }

    private void notPressed() {
        // TODO will be change
        Platform.runLater(gamePane::clear);
        endRound();
    }

    private void endRound() {
        // TODO more activities should be done here
        status.endRound();
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
