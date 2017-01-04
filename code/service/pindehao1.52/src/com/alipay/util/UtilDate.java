package com.alipay.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/* *
 * 类名：UtilDate功能：自定义订单类详细：工具类，可以用作获取系统日期、订单编号等版本：3.3日期：2012-08-17说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class UtilDate {

  /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
  public static final String dtLong = "yyyyMMddHHmmss";

  /** 完整时间 yyyy-MM-dd HH:mm:ss */
  public static final String simple = "yyyy-MM-dd HH:mm:ss";

  /** 年月日(无下划线) yyyyMMdd */
  public static final String dtShort = "yyyyMMdd";


  /**
   * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
   * 
   * @return 以yyyyMMddHHmmss为格式的当前系统时间
   */
  public static String getOrderNum() {
    Date date = new Date();
    DateFormat df = new SimpleDateFormat(dtLong);
    return df.format(date);
  }

  /**
   * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
   * 
   * @return
   */
  public static String getDateFormatter() {
    Date date = new Date();
    DateFormat df = new SimpleDateFormat(simple);
    return df.format(date);
  }

  /**
   * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
   * 
   * @return
   */
  public static String getDate() {
    Date date = new Date();
    DateFormat df = new SimpleDateFormat(dtShort);
    return df.format(date);
  }

  /**
   * 产生随机的三位数
   * 
   * @return
   */
  public static String getThree() {
    Random rad = new Random();
    return rad.nextInt(1000) + "";
  }

  /**
   * 时间差
   * 
   * @param beginStr
   * @param endStr
   * @return
   */
  public static String timeDifference(String beginStr, String endStr) {
    SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    long between = 0;
    try {
      java.util.Date begin = dfs.parse(beginStr);
      java.util.Date end = dfs.parse(endStr);
      between = end.getTime() - begin.getTime();// 得到两者的毫秒数
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    long day1 = between / (24 * 60 * 60 * 1000);
    long hour = between / (60 * 60 * 1000) - day1 * 24;
    long min = between / (60 * 1000) - day1 * 24 * 60 - hour * 60;
    long s = between / 1000 - day1 * 24 * 60 * 60 - hour * 60 * 60 - min * 60;
    // long ms = (between - day1 * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s
    // * 1000);
    // System.out.println(day1 + "天" + hour + "小时" + min + "分" + s + "秒" + ms + "毫秒");
    String dTime = "";
    if (day1 != 0) {
      dTime = day1 + "天前";
    }
    if (hour != 0 && day1 == 0) {
      dTime = hour + "小时前";
    }
    if (min != 0 && day1 == 0 && hour == 0) {
      dTime = min + "分前";
    }
    if (s != 0 && min == 0 && day1 == 0 && hour == 0) {
      dTime = s + "秒前";
    }
    return dTime;
  }
}
