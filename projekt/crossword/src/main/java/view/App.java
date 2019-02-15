package view;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Klasa aplikacji z metodą main.
 *
 * @author Sandra
 */

public class App extends Application {

    /**
     * Ustawia tytuł okna i wywołuje widok okna startowego.
     *
     * @param primaryStage referencja do okna
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Krzyżówka");
        StartView.view(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}