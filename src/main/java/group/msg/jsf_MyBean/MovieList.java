package group.msg.jsf_MyBean;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
public class MovieList {
    private String name;
    private int id;
    private String rating;

    private List<Movie> list = new ArrayList<>();

    public void addingMovie() {
        list.add(new Movie(id,name, rating));
    }
}

