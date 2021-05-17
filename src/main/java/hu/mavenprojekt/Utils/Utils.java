package hu.mavenprojekt.Utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.mavenprojekt.Components.Model.Board;
import hu.mavenprojekt.Components.View.GUI;
import hu.mavenprojekt.Components.View.GameGUI;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;

/**
 * {@link Class} that contains {@link java.lang.reflect.Method}s that are
 * helping out the game.
 */
public final class Utils {

    private Utils() {
    }

    /**
     * A {@link java.lang.reflect.Method} to parse a JSON file to a {@link Board}
     * {@link Object} (Load in saved games).
     *
     * @param file A {@link File}, the saved game.
     * @return A {@link Board} that is reconstructed from the JSON file.
     */
    public static Board parseLevel(final File file) {
        ObjectMapper mapper = new ObjectMapper();

        Board board = null;
        try {
            board = mapper.readValue(file, Board.class);
        } catch (JsonParseException e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            return board;
        }
    }

    /**
     * A {@link java.lang.reflect.Method} to parse a URL (mainly the default game's
     * JSON file loaded with classloader) to a {@link Board}.
     *
     * @param file A {@link URL} that is pointing to the saved game's file.
     * @return A {@link Board} that is reconstructed from the save file.
     */
    public static Board parseLevel(final URL file) {
        ObjectMapper mapper = new ObjectMapper();

        Board board = null;
        try {
            board = mapper.readValue(file, Board.class);
        } catch (JsonParseException e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        } catch (JsonMappingException e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            return board;
        }
    }

    /**
     * A {@link java.lang.reflect.Method} that creates a {@link GameGUI} for a
     * loaded saved game.
     *
     * @param menu A {@link hu.mavenprojekt.Components.View.MainMenu}, the new
     *             {@link GameGUI}'s parent, the menu where we chose to load the
     *             save.
     * @param file A {@link File}, the saved game's JSON file.
     * @return A {@link GameGUI} with a recreated {@link Board}.
     */
    public static GameGUI setGame(final GUI menu, final File file) {
        Board board = parseLevel(file);
        if (board != null) {
            GameGUI gameGUI = new GameGUI(menu, board);
            return gameGUI;

        } else {
            return null;
        }

    }

    /**
     * A {@link java.lang.reflect.Method} that creates a {@link GameGUI} for a
     * loaded save (mainly the default game's JSON file loaded with classloader).
     *
     * @param menu A {@link hu.mavenprojekt.Components.View.MainMenu}, the new
     *             {@link GameGUI}'s parent, the menu where we chose to load the
     *             save.
     * @param file A {@link URL}, thats pointing to the save file.
     * @return A {@link GameGUI} with a recreated {@link Board}.
     */
    public static GameGUI setGame(final GUI menu, final URL file) {
        Board board = parseLevel(file);
        if (board != null) {
            GameGUI gameGUI = new GameGUI(menu, board);
            return gameGUI;

        } else {
            return null;
        }

    }

    /**
     * A {@link java.lang.reflect.Method} that shows a popup window if the player
     * has won a game, and asks if they want to play a new one.
     *
     * @return An {@link Integer}, the result of the player's choice.
     */
    public static Integer showAlert() {
        String content = "Do you want to start a new game?";
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, content, ButtonType.YES, ButtonType.NO,
                ButtonType.CANCEL);
        alert.setTitle("You win!");
        alert.setHeaderText(null);

        Integer res = null;

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            res = 0;
        } else if (result.isPresent() && result.get() == ButtonType.NO) {
            res = 1;
        } else if (result.isPresent() && result.get() == ButtonType.CANCEL) {
            res = -1;
        }
        return res;
    }

}
