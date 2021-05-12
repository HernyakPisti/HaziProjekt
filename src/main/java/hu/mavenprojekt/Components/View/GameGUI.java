package hu.mavenprojekt.Components.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import hu.mavenprojekt.Components.Model.Board;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import org.tinylog.Logger;

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
    private VBox menu = null;
    private HBox directionBox = null;

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
        this.menu = new VBox();

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setOnAction(e -> {
            Logger.info("Clicked on Main Menu");
            this.stage.setScene(((MainMenu) parent).getScene());
        });

        Button cancelButton = new Button("Exit");
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(e -> {
            Logger.info("Clicked on Exit");
            this.stage.close();
        });

        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> {
            Logger.info("Clicked on New Game");
            this.boardGUI.getBoardController().reset(true);
        });

        Button restartButton = new Button("Restart");
        restartButton.setOnAction(e -> {
            Logger.info("Clicked on Restart");
            this.boardGUI.getBoardController().reset();
        });

        Button saveButton = new Button("Save Game");
        saveButton.setOnAction(e -> {
            Logger.info("Clicked on Save Game");
            Stage s = new Stage();
            s.setTitle("Save game");
            FileChooser fileChooser = new FileChooser();
            File directory = new File("./levels");
            if (!directory.exists()) {
                directory = new File("../levels");
                if (!directory.exists())
                    directory = new File(".");
            }
            fileChooser.setInitialDirectory(directory);
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JSON Files", "*.json"));
            fileChooser.setInitialFileName("save.json");
            File f = fileChooser.showSaveDialog(s);

            this.boardGUI.getBoardController().Save(f);
        });

        this.menu.getChildren().addAll(mainMenuButton, newGameButton, restartButton, saveButton, cancelButton);
        this.menu.setAlignment(Pos.CENTER);
        this.menu.setSpacing(10);
        this.menu.setPadding(new Insets(10));

        this.boardPane = new VBox();

        this.directionBox = new HBox();
        this.directionBox.setAlignment(Pos.CENTER);
        this.directionBox.setSpacing(10);
        this.directionBox.setPadding(new Insets(10));

        Label directionLabel = new Label("Current direction: ");

        FileInputStream inputStream;
        this.imageView = new ImageView();
        this.imageView.setFitHeight(80);
        this.imageView.setFitWidth(80);
        this.arrow= new Image(getClass().getResourceAsStream("/arrow.jpg"));
        rotateArrow(this.boardGUI.getBoardController().getPlayer().getCurrentHeading());


        this.directionBox.getChildren().addAll(directionLabel, imageView);

        this.boardPane.setAlignment(Pos.TOP_CENTER);
        this.boardPane.getChildren().addAll(this.directionBox, this.boardGUI.getRoot());

        this.base.getChildren().addAll(this.boardPane, menu);
        this.root = new Scene(this.base, screenWidth + 200, screenHeight + 150);

        this.root.setOnKeyPressed(e -> {
            String ch = this.boardGUI.getBoardController().move(e);
            if (ch != null)
                rotateArrow(ch);
        });
    }

    private void rotateArrow(String heading) {
        if (this.arrow != null) {
            switch (heading) {
                case "":
                    this.imageView.setImage(null);
                    break;
                case "a":
                    if (this.imageView.getImage() == null)
                        this.imageView.setImage(this.arrow);
                    this.imageView.setRotate(180);
                    break;
                case "s":
                    if (this.imageView.getImage() == null)
                        this.imageView.setImage(this.arrow);
                    this.imageView.setRotate(90);
                    break;
                case "d":
                    if (this.imageView.getImage() == null)
                        this.imageView.setImage(this.arrow);
                    this.imageView.setRotate(0);
                    break;
                case "w":
                    if (this.imageView.getImage() == null)
                        this.imageView.setImage(this.arrow);
                    this.imageView.setRotate(270);
                    break;
                default:
                    break;
            }
        }
    }

    public Scene getRoot() {
        return this.root;
    }

}
