package group.msg.jsf_MyBean;

import lombok.Data;

@Data
public class Movie
{
    private int id;
    private String name;
    private String rating;

    public Movie(){}

    public Movie(int id,String name, String rating) {

        this.id=id;
        this.name = name;
        this.rating = rating;
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
