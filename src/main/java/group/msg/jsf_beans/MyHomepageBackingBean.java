package group.msg.jsf_beans;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Data
@Named
@SessionScoped
public class MyHomepageBackingBean implements Serializable {
    private String loggedInUserName;

    @Inject
    private MyLoginBackingBean myLoginBackingBean;

    @PostConstruct
    public void init() {
        loggedInUserName = myLoginBackingBean.getCurrentlyLoggedInUsername();
    }

    public String navigateTo(String page) {
        return page;
    }
}
