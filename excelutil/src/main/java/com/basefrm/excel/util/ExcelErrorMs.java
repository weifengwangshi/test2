package com.basefrm.excel.util;

import com.basefrm.excel.parsing.ExcelError;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
@Component
public class ExcelErrorMs {
    private static String filePath;
    @Value("${file.excel.basePath}")
    public void setFilePath(String filePath) {
        ExcelErrorMs.filePath = filePath;
    }
    public static boolean  writeExcel(List<ExcelError> result){
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            //新建工作表
            // XSSFSheet sheet = workbook.createSheet("ErrorMessages");
            HSSFSheet sheet = workbook.createSheet("ErrorMessages");
            sheet.setColumnWidth( 2, 40000);
            //创建合并单元格对象
            //创建样式
            HSSFCellStyle stylet = workbook.createCellStyle();
            HSSFFont fontt = workbook.createFont();
            fontt.setFontHeightInPoints((short) 16);
            //font.setFontHeight((short)320); 效果和上面一样。用这个方法设置大小，值要设置为字体大小*20倍，具体看API文档
            stylet.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            stylet.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
            fontt.setBold(true);
            stylet.setFont(fontt);
            //创建字体
            HSSFCellStyle style = workbook.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 12);
            //font.setFontHeight((short)320); 效果和上面一样。用这个方法设置大小，值要设置为字体大小*20倍，具体看API文档
            font.setColor(HSSFColor.RED.index);
            font.setBold(true);
            style.setFont(font);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
            HSSFRow rowt = sheet.createRow(0);
            rowt.setHeight((short) 0x349);
            HSSFCell cellt = rowt.createCell(0);
            cellt.setCellValue("导入读取数据时出错信息");
            cellt.setCellStyle(stylet);
            HSSFRow rowtit = sheet.createRow(1);
            HSSFCell cellxh = rowtit.createCell(0);
            HSSFCell cellhh = rowtit.createCell(1);
            HSSFCell cellxx = rowtit.createCell(2);
            cellxh.setCellValue("序号");
            cellhh.setCellValue("行号");
            cellxx.setCellValue("信息");
            cellxh.setCellStyle(stylet);
            cellhh.setCellStyle(stylet);
            cellxx.setCellStyle(stylet);
            for(int w  =0; w < result.size();w++) {
                //创建行,0表示第一行
                int js = w + 2;
                HSSFRow row = sheet.createRow(js);
                //创建单元格行号由row确定,列号作为参数传递给createCell;第一列从0开始计算
                HSSFCell cell = row.createCell(0);
                HSSFCell cellr = row.createCell(1);
                HSSFCell cells = row.createCell(2);
                //给单元格赋值
                cell.setCellValue(w+1);
                cellr.setCellValue(result.get(w).getRow());
                cells.setCellValue(result.get(w).getErrorMsg());
                cell.setCellStyle(style);
                cellr.setCellStyle(style);
                cells.setCellStyle(style);
            }
            //创建输出流
            //创建临时文件
//                String folderPath2 = System.getProperty("catalina.base") + "/temp/";
//                String importInfo=folderPath2 + "ExcelErrorInfo"+"-"+yh.getYhbh()+".xlsx";
//                FileOutputStream fos = new FileOutputStream(new File(importInfo));
//            System.out.println("测试路径值：=============================++++++++++++： "+filePath);
            FileOutputStream fos = new FileOutputStream(new File(filePath+"ExcelErrorInfos.xlsx"));
            workbook.write(fos);
            workbook.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
