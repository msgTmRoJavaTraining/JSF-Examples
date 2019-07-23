package group.msg.EJBs;


import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import group.msg.entities.Employee;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.ejb.Stateless;
import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

@Stateless
public class OperationsEJB implements Serializable {

    public InputStream exportEmployeesAsPDF(List<Employee> list) {
        ByteArrayInputStream inputStream = null;
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();
            for (Employee emp : list
            ) {
                String line = emp.getName() + " " + emp.getCnp() + " " + emp.getRole();
                document.add(new Paragraph(line));
            }
            document.close();
            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public InputStream exportEmployeesAsExcel(List<Employee> list) throws IOException {
        Field[] fields = Employee.class.getDeclaredFields();
        int numberOfColumns = fields.length;
        ByteArrayInputStream inputStream;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet("Employee");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < numberOfColumns; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(fields[i].getName().toUpperCase());
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for (Employee employee : list) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(employee.getCnp());

            row.createCell(1)
                    .setCellValue(employee.getName());

            Cell dateOfBirthCell = row.createCell(2);
            dateOfBirthCell.setCellValue(employee.getRole());
        }

        for (int i = 0; i < numberOfColumns; i++) {
            sheet.autoSizeColumn(i);
        }
        workbook.write(outputStream);
        workbook.close();
        inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        return inputStream;
    }
}
