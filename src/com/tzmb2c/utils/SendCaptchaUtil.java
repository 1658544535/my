package com.tzmb2c.utils;

import java.util.HashMap;

public class SendCaptchaUtil {
  public final static String SendCaptcha(String phone, String UserKey) {


    StringBuffer sbf = new StringBuffer();
    // XML头部
    sbf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    sbf.append("<PLUGINREQUEST>");
    new HashMap<String, String>();
    // params.put("SpCode", "003096");
    // params.put("LoginName", "zj_qyjs");
    // params.put("Password", "qyjs0205");
    // params.put("SpCode", "210837");
    // params.put("LoginName", "jh_hykj");
    // params.put("Password", "hykj0205");
    // params.put("MessageContent", "欢迎使用嗨乐服务，您的验证码是"+num+"，请在页面填写完成验证。");
    // params.put("UserNumber", loginName);
    // params.put("SerialNumber", "20140620010101010000");
    // params.put("ScheduleTime", "");
    // params.put("f", "1");
    //
    // if (HttpUtil.http(url, params).contains("result=0")) {
    sbf.append("<BIZCODE>").append("消息标志").append("</BIZCODE>");
    sbf.append("<BIZNAME>").append("接口业务名称").append("</BIZNAME>");
    sbf.append("<TRANSID>").append("消息序列号").append("</TRANSID>");
    sbf.append("<TIMESTAMP>").append("时间戳").append("</TIMESTAMP>");
    sbf.append("<VERSION").append("业务内容报文的版本号").append("</VERSION>");
    sbf.append("<SVCCONT>").append("加密后的消息体，即Base64(DES(MD5(消息体) + 消息体))").append("</SVCCONT>");
    //
    // }else{
    // sbf.append("<retCode>").append("false").append("</retCode>");
    // sbf.append("<errorMsg>").append("验证码发送失败").append("</errorMsg>");
    // }
    sbf.append("</PLUGINREQUEST>");
    return null;
  }

  public static void main(String[] args) {

    System.out.print(MD5Util.MD5("admin"));

  }
}
