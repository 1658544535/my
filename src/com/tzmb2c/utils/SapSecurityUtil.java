package com.tzmb2c.utils;



public class SapSecurityUtil {
  private static final String key = "ABCDEFG123!@#$%^123456";

  public static String encrypt(String str) {
    String rtn = "";
    DESClient des = null;
    des = DESClient.getInstance(key);

    rtn = des.encrypt(str);
    return rtn;
  }

  public static String decrypt(String str) {
    String rtn = "";
    DESClient des = DESClient.getInstance(key);
    rtn = des.decrypt(str);
    return rtn;
  }

  public static void main(String[] args) {
    String x =
        "<?xml version='1.0' encoding='UTF-8'?><BODY><EID>20971990399</EID><USERID>admin</USERID><PASSWORD>mt95m4ub</PASSWORD><PLUGINTYPE>05</PLUGINTYPE></BODY>";

    System.out.println(decrypt(x));
  }
}
