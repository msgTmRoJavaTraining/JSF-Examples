package group.msg.entities;

import lombok.Data;

@Data
public class Movie {
    public String title;
    public String genre;
    public int rating;
    public int productionYear;

    public Movie() {}

    public Movie(String title, String genre, int rating, int productionYear) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.productionYear = productionYear;
    }

    @Override
    public String toString() {
        return title + ", genre: " + genre + ", " + rating + "/5 stars" + ", launch date: " + productionYear;
    }
}
