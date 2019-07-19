package JSFExercisesClasses.jsf_beans;

import JSFExercisesClasses.Entities.Movie;
import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import JSFExercisesClasses.Entities.Movie;
@SessionScoped
@Named
@Data
public class SessionBean implements Serializable {
    private String name;
    private int year;
    private int length;
    private String author;

    List<Movie> myList = new ArrayList<Movie>();
    Movie movie = new Movie(name,year,length,author);
    private String outputMessage = "Empty";

    public String calculateOutput(){
        myList.add(movie);
        return "Name"+name+" Year:"+year+" Length:"+length+" Author:" + author;
    }
    public void saveInput(){

       outputMessage = calculateOutput();
    }
}
