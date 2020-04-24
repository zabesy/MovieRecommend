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
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLMovieScene.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
       
       Scene scene = new Scene(root);
       scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
        stage.setScene(scene);
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
    public static void main(String[] args) throws FileNotFoundException, IOException{
        List<Movie> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("data\\combinedMovieData.txt"));
        String theline;
        br.readLine();
        while((theline = br.readLine())!=null){
            String[] values =theline.split(",",-1);
            if(!values[3].isBlank() || !values[3].isEmpty()){
            String[] avalues = values[3].split(Pattern.quote("|"),-1);
            Movie movie = new Movie(values[1],Integer.parseInt(values[2]),Double.parseDouble(values[4]));
            for(int i=0;i<avalues.length;i++){
                movie.addGenre(new Genre(avalues[i]));
            }
            list.add(movie);
            }
        }
        
        br.close();
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            for(Movie a:list){
                session.save(a);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        
        launch(args);
    }

}
