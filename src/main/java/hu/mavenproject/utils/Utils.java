package hu.mavenproject.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import hu.mavenproject.components.model.Board;
import hu.mavenproject.components.model.Player;
import hu.mavenproject.components.model.Result;
import hu.mavenproject.components.view.GUI;
import hu.mavenproject.components.view.GameGUI;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.tinylog.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * {@link Class} that contains {@link java.lang.reflect.Method}s that are
 * helping out the game.
 */
public final class Utils {

    private Utils() {
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
     * loaded save (mainly the default game's JSON file loaded with classloader).
     *
     * @param menu A {@link hu.mavenproject.components.view.MainMenu}, the new
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

    /**
     * A {@link java.lang.reflect.Method} that shows a wanring message.
     *
     * @param message A {@link String}, the message.
     */
    public static void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle("Warning!");
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    /**
     * A {@link java.lang.reflect.Method} that saves the result to a file.
     *
     * @param p    The {@link Player} object.
     */
    public static void writeResults(Player p) {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        File f = Paths.get("./results.json").toFile();
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                Logger.error(e.getMessage());
            }
        }
        List<Result> resultList = null;
        try {
            resultList = objectMapper.readValue(new FileInputStream("results.json"),
                    typeFactory.constructCollectionType(List.class, Result.class));
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        if (resultList == null) {
            resultList = new ArrayList<Result>();
        }
        Result r = new Result(p.getName(), p.getScore());
        resultList.add(r);
        try {
            objectMapper.writeValue(new FileOutputStream("results.json"), resultList);
        } catch (IOException e) {
            Logger.error(e.getMessage());
        }
    }

}
