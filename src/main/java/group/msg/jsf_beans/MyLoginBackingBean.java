package group.msg.jsf_beans;

import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Data
@Named
@SessionScoped
public class MyLoginBackingBean implements Serializable {
    private String user;
    private String pass;

    public String validateCredentials() {
        if (user.equals("razvan") && pass.equals("razvan")) {
            return "homepage_razvan";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error", "Invalid credentials."));
            user = "";
            pass = "";
            return "";
        }
    }

    public String getCurrentlyLoggedInUsername() {
        return user;
    }
}
