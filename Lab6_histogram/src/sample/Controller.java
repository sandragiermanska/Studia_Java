package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField dodajArgument;

    @FXML
    private TextField dodajWartosc;

    @FXML
    private TextField usunArgument;

    @FXML
    private BarChart wykres;
    XYChart.Series set1 = new XYChart.Series<>();


    public void pressButtonDodaj(ActionEvent event) {
        set1.getData().add(new XYChart.Data(dodajArgument.getText(), Integer.parseInt(dodajWartosc.getText())));
    }


    public void pressButtonWyjdz(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wykres.getData().add(set1);
    }
}
