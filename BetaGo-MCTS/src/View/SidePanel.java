package View;

import Controller.GameViewController;
import go.Main;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

class SidePanel extends VBox {
    private Label label;
    public static int HEIGHT = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1.20);
    public static int WIDTH = HEIGHT;
    int imageWidth = (int)(WIDTH * 0.20);

    ImageView whiteImageView = new ImageView( new Image(Main.class.getResourceAsStream("../images/white.png"), imageWidth, imageWidth, true, true) );
    ImageView blackImageView = new ImageView( new Image(Main.class.getResourceAsStream("../images/black.png"), imageWidth, imageWidth, true, true) );
    ImageView currPlayerImage = new ImageView(blackImageView.getImage());

    SidePanel(GameViewController gameViewController, int WIDTH, int HEIGHT) {
    	
        getStyleClass().add("sidePanel");

        Button homeScreenBtn = new Button("Home Screen");
        homeScreenBtn.setOnAction(e -> gameViewController.displayHomeScreen());

        Button exitBtn = new Button("Quit");
        exitBtn.setOnAction(e -> System.exit(0));

        Button passTurnBtn = new Button("Pass turn");
        passTurnBtn.setOnAction(e -> {
            gameViewController.passTurn();
            gameViewController.updateGamePlayScreen(gameViewController, WIDTH, HEIGHT);
        });

        Button newGameBtn = new Button("Restart");
        newGameBtn.setOnAction(e -> {
            gameViewController.restartGame();
            gameViewController.updateGamePlayScreen(gameViewController, WIDTH, HEIGHT);
        });

        label = new Label("");
        label.getStyleClass().add("sidePanelLabel");
        label.setPrefWidth(WIDTH * 0.20);
        updateLabel(gameViewController);

        for(Button button : new Button[]{passTurnBtn, newGameBtn, homeScreenBtn, exitBtn}) {
            button.setMinWidth(WIDTH * 0.20);
            button.setMinHeight(HEIGHT * 0.06);
            button.getStyleClass().add("sidePanelButton");
        }

        getChildren().addAll(label, currPlayerImage, passTurnBtn, newGameBtn, homeScreenBtn, exitBtn);
    }

    void updateLabel(GameViewController gameViewController) {
        gameViewController.updateLabel(label, currPlayerImage, blackImageView, whiteImageView);
    }
}