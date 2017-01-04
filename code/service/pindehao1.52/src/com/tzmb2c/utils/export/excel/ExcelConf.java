package com.tzmb2c.utils.export.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Title:对需要到处EXCEL的pojo类做排序和表头的预定义
 * @Description:
 * @Author:fengjianshe
 * @Since:2013年8月13日
 * @Version:1.1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExcelConf {
  public String headName() default "";

  public String order();
}
