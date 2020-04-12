/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.view;

import hu.unideb.inf.model.Model;
import hu.unideb.inf.model.Movie;
import hu.unideb.inf.util.HibernateUtil;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * FXML Controller class
 *
 * @author adam-
 */
public class FXMLDisplayerController implements Initializable {
    
    @FXML
    private TableView<Movie> table;
    
    @FXML
    private TableColumn<Movie, Integer> idColumn;

    @FXML
    private TableColumn<Movie, String> movieNameColumn;

    @FXML
    private TableColumn<Movie, Integer> yearColumn;

    @FXML
    private TableColumn<Movie, Double> ratingColumn;
    
    //@FXML
    //private TableColumn<Movie, String> genreColumn;
    
    public ObservableList<Movie> list = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));
        movieNameColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("movieName"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<Movie, Double>("rating"));
        //genreColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
        table.setItems(this.list);
    }
    
    public void search(String theGenre, int theYear){
        
    }
    
    public void search(int theYear){
        
        
    }
    
    public void search(String theGenre){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hqlstatement = "select b from Movie b join b.genres a where a.genre = '" + theGenre + "'";
            List<Movie> movies = session.createQuery(hqlstatement, Movie.class).list();
            ObservableList<Movie> amovies = FXCollections.observableList(movies);
            amovies.forEach(a -> System.out.println(a.toString()));
            this.list = amovies;
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    /*public void start(ObservableList<Movie> list){
        id.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("id"));
        movieName.setCellValueFactory(new PropertyValueFactory<Movie, String>("movieName"));
        year.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year"));
        rating.setCellValueFactory(new PropertyValueFactory<Movie, Double>("rating"));
        table.setItems(list);
    }*/
    
    
    
}
