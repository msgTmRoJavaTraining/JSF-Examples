package group.msg.ejb_exercise;

import lombok.Data;
import org.primefaces.model.DefaultStreamedContent;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ViewScoped
@Named
@Data
public class EmployeeExportBackingBean implements Serializable {
    private Employee emp=new Employee();
    private List<Employee> emps = new ArrayList<>();
    private DefaultStreamedContent exp;

    @Inject
    private PDFConverter ejb;
    public void addEmployeeToList(){
        Employee input = new Employee(emp);
        emps.add(input);
    }

    public void exportToPDF(){
        String fileName = "employees.pdf";
        exp = new DefaultStreamedContent(
                ejb.exportEmployeeAsPDF(emps),
                FacesContext.getCurrentInstance().getExternalContext().getMimeType(fileName),
                fileName
        );
    }

}
