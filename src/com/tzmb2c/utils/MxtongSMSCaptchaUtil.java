package com.tzmb2c.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class MxtongSMSCaptchaUtil {

  public static boolean sendMxtongSMS(String phone, String content)
      throws UnsupportedEncodingException {

    String url = "http://www.mxtong.net.cn/GateWay/Services.asmx/DirectSend?";
    HttpClient httpClient = new HttpClient();
    httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
    PostMethod getMethod = new PostMethod(url);
    String ContentUTF = java.net.URLEncoder.encode(content + "妈妈圈的玩具专属特卖【拼得好】", "utf-8");
    NameValuePair[] data =
        {new NameValuePair("UserID", "966818"), new NameValuePair("Account", "admin"),
            new NameValuePair("Password", "966818"), new NameValuePair("Phones", phone),
            new NameValuePair("SendType", "1"), new NameValuePair("SendTime", ""),
            new NameValuePair("PostFixNumber", ""), new NameValuePair("Content", ContentUTF)};
    getMethod.setRequestBody(data);
    getMethod.addRequestHeader("Connection", "close");
    try {
      int statusCode = httpClient.executeMethod(getMethod);
      if (statusCode != HttpStatus.SC_OK) {
        System.err.println("Method failed: " + getMethod.getStatusLine());
      }
      byte[] responseBody = getMethod.getResponseBody();
      String str = new String(responseBody, "utf-8");
      if (str.contains("GBK")) {
        str = str.replaceAll("GBK", "utf-8");
      }
      int beginPoint = str.indexOf("<RetCode>");
      int endPoint = str.indexOf("</RetCode>");
      String result = "RetCode=";
      System.out.println(result + str.substring(beginPoint + 9, endPoint));
      System.out.println(str);
      // return getMethod.getResponseBodyAsString();
    } catch (HttpException e) {
      e.getMessage();
      System.out.println(1);
    } catch (IOException e) {
      e.getMessage();
      System.out.println(2);
    } finally {
      getMethod.releaseConnection();
    }
    return true;
  }
}
