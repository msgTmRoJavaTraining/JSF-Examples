package group.msg.jsf_MyBean;

import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
    private Date date3;
    private String genre;
    private String[] suitableFor;


    public void saveInput()
    {
        if(rating.contains(".")) {
            outputMessage += id+" " + savedData + " " + rating+ " "+genre+" "+" "+date3+" ";
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

    public Date getDate3() {
        return date3;
    }

    public void setDate3(Date date3) {
        this.date3 = date3;
    }

    public void addingMovie() {
        list.add(new Movie(id, savedData, rating,date3,genre,suitableFor));
    }

    public static List<Movie> getList() {
        return list;
    }

    public String getGenre() {
        return genre;
    }

}