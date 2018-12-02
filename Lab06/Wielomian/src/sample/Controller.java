package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import java.io.IOException;

import static java.lang.Math.pow;

public class Controller {

    @FXML
    TextField x4, x3, x2, x1, x0;
    @FXML
    TextField zakresMin, zakresMax;
    @FXML
    TextField prob;

    @FXML
    LineChart chart;

    private double function(double x) {
        return Integer.parseInt(x4.getText()) * pow(x, 4) +
                Integer.parseInt(x3.getText()) * pow(x, 3) +
                Integer.parseInt(x2.getText()) * pow(x, 2) +
                Integer.parseInt(x1.getText()) * x +
                Integer.parseInt(x0.getText());
    }

    public void setChart() {
        XYChart.Series series = new XYChart.Series();
        try {
            for (double i = Integer.parseInt(zakresMin.getText()); i < Integer.parseInt(zakresMax.getText());
                 i += Double.parseDouble(prob.getText())) {
                series.getData().add(new XYChart.Data(i, function(i)));
            }
            chart.getData().add(series);

        }
        catch (Exception e) {

        }
    }


    public void pressButton(ActionEvent event) throws IOException {
        setChart();
    }


}
