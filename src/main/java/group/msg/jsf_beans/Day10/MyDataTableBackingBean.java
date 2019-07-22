package group.msg.jsf_beans.Day10;

import group.msg.AnimalsHolder;
import group.msg.entities.Animal;
import lombok.Data;
import org.jboss.weld.util.LazyValueHolder;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Data
@Named
@ViewScoped
public class MyDataTableBackingBean implements Serializable {

    private Movie selectedMovie;
    private List<Movie> movieList;
    private String outputMessage;


    public void rowSelected(SelectEvent event) {
        outputMessage = selectedMovie.getName();
    }


}
