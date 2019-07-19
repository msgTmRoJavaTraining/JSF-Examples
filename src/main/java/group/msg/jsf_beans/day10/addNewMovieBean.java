package group.msg.jsf_beans.day10;

import group.msg.entities.Movie;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
@Data
public class addNewMovieBean implements Serializable {
    private String inputMovieTitle;
    private String inputMovieGenre;
    private int inputMovieRating;
    private int inputMovieProductionYear;
    private List<Movie> movieList = new ArrayList<>();

    private StringBuilder builder = new StringBuilder();
    private String outputMessage;

    public void addMovieToDataBase() {
        if (isStringValid(inputMovieTitle)) {
            if(isStringValid(inputMovieTitle)) {
                movieList.add(new Movie(inputMovieTitle, inputMovieGenre, inputMovieRating, inputMovieProductionYear));
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

    public List<Movie> getMovieList() {
        return movieList;
    }
}
