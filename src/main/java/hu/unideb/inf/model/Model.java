/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import hu.unideb.inf.util.HibernateUtil;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author adam-
 */
public class Model {
    private Movie movie;
    Transaction transaction = null;
    
    public Movie getMovie(){
        return movie;
    }

    public Model() throws FileNotFoundException, IOException {
        /*try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Movie> movies = session.createQuery("from Movie", Movie.class).list();
            movies.forEach(s -> System.out.println(s.getMovieName()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        */
        
        //Add the genres and movies
        
        List<Movie> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("data/combinedMovieData.txt"));
        String theline;
        br.readLine();
        while((theline = br.readLine())!=null){
            String[] values =theline.split(",",-1);
            //This one doesn't work
            String[] avalues = values[3].split(Pattern.quote("|"),-1);
            Movie movie = new Movie(values[1],Integer.parseInt(values[2]),Double.parseDouble(values[4]));
            for(int i=0;i<avalues.length;i++){
                movie.addGenre(new Genre(avalues[i]));
            }
            list.add(movie);
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
        
        
    }
    
    
    
}
