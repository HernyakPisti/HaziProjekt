package hu.mavenprojekt;

import hu.mavenprojekt.Components.View.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The class that is inherited from javafx {@link Application}.
 */
public final class App extends Application {

    /**
     * A {@link java.lang.reflect.Method} that starts the javafx
     * {@link Application}.
     *
     * @param stage A {@link Stage}, the game's window.
     */
    @Override
    public void start(final Stage stage) {

        int screenWidth = 500;
        int screenHeight = 300;

        MainMenu m = new MainMenu(stage);
        Scene scene = new Scene(m.getRoot(), screenWidth, screenHeight);
        m.setScene(scene);

        stage.setScene(scene);
        stage.setTitle("Game");
        stage.show();
    }

    /**
     * The original main {@link java.lang.reflect.Method}.
     *
     * @param args The program's arguments.
     */
    public static void main(final String[] args) {

        launch();

    }
}
