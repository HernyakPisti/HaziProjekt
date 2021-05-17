package hu.mavenprojekt.Components.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.mavenprojekt.Components.Model.Board;
import hu.mavenprojekt.Components.Model.Boardelements.Target;
import hu.mavenprojekt.Components.Model.Player;
import hu.mavenprojekt.Components.View.BoardGUI;
import hu.mavenprojekt.Components.View.GUI;
import hu.mavenprojekt.Utils.Utils;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;

/**
 * The {@link Class} that is responsible for controoling the game's
 * {@link Board} depending on the inputs.
 */
public final class BoardController {

    private GUI boardGUI = null;
    private Board board = null;
    private int N = 10;
    private int M = 10;

    /**
     * {@link java.lang.reflect.Constructor} for {@link BoardController}, used for
     * new games.
     *
     * @param boardGUI_ The {@link BoardGUI} that is currently in the game.
     * @param n         The {@link Board}'s N parameter, it's number of rows.
     * @param m         The {@link Board}'s M parameter, it's number of columns.
     */
    public BoardController(final GUI boardGUI_, final int n, final int m) {
        this.boardGUI = boardGUI_;
        this.N = n;
        this.M = m;
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
        this.N = board_.getN();
        this.M = board_.getM();
        this.board = board_;
    }

    /**
     * A {@link java.lang.reflect.Method} used for generating a randomized
     * {@link Board} from an existing one.
     *
     * @param b The existing {@link Board} that is getting randomized.
     * @return The rerandomized {@link Board}.
     */
    public Board generateDefaultBoard(final Board b) {
        b.generateDefault();
        this.board = b;
        return this.board;
    }

    /**
     * A {@link java.lang.reflect.Method} used for generating a new randomized
     * {@link Board}.
     *
     * @return The generated randomized {@link Board}.
     */
    public Board generateDefaultBoard() {
        this.board = new Board(this.N, this.M);
        this.board.generateDefault();
        return this.board;
    }

    /**
     * A {@link java.lang.reflect.Method} that is used for saving a current
     * gamestate to a JSON file.
     *
     * @param file A {@link File} the gamestate will be saved.
     */
    public void save(final File file) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(file, this.board);
        } catch (IOException e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        }
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
     * returning to {@link hu.mavenprojekt.Components.View.MainMenu} or
     * close the game.
     */

    public String move(final KeyEvent e) {
        return move2(e.getCode());
    }


    /**
     * A {@link java.lang.reflect.Method} used for handling the move inputs, in
     * regards of the {@link Board}. It is responsible to redraw the board after
     * move, and for checking if the player has won.
     *
     * @param e The {{@link KeyCode} is used for determine
     *          the direction the player wants to move.
     * @return A {@link Character} that signals for the {@link BoardGUI} the result
     * of the move input's handling. Can signal a direction, or a code for
     * returning to {@link hu.mavenprojekt.Components.View.MainMenu} or
     * close the game.
     */
    public String move2(final KeyCode e) {
        String h = null;
        String c = "";
        if (e == KeyCode.A) {
            c = "a";
        } else if (e == KeyCode.S) {
            c = "s";
        } else if (e == KeyCode.D) {
            c = "d";
        } else if (e == KeyCode.W) {
            c = "w";
        }

        if (((BoardGUI) this.boardGUI).getPlayerController().move(this.board, c)) {
            h = c;
        }
        ((BoardGUI) boardGUI).draw();
        ((BoardGUI) boardGUI).drawConsole();
        if (checkWin()) {
            Logger.info("Game finished");
            try {
                Integer result = Utils.showAlert();
                if (result == null) {
                    throw new NullPointerException();
                } else if (result == 0) {
                    Logger.info("New game started");
                    reset(true);
                    h = "";
                } else if (result == 1) {
                    Logger.info("Back to main menu");
                    h = "mm";
                } else if (result == -1) {
                    Logger.info("Game closed");
                    h = "cl";
                }
            } catch (Exception ex) {
                Logger.error(ex.getMessage());
                System.out.println(ex.getStackTrace());
            }
        }

        return h;

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
     * A {@link java.lang.reflect.Method} to reset the game. Either to the current
     * game's starting or a new randomized game.
     *
     * @param newgame The {@link Boolean} parameter that datermines if the game
     *                should be rerandomized or not.
     */
    public void reset(final boolean newgame) {
        this.board.getPlayer().reset();
        if (newgame) {
            this.board.getPlayer().reset(newgame, this.board.getN(), this.board.getM());
            generateDefaultBoard(this.board);
        }
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
}
