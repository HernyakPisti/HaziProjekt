package hu.mavenprojekt.Components.Model.Boardelements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StartTest {

    Start start;

    @BeforeEach
    void init() {
        start = new Start();
    }

    @Test
    void testToString() {
        assertEquals("O", start.toString());
    }
}