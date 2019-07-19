package group.msg.jsf_beans;

import group.msg.entities.Movie;
import lombok.Data;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
@Data
public class MoviesBackingBean implements Serializable {
    String movieName;
    String movieCate;
    String result="";
    List<Movie> movies=new ArrayList<>();

    public void addToList(){
        movies.add(new Movie(movieName,movieCate));
    }
    public String printList(){
        movies.forEach(movies->result+=movies.getName()+" "+movies.getCateg()+"\n");
        return result;
    }
}
