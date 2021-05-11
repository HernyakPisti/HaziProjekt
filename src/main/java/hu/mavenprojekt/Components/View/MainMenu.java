
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



public final class MainMenu implements GUI {
    private Scene scene = null;
    private VBox root = null;
    private Stage stage = null;
    private HBox sizeBox = null;
    private GameGUI gameGUI = null;

    public MainMenu(Stage stage_) {
        construct();
        this.stage = stage_;
    }

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
            fileChooser.setInitialDirectory(new File("./levels"));
            File file = fileChooser.showOpenDialog(s);
            if (file != null) {
                this.gameGUI = Utils.setGame(this, file);
                if (this.gameGUI != null)
                    this.stage.setScene(this.gameGUI.getRoot());
                    Logger.info("Level successfully loaded from: " + file);

            }

        });

        this.root.getChildren().addAll(sizeBox, newGameButton, openFile, cancelButton);

    }


    public VBox getRoot() {
        return this.root;
    }


    public void setScene(Scene scene) {
        this.scene = scene;
    }


    public Scene getScene() {
        return this.scene;
    }


    public Stage getStage() {
        return this.stage;
    }
}
