import static org.junit.Assert.assertEquals;

import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.FlowView;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class AppTest extends ApplicationTest {

    private Controller controller;

    @Override
    public void start(Stage stage) throws Exception {
        Flow flow = new Flow(Controller.class);
        FlowHandler handler = flow.createHandler();
        StackPane scene = handler.start();
        stage.setScene(new Scene(scene));
        FlowView view = handler.getCurrentView();
        controller = (Controller) view.getViewContext().getController();
    }

    @Test
    public void testWyczysc() {
        controller.dodajArgument.setText("abc");
        controller.dodajWartosc.setText("5");
        controller.pressButtonWyczysc(new ActionEvent());
        assertEquals("", controller.dodajArgument.getText());
        assertEquals("", controller.dodajWartosc.getText());
    }

    @Test
    public void testDodaj() throws NotANumberException {
        controller.dodajArgument.setText("abc");
        controller.dodajWartosc.setText("5");
        controller.pressButtonDodaj(new ActionEvent());
        assertEquals("abc", ((XYChart.Data) controller.set1.getData().get(0)).getXValue());
        assertEquals(5.0, ((XYChart.Data) controller.set1.getData().get(0)).getYValue());
    }

    @Test(expected = NotANumberException.class)
    public void throwsNotANumberException() throws Exception{
        controller.dodajArgument.setText("abc");
        controller.dodajWartosc.setText("abc");
        controller.pressButtonDodaj(new ActionEvent());
    }

}
