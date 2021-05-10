package hu.mavenprojekt.Utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.mavenprojekt.Components.Model.Board;
import hu.mavenprojekt.Components.View.GUI;
import hu.mavenprojekt.Components.View.GameGUI;

import java.io.File;
import java.io.IOException;

public final class Utils {

    private Utils() {
    }

    public static Board parseLevel(final File file) {
        ObjectMapper mapper = new ObjectMapper();

        Board board = null;
        try {
            board = mapper.readValue(file, Board.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return board;
        }
    }

    public static GameGUI setGame(final GUI menu,final File file) {
        Board board = parseLevel(file);
        if (board != null) {
            GameGUI gameGUI = new GameGUI(menu, board);
            return gameGUI;

        } else {
            return null;
        }

    }

}
