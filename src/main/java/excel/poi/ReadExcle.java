package excel.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;

public class ReadExcle {

    /** Excel文件的存放位置。注意是反斜线*/
    public static String fileToBeRead = "D:\\test1.xls";
    public static void main(String argv[]) {
        try {
            // 创建对Excel工作簿文件的引用
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead));
            // 创建对工作表的引用。
            // 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
            HSSFSheet sheet = workbook.getSheet("Sheet1");
            // 也可用getSheetAt(int index)按索引引用，
            // 在Excel文档中，第一张工作表的缺省索引是0，
            // 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
            // 读取左上端单元
            HSSFRow row = sheet.getRow(0);
            HSSFCell cell = row.getCell((short)0);
            // 输出单元内容，cell.getStringCellValue()就是取所在单元的值
            System.out.println("左上端单元是： " + cell.getStringCellValue());
        } catch (Exception e) {
            System.out.println("已运行xlRead() : " + e);
        }
    }


    /**
     * 在这里，我们将只介绍一些和格式设置有关的语句，我们假定workbook就是对一个工作簿的引用。在Java中，第一步要做的就是创建和设置字体和单元格的格式，然后再应用这些格式：
     *
     * 1、创建字体，设置其为红色、粗体：
     * HSSFFont font = workbook.createFont();
     * font.setColor(HSSFFont.COLOR_RED);
     * font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
     * 2、创建格式
     * cellStyle.setFont(font);
     * 3、应用格式
     * HSSFCell cell = row.createCell((short) 0);
     * cell.setCellStyle(cellStyle);
     * cell.setCellType(HSSFCell.CELL_TYPE_STRING);
     * cell.setCellValue("标题 ");
     *
     */

}
