package GUI;

import Animation.MainMenuAnimation;
import Controller.ButtonMenu;
import Loaders.SoundLoader;
import Loaders.SpriteLoaderMenu;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Main_GUI extends Application{

    Button buttonStart;
    Button buttonInstructions;
    Button buttonExit;
    Group mainScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Main scene
            mainScene = new Group();
            // Animation for menu
            MainMenuAnimation animation = new MainMenuAnimation();

            // Menu sound
            SoundLoader soundLoader = new SoundLoader();
            soundLoader.initMenuSound();


            // Load Menu Images
            SpriteLoaderMenu spriteLoader = new SpriteLoaderMenu();

            ImageView gameImageLabel = spriteLoader.loadSpriteOnPosition(
                    spriteLoader.LABEL_SPRITE_PATH, 700, 10, 150
            );

            animation.moveToAnimationImage(gameImageLabel, 3000, 30);

            ImageView smallHouse = spriteLoader.loadSpriteOnPosition(
                    spriteLoader.SMALL_HOUSE_SPRITE_PATH, 400, 155, 150
            );

            ImageView bigHouse = spriteLoader.loadSpriteOnPosition(
                    spriteLoader.BIG_HOUSE_SPRITE_PATH, 180, 97, 150
            );


            ImageView bigHouse2 = spriteLoader.loadSpriteOnPosition(
                    spriteLoader.BIG_HOUSE_SPRITE_PATH, 1000, 97, 150
            );

            // Game Name

            Label gameName = new Label("illness runner");
            // Add css class
            gameName.getStyleClass().add("gameName");
            gameName.setLayoutX(690);
            gameName.setLayoutY(175);

            // Score

            Label scoreLabel = new Label("BEST SCORE:");
            // Add css class
            scoreLabel.getStyleClass().add("scoreLabel");
            scoreLabel.setLayoutX(400);
            scoreLabel.setLayoutY(600);

            Label scorePoint = new Label("00000000");
            // Add css class
            scorePoint.getStyleClass().add("scoreLabel");
            scorePoint.setLayoutX(690);
            scorePoint.setLayoutY(600);


            // Line Settings

            Line line1 = new Line(0, 300, 1280, 300);

            Scene scene = new Scene(mainScene, 1280, 720);

            ButtonMenu menuButton = new ButtonMenu(soundLoader, mainScene, scene);



            mainScene.getChildren().add(menuButton.startButtonConf("Start", 200, 450));
            mainScene.getChildren().add(menuButton.instructionsButtonConf("Instructions", 500, 450));
            mainScene.getChildren().add(menuButton.exitButtonConf("Exit", 870, 450));
            mainScene.getChildren().add(bigHouse);
            mainScene.getChildren().add(bigHouse2);
            mainScene.getChildren().add(smallHouse);
            mainScene.getChildren().add(gameImageLabel);
            mainScene.getChildren().add(line1);
            mainScene.getChildren().add(gameName);

            mainScene.getChildren().add(scoreLabel);
            mainScene.getChildren().add(scorePoint);

            // Add CSS style to group
            scene.getStylesheets().add("./lib/style.css");

            // Frame set up
            primaryStage.setTitle("Covid Dingo Game ");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            soundLoader.backgroundMusic.play();

        } catch(ArithmeticException e){System.out.println(e);}
    }
}
