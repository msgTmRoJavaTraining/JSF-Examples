package group.msg.jsf_beans.Day12;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.ejb.Stateless;

import java.io.*;
import java.util.List;

@Stateless
public class MyEJB implements Serializable {



    public InputStream exportEmployeeAsPDF(Employee employee) throws DocumentException {

        String date=employee.getName() + " "+ employee.getRole() + " " + employee.getSalary() + "\n";

        Document document=new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            Font catFont = new Font(Font.FontFamily.TIMES_ROMAN,18);

            PdfWriter.getInstance(document, outputStream);

            document.open();

            Chunk chunk = new Chunk(date,catFont);
            document.add(chunk);

            document.close();

        }
        catch(DocumentException e){
            e.printStackTrace();
        }
        return new ByteArrayInputStream(outputStream.toByteArray());
    }


    public ByteArrayInputStream exportEmployeeAsExcel(List<Employee> employees) throws IOException {
        String[] columns = {"Name", "Role", "Salary"};

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employee");

        CreationHelper createHelper = workbook.getCreationHelper();

        org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

        int rowNum = 1;
        for(Employee employee: employees) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(employee.getName());

            row.createCell(1)
                    .setCellValue(employee.getRole());

            row.createCell(2)
                    .setCellValue(employee.getSalary());
        }

        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
        workbook.write(fileOut);
        fileOut.close();

        workbook.close();
        return new ByteArrayInputStream(fileOut.toByteArray());
    }
}
