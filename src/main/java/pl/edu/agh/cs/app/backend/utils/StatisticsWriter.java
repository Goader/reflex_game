package pl.edu.agh.cs.app.backend.utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StatisticsWriter {
    public static void write(List<Integer> reactionTimes) {
        JSONObject stats = new JSONObject();

        JSONArray reactions = new JSONArray();
        reactions.addAll(reactionTimes);

        stats.put("Reaction times", reactions);

        try (FileWriter writer = new FileWriter(
                StatisticsWriter.class.getResource("/reactions.json").getPath(), false
        )) {
            stats.writeJSONString(writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Platform.exit();
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }

        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("JSON written");
        msg.setHeaderText("JSON file containing statistics has been written to the build/resources");
        msg.show();
    }
}
