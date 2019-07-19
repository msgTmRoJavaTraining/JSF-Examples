package JSFExercisesClasses.Entities;

import lombok.Data;

@Data
public class Movie {

    private String name;
    private int aparitionYear;
    private int length;
    private String author;

    public Movie(String name, int aparitionYear, int length, String author) {
        this.name = name;
        this.aparitionYear = aparitionYear;
        this.length = length;
        this.author = author;
    }
}
