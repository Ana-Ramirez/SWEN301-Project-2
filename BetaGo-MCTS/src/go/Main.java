package go;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import Controller.GameViewController;
import View.HomeScreen;

public class Main extends Application {

    public static int HEIGHT = (int) (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1.20);
    public static int WIDTH = HEIGHT;

    public GameViewController gameViewController;
    public HomeScreen homeScreen;
    
    public static void main(String[] args) {
    	System.out.println("ANA RAMIREZ RUNNING THIS PROGRAM");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        gameViewController = new GameViewController(primaryStage, WIDTH, HEIGHT);
        gameViewController.getStylesheets().add("go/stylesheet.css");
        gameViewController.setPrefSize(WIDTH, HEIGHT);
        gameViewController.displayHomeScreen();
        
        primaryStage.setTitle("Ana Ramirez BetaGo");
        primaryStage.setScene( new Scene(gameViewController, WIDTH / 0.80, HEIGHT) );
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}