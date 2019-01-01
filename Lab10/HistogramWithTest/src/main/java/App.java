import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Flow flow = new Flow(Controller.class);
        FlowHandler handler = flow.createHandler();
        StackPane scene = handler.start();

        primaryStage.setTitle("Histogram");
        primaryStage.setScene(new Scene(scene));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
