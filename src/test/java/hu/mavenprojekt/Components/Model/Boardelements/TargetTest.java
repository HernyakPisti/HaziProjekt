package hu.mavenprojekt.Components.Model.Boardelements;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TargetTest {

    Target target;

    @BeforeEach
    void init() {
        target = new Target();
    }

    @Test
    void testToString() {
        assertEquals("$", target.toString());
    }
}