/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.view;

import hu.unideb.inf.model.Genre;
import hu.unideb.inf.model.Movie;
import hu.unideb.inf.util.HibernateUtil;
import java.net.URL;
import java.time.Year;
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
    
    
    @FXML private TableView<Movie> table = new TableView<Movie>();
    
    @FXML private TableColumn<Movie,Integer>idColumn;

    @FXML private TableColumn<Movie,String>movieNameColumn;

    @FXML private TableColumn<Movie,Integer>yearColumn;

    @FXML private TableColumn<Movie,Double>ratingColumn;
    
    @FXML private TableColumn<Movie,List<String>>genresColumn;
    
    public ObservableList<Movie> list;
    
    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
     private String hqlstatement = "";
     
     
     
    public void theSearch(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           //String hqlstatement = "select b from Movie b join b.genres a where a.genre = '" + "Comedy" + "'";
            List<Movie> movies = session.createQuery(hqlstatement, Movie.class).list();
            ObservableList<Movie> amovies = FXCollections.observableArrayList();
            for(int i=0;i<movies.size();i++){
                amovies.add(movies.get(i));
            }
            amovies.forEach(a -> System.out.println(a.toString()));
            //this.list = amovies;
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            movieNameColumn.setCellValueFactory(new PropertyValueFactory<>("movieName"));
            yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
            ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
            genresColumn.setCellValueFactory(new PropertyValueFactory<>("genres"));
            table.setItems(amovies);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void searchYearMax(String max){
       setHqlstatement("select b from Movie b where b.year < " + Integer.parseInt(max));
    }
    
    public void searchYearMin(String min){
       setHqlstatement("select b from Movie b where b.year > " + Integer.parseInt(min));
    }
    
    public void searchMaxMin(String min, String max){
       setHqlstatement("select b from Movie b where b.year >" + Integer.parseInt(min) + "and b.year <" + Integer.parseInt(max));
    }
    
    //Trying to implement this one but for now just have it as a comment
    
    public void searchYearMaxGenre(List<String> genres, String max){
            /*try(Session session = HibernateUtil.getSessionFactory().openSession()){
            //setHqlstatement("select b from Movie b join b.genres a where a.genre = 'Action' and a.genre = 'Sci-Fi'");
            //String hqlstatement = "select b from Movie b join b.genres a where a.genre = '" + "Comedy" + "'";
            //String hejsan = "select b from Movie b join b.genres a where a.genre in (:search)";
            String hejsan = "select b from Movie b join b.genres a where a.genre in (:asearch) and a.genre in (:bsearch)";
            List<Movie> movies = session.createQuery(hejsan, Movie.class)
                    .setParameter("asearch",genres.get(0))
                    .setParameter("bsearch",genres.get(1))
                    .list();
            ObservableList<Movie> amovies = FXCollections.observableArrayList();
            for(int i=0;i<movies.size();i++){
                amovies.add(movies.get(i));
            }
            amovies.forEach(a -> System.out.println(a.toString()));
            
            }
            catch(Exception e){
                e.printStackTrace();
            }*/
    }
    
    public void searchYearMinGenre(List genres, String min){
       setHqlstatement("select * from Movie where year >"+min+"and genre =="+genres);      
    }
    
    public void SEARCH(List genres, String min, String max){
       setHqlstatement("select * from Movie where year >"+min+"and year<"+max+"and genre=="+genres);
    }
    
    public void search(){
        setHqlstatement("select * from Movie");
    }
    
    public String getHqlstatement() {
        return hqlstatement;
    }
    
    public void setHqlstatement(String hqlstatement) {
        this.hqlstatement = hqlstatement;
    }
    
    
    
    
}


  