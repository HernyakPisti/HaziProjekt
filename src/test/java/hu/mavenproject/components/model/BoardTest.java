package hu.mavenproject.components.model;

import hu.mavenproject.components.model.boardelements.Tile;
import hu.mavenproject.components.model.boardelements.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board, board1, board2, board3, board4, board5;
    Player player, player2;
    char[][] map;

    @BeforeEach
    void init() {
        player = new Player(1, 1);
        player2 = new Player(10, 10, "s");
        board = new Board();
        board1 = new Board(10);
        board2 = new Board(5, player);
        board3 = new Board(10, 10);
        board4 = new Board(5, 5, player);
        board5 = new Board(player);
        map = new char[][]{{'#', '#', '#', '#', '#'}, {'#', '.', '$', '.', '#'}, {'#', '.', 'O', '.', '#'}, {'#', '.', '.', '.', '#'}, {'#', '#', '#', '#', '#'}};
        board4.setCharMap(map);
        board4.resetMap();
    }


    @Test
    void testToString() {
        assertEquals(board4.getN() * board4.getM(), board4.toString().length());
        assertTrue(board4.toString().contains("$"));
        assertTrue(board4.toString().contains("O"));
        assertTrue(board4.toString().contains("#"));
        assertTrue(board4.toString().contains("."));
    }

    @Test
    void fromString() {
        board2.fromString(map);
        assertTrue(board2.toString().contains("$"));
        assertThrows(IllegalArgumentException.class, () -> {
            board2.fromString(new char[][]{{'#', '#', '#', '#', '#'}, {'#', '.', '$', '.', '}'}, {'#', '.', '.', '.', '#'}, {'#', '.', '.', '.', '#'}, {'#', '#', '#', '#', '#'}});
        });
    }

    @Test
    void resetMap() {
        board2.setCharMap(map);
        board2.resetMap();
        assertEquals("######.$.##.O.##...######", board2.toString());
    }

    @Test
    void getTileAt() {
        Wall wall = new Wall();
        assertEquals(wall.toString(), board4.getTileAt(0, 0).toString());
    }

    @Test
    void getPlayer() {
        assertEquals(player, board2.getPlayer());
    }

    @Test
    void getMap() {
        assertTrue(board2.getMap() instanceof Tile[][]);
    }

    @Test
    void generateDefault() {
        board1.generateDefault();
        assertTrue(board1.toString().contains("#"));
    }
}