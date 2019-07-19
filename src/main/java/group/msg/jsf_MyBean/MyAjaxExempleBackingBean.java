package group.msg.jsf_MyBean;

import lombok.Data;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@Data
public class MyAjaxExempleBackingBean implements Serializable
{

    private String movieName;
    private String message;

    public void addMovieToDatabase() {
        if (isValid(movieName)) {
            message = movieName+" added!";
        } else {
            message = "Invalid movie name.";
        }
    }
    public boolean isValid(String name)
    {
        return name.split(" ").length > 1;
    }

}
