package com.tencent.protocol.pay_protocol;

/**
 * User: rizenguo Date: 2014/10/22 Time: 16:42
 */

/**
 * 支付提交Post数据给到API之后，API会返回XML格式的数据，这个类用来装这些数据
 */
public class PayResData {
  // 协议层
  private String return_code = "";
  private String return_msg = "";

  public String getReturn_code() {
    return return_code;
  }

  public void setReturn_code(String return_code) {
    this.return_code = return_code;
  }

  public String getReturn_msg() {
    return return_msg;
  }

  public void setReturn_msg(String return_msg) {
    this.return_msg = return_msg;
  }
}
