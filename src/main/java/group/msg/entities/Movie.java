package group.msg.entities;

import lombok.Data;

@Data
public class Movie {
    private String name;
    private double rating;
    private String genre;
    private int year;
    private String information;
    private String[] suitableFor;

    public Movie(String name, double rating, String genre, int year, String information, String[] suitableFor) {
        this.name = name;
        this.rating = rating;
        this.genre = genre;
        this.year = year;
        this.information = information;
        this.suitableFor = suitableFor;
    }

    public enum public_category {
        Kids, Teenagers, Adults, Seniors
    }
}
