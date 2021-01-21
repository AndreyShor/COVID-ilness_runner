package Controller.gameObjects;

import Loaders.SoundLoader;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MainCharacter extends ImageView{

    public boolean jumpFinish = true;
    public boolean slideFinish = true;


    private Image positionStand;
    private Image positionSlide;


    public int width;
    public int height;

    public double xCoordinate;
    public double yCoordinate;

    public MainCharacter(Image stand, Image slide){
        this.positionStand = stand;
        this.positionSlide = slide;

        width = 150;
        // Set position on a screen
        xCoordinate = 100.0;
        yCoordinate = 450.0;
        // Set up character ImageView;
        stand();
        this.setX(xCoordinate);
        this.setY(yCoordinate);
        this.setFitWidth(150);
        this.setPreserveRatio(true);
        this.setViewOrder(-20.0);
    }

    public void stand() {
        this.setImage(positionStand);
    }

    public void jump(int jumpHeight, int speed) {
        SoundLoader sound = new SoundLoader();
        this.jumpFinish = false;

        // Set up animation timeline
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);

        // setup from where to what position
        final KeyValue kv = new KeyValue(this.yProperty(), jumpHeight);
        final KeyFrame kf = new KeyFrame(Duration.millis(speed), kv);

        timeline.getKeyFrames().add(kf);
        // Avoid non stop click on SPACE
        timeline.setOnFinished(e -> {
            this.jumpFinish = true;
        });
        timeline.play();

    }

    public void slide(double slideHeight, int speed) {
        this.slideFinish = false;
        // Change character View
        this.setImage(positionSlide);

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(2);
        timeline.setAutoReverse(true);

        // setup from where to what position
        final KeyValue kv = new KeyValue(this.yProperty(), slideHeight);
        final KeyFrame kf = new KeyFrame(Duration.millis(speed), kv);

        timeline.getKeyFrames().add(kf);
        // Avoid non stop click on S
        timeline.setOnFinished(e -> {
            this.slideFinish = true;
            stand();
        });
        timeline.play();
    }



}
