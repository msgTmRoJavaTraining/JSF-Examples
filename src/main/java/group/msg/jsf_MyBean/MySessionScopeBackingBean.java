package group.msg.jsf_MyBean;

import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@SessionScoped
@Named
@Data
public class MySessionScopeBackingBean implements Serializable
{
    private String savedData ;
    private int rating;
    private String outputMessage = "";


    public void saveInput()
    {
        outputMessage +=" "+savedData+" "+ rating;
    }
}
