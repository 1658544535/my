package com.tencent;

import com.tencent.business.DownloadBillBusiness;
import com.tencent.business.RefundBusiness;
import com.tencent.business.RefundQueryBusiness;
import com.tencent.business.ScanPayBusiness;
import com.tencent.common.Configure;
import com.tencent.protocol.downloadbill_protocol.DownloadBillReqData;
import com.tencent.protocol.pay_protocol.ScanPayReqData;
import com.tencent.protocol.pay_query_protocol.ScanPayQueryReqData;
import com.tencent.protocol.pre_pay_protocol.PrePayReqData;
import com.tencent.protocol.refund_protocol.RefundReqData;
import com.tencent.protocol.refund_query_protocol.RefundQueryReqData;
import com.tencent.protocol.reverse_protocol.ReverseReqData;
import com.tencent.service.DownloadBillService;
import com.tencent.service.PrePayService;
import com.tencent.service.RefundQueryService;
import com.tencent.service.RefundService;
import com.tencent.service.ReverseService;
import com.tencent.service.ScanPayQueryService;
import com.tencent.service.ScanPayService;

/**
 * SDK总入口
 */
public class WXPay {

  /*static {
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
    initSDKConfiguration(key, appID, mchID, sdbMchID, certLocalPath, certPassword);

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
    initJSConfiguration(jskey, jsappID, jsmchID, jssdbMchID, jscertLocalPath, jscertPassword);
    Util.log(">>> 初始化微信支付参数成功，APPID=" + appID);
  }*/

  /**
   * 初始化SDK依赖的几个关键配置
   * 
   * @param key 签名算法需要用到的秘钥
   * @param appID 公众账号ID
   * @param mchID 商户ID
   * @param sdbMchID 子商户ID，受理模式必填
   * @param certLocalPath HTTP证书在服务器中的路径，用来加载证书用
   * @param certPassword HTTP证书的密码，默认等于MCHID
   */
  public static void initSDKConfiguration(String key, String appID, String mchID, String sdbMchID,
      String certLocalPath, String certPassword) {
    Configure.setKey(key);
    Configure.setAppID(appID);
    Configure.setMchID(mchID);
    Configure.setSubMchID(sdbMchID);
    Configure.setCertLocalPath(certLocalPath);
    Configure.setCertPassword(certPassword);
  }

  /**
   * 初始化公众号支付依赖的几个关键配置
   * 
   * @param key 签名算法需要用到的秘钥
   * @param appID 公众账号ID
   * @param mchID 商户ID
   */
  public static void initJSConfiguration(String jskey, String jsappID, String jsmchID,
      String jssdbMchID, String jscertLocalPath, String jscertPassword) {
    Configure.setJskey(jskey);
    Configure.setJsappID(jsappID);
    Configure.setJsmchID(jsmchID);
    Configure.setJssubMchID(jssdbMchID);
    Configure.setJscertLocalPath(jscertLocalPath);
    Configure.setJscertPassword(jscertPassword);
  }

  /**
   * 预请求支付服务
   * 
   * @param prePayReqData 这个数据对象里面包含了API要求提交的各种数据字段
   * @return API返回的数据
   * @throws Exception
   */
  public static String requestPrePayService(PrePayReqData prePayReqData) throws Exception {
    if (Configure.JSAPI.equals(prePayReqData.getWxtrade())) {
      // 公众号支付
      return new PrePayService().jsrequest(prePayReqData);
    } else {
      return new PrePayService().request(prePayReqData);
    }
  }

  /**
   * 请求支付服务
   * 
   * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
   * @return API返回的数据
   * @throws Exception
   */
  public static String requestScanPayService(ScanPayReqData scanPayReqData) throws Exception {
    if (Configure.JSAPI.equals(scanPayReqData.getWxtrade())) {
      // 公众号支付
      return new ScanPayService().jsrequest(scanPayReqData);
    } else {
      return new ScanPayService().request(scanPayReqData);
    }
  }

  /**
   * 请求支付查询服务
   * 
   * @param scanPayQueryReqData 这个数据对象里面包含了API要求提交的各种数据字段
   * @return API返回的XML数据
   * @throws Exception
   */
  public static String requestScanPayQueryService(ScanPayQueryReqData scanPayQueryReqData)
      throws Exception {
    if (Configure.JSAPI.equals(scanPayQueryReqData.getWxtrade())) {
      // 公众号支付
      return new ScanPayQueryService().jsrequest(scanPayQueryReqData);
    } else {
      return new ScanPayQueryService().request(scanPayQueryReqData);
    }
  }


  /**
   * 请求退款服务
   * 
   * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
   * @return API返回的XML数据
   * @throws Exception
   */
  public static String requestRefundService(RefundReqData refundReqData) throws Exception {
    if (Configure.JSAPI.equals(refundReqData.getWxtrade())) {
      // 公众号支付
      return new RefundService().jsrequest(refundReqData);
    } else {
      return new RefundService().request(refundReqData);
    }
  }

  /**
   * 请求退款查询服务
   * 
   * @param refundQueryReqData 这个数据对象里面包含了API要求提交的各种数据字段
   * @return API返回的XML数据
   * @throws Exception
   */
  public static String requestRefundQueryService(RefundQueryReqData refundQueryReqData)
      throws Exception {
    if (Configure.JSAPI.equals(refundQueryReqData.getWxtrade())) {
      // 公众号支付
      return new RefundQueryService().jsrequest(refundQueryReqData);
    } else {
      return new RefundQueryService().request(refundQueryReqData);
    }
  }

  /**
   * 请求撤销服务
   * 
   * @param reverseReqData 这个数据对象里面包含了API要求提交的各种数据字段
   * @return API返回的XML数据
   * @throws Exception
   */
  public static String requestReverseService(ReverseReqData reverseReqData) throws Exception {
    if (Configure.JSAPI.equals(reverseReqData.getWxtrade())) {
      // 公众号支付
      return new ReverseService().jsrequest(reverseReqData);
    } else {
      return new ReverseService().request(reverseReqData);
    }
  }

  /**
   * 请求对账单下载服务
   * 
   * @param downloadBillReqData 这个数据对象里面包含了API要求提交的各种数据字段
   * @return API返回的XML数据
   * @throws Exception
   */
  public static String requestDownloadBillService(DownloadBillReqData downloadBillReqData)
      throws Exception {
    if (Configure.JSAPI.equals(downloadBillReqData.getWxtrade())) {
      // 公众号支付
      return new DownloadBillService().jsrequest(downloadBillReqData);
    } else {
      return new DownloadBillService().request(downloadBillReqData);
    }
  }

  /**
   * 直接执行被扫支付业务逻辑（包含最佳实践流程）
   * 
   * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
   * @param resultListener 商户需要自己监听被扫支付业务逻辑可能触发的各种分支事件，并做好合理的响应处理
   * @throws Exception
   */
  public static void doScanPayBusiness(ScanPayReqData scanPayReqData,
      ScanPayBusiness.ResultListener resultListener) throws Exception {
    new ScanPayBusiness().run(scanPayReqData, resultListener);
  }

  /**
   * 调用退款业务逻辑
   * 
   * @param refundReqData 这个数据对象里面包含了API要求提交的各种数据字段
   * @param resultListener 业务逻辑可能走到的结果分支，需要商户处理
   * @throws Exception
   */
  public static void doRefundBusiness(RefundReqData refundReqData,
      RefundBusiness.ResultListener resultListener) throws Exception {
    new RefundBusiness().run(refundReqData, resultListener);
  }

  /**
   * 运行退款查询的业务逻辑
   * 
   * @param refundQueryReqData 这个数据对象里面包含了API要求提交的各种数据字段
   * @param resultListener 商户需要自己监听被扫支付业务逻辑可能触发的各种分支事件，并做好合理的响应处理
   * @throws Exception
   */
  public static void doRefundQueryBusiness(RefundQueryReqData refundQueryReqData,
      RefundQueryBusiness.ResultListener resultListener) throws Exception {
    new RefundQueryBusiness().run(refundQueryReqData, resultListener);
  }

  /**
   * 请求对账单下载服务
   * 
   * @param downloadBillReqData 这个数据对象里面包含了API要求提交的各种数据字段
   * @param resultListener 商户需要自己监听被扫支付业务逻辑可能触发的各种分支事件，并做好合理的响应处理
   * @return API返回的XML数据
   * @throws Exception
   */
  public static void doDownloadBillBusiness(DownloadBillReqData downloadBillReqData,
      DownloadBillBusiness.ResultListener resultListener) throws Exception {
    new DownloadBillBusiness().run(downloadBillReqData, resultListener);
  }


}
