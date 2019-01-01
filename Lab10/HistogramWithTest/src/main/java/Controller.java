import io.datafx.controller.FXMLController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.layout.AnchorPane;

@FXMLController("sample.fxml")
public class Controller extends AnchorPane implements Initializable {

    @FXML
    TextField dodajArgument;

    @FXML
    TextField dodajWartosc;

    @FXML
    private BarChart wykres;
    XYChart.Series set1 = new XYChart.Series<>();

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public void pressButtonDodaj(ActionEvent event) throws NotANumberException {
        if(isNumeric(dodajWartosc.getText())) {
            set1.getData().add(new XYChart.Data(dodajArgument.getText(), Integer.parseInt(dodajWartosc.getText())));
        }
        else {
            throw new NotANumberException();
        }
    }

    public void pressButtonWyjdz(ActionEvent event) {
        Platform.exit();
    }

    public void pressButtonWyczysc(ActionEvent event) {
        dodajArgument.setText("");
        dodajWartosc.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wykres.getData().add(set1);
    }
}
