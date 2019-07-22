package group.msg.jsf_beans.day10;

import group.msg.WebHelper;
import group.msg.jsf_beans.LoginBackingBean;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Session;
import java.io.Serializable;

@RequestScoped
@Named
@Data
public class homePageBean implements Serializable {
    @Inject
    private MyLoginBean myLoginBean;

    private String loggedInUserName;

    @PostConstruct
    public void init(){
        loggedInUserName = myLoginBean.getCurrentlyLoggedInUsername();
    }

    public String navigateTo(String page){
        return page;
    }

    public String logUserOut() {
        addNewMovieBean.getMovieList().clear();
        WebHelper.getSession().setAttribute("loggedIn",false);
        WebHelper.getSession().invalidate();
        return "my_login";
    }
}
