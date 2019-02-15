package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Klasa obsługująca widok startowy
 *
 * @author Sandra
 */

public class StartView {

    /**
     * Odpowiada za wyświetlenie dwóch przycisków. Jeden do wygenerowania
     * nowej krzyżówki, drugi do załadowania już zapisanej.
     *
     * @param primaryStage referencja do okna
     */

    static void view(Stage primaryStage) {

        VBox sceneVBox = new VBox(10);
        sceneVBox.setPadding(new Insets(50));

        Button generateButton = new Button("Generuj nową krzyżówkę");
        generateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GenerateView.view(primaryStage);
            }
        });

        Button browserButton = new Button("Przeglądaj zapisane krzyżówki");
        browserButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                BrowserView.view(primaryStage);
            }
        });

        sceneVBox.getChildren().addAll(generateButton, browserButton);

        Scene scene = new Scene(sceneVBox, 400, 150);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

}