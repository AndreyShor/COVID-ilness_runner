package Animation;

import javafx.animation.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class MainMenuAnimation {


    public MainMenuAnimation() {

    }
    // Animation for main menu
    public void moveToAnimationImage (ImageView image, int duration, int to) {

        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        System.out.println(image);

        final KeyValue kv = new KeyValue(image.yProperty(), to);
        final KeyFrame kf = new KeyFrame(Duration.millis(duration), kv);

        timeline.getKeyFrames().add(kf);
        timeline.play();

    }




}
