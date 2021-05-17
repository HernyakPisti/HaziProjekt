package hu.mavenprojekt.Utils;

import hu.mavenprojekt.Components.Controller.BoardController;
import hu.mavenprojekt.Components.Model.Board;
import hu.mavenprojekt.Components.View.BoardGUI;
import hu.mavenprojekt.Components.View.GUI;
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

    @BeforeEach
    void init() {
        b = new Board();
        f1 = null;
        bc = new BoardController(boardGUI, b);
        bc.generateDefaultBoard(b);
    }

    @Test
    void parseTest() throws MalformedURLException {
        assertNull(Utils.parseLevel(f1));
        savefile = new File("./levels/testsave.json");
        bc.save(savefile);
        Board b2 = Utils.parseLevel(savefile);
        b2.resetMap();
        assertEquals(b.toString(), b2.toString());
        url = new URL("file", "localhost", "./levels/testsave.json");
        Board b3 = Utils.parseLevel(url);
        b3.resetMap();
        assertEquals(b.toString(), b3.toString());
    }

    @Test
    void setGameTest() {
        assertNull(Utils.setGame(gui, f1));
        URL u = null;
        assertNull(Utils.setGame(gui, u));
    }

}
