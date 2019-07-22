package group.msg.jsf_beans.day10;

import group.msg.entities.Movie;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
@Data
public class addNewMovieBean implements Serializable {
    private String inputMovieTitle;
    private String inputMovieGenre;
    private int inputMovieRating;
    private Date productionDate;
    private List<String> movieGenres;
    private List<String> suitableForOptions, suitableForOptionsSelected;

    @PostConstruct
    public void init() {
        movieGenres = new ArrayList<>();

        movieGenres.add("Romance");
        movieGenres.add("Drama");
        movieGenres.add("Action");
        movieGenres.add("Fantasy");
        movieGenres.add("Comedy");

        suitableForOptions = new ArrayList<>();
        suitableForOptions.add("Kids");
        suitableForOptions.add("Teenagers");
        suitableForOptions.add("Adults");

        suitableForOptionsSelected = new ArrayList<>();
    }

    private static List<Movie> movieList = new ArrayList<>();

    private StringBuilder builder = new StringBuilder();
    private String outputMessage;

    public void addMovieToDataBase() {
        if (isStringValid(inputMovieTitle)) {
            if(isStringValid(inputMovieTitle)) {
                movieList.add(new Movie(inputMovieTitle, inputMovieGenre, inputMovieRating, productionDate, suitableForOptionsSelected));
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Movie Status", "New movie added successfully!"));
            }
        }
    }

    private void showMovieList() {
        builder = new StringBuilder();

        for(Movie m : movieList) {
            builder.append(m.toString());
        }

        outputMessage = builder.toString();
    }

    private boolean isStringValid(String name) {
        return name.length() > 1;
    }

    public static List<Movie> getMovieList() {
        return movieList;
    }

    public List<String> getMovieGenres() {
        return movieGenres;
    }
}
