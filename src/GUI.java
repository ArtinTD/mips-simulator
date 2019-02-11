import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GUI extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("design.fxml"));
//        loader.setLocation(new URL("file:///C:/data/hello-world.fxml"));
//        GUIController controller = new GUIController();
//        loader.setController(controller);
        VBox vbox = loader.<VBox>load();

        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        Application.launch(args);
    }
}
