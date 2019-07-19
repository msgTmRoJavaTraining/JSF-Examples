package group.msg.jsf_MyBean;

import lombok.Data;

@Data
public class Movie
{
    private String name;
    private int rating;

    public Movie(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }
}
