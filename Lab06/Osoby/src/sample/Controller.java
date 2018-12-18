package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    private DB db = new DB();

    private ObservableList<String> sortChoiceList = FXCollections.observableArrayList(
            "pierwszym imieniu",
            "drugim imieniu",
            "nazwisku",
            "numerze pesel",
            "dacie urodzenia");


    @FXML
    private ChoiceBox sortChoice;

    @FXML
    private Button sortButton;

    @FXML
    private TextArea sortOutput;

    @FXML
    private TextField addFullName, addPesel, addPhone;

    @FXML
    private Button addButton;

    public void pressSortButton(ActionEvent event) {
        if (sortChoice.getValue().equals("pierwszym imieniu")) {
            db.sort(new ComparatorFirstName());
        }
        else if (sortChoice.getValue().equals("drugim imieniu")) {
            db.sort(new ComparatorSecondName());
        }
        else if (sortChoice.getValue().equals("nazwisku")) {
            db.sort(new ComparatorSurname());
        }
        else if (sortChoice.getValue().equals("numerze pesel")) {
            db.sort(new ComparatorPesel());
        }
        else if (sortChoice.getValue().equals("dacie urodzenia")) {
            db.sort(new ComparatorBirthdate());
        }
        String result = "";
        for (Person p : db) {
            result = result + p.getFullName() + "\t" + p.getPesel() + "\t" + p.getPhone() + "\n";
        }
        sortOutput.setText(result);
    }

    public void pressAddButton(ActionEvent event) {
        Person p = new Person(
                addFullName.getText(),
                addPhone.getText(),
                addPesel.getText());
        db.addPerson(p);
        addFullName.setText("");
        addPhone.setText("");
        addPesel.setText("");

    }

    @FXML
    public void initialize() {
        sortChoice.setItems(sortChoiceList);
        Person person = new Person("Antoni Zbigniew Kowalski", "987654321", "10212112345");
        Person person1 = new Person("Dawid Dawid Dawid", "123123123", "98010199999");
        Person person2 = new Person("Jan Nowak", "456456456", "50121266566");
        db.addPerson(person);
        db.addPerson(person1);
        db.addPerson(person2);
    }
}
