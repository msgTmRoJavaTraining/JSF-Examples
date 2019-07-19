package group.msg.jsf_MyBean;

import group.msg.WebHelper;
import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
@Data
@Named
@SessionScoped
public class MyLoginBackingBean implements Serializable
{

    private String user;
    private String password;

    public String validateCredentials()
    {
        if(user.equals("movie") && password.equals("movie"))
        {
            WebHelper.getSession().setAttribute("loggedIn",true);
            return "my_homepage";
        }
        else
        {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error","Incorrect username or password "));
            return "";
        }
    }

    public String getCurrentlyLoggedInUsername(){
        return user;
    }
}
