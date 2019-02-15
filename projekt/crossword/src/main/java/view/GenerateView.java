package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import strategy.AdvancedStrategy;
import strategy.SimpleStrategy;
import strategy.Strategy;

/**
 * Klasa odpowiedzialna za widok ustalania wymiarów i strategii
 * nowej krzyżówki.
 *
 * @author Sandra
 */

public class GenerateView {

    /**
     * Odpowiada za wyświetlenie pól do podania wysokości i szerokosci krzyżówki
     * oraz wybrania strategii.
     *
     * @param primaryStage referencja do okna
     */
    static void view(Stage primaryStage) {

        String widthQuestion = "Podaj maksymalną szerokość krzyżówki";
        String heightQuestion = "Podaj maksymalną wysokość krzyżówki";

        String simpleStrategyOption = "krzyżówka pozioma";
        String advancedStrategyOption = "krzyżówka mieszana";

        Label widthLabel = new Label(widthQuestion);
        TextField widthTextField = new TextField();

        Label heightLabel = new Label(heightQuestion);
        TextField heightTextField = new TextField();

        ObservableList<String> options = FXCollections.observableArrayList(
                simpleStrategyOption,
                advancedStrategyOption
        );

        final ComboBox comboBox = new ComboBox(options);
        comboBox.setValue(simpleStrategyOption);

        Button button = new Button("Generuj");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int width = 0, height = 0;
                if (tryParseInt(widthTextField.getText()) &&
                        tryParseInt(heightTextField.getText())) {

                    width = Integer.parseInt(widthTextField.getText());
                    height = Integer.parseInt(heightTextField.getText());
                }
                Strategy strategy;
                if (comboBox.getValue() == simpleStrategyOption) {
                    strategy = new SimpleStrategy();
                } else {
                    strategy = new AdvancedStrategy();
                }
                if (width > 0 && height > 0) {
                    try {
                        CrosswordView.generateView(primaryStage, width + 2, height + 2, strategy);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(comboBox, button);

        VBox sceneVBox = new VBox(5);
        sceneVBox.getChildren().addAll(widthLabel, widthTextField, heightLabel, heightTextField, hBox);

        Scene scene = new Scene(sceneVBox, 400, 150);
        primaryStage.setScene(scene);

    }

    /**
     * Funkcja sprawdzająca czy można parsować stringa na integera
     *
     * @param value wartość string parsowana
     * @return informacja czy można parsować
     */
    private static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
