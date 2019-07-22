package JSFExercisesClasses.jsf_beans;

import JSFExercisesClasses.jsf_beans.LoginBean;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
@Data
public class HomePageBean {

    @Inject
    private LoginBean loginBackingBean;

    private String loggedInUserName;

    @PostConstruct
    public void init(){
        loggedInUserName = loginBackingBean.getCurrentlyLoggedInUsername();
    }



    public String navigateTo(String page){
        return page;
    }
}
