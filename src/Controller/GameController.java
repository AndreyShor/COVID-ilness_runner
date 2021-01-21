package Controller;

import Controller.gameObjects.MainCharacter;
import Controller.gameObjects.ObjectFactory.ObjectFactory;
import Controller.gameObjects.ObjectFactory.gameObject;
import Loaders.SoundLoader;
import Loaders.SpriteLoaderGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Line;
import javafx.util.Duration;


public class GameController {

    private Group viewScene;
    private Scene allScene;

    private double spawnEnemyTime;
    private double spawnHealthTime;
    private double spawnBackTime = 2.0;

    private SimpleIntegerProperty score;
    private SimpleIntegerProperty healthScore;

    GameController(Group viewSScene, Scene allScene) {

        this.viewScene = viewSScene;
        this.allScene = allScene;
        // Set up observable for Score and Health
        score = new SimpleIntegerProperty();
        healthScore = new SimpleIntegerProperty();

        this.score.set(0); // // Set up start score
        this.healthScore.set(3); // Set up initial health level
    }

    public void startGame() {
        this.viewScene.getChildren().clear();

        // Background Sound Music and effect

        SoundLoader sound = new SoundLoader();

        sound.initGameSound();

        sound.citySound.play();
        sound.gameMusic.play();

        // Character Control

        SpriteLoaderGame gameSpriteLoader = new SpriteLoaderGame();

        MainCharacter character = new MainCharacter(
                gameSpriteLoader.loadSprite(gameSpriteLoader.CHARACTER_SPRITE_PATH),
                gameSpriteLoader.loadSprite(gameSpriteLoader.CHARACTER_DOWN_SPRITE_PATH)
        );



                // check button pressed
        allScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE && character.jumpFinish && character.slideFinish) {
                sound.jumpSound.stop();
                sound.jumpSound.play();
                character.jump(200, 400);
            } else if (e.getCode() == KeyCode.S && character.slideFinish && character.jumpFinish) {
                sound.slideSound.stop();
                sound.slideSound.play();
                character.slide(character.yCoordinate + 100.0, 600);
            }
        });

        // Enemy Control
        this.spawnEnemyTime = 3.0;
        Timeline enemyController = new Timeline(
                new KeyFrame(Duration.seconds(this.spawnEnemyTime),
                        event -> {
                            ObjectFactory factory = new ObjectFactory();
                            // Enemy Factory spawn new enemy
                            gameObject enemy = (gameObject) factory.choiceRandomEnemy();

                            // Set up Thread to check enemy location and intersection with character
                            Thread t = new Thread(() -> {

                                while(true){
                                    if (character.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                                        // Decrease health of character
                                        Platform.runLater(this::decreaseHealth);
                                        sound.coughtSound.stop();
                                        sound.coughtSound.play();

                                        // clean enemy from visible screen
                                        enemy.timeline.jumpTo(Duration.millis(2900));
                                        break;
                                    } else if(enemy.xProperty().get() < -100.0 ) {
                                        // Break thread at the end of animation
                                        enemy.timeline.jumpTo(Duration.millis(2900));
                                        break;
                                    }
                                }

                            });

                            t.start();

                            enemy.getAchieveX().addListener(
                                    (observable, oldValue, newValue) -> {
                                        if (newValue) {
                                            t.interrupt();
                                            // Delete enemy at the end position
                                            this.viewScene.getChildren().remove(enemy);
                                        }
                                    });
                            // Add Enemy on a screen
                            this.viewScene.getChildren().add(enemy);
                        })
        );

        // Health thing control

        this.spawnHealthTime = 8.2;

        Timeline healthController = new Timeline(
                new KeyFrame(Duration.seconds(this.spawnHealthTime),
                        event -> {
                            ObjectFactory factory = new ObjectFactory();
                            // Health Factory spawn new health object (Mask or Antiseptic)
                            gameObject health = (gameObject) factory.choiceRandomHealth();

                            // Set up Thread to check enemy location and intersection with character
                            Thread t_health = new Thread(() -> {

                                while(true){
                                    if (character.getBoundsInParent().intersects(health.getBoundsInParent())) {
                                        Platform.runLater(this::increaseHealth);
                                        // Increase health of character
                                        sound.awardSound.stop();
                                        sound.awardSound.play();

                                        // clean health from visible screen
                                        health.timeline.jumpTo(Duration.millis(2900));
                                        break;
                                    } else if(health.xProperty().get() < -100.0 ) {
                                        // Break thread at the end of animation
                                        health.timeline.jumpTo(Duration.millis(2900));
                                        break;
                                    }
                                }

                            });

                            t_health.start();

                            health.getAchieveX().addListener(
                                    (observable, oldValue, newValue) -> {
                                        if (newValue) {
                                            // Delete enemy at the end position
                                            t_health.interrupt();
                                            this.viewScene.getChildren().remove(health);
                                        }
                                    });
                            // Add Health on a screen
                            this.viewScene.getChildren().add(health);
                        })
        );

        // Background Controller

        Timeline backgroundController = new Timeline(
                new KeyFrame(Duration.seconds(this.spawnBackTime),
                        event -> {
                            ObjectFactory factory = new ObjectFactory();
                            // BackGround Factory spawn new background object (Houses or Trees and ...)
                            gameObject backGround = (gameObject) factory.choiceRandomBackground();

                            backGround.getAchieveX().addListener(
                                    (observable, oldValue, newValue) -> {
                                        if (newValue) {
                                            // Delete house at the end position
                                            this.viewScene.getChildren().remove(backGround);
                                        }
                                    });

                            this.viewScene.getChildren().add(backGround);
                        })
        );

        // Score counter

        Timeline scoreCounter = new Timeline(
                new KeyFrame(Duration.seconds(0.1),
                        event -> {
                            int value = this.score.get();
                            value += 5;
                            this.score.set(value);
                        }
                )
        );



        // Score Label and number
        Label scoreField = new Label();
        Label scoreText = new Label("Your Score: ");
        scoreField.textProperty().bind(this.score.asString());
        scoreField.getStyleClass().add("score");
        scoreText.getStyleClass().add("score");

        scoreField.setLayoutX(300);
        scoreField.setLayoutY(10);
        scoreText.setLayoutX(10);
        scoreText.setLayoutY(10);

        Line line = new Line(0, 300, 1280, 300);

        // Health label and number

        ImageView heart = new ImageView();
        heart.setImage(gameSpriteLoader.loadSprite(gameSpriteLoader.HEART_OFF_SPRITE_PATH));
        heart.setX(400);
        heart.setY(10);
        heart.setFitWidth(40);
        heart.setPreserveRatio(true);

        Label healthField = new Label();
        healthField.getStyleClass().add("score");
        healthField.textProperty().bind(this.healthScore.asString());
        healthField.setLayoutX(455);
        healthField.setLayoutY(10);


        // Game Over Event
        healthScore.addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() == 0) {
                // Stop all controllers
                healthController.stop();
                enemyController.stop();
                scoreCounter.stop();
                backgroundController.stop();
                // clear scene
                this.viewScene.getChildren().clear();
                // Add label
                Label gameOverLabel = new Label("Game Over");
                gameOverLabel.setLayoutX(500);
                gameOverLabel.setLayoutY(300);
                gameOverLabel.getStyleClass().add("gameOver");

                // Add button exit
                Button buttonExit = new Button("Exit");
                buttonExit.setLayoutX(540);
                buttonExit.setLayoutY(400);
                buttonExit.setViewOrder(10);
                buttonExit.getStyleClass().add("menuButton");

                buttonExit.setOnAction(actionEvent -> {
                    Platform.exit();
                });


                // add to group
                this.viewScene.getChildren().add(gameOverLabel);
                this.viewScene.getChildren().add(buttonExit);
                this.viewScene.getChildren().add(scoreField);
                this.viewScene.getChildren().add(scoreText);

                // Play sound
                sound.gameOverSound.play();
            }
        });

        // Start Game

        this.viewScene.getChildren().add(character);
        this.viewScene.getChildren().add(line);


        this.viewScene.getChildren().add(heart);
        this.viewScene.getChildren().add(healthField);
        this.viewScene.getChildren().add(scoreField);
        this.viewScene.getChildren().add(scoreText);

        // Run all controllers
        enemyController.setCycleCount(Timeline.INDEFINITE);
        enemyController.play();

        healthController.setCycleCount(Timeline.INDEFINITE);
        healthController.play();

        backgroundController.setCycleCount(Timeline.INDEFINITE);
        backgroundController.play();

        scoreCounter.setCycleCount(Timeline.INDEFINITE);
        scoreCounter.play();
    }

    private void increaseHealth() {
        // increase health level
        int hLevel = this.healthScore.get();
        hLevel +=1;
        this.healthScore.set(hLevel);
    }

    private void decreaseHealth() {
        // decrease health level
        int hLevel = this.healthScore.get();
        hLevel -=1;
        this.healthScore.set(hLevel);
    }
}


