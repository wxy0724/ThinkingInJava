package excel.poi;

import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;

/**
 * Apache POI是Apache软件基金会的开放源码函式库，POI提供API给Java程序对Microsoft Office格式档案读和写的功能。
 * 结构：
 * HSSF － 提供读写Microsoft Excel格式档案的功能。
 * XSSF － 提供读写Microsoft Excel OOXML格式档案的功能。
 * HWPF － 提供读写Microsoft Word格式档案的功能。
 * HSLF － 提供读写Microsoft PowerPoint格式档案的功能。
 * HDGF － 提供读写Microsoft Visio格式档案的功能。
 */
public class CreateExcel {
    /** Excel文件要存放的位置，假定在D盘下 */
    public static String outputFile = "E://test.xls";

    public static void main(String[] args) {
        try {
            // 创建新的Excle
            HSSFWorkbook workbook = new HSSFWorkbook();
            // 在Excle工作簿中建一个工作表，其名为缺省值
            // 如果新建一名为“效益指标”的工作表，其语句为：
            // HSSFSheet sheet = workbook.createSheet("效益指标");
            HSSFSheet sheet = workbook.createSheet();
            // 在索引0的位置创建行（最顶端的行）
            HSSFRow row = sheet.createRow((short) 0);
            // 在索引0的位置创建单元格（左上端）
            HSSFCell cell = row.createCell((short) 0);
            // 定义单元格为字符串类型
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            // 在单元格输入一些内容
            cell.setCellValue("增加值");
            // 新建一个输出文件流
            FileOutputStream fOut = new FileOutputStream(outputFile);
            // 把相应的Excel 工作簿存盘
            workbook.write(fOut);
            fOut.flush();
            // 操作结束,关闭文件
            fOut.close();
            System.out.println("文件生成...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
