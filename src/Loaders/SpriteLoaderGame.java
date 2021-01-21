package Loaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SpriteLoaderGame {

    // SPRITE PATH, ERROR can occur here
    public final String CHARACTER_SPRITE_PATH = "src/lib/sprites/running_man.png";
    public final String CHARACTER_DOWN_SPRITE_PATH = "src/lib/sprites/sliding-man.png";

    public final String ENEMY1_SPRITE_PATH = "src/lib/sprites/enemy1.png";
    public final String ENEMY2_SPRITE_PATH = "src/lib/sprites/enemy2.png";

    public final String MASK_SPRITE_PATH = "src/lib/sprites/mask.png";
    public final String ANTISEPTIC_SPRITE_PATH = "src/lib/sprites/antiseptic.png";

    public final String HEART_ON_SPRITE_PATH = "src/lib/sprites/heart_no.png";
    public final String HEART_OFF_SPRITE_PATH = "src/lib/sprites/heart_yes.png";

    public final String TREE_SPRITE_PATH = "src/lib/sprites/trees.png";
    public final String SMALL_HOUSE_SPRITE_PATH = "src/lib/sprites/Building_small.png";
    public final String BIG_HOUSE_SPRITE_PATH = "src/lib/sprites/Building_Big.png";
    public final String BENCH_SPRITE_PATH = "src/lib/sprites/bench.png";


    public SpriteLoaderGame() {
    }

    // Load Sprite into game objects
    public Image loadSprite(String pathToFile) {
        try {
            InputStream stream = new FileInputStream(pathToFile);
            System.out.println("stream: " + stream);
            Image image = new Image(stream);

            return image;
        } catch (ArithmeticException | FileNotFoundException e) {
            System.out.println(e);
            return null;
        }
    }

}
