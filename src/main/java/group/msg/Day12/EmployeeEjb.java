package group.msg.Day12;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import javax.ejb.Stateless;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Stateless
public class EmployeeEjb
{
    public InputStream exportEmployeeAsPDF(Employee employee) throws IOException, DocumentException {

        String x="Nume: "+employee.getName()+ " CNP: "+employee.getCNP()+" Role: "+employee.getRole();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Font font=FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            PdfWriter.getInstance(document, out);
            document.open();

            Chunk chunk=new Chunk(x,font);
            document.add(chunk);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }


    


}
