package pl.edu.agh.cs.app.backend.generators;

import pl.edu.agh.cs.app.backend.icons.Icon;

public class LayoutGenerator {
    private final PointsGenerator pointsGenerator;
    private final IconsGenerator iconsGenerator;

    public LayoutGenerator(PointsGenerator pointsGenerator, IconsGenerator iconsGenerator) {
        this.pointsGenerator = pointsGenerator;
        this.iconsGenerator = iconsGenerator;
    }
}
