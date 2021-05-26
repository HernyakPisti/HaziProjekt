package hu.mavenproject.components.controller;

import hu.mavenproject.components.model.Board;
import hu.mavenproject.components.model.Board.DIRECTION;
import hu.mavenproject.components.model.Player;
import hu.mavenproject.components.view.BoardGUI;
import hu.mavenproject.components.view.GUI;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class BoardControllerTest {

    File file;
    BoardController boardController, boardController2;
    BoardGUI boardGUI;
    Board board, board2;
    Player player;
    GUI gui;

    @BeforeEach
    void init() {
        file = null;
        player = new Player();
        board = new Board();
        board2 = new Board(5, 5);
        boardGUI = new BoardGUI(gui);
        boardController = new BoardController(boardGUI, board);
    }

    @Test
    void testGenerateDefaultBoard() {
        boardController.generateDefaultBoard();
        System.out.println(boardController.getBoard());
        assertTrue(boardController.getBoard().toString().contains("."));
        assertTrue(boardController.getBoard().toString().contains("#"));
        assertTrue(boardController.getBoard().toString().contains("$"));
        assertTrue(boardController.getBoard().toString().contains("O"));
    }

    @Test
    void move() {
        KeyEvent k1 = new KeyEvent(KeyEvent.KEY_PRESSED, KeyCode.D.toString(), KeyCode.D.toString(), KeyCode.D, false,
                false, false, false);
        KeyEvent k2 = new KeyEvent(KeyEvent.KEY_PRESSED, KeyCode.S.toString(), KeyCode.S.toString(), KeyCode.S, false,
                false, false, false);
        KeyEvent k3 = new KeyEvent(KeyEvent.KEY_PRESSED, KeyCode.A.toString(), KeyCode.A.toString(), KeyCode.A, false,
                false, false, false);
        KeyEvent k4 = new KeyEvent(KeyEvent.KEY_PRESSED, KeyCode.W.toString(), KeyCode.W.toString(), KeyCode.W, false,
                false, false, false);
        KeyEvent k5 = new KeyEvent(KeyEvent.KEY_PRESSED, KeyCode.Q.toString(), KeyCode.Q.toString(), KeyCode.Q, false,
                false, false, false);
        boardController.generateDefaultBoard();
        assertEquals(DIRECTION.RIGHT, boardController.move(k1));
        assertEquals(DIRECTION.DOWN, boardController.move(k2));
        assertEquals(DIRECTION.LEFT, boardController.move(k3));
        assertEquals(DIRECTION.UP, boardController.move(k4));
        assertEquals(-1, boardController.move(k3));
        assertEquals(DIRECTION.RIGHT, boardController.move(k1));
        assertEquals(DIRECTION.RIGHT, boardController.move(k1));
        assertEquals(-1, boardController.move(k5));
    }

    @Test
    void getBoard() {
        assertEquals(board.toString(), boardController.getBoard().toString());
    }

    @Test
    void reset() {
        assertEquals(board.toString(), boardController.getBoard().toString());
        boardController.reset();
        assertEquals(board.toString(), boardController.getBoard().toString());
    }

    @Test
    void getPlayer() {
        assertSame(player.toString(), boardController.getBoard().getPlayer().toString());
    }
}