package Controller.gameObjects.ObjectFactory;

import Loaders.SpriteLoaderGame;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Antiseptic extends gameObject {


    public double yCoordinate;

    private double xCoordinate;

    Antiseptic(double initialPositionX, double goTO) {
        super();
        yCoordinate = 600.0;
        xCoordinate = initialPositionX; // 1000

        // ImageView of Antiseptic setup
        SpriteLoaderGame image = new SpriteLoaderGame();
        this.setX(xCoordinate);
        this.setY(yCoordinate);
        this.setFitWidth(100);
        this.setPreserveRatio(true);
        this.setImage(image.loadSprite(image.ANTISEPTIC_SPRITE_PATH));
        move(goTO, 3000);
    }
    public void move(double moveToX, double speed) {
        timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(true);


        final KeyValue kv = new KeyValue(this.xProperty(), moveToX);
        final KeyFrame kf = new KeyFrame(Duration.millis(speed), kv);

        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(e -> {
            this.getAchieveX().set(true);
        });
        timeline.play();
    }
}
