package hu.mavenprojekt.Components.View;

import hu.mavenprojekt.Utils.Utils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.File;
import java.net.URL;

/**
 * {@link MainMenu} {@link Class}, represents the games main menu.
 */
public final class MainMenu implements GUI {
    private Scene scene = null;
    private VBox root = null;
    private Stage stage = null;
    private HBox sizeBox = null;
    private GameGUI gameGUI = null;

    /**
     * {@link java.lang.reflect.Constructor} for the {@link MainMenu}.
     *
     * @param stage_ The {@link Stage} the game is running on.
     */
    public MainMenu(final Stage stage_) {
        construct();
        this.stage = stage_;
    }

    /**
     * A {@link java.lang.reflect.Method} which contructs the layout of the
     * {@link MainMenu}.
     */
    public void construct() {
        this.root = new VBox();
        this.sizeBox = new HBox();
        sizeBox.setAlignment(Pos.CENTER);
        sizeBox.setSpacing(10);

        Label meretLabel = new Label("Level size:");

        Spinner<Integer> spinnerX = new Spinner<Integer>(5, 15, 10);
        Spinner<Integer> spinnerY = new Spinner<Integer>(5, 15, 10);
        Label labelX = new Label("x");
        spinnerX.setMaxWidth(80);
        spinnerY.setMaxWidth(80);

        sizeBox.getChildren().addAll(meretLabel, spinnerX, labelX, spinnerY);

        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Button newGameButton = new Button("New Game");
        newGameButton.setDefaultButton(true);
        newGameButton.setOnAction(e -> {
            Logger.info("Clicked on New Game");
            this.gameGUI = new GameGUI(this, this.stage, spinnerX.getValue() * 40, spinnerY.getValue() * 40,
                    spinnerY.getValue(), spinnerX.getValue());
            this.stage.setScene(this.gameGUI.getRoot());

        });

        Button cancelButton = new Button("Exit");
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(e -> {
            Logger.info("Clicked on Exit. Closing the game.");
            this.stage.close();
        });

        Button openSampleMap = new Button("Open Sample Map");
        openSampleMap.setOnAction(e -> {
            Logger.info("Opened sample map");
            URL file = getClass().getResource("/sample.json");
            if (file != null) {
                this.gameGUI = Utils.setGame(this, file);
                if (this.gameGUI != null) {
                    this.stage.setScene(this.gameGUI.getRoot());
                }
            }
        });


        Button openFile = new Button("Open level");
        openFile.setOnAction(e -> {
            String log = "Clicked on Open level";
            Stage s = new Stage();
            s.setTitle("Open file");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JSON Files", "*.json"));
            File directory = new File("./levels");
            if (!directory.exists()) {
                directory = new File("../levels");
                if (!directory.exists()) {
                    directory = new File(".");
                }
            }
            fileChooser.setInitialDirectory(directory);
            File file = fileChooser.showOpenDialog(s);
            if (file != null) {
                log += ": " + file.getName();
                this.gameGUI = Utils.setGame(this, file);
                if (this.gameGUI != null) {
                    this.stage.setScene(this.gameGUI.getRoot());
                }
            }
            Logger.info(log);

        });

        this.root.getChildren().addAll(sizeBox, newGameButton, openSampleMap, openFile, cancelButton);

    }

    /**
     * A {@link java.lang.reflect.Method} that returns the {@link MainMenu}'s root
     * component so it can be used as a root for a {@link Scene}.
     *
     * @return {@link VBox}, the root component of the {@link MainMenu}.
     */
    public VBox getRoot() {
        return this.root;
    }

    /**
     * A {@link java.lang.reflect.Method} that returns the {@link Scene} the
     * {@link MainMenu} is on.
     *
     * @return The {@link Scene} the {@link MainMenu} is on.
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * A {@link java.lang.reflect.Method} to set the {@link Scene} variable, so it
     * can be used to get back to the {@link MainMenu} from a game.
     *
     * @param scene_ A {@link Scene} the {@link MainMenu} is on.
     */
    public void setScene(final Scene scene_) {
        this.scene = scene_;
    }

    /**
     * A {@link java.lang.reflect.Method} that returns the game's {@link Stage}, so
     * it can be closed from other {@link Scene}s.
     *
     * @return {@link Stage}, the game's window.
     */
    public Stage getStage() {
        return this.stage;
    }
}
