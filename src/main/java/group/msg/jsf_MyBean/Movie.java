package group.msg.jsf_MyBean;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Movie
{
    private int id;
    private String name;
    private String rating;
    private Date date;
    private String genre;
    public Movie(){}

    public Movie(int id,String name, String rating,Date date,String genre) {

        this.id=id;
        this.name = name;
        this.rating = rating;
        this.date=date;
        this.genre=genre;
    }

    public String getName() {
        return name;
    }

    public String getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }
}
