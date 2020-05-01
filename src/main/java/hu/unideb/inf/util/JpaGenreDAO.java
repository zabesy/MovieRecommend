/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.util;

import hu.unideb.inf.model.Genre;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author admin
 */
public class JpaGenreDAO implements GenreDAO {

    Session session;
    Transaction transaction;

    public JpaGenreDAO() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public void close(){
        session.close();
        System.out.println("DAO closed...");
    }

    @Override
    public void saveGenre(Genre a) {
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
    public void deleteGenre(Genre a) {
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
    public void updateGenre(Genre a) {
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
    
    public List<Genre> getGenres(){
        String hql = "FROM net.javaguides.hibernate.entity.Genre";
        Query query = session.createQuery(hql);
        return query.list();
    }

   
    
}
