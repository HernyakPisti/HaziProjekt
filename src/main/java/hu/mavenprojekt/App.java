package hu.mavenprojekt;


import hu.mavenprojekt.Components.View.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class App extends Application {
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

    public static void main(String[] args) {

        launch();

    }
}
