
package hu.mavenprojekt.Components.View;

import java.io.File;
import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import hu.mavenprojekt.Utils.Utils;
import org.tinylog.Logger;

/**
 * MainMenu class, represents the games main menu.
 */
public final class MainMenu implements GUI {
    private Scene scene = null;
    private VBox root = null;
    private Stage stage = null;
    private HBox sizeBox = null;
    private GameGUI gameGUI = null;

    /**
     * Constructor for the main menu.
     *
     * @param stage_ the stage the game is running on
     */
    public MainMenu(Stage stage_) {
        construct();
        this.stage = stage_;
    }

    /**
     * construct method which contructs the layout of the main menu.
     */
    public void construct() {
        this.root = new VBox();
        this.sizeBox = new HBox();
        sizeBox.setAlignment(Pos.CENTER);
        sizeBox.setSpacing(10);

        Label meretLabel = new Label("Pálya méret:");

        Spinner<Integer> spinnerX = new Spinner<Integer>(10, 20, 10);
        Spinner<Integer> spinnerY = new Spinner<Integer>(10, 20, 10);
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
            this.gameGUI = new GameGUI(this, this.stage, (int) spinnerX.getValue() * 40, (int) spinnerY.getValue() * 40,
                    (int) spinnerY.getValue(), (int) spinnerX.getValue());
            this.stage.setScene(this.gameGUI.getRoot());

        });

        Button cancelButton = new Button("Exit");
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(e -> {
            Logger.info("Clicked on Exit");
            this.stage.close();
        });

        Button openFile = new Button("Open level");
        openFile.setOnAction(e -> {
            Logger.info("Clicked on Open level");
            Stage s = new Stage();
            s.setTitle("Open file");
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JSON Files", "*.json"));
            File directory = new File("./levels");
            if (!directory.exists()) {
                directory = new File("../levels");
                if (!directory.exists())
                    directory = new File(".");
            }
            fileChooser.setInitialDirectory(directory);
            File file = fileChooser.showOpenDialog(s);
            if (file != null) {
                this.gameGUI = Utils.setGame(this, file);
                if (this.gameGUI != null)
                    this.stage.setScene(this.gameGUI.getRoot());
            }

        });

        this.root.getChildren().addAll(sizeBox, newGameButton, openFile, cancelButton);

    }

    /**
     * method that returns the main menu's root component so it can be used as a
     * root for a scene.
     *
     * @return {@link VBox}, the root component of the main menu
     */
    public VBox getRoot() {
        return this.root;
    }

    /**
     * method to set the scene variable, so it can be used to get back to the main
     * menu from a game.
     *
     * @param scene the scene the main mau is on
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * a method that returns the scene the main menu is on.
     *
     * @return {@link Scene} the main menu is on
     */
    public Scene getScene() {
        return this.scene;
    }

    /**
     * method that returns the game's stage, so it can be closed from other scenes.
     *
     * @return {@link Stage}, the game's window
     */
    public Stage getStage() {
        return this.stage;
    }
}
