package group.msg.jsf_MyBean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Movie implements Serializable
{
    private int id;
    private String name;
    private String rating;
    private Date date;
    private String genre;
    private String suitableFor;

    public Date getDate() {
        return date;
    }

    public String getGenre() {
        return genre;
    }

    public Movie()
    {

    }

    public Movie(int id,String name, String rating,Date date,String genre,String s) {

        this.id=id;
        this.name = name;
        this.rating = rating;
        this.date=date;
        this.genre=genre;
        this.suitableFor=s;
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
