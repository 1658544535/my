package com.alipay.config;

import com.alipay.util.UtilDate;

/* *
 * 类名：AlipayConfig功能：基础配置类详细：设置帐户有关信息及返回路径版本：3.3日期：2012-08-10说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 * 
 * 提示：如何获取安全校验码和合作身份者ID1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 * 2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”
 * 
 * 安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？解决方法：1、检查浏览器配置，不让浏览器做弹框屏蔽设置2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

  // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
  // 合作身份者ID，以2088开头由16位纯数字组成的字符串
  public static String partner = "2088521027468735";
  // 商户的私钥
  public static String key = "mcm4op2s48amoqibnu3p5x2k1s6uu0qe";
  // 收款支付宝账号，以2088开头由16位纯数字组成的字符串
  public static String seller_id = partner;
  // 支付宝的私钥RSA
  public static String private_key =
      "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJjwKlren2vwGoCa/sNckYao+LeJoFKB76xy5SnKLxmpywKs99JpFhYZtv2TPZg9WZhybw+CkQ+gknJzDMsZRJJiluaRaUjMuIgRwUcToAi5ya4pxifFJ71KhL9o3RMezeqEvZsBOvjjFVm0ilreagBRbwoQ9vrTqLxzzlhV7drdAgMBAAECgYACPhtQ48zQp1+sHRm5QaU8pUOoZVM9gnxCOhkUKMvSA4RfkKu9oR7ayhu90ifoEJY8vwjT4GVCJmefWAKLA97WhNzWefNucyFFJ9w3IXUQs1Y1HiuIk9Pzw+QXVRkr/Q8MBkTs9uDh0DXExFF1e2kPo5IAxsi+tvOqsIxpYrLigQJBANM8QN1BfIRrcn1ajYtyOQlHew5ckXgTKVJ1PHTugMnFZLxMoACqHbSJwWnJMVaZYISlTYKjb03yZDZe+J+VIP0CQQC5WTmbI7IG2BA3mVdSUO6/+9GCEK4uCcOxxamHYJAjvelUCuQlGiDzhKzH5bXz0wIyHH2+q5ZCNtb74FcojjdhAkB9qtH4D3aRnQ6uzsl4lUqjX5gw8hdE9TlXKhH+ismpwDpu37Ms0gf8GRws4BnvKC5Im8MiHjMgjzzZL+T/o4vNAkEAk909mqmYbeNS1LgtNItCXMCNPGEIQ8wgZaMWAt0jQJBSI2zo4zx8CynOGU8FoDW3K7kVC9ahccOEML6hacZDIQJAHXwkkDw+NVPGgMAnSVVOSiSbxo7olYnoOktdvGe3KMI6Ybzymgc8p+vV3kd7YPLsY7lK0uGflCe0QLX0p7tiNQ==";
  // 支付宝的私钥MD5
  // public static String private_key = "mcm4op2s48amoqibnu3p5x2k1s6uu0qe";

  // 支付宝的公钥，无需修改该值
  public static String ali_public_key =
      "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

  public static String seller_email = "pindehao@5315.cn";

  // 服务器异步通知页面路径
  public static String notify_url = "http://pdh.choupinhui.net/getnotifyUrl.do";

  // 需http://格式的完整路径，不能加?id=123这类自定义参数

  // 页面跳转同步通知页面路径
  public static String return_url = "http://pdh.choupinhui.net/getreturnUrl.do";
  // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

  // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑


  // 调试用，创建TXT日志文件夹路径
  public static String log_path = "D:\\";

  // 字符编码格式 目前支持 gbk 或 utf-8
  public static String input_charset = "utf-8";

  // 签名方式 不需修改
  // public static String sign_type = "MD5";
  public static String sign_type = "RSA";

  // 调用的接口名，无需修改(退款时用到)
  public static String service = "refund_fastpay_by_platform_pwd";

  // 退款接口
  public static String refund_api = "https://openapi.alipay.com/gateway.do";

  // 退款日期 时间格式 yyyy-MM-dd HH:mm:ss
  public static String refund_date = UtilDate.getDateFormatter();

  // 服务器异步通知页面路径
  public static String batch_refund_notify_url =
      "http://pdh.choupinhui.net/aliPayBatchRefundNotify.do";

}
