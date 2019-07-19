package group.msg.jsf_beans.Day10;

import lombok.Data;

@Data
public class Movie {

    public String name;
    public String genre;
    public int rating;

    public Movie(){}

    public Movie(String name, String genre, int rating) {
        this.name = name;
        this.genre = genre;
        this.rating = rating;
    }
    public Movie(Movie m) {
        this.name = m.name;
        this.genre =m.genre;
        this.rating = m.rating;
    }

}
