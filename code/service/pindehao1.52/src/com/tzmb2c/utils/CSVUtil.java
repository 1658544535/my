package com.tzmb2c.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class CSVUtil {

  /**
   * 导出
   * 
   * @param file csv文件(路径+文件名)
   * @param dataList 数据
   * @return
   */
  public static boolean exportCsv(File file, List<String> dataList) {
    boolean isSucess = false;
    BufferedWriter bw = null;
    try {
      bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "gbk"));// 中文乱码
      if (dataList != null && !dataList.isEmpty()) {
        for (String data : dataList) {
          bw.append(data).append("\r\n");
        }
      }
      isSucess = true;
    } catch (Exception e) {
      isSucess = false;
    } finally {
      if (bw != null) {
        try {
          bw.close();
          bw = null;
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return isSucess;
  }

  /**
   * 导入
   * 
   * @param file csv文件(路径+文件)
   * @return
   */
  public static List<String> importCsv(File file) {
    List<String> dataList = new ArrayList<String>();

    BufferedReader br = null;
    try {
      br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));// 中文乱码
      String line = "";
      while ((line = br.readLine()) != null) {
        dataList.add(line);
      }
    } catch (Exception e) {
    } finally {
      if (br != null) {
        try {
          br.close();
          br = null;
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

    return dataList;
  }
}
