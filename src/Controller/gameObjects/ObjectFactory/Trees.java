package Controller.gameObjects.ObjectFactory;

import Loaders.SpriteLoaderGame;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Trees extends gameObject{

    public double yCoordinate;

    private double xCoordinate;

    Trees(double initialPositionX, double goTO) {
        super();
        yCoordinate = 150;
        xCoordinate = initialPositionX; // 1000

        //ImageView of Trees setup
        SpriteLoaderGame image = new SpriteLoaderGame();
        this.setX(xCoordinate);
        this.setY(yCoordinate);
        this.setFitWidth(150);
        this.setPreserveRatio(true);
        this.setImage(image.loadSprite(image.TREE_SPRITE_PATH));
        move(goTO, 14000);
    }

    public void move(double moveToX, double speed) {
        final Timeline timeline = new Timeline();
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
