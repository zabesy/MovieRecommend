/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "movie_name")
    private String movieName;
    @Column(name = "year")
    private int year;
    @Column(name = "rating")
    private double rating;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private List<Genre> genres = new ArrayList<>();

    public void addGenre(Genre a) {
        genres.add(a);
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public Movie(String movieName, int year, double rating) {
        this.movieName = movieName;
        this.year = year;
        this.rating = rating;
    }
    
    public Movie(){
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    
    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", movieName=" + movieName + ", year=" + year + ", rating=" + rating + ", genres=" + genres + '}';
    }
    
    
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(name = "moviename")
    private String movieName;

    @Column(name = "year")
    private int year;
    
    @Column(name="genre")
    private String genre;
    
    @Column(name = "rating")
    private double rating;
    

    public Movie() {
    }

    public Movie(String movieName, int year, String genre, double rating) {
        this.movieName = movieName;
        this.year = year;
        this.genre = genre;
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", movieName=" + movieName + ", year=" + year + ", genre=" + genre + ", rating=" + rating + '}';
    }
    */

    

    

}