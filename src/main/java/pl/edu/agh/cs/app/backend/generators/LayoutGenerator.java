package pl.edu.agh.cs.app.backend.generators;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import pl.edu.agh.cs.app.backend.geometry.Vector2d;
import pl.edu.agh.cs.app.backend.icons.Icon;
import pl.edu.agh.cs.app.backend.data.GameConfiguration;

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

            icon.setEffect(new DropShadow(8, Color.rgb(0, 0, 0, 0.8)));
            icon.setId("icon");
        }

        return icons;
    }
}
