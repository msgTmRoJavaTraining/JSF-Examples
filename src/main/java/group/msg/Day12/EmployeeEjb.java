package group.msg.Day12;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import com.sun.xml.ws.policy.privateutil.PolicyUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.ejb.Stateless;
import java.awt.*;
import java.io.*;
import java.util.List;

@Stateless
public class EmployeeEjb implements Serializable
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
