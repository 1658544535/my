package com.tencent.service;

import com.tencent.common.Configure;
import com.tencent.protocol.pre_pay_protocol.PrePayReqData;

/**
 * User: rizenguo Date: 2014/10/29 Time: 16:03
 */
public class PrePayService extends BaseService {

  public PrePayService() throws IllegalAccessException, InstantiationException,
      ClassNotFoundException {
    super(Configure.PRE_PAY_API);
  }

  /**
   * 请求支付服务
   * 
   * @param prePayReqData 这个数据对象里面包含了API要求提交的各种数据字段
   * @return API返回的数据
   * @throws Exception
   */
  public String request(PrePayReqData prePayReqData) throws Exception {

    // --------------------------------------------------------------------
    // 发送HTTPS的Post请求到API地址
    // --------------------------------------------------------------------
    String responseString = sendPost(prePayReqData);

    return responseString;
  }

  /**
   * 公众号支付 请求支付服务
   * 
   * @param prePayReqData 这个数据对象里面包含了API要求提交的各种数据字段
   * @return API返回的数据
   * @throws Exception
   */
  public String jsrequest(PrePayReqData prePayReqData) throws Exception {

    // --------------------------------------------------------------------
    // 发送HTTPS的Post请求到API地址
    // --------------------------------------------------------------------
    String responseString = sendJSPost(prePayReqData);

    return responseString;
  }
}
