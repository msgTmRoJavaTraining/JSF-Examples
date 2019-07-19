package group.msg.jsf_beans.day10;

import group.msg.WebHelper;
import lombok.Data;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Data
@Named
@ApplicationScoped
public class MyLoginBean implements Serializable {
    // ATENTIE! cand folosesti Bean-ul in xhtml/JSF, numele lui va fi convertit la camelCase
    // MyLoginBean -> myLoginBean. Putem sa facem altfel, cu @Named("andrei") si atunci va fi andrei.email
    private String email;
    private String password;

    public String validateUsernamePassword() {
            if (email.equals("andreihao@gmail.com") && password.equals("admin")) {
            WebHelper.getSession().setAttribute("loggedIn",true);
            return "homepage";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Date incorecte", "Email sau parola incorecte."));
            return "";
        }
    }

    public String getCurrentlyLoggedInUsername(){
        return email;
    }
}
