package entrepriseJavaBean;



import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Data
@Named
@SessionScoped
public class XlDataTableBackingBean implements Serializable {
    private String name;
    private String cnp;
    private int age;
    @Inject
    Employee emp;
    private static List<Employee> list;

    @PostConstruct
    public void init() {
        list = new ArrayList<Employee>();

    }

    public static List<Employee> getList() {
        return list;
    }

    public static void setList(List<Employee> employee) {
        list = employee;
    }

    public void addPerson(Employee employee) {

        list.add(employee);
    }
}