package hu.mavenproject.components.view;

import hu.mavenproject.components.model.Board;
import hu.mavenproject.components.model.Board.DIRECTION;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.tinylog.Logger;

/**
 * The {@link Class} that represents the game's GUI.
 */
public final class GameGUI implements GUI {
    private Scene root = null;
    private Stage stage = null;
    private HBox base = null;
    private BoardGUI boardGUI = null;
    private final int screenWidth;
    private final int screenHeight;
    private final GUI parent;
    private VBox boardPane;
    private Image arrow = null;
    private ImageView imageView = null;
    private VBox menu = null;
    private HBox directionBox = null;
    private HBox scoreBox = null;
    private Label scoreLabel;

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

        Button restartButton = new Button("Restart");
        restartButton.setOnAction(e -> {
            Logger.info("Clicked on Restart");
            this.boardGUI.getBoardController().reset();
            rotateArrow(this.boardGUI.getBoardController().getBoard().getPlayer().getStartDirection());
            this.scoreLabel
                    .setText(String.valueOf(this.boardGUI.getBoardController().getBoard().getPlayer().getScore()));
        });

        this.menu.getChildren().addAll(mainMenuButton, restartButton, cancelButton);
        this.menu.setAlignment(Pos.CENTER);
        this.menu.setSpacing(10);
        this.menu.setPadding(new Insets(10));

        this.boardPane = new VBox();

        this.scoreBox = new HBox();
        this.scoreBox.setAlignment(Pos.CENTER);
        this.scoreBox.setSpacing(10);
        this.scoreBox.setPadding(new Insets(10, 0, 0, 0));

        Label scoreTextLabel = new Label("Score:");
        this.scoreLabel = new Label("0");

        this.scoreBox.getChildren().addAll(scoreTextLabel, scoreLabel);

        this.directionBox = new HBox();
        this.directionBox.setAlignment(Pos.CENTER);
        this.directionBox.setSpacing(10);

        Label directionLabel = new Label("Current direction: ");

        this.imageView = new ImageView();
        this.imageView.setFitHeight(80);
        this.imageView.setFitWidth(80);
        this.arrow = new Image(getClass().getResourceAsStream("/arrow.jpg"));
        rotateArrow(this.boardGUI.getBoardController().getBoard().getPlayer().getCurrentDirection());

        this.directionBox.getChildren().addAll(directionLabel, imageView);

        this.boardPane.setAlignment(Pos.TOP_CENTER);
        this.boardPane.getChildren().addAll(this.scoreBox, this.directionBox, this.boardGUI.getRoot());

        this.base.getChildren().addAll(this.boardPane, this.menu);
        this.root = new Scene(this.base, screenWidth + 300, screenHeight + 350);

        this.root.setOnKeyPressed(e -> {
            int res = this.boardGUI.getBoardController().move(e);
            if (res > -1) {
                this.scoreLabel
                        .setText(String.valueOf(this.boardGUI.getBoardController().getBoard().getPlayer().getScore()));
                if (res == 6) {
                    this.stage.setScene(((MainMenu) parent).getScene());
                } else if (res == 7) {
                    this.stage.close();
                } else {
                    rotateArrow(res);
                }
            }
        });
    }

    /**
     * A {@link java.lang.reflect.Method} that is used to show and rotate tha
     * direction signaling arrow {@link Image}.
     *
     * @param heading A {@link String} that represents the
     *                {@link hu.mavenproject.components.model.Player}'s current
     *                direction.
     */
    private void rotateArrow(final int heading) {
        if (this.arrow != null) {
            switch (heading) {
                case DIRECTION.NONE:
                    this.imageView.setImage(null);
                    break;
                case DIRECTION.LEFT:
                    if (this.imageView.getImage() == null) {
                        this.imageView.setImage(this.arrow);
                    }
                    this.imageView.setRotate(180);
                    break;
                case DIRECTION.DOWN:
                    if (this.imageView.getImage() == null) {
                        this.imageView.setImage(this.arrow);
                    }
                    this.imageView.setRotate(90);
                    break;
                case DIRECTION.RIGHT:
                    if (this.imageView.getImage() == null) {
                        this.imageView.setImage(this.arrow);
                    }
                    this.imageView.setRotate(0);
                    break;
                case DIRECTION.UP:
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

    /**
     * A {@link java.lang.reflect.Method} that returns this GUI element's root.
     *
     * @return A {@link Scene}, this GUI element's root.
     */
    public BoardGUI getBoardGUI() {
        return this.boardGUI;
    }

}
