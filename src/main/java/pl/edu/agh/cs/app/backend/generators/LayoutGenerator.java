package pl.edu.agh.cs.app.backend.generators;

import javafx.scene.transform.Rotate;
import pl.edu.agh.cs.app.backend.geometry.Vector2d;
import pl.edu.agh.cs.app.backend.icons.Icon;
import pl.edu.agh.cs.app.backend.utils.GameConfiguration;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LayoutGenerator {
    private final PointsGenerator pointsGenerator;
    private final IconsGenerator iconsGenerator;

    private final GameConfiguration config;
    private final Random random;

    public LayoutGenerator(GameConfiguration config) {
        this.pointsGenerator = new PointsGenerator(config);
        this.iconsGenerator = new IconsGenerator();
        this.config = config;
        this.random = new Random();
    }

    public List<Icon> generate() {
        int count = config.getElementsCount();
        List<Icon> icons = iconsGenerator.generate(count);
        List<Vector2d> positions = pointsGenerator.generate(count);

        if (icons.size() != positions.size()) {
            throw new IllegalStateException("Icons and point generators generated different " +
                    "count of objects given the same parameter");
        }

        Iterator<Icon> iconIterator = icons.iterator();
        Iterator<Vector2d> positionIterator = positions.iterator();
        while (iconIterator.hasNext() && positionIterator.hasNext()) {
            Icon icon = iconIterator.next();
            Vector2d position = positionIterator.next();

            icon.setCenter(position);
            icon.setRadius(config.getRadius());
            icon.setRotate(random.nextInt(360));

            icon.setOnMouseClicked(e -> {
                System.out.println(position);
            });
            // TODO maybe some other effects
        }

        return icons;
    }
}
