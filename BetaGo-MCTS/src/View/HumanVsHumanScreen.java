package View;

import Controller.GameViewController;
import go.Main;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

class HumanVsHumanScreen extends VBox {
	
	GameViewController controller;

	
    HumanVsHumanScreen(GameViewController gameViewController, int WIDTH, int HEIGHT) {
        getStyleClass().add("humanVsHumanScreen");
        controller = gameViewController;

        PlayAndBackButtons playAndBackBtns = new PlayAndBackButtons(gameViewController, WIDTH, HEIGHT);
        BoardSizeButtons boardSizeBtns = new BoardSizeButtons(gameViewController, WIDTH, HEIGHT);

        TextField playerOneName = new TextField("Player 1");
        TextField playerTwoName = new TextField("Player 2");
        
        playerOneName.setOnKeyReleased(event -> controller.setPlayerOneName(playerOneName.getText()));
        playerTwoName.setOnKeyReleased(event -> controller.setPlayerTwoName(playerTwoName.getText()));

        GridPane nameSelectGrid = new GridPane();
        nameSelectGrid.add( new Label("Players"), 0, 0, 2, 1);

        int imageWidth = WIDTH / 5;
        ImageView whiteImageView = new ImageView( new Image(Main.class.getResourceAsStream("../images/white.png"), imageWidth, imageWidth, true, true) );
        ImageView blackImageView = new ImageView( new Image(Main.class.getResourceAsStream("../images/black.png"), imageWidth, imageWidth, true, true) );

        nameSelectGrid.add(blackImageView, 0, 1);
        nameSelectGrid.add(playerOneName,  1, 1);
        nameSelectGrid.add(whiteImageView, 0, 2);
        nameSelectGrid.add(playerTwoName,  1, 2);

        nameSelectGrid.setPrefWidth(WIDTH / 5);
        nameSelectGrid.setMinWidth(WIDTH  / 5);
        nameSelectGrid.setVgap(5);
        nameSelectGrid.setHgap(20);
        nameSelectGrid.setStyle("-fx-alignment: center;" );

        Label boardSizeLabel = new Label("Board size");
        getChildren().addAll(boardSizeLabel, boardSizeBtns, nameSelectGrid, playAndBackBtns);
    }
}