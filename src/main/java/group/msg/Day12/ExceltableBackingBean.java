package group.msg.Day12;

import group.msg.jsf_MyBean.Movie;
import group.msg.jsf_MyBean.MySessionScopeBackingBean;
import lombok.Data;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Named
@SessionScoped

public class ExceltableBackingBean extends LazyDataModel<Movie> implements Serializable
{

        private Employee selectedEmployee;
        private List<Employee> employeeList;
        private String outputMessage;

        @PostConstruct
        public void init() {
            employeeList = Excel1BackingBean.getList();
        }


        public void rowSelected(SelectEvent event) {
            outputMessage = "Selected movie: "+selectedEmployee.getName();
        }


    }
