package Controller.gameObjects.ObjectFactory;

import Loaders.SpriteLoaderGame;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class VirusCloud extends gameObject implements Virus {

    public double yCoordinate;

    private double xCoordinate;

    VirusCloud(double initialPositionX, int goTO) {
        super();
        yCoordinate = 280.0;
        xCoordinate = initialPositionX; // 1000
        //ImageView of SmallHouse setup

        SpriteLoaderGame image = new SpriteLoaderGame();
        this.setX(xCoordinate);
        this.setY(yCoordinate);
        this.setFitWidth(250);
        this.setPreserveRatio(true);
        this.setImage(image.loadSprite(image.ENEMY2_SPRITE_PATH));
        this.setViewOrder(-20.0);
        move(goTO, 3000);
    }


    @Override
    public void move(double moveToX, double speed) {
        this.timeline = new Timeline();
        this.timeline.setCycleCount(1);
        this.timeline.setAutoReverse(true);


        final KeyValue kv = new KeyValue(this.xProperty(), moveToX);
        final KeyFrame kf = new KeyFrame(Duration.millis(speed), kv);

        this.timeline.getKeyFrames().add(kf);
        this.timeline.setOnFinished(e -> {
            this.getAchieveX().set(true);
        });
        this.timeline.play();
    }

}
