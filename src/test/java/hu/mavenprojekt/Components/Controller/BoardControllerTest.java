package hu.mavenprojekt.Components.Controller;

import hu.mavenprojekt.Components.Model.Board;
import hu.mavenprojekt.Components.Model.Player;
import hu.mavenprojekt.Components.View.BoardGUI;
import hu.mavenprojekt.Components.View.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardControllerTest {

    BoardController boardController,boardController2;
    BoardGUI boardGUI;
    Board board,board2;
    Player player;
    GUI gui;
    @BeforeEach
    void init(){
        player = new Player();
        board = new Board();
        board2 = new Board(5,5);
        boardGUI= new BoardGUI(gui);
        boardController=new BoardController(boardGUI,10,10);
        boardController2=new BoardController(boardGUI,5,5);
    }

    @Test
    void generateDefaultBoard() {
        boardController.generateDefaultBoard(board);
        assertSame(boardController.getBoard(),board);
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
    }

    @Test
    void move() {
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
    }

    @Test
    void getPlayer() {
        boardController.generateDefaultBoard();
        assertSame(player.toString(),boardController.getPlayer().toString());
    }
}