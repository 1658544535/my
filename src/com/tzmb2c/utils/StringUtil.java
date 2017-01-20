package com.tzmb2c.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import maowu.framework.utils.datetime.DateTimeUtil;

import org.apache.commons.lang3.StringUtils;

import com.tzmb2c.business.service.WalletService;

public class StringUtil {
  public static Random r = new Random();
  private static Integer serialNum = 10000;

  /**
   * 获取业务主键
   * 
   * @return
   */
  public static String getPrimarykey() {
    String primarykey = DateTimeUtil.getDateTime("yyyyMMddHHmmssSSS") + new Random().nextInt(100);
    if (StringUtils.isNotEmpty(primarykey) && primarykey.length() > 30) {
      primarykey = primarykey.substring(0, 30);
    }
    return primarykey;
  }

  /**
   * 格式化日期
   * 
   * @param dateStr yyyy-MM-dd
   * @return retStr yyyyMMdd
   */
  public static String formatDateStr(String dateStr) {
    String retStr = null;
    if (StringUtils.isNotEmpty(dateStr)) {
      if (dateStr.indexOf("-") > -1) {
        retStr = dateStr.replace("-", "");
      }
    }
    return retStr;
  }

  public static Date stringToDate(String dateStr) {
    // DateFormat dd = new SimpleDateFormat(formatStr);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = null;
    try {
      date = sdf.parse(dateStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return date;
  }

  public static Date stringDate(String dateStr) {
    // DateFormat dd = new SimpleDateFormat(formatStr);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = null;
    try {
      date = sdf.parse(dateStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return date;
  }

  public static String dateToString(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String str = sdf.format(date);
    return str;
  }

  // 订单显示精确到秒
  public static String dateString(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String str = sdf.format(date);
    return str;
  }

  // 显示精确到分
  public static String dateStr(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String str = sdf.format(date);
    return str;
  }

  /***
   * 获取当前时间的字符串类型
   * 
   * @return
   * @Description:精确到毫秒加三位随机数
   */
  public static String getCurrentDateStr() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
    java.util.Date date = new java.util.Date();
    Random rad = new Random();
    return sdf.format(date) + rad.nextInt(1000) + "";
  }

  /***
   * 获取当前时间的字符串类型,精确到毫秒
   * 
   * @return
   * @Description:
   */
  public static String getCurrentDateStrByfu() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
    java.util.Date date = new java.util.Date();
    return sdf.format(date);
  }

  /**
   * 
   * 字符串剔除HTML标签
   * */
  public static String delHTMLTag(String htmlStr) {
    String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

    Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
    Matcher m_script = p_script.matcher(htmlStr);
    htmlStr = m_script.replaceAll(""); // 过滤script标签

    Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
    Matcher m_style = p_style.matcher(htmlStr);
    htmlStr = m_style.replaceAll(""); // 过滤style标签

    Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
    Matcher m_html = p_html.matcher(htmlStr);
    htmlStr = m_html.replaceAll(""); // 过滤html标签

    return htmlStr.trim(); // 返回文本字符串
  }

  /**
   * 
   * 提取字符串内所有的img标签下的src
   * */
  public static String[] getImagesSrc(String content) {
    Pattern p = Pattern.compile("(?:src=\"?)(.*?)\"?\\s");
    Matcher m = p.matcher(content);
    int k = 0;
    while (m.find()) {
      k = ++k;
      System.out.println(k);
    }
    String[] arr = new String[k];
    int i = 0;
    Matcher b = p.matcher(content);
    while (b.find()) {
      arr[i] = b.group(1);
      System.out.println("路径是：" + b.group(1));

      System.out.println("hehe:" + arr[i]);
      i++;
    }
    System.out.println();
    for (int j = 0; j < arr.length; j++) {
      System.out.println("路径是+：" + arr[j]);
    }
    return arr;
  }

  /*
   * public static void main(String[] args) { String bireinfo = ""; Pattern p =
   * Pattern.compile("(?:src=\"?)(.*?)\"?\\s"); Matcher m = p.matcher(bireinfo); int k = 0; while
   * (m.find()) { k = ++k; System.out.println(k); } String[] arr = new String[k]; int i = 0; Matcher
   * b = p.matcher(bireinfo); while (b.find()) { arr[i] = b.group(1); System.out.println("路径是：" +
   * b.group(1));
   * 
   * System.out.println("hehe:" + arr[i]); i++; } System.out.println(); for (int j = 0; j <
   * arr.length; j++) { System.out.println("路径是+：" + arr[j]); } int aa = CheckSecurity("123456");
   * System.out.println(aa); for (int j = 0; j < 100; j++) { genRandomStr(5); }
   * 
   * }
   */

  /**
   * 规则：1.长度小于6的为弱 2.单一数字、字母或符号的为弱 3.使用两种符号的为中 4.两种以上的为强
   * 
   * @param pwd
   * @return
   */
  public static int CheckSecurity(String pwd) {
    Pattern pattern = Pattern.compile("^(?:([a-z])|([A-Z])|([0-9])|(.)){6,}|(.)+$");
    Matcher matcher = pattern.matcher(pwd);
    String mat = matcher.replaceAll("$1$2$3$4$5");
    return mat.length();
  }

  /**
   * 产生随机数
   * 
   * @param len 位数
   * @return
   */
  public static String genRandomStr(int len) {
    String str = "";
    Random ran = new Random();
    String v = StringUtils.rightPad("1", len + 1, "0");
    int num = 0;
    num = ran.nextInt(Integer.parseInt(v));
    str = StringUtils.leftPad(String.valueOf(num), len, "0");
    // System.out.println(str);
    return str;
  }

  /**
   * 转换null为空字符串
   * 
   * @param str
   * @throw
   * @return String
   */
  public static String checkString(String str) {
    return str == null ? "" : str;
  }

  /**
   * 转换null为数字0
   * 
   * @param str
   * @throw
   * @return String
   */
  public static Integer checkInt(Integer num) {
    return num == null ? 0 : num;
  }

  /**
   * 转换null为数字0
   * 
   * @param str
   * @throw
   * @return String
   */
  public static Long checkLong(Long num) {
    return num == null ? 0L : num;
  }

  /**
   * 转换null为数字0
   * 
   * @param str
   * @throw
   * @return String
   */
  public static Double checkDouble(Double num) {
    return num == null ? 0.0 : num;
  }

  /**
   * 接口输出值检查
   * 
   * @throw
   * @return String
   */
  public static String checkVal(Object o) {
    if (o == null || "null".equals(o)) {
      return "";
    } else if (o instanceof Double || o instanceof Float) {
      DecimalFormat df = new DecimalFormat("#.##");
      return df.format(o);
    } else if (o instanceof Date) {
      return dateString((Date) o);
    } else {
      return String.valueOf(o);
    }
  }

  /**
   * 返回粉丝数字典
   * 
   * @param fanType
   * @throw
   * @return String
   */
  public static String fansDic(int fanType) {
    String name = "";
    if (fanType == 1) {
      name = "2000≤粉丝数<10000";
    } else if (fanType == 2) {
      name = "1W≤粉丝数＜5W";
    } else if (fanType == 3) {
      name = "5W≤粉丝数＜10W";
    } else if (fanType == 4) {
      name = "10W≤粉丝数＜50W";
    } else if (fanType == 5) {
      name = "50W≤粉丝数＜100W";
    } else if (fanType == 6) {
      name = "100W≤粉丝数＜200W";
    } else if (fanType == 7) {
      name = "粉丝数≥200W";
    }
    return name;
  }

  /**
   * 返回内容产出字典
   * 
   * @param fanType
   * @throw
   * @return String
   */
  public static String contentDic(int contType) {
    String name = "";
    if (contType == 1) {
      name = "1";
    } else if (contType == 2) {
      name = "2";
    } else if (contType == 3) {
      name = "3";
    } else if (contType == 4) {
      name = "4";
    } else if (contType == 5) {
      name = "5";
    } else if (contType == 6) {
      name = "6";
    } else if (contType == 7) {
      name = "7";
    } else if (contType == 8) {
      name = "8";
    } else if (contType == 9) {
      name = "9";
    } else if (contType == 10) {
      name = "10";
    } else if (contType == 11) {
      name = "11-20";
    } else if (contType == 12) {
      name = "21-30";
    } else if (contType == 13) {
      name = "31-40";
    } else if (contType == 14) {
      name = "40以上";
    }
    return name;
  }

  /**
   * 生成不重复随机字符串包括字母数字
   * 
   * @param len
   * @return
   */
  public static String generateRandomStr(int len) {
    // 字符源，可以根据需要删减
    String generateSource = "0123456789";
    String rtnStr = "";
    for (int i = 0; i < len; i++) {
      // 循环随机获得当次字符，并移走选出的字符
      String nowStr =
          String.valueOf(generateSource.charAt((int) Math.floor(Math.random()
              * generateSource.length())));
      rtnStr += nowStr;
      generateSource = generateSource.replaceAll(nowStr, "");
    }
    return rtnStr;
  }

  /**
   * 时间戳转日期字符串
   */
  public static String timestampToDateStr(Long timestamp) {
    String date =
        new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(
            timestamp * 1000));
    return date;
  }

  /**
   * 判断long值是否为空
   * 
   * @param value long类型的值
   * @return true:不为空且不等于0;false:为空或等于0
   */
  public static Boolean checkIsNotNull(Long value) {
    return value != null && value > 0 ? true : false;
  }

  /**
   * 
   * 将地址接收到的json转成str
   * 
   * @param url
   * @return
   * @throw
   * @return String
   */
  public static String loadJson(String url) {
    StringBuilder json = new StringBuilder();
    try {
      URL urlObject = new URL(url);
      URLConnection uc = urlObject.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
      String inputLine = null;
      while ((inputLine = in.readLine()) != null) {
        json.append(inputLine);
      }
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return json.toString();
  }

  /**
   * 
   * 将地址接收到的json转成str
   * 
   * @param url URL-例如：http://wxpdh.choupinhui.net/wx_msgtpl.php
   * @param param 参数-例如：act=delivery&oid=&openid=
   * @param method 调用方法(get|post)
   * @return
   * @throw
   * @return String
   */
  public static String loadJson(String url, String param, String method) {
    StringBuilder json = new StringBuilder();
    try {
      if ("get".equals(method)) {
        url = url + "?" + param;
      }
      System.out.println(">>>>>通知链接" + url);

      // String url = url.substring(0, url.indexOf("?"))
      URL urlObject = new URL(url);
      URLConnection uc = urlObject.openConnection();

      if ("post".equals(method)) {
        // uc.setRequestProperty("accept", "*/*");
        // uc.setRequestProperty("connection", "Keep-Alive");
        // uc.setRequestProperty("user-agent",
        // "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        uc.setDoOutput(true);
        uc.setDoInput(true);
        PrintWriter out = new PrintWriter(uc.getOutputStream());
        // String param = url.substring(url.indexOf("?") + 1);
        out.print(param);
        out.flush();
      }

      BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
      String inputLine = null;
      while ((inputLine = in.readLine()) != null) {
        json.append(inputLine);
      }
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(json);
    return json.toString();
  }

  /**
   * 获取序列号
   * 
   * @return
   */
  public static Integer getSerialNum() {
    if (serialNum >= 99999) {
      serialNum = 10000;
    } else {
      serialNum++;
    }
    return serialNum;
  }

  /**
   * 计算百分比字符串
   * 
   * @param g 子
   * @param z 母
   * @return
   */
  public static String calcPerceStr(Integer g, Integer z) {
    double percent = Double.valueOf(g) / Double.valueOf(z);
    NumberFormat nt = NumberFormat.getPercentInstance();
    nt.setMinimumFractionDigits(2);
    return nt.format(percent);
  }

  /**
   * 
   * 获取兑换码
   * 
   * @return
   * @throw
   * @return String
   */
  public static String getRedeemCode() {
    String code =
        System.currentTimeMillis() + new WalletService().genInviteCode()
            + System.currentTimeMillis();
    code = MD5Util.MD5(code);
    code = code.substring(10, 22);
    return code;
  }


}
