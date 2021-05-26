package hu.mavenproject.utils;

import hu.mavenproject.components.controller.BoardController;
import hu.mavenproject.components.model.Board;
import hu.mavenproject.components.view.BoardGUI;
import hu.mavenproject.components.view.GUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UtilsTest {

    Board b;
    File f1, savefile;
    GUI gui;
    BoardGUI boardGUI;
    BoardController bc;
    URL url;
    char[][] map;

    @BeforeEach
    void init() {
        b = new Board(5, 5);
        map = new char[][]{{'#', '#', '#', '#', '#'}, {'#', '.', '.', '.', '#'}, {'#', '.', '.', '.', '#'}, {'#', '.', '.', '.', '#'}, {'#', '#', '#', '#', '#'}};
        b.setCharMap(map);
        b.resetMap();
    }

    @Test
    void parseTest() throws MalformedURLException {
        URL u = null;
        assertNull(Utils.parseLevel(u));
        url = getClass().getResource("/sample.json");
        b = Utils.parseLevel(url);
        b.resetMap();
        Board b2 = Utils.parseLevel(url);
        b2.resetMap();
        assertEquals(b.toString(), b2.toString());
    }

    @Test
    void setGameTest() {
        URL u = null;
        assertNull(Utils.setGame(gui, u));
    }

}
