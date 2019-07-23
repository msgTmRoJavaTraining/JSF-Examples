package group.msg.jsf_beans.Day12;

import com.itextpdf.text.DocumentException;
import group.msg.jsf_beans.Day10.Movie;
import lombok.Data;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Named
@SessionScoped
public class PdfBackingBean implements Serializable {

    @Inject
    private Employee employee;
    private List<Employee> employees;
    private StreamedContent exportEmployeeStreamedContent;
    private StreamedContent exportEmployeeStreamedContent1;


    public void addEmployeeToDatabase(){
        Employee e = new Employee(employee);
        employees.add(e);
    }

    @PostConstruct
    public void init(){
        employees= new ArrayList<>();
        employees.add(new Employee("Ana","Manager",2000));
        employees.add(new Employee("Radu","Assistant",1500));
        employees.add(new Employee("Mihai","fdh",1000));
    }

    @Inject
    private MyEJB myEjb;

    public void exportToPdf() throws DocumentException {
        String fileName="exported_employee.pdf";
        exportEmployeeStreamedContent=new DefaultStreamedContent(myEjb.exportEmployeeAsPDF(employee),
                FacesContext.getCurrentInstance().getExternalContext().getMimeType(fileName),fileName);

    }

    public void exportToExcel() throws IOException {
        String fileName="exported_employee.xlsx";
        exportEmployeeStreamedContent1=new DefaultStreamedContent(myEjb.exportEmployeeAsExcel(employees),
                FacesContext.getCurrentInstance().getExternalContext().getMimeType(fileName),fileName);


    }


}
