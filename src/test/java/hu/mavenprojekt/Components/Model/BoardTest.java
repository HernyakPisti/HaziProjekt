package hu.mavenprojekt.Components.Model;

import hu.mavenprojekt.Components.Model.Boardelements.Tile;
import hu.mavenprojekt.Components.Model.Boardelements.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board, board1, board2, board3, board4, board5;
    Player player, player2;

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
    }

    @Test
    void constructors() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Board(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Board(-1, player);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Board(10, 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Board(-1, -1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Board(-1, 1, player);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Board(-1, -1, player);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Board(1, -1, player);
        });
    }

    @Test
    void generateDefault() {
        board.generateDefault();
        assertEquals(board.getN() * board.getM(), board.getMapString().length());
        assertTrue(board.getMapString().contains("$"));
        assertTrue(board.getMapString().contains("O"));
        assertTrue(board.getMapString().contains("#"));
        assertTrue(board.getMapString().contains("."));
    }

    @Test
    void testToString() {
        board.generateDefault();
        assertEquals(board.getN() * board.getM(), board.getMapString().length());
        assertTrue(board.getMapString().contains("$"));
        assertTrue(board.getMapString().contains("O"));
        assertTrue(board.getMapString().contains("#"));
        assertTrue(board.getMapString().contains("."));
    }

    @Test
    void fromString() {
        board2.fromString("######.O.##...##.$.######");
        assertTrue(board2.getMapString().contains("$"));
        assertThrows(IllegalArgumentException.class, () -> {
            board2.fromString("######.O.##...##..##$###");// N*M!=map.length
        });
        assertThrows(IllegalArgumentException.class, () -> {
            board2.fromString("######OOO##...##...######");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            board2.fromString("######$$$##...##...######");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            board2.fromString("######..O##...##...######");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            board2.fromString("######..$##...##...######");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            board2.fromString("######.O.##...##.]##$####");
        });
    }

    @Test
    void resetMap() {
        board2.setMapString("..$.....................O");
        board2.resetMap();
        assertEquals("..$.....................O", board2.toString());
    }

    @Test
    void getN() {
        assertEquals(10, board.getN());
        assertEquals(10, board1.getN());
        assertEquals(5, board2.getN());
        assertEquals(10, board3.getN());
        assertEquals(5, board4.getN());
        assertEquals(10, board5.getN());
    }

    @Test
    void setN() {
        board.setN(4);
        assertEquals(4, board.getN());
        assertThrows(IllegalArgumentException.class, () -> {
            board.setN(-1);
        });
    }

    @Test
    void getM() {
        assertEquals(10, board.getM());
        assertEquals(10, board1.getM());
        assertEquals(5, board2.getM());
        assertEquals(10, board3.getM());
        assertEquals(5, board4.getM());
        assertEquals(10, board5.getM());
    }

    @Test
    void setM() {
        board.setM(5);
        assertEquals(5, board.getM());
        assertThrows(IllegalArgumentException.class, () -> {
            board.setM(-1);
        });
    }

    @Test
    void getMap() {
        board.generateDefault();
        assertTrue(board.getMap() instanceof Tile[][]);
    }

    @Test
    void setMap() {
        assertFalse(board2.getMap().equals(board.getMap()));
        board2.setMap(board.getMap());
        assertSame(board.getMap(), board2.getMap());
    }

    @Test
    void getPlayer() {
        assertEquals(player.toString(), board.getPlayer().toString());
    }

    @Test
    void setPlayer() {
        board.setPlayer(player2);
        assertEquals(player2, board.getPlayer());
    }

    @Test
    void getMapString() {
        board.generateDefault();
        assertTrue(board.getMapString().contains("O"));
        assertTrue(board.getMapString().contains("$"));
        assertTrue(board.getMapString().contains("."));
        assertTrue(board.getMapString().contains("#"));
    }

    @Test
    void setMapString() {
        board2.setMapString("...O..............$......");
        board2.resetMap();
        assertFalse(board2.getMapString().contains("#"));
        board2.setMapString("....$...................O");
        board2.resetMap();
        assertTrue(board2.getMapString().contains("O"));
    }

    @Test
    void getTileAt() {
        board.generateDefault();
        Wall wall = new Wall();
        assertEquals(wall.toString(), board.getTileAt(0, 0).toString());
    }
}