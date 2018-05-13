package View;

import Controller.GameViewController;
import go.Main;
import go.Model.Point;
import go.Model.Utility.Pair;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

class BoardView extends Canvas {
    
    private GraphicsContext gc;

    BoardView(GameViewController gameViewController, int WIDTH, int HEIGHT) {
        super(WIDTH, HEIGHT);
        getStyleClass().add("boardView");
        gc = getGraphicsContext2D();

        setOnMouseClicked(event -> {
        	
            if(gameViewController.getCurrentPlayerAI()) return;

            Pair<Integer, Integer> position = pixelToBoardCoordinate(event.getX(), event.getY(), gameViewController);
            int row = position.getValue(), col = position.getKey();

            attemptToPlaceStone(gameViewController, row, col);
        });

        setOnMouseMoved(event -> {
            if(gameViewController.getCurrentPlayerAI()) return;

            Pair<Integer, Integer> position = pixelToBoardCoordinate(event.getX(), event.getY(), gameViewController);
            int row = position.getValue(), col = position.getKey();

            if(gameViewController.isValidMove(row, col)) {
                update(gameViewController, WIDTH, HEIGHT);                                                   // draw board on top of previously drawn valid move
                                                                            // draw valid move on top of board
                int xOffset = (int)(getWidth()  / 1.0 / gameViewController.getBoardSize());
                int yOffset = (int)(getHeight() / 1.0 / gameViewController.getBoardSize());
                gc.setStroke(new Color((double)180/255, (double)8/255, (double)19/255, 1.0)); gc.setLineWidth(5.0);

                if(row != 0)
                    gc.strokeLine(col * xOffset + xOffset / 2,  yOffset / 2, col * xOffset + xOffset / 2, row * yOffset - 1);
                if(row != gameViewController.getBoardSize() - 1)
                     gc.strokeLine(col * xOffset + xOffset / 2,  row * yOffset + yOffset, col * xOffset + xOffset / 2, gameViewController.getBoardSize() * yOffset - yOffset / 2 - 1);

                if(col != 0)
                    gc.strokeLine(xOffset / 2, row * yOffset + yOffset / 2, col * xOffset - 1, row * yOffset + yOffset / 2);
                if(col != gameViewController.getBoardSize() - 1)
                    gc.strokeLine(col * xOffset + xOffset, row * yOffset + yOffset / 2, gameViewController.getBoardSize() * xOffset - xOffset / 2 - 1, row * yOffset + yOffset / 2);

                if(gameViewController.getCurrentPlayerColor() == Color.WHITE)
                    drawCircle(row, col, new Color(1, 1, 1, 0.5), gameViewController);
                else
                    drawCircle(row, col, new Color(0, 0, 0, 0.5), gameViewController);
            }

        });

        setOnMouseExited(event -> update(gameViewController, WIDTH, HEIGHT));

        drawBackground(gameViewController, WIDTH, HEIGHT);
    }

    private void attemptToPlaceStone(GameViewController gameViewController, int row, int col) {
    	gameViewController.attemptToPlaceStone(gameViewController, row, col);
        
    }

    void update(GameViewController gameViewController, int width, int height) {
        drawBackground(gameViewController, width, height);

        Point[][] points = gameViewController.getBoard();

        for (int i = 0; i < points.length; i++)
            for (int j = 0; j < points.length; j++)
                if(points[i][j].getStone() != null)
                    drawCircle(i, j, points[i][j].getStone().getColor(), gameViewController);
    }

    private void drawCircle(double row, double col, Paint p, GameViewController gameViewController) {
        int xOffset = (int)getWidth()  / gameViewController.getBoardSize();
        int yOffset = (int)getHeight() / gameViewController.getBoardSize();

        gc.setFill(p);
        gc.fillOval(col * xOffset, row * yOffset, xOffset, yOffset);
    }

    private Pair<Integer, Integer> pixelToBoardCoordinate(double x , double y, GameViewController gameViewController) {
        x = (x / (getWidth()  / gameViewController.getBoardSize()));
        y = (y / (getHeight() / gameViewController.getBoardSize()));
        return new Pair<>((int)x, (int)y);
    }

    private void drawGridLines(int size, GameViewController gameViewController) {
        int xOffset = (int)(getWidth()  / 1.0 / size);
        int yOffset = (int)(getHeight() / 1.0 / size);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);

        for (int i = 0; i < size; i++) {
            gc.strokeLine(i * xOffset + xOffset / 2,  yOffset / 2, i * xOffset + xOffset / 2, gameViewController.getBoardSize() * yOffset - yOffset / 2 - 1);
            gc.strokeLine(xOffset / 2, i * yOffset + yOffset / 2, gameViewController.getBoardSize() * xOffset - xOffset / 2 - 1, i * yOffset + yOffset / 2);
        }
    }

    final Image woodImg = new Image(Main.class.getResourceAsStream("../images/wood1.jpg"));
    private void drawBackground(GameViewController gameViewController, int WIDTH, int HEIGHT) {
        gc.drawImage(woodImg, 0, 0, WIDTH, HEIGHT);
        drawGridLines(gameViewController.getBoardSize(), gameViewController);
    }
}