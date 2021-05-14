package hu.mavenprojekt.Components.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PlayerTest {

    Player player,player1,player2;

    @BeforeEach
    void init(){
         player = new Player();
         player1 = new Player(10, 10);
         player2 = new Player(5,5,"d");
    }

    @Test
    void testToString() {
        assertEquals("X",player.toString());
    }

    @Test
    void reset() {
        player.reset();
        assertEquals(player.getX(),player.getStartX());
        assertEquals(player.getY(),player.getStartY());
        assertEquals(player.getCurrentHeading(),player.getStartHeading());
    }

    @Test
    void testReset() {
        player.reset(false,10,10);
        assertEquals(player.getX(),player.getStartX());
        assertEquals(player.getY(),player.getStartY());
        assertEquals(player.getCurrentHeading(),player.getStartHeading());
        player.reset(true,10,10);
        assertEquals("",player.getCurrentHeading());
        assertNotEquals(10,player.getX());
        assertNotEquals(10,player.getY());
        assertEquals(player.getStartX(),player.getX());
        assertEquals(player.getStartY(),player.getY());
        assertEquals("",player.getCurrentHeading());
    }

    @Test
    void move() {
        player.move(1,1,"d");
        assertEquals(2,player.getX());
    }

    @Test
    void getX() {
        assertEquals(1,player.getX());
        assertEquals(10,player1.getX());
        assertEquals(5,player2.getX());
    }

    @Test
    void setX() {
        player.setX(2);
        assertEquals(2,player.getX());
    }

    @Test
    void getY() {
        assertEquals(1,player.getY());
        assertEquals(10,player1.getY());
    }

    @Test
    void setY() {
        player.setY(2);
        assertEquals(2,player.getY());
    }

    @Test
    void getStartX() {
        assertEquals(1,player.getStartX());
        assertEquals(10,player1.getStartX());
    }

    @Test
    void setStartX() {
        player.setStartX(3);
        assertEquals(3,player.getStartX());
    }

    @Test
    void getStartY() {
        assertEquals(1,player.getStartY());
        assertEquals(10,player1.getStartY());
    }

    @Test
    void setStartY() {
        player.setStartY(3);
        assertEquals(3,player.getStartY());
    }

    @Test
    void getCurrentHeading() {
        assertEquals("",player.getCurrentHeading());
        assertEquals("",player1.getCurrentHeading());
    }

    @Test
    void setCurrentHeading() {
        player.setCurrentHeading("a");
        assertEquals("a",player.getCurrentHeading());
    }

    @Test
    void getStartHeading() {
        assertEquals("",player.getStartHeading());
        assertEquals("",player1.getStartHeading());
    }

    @Test
    void setStartHeading() {
        player.setStartHeading("s");
        assertEquals("s",player.getStartHeading());
    }
}