package hu.mavenprojekt.Components.Controller;

import hu.mavenprojekt.Components.Model.Board;
import hu.mavenprojekt.Components.Model.Boardelements.Wall;
import hu.mavenprojekt.Components.View.GUI;
import org.tinylog.Logger;

/**
 * The {@link Class} that is responsible for controlling the game's
 * {@link hu.mavenprojekt.Components.Model.Player}.
 */
public final class PlayerController {

    private GUI boardGUI = null;

    /**
     * {@link java.lang.reflect.Constructor} for the {@link PlayerController} class.
     *
     * @param gui A {@link hu.mavenprojekt.Components.View.BoardGUI} parameter that
     *            is currently in the game.
     */
    public PlayerController(final GUI gui) {
        this.boardGUI = gui;
    }

    /**
     * The {@link java.lang.reflect.Method} that is responsible for moving the
     * {@link hu.mavenprojekt.Components.Model.Player} depending on the inputs.
     *
     * @param board The current game's {@link Board}.
     * @param c     The {@link Character} that determins where the player wants to
     *              move.
     * @return A {@link Boolean} that represents the success of the move.
     */
    public boolean move(final Board board, final String c) {
        boolean flag = false;
        int x = 0, y = 0;
        switch (c) {
            case "a":
                x = -1;
                break;
            case "s":
                y = 1;
                break;
            case "d":
                x = 1;
                break;
            case "w":
                y = -1;
                break;
            default:
                break;
        }
        if (checkInput(board, x, y, c)) {
            board.getPlayer().move(x, y, c);
            Logger.info("Moved to X: " + (board.getPlayer().getX() + 1) + ", Y: " + (board.getPlayer().getY() + 1));
            flag = true;
        }
        if (!flag) {
            Logger.info("Can't move to  X: " + (board.getPlayer().getX() + 1 + x) + ", Y: "
                    + (board.getPlayer().getY() + 1 + y));
        }
        return flag;

    }

    /**
     * A {@link java.lang.reflect.Method} for checking if the
     * {@link hu.mavenprojekt.Components.Model.Player} object can move where the
     * inputs pointing.
     *
     * @param board The game's {@link Board}.
     * @param x     The amount of units the player wants to go on the X-axis. (Can
     *              be negative!).
     * @param y     The amount of units the player wants to go on the Y-axis. (Can
     *              be negative!).
     * @param c     The character that represents the desired direction.
     * @return A {@link Boolean} depending on the success of the move.
     */
    private boolean checkInput(final Board board, final int x, final int y, final String c) {
        boolean flag = false;

        if (!(board.getMap()[board.getPlayer().getY() + y][board.getPlayer().getX() + x] instanceof Wall)) {

            if (board.getPlayer().getCurrentHeading().equals(c.trim())) {
                flag = true;
            } else {
                switch (board.getPlayer().getCurrentHeading()) {
                    case "":
                        flag = true;
                        break;
                    case "w":
                        if (c.equals("d")) {
                            flag = true;
                        }
                        break;
                    case "a":
                        if (c.equals("w")) {
                            flag = true;
                        }
                        break;
                    case "s":
                        if (c.equals("a")) {
                            flag = true;
                        }
                        break;
                    case "d":
                        if (c.equals("s")) {
                            flag = true;
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        return flag;

    }

}
