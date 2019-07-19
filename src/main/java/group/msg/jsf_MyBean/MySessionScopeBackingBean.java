package group.msg.jsf_MyBean;

import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@SessionScoped
@Named
@Data
public class MySessionScopeBackingBean implements Serializable
{
    private String savedData ;
    private String rating;
    private String outputMessage = "";


    public void saveInput()
    {
        if(rating.contains("."))
        outputMessage +=" "+savedData+" "+ rating;
        else
        {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Error","Rating must be decimal a number"));
        }
    }
}
