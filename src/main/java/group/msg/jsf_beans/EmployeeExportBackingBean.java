package group.msg.jsf_beans;

import group.msg.EJBs.OperationsEJB;
import group.msg.entities.Employee;
import lombok.Data;
import org.primefaces.model.DefaultStreamedContent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
@Data
@SessionScoped
public class EmployeeExportBackingBean implements Serializable {
    private List<Employee> list = new ArrayList<>();
    private String name;
    private String cnp;
    private String role;
    private String outputMessage;

    @Inject
    private OperationsEJB operationsEJB;

    private DefaultStreamedContent exportedEmployeeStreamedContent;

    @PostConstruct
    void init() {
        list.add(new Employee("Razvan Mihaescu", "1970626351577", "Trainee"));
    }

    public void exportToExcel() throws IOException {
        String fileName = "exported_employee.xls";
        exportedEmployeeStreamedContent = new DefaultStreamedContent(
                operationsEJB.exportEmployeesAsExcel(list),
                FacesContext.getCurrentInstance().getExternalContext().getMimeType(fileName),
                fileName
        );
    }

    public void AddEmployee() {
        boolean valid=true;
        Optional<Employee> answer = list.stream().filter(employee -> employee.getCnp().equals(this.cnp)).findAny();
        if (answer.isPresent()) {
            this.outputMessage = "Sorry, duplicate CNP!";
        } else {
            if (cnp.length() != 13) {
                this.outputMessage = "Sorry, CNP must have 13 numbers";
            } else {
                for (char c : cnp.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        this.outputMessage = "Sorry, CNP must have only numbers";
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    list.add(new Employee(name, cnp, role));
                    outputMessage = "";
                    name = "";
                    role = "";
                    cnp = "";
                }
            }
        }
    }

        public void exportToPDF () {
            String fileName = "exported_employee.pdf";
            exportedEmployeeStreamedContent = new DefaultStreamedContent(
                    operationsEJB.exportEmployeesAsPDF(list),
                    FacesContext.getCurrentInstance().getExternalContext().getMimeType(fileName),
                    fileName
            );
        }
    }
