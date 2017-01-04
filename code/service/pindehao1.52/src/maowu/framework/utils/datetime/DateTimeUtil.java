package maowu.framework.utils.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

public class DateTimeUtil {

  private static final Logger logger = Logger.getLogger(DateTimeUtil.class);
  private static String datePattern = "MM/dd/yyyy";
  private static String timePattern = "HH:mm";
  private static String dateTimePattern = "yyyyMMddHHmmss";

  /**
   * Return default datePattern (MM/dd/yyyy)
   * 
   * @return a string representing the date pattern on the UI
   */
  public static String getDatePattern() {
    return datePattern;
  }

  /**
   * This method attempts to convert an Oracle-formatted date in the form dd-MMM-yyyy to mm/dd/yyyy.
   * 
   * @param aDate date from database as a string
   * @return formatted string for the ui
   */
  public static final String getDate(Date aDate) {
    SimpleDateFormat df = null;
    String returnValue = "";

    if (aDate != null) {
      df = new SimpleDateFormat(datePattern);
      returnValue = df.format(aDate);
    }

    return returnValue;
  }

  /**
   * dateTimePattern = "yyyyMMddHHmmss";
   * 
   * @return
   */
  public static final String getDateTime(String pattern) {
    SimpleDateFormat df = null;
    String returnValue = "";

    df = new SimpleDateFormat(pattern);
    returnValue = df.format(new Date());

    return returnValue;
  }

  public static final String getDateTime() {
    SimpleDateFormat df = null;
    String returnValue = "";

    df = new SimpleDateFormat(dateTimePattern);
    returnValue = df.format(new Date());

    return returnValue;
  }

  /**
   * This method generates a string representation of a date/time in the format you specify on input
   * 
   * @param aMask the date pattern the string is in
   * @param strDate a string representation of a date
   * @return a converted Date object
   * @see java.text.SimpleDateFormat
   * @throws ParseException
   */
  public static final Date convertStringToDate(String aMask, String strDate) throws ParseException {
    SimpleDateFormat df = null;
    Date date = null;
    df = new SimpleDateFormat(aMask);

    if (logger.isDebugEnabled()) {
      logger.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
    }

    try {
      date = df.parse(strDate);
    } catch (ParseException pe) {
      // logger.error("ParseException: " + pe);
      throw new ParseException(pe.getMessage(), pe.getErrorOffset());
    }

    return date;
  }

  /**
   * This method returns the current date time in the format: MM/dd/yyyy HH:MM a
   * 
   * @param theTime the current time
   * @return the current date/time
   */
  public static String getTimeNow(Date theTime) {
    return getDateTime(timePattern, theTime);
  }

  /**
   * This method returns the current date in the format: MM/dd/yyyy
   * 
   * @return the current date
   * @throws ParseException
   */
  public static Calendar getToday() throws ParseException {
    Date today = new Date();
    SimpleDateFormat df = new SimpleDateFormat(datePattern);

    // This seems like quite a hack (date -> string -> date),
    // but it works ;-)
    String todayAsString = df.format(today);
    Calendar cal = new GregorianCalendar();
    cal.setTime(convertStringToDate(todayAsString));

    return cal;
  }

  /**
   * This method generates a string representation of a date's date/time in the format you specify
   * on input
   * 
   * @param aMask the date pattern the string is in
   * @param aDate a date object
   * @return a formatted string representation of the date
   * 
   * @see java.text.SimpleDateFormat
   */
  public static final String getDateTime(String aMask, Date aDate) {
    SimpleDateFormat df = null;
    String returnValue = "";

    if (aDate == null) {
      logger.error("aDate is null!");
    } else {
      df = new SimpleDateFormat(aMask);
      returnValue = df.format(aDate);
    }

    return returnValue;
  }

  /**
   * This method generates a string representation of a date based on the System Property
   * 'dateFormat' in the format you specify on input
   * 
   * @param aDate A date to convert
   * @return a string representation of the date
   */
  public static final String convertDateToString(Date aDate) {
    return getDateTime(datePattern, aDate);
  }

  /**
   * This method converts a String to a date using the datePattern
   * 
   * @param strDate the date to convert (in format MM/dd/yyyy)
   * @return a date object
   * 
   * @throws ParseException
   */
  public static Date convertStringToDate(String strDate) throws ParseException {
    Date aDate = null;

    try {
      if (logger.isDebugEnabled()) {
        logger.debug("converting date with pattern: " + datePattern);
      }

      aDate = convertStringToDate(datePattern, strDate);
    } catch (ParseException pe) {
      logger.error("Could not convert '" + strDate + "' to a date, throwing exception");
      pe.printStackTrace();
      throw new ParseException(pe.getMessage(), pe.getErrorOffset());

    }

    return aDate;
  }

  /**
   * 获得当月的第一天
   * 
   * @return
   */
  public static String getFirstDayOfMonth() {
    String day = "";
    Calendar cal = new GregorianCalendar();
    if (cal.get(Calendar.MONTH) + 1 < 10) {
      day = cal.get(Calendar.YEAR) + "-0" + (cal.get(Calendar.MONTH) + 1) + "-01";
    } else {
      day = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-01";
    }
    return day;
  }

  /**
   * 获得当月的最后一天
   * 
   * @return
   */
  public static String getLastDayOfMonth() {
    String day = "";
    try {
      Calendar cal = new GregorianCalendar();
      int year;
      int month;
      year = cal.get(Calendar.YEAR);
      month = cal.get(Calendar.MONTH) + 1; // 实际月份的后有一个月
      if (month > 11) {
        year++;
        month = 12;
      }
      GregorianCalendar cal1 = new GregorianCalendar();
      cal1.set(year, month - 1, 1);
      cal1.roll(Calendar.DATE, -1); // 向前回滚得到当月的日期
      int date = cal1.get(Calendar.DATE);
      if (month < 10) {
        if (date < 10) {
          day = year + "-0" + month + "-0" + date;
        } else {
          day = year + "-0" + month + "-" + date;
        }
      } else {
        if (date < 10) {
          day = year + "-" + month + "-0" + date;
        } else {
          day = year + "-" + month + "-" + date;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return day;
  }

  /**
   * 加减月
   * 
   * @param month
   * @param offset
   * @param pattern
   * @return
   */
  public static String addMonth(String month, int offset, String pattern) {

    try {
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      Calendar cl = Calendar.getInstance();
      cl.setTime(sdf.parse(month));
      cl.add(Calendar.MONTH, offset);
      return sdf.format(cl.getTime());
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 转换日期字符串
   * 
   * @param day
   * @param srcPattern
   * @param toPattern
   * @return
   */
  public static String convertDayString(String day, String srcPattern, String toPattern) {
    try {
      SimpleDateFormat srcFormat = new SimpleDateFormat(srcPattern);
      SimpleDateFormat toFormat = new SimpleDateFormat(toPattern);
      return toFormat.format(srcFormat.parse(day));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * @Title: compareDate
   * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
   * @param s
   * @param e
   * @return boolean
   * @throws
   * @author lyt
   */
  public static boolean compareDate(String s, String e) {
    if (fomatDate(s) == null || fomatDate(e) == null) {
      return false;
    }
    return fomatDate(s).getTime() >= fomatDate(e).getTime();
  }

  /**
   * 格式化日期
   * 
   * @return
   */
  public static Date fomatDate(String date) {
    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      return fmt.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 时间戳转日期字符串
   * 
   * @param args
   * @throws ParseException
   */
  public static String currToDateStr(Long curr) {
    String date =
        new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            .format(new java.util.Date(curr * 1000));
    return date;
  }

  /**
   * 获取n天后的日期
   * 
   * @param n
   * @return
   */
  public static String getDate(int n) {
    Date date = new Date();// 取时间
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, n);// 把日期往后增加一天.整数往后推,负数往前移动
    date = calendar.getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(date);
  }

  /**
   * 计算时间间隔几分钟
   * 
   * @param nowTime 当前时间
   * @param acTime 活动时间
   * @return
   * @throws Exception
   */
  public static Long getTimeChange(Date nowTime, Date acTime) throws Exception {
    // 计算差值，分钟数
    long minutes = -(nowTime.getTime() - acTime.getTime()) / (1000 * 60);
    // System.out.println("计算差值，分钟数" + minutes);
    return minutes;
  }
}
