/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.util;

import hu.unideb.inf.model.Movie;
import java.util.List;
import net.javaguides.hibernate.entity.Animal;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author admin
 */
public interface MovieDAO extends AutoCloseable{
  
    public void saveMovie(Movie a);
    public void deleteMovie(Movie a);    
    public void updateMovie(Movie a);    
    public List<Movie> getMovies();
    
    @Override
    default public void close(){        
    }
    
