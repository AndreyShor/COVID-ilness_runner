package Controller.gameObjects.ObjectFactory;

import javafx.scene.Scene;

import java.util.Random;

public class ObjectFactory {
    private Virus virusGenerated;
    Scene scene;

    public ObjectFactory() {

    }

    public Virus choiceRandomEnemy() {
        // Factory randomly return enemy, change of each enemy occur is 50%
        Random r = new Random();
        int low = 1;
        int high = 3;
        int result = r.nextInt(high-low) + low;

        if (result == 1) {
            this.virusGenerated = new SingleVirus(1300, -300);
        } else if (result == 2) {
            this.virusGenerated = new VirusCloud(1300, -300);
        }


        return this.virusGenerated;
    }

    public Virus choiceRandomHealth() {
        // Factory randomly return health objects to increase character health, change of each health object occur is 50%
        Random r = new Random();
        int low = 1;
        int high = 3;
        int result = r.nextInt(high-low) + low;

        if (result == 1) {
            this.virusGenerated = new Antiseptic(1300, -100);
        } else if (result == 2) {
            this.virusGenerated = new HealthMask(1300, -500);
        }


        return this.virusGenerated;
    }

    public Virus choiceRandomBackground() {
        // Factory randomly return background element, change of each background element occur is 25%
        Random r = new Random();
        int low = 1;
        int high = 5;
        int result = r.nextInt(high-low) + low;

        if (result == 1) {
            this.virusGenerated = new Trees(1300, -300);
        } else if (result == 2) {
            this.virusGenerated = new Bench(1300, -300);
        } else if (result == 3) {
            this.virusGenerated = new SmallHouse(1300, -300);
        } else if (result == 4) {
            this.virusGenerated = new BigHouse(1300, -300);
        }


        return this.virusGenerated;
    }

    public void dead() {
    }
}
