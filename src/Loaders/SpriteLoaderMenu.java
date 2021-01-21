package Loaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SpriteLoaderMenu {


    // Sprite path, ERROR can occur here
    public final String LABEL_SPRITE_PATH = "src/lib/sprites/covid-19.png";
    public final String SMALL_HOUSE_SPRITE_PATH = "src/lib/sprites/Building_small.png";
    public final String BIG_HOUSE_SPRITE_PATH = "src/lib/sprites/Building_Big.png";

    // load sprite on exact position on the screen
    public ImageView loadSpriteOnPosition(String pathToFile, int x_coordinate, int y_coordinate, int width) {
        try {
            InputStream stream = new FileInputStream(pathToFile);
            Image image = new Image(stream);
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setX(x_coordinate);
            imageView.setY(y_coordinate);
            imageView.setFitWidth(width);
            imageView.setPreserveRatio(true);

            return imageView;
        } catch (ArithmeticException | FileNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

}
