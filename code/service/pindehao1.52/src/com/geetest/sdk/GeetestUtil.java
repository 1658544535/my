package com.geetest.sdk;

import javax.servlet.http.HttpServletRequest;


public class GeetestUtil {

  /**
   * 获取极验验证码.
   * 
   * @param request
   * @return
   */
  public static String getGeetestCaptcha(HttpServletRequest request) {
    GeetestLib gtSdk = new GeetestLib();
    gtSdk.setCaptchaId(GeetestConfig.getCaptcha_id());
    gtSdk.setPrivateKey(GeetestConfig.getPrivate_key());

    String resStr = "{}";
    // 进行验证预处理
    if (gtSdk.preProcess() == 1) {
      // gt-server服务正常,预处理完成
      System.out.println(">>1-预处理成功");
      resStr = gtSdk.getSuccessPreProcessRes(); // 预处理成功，获取标准返回
      gtSdk.setGtServerStatusSession(request, 1); // 在session中设置gt-server服务状态
      System.out.println(">>1-状态1");
    } else {
      // 预处理失败
      System.out.println(">>1-预处理失败");
      resStr = gtSdk.getFailPreProcessRes(); // 无法连接到gt-server服务器，进行相应处理, 获得返回
      gtSdk.setGtServerStatusSession(request, 0); // 在session中设置gt-server服务状态
      System.out.println(">>1-状态0");
    }

    gtSdk.setChallengeSession(request); // 将challenge设置到session中，二次验证进行challenge比对
    return resStr;
  }

  /**
   * 二次校验验证码
   * 
   * @param request
   * @return
   */
  public static boolean validGeetestCaptcha(HttpServletRequest request) {
    GeetestLib gtSdk = new GeetestLib();

    gtSdk.setPrivateKey(GeetestConfig.getPrivate_key());
    // 从session中获取gt-server状态
    int gt_server_status_code = GeetestLib.getGtServerStatusSession(request);
    System.out.println(">>2-验证服务器状态：" + gt_server_status_code);
    // 从session中获取challenge
    gtSdk.getChallengeSession(request);

    String gtResult = "fail";

    if (gt_server_status_code == 1) {
      // gt-server正常，向gt-server进行二次验证
      gtResult = gtSdk.enhencedValidateRequest(request);
      System.out.println(gtResult);
    } else {
      // gt-server非正常情况下，进行failback模式验证
      System.out.println(">>>>>>geestest-failback:use your own server captcha validate");
      gtResult = "fail";

      gtResult = gtSdk.failbackValidateRequest(request);

    }

    if (gtResult.equals(GeetestLib.success_res)) {
      // 验证成功
      return true;

    } else if (gtResult.equals(GeetestLib.forbidden_res)) {
      // 验证被判为机器人
      return false;
    } else {
      // 验证失败
      return false;
    }
  }
}
