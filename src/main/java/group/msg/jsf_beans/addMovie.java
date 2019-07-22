package group.msg.jsf_beans;

import group.msg.entities.Movie;
import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Data
@Named
@SessionScoped
public class addMovie implements Serializable {
    private String name;
    private double rating;
    private String genre;
    private int year;
    private Date date;
    private String information;
    private List<Movie> list = new ArrayList<>();
    private String output_message;

    public void addingMovieAjax() {
        Optional<Movie> answer = list.stream().filter(movie -> movie.getName().equals(this.name)).findAny();
        if (answer.isPresent()) {
            this.output_message = "Sorry, you watched this movie already!";
        } else {
            if ((rating > 0) && (rating <= 5.0)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                year = calendar.get(Calendar.YEAR);
                list.add(new Movie(name, rating, genre, year, information));
                list.add(new Movie("Get Out", 5, "Thriller", 2018, "interesting"));
                list.add(new Movie("Logan", 5, "Action/Adventure", 2018, "if you're a X-Men fan, this movie is a must"));
                list.add(new Movie("Avengers: Endgame", 4.5, "SF/Adventure", 2018, "MCU"));
                list.add(new Movie("Mother", 4, "Thriller", 2018, "psychologic"));
                this.output_message = "Congratulations, you've added " + name + " in your watchlist!";
            } else {
                FacesContext context = FacesContext.getCurrentInstance();

                context.addMessage(null, new FacesMessage("Failed", "Check the rating!"));
                this.output_message = "Check the rating!";
            }
        }
    }

    public String goBack() {
        output_message = "";
        return "homepage_razvan";
    }
}
