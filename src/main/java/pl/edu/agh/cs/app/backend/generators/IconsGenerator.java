package pl.edu.agh.cs.app.backend.generators;

import pl.edu.agh.cs.app.backend.icons.Icon;
import pl.edu.agh.cs.app.backend.utils.IconsLoader;

import java.util.*;

public class IconsGenerator {
    private final LinkedList<Icon> storage;

    public IconsGenerator() {
        String ICONS_PATH = "icons";
        IconsLoader loader = new IconsLoader(ICONS_PATH);

        storage = new LinkedList<>(loader.loadIcons());
    }

    public List<Icon> generate(int count) {
        Collections.shuffle(storage);
        return storage.subList(0, count);
    }
}
