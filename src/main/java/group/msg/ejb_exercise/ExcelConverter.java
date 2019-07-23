package group.msg.ejb_exercise;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.ejb.Stateless;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

@Stateless
public class ExcelConverter implements Serializable {
    public InputStream exportEmployeeAsExcel(List<Employee> emps){
        Workbook workbook =  new XSSFWorkbook();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        XSSFCellStyle style= (XSSFCellStyle) workbook.createCellStyle();
        style.setBorderBottom(XSSFCellStyle.BORDER_MEDIUM);
        style.setBorderTop(XSSFCellStyle.BORDER_MEDIUM);
        style.setBorderRight(XSSFCellStyle.BORDER_MEDIUM);
        style.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);
        try {
            Sheet sheet = workbook.createSheet("employees");
            int rowIndex = 1;
            int rowidx= 0;
            int columnidx=0;
            Row row = sheet.createRow(rowidx++);
            row.createCell(columnidx++).setCellValue("CNP");
            row.createCell(columnidx++).setCellValue("Name");
            row.createCell(columnidx++).setCellValue("Role");
            for (Employee e : emps) {

                Row rowInsert = sheet.createRow(rowIndex++);
                int columnIndex = 0;
                rowInsert.createCell(columnIndex++).setCellValue(e.getCNP());
                rowInsert.createCell(columnIndex++).setCellValue(e.getName());
                rowInsert.createCell(columnIndex++).setCellValue(e.getRole());

            }
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(outputStream.toByteArray());
        return bis;
    }
}
