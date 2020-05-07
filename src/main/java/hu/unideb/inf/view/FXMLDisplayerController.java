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
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * FXML Controller class
 *
 * @author adam-
 */

public class FXMLDisplayerController implements Initializable {
    
    
    @FXML private TableView<Movie> table = new TableView<Movie>();
    
    //@FXML private TableColumn<Movie,Integer>idColumn;

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
     
    
    public void implementList(ObservableList<Movie> theList){
        //if(theList.size() == 0){ print out alarm that no movies has those inputs or smth else}
        //idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        movieNameColumn.setCellValueFactory(new PropertyValueFactory<>("movieName"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
        genresColumn.setCellValueFactory(new PropertyValueFactory<>("genres"));
        
        table.setItems(theList);
        
    }
    
    
    
    public void searchYearMax(String max){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hqlstatement = "select b from Movie b where b.year <= " + Integer.parseInt(max) + " order by b.rating desc";
            List<Movie> movies = session.createQuery(hqlstatement, Movie.class).list();
            ObservableList<Movie> amovies = FXCollections.observableArrayList();
            for(int i=0;i<movies.size();i++){
                amovies.add(movies.get(i));
            }
            amovies.forEach(a -> System.out.println(a.toString()));
            
            implementList(amovies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void searchYearMin(String min){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hqlstatement = "select b from Movie b where b.year >= " + Integer.parseInt(min) + " order by b.rating desc";
            List<Movie> movies = session.createQuery(hqlstatement, Movie.class).list();
            ObservableList<Movie> amovies = FXCollections.observableArrayList();
            for(int i=0;i<movies.size();i++){
                amovies.add(movies.get(i));
            }
            amovies.forEach(a -> System.out.println(a.toString()));
            
            implementList(amovies);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void searchMaxMin(String min, String max){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hqlstatement = "select b from Movie b where b.year >= " + Integer.parseInt(min) + " and b.year <= " + Integer.parseInt(max) + " order by b.rating desc";
            List<Movie> movies = session.createQuery(hqlstatement, Movie.class).list();
            ObservableList<Movie> amovies = FXCollections.observableArrayList();
            for(int i=0;i<movies.size();i++){
                amovies.add(movies.get(i));
            }
            
            amovies.forEach(a -> System.out.println(a.toString()));
            implementList(amovies);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    public void searchYearMaxGenre(List<String> genres, String max){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hqlstatement = "select b from Movie b join b.genres a where a.genre in (:asearch) group by b having count(a)=:genre_count and b.year <= " + Integer.parseInt(max) + " order by b.rating desc";
            List<Movie> movies = session.createQuery(hqlstatement, Movie.class)
                .setParameterList("asearch",genres)
                .setInteger("genre_count",genres.size())
                .list();
            ObservableList<Movie> amovies = FXCollections.observableArrayList();
            for(int i=0;i<movies.size();i++){
                amovies.add(movies.get(i));
            }
        
            amovies.forEach(a -> System.out.println(a.toString()));
            implementList(amovies);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void searchYearMinGenre(List<String> genres, String min){
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hqlstatement = "select b from Movie b join b.genres a where a.genre in (:asearch) group by b having count(a)=:genre_count and b.year >= " + Integer.parseInt(min) + " order by b.rating desc";
            List<Movie> movies = session.createQuery(hqlstatement, Movie.class)
                    .setParameterList("asearch",genres)
                    .setInteger("genre_count",genres.size())
                    .list();
            ObservableList<Movie> amovies = FXCollections.observableArrayList();
            for(int i=0;i<movies.size();i++){
                amovies.add(movies.get(i));
            
            }
            amovies.forEach(a -> System.out.println(a.toString()));
            
            implementList(amovies);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void searchYearMinMaxGenre(List<String> genres, String min, String max){
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hqlstatement = "select b from Movie b join b.genres a where a.genre in (:asearch) group by b having count(a)=:genre_count and b.year >= " + Integer.parseInt(min) + " and b.year <= " + Integer.parseInt(max) + " order by b.rating desc";
            List<Movie> movies = session.createQuery(hqlstatement, Movie.class)
                    .setParameterList("asearch",genres)
                    .setInteger("genre_count",genres.size())
                    .list();
            ObservableList<Movie> amovies = FXCollections.observableArrayList();
            for(int i=0;i<movies.size();i++){
                amovies.add(movies.get(i));
            
            }
            amovies.forEach(a -> System.out.println(a.toString()));
            implementList(amovies);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void searchAll(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hqlstatement = "from Movie b select b order by b.rating desc";
            List<Movie> movies = session.createQuery(hqlstatement, Movie.class).list();
            ObservableList<Movie> amovies = FXCollections.observableArrayList();
            for(int i=0;i<movies.size();i++){
                amovies.add(movies.get(i));
            }
            
            amovies.forEach(a -> System.out.println(a.toString()));
            implementList(amovies);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void searchGenre(List<String> genres){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hqlstatement = "select b from Movie b join b.genres a where a.genre in (:asearch) group by b having count(a)=:genre_count order by b.rating desc";
            List<Movie> movies = session.createQuery(hqlstatement, Movie.class)
                    .setParameterList("asearch",genres)
                    .setInteger("genre_count",genres.size())
                    .list();
            ObservableList<Movie> amovies = FXCollections.observableArrayList();
            for(int i=0;i<movies.size();i++){
                amovies.add(movies.get(i));
            }
            amovies.forEach(a -> System.out.println(a.toString()));
            implementList(amovies);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
}


  