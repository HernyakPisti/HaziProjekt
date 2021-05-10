package hu.mavenprojekt.Components.View;

import hu.mavenprojekt.Components.Controller.BoardController;
import hu.mavenprojekt.Components.Controller.PlayerController;
import hu.mavenprojekt.Components.Model.Board;
import hu.mavenprojekt.Components.Model.Player;
import hu.mavenprojekt.Components.Model.Boardelements.Ground;
import hu.mavenprojekt.Components.Model.Boardelements.Start;
import hu.mavenprojekt.Components.Model.Boardelements.Target;
import hu.mavenprojekt.Components.Model.Boardelements.Wall;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public final class BoardGUI implements GUI {
    private BoardController boardController = null;
    private PlayerController playerController = null;
    private int screenWidth;
    private int screenHeight;
    private GridPane root;
    private GUI parent;

    public BoardGUI(GUI p) {
        this.boardController = new BoardController(this, 10, 10);
        this.playerController = new PlayerController(this);
        this.screenWidth = 640;
        this.screenHeight = 640;
        this.parent = p;
        construct();
    }

    public BoardGUI(GUI p, int screenWidth, int screenHeight, int N, int M) {
        this.boardController = new BoardController(this, N, M);
        this.playerController = new PlayerController(this);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.parent = p;
        construct();
    }

    public BoardGUI(GUI p, int screenWidth, int screenHeight, Board board) {
        this.boardController = new BoardController(this, board);
        this.playerController = new PlayerController(this);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.parent = p;
        construct();
    }

    public void construct() {
        if (this.boardController.getBoard() == null)
            this.boardController.generateDefaultBoard();
        else
            this.boardController.getBoard().resetMap();

        this.root = new GridPane();
        this.root.setPadding(new Insets(10));
        draw();
        drawConsole();

    }

    public void draw() {
        this.root.getChildren().clear();
        for (int i = 0; i < this.boardController.getBoard().getN(); i++) {
            for (int j = 0; j < this.boardController.getBoard().getM(); j++) {
                StackPane stackPane = new StackPane();
                Rectangle t = new Rectangle((double) this.screenWidth / this.boardController.getBoard().getM(),
                        (double) this.screenHeight / this.boardController.getBoard().getN());
                t.setStroke(Color.web("#000000"));
                if (this.boardController.getBoard().getTileAt(i, j) instanceof Wall) {
                    t.setFill(Color.web("#000000"));
                    t.setStroke(Color.web("#FFFFFF"));
                } else if (this.boardController.getBoard().getTileAt(i, j) instanceof Ground) {
                    t.setFill(Color.web("#F4F4F4"));
                } else if (this.boardController.getBoard().getTileAt(i, j) instanceof Start) {
                    t.setFill(Color.web("#00FF00"));
                } else if (this.boardController.getBoard().getTileAt(i, j) instanceof Target) {
                    t.setFill(Color.web("#FFF000"));
                }

                t.setStrokeWidth(1);
                stackPane.getChildren().add(t);
                Player p = this.boardController.getBoard().getPlayer();
                if (i == p.getY() && j == p.getX()) {
                    Circle c = new Circle(
                            (double) this.screenWidth / (2 * this.boardController.getBoard().getN()),
                            (double) this.screenHeight / (2 * this.boardController.getBoard().getM()),
                            (double) this.screenWidth / (2 * this.boardController.getBoard().getM()) - 10);
                    c.setFill(Color.web("#0000FF"));
                    c.setStroke(Color.web("#000000"));
                    c.setStrokeWidth(1);
                    stackPane.getChildren().add(c);
                    Line l = new Line();
                    l.setStartX(c.getCenterX());
                    l.setStartY(c.getCenterY());
                    switch (p.getCurrentHeading()) {
                        case "a":
                            l.setEndX(c.getCenterX() + c.getRadius());
                            l.setEndY(c.getCenterY());
                            break;
                        case "s":
                            l.setEndX(c.getCenterX());
                            l.setEndY(c.getCenterY() + c.getRadius());
                            break;
                        case "d":
                            l.setEndX(c.getCenterX() - c.getRadius());
                            l.setEndY(c.getCenterY());
                            break;
                        case "w":
                            l.setEndX(c.getCenterX());
                            l.setEndY(c.getCenterY() - c.getRadius());
                            break;
                        default:
                            l.setEndX(l.getStartX());
                            l.setEndY(l.getStartY());
                            break;
                    }
                    stackPane.getChildren().add(l);

                }
                this.root.add(stackPane, j, i);
            }
        }
    }

    public void drawConsole() {
        String str = "";
        for (int i = 0; i < this.boardController.getBoard().getN(); i++) {
            for (int j = 0; j < this.boardController.getBoard().getM(); j++) {
                if (j == this.boardController.getBoard().getPlayer().getX()
                        && i == this.boardController.getBoard().getPlayer().getY()) {
                    str += this.boardController.getBoard().getPlayer();
                } else {
                    str += this.boardController.getBoard().getTileAt(i, j);
                }

            }
            str += "\n";
        }
        System.out.println(str);
    }

    public BoardController getBoardController() {
        return this.boardController;
    }

    public PlayerController getPlayerController() {
        return this.playerController;
    }

    public GridPane getRoot() {
        return this.root;
    }

}
