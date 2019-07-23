package entrepriseJavaBean;

import lombok.Data;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@Data
@ApplicationScoped
public class ExportPDFBackingBean implements Serializable {
    @Inject
    private Employee employee;
    private StreamedContent exportedEmployeeStreamedContent;
    @Inject
    private PdfExportBean exportedBean;
    public void exportToPdf(){

        String filename = "exported_employee.pdf";
        exportedEmployeeStreamedContent = new DefaultStreamedContent(exportedBean.exportEmployeeAsPDF(employee), FacesContext.getCurrentInstance().
                getExternalContext().getMimeType(filename),filename);
    }
}
