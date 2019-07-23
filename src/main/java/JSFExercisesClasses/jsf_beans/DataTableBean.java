package JSFExercisesClasses.jsf_beans;

import JSFExercisesClasses.Entities.Movie;

import lombok.Data;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Named
@ViewScoped
public class DataTableBean extends LazyDataModel<Movie> {

    private Movie selectedMovie;
    private static List<Movie> movieList = new ArrayList<>();
    private String outputMessage;

    public static void addMovie(Movie movie){
        movieList.add(movie);
    }

    public static List<Movie> getMovieList() {
        return movieList;
    }

    public void rowSelected(SelectEvent event) {
        outputMessage = selectedMovie.getName();
    }

}
