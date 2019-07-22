package group.msg.entities;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
public class Movie {
    public String title;
    public String genre;
    public int rating;
    public Date productionDate;
    public List<String> suitableFor;

    public Movie() {}

    public Movie(String title, String genre, int rating, Date productionDate, List<String> suitableFor) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.productionDate = productionDate;
        this.suitableFor = suitableFor;
    }

    @Override
    public String toString() {
        return title + ", genre: " + genre + ", " + rating + "/5 stars" + ", launch date: " + productionDate + ", suitable for: " + suitableFor.toString();
    }

    public String getProductionDate() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd/MM/yyyy");
        return formattedDate.format(productionDate);
    }

    public String getStringRating() {
        return String.valueOf(rating);
    }

    public Date obtainProductionAsDate() {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(new SimpleDateFormat("dd/MM/yyyy").format(productionDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
