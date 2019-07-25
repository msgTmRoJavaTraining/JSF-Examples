package group.msg.Day12;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PRAcroForm;
import lombok.Data;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Data
@Named
public class EmployeeBackingBean
{
    private String name;

    @Inject
    private EmployeeEjb myEjb;
    @Inject
    private Employee employee;
    @Inject
    private ExcelEJB excelEJB;

    private List<Employee> employeeList=Excel1BackingBean.getList();

    private StreamedContent exportEmployeeStreamContent;
    private StreamedContent exportEmployeeStreamContent1;

    public Employee getEmployee() {
        return employee;
    }

    public void exportToPdf() throws IOException, DocumentException {
        String fileName="exported_employee.pdf";

        exportEmployeeStreamContent =new DefaultStreamedContent(myEjb.exportEmployeeAsPDF(employee),
        FacesContext.getCurrentInstance().getExternalContext().getMimeType(fileName),fileName);
    }

    public void exportToExcel() throws IOException
    {
        String fileName="exported_employee.xlsx";

        exportEmployeeStreamContent1 =new DefaultStreamedContent(excelEJB.exportEmployeeAsExcel(employeeList),
        FacesContext.getCurrentInstance().getExternalContext().getMimeType(fileName),fileName);
    }
}