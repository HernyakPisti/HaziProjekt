package hu.mavenprojekt.Components.Model.Boardelements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WallTest {

    Wall wall;

    @BeforeEach
    void init(){
        wall=new Wall();
    }

    @Test
    void testToString() {
        assertEquals("#",wall.toString());
    }
}