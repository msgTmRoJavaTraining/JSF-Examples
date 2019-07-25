package group.msg.Day12;

import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.*;
import java.util.List;

@SessionScoped
@Named
@Data
public class ExcelEJB implements Serializable
{

    public InputStream exportEmployeeAsExcel(List<Employee> employeeList) throws IOException {

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Employees");
        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2,5000);

        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(headerStyle.SOLID_FOREGROUND);

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Name");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(1);
        headerCell.setCellValue("CNP");
        headerCell.setCellStyle(headerStyle);

        headerCell = header.createCell(2);
        headerCell.setCellValue("Role");
        headerCell.setCellStyle(headerStyle);

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        int numberOfRows = 1;
        for(Employee singleEmployee : employeeList) {
            Row row = sheet.createRow(numberOfRows++);

            row.createCell(0).setCellValue(singleEmployee.getName());
            row.createCell(2).setCellValue(singleEmployee.getRole());
            row.createCell(1).setCellValue(singleEmployee.getCNP());
        }


        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
