package View;

import Controller.GameViewController;
import View.HumanVsComputerScreen;
import View.HumanVsHumanScreen;
import go.Model.Game;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomeScreen extends VBox {
	
    public HomeScreen(GameViewController gameViewController, int WIDTH, int HEIGHT) {
        getStyleClass().add("homeScreen");
        
        Label label = new Label("BetaGo");
        label.getStyleClass().add("homeScreenLabel");

        Button newGameBtn = new Button("New Game");
        newGameBtn.setOnAction(e -> {
            gameViewController.displayGamePlayScreen(gameViewController, WIDTH, HEIGHT);
        });

        Button exitBtn = new Button("Quit");
        exitBtn.setOnAction(e -> System.exit(0));


        Button vsHumanBtn = new Button("Human vs Human");
        vsHumanBtn.setOnAction(e -> {
            gameViewController.displayScreen(new HumanVsHumanScreen(gameViewController, WIDTH, HEIGHT));
        });
        vsHumanBtn.setMinWidth(WIDTH * .5);

        Button vsComputerBtn = new Button("Human vs Computer");
        vsComputerBtn.setOnAction(e -> {
            gameViewController.isVsComputer();
            gameViewController.displayScreen(new HumanVsComputerScreen(gameViewController, WIDTH, HEIGHT));
        });
        vsComputerBtn.setMinWidth(WIDTH * .5);

        vsHumanBtn.setStyle("-fx-border-color: black;");
        vsComputerBtn.setStyle("-fx-border-color: transparent black black black;");

        for(Button button : new Button[]{newGameBtn, exitBtn}) {
            button.getStyleClass().add("homeScreenButton");
            button.setMinWidth(WIDTH * .5);
        }

        VBox newGameOptions = new VBox();
        newGameOptions.setMaxWidth(WIDTH * .5);
        newGameOptions.getStyleClass().add("newGameOptions");
        newGameOptions.getChildren().addAll(vsHumanBtn, vsComputerBtn);

        newGameBtn.setOnMouseEntered(e -> {
            this.getChildren().removeAll(getChildren());
            this.getChildren().add(label);
            this.getChildren().add(newGameOptions);
            this.getChildren().add(exitBtn);
        });

        newGameOptions.setOnMouseExited(e -> {
            this.getChildren().removeAll(getChildren());
            this.getChildren().add(label);
            this.getChildren().add(newGameBtn);
            this.getChildren().add(exitBtn);
        });

        getChildren().addAll(label, newGameBtn, exitBtn);
    }
}