package com.tzmb2c.common;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import maowu.framework.utils.web.SuperAction;

import org.jfree.chart.JFreeChart;

import com.tzmb2c.utils.BeanFacade;
import com.tzmb2c.utils.PropertiesHelper;
import com.tzmb2c.utils.export.excel.ExcelProcessor;
import com.tzmb2c.utils.export.excel.Student;
import com.tzmb2c.utils.jfree.JfreeBarCreator;
import com.tzmb2c.utils.jfree.JfreeLineAndShapeCreator;
import com.tzmb2c.utils.jfree.JfreePieCreator;
import com.tzmb2c.utils.pagination.Pagination;

/**
 * 这！只是个demo
 * 
 * @Title:
 * @Description:
 * @Author:fengjianshe
 * @Since:2013年8月7日
 * @Version:1.1.0
 */
public class JfreeAction extends SuperAction {

  private String departmentId;
  private List<Dept> allDept = new ArrayList<Dept>();
  private JFreeChart chart;

  public void setPage(Pagination page) {}

  private String city;
  private String sex;
  /**
   * 图标图片的宽度--与弹出的宽要小;
   */
  private final int width = Integer.valueOf(PropertiesHelper.getValue("jfree.chart.width"));
  /**
   * 图标图片的高度--与弹出的高要小;
   */
  private final int height = Integer.valueOf(PropertiesHelper.getValue("jfree.chart.height"));

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(String departmentId) {
    this.departmentId = departmentId;
  }

  public JFreeChart getChart() {
    return chart;
  }

  public void setChart(JFreeChart chart) {
    this.chart = chart;
  }

  public List<Dept> getAllDept() {
    return allDept;
  }

  public void setAllDept(List<Dept> allDept) {
    this.allDept = allDept;
  }

  /**
   * 折线图
   * 
   * @return
   * @Description:
   */
  public String createLineFreeChart() {
    double[][] data =
        new double[][] { {672, 766, 223, 540, 126}, {325, 521, 210, 340, 106},
            {332, 256, 523, 240, 526}, {432, 156, 723, 240, 326}};
    String[] rowKeys = {"苹果", "梨子", "葡萄", "西瓜"};
    String[] columnKeys = {"北京", "上海", "广州", "成都", "深圳"};
    String title = "大标题";
    String x_title = "x标题";
    String y_title = "y标题";
    // 以上数据需根据业务自行构建
    JfreeLineAndShapeCreator jf = BeanFacade.getBean(JfreeLineAndShapeCreator.class);
    chart = jf.makeLineAndShapeChart(data, rowKeys, columnKeys, title, x_title, y_title);
    return SUCCESS;

  }

  /**
   * 柱状图
   * 
   * @return
   * @Description:
   */
  public String createBarChart() {
    double[][] data = new double[][] {{672, 766, 223, 540, 126}};
    String[] rowKeys = {"苹果"};
    String[] columnKeys = {"北京", "上海", "广州", "成都", "深圳"};
    String title = "大标题";
    String x_title = "x标题";
    String y_title = "y标题";
    // 以上数据需根据业务自行构建
    JfreeBarCreator jf = BeanFacade.getBean(JfreeBarCreator.class);
    chart = jf.makeBarChart(data, rowKeys, columnKeys, x_title, y_title, title);
    return SUCCESS;

  }

  /**
   * 饼状图
   * 
   * @return
   * @Description:
   */
  public String createPieChart() {
    double[] data = {9, 91};
    String[] keys = {"失败率", "成功率"};
    String title = "饼图";

    // 以上数据需根据业务自行构建
    JfreePieCreator jf = BeanFacade.getBean(JfreePieCreator.class);
    chart = jf.makePieChart(data, keys, title);
    return SUCCESS;

  }

  public String goDemo() {

    return SUCCESS;
  }

  public String getDeparts() {
    System.out.println("------------------");
    System.out.println(departmentId);
    for (int i = 0; i < 4; i++) {
      Dept b = new Dept();
      b.setDeptId(String.valueOf(i));
      b.setDeptName(String.valueOf(i));
      allDept.add(b);
    }

    return SUCCESS;
  }

  public String getExcel() throws NoSuchMethodException, SecurityException, IllegalAccessException,
      IllegalArgumentException, InvocationTargetException {
    try {
      List<Student> list = new ArrayList<Student>();
      for (int i = 0; i < 4; i++) {
        Student st = new Student();
        st.setAge("年龄" + i);
        st.setName("名字" + i);
        st.setSno("身份证" + i);
        list.add(st);
      }
      this.downloadFileName = "excelXX";
      ExcelProcessor<Student> e = new ExcelProcessor<Student>(list, Student.class, "ceshi");

      this.inputStream = e.generateExcelSteam();
    } catch (Throwable t) {

      t.printStackTrace();
    }

    return SUCCESS;
  }

}
