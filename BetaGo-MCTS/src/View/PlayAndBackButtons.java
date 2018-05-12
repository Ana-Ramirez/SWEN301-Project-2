package View;

import Controller.GameViewController;
import go.Model.Game;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

class PlayAndBackButtons extends HBox {
    PlayAndBackButtons(GameViewController gameViewController, int WIDTH, int HEIGHT) {
        getStyleClass().add("playBackButtons");

        Button playBtn = new Button("Play");
        playBtn.setOnAction(e -> {
            gameViewController.restartGame();
            gameViewController.displayGamePlayScreen(gameViewController, WIDTH, HEIGHT);
        });

        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> gameViewController.displayHomeScreen());

        playBtn.setStyle("-fx-border-color: black;");
        backBtn.setStyle("-fx-border-color: black;");

        playBtn.setMinWidth(WIDTH * .40);
        backBtn.setMinWidth(WIDTH * .40);

        getChildren().addAll(backBtn, playBtn);
    }
}