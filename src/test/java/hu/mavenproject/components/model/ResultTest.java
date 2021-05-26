package hu.mavenproject.components.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultTest {

    Result result, result2;

    @BeforeEach
    void init() {
        result = new Result();
        result2 = new Result("Pisti", 21);
    }

    @Test
    void testToString() {
        assertEquals("Pisti - 21", result2.toString());
    }

    @Test
    void getScore() {
        assertEquals(0, result.getScore());
    }

    @Test
    void setScore() {
        result2.setScore(22);
        assertEquals(22, result2.getScore());
    }

    @Test
    void setName() {
        result.setName("Test");
        assertEquals("Test", result.getName());
    }

    @Test
    void getName() {
        assertEquals("Pisti", result2.getName());
    }
}