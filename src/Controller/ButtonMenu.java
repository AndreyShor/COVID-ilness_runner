package Controller;

import Controller.GameController;
import GUI.Instructions_GUI;
import Loaders.SoundLoader;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class ButtonMenu {

    private Button buttonStart;
    private Button buttonInstructions;
    private Button buttonExit;
    private SoundLoader soundLoader;

    private Scene allScene;
    private Group mainScene;

    private Instructions_GUI instructionMenu;


    public ButtonMenu(SoundLoader sound, Group root, Scene scene) {
        this.mainScene = root;
        this.soundLoader = sound;
        this.allScene = scene;
    }

    public Button startButtonConf(String text, int x_coordinate, int y_coordinate )  {
        // Set up start button
        buttonStart = new Button(text);
        buttonStart.setLayoutX(x_coordinate);
        buttonStart.setLayoutY(y_coordinate);
        buttonStart.setViewOrder(10);
        buttonStart.getStyleClass().add("menuButton");

        buttonStart.setOnAction(actionEvent -> startHandle());

        return this.buttonStart;
    }

    public Button instructionsButtonConf(String text, int x_coordinate, int y_coordinate ) {
        // Set up instruction button
        buttonInstructions = new Button(text);
        buttonInstructions.setLayoutX(x_coordinate);
        buttonInstructions.setLayoutY(y_coordinate);
        buttonInstructions.setViewOrder(10);
        buttonInstructions.getStyleClass().add("menuButton");

        buttonInstructions.setOnAction(actionEvent -> instructionHandle());

        return this.buttonInstructions;
    }

    public Button exitButtonConf(String text, int x_coordinate, int y_coordinate) {
        // Set up Exit button
        buttonExit = new Button(text);
        buttonExit.setLayoutX(x_coordinate);
        buttonExit.setLayoutY(y_coordinate);
        buttonExit.setViewOrder(10);
        buttonExit.getStyleClass().add("menuButton");

        buttonExit.setOnAction(actionEvent -> exitHandle());

        return this.buttonExit;
    }


    private void startHandle() {
        // On start click do ..
        soundLoader.clickSound.stop();
        soundLoader.clickSound.play();

        GameController gameController = new GameController(mainScene, allScene);
        gameController.startGame();


        soundLoader.backgroundMusic.stop();
    }

    private void instructionHandle() {
        // On instruction click do ..
        soundLoader.clickSound.stop();
        soundLoader.clickSound.play(100);

        if(instructionMenu == null) {
            instructionMenu = new Instructions_GUI("Instruction", soundLoader);
            instructionMenu.show();
        } else if (instructionMenu.isShowing()){
            instructionMenu.toFront();
        } else {
            instructionMenu.show();
        }
    }

    private void exitHandle() {
        // On exit click do ..
        soundLoader.clickSound.stop();
        soundLoader.clickSound.play();

        Platform.exit();
    }


}
