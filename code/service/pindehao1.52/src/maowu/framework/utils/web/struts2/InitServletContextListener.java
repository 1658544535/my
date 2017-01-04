package maowu.framework.utils.web.struts2;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tencent.WXPay;
import com.tencent.common.Util;
import com.tzmb2c.utils.PropertiesHelper;

public class InitServletContextListener implements ServletContextListener {
  @Override
  public void contextDestroyed(ServletContextEvent arg0) {}

  @Override
  public void contextInitialized(ServletContextEvent arg0) {
    System.out.println("加载微信支付参数");
    try {
      // key 签名算法需要用到的秘钥
      String key = PropertiesHelper.getValue("wx_key");
      // appID 公众账号ID
      String appID = PropertiesHelper.getValue("wx_app_id");
      // mchID 商户ID
      String mchID = PropertiesHelper.getValue("wx_mch_id");
      // sdbMchID 子商户ID，受理模式必填
      String sdbMchID = null;
      // certLocalPath HTTP证书在服务器中的路径，用来加载证书用
      String certLocalPath = PropertiesHelper.getValue("wx_cert_path");
      // certPassword HTTP证书的密码，默认等于MCHID
      String certPassword = mchID;
      WXPay.initSDKConfiguration(key, appID, mchID, sdbMchID, certLocalPath, certPassword);

      // key 签名算法需要用到的秘钥
      String jskey = PropertiesHelper.getValue("wx_js_key");
      // appID 公众账号ID
      String jsappID = PropertiesHelper.getValue("wx_js_app_id");
      // mchID 商户ID
      String jsmchID = PropertiesHelper.getValue("wx_js_mch_id");
      // sdbMchID 子商户ID，受理模式必填
      String jssdbMchID = null;
      // certLocalPath HTTP证书在服务器中的路径，用来加载证书用
      String jscertLocalPath = PropertiesHelper.getValue("wx_js_cert_path");
      // certPassword HTTP证书的密码，默认等于MCHID
      String jscertPassword = jsmchID;
      // 公众号参数初始化
      WXPay.initJSConfiguration(jskey, jsappID, jsmchID, jssdbMchID, jscertLocalPath,
          jscertPassword);
      Util.log(">>> 初始化微信支付参数成功，APPID=" + appID);
    } catch (Exception e) {
      System.out.println("加载微信支付参数失败！");
      e.printStackTrace();
    }
  }
}
