package hu.mavenprojekt.Components.Controller;

import hu.mavenprojekt.Components.Model.Board;
import hu.mavenprojekt.Components.Model.Player;
import hu.mavenprojekt.Components.View.BoardGUI;
import hu.mavenprojekt.Components.View.GUI;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class BoardControllerTest {

    File file;
    BoardController boardController,boardController2;
    BoardGUI boardGUI;
    Board board,board2;
    Player player;
    GUI gui;
    @BeforeEach
    void init(){
        file=null;
        player = new Player();
        board = new Board();
        board2 = new Board(5,5);
        boardGUI= new BoardGUI(gui);
        boardController=new BoardController(boardGUI,10,10);
        boardController2=new BoardController(boardGUI,board);
    }

    @Test
    void generateDefaultBoard() {
        boardController.generateDefaultBoard(board);
        assertSame(boardController.getBoard(),board);
        boardController2.generateDefaultBoard(board);
        assertSame(boardController2.getBoard(),board);
    }

    @Test
    void testGenerateDefaultBoard() {
        boardController.generateDefaultBoard();
        assertTrue(boardController.getBoard().getMapString().contains("."));;
        assertTrue(boardController.getBoard().getMapString().contains("#"));;
        assertTrue(boardController.getBoard().getMapString().contains("$"));;
        assertTrue(boardController.getBoard().getMapString().contains("O"));;
    }

    @Test
    void save() {
       assertThrows(IllegalArgumentException.class,()->new BoardController(boardGUI,board).save(file));
    }

    @Test
    void move2() {
        boardController.generateDefaultBoard();
        assertEquals(null,boardController.move2(KeyCode.A));
        boardController.generateDefaultBoard();
        assertEquals("s",boardController.move2(KeyCode.S));
        boardController.generateDefaultBoard();
        assertEquals("d",boardController.move2(KeyCode.D));
        boardController.generateDefaultBoard();
        assertEquals(null,boardController.move2(KeyCode.W));
    }

    @Test
    void getBoard() {
        boardController.generateDefaultBoard(board);
        assertEquals(board.getMapString(),boardController.getBoard().getMapString());
    }

    @Test
    void reset() {
        boardController.generateDefaultBoard(board);
        assertEquals(board.getMapString(),boardController.getBoard().getMapString());
        board.generateDefault();
        boardController.reset();
        assertEquals(board.getMapString(),boardController.getBoard().getMapString());
    }

    @Test
    void testReset() {
        boardController.generateDefaultBoard(board);
        assertEquals(board.getMapString(),boardController.getBoard().getMapString());
        board.generateDefault();
        boardController.reset(false);
        assertEquals(board.getMapString(),boardController.getBoard().getMapString());
    }

    @Test
    void getPlayer() {
        boardController.generateDefaultBoard();
        assertSame(player.toString(),boardController.getPlayer().toString());
    }
}