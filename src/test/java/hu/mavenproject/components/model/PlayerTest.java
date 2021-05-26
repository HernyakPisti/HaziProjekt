package hu.mavenproject.components.model;

import hu.mavenproject.components.model.Board.DIRECTION;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    Player player, player1, player2, player3;

    @BeforeEach
    void init() {
        player = new Player();
        player1 = new Player(10, 10);
        player2 = new Player(5, 5, "d");
        player3 = new Player(5, 5, "Pisti", 0);
    }

    @Test
    void testToString() {
        assertEquals("X", player.toString());
    }

    @Test
    void reset() {
        player.reset();
        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
        assertEquals(DIRECTION.NONE, player.getCurrentDirection());
    }

    @Test
    void move() {
        player.move(1, 1, DIRECTION.RIGHT);
        assertEquals(2, player.getX());
    }

    @Test
    void getScore() {
        assertEquals(0, player3.getScore());
        player3.move(1, 1, DIRECTION.RIGHT);
        assertEquals(1, player3.getScore());
    }

    @Test
    void getName() {
        assertEquals("Pisti", player3.getName());
    }

    @Test
    void getStartDirection() {
        assertEquals(DIRECTION.NONE, player3.getStartDirection());
    }
}