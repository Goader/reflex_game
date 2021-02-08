package pl.edu.agh.cs.app.backend.generators;

import pl.edu.agh.cs.app.backend.geometry.Vector2d;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PointsGenerator {
    private int maxY;
    private int maxX;
    private int minY;
    private int minX;
    private int radius;

    private final GameConfiguration config;

    private final Random random;

    public PointsGenerator(GameConfiguration config) {
        this.config = config;

        random = new Random();
    }

    private void updateParameters() {
        radius = config.getRadius();

        maxY = config.getGameHeight() - radius;
        minY = radius;

        maxX = config.getGameWidth() - radius;
        minX = radius;
    }

    private Vector2d generatePoint() {
        int x = random.nextInt(maxX - minX) + minX;
        int y = random.nextInt(maxY - minY) + minY;
        return new Vector2d(x, y);
    }

    // we can replace this naive approach using the Poisson Disc Sampling Algorithm
    // https://www.youtube.com/watch?v=7WcmyxyFO7o
    // https://www.cs.ubc.ca/~rbridson/docs/bridson-siggraph07-poissondisk.pdf
    public List<Vector2d> generate(int pointsCount) {
        updateParameters();
        LinkedList<Vector2d> points = new LinkedList<>();
        boolean valid;
        long timeStarted = System.currentTimeMillis();
        while (points.size() < pointsCount) {
            Vector2d newPoint = generatePoint();
            valid = true;

            for (Vector2d point : points) {
                if (newPoint.dist(point) <= 2 * radius) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                points.add(newPoint);
            }

            if (System.currentTimeMillis() - timeStarted > 2500) {
                return generate(pointsCount);
            }
        }
        return points;
    }
}
