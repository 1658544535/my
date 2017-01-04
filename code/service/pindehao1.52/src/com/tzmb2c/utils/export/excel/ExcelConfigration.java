package com.tzmb2c.utils.export.excel;

/**
 * 
 * @Title:excel导入对象整体配置解析类
 * @Description:
 * @Author:fengjianshe
 * @Since:2013年8月13日
 * @Version:1.1.0
 */
public class ExcelConfigration implements Comparable {
  // 排序
  private int order;
  // 返回类型
  private Class<?> returnType;
  // 标题名
  private String headName;
  // 方法名
  private String methodName;

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  public Class<?> getReturnType() {
    return returnType;
  }

  public void setReturnType(Class<?> returnType) {
    this.returnType = returnType;
  }

  public String getHeadName() {
    return headName;
  }

  public void setHeadName(String headName) {
    this.headName = headName;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  @Override
  public int compareTo(Object obj) {
    // TODO Auto-generated method stub
    ExcelConfigration conf = (ExcelConfigration) obj;
    int num = new Integer(this.order).compareTo(new Integer(conf.getOrder()));
    return num;

  }

}
