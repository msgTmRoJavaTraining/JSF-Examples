package group.msg.jsf_beans;

import group.msg.entities.Movie;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

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
    private String output_message;
    private String[] public_category;
    private String[] selectedCategory;

    @Inject
    private MyHomepageBackingBean myHomepageBackingBean;

    @PostConstruct
    public void init() {
        getEnumElements();
    }

    public void addingMovieAjax() {
        Optional<Movie> answer = myHomepageBackingBean.getList().stream().filter(movie -> movie.getName().equals(this.name)).findAny();
        if (answer.isPresent()) {
            this.output_message = "Sorry, you watched this movie already!";
        } else {
            if ((rating > 0) && (rating <= 5.0)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                year = calendar.get(Calendar.YEAR);
                myHomepageBackingBean.addMovie(new Movie(name, rating, genre, year, information, selectedCategory));
                Clear();
                this.output_message = "Congratulations, you've added " + name + " in your watchlist!";
            } else {
                FacesContext context = FacesContext.getCurrentInstance();

                context.addMessage(null, new FacesMessage("Failed", "Check the rating!"));
                this.output_message = "Check the rating!";
            }
        }
    }

    public String[] getEnumElements() {
        public_category = new String[Movie.public_category.values().length];

        int i = 0;
        for (Movie.public_category role : Movie.public_category.values()) {
            public_category[i++] = role.toString();
        }
        return public_category;
    }

    private void Clear() {
        this.name = "";
        this.genre = "";
        this.rating = 0;
        this.date = null;
        this.information = "";
        this.output_message = "";
        this.selectedCategory = null;
    }

    public String goBack() {
        output_message = "";
        return "homepage_razvan";
    }
}
