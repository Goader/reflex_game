package pl.edu.agh.cs.app.backend;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;
import pl.edu.agh.cs.app.backend.generators.LayoutGenerator;
import pl.edu.agh.cs.app.backend.geometry.Vector2d;
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

    private void updateLabel(Label label, String text) {
        Platform.runLater(() -> {
            label.setText(text);
            gamePane.clear();
            label.setLayoutX((config.getGameWidth() - labelWidth(label)) / 2);
            label.setLayoutY((config.getGameHeight() - labelHeight(label)) / 2);
            gamePane.add(label);
        });
    }

    public void startGame() {
        if (gamePane == null) {
            throw new IllegalStateException("You cannot start game without linking the game window first");
        }

        nextRound();
    }

    private void setMouseClickHandler(Icon icon, PressedStatus pressed) {
        icon.setOnMouseClicked(e -> {
            status.pressed(pressed);
            Thread thread = new Thread(this.getPressedTask());
            thread.setDaemon(true);
            thread.start();
        });
    }

    public void nextRound() {
        final int msgIconSpacing = 100;
        Label findMsg = new Label("Try to find this icon");
        findMsg.setStyle("-fx-font-size: 128px;\n" +
                "-fx-font-weight: bold;\n" +
                "-fx-text-fill: #333333;\n" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 1, 1, 1, 1);");
        List<Icon> icons = generator.generate();
        for (Icon icon : icons) {
            setMouseClickHandler(icon, PressedStatus.FAILURE);
        }
        Icon searchedIcon = icons.get(new Random().nextInt(icons.size()));
        setMouseClickHandler(searchedIcon, PressedStatus.SUCCESS);
        Icon findIcon = searchedIcon.copy();

        Platform.runLater(() -> {
            gamePane.clear();

            double msgHeight =
                    (config.getGameHeight() - labelHeight(findMsg) - msgIconSpacing - 2 * config.getRadius()) / 2;

            findMsg.setLayoutX((config.getGameWidth() - labelWidth(findMsg)) / 2);
            findMsg.setLayoutY(msgHeight);
            Vector2d center = new Vector2d(config.getGameWidth() / 2,
                    (int) (msgHeight + labelHeight(findMsg) + msgIconSpacing));
            findIcon.setCenter(center);
            gamePane.add(findMsg, findIcon);
        });
        status.startRound();
        sleep(3000);
        startCountdown(icons);
    }

    private void startCountdown(List<Icon> icons) {
        Label countdown = new Label();
        countdown.setStyle("-fx-font-size: 128px;\n" +
                "-fx-font-weight: bold;\n" +
                "-fx-text-fill: #333333;\n" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 1,1,1,1);");

        updateLabel(countdown, "3");
        sleep(1000);
        updateLabel(countdown, "2");
        sleep(1000);
        updateLabel(countdown, "1");
        sleep(1000);
        updateLabel(countdown, "Go!");
        sleep(1000);

        play(icons);
    }

    private void play(List<Icon> icons) {
        Platform.runLater(() -> {
            gamePane.clear();
            gamePane.addAll(icons);
            // TODO add time countdown somewhere
        });
    }

    private void pressed() {
        // TODO maybe more actions
//        sleep(200);  // dont know how to create the feeling of really clicking  // maybe CSS?
        if (status.isSuccess()) success();
        else if (status.isFailure()) failure();
        else notPressed();
    }

    private void success() {
        // TODO maybe more styling
        Label success = new Label();
        success.setStyle("-fx-font-size: 128px;\n" +
                "-fx-font-weight: bold;\n" +
                "-fx-text-fill: #007700;\n" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 1,1,1,1);");
        updateLabel(success, "Success");
        sleep(1800);
        endRound();
    }

    private void failure() {
        // TODO maybe more styling
        Label failure = new Label();
        failure.setStyle("-fx-font-size: 128px;\n" +
                "-fx-font-weight: bold;\n" +
                "-fx-text-fill: #990000;\n" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 1,1,1,1);");
        updateLabel(failure, "Failure");
        sleep(1800);
        endRound();
    }

    private void notPressed() {
        // TODO maybe more styling
        Label failure = new Label();
        failure.setStyle("-fx-font-size: 128px;\n" +
                "-fx-font-weight: bold;\n" +
                "-fx-text-fill: #333333;\n" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 1,1,1,1);");
        updateLabel(failure, "Nothing pressed");
        sleep(1800);
        endRound();
    }

    private void endRound() {
        // TODO more activities should be done here
        Label endMsg = new Label();
        endMsg.setStyle("-fx-font-size: 64px;\n" +
                "-fx-font-weight: bold;\n" +
                "-fx-text-fill: #333333;\n" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 1,1,1,1);");
        updateLabel(endMsg, "Press the green button to continue");
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
