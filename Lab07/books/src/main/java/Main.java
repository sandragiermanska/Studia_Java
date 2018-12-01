import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    DB database = new DB();

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox box = new VBox(5);

        HBox searching = new HBox(5);
        ObservableList<String> options = FXCollections.observableArrayList(
                "wyszukaj wszystkie dane",
                "wyszukaj po nazwisku autora",
                "wyszukaj po numerze ISBN"
        );
        final ComboBox comboBox = new ComboBox(options);
        comboBox.setPromptText("opcja");

        final TextField argument = new TextField();
        argument.setText("nazwisko autora/nr ISBN");

        Button buttonSearch = new Button("Wyszukaj");
        final TextArea result = new TextArea();
        result.setEditable(false);

        final Label isAddedLabel = new Label();

        buttonSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                isAddedLabel.setText("");
                if (comboBox.getValue() == "wyszukaj wszystkie dane") {
                    String temp = database.selectAll();
                    if (temp == null) {
                        Platform.exit();
                    }
                    result.setText(temp);
                } else if (comboBox.getValue() == "wyszukaj po nazwisku autora") {
                    String temp = database.selectByAuthor(argument.getText());
                    if (temp == null) {
                        Platform.exit();
                    }
                    result.setText(temp);
                } else if (comboBox.getValue() == "wyszukaj po numerze ISBN") {
                    String temp = database.selectByISBN(argument.getText());
                    if (temp == null) {
                        Platform.exit();
                    }
                    result.setText(temp);
                } else {
                    result.setText("Nie wybrano opcji");
                }
            }
        });

        searching.getChildren().addAll(comboBox, argument, buttonSearch);

        HBox addArguments = new HBox(5);
        final TextField addIsbn = new TextField();
        addIsbn.setText("ISBN");
        final TextField addTitle = new TextField();
        addTitle.setText("tytuł");
        final TextField addAuthor = new TextField();
        addAuthor.setText("autor");
        final TextField addYear = new TextField();
        addYear.setText("rok");
        addArguments.getChildren().addAll(addIsbn, addTitle, addAuthor, addYear);
        Button add = new Button("Dodaj");

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean isAdded = database.addBook(addIsbn.getText(), addTitle.getText(),
                        addAuthor.getText(), addYear.getText());
                if (isAdded) {
                    isAddedLabel.setText("dodano rekord");
                } else {
                    isAddedLabel.setText("błąd przy dodawaniu rekordu");
                }
            }
        });

        box.getChildren().addAll(searching, result, addArguments, add, isAddedLabel);
        Scene scene = new Scene(box, 600, 400);


        primaryStage.setTitle("Books");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
