package com.tzmb2c.utils.export.excel;



public class Student {
  private String sno;
  private String name;
  private String age;

  @ExcelConf(headName = "身份证号", order = "1")
  public String getSno() {
    return sno;
  }

  public void setSno(String sno) {
    this.sno = sno;
  }

  @ExcelConf(headName = "名字", order = "2")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @ExcelConf(headName = "年龄", order = "3")
  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }



}
