package hu.mavenproject.components.view;

import hu.mavenproject.utils.Utils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.net.URL;

/**
 * {@link MainMenu} {@link Class}, represents the games main menu.
 */
public final class MainMenu implements GUI {
    private Scene scene = null;
    private VBox root = null;
    private Stage stage = null;
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

        HBox nameBox = new HBox();
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setSpacing(10);

        Label nameLabel = new Label("Player name");
        TextField nameInput = new TextField();
        nameBox.getChildren().addAll(nameLabel, nameInput);

        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Button cancelButton = new Button("Cancel");
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(e -> {
            Logger.info("Clicked on Cancel. Closing the game.");
            this.stage.close();
        });

        Button openSampleMap = new Button("Start New Game");
        openSampleMap.setOnAction(e -> {
            if (nameInput.getText().trim().length() == 0) {
                Utils.showWarning("You must add a name!");
                nameInput.requestFocus();
                return;
            }
            Logger.info("Started new game");
            URL file = getClass().getResource("/sample.json");
            if (file != null) {
                this.gameGUI = Utils.setGame(this, file);
                if (this.gameGUI != null) {
                    this.stage.setScene(this.gameGUI.getRoot());
                }
                this.gameGUI.getBoardGUI().getBoardController().getPlayer().setName(nameInput.getText().trim());
                nameInput.setText("");
            }
        });

        Button resultButton = new Button("Results");
        resultButton.setOnAction(e -> {
            Logger.info("Clicked on Results");
            ResultsGUI resultsGUI = new ResultsGUI(this);
            this.stage.setScene(new Scene(resultsGUI.getRoot(), 200, 300));
        });

        this.root.getChildren().addAll(nameBox, openSampleMap, resultButton, cancelButton);

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
