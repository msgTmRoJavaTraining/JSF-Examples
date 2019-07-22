package group.msg.jsf_beans;

import group.msg.entities.Movie;
import lombok.Data;
import org.primefaces.event.SelectEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Named
@SessionScoped
public class MyHomepageBackingBean implements Serializable {
    private List<Movie> list = new ArrayList<>();
    private String loggedInUserName;
    private String outputMessage;
    private Movie selectedMovie;

    @Inject
    private MyLoginBackingBean myLoginBackingBean;

    @PostConstruct
    public void init() {
        loggedInUserName = myLoginBackingBean.getCurrentlyLoggedInUsername();
    }

    public void rowSelected(SelectEvent event) {
        selectedMovie = (Movie) event.getObject();
        String ages = Arrays.toString(selectedMovie.getSuitableFor());
        outputMessage = "Information:" + selectedMovie.getInformation()
                + " Suitable for: " + ages;
    }

    public void addMovie(Movie m) {
        list.add(m);
    }

    public String navigateTo(String page) {
        return page;
    }
}
