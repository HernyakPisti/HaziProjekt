package hu.mavenprojekt.Components.View;

import hu.mavenprojekt.Components.Model.Board;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;


public final class GameGUI implements GUI {
    private Scene root = null;
    private Stage stage = null;
    private HBox base = null;
    private BoardGUI boardGUI = null;
    private int screenWidth;
    private int screenHeight;
    private GUI parent;
    private VBox boardPane;
    private Image arrow = null;
    private ImageView imageView = null;

    public GameGUI(GUI parent, Stage stage_, int screenWidth, int screenHeight, int N, int M) {
        this.base = new HBox();
        this.stage = stage_;
        this.parent = parent;
        this.boardGUI = new BoardGUI(this, screenWidth, screenHeight, N, M);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        construct();
    }

    public GameGUI(GUI parent, Board board) {
        this.base = new HBox();
        this.stage = ((MainMenu) parent).getStage();
        this.parent = parent;
        this.screenHeight = board.getN() * 40;
        this.screenWidth = board.getM() * 40;
        this.boardGUI = new BoardGUI(this, this.screenWidth, this.screenHeight, board);
        construct();
    }

    public void construct() {
        VBox menu = new VBox();

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setOnAction(e -> {
            this.stage.setScene(((MainMenu) parent).getScene());
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(e -> {
            this.stage.close();
        });

        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> {
            this.boardGUI.getBoardController().reset(true);
        });

        Button restartButton = new Button("Restart");
        restartButton.setOnAction(e -> {
            this.boardGUI.getBoardController().reset();
        });

        Button saveButton = new Button("Save Game");
        saveButton.setOnAction(e -> {
            Stage s = new Stage();
            s.setTitle("Save game");
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./levels"));
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JSON Files", "*.json"));
            fileChooser.setInitialFileName("save.json");
            File f = fileChooser.showSaveDialog(s);
            this.boardGUI.getBoardController().Save(f);
        });

        menu.getChildren().addAll(mainMenuButton, newGameButton, restartButton, saveButton, cancelButton);
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(10);
        menu.setPadding(new Insets(10));

        this.boardPane = new VBox();

        this.boardPane.setAlignment(Pos.TOP_CENTER);
        this.boardPane.getChildren().addAll(this.boardGUI.getRoot());

        this.base.getChildren().addAll(this.boardPane, menu);
        this.root = new Scene(this.base, screenWidth + 200, screenHeight + 150);

        this.root.setOnKeyPressed(e -> {
            String ch = this.boardGUI.getBoardController().move(e);

        });
    }


    public Scene getRoot() {
        return this.root;
    }

}
