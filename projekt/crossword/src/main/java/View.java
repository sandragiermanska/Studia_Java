import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Iterator;

public class View extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Krzyżówka");
        startOption();
    }

    public void startOption() {
        Label widthLabel = new Label();
        widthLabel.setText("Podaj maksymalną szerokość krzyżówki");
        TextField widthTextField = new TextField();

        Label heightLabel = new Label();
        heightLabel.setText("Podaj maksymalną wysokość krzyżówki");
        TextField heightTextField = new TextField();

        ObservableList<String> options = FXCollections.observableArrayList(
                "krzyżówka pozioma",
                "krzyżówka mieszana"
        );
        final ComboBox comboBox = new ComboBox(options);
        comboBox.setValue(options.get(0));

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
                if (comboBox.getValue() == "krzyżówka pozioma") {
                    strategy = new SimpleStrategy();
                } else {
                    strategy = new AdvancedStrategy();
                }
                if (width > 0 && height > 0) {
                    try {
                        startCrossword(width, height, strategy);
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
        primaryStage.show();


    }

    public void startCrossword(int width, int height, Strategy strategy) throws Exception {

        HBox sceneHBox = new HBox(10);
        sceneHBox.setPadding(new Insets(10,10,10,10));

        GridPane crosswordSpace = new GridPane();
        crosswordSpace.setPadding(new Insets(0,0,0,5));

        Button button = new Button("Wygeneruj kolejną");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startOption();
            }
        });

        VBox clues = new VBox(5);
        VBox crosswordAndButton = new VBox(5);

        crosswordAndButton.getChildren().addAll(crosswordSpace, button);
        sceneHBox.getChildren().addAll(clues, crosswordAndButton);

        InteliCrosswordDB db = new InteliCrosswordDB("C:\\Users\\Sandra\\Desktop\\Studia\\III semestr\\PO_Java\\Repo\\projekt\\crossword\\src\\main\\resources\\cwdb.txt");
        Crossword crossword = new Crossword(width,height,db);
        try {
            crossword.generate(strategy);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Board board = crossword.getBoardCopy();

        Iterator<CwEntry> iterator = crossword.getROEntryIter();
        int i = 0, iVisible = 1;

        while (iterator.hasNext()) {
            iterator.next();
            i++;

            CwEntry entry = crossword.getEntry(i - 1);

            Label number = new Label(String.valueOf(iVisible));
            Label clue = new Label(iVisible + ". " + entry.getClue());

            //dodawanie numerków przed hasłem
            if (entry.isVisible()) {
                if (entry.getDirection() == Direction.HORIZ) {
                    crosswordSpace.add(number, entry.getFirstPosX() - 1, entry.getFirstPosY());
                    GridPane.setHalignment(number, HPos.RIGHT);
                } else {
                    crosswordSpace.add(number, entry.getFirstPosX(), entry.getFirstPosY() - 1);
                    GridPane.setValignment(number, VPos.BOTTOM);
                }
            }

            int horiz = 0, vert = 0;

            if (entry.getDirection() == Direction.HORIZ) {
                horiz = 1;
            } else {
                vert = 1;
            }

            //dodawanie pól tekstowych
            for (int j = 0; j < entry.getWord().length(); j++) {
                LimitedTextField textField = new LimitedTextField();
                textField.setMaxLength(1); //tylko jedna litera
                textField.setPrefSize(25,10);
                if (getNodeFromGridPane(crosswordSpace, entry.getFirstPosX() + j*horiz, entry.getFirstPosY() + j*vert) == null) {
                    crosswordSpace.add(textField, entry.getFirstPosX() + j * horiz, entry.getFirstPosY() + j * vert);
                }
            }
            if (entry.isVisible()) {
                clues.getChildren().add(clue);
                iVisible++;

            }
        }

        Scene scene = new Scene(sceneHBox);

        App.printBoard(board);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

}
