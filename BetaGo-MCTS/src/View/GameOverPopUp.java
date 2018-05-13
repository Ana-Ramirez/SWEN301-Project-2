package View;

import Controller.GameViewController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOverPopUp extends Stage {

    private Stage dialog;
    private Label scoreLabel;

    public GameOverPopUp(Stage primaryStage, GameViewController gameViewController, int WIDTH, int HEIGHT) {
        dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);

        Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setStyle("-fx-font-size: 44pt; -fx-border-width: 3; -fx-border-color: transparent transparent black transparent;");

        scoreLabel = new Label("");
        scoreLabel.setStyle("-fx-font-size: 30pt;");

        Button homeScreenBtn = new Button("Home Screen");
        homeScreenBtn.setOnAction(e -> {
            this.hide();
            gameViewController.displayHomeScreen();
        });

        Button playAgainBtn = new Button("Play again");
        playAgainBtn.setOnAction(e -> {
            this.hide();
            gameViewController.restartGame();
        });

        Button quitBtn = new Button("Quit");
        quitBtn.setOnAction(e -> System.exit(0) );

        quitBtn.setStyle("-fx-border-color: black; -fx-font-size: 18pt;");
        homeScreenBtn.setStyle("-fx-border-color: black; -fx-font-size: 18pt;");
        playAgainBtn.setStyle("-fx-border-color: black; -fx-font-size: 18pt;");

        quitBtn.setMinWidth(WIDTH * (1.2 * 3/4) * .20);
        playAgainBtn.setMinWidth(WIDTH * (1.2 * 3/4) * .20);
        homeScreenBtn.setMinWidth(WIDTH * (1.2 * 3/4) * .20);

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(playAgainBtn, homeScreenBtn, quitBtn);
        hBox.setStyle("-fx-alignment: center;");

        VBox dialogVbox = new VBox(30);
        dialogVbox.getStyleClass().add("endGamePopup");
        dialogVbox.getChildren().addAll(gameOverLabel, scoreLabel, hBox);

        Scene dialogScene = new Scene(dialogVbox, WIDTH * (1.2 * 3/4), HEIGHT* (1.0 * 3/4));
        dialogScene.getStylesheets().add("go/stylesheet.css");

        dialog.setOnCloseRequest(event -> System.exit(0));
        dialog.setOpacity(.90);
        dialog.setScene(dialogScene);
        dialog.setResizable(false);
    }

    public void display(GameViewController gameViewController) {

    	gameViewController.displayGameOverPopUp(scoreLabel, dialog);
    }

    public void hide() {
        dialog.hide();
    }
}