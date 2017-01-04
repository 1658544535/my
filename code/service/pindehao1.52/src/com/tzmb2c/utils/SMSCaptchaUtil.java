package com.tzmb2c.utils;

import com.tzmb2c.SAPService.SAPServiceClientTest;
import com.tzmb2c.SAPService.Entity.SapEntity;

public class SMSCaptchaUtil {
  public static void SendCaptcha(String phone, String content) {

    String url = "http://120.197.89.51/sap_web2/services/SAPService";
    SAPServiceClientTest client = new SAPServiceClientTest(url);
    SapEntity se = new SapEntity();
    // 测试用户登录接口
    SAPServiceClientTest.userAuthen(se);
    // 测试短信发送接口
    try {
      client.sendSms(se, phone, content);
    } catch (Exception e) {

      e.printStackTrace();
    }
    System.out.println("msm send completed");

  }

}
