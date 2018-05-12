package View;

import Controller.GameViewController;
import View.BoardSizeButtons;
import go.Main;
import go.Model.Game;
import View.PlayAndBackButtons;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

class HumanVsComputerScreen extends VBox {
    HumanVsComputerScreen(GameViewController gameViewController, int WIDTH, int HEIGHT) {
        getStyleClass().add("humanVsHumanScreen");

        PlayAndBackButtons playAndBackBtns = new PlayAndBackButtons(gameViewController, HEIGHT, HEIGHT);
        BoardSizeButtons boardSizeBtns = new BoardSizeButtons(gameViewController, HEIGHT, HEIGHT);

        Button easyBtn = new Button("Easy");
        Button hardBtn = new Button("Hard");
        easyBtn.setStyle("-fx-border-color: black; -fx-base: #00802b;");
        hardBtn.setStyle("-fx-border-color: black;");

        easyBtn.setOnAction(event -> {
            hardBtn.setStyle("fx-base: #666666;");
            easyBtn.setStyle("-fx-base: #00802b;");
        });

        hardBtn.setOnAction(event -> {
            easyBtn.setStyle("fx-base: #666666;");
            hardBtn.setStyle("-fx-base: #00802b;");
        });

        HBox difficultyBtns = new HBox();
        difficultyBtns.setStyle("-fx-alignment: center;");
        difficultyBtns.setSpacing(20.0);
        difficultyBtns.getChildren().addAll(easyBtn, hardBtn);

        int imageWidth = (int)(WIDTH * 0.25);
        ImageView whiteImageView = new ImageView( new Image(Main.class.getResourceAsStream("../images/white.png"), imageWidth, imageWidth, true, true) );
        ImageView blackImageView = new ImageView( new Image(Main.class.getResourceAsStream("../images/black.png"), imageWidth, imageWidth, true, true) );
        whiteImageView.setOpacity(0.45);

        whiteImageView.setOnMouseClicked(event -> {
        	gameViewController.enable0AiDisable1AI(whiteImageView);
        	
            whiteImageView.setOpacity(1.0);
            blackImageView.setOpacity(0.45);
        });
        blackImageView.setOnMouseClicked(event -> {
        	gameViewController.enable1AiDisable0AI(blackImageView);

            whiteImageView.setOpacity(0.45);
            blackImageView.setOpacity(1.0);
        });

        HBox stoneBtns = new HBox();
        stoneBtns.setStyle("-fx-alignment: center;");
        stoneBtns.setSpacing(20.0);
        stoneBtns.getChildren().addAll(blackImageView, whiteImageView);

        VBox diffLabels = new VBox();
        diffLabels.setStyle("-fx-alignment: center; -fx-spacing: 10;");

        Label difficultyLabel = new Label("Difficulty");
        Label difficultyLabel1 = new Label("Computer thinks " + (int)(25/3.333) + " seconds per move");
        difficultyLabel1.setStyle(" -fx-font-size: 15pt; -fx-spacing: 30;");

        Slider slider = new Slider(0, 100, 25);
        slider.setMaxWidth(WIDTH * 0.85); slider.setMinWidth(WIDTH * 0.85);
        slider.setShowTickMarks(false);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(false);
        slider.setStyle("-fx-alignment: center; -fx-control-inner-background: #666666; -fx-color:#549534; -fx-base: #549534; ");
        slider.valueProperty().addListener((ChangeListener) (arg0, arg1, arg2) -> {
            difficultyLabel1.setText("Computer thinks " + (int)(slider.getValue()/3.333) + " second" + ((int)slider.getValue() == 1 ? "" : "s") +  " per move");
            gameViewController.setMoveTime( (long)(slider.getValue()/3.333)  );
        });

        slider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                if (n == slider.getMin())   return "Easy";
                if (n == Math.ceil((slider.getMax() + slider.getMin()) / 2))    return "Medium";
                if (n == slider.getMax())   return "Hard";
                return "";
            }
            @Override
            public Double fromString(String s) {
                switch (s) {
                    case "Easy":   return 0d;
                    case "Medium": return 1d;
                    case "Hard":   return 3d;
                    default:       return 3d;
                }
            }
        });

        slider.setScaleY(.5);

        diffLabels.getChildren().addAll(difficultyLabel, difficultyLabel1, slider, stoneBtns);

        Label boardSizeLabel = new Label("Board size");

        getChildren().addAll(boardSizeLabel, boardSizeBtns, diffLabels, playAndBackBtns);
    }

}