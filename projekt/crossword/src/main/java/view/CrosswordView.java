package view;

import crossword.*;
import database.InteliCrosswordDB;
import exceptions.EntryNotFound;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import strategy.SimpleStrategy;
import strategy.Strategy;

import java.io.*;
import java.util.LinkedList;

/**
 * Klasa obsługująca widok krzyżówki, jej sprawdzanie oraz zapis.
 *
 * @author Sandra
 */

public class CrosswordView {

    static GridPane crosswordGrid;
    static LinkedList<CellView> cellViewList = new LinkedList<>();
    static CellView currentCellView;

    /**
     * Funkcja generująca krzyżówkę.
     * Jeżeli nie uda się jej wygenerować podaje stosowny komunikat.
     *
     * @param primaryStage referencja do okna
     * @param width        szerokość krzyżówki razem z krawędziami
     * @param height       wysokość krzyżówki razem z krawędziami
     * @param strategy     strategia do wygenerowania krzyżówki
     */
    static void generateView(Stage primaryStage, int width, int height, Strategy strategy) {

        InteliCrosswordDB db = new InteliCrosswordDB("src/main/resources/cwdb.txt");
        Crossword crossword = new Crossword(width, height, db);

        try {
            crossword.generate(strategy);
            view(crossword, primaryStage);
        } catch (EntryNotFound e) {
            Label label = new Label("Nie udało się wygenerować krzyżówki");
            Button startScreenButton = new Button("Wróć do ekranu startowego");
            startScreenButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    StartView.view(primaryStage);
                }
            });

            VBox sceneVbox = new VBox(5);
            sceneVbox.setPadding(new Insets(10));
            sceneVbox.getChildren().addAll(label, startScreenButton);
            Scene scene = new Scene(sceneVbox);
            primaryStage.setScene(scene);
        }
    }

    /**
     * Funkcja wyświetlająca krzyżówkę.
     *
     * @param primaryStage referencja do okna
     * @param crossword    krzyżówka do wyświetlenia
     */
    private static void view(Crossword crossword, Stage primaryStage) {

        HBox sceneHBox = new HBox(10);
        sceneHBox.setPadding(new Insets(10, 10, 10, 10));

        crosswordGrid = new GridPane();
        crosswordGrid.setPadding(new Insets(0, 0, 0, 5));

        Button startScreenButton = new Button("Wróć do ekranu startowego");
        startScreenButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StartView.view(primaryStage);
            }
        });

        Button validateButton = new Button("Sprawdź");
        validateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setTitle(null);
                message.setHeaderText(null);
                if (isValidCrossword(crossword.getBoard())) {
                    message.setContentText("Krzyżówka wypełniona prawidłowo");
                } else {
                    message.setContentText("Krzyżówka zawiera błędy, popraw i spróbuj ponownie");
                }
                message.showAndWait();
            }
        });

        Button serializableButton = new Button("Zapisz");
        serializableButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (saveCrossword(crossword)) {
                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setTitle("Zapis do pliku");
                    message.setHeaderText(null);
                    message.setContentText("Zapisano pomyślnie");
                    message.showAndWait();
                }
            }
        });

        HBox buttons = new HBox(5);
        buttons.getChildren().addAll(validateButton, serializableButton, startScreenButton);

        VBox crosswordAndButtons = new VBox(5);
        crosswordAndButtons.getChildren().addAll(crosswordGrid, buttons);

        VBox cluesVBox = new VBox(5);

        //żeby gdy za duża to dało się przesuwać
        ScrollPane cluesScrollPane = new ScrollPane();
        ScrollPane crosswordScrollPane = new ScrollPane();
        cluesScrollPane.setContent(cluesVBox);
        crosswordScrollPane.setContent(crosswordAndButtons);

        sceneHBox.getChildren().addAll(cluesScrollPane, crosswordScrollPane);

        int iVisible = 1;

        LinkedList<CwEntry> entryLinkedList = crossword.getEntries();
        cellViewList.clear();
        int size = entryLinkedList.size();

        for (int i = 0; i < size; i++) {

            if (crossword.getStrategy() == SimpleStrategy.class && i == 0) continue;

            CwEntry entry = crossword.getEntry(i);

            Label number = new Label(String.valueOf(iVisible));
            Label clue = new Label(iVisible + ". " + entry.getClue());

            //dodawanie numerków przed hasłem
            if (entry.isVisible()) {
                if (entry.getDirection() == Direction.HORIZ) {
                    crosswordGrid.add(number, entry.getFirstPosX() - 1, entry.getFirstPosY());
                    GridPane.setHalignment(number, HPos.RIGHT);
                } else {
                    crosswordGrid.add(number, entry.getFirstPosX(), entry.getFirstPosY() - 1);
                    GridPane.setValignment(number, VPos.BOTTOM);
                }
            }

            int horiz = 0, vert = 0;

            if (entry.getDirection() == Direction.HORIZ) {
                horiz = 1;
            } else {
                vert = 1;
            }

            CellView previousCellView = null;

            //dodawanie pól tekstowych
            for (int j = 0; j < entry.getWord().length(); j++) {
                int x = entry.getFirstPosX() + j * horiz;
                int y = entry.getFirstPosY() + j * vert;

                CellView cellView = new CellView(x, y);
                if (j == 0) {
                    cellViewList.add(cellView);
                } else {
                    previousCellView.setNext(cellView);
                }
                previousCellView = cellView;

                LimitedTextField textField = new LimitedTextField();
                textField.setMaxLength(1); //tylko jedna litera
                textField.setPointOnBoard(new Point(x, y));
                textField.setPrefSize(25, 10);

                Executeable onKeyPressed = s -> onKeyPressed(s);
                textField.setOnKeyPressed(onKeyPressed);

                Executeable onClick = s -> onClick(s);
                textField.setOnClick(onClick);

                LimitedTextField nodeFromGridPane = (LimitedTextField) getNodeFromGridPane(x, y);
                if (nodeFromGridPane == null) {
                    crosswordGrid.add(textField, x, y);
                }
            }
            if (entry.isVisible()) {
                cluesVBox.getChildren().add(clue);
                iVisible++;

            }
        }

        currentCellView = cellViewList.getFirst();

        Scene scene = new Scene(sceneHBox);
        primaryStage.setScene(scene);
    }

    /**
     * Funkcja wywoływana po kliknięciu na pole tekstowe.
     * Ustawia wskaźnik aktualnej komórki (currentCellView) na kliknięte pole tekstowe.
     *
     * @param textField kliknięte pole tekstowe
     */
    static void onClick(LimitedTextField textField) {
        for (CellView cellView : cellViewList) {
            if (cellView.getX() == textField.getPointOnBoard().getX() &&
                    cellView.getY() == textField.getPointOnBoard().getY()) {

                currentCellView = cellView;
                return;
            }
        }

        for (CellView cellView : cellViewList) {
            CellView temp = cellView.getNext();
            while (temp != null) {
                if (temp.getX() == textField.getPointOnBoard().getX() &&
                        temp.getY() == textField.getPointOnBoard().getY()) {

                    currentCellView = temp;
                    return;
                }
                temp = temp.getNext();
            }
        }
    }

    /**
     * Funkcja wywoływana po wprowadzeniu litery do danego pola tekstowego.
     * Przesuwa wskażnik na następne pole tekstowe w wyrazie.
     *
     * @param textField pole tekstowe do którego coś wprowadzono
     */
    static void onKeyPressed(LimitedTextField textField) {
        int x, y;
        if (currentCellView == null) {
            onClick(textField);
        }

        if (currentCellView != null) {
            currentCellView = currentCellView.getNext();
            if (currentCellView != null) {
                x = currentCellView.getX();
                y = currentCellView.getY();
                Node node = getNodeFromGridPane(x, y);
                if (node instanceof LimitedTextField) {
                    (node).requestFocus();
                }
            }
        }

    }

    /**
     * Funkcja ładująca istniejącą krzyżówkę z pliku.
     *
     * @param primaryStage     referencja do okna
     * @param fileAbsolutePath ścieżka do pliku z zserializowaną krzyżówką
     */
    static void loadView(Stage primaryStage, String fileAbsolutePath) {
        Crossword crossword;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileAbsolutePath))) {
            crossword = (Crossword) inputStream.readObject();
            view(crossword, primaryStage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funkcja pobierająca pole tekstowe z siatki na podstawie współrzędnych.
     *
     * @param col numer kolumny
     * @param row numer wiersza
     */
    static private Node getNodeFromGridPane(int col, int row) {
        for (Node node : crosswordGrid.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    /**
     * Funkcja sprawdzająca czy krzyżówka jest wypełniona prawidłowo.
     *
     * @param board tablica krzyżówki z poprawnymi literami
     * @return czy krzyżówka jest wypełniona poprawnie
     */
    static private boolean isValidCrossword(Board board) {
        for (Node node : crosswordGrid.getChildren()) {
            if (node instanceof LimitedTextField) {
                int x = GridPane.getColumnIndex(node);
                int y = GridPane.getRowIndex(node);
                if (!((LimitedTextField) node).getText().equals(board.getCell(x, y).getContent().toUpperCase())) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * Funkcja zapisująca krzyżówkę do pliku.
     *
     * @param crossword krzyżówka do zapisania
     * @return informacja czy zapis się powiódł
     */
    static private boolean saveCrossword(Crossword crossword) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/crosswords/" + crossword.getID() + ".bin"))) {
            outputStream.writeObject(crossword);
        } catch (IOException e) {
            File file = new File("src/main/resources/crosswords/" + crossword.getID() + ".bin");
            file.delete();
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setTitle("Zapis do pliku");
            message.setHeaderText(null);
            message.setContentText("Zapis nie powiódł się.");
            message.showAndWait();
            return false;
        }
        return true;
    }
}
