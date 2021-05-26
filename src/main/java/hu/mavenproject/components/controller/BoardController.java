package hu.mavenproject.components.controller;

import hu.mavenproject.components.model.Board;
import hu.mavenproject.components.model.Board.DIRECTION;
import hu.mavenproject.components.model.boardelements.Target;
import hu.mavenproject.components.model.Player;
import hu.mavenproject.components.view.BoardGUI;
import hu.mavenproject.components.view.GUI;
import hu.mavenproject.utils.Utils;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.tinylog.Logger;

import java.net.URL;

/**
 * The {@link Class} that is responsible for controoling the game's
 * {@link Board} depending on the inputs.
 */
public final class BoardController {

    private GUI boardGUI = null;
    private Board board = null;

    /**
     * {@link java.lang.reflect.Constructor} for {@link BoardController}, used for
     * new games.
     *
     * @param boardGUI_ The {@link BoardGUI} that is currently in the game.
     */
    public BoardController(final GUI boardGUI_) {
        this.boardGUI = boardGUI_;
    }

    /**
     * {@link java.lang.reflect.Constructor} for {@link BoardController}, used for
     * existing {@link Board} (loading in saved games).
     *
     * @param boardGUI_ The {@link BoardGUI} that is currently is in the game.
     * @param board_    The current game's {@link Board}
     */
    public BoardController(final GUI boardGUI_, final Board board_) {
        this.boardGUI = boardGUI_;
        this.board = board_;
    }

    /**
     * A {@link java.lang.reflect.Method} used for generating a new randomized
     * {@link Board}.
     *
     * @return The generated randomized {@link Board}.
     */
    public Board generateDefaultBoard() {
        if (this.board == null) {
            this.board = new Board();
        }
        this.board.generateDefault();
        return this.board;
    }

    /**
     * A {@link java.lang.reflect.Method} used for handling the move inputs, in
     * regards of the {@link Board}. It is responsible to redraw the board after
     * move, and for checking if the player has won.
     *
     * @param e The {@link KeyEvent} that's {@link KeyCode} is used for determine
     *          the direction the player wants to move.
     * @return A {@link Character} that signals for the {@link BoardGUI} the result
     * of the move input's handling. Can signal a direction, or a code for
     * returning to {@link hu.mavenproject.components.view.MainMenu} or
     * close the game.
     */
    public int move(final KeyEvent e) {

        int res = -1;
        int direction = -1;
        if (e.getCode() == KeyCode.A) {
            direction = DIRECTION.LEFT;
        } else if (e.getCode() == KeyCode.S) {
            direction = DIRECTION.DOWN;
        } else if (e.getCode() == KeyCode.D) {
            direction = DIRECTION.RIGHT;
        } else if (e.getCode() == KeyCode.W) {
            direction = DIRECTION.UP;
        }

        if (((BoardGUI) this.boardGUI).getPlayerController().move(this.board, direction)) {
            res = direction;
        }
        ((BoardGUI) boardGUI).draw();
        ((BoardGUI) boardGUI).drawConsole();
        if (checkWin()) {
            Logger.info("Game finished");
            try {
                Integer result = Utils.showAlert();
                URL file = getClass().getResource("/results.json");
                Utils.writeResults(file, board.getPlayer());
                if (result == null) {
                    throw new NullPointerException();
                } else if (result == 0) {
                    Logger.info("New game started");
                    reset();
                    res = 5;
                } else if (result == 1) {
                    Logger.info("Back to main menu");
                    res = 6;
                } else if (result == -1) {
                    Logger.info("Game closed");
                    res = 7;
                }
            } catch (Exception ex) {
                Logger.error(ex.getMessage());
                System.out.println(ex.getStackTrace());
            }
        }

        return res;

    }

    /**
     * A {@link java.lang.reflect.Method} to reset(the current game's starting
     * state) the game.
     */
    public void reset() {
        this.board.getPlayer().reset();
        ((BoardGUI) this.boardGUI).draw();
        ((BoardGUI) this.boardGUI).drawConsole();
    }

    /**
     * The {@link java.lang.reflect.Method} that is responsible for checking if the
     * player has won the game.
     *
     * @return A {@link Boolean}. True if the player has won, false otherwise.
     */
    private boolean checkWin() {
        return this.board.getMap()[this.board.getPlayer().getY()][this.board.getPlayer().getX()] instanceof Target;
    }

    /**
     * A {@link java.lang.reflect.Method} which returns the current game's
     * {@link Player} object.
     *
     * @return The game's {@link Player} object.
     */
    public Player getPlayer() {
        return this.board.getPlayer();
    }

    /**
     * A {@link java.lang.reflect.Method} that returns the current game's
     * {@link Board}.
     *
     * @return The current game's {@link Board}.
     */
    public Board getBoard() {
        return this.board;
    }
}
