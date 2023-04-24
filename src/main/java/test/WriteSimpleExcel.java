package test;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteSimpleExcel {
    private static final String DATA_TYPES = "src/main/resources/data/ex1.xlsx";
    public static void main(String[] args) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Data Types");
        int rowCount = 0;
        Object[][] datatypes = {
                {"Datatype", "Type", "Size (in bytes)"},
                {"int", "primitive", 2},
                {"float", "primitive", 4},
                {"double", "primitive", 8},
                {"char", "primitive", 1},
                {"String", "reference", "N/A"}
        };
        for (Object[] obj : datatypes) {
            Row row = sheet.createRow(rowCount++);
            int colNum = 0;
            for (Object field : obj) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }
        try (FileOutputStream fout = new FileOutputStream(DATA_TYPES)) {
            workbook.write(fout);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
