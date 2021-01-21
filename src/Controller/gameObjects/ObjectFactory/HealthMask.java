package Controller.gameObjects.ObjectFactory;

import Loaders.SpriteLoaderGame;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class HealthMask extends gameObject {

    public double yCoordinate;

    private double xCoordinate;

    HealthMask(double initialPositionX, double goTO) {
        super();
        yCoordinate = 280;
        xCoordinate = initialPositionX; // 1000

        // ImageView of HealthMask setup
        SpriteLoaderGame image = new SpriteLoaderGame();
        this.setX(xCoordinate);
        this.setY(yCoordinate);
        this.setFitWidth(150);
        this.setPreserveRatio(true);
        this.setImage(image.loadSprite(image.MASK_SPRITE_PATH));
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
