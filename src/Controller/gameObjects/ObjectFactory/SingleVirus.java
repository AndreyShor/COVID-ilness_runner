package Controller.gameObjects.ObjectFactory;

import Loaders.SpriteLoaderGame;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class SingleVirus extends gameObject implements Virus {

    public double yCoordinate;

    private double xCoordinate;


    SingleVirus(double initialPositionX, double goTO){
        super();
        yCoordinate = 520.0;
        xCoordinate = initialPositionX; // 1000

        //ImageView of SingleVirus Enemy setup
        SpriteLoaderGame image = new SpriteLoaderGame();
        this.setX(xCoordinate);
        this.setY(yCoordinate);
        this.setFitWidth(100);
        this.setPreserveRatio(true);
        this.setImage(image.loadSprite(image.ENEMY1_SPRITE_PATH));
        this.setViewOrder(20);
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
