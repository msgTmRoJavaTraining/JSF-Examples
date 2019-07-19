package group.msg.jsf_MyBean;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Data
@Named
public class MyHomepageBackingBean
{

    @Inject
    private MyLoginBackingBean myLoginBackingBean;

    private String username;

    @PostConstruct
    public void init(){
        username = myLoginBackingBean.getCurrentlyLoggedInUsername();
    }

    public String navigateTo(String page){
        return page;
    }

}
