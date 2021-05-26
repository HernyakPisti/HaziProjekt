package hu.mavenproject.components.controller;

import hu.mavenproject.components.model.Board;
import hu.mavenproject.components.model.Board.DIRECTION;
import hu.mavenproject.components.model.boardelements.Wall;
import hu.mavenproject.components.view.GUI;
import org.tinylog.Logger;

/**
 * The {@link Class} that is responsible for controlling the game's
 * {@link hu.mavenproject.components.model.Player}.
 */
public final class PlayerController {

    private GUI boardGUI = null;

    /**
     * {@link java.lang.reflect.Constructor} for the {@link PlayerController} class.
     *
     * @param gui A {@link hu.mavenproject.components.view.BoardGUI} parameter that
     *            is currently in the game.
     */
    public PlayerController(final GUI gui) {
        this.boardGUI = gui;
    }

    /**
     * The {@link java.lang.reflect.Method} that is responsible for moving the
     * {@link hu.mavenproject.components.model.Player} depending on the inputs.
     *
     * @param board The current game's {@link Board}.
     * @param dir   The {@link Integer} that determins where the player wants to
     *              move.
     * @return A {@link Boolean} that represents the success of the move.
     */
    public boolean move(final Board board, final int dir) {
        boolean flag = false;
        int x = 0, y = 0;
        switch (dir) {
            case DIRECTION.LEFT:
                x = -1;
                break;
            case DIRECTION.DOWN:
                y = 1;
                break;
            case DIRECTION.RIGHT:
                x = 1;
                break;
            case DIRECTION.UP:
                y = -1;
                break;
            default:
                break;
        }
        if (checkInput(board, x, y, dir)) {
            board.getPlayer().move(x, y, dir);
            Logger.info("Moved to X: " + (board.getPlayer().getX() + 1) + ", Y: " + (board.getPlayer().getY() + 1));
            flag = true;
        }
        if (!flag) {
            Logger.info("Can't move to  X: " + (board.getPlayer().getX() + 1 + x) + ", Y: " + (board.getPlayer().getY() + 1 + y));
        }
        return flag;

    }

    /**
     * A {@link java.lang.reflect.Method} for checking if the
     * {@link hu.mavenproject.components.model.Player} object can move where the
     * inputs pointing.
     *
     * @param board The game's {@link Board}.
     * @param x     The amount of units the player wants to go on the X-axis. (Can
     *              be negative!).
     * @param y     The amount of units the player wants to go on the Y-axis. (Can
     *              be negative!).
     * @param dir   The {@link Integer} that represents the desired direction.
     * @return A {@link Boolean} depending on the success of the move.
     */
    private boolean checkInput(final Board board, final int x, final int y, final int dir) {
        boolean flag = false;

        if (!(board.getMap()[board.getPlayer().getY() + y][board.getPlayer().getX() + x] instanceof Wall)) {

            if (board.getPlayer().getCurrentDirection() == dir) {
                flag = true;
            } else {
                switch (board.getPlayer().getCurrentDirection()) {
                    case DIRECTION.NONE:
                        flag = true;
                        break;
                    case DIRECTION.UP:
                        if (dir == DIRECTION.RIGHT) {
                            flag = true;
                        }
                        break;
                    case DIRECTION.LEFT:
                        if (dir == DIRECTION.UP) {
                            flag = true;
                        }
                        break;
                    case DIRECTION.DOWN:
                        if (dir == DIRECTION.LEFT) {
                            flag = true;
                        }
                        break;
                    case DIRECTION.RIGHT:
                        if (dir == DIRECTION.DOWN) {
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
