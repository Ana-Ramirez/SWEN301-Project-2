package View;

import Controller.GameViewController;
import View.BoardView;
import View.SidePanel;
import go.Model.Game;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class GamePlayScreen extends GridPane {

    private BoardView boardView;
    private SidePanel sidePanel;

    public GamePlayScreen(GameViewController gameViewController, int WIDTH, int HEIGHT) {
        boardView = new BoardView(gameViewController, WIDTH, HEIGHT);
        sidePanel = new SidePanel(gameViewController, WIDTH, HEIGHT);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(80);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(20);
        getColumnConstraints().addAll(col1, col2);

        add(boardView, 0, 0);
        add(sidePanel, 1, 0);
    }

    public void update(GameViewController gameViewController, int WIDTH, int HEIGHT) {
        boardView.update(gameViewController, WIDTH, HEIGHT);
        sidePanel.updateLabel(gameViewController);
    }

}