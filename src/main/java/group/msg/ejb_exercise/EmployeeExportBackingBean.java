package group.msg.ejb_exercise;

import lombok.Data;
import org.primefaces.model.DefaultStreamedContent;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
@Data
public class EmployeeExportBackingBean implements Serializable {
    private Employee emp=new Employee();
    private List<Employee> emps = new ArrayList<>();
    private DefaultStreamedContent exp;

    @Inject
    private PDFConverter ejb;
    @Inject
    private ExcelConverter ejb_excel;
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
    public void exportToExcel(){
        String fileName = "ExcelOutput.xlsx";
        exp = new DefaultStreamedContent(
                ejb_excel.exportEmployeeAsExcel(emps),
                FacesContext.getCurrentInstance().getExternalContext().getMimeType(fileName),
                fileName
        );
    }
    public String navigateTo(String page){
        return page;
    }

}
