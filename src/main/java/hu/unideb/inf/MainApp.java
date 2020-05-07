package hu.unideb.inf;

import hu.unideb.inf.model.Genre;
import hu.unideb.inf.model.Model;
import hu.unideb.inf.model.Movie;
import hu.unideb.inf.util.GenreDAO;
import hu.unideb.inf.util.HibernateUtil;
import hu.unideb.inf.util.JpaGenreDAO;
import hu.unideb.inf.util.JpaMovieDAO;
import hu.unideb.inf.util.MovieDAO;
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
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class MainApp extends Application {
    private double xoffset = 0;
    private double yoffset = 0;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLMovieScene.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               xoffset = event.getSceneX();
               yoffset = event.getSceneY();
           }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {

           @Override
           public void handle(MouseEvent event) {
            stage.setX(event.getScreenX()- xoffset);
            stage.setY(event.getScreenY()- yoffset);
           }
        });
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
        //GenreDAO genreDAO = new JpaGenreDAO();
        //MovieDAO movieDAO = new JpaMovieDAO();
        while((theline = br.readLine())!=null){
            String[] values =theline.split(",",-1);
            if(!values[3].isBlank() || !values[3].isEmpty()){
            String[] avalues = values[3].split(Pattern.quote("|"),-1);
            Movie movie = new Movie(values[1],Integer.parseInt(values[2]),Double.parseDouble(values[4]));
            //movieDAO.saveMovie(movie);
            for(int i=0;i<avalues.length;i++){
                Genre gen = new Genre(avalues[i]);
                movie.addGenre(gen);
                //genreDAO.saveGenre(gen);
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
