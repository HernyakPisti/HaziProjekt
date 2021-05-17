package hu.mavenprojekt.Components.Controller;

import hu.mavenprojekt.Components.Model.Board;
import hu.mavenprojekt.Components.Model.Player;
import hu.mavenprojekt.Components.View.BoardGUI;
import hu.mavenprojekt.Components.View.GUI;
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
        boardController = new BoardController(boardGUI, 10, 10);
        boardController2 = new BoardController(boardGUI, board);
    }

    @Test
    void generateDefaultBoard() {
        boardController.generateDefaultBoard(board);
        assertSame(boardController.getBoard(), board);
        boardController2.generateDefaultBoard(board);
        assertSame(boardController2.getBoard(), board);
    }

    @Test
    void testGenerateDefaultBoard() {
        boardController.generateDefaultBoard();
        assertTrue(boardController.getBoard().getMapString().contains("."));
        assertTrue(boardController.getBoard().getMapString().contains("#"));
        assertTrue(boardController.getBoard().getMapString().contains("$"));
        assertTrue(boardController.getBoard().getMapString().contains("O"));
    }

    @Test
    void save() {
        assertThrows(IllegalArgumentException.class, () -> new BoardController(boardGUI, board).save(file));
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
        boardController.getBoard().setM(5);
        boardController.getBoard().setN(5);
        boardController.getBoard().setMapString("######O..##...##.$.######");
        boardController.getBoard().resetMap();
        assertEquals("d", boardController.move(k1));
        assertEquals("s", boardController.move(k2));
        assertEquals("a", boardController.move(k3));
        assertEquals("w", boardController.move(k4));
        assertNull(boardController.move(k3));
        assertEquals("d", boardController.move(k1));
        assertEquals("d", boardController.move(k1));
        assertNull(boardController.move(k5));
    }

    @Test
    void getBoard() {
        boardController.generateDefaultBoard(board);
        assertEquals(board.getMapString(), boardController.getBoard().getMapString());
    }

    @Test
    void reset() {
        boardController.generateDefaultBoard(board);
        assertEquals(board.getMapString(), boardController.getBoard().getMapString());
        board.generateDefault();
        boardController.reset();
        assertEquals(board.getMapString(), boardController.getBoard().getMapString());
        String before = boardController.getBoard().getMapString();
        boardController.reset(true);
        assertNotEquals(before, boardController.getBoard().getMapString());
    }

    @Test
    void testReset() {
        boardController.generateDefaultBoard(board);
        assertEquals(board.getMapString(), boardController.getBoard().getMapString());
        board.generateDefault();
        boardController.reset(false);
        assertEquals(board.getMapString(), boardController.getBoard().getMapString());
    }

    @Test
    void getPlayer() {
        boardController.generateDefaultBoard();
        assertSame(player.toString(), boardController.getPlayer().toString());
    }
}