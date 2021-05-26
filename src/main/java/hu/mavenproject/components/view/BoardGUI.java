package hu.mavenproject.components.view;

import hu.mavenproject.components.controller.BoardController;
import hu.mavenproject.components.controller.PlayerController;
import hu.mavenproject.components.model.Board;
import hu.mavenproject.components.model.boardelements.Ground;
import hu.mavenproject.components.model.boardelements.Start;
import hu.mavenproject.components.model.boardelements.Target;
import hu.mavenproject.components.model.boardelements.Wall;
import hu.mavenproject.components.model.Player;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.tinylog.Logger;

/**
 * A {@link Class} that represents the GUI of the gamearea.
 */
public final class BoardGUI implements GUI {

    private BoardController boardController = null;
    private PlayerController playerController = null;
    private int screenWidth;
    private int screenHeight;
    private GridPane root;
    private final GUI parent;

    /**
     * {@link java.lang.reflect.Constructor} for {@link BoardGUI}.Defaults
     * everything.
     *
     * @param p A {@link GUI} object, this object's parent.
     */
    public BoardGUI(final GUI p) {
        this.boardController = new BoardController(this);
        this.playerController = new PlayerController(this);
        this.screenWidth = 640;
        this.screenHeight = 640;
        this.parent = p;
        construct();
    }

    /**
     * {@link java.lang.reflect.Constructor} for {@link BoardGUI}. Uses an existing
     * {@link Board} (loading in saved games), and creates a window with the given
     * parameters.
     *
     * @param p             A {@link GUI} object, this object's parent.
     * @param screenWidth_  An {@link Integer}, the game's window's width. Must be
     *                      positive!
     * @param screenHeight_ An {@link Integer}, the game's window's height. Must be
     *                      positive!
     * @param board_        The existing {@link Board}.
     * @throws IllegalArgumentException If screen parameters are 0 or negative.
     */
    public BoardGUI(final GUI p, final int screenWidth_, final int screenHeight_, final Board board_) {
        if (screenWidth_ > 0 && screenHeight_ > 0) {
            this.boardController = new BoardController(this, board_);
            this.playerController = new PlayerController(this);
            this.screenWidth = screenWidth_;
            this.screenHeight = screenHeight_;
            this.parent = p;
            construct();
        } else {
            Logger.error("\"screenWidth\" and \"screenHeight\" must be greater than 0. Got screenWidth: " + screenWidth_
                    + ", screenHeight: " + screenHeight_);
            throw new IllegalArgumentException(
                    "\"screenWidth\" and \"screenHeight\" must be greater than 0. Got screenWidth: " + screenWidth_
                            + ", screenHeight: " + screenHeight_);
        }
    }

    /**
     * A {@link java.lang.reflect.Method} that is used to construct the GUI layout
     * of the {@link Board}.
     */
    public void construct() {
        if (this.boardController.getBoard() == null) {
            this.boardController.generateDefaultBoard();
        } else {
            this.boardController.getBoard().resetMap();
            this.screenWidth = this.boardController.getBoard().getM() * 40;
            this.screenHeight = this.boardController.getBoard().getN() * 40;
        }

        this.root = new GridPane();
        this.root.setPadding(new Insets(10));
        draw();
        drawConsole();

    }

    /**
     * The {@link java.lang.reflect.Method} that is responsible for drawing th GUI.
     */
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
                    t.setFill(Color.web("#FF0000"));
                } else if (this.boardController.getBoard().getTileAt(i, j) instanceof Target) {
                    t.setFill(Color.web("#FF0000"));
                }

                t.setStrokeWidth(1);
                stackPane.getChildren().add(t);
                Player p = this.boardController.getBoard().getPlayer();
                if (i == p.getY() && j == p.getX()) {
                    Circle c = new Circle((double) this.screenWidth / (2 * this.boardController.getBoard().getN()),
                            (double) this.screenHeight / (2 * this.boardController.getBoard().getM()),
                            (double) this.screenWidth / (2 * this.boardController.getBoard().getM()) - 10);
                    c.setFill(Color.web("#0000FF"));
                    c.setStroke(Color.web("#000000"));
                    c.setStrokeWidth(1);
                    stackPane.getChildren().add(c);

                }
                this.root.add(stackPane, j, i);
            }
        }
    }

    /**
     * The {@link java.lang.reflect.Method} that is responsible for printing put the
     * game to the console.
     */
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

    /**
     * The {@link java.lang.reflect.Method} that returns the current {@link Board}'s
     * controller.
     *
     * @return A {@link BoardController}, the current game's
     * {@link BoardController}.
     */
    public BoardController getBoardController() {
        return this.boardController;
    }

    /**
     * The {@link java.lang.reflect.Method} that returns the current
     * {@link Player}'s controller.
     *
     * @return A {@link PlayerController}, the current game's
     * {@link PlayerController}.
     */
    public PlayerController getPlayerController() {
        return this.playerController;
    }

    /**
     * The {@link java.lang.reflect.Method} that returns this GUI elements root
     * element, so it can be used as a root for a {@link javafx.scene.Scene}.
     *
     * @return A {@link GridPane}, this GUI element's root.
     */
    public GridPane getRoot() {
        return this.root;
    }

}
