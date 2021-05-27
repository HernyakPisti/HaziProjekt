package hu.mavenproject.components.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import hu.mavenproject.components.model.Result;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import org.tinylog.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A {@link Class} for the leaderboard GUI.
 */
public class ResultsGUI implements GUI {

    private final GUI parent;
    private ListView<Result> view;
    private VBox root;

    /**
     * {@link java.lang.reflect.Constructor} for {@link ResultsGUI}.
     *
     * @param parent_ {@link GUI} object, this {@link Object}'s parent.
     */
    public ResultsGUI(GUI parent_) {
        this.parent = parent_;
        construct();
    }

    /**
     * A {@link java.lang.reflect.Method} that contructs the layout and list out the
     * results.
     */
    private void construct() {
        this.root = new VBox();

        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            ((MainMenu) this.parent).getStage().setScene(((MainMenu) this.parent).getScene());
        });

        this.view = null;
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        File file = Paths.get("./results.json").toFile();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.error(ex.getMessage());
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
        ObservableList<Result> items = FXCollections.observableArrayList(resultList);
        Comparator<Result> comparator = Comparator.comparingInt(Result::getScore);
        FXCollections.sort(items, comparator);
        this.view = new ListView<Result>(items);
        this.root.getChildren().addAll(this.view, backButton);
    }

    /**
     * A {@link java.lang.reflect.Method} that returns this GUI element's root.
     *
     * @return A {@link VBox}, this GUI's root.
     */
    public VBox getRoot() {
        return this.root;
    }
}