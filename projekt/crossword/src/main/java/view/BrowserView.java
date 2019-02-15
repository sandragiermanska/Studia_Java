package view;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Klasa odpowiedzialna za przeglądanie istniejących krzyżówek
 * i załadowanie ich.
 *
 * @author Sandra
 */

public class BrowserView {

    /**
     * Funkcja otwierająca przeglądarkę plików i ładująca wybraną krzyżówkę.
     *
     * @param primaryStage referencja do okna
     */
    static void view(Stage primaryStage) {
        String file;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src/main/resources/crosswords"));
        File f = fileChooser.showOpenDialog(null);
        if (f != null) {
            file = f.getAbsolutePath();
            CrosswordView.loadView(primaryStage, file);
        }
    }
}
