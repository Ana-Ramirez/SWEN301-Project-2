package View;

import Controller.GameViewController;
import go.Model.Game;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

class BoardSizeButtons extends HBox {
    BoardSizeButtons(GameViewController gameViewController, int WIDTH, int HEIGHT) {
        getStyleClass().add("playBackButtons");

        final int[] boardSizes = new int[]{9, 13, 19};
        Button[] buttons = new Button[3];

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button(String.valueOf(boardSizes[i]));
            buttons[i].getStyleClass().add("boardSizeButtons");
            buttons[i].setPrefWidth(WIDTH / 5);
            buttons[i].setMinWidth(WIDTH / 5);

            int finalI = i;
            buttons[i].setOnAction((ActionEvent e) -> {
                for(Button btn : buttons) btn.setStyle("-fx-base: #666666;");
                buttons[finalI].setStyle("-fx-base: #00802b;");

                gameViewController.setBoardSize(boardSizes, finalI);
                
                gameViewController.updateGamePlayScreen(gameViewController, WIDTH, HEIGHT);
            });

            getChildren().add(buttons[i]);
        }

        buttons[0].setStyle("-fx-base: #00802b;");
    }
}