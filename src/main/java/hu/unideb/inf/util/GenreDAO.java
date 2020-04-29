/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.util;

import hu.unideb.inf.model.Genre;
import java.util.List;
import net.javaguides.hibernate.entity.Animal;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author admin
 */
public interface GenreDAO extends AutoCloseable{
  
    public void saveGenre(Genre a);
    public void deleteGenre(Genre a);    
    public void updateGenre(Genre a);    
    public List<Genre> getGenres();
    
    @Override
    default public void close(){        
    }
    
}