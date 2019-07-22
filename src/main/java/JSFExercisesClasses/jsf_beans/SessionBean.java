package JSFExercisesClasses.jsf_beans;

import JSFExercisesClasses.Entities.Movie;
import JSFExercisesClasses.MovieHolder;
import lombok.Data;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Named
@Data
public class SessionBean implements Serializable {
    private String name;
    private int year;
    private int length;
    private String author;
    private String rating;



    private String outputMessage = "Empty";
    private String messageError = "Fatal error:Rating is not a number";
    public String calculateOutput(){
        Movie movie = new Movie(name,year,length,author,rating);
        DataTableBean.addMovie(movie);
        if(rating.contains(".")) {
            return "Name:" + name + " Year:" + year + " Length:" + length + " Author:" + author + " Rating:" + rating;
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Invalid."));
            return "";
        }
    }

    public void saveInput(){

        outputMessage = calculateOutput();
    }




}
