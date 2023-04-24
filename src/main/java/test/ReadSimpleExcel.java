package test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ReadSimpleExcel {
    private static final String DATA_TYPES = "src/main/resources/data/ex1.xlsx";
    public static void main(String[] args) {
        try(FileInputStream fis = new FileInputStream(DATA_TYPES)) {
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet mySheet = workbook.getSheetAt(0);
            Iterator<Row> it = mySheet.iterator();
            while(it.hasNext()) {
                Row currRow = it.next();
                Iterator<Cell> cell = currRow.cellIterator();
                while(cell.hasNext()) {
                    Cell currCell = cell.next();
                    if(currCell.getCellType() == CellType.STRING) {
                        System.out.print(currCell.getStringCellValue() + ":");
                    } else if (currCell.getCellType() == CellType.NUMERIC) {
                        System.out.print(currCell.getNumericCellValue() + ":");
                    }
                }
                System.out.println();
            }
            workbook.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
