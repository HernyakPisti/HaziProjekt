package hu.mavenprojekt.Components.Model.Boardelements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroundTest {

    Ground ground;

    @BeforeEach
    void init(){
        ground=new Ground();
    }

    @Test
    void testToString() {
        assertEquals(".",ground.toString());
    }
}