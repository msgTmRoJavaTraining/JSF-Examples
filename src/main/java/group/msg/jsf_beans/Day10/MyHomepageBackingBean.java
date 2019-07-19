package group.msg.jsf_beans.Day10;

import group.msg.jsf_beans.LoginBackingBean;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@RequestScoped
@Named
@Data
public class MyHomepageBackingBean implements Serializable {
    @Inject
    private MyLoginBackingBean loginBackingBean;

    private String loggedInUserName;

    @PostConstruct
    public void init(){
        loggedInUserName = loginBackingBean.getCurrentlyLoggedInUsername();
    }

    public String ajaxExampleButtonClicked() {
        return "ajax_example";
    }

    public String dataTableExampleButtonClicked() {
        return "data_table_example";
    }

    public String navigateTo(String page){
        return page;
    }
}
