package JSFExercisesClasses.Entities;

import lombok.Data;

@Data
public class Movie {

    private String name;
    private int aparitionYear;
    private int length;
    private String author;
    private String rating;
    public Movie(){

    }
    public Movie(String name, int aparitionYear, int length, String author,String rating) {
        this.name = name;
        this.aparitionYear = aparitionYear;
        this.length = length;
        this.author = author;
        this.rating = rating;
    }
}
