package group.msg.entities;

import lombok.Data;

@Data
public class Movie {
    private String name;
    private double rating;
    private String genre;
    private int year;
    private String information;

    public Movie(String name, double rating, String genre, int year, String information) {
        this.name = name;
        this.rating = rating;
        this.genre = genre;
        this.year = year;
        this.information = information;
    }
}
