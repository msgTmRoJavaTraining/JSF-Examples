package group.msg.jsf_beans;

import group.msg.entities.Movie;
import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Named
@SessionScoped
public class addMovie implements Serializable {
    private String name;
    private double rating;
    private String genre;
    private int year;
    private List<Movie> list = new ArrayList<>();
    private String output_message;

    public void addingMovieAjax() {
        Optional<Movie> answer = list.stream().filter(movie -> movie.getName().equals(this.name)).findAny();
        if (answer.isPresent()) {
            this.output_message = "Sorry, you watched this movie already!";
        } else {
            list.add(new Movie(name, rating, genre, year));
            this.output_message = "Congratulations, you've added " + name + " in your watchlist!";
        }
    }
    
    public String goBack() {
        output_message = "";
        return "homepage_razvan";
    }
}
