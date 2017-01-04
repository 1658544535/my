package com.tzmb2c.utils.export.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 
 * @Title:导出excel公共处理类
 * @Description:
 * @Author:fengjianshe
 * @Since:2013年8月12日
 * @Version:1.1.0
 */

public class ExcelProcessor<T> {

  private List<T> list;
  private Class<T> clazz;
  private String sheetName;
  private TreeSet<ExcelConfigration> set;

  public ExcelProcessor(List<T> list, Class<T> clazz, String sheetName) {
    super();
    this.list = list;
    this.clazz = clazz;
    this.sheetName = sheetName;
  }

  private void installConfig() {
    this.set = new TreeSet<ExcelConfigration>();
    Method[] methods = clazz.getMethods();
    for (Method m : methods) {
      ExcelConf excelConf = m.getAnnotation(ExcelConf.class);
      if (excelConf != null) {
        ExcelConfigration conf = new ExcelConfigration();
        conf.setOrder(Integer.valueOf(excelConf.order()));
        conf.setHeadName(excelConf.headName());
        conf.setMethodName(m.getName());
        conf.setReturnType(m.getReturnType());
        set.add(conf);
      }
    }

  }

  public InputStream generateExcelSteam() throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    installConfig();
    ByteArrayInputStream aas = null;
    HSSFWorkbook wb = new HSSFWorkbook();
    HSSFSheet sheet = wb.createSheet(sheetName);
    HSSFRow row = sheet.createRow(0);
    HSSFCellStyle style = wb.createCellStyle();
    style.setAlignment(CellStyle.ALIGN_CENTER);
    int i = 0;
    for (ExcelConfigration conf : set) {
      HSSFCell cell = row.createCell(i);
      cell.setCellValue(new HSSFRichTextString(conf.getHeadName()));
      cell.setCellStyle(style);
      i++;
    }
    int k = 0;
    for (T t : list) {
      row = sheet.createRow(k + 1);
      int cellNum = 0;
      for (ExcelConfigration conf : set) {
        HSSFCell cell = row.createCell(cellNum);
        Method m = clazz.getMethod(conf.getMethodName(), null);
        Class retTypeClass = conf.getReturnType();
        if (retTypeClass.isInstance(Integer.class) || retTypeClass.isInstance(Double.class)
            || retTypeClass.isInstance(Float.class)) {
          cell.setCellValue(Double.valueOf(String.valueOf(m.invoke(t))));
        } else {
          cell.setCellValue(new HSSFRichTextString(String.valueOf(m.invoke(t))));
        }
        cell.setCellStyle(style);
        cellNum++;
      }
      k++;
    }
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    try {
      wb.write(os);
    } catch (IOException e) {
      e.printStackTrace();
    }
    byte[] content = os.toByteArray();
    aas = new ByteArrayInputStream(content);
    return aas;
  }
}
