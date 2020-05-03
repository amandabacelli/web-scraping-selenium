package converts;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelConverts {

    public void saveDataToExcel(HashMap<String, String> excel) throws Exception {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        int indexRow = 0;
        Cell cell_lesson_text;
        Cell cell_lesson_link;

        for (Map.Entry<String, String> eachMapItem : excel.entrySet()) {
            Row row = sheet.createRow(indexRow++);
            cell_lesson_text = row.createCell(0);
            cell_lesson_link = row.createCell(1);

            cell_lesson_text.setCellValue(eachMapItem.getKey());
            cell_lesson_link.setCellValue(eachMapItem.getValue());
        }

        FileOutputStream fileOutputStream = new FileOutputStream(new File("excel//links.xlsx"));
        workbook.write(fileOutputStream);
    }

}
