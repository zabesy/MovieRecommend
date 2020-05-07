/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.util;

import hu.unideb.inf.model.Movie;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author admin
 */
public class JpaMovieDAO implements MovieDAO {

    Session session;
    Transaction transaction;

    public JpaMovieDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public void close(){
        session.close();
        System.out.println("DAO closed...");
    }

    @Override
    public void saveMovie(Movie a) {
        try {
            transaction = session.beginTransaction();
            session.save(a);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void deleteMovie(Movie a) {
        try {
            transaction = session.beginTransaction();
            session.remove(a);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    
    @Override
    public void updateMovie(Movie a) {
        try {
            transaction = session.beginTransaction();
            session.update(a);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

   
    @Override
    public List<Movie> getMovies() {
       String hql = "FROM hu.unideb.inf.model.Movie";
       Query query = session.createQuery(hql);
       return query.list();
    }
    
}
