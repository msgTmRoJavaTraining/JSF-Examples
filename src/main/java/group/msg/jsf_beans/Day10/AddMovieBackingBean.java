package group.msg.jsf_beans.Day10;


import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@Data
@SessionScoped

public class AddMovieBackingBean implements Serializable {

    private Movie givenMovie= new Movie();

    private List<Movie> movieList=new ArrayList<>();


    public void addMovieToDatabase(){
        Movie m = new Movie(givenMovie);
        movieList.add(m);
    }

    public String listOfMovies(List<Movie>list){
        StringBuilder br = new StringBuilder();
        for(Movie m: list){
            br.append( m.toString()) ;
            br.append("\n");
        }
    return br.toString();
    }


}
