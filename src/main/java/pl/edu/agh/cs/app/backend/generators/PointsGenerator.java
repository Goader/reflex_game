package pl.edu.agh.cs.app.backend.generators;

import pl.edu.agh.cs.app.backend.geometry.Vector2d;
import pl.edu.agh.cs.app.backend.utils.GameConfiguration;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class PointsGenerator {
    private final int maxY;
    private final int maxX;
    private final int minY;
    private final int minX;
    private final int radius;

    private final Random random;

    public PointsGenerator(GameConfiguration config) {
        this.radius = config.getRadius();

        maxY = config.getGameHeight() - radius;
        minY = radius;

        maxX = config.getGameWidth() - radius;
        minX = radius;

        random = new Random();
    }

    public Vector2d generatePoint() {
        int x = random.nextInt(maxX - minX) + minX;
        int y = random.nextInt(maxY - minY) + minY;
        return new Vector2d(x, y);
    }

    // TODO we can replace this naive approach using the Poisson Disc Sampling Algorithm
    // https://www.youtube.com/watch?v=7WcmyxyFO7o
    // https://www.cs.ubc.ca/~rbridson/docs/bridson-siggraph07-poissondisk.pdf
    public List<Vector2d> generate(int pointsCount) {
        LinkedList<Vector2d> points = new LinkedList<>();
        boolean valid;
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
        }
        return points;
    }
}
