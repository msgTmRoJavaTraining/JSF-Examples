package group.msg.jsf_MyBean;

import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SessionScoped
@Named
@Data
public class MySessionScopeBackingBean implements Serializable
{
    private String savedData ;
    private String rating;
    private int id;
    private String outputMessage = "";

    public void saveInput()
    {
        if(rating.contains(".")) {
            outputMessage += id+" " + savedData + " " + rating+ " ";
            addingMovie();
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error","Rating must be decimal a number"));
        }
    }

    public String navigateTo(String page){
        return page;
    }

    private static List<Movie> list = new ArrayList<>();

    public void addingMovie() {
        list.add(new Movie(id, savedData, rating));
    }

    public static List<Movie> getList() {
        return list;
    }
}