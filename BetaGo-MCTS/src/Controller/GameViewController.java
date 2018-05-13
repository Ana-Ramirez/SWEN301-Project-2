package Controller;

import View.GameOverPopUp;
import View.GamePlayScreen;
import View.HomeScreen;
import go.Model.Game;
import go.Model.Point;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameViewController extends StackPane {

    private HomeScreen homeScreen;
    private GamePlayScreen gamePlayScreen;
    private GameOverPopUp gameOverPopUp;
    
    Game game;

    public GameViewController(Stage primaryStage, int WIDTH, int HEIGHT) {
  
    	game = new Game();
    	homeScreen = new HomeScreen(this, WIDTH, HEIGHT);
        gamePlayScreen = new GamePlayScreen(this, WIDTH, HEIGHT);
        gameOverPopUp = new GameOverPopUp(primaryStage, this, WIDTH, HEIGHT);
    }

    public void isVsComputer() {
    	game.getPlayers()[1].enableAI();
    }
    
    public void enable0AiDisable1AI(ImageView whiteImageView) {
    	game.getPlayers()[0].enableAI();
        game.getPlayers()[1].disableAI();
    }
    
    public void enable1AiDisable0AI(ImageView blackImageView) {
        game.getPlayers()[0].disableAI();
        game.getPlayers()[1].enableAI();
	}
    
    public void setBoardSize(int[] boardSizes, int finalI) {
    	game.setBoardSize(boardSizes[finalI]);
    }
    
    public int getBoardSize() {
    	return game.getBoardSize();
    }
    
    public void attemptToPlaceStone(GameViewController gameViewController, int row, int col) {
    	if(game.isValidMove(row, col))
            game.playerMove(row, col);
        	updateGamePlayScreen(gameViewController, row, col);
    }
    
    public boolean getCurrentPlayerAI() {
    	return game.getCurrentPlayer().isUsingAI();
    }
    
    public Color getCurrentPlayerColor() {
    	return game.getCurrentPlayer().getColor();
    }
    
    public boolean isValidMove(int row, int col) {
    	return game.isValidMove(row, col);
    }
    
    public Point[][] getBoard() {
    	return game.getBoard().getPoints();
    }
    
    public void setMoveTime(long l) {
		game.setMoveTime( (long)(l));
	}
    
    public void restartGame() {
    	game.restartGame();
    }
    
    public void passTurn() {
    	game.passTurn();
    }
    
    public void updateLabel(Label label, ImageView currPlayerImage, ImageView blackImageView, ImageView whiteImageView) {
    	label.setText(game.getCurrentPlayer().getName() + "'s turn\n");

        if (game.getCurrentPlayer().getColor() == Color.BLACK)
            currPlayerImage.setImage(blackImageView.getImage());
        else
            currPlayerImage.setImage(whiteImageView.getImage());
    }
    
    public void displayHomeScreen() {
        displayScreen(homeScreen);
    }

    public void displayGamePlayScreen(GameViewController gameViewController, int WIDTH, int HEIGHT) {
        updateGamePlayScreen(gameViewController, WIDTH, HEIGHT);
        displayScreen(gamePlayScreen);
    }

    public void updateGamePlayScreen(GameViewController gameViewController, int WIDTH, int HEIGHT) {
        gamePlayScreen.update(gameViewController, WIDTH, HEIGHT);
        if(game.isGameOver()) {
        	gameOverPopUp.display(this);
        }
    }

    public void displayScreen(Node screen) {
        gameOverPopUp.hide();
        this.getChildren().removeAll(this.getChildren());
        this.getChildren().add(screen);
    }

    public void displayGameOverPopUp(Label scoreLabel, Stage dialog) {
    	
        String winner = game.getPlayers()[0].getScore() > game.getPlayers()[1].getScore()
                        ? game.getPlayers()[0].getName() : game.getPlayers()[1].getName();

        scoreLabel.setText(winner + " wins!\n" +
                           game.getPlayers()[0].getName() + ": " + game.getPlayers()[0].getScore() + "\n" +
                           game.getPlayers()[1].getName() + ": " + game.getPlayers()[1].getScore());
        dialog.show();
    	
        gameOverPopUp.display(this);
    }

	public void setPlayerOneName(String name) {
		game.setPlayerOneName(name);
	}
	
	public void setPlayerTwoName(String name) {
		game.setPlayerTwoName(name);
	}
	
}