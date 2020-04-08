package hu.unideb.inf;

import hu.unideb.inf.model.Model;
import hu.unideb.inf.view.FXMLMovieSceneController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLMovieScene.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Movies");
        stage.setScene(scene);
        
        ((FXMLMovieSceneController)loader.getController()).setModel(new Model());
        
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
