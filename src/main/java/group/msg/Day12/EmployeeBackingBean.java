package group.msg.Day12;

import com.itextpdf.text.DocumentException;
import lombok.Data;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

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

    private StreamedContent exportEmployeeStreamContent;

    public Employee getEmployee() {
        return employee;
    }

    public void exportToPdf() throws IOException, DocumentException {
        String fileName="exported_employee.pdf";

        exportEmployeeStreamContent =new DefaultStreamedContent(myEjb.exportEmployeeAsPDF(employee),
        FacesContext.getCurrentInstance().getExternalContext().getMimeType(fileName),fileName);
    }

}
