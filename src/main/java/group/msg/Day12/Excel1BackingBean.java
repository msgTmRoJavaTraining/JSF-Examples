package group.msg.Day12;

import group.msg.jsf_MyBean.Movie;
import lombok.Data;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SessionScoped
@Named
@Data

public class Excel1BackingBean implements Serializable {

        private String name ;
        private String CNP;
        private String role;

        public void saveInput()
        {
                addingMovie();
        }

        public String navigateTo(String page){
            return page;
        }

        private static List<Employee> list = new ArrayList<>();

        public void addingMovie()
        {
            list.add(new Employee(name,CNP,role));
        }

        public static List<Employee> getList() {
            return list;
        }



}
