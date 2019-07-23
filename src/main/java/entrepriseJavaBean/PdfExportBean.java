package entrepriseJavaBean;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;



import javax.ejb.Stateless;

@Stateless
public class PdfExportBean  {

    public InputStream exportEmployeeAsPDF(Employee emp){
        String empString  = "Nume:"+emp.getName()+" CNP:"+emp.getCnp()+" Age:"+emp.getAge();
        Document document = new Document();
        ByteArrayOutputStream out =  new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, out);

            document.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk(empString, font);

            document.add(chunk);
            document.close();
        }catch(DocumentException e){
             e.printStackTrace();
        }

        ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());


        return inputStream;

    }
}
