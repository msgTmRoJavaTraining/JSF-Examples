package group.msg.entities;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class SelectOneMenuView implements Serializable {

    private String console;

    private String option;
    private List<String> options;

    private String gender;
    private Map<String,String> genders = new HashMap<>();



    @PostConstruct
    public void init() {
        genders = new HashMap<>();
        genders.put("Comedy", "comedy");
        genders.put("Horror","horror");
        genders.put("Thriller","thriller");
        genders.put("Action","action");
        genders.put("SF","sf");
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public Map<String, String> getGenders() {
        return genders;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}