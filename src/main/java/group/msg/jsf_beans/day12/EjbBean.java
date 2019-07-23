package group.msg.jsf_beans.day12;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import group.msg.entities.Employee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.ejb.Stateless;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

@Stateless
public class EjbBean implements Serializable {
    private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL);


    public ByteArrayInputStream exportToPDF(List<Employee> employees) {
        try {
            Document document = new Document();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, outputStream);
            document.open();
            addMetaData(document);
            addContent(document, employees);
            document.close();

            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void addMetaData(Document document) {
        document.addTitle("My PDF File");
        document.addSubject("Object to PDF iText MSG");
        document.addKeywords("Java, PDF, iText, MSG");
        document.addAuthor("Astanei Andrei");
        document.addCreator("Astanei Andrei");
    }

    private void addContent(Document document, List<Employee> receivedEmployeeList) throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("List of Employees", titleFont));

        addEmptyLine(preface, 1);

        for (Employee singleEmployee : receivedEmployeeList) {
            preface.add(new Paragraph(
                    "Name: " + singleEmployee.getName() + ", Role: " + singleEmployee.getRole() + ", CNP: " + singleEmployee.getCnp(),
                    normalFont));
            addEmptyLine(preface, 1);
        }

        document.add(preface);
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public ByteArrayInputStream exportToEXCEL(List<Employee> employees) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employee List");

        org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontName("Georgia");

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        Field[] fields = Employee.class.getDeclaredFields();
        for(int i = 0; i < fields.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(fields[i].getName());
            cell.setCellStyle(headerCellStyle);
        }

        int numberOfRows = 1;
        for(Employee singleEmployee : employees) {
            Row row = sheet.createRow(numberOfRows++);

            row.createCell(0).setCellValue(singleEmployee.getName());
            row.createCell(1).setCellValue(singleEmployee.getRole());
            row.createCell(2).setCellValue(singleEmployee.getCnp());
        }

        // Resize all columns to fit the content size
        for(int i = 0; i < fields.length; i++) {
            sheet.autoSizeColumn(i);
        }

        //Generarea de fisier pentru descarcare
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();

            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
