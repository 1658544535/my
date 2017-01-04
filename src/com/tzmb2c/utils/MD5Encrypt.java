package com.tzmb2c.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class MD5Encrypt {
  /**
   * 十六进制字符数组,用于加密字节数组转化为十六进制字符串时使用
   */
  private static char hexChars[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
      'C', 'D', 'E', 'F'};

  private MD5Encrypt() {}


  /**
   * 加密方法,对原字符串str进行MD5加密,返回加密后的字符串
   * 
   * @param str 原字符串
   * @return String 加密后字符串
   * @throws NoSuchAlgorithmException 当所运行的jdk不支持MD5算法时抛出此异常
   * @throws UnsupportedEncodingException
   * @throws IllegalArgumentException 当输入参数为null或长度为0时抛出此异常
   */
  public static String md5(String str) throws NoSuchAlgorithmException,
      UnsupportedEncodingException {
    // 返回的加密字符串
    String strDesc = null;
    // 输入参数检测
    if (str == null || str.length() <= 0) {
      throw new IllegalArgumentException("输入参数str为空或长度为0.");
    }
    // 获取MD5加密器
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw e;
    }
    // 加密处理
    byte[] byteTheDesc = md.digest(str.getBytes("GBK"));
    // 加密后的十六进制字符数组
    char[] theChars = new char[byteTheDesc.length * 2];
    // 将加密后的字节数组转化为十六进制字符串
    int k = 0;
    byte b = 0;
    for (int i = 0; i < byteTheDesc.length; i++) {
      b = byteTheDesc[i];
      theChars[k++] = hexChars[b >>> 4 & 0xf];
      theChars[k++] = hexChars[b & 0xf];
    }
    strDesc = new String(theChars);
    // 返回加密后的字符串
    return strDesc;
  }

  public static void main(String[] args) {
    Date d = new Date();
    System.out.println(d);

  }
}
