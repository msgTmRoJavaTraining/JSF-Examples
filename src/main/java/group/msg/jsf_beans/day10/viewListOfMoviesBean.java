package group.msg.jsf_beans.day10;

import group.msg.entities.Movie;
import lombok.Data;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Named
@ApplicationScoped
public class viewListOfMoviesBean implements Serializable {
    private static boolean dummyValuesGenerated = false;
    private Movie selectedMovie;
    private String outputMessage;
    private List<Movie> listOfMovies;

    @PostConstruct
    public void init() {
        listOfMovies = addNewMovieBean.getMovieList();

        if(!dummyValuesGenerated) {
                try {
                    addNewMovieBean.getMovieList().add(new Movie("Titanic", "Romance", 5, new SimpleDateFormat("dd/MM/yyyy").parse("13/09/1997"), new ArrayList<String>(Arrays.asList("Teenagers", "Adults"))));
                    addNewMovieBean.getMovieList().add(new Movie("Avatar", "Fantasy", 5, new SimpleDateFormat("dd/MM/yyyy").parse("09/11/2009"), new ArrayList<String>(Arrays.asList("Teenagers", "Adults"))));
                    addNewMovieBean.getMovieList().add(new Movie("Shazam", "Comedy", 4, new SimpleDateFormat("dd/MM/yyyy").parse("24/12/2019"), new ArrayList<String>(Arrays.asList("Teenagers", "Adults"))));
                    addNewMovieBean.getMovieList().add(new Movie("Spider Man - Home Coming", "Action", 3, new SimpleDateFormat("dd/MM/yyyy").parse("07/07/2019"), new ArrayList<String>(Arrays.asList("Kids", "Teenagers", "Adults"))));
                    addNewMovieBean.getMovieList().add(new Movie("Thor Ragnarok", "Comedy", 4, new SimpleDateFormat("dd/MM/yyyy").parse("27/08/2018"), new ArrayList<String>(Arrays.asList("Teenagers", "Adults"))));
                    addNewMovieBean.getMovieList().add(new Movie("Captain Marvel", "Action", 3, new SimpleDateFormat("dd/MM/yyyy").parse("08/03/2019"), new ArrayList<String>(Arrays.asList("Teenagers", "Adults"))));
                    addNewMovieBean.getMovieList().add(new Movie("Avengers End Game", "Action", 4, new SimpleDateFormat("dd/MM/yyyy").parse("20/04/2019"), new ArrayList<String>(Arrays.asList("Teenagers", "Adults"))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                dummyValuesGenerated = true;
        }
    }

    public List<Movie> getListOfMovies() {
        return listOfMovies;
    }

    public Movie getSelectedMovie() {
        return selectedMovie;
    }

    public void setSelectedMovie(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    public void onRowSelect(SelectEvent event) {
        Movie currentMovie = (Movie) event.getObject();
        outputMessage = currentMovie.getTitle();
    }
}
