package pl.edu.agh.cs.app.backend.utils;

import javafx.application.Platform;
import javafx.scene.image.Image;
import pl.edu.agh.cs.app.backend.icons.Icon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class IconsLoader {
    private final String folderPath;

    public IconsLoader(String resourcesIconsPath) {
        folderPath = resourcesIconsPath;
    }

    public List<Path> getIconsFilePaths() {
        LinkedList<Path> filepaths = new LinkedList<>();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(folderPath);
        String path;
        try {
            if (url == null) throw new IllegalStateException("Cannot find the needed resources");
            path = url.toURI().getPath();

            File[] files = new File(path).listFiles();

            if (files == null) throw new IllegalStateException("Cannot find the needed resources");

            for (File f : files) {
                filepaths.add(f.toPath());
            }
            return filepaths;
        } catch (NullPointerException | URISyntaxException ex) {
            ex.printStackTrace();
            Platform.exit();
        }
        return new LinkedList<>();
    }

    public List<Icon> loadIcons() {
        LinkedList<Icon> icons = new LinkedList<>();

        List<Path> paths = getIconsFilePaths();
        for (Path path : paths) {
            try {
                Image img = new Image(new FileInputStream(path.toFile()));
                icons.add(new Icon(img));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                Platform.exit();
            }
        }
        return icons;
    }
}
