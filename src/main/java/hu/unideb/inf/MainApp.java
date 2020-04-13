package hu.unideb.inf;

import hu.unideb.inf.model.Genre;
import hu.unideb.inf.model.Model;
import hu.unideb.inf.model.Movie;
import hu.unideb.inf.util.HibernateUtil;
import hu.unideb.inf.view.FXMLMovieSceneController;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;


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
    public static void main(String[] args){
        launch(args);
    }

}
