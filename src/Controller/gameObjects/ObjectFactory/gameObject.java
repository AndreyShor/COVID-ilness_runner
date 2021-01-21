package Controller.gameObjects.ObjectFactory;

import javafx.animation.Timeline;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;

public abstract class gameObject extends ImageView implements Virus{

    private SimpleBooleanProperty achieveX;
    public Timeline timeline;

    gameObject() {
        super();
        // check if object on final position
        achieveX = new SimpleBooleanProperty();
    }


    public SimpleBooleanProperty getAchieveX() {
        return this.achieveX;
    }

}
