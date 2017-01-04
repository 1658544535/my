package com.tzmb2c.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class StringDate {
  public static Random r = new Random();

  /**
   * 判断是否为当日时间
   * 
   * @return
   */
  public static boolean isToday(Date date) {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(date);
    int year1 = c1.get(Calendar.YEAR);
    int month1 = c1.get(Calendar.MONTH) + 1;
    int day1 = c1.get(Calendar.DAY_OF_MONTH);
    Calendar c2 = Calendar.getInstance();
    c2.setTime(new Date());
    int year2 = c2.get(Calendar.YEAR);
    int month2 = c2.get(Calendar.MONTH) + 1;
    int day2 = c2.get(Calendar.DAY_OF_MONTH);
    if (year1 == year2 && month1 == month2 && day1 == day2) {
      return true;
    }
    return false;
  }

  /**
   * 判断是否为本周
   * 
   * @return
   */
  public static boolean isThisWeek(Date date) {
    Calendar c1 = Calendar.getInstance();
    c1.setTime(date);
    int year1 = c1.get(Calendar.YEAR);
    int Week1 = c1.get(Calendar.WEEK_OF_YEAR);
    Calendar c2 = Calendar.getInstance();
    c2.setTime(new Date());
    int year2 = c2.get(Calendar.YEAR);
    int Week2 = c2.get(Calendar.WEEK_OF_YEAR);
    if (year1 == year2 && Week1 == Week2) {
      return true;
    }
    return false;
  }

  /**
   * 日期转换为星期
   * 
   * @return
   */
  public static String getWeekOfDate(Date date) {
    String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
    if (w < 0) {
      w = 0;
    }
    return weekDays[w];
  }
}
