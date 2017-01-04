package com.tzmb2c.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Excel {

  public static void createExcel(String exceSheetlName, List<Map<String, Object>> mapList) {

    // 先创建工作簿对象
    HSSFWorkbook workbook2003 = new HSSFWorkbook();
    // 创建工作表对象并命名
    HSSFSheet sheet = workbook2003.createSheet(exceSheetlName);

    int k = 0;

    List<String> mapNameList = new ArrayList<>();

    for (int i = 0; i < mapList.size(); i++) {
      Map<String, Object> map = new HashMap<String, Object>();
      map = null;

      map = mapList.get(i);

      map.keySet().iterator();
      Iterator iter = map.entrySet().iterator();
      if (i == 0) {
        HSSFRow row = sheet.createRow(0);
        while (iter.hasNext()) {
          Map.Entry<String, String> e = (Map.Entry<String, String>) iter.next();
          row.createCell(k).setCellValue(e.getKey().toString());
          mapNameList.add(e.getKey().toString());
          System.out.println("Key:" + e.getKey() + "Value:" + e.getValue());
          k++;
        }
      }

      HSSFRow newRow = sheet.createRow(i + 1);
      for (int j = 0; j < mapNameList.size(); j++) {
        newRow.createCell(j).setCellValue(map.get(mapNameList.get(j).toString()).toString());
      }
    }

    // 生成文件
    File file = new File("d:\\Mystudent.xls");
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(file);
      workbook2003.write(fos);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (fos != null) {
        try {
          fos.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

  }
}
