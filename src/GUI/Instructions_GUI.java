package GUI;

import Loaders.SoundLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Instructions_GUI extends Stage {

    private final int INSTRUCTION_WINDOW_X = 600;
    private final int INSTRUCTION_WINDOW_Y = 400;
    private Scene instScene;
    private SoundLoader sound;

    public Instructions_GUI(String nameStage, SoundLoader soundLoader) {
        super();
        this.setTitle(nameStage);

        this.sound = soundLoader;

        Group elementGroup = new Group();
        // ADD close button to group and scene
        elementGroup.getChildren().add(createText("Instructions", "h1_inst", 210, 20));
        elementGroup.getChildren().add(closeButton("Close", 180, 330));

        // ADD text list to scene
        createList(elementGroup);

        instScene = new Scene(elementGroup, INSTRUCTION_WINDOW_X, INSTRUCTION_WINDOW_Y);
        instScene.getStylesheets().add("./lib/style.css");

        this.setScene(instScene);
    }


    private Label createText(String text, String css_class, int x_coordinate, int y_coordinate){
        Label textField = new Label(text);
        textField.getStyleClass().add(css_class);
        textField.setLayoutX(x_coordinate);
        textField.setLayoutY(y_coordinate);

        return textField;

    }

    private void createList(Group elementGroup) {
        elementGroup.getChildren().add(
                createText("1. Wear a mask to increase your chances of not getting the virus ",
                        "p_inst", 20, 100
                )
        );

        elementGroup.getChildren().add(
                createText("2. Use an antiseptic to keep yourself safe ",
                        "p_inst", 20, 160
                )
        );

        elementGroup.getChildren().add(
                createText("3. Keep distance between people, otherwise you will get infected ",
                        "p_inst", 20, 220
                )
        );

        elementGroup.getChildren().add(
                createText("4. Jump over virus press \"space\", press \"S\" to bend down",
                        "p_inst", 20, 280
                )
        );
    }

    private Button closeButton(String label, int x_coordinate, int y_coordinate) {
        Button buttonClose = new Button(label);
        buttonClose.setLayoutX(x_coordinate);
        buttonClose.setLayoutY(y_coordinate);
        buttonClose.setViewOrder(10);
        buttonClose.getStyleClass().add("menuButton");

        buttonClose.setOnAction(actionEvent -> {

            sound.clickSound.stop();
            sound.clickSound.play();
            this.close();
        });

        return buttonClose;

    }

}
