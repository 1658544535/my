package com.tzmb2c.utils;


public class SmsSendUtil {
  public static boolean sendSMS(String phone, String text) {
    YunPianSMSApi.sendSms(phone, text);
    return true;
  }

  public static boolean sendSMS(String phone, String code, long tpId) {
    YunPianSMSApi.sendSms(phone, code, tpId);
    return true;
  }

  public static void main(String[] args) {
    // System.out.println(SmsSendUtil.sendSMS("13428342228", "888888", 1179353));
    // System.out.println(SmsSendUtil.sendSMS("13428342228", "", 1179365));
    // System.out.println(SmsSendUtil.sendSMS("13428342228",
    // "【拼得好】您申请了 拼得好 会员注册，验证码为：777777，两分钟内有效。请在注册页面中输入验证码完成注册，淘竹马将竭诚为您服务。"));
    // String content = "【拼得好】客官您好，感谢您支持淘竹马，你的包裹已寄出，请注意查收，客服电话/QQ：4001503677，登陆APP邀请好友分享快乐。" ;
    // SmsSendUtil.sendSMS("13428342228", content);
  }
}
