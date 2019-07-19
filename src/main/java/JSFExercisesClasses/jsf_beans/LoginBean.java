package JSFExercisesClasses.jsf_beans;

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
public class LoginBean implements Serializable {
    private String user;
    private String pwd;

    public String validateUsernamePassword() {
        if (user.equals("IonutB") && pwd.equals("BIonut")) {
            WebHelper.getSession().setAttribute("loggedIn",true);
            return "homepage";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Invalid credentials."));
            return "";
        }
    }

    public String getCurrentlyLoggedInUsername(){
        return user;
    }
}
