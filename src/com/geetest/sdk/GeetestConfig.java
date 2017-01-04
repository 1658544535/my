package com.geetest.sdk;

/**
 * GeetestWeb配置文件
 * 
 * 
 */
public class GeetestConfig {

  // 填入自己的captcha_id和private_key
  private static final String captcha_id = "2e59d827cc99a7272e352a816a875792";
  private static final String private_key = "385b1d51aaf1bc3c3d44fd30f9cb5495";

  public static final String getCaptcha_id() {
    return captcha_id;
  }

  public static final String getPrivate_key() {
    return private_key;
  }

}
