package group.msg.jsf_exerciseBeans;

import group.msg.entities.Movie;
import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Named
@SessionScoped
public class MoviesBean implements Serializable {
    private Movie givenMovie= new Movie();
    private List<Movie>moviesList= new ArrayList<>();


    public String addMovie(){
        Movie param = new Movie(givenMovie);
        moviesList.add(param);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Movie list:", listOfMovies(moviesList)));
        return null;
    }
    public String listOfMovies(List<Movie>list){
        StringBuilder br = new StringBuilder();
        for(Movie m: list){
           br.append( m.toString()) ;
            br.append("\n");
        }
        return br.toString();
    }

    public Movie getGivenMovie() {
        return givenMovie;
    }

    public void setGivenMovie(Movie givenMovie) {
        this.givenMovie = givenMovie;
    }

    public List<Movie> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }
    public String navigateTo(String page){
        return page;
    }
}
