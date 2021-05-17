package hu.mavenprojekt.Components.View;

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
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.File;

/**
 * The {@link Class} that represents the game's GUI.
 */
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

    /**
     * {@link java.lang.reflect.Constructor} for {@link GameGUI}.
     *
     * @param parent_       A {@link GUI} object, this object's parent.
     * @param stage_        A {@link Stage}, the game's main stage.
     * @param screenWidth_  An {@link Integer}, the game's window's width. Must be
     *                      positive!
     * @param screenHeight_ An {@link Integer}, the game's window's height. Must be
     *                      positive!
     * @param N             An {@link Integer}, the game's {@link Board}'s N
     *                      parameter (number of rows). Must be positive!
     * @param M             An {@link Integer}, the game's {@link Board}'s M
     *                      parameter (number of columns). Must be positive!
     */
    public GameGUI(final GUI parent_, final Stage stage_, final int screenWidth_, final int screenHeight_, final int N,
                   final int M) {
        if (screenHeight_ > 0 && screenWidth_ > 0 && N > 0 && M > 0) {
            this.base = new HBox();
            this.stage = stage_;
            this.parent = parent_;
            this.boardGUI = new BoardGUI(this, screenWidth_, screenHeight_, N, M);
            this.screenWidth = screenWidth_;
            this.screenHeight = screenHeight_;
            construct();
        } else {
            Logger.error("\"screenWidth\", \"screenHeight\", \"N\" and \"M\" must be greater than 0. Got screenWidth: "
                    + screenWidth_ + ", screenHeight: " + screenHeight_ + ", N: " + N + ", M: " + M);
        }

    }

    /**
     * {@link java.lang.reflect.Constructor} for {@link GameGUI} with an existing
     * {@link Board} (loading saved games).
     *
     * @param parent_ A {@link GUI} object, this object's parent.
     * @param board   The current game's {@link Board}.
     */
    public GameGUI(final GUI parent_, final Board board) {
        this.base = new HBox();
        this.stage = ((MainMenu) parent_).getStage();
        this.parent = parent_;
        this.screenHeight = board.getN() * 40;
        this.screenWidth = board.getM() * 40;
        this.boardGUI = new BoardGUI(this, this.screenWidth, this.screenHeight, board);
        construct();
    }

    /**
     * The {@link java.lang.reflect.Method} used to construct he GUI layout of the
     * gamearea.
     */
    public void construct() {
        this.menu = new VBox();

        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setOnAction(e -> {
            Logger.info("Clicked on Main Menu");
            this.stage.setScene(((MainMenu) parent).getScene());
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(e -> {
            Logger.info("Clicked on Cancel. Clsoing the game.");
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
            String log = "Clicked on Save Game";
            Stage s = new Stage();
            s.setTitle("Save game");
            FileChooser fileChooser = new FileChooser();
            File directory = new File("./levels");
            if (!directory.exists()) {
                directory = new File("../levels");
                if (!directory.exists()) {
                    directory = new File(".");
                }
            }
            fileChooser.setInitialDirectory(directory);
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JSON Files", "*.json"));
            fileChooser.setInitialFileName("save.json");
            File f = fileChooser.showSaveDialog(s);

            if (f != null) {
                this.boardGUI.getBoardController().save(f);
                log += ": " + f.getName();
            }
            Logger.info(log);
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

        this.imageView = new ImageView();
        this.imageView.setFitHeight(80);
        this.imageView.setFitWidth(80);
        this.arrow = new Image(getClass().getResourceAsStream("/arrow.jpg"));
        rotateArrow(this.boardGUI.getBoardController().getPlayer().getCurrentHeading());

        this.directionBox.getChildren().addAll(directionLabel, imageView);

        this.boardPane.setAlignment(Pos.TOP_CENTER);
        this.boardPane.getChildren().addAll(this.directionBox, this.boardGUI.getRoot());

        this.base.getChildren().addAll(this.boardPane, menu);
        this.root = new Scene(this.base, screenWidth + 200, screenHeight + 150);

        this.root.setOnKeyPressed(e -> {
            String ch = this.boardGUI.getBoardController().move(e);
            if (ch != null) {
                if (ch == "mm") {
                    this.stage.setScene(((MainMenu) parent).getScene());
                } else if (ch == "cl") {
                    this.stage.close();
                } else {
                    rotateArrow(ch);
                }
            }
        });
    }

    /**
     * A {@link java.lang.reflect.Method} that is used to show and rotate tha
     * direction signaling arrow {@link Image}.
     *
     * @param heading A {@link String} that represents the
     *                {@link hu.mavenprojekt.Components.Model.Player}'s current
     *                direction.
     */
    private void rotateArrow(final String heading) {
        if (this.arrow != null) {
            switch (heading) {
                case "":
                    this.imageView.setImage(null);
                    break;
                case "a":
                    if (this.imageView.getImage() == null) {
                        this.imageView.setImage(this.arrow);
                    }
                    this.imageView.setRotate(180);
                    break;
                case "s":
                    if (this.imageView.getImage() == null) {
                        this.imageView.setImage(this.arrow);
                    }
                    this.imageView.setRotate(90);
                    break;
                case "d":
                    if (this.imageView.getImage() == null) {
                        this.imageView.setImage(this.arrow);
                    }
                    this.imageView.setRotate(0);
                    break;
                case "w":
                    if (this.imageView.getImage() == null) {
                        this.imageView.setImage(this.arrow);
                    }
                    this.imageView.setRotate(270);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * A {@link java.lang.reflect.Method} that returns this GUI element's root.
     *
     * @return A {@link Scene}, this GUI element's root.
     */
    public Scene getRoot() {
        return this.root;
    }

}
