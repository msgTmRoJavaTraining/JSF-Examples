package group.msg.jsf_exerciseBeans;

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
public class LoginDataBean implements Serializable {
    private String userName;
    private String password;
    public String validateLoginData() {
        if (userName.equals("user") && password.equals("pass")) {
            WebHelper.getSession().setAttribute("loggedIn",true);
            return "moviePage";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Invalid credentials."));
            return "";
        }
    }
    public String getCurrentlyLoggedInUsername(){
        return userName;
    }
}
