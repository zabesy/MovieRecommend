/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;

import hu.unideb.inf.util.HibernateUtil;
import java.util.List;
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

    public Model() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Movie> movies = session.createQuery("from Movie", Movie.class).list();
            movies.forEach(s -> System.out.println(s.getMovieName()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        
    }
    
    
    
}
