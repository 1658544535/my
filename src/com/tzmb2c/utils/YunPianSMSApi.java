package com.tzmb2c.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class YunPianSMSApi {
  // 查账户信息的http地址
  private static String URI_GET_USER_INFO = "http://yunpian.com/v1/user/get.json";

  // 通用发送接口的http地址
  private static String URI_SEND_SMS = "http://yunpian.com/v1/sms/send.json";

  // 模板发送接口的http地址
  private static String URI_TPL_SEND_SMS = "http://yunpian.com/v1/sms/tpl_send.json";

  // 发送语音验证码接口的http地址
  private static String URI_SEND_VOICE = "http://yunpian.com/v1/voice/send.json";

  // 编码格式。发送编码格式统一用UTF-8
  private static String ENCODING = "UTF-8";

  private static String APIKEY = "5b3745b33fd0875459e240cfb5b2e621";



  /*public static void main(String[] args) throws IOException, URISyntaxException {
    // 修改为您的apikey.apikey可在官网（http://www.yuanpian.com)登录后用户中心首页看到
    String apikey = "5929a891ae69fba2550bb2dc5017d32c";
    // 修改为您要发送的手机号
    String mobile = "xxxxxxxxxxx";

    *//**************** 查账户信息调用示例 *****************/
  /*
  System.out.println(YunPianSMSApi.getUserInfo(apikey));

  *//**************** 使用通用接口发短信(推荐) *****************/
  /*
  // 设置您要发送的内容(内容必须和某个模板匹配。以下例子匹配的是系统提供的1号模板）
  String text = "【云片网】您的验证码是1234";
  // 发短信调用示例
  System.out.println(YunPianSMSApi.sendSms(apikey, text, mobile));

  *//**************** 使用模板接口发短信(不推荐，建议使用通用接口) *****************/
  /*
  // 设置模板ID，如使用1号模板:【#company#】您的验证码是#code#
  long tpl_id = 1;
  // 设置对应的模板变量值
  // 如果变量名或者变量值中带有#&=%中的任意一个特殊符号，需要先分别进行urlencode编码
  // 如code值是#1234#,需作如下编码转换
  String codeValue = URLEncoder.encode("#1234#", ENCODING);
  String tpl_value = "#code#=" + codeValue + "&#company#=云片网";
  // 模板发送的调用示例
  System.out.println(YunPianSMSApi.tplSendSms(apikey, tpl_id, tpl_value, mobile));

  *//**************** 使用接口发语音验证码 *****************/
  /*
  String code = "1234";
  System.out.println(YunPianSMSApi.sendVoice(apikey, mobile, code));
  }*/

  /**
   * 发送短信.
   * 
   * @param text 内容
   * @param phone 手机号
   * @return
   */
  public static String sendSms(String phone, String text) {
    String rsp = "";
    try {
      rsp = YunPianSMSApi.sendSms(APIKEY, text, phone);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return rsp;
  }

  /**
   * 云片发送短信(非智能匹配).
   * 
   * @param phone 手机
   * @param param 参数map:模板参数：值 {"code":"77777","company":"拼得好"}
   * @param tpId 模板ID
   * 
   *        1179353 【拼得好】您申请了 拼得好 会员注册，验证码为：#code#，两分钟内有效。请在注册页面中输入验证码完成注册，淘竹马将竭诚为您服务。 1179355
   *        【拼得好】您申请了 拼得好 会员密码修改，验证码为：#code#，两分钟内有效。淘竹马将竭诚为您服务。 1179357
   *        【拼得好】您申请了绑定第三方账号，验证码为：#code#，两分钟内有效，淘竹马将竭诚为您服务。 1179361
   *        【拼得好】您的验证码为：#code#，两分钟内有效。淘竹马将竭诚为您服务。 1179365
   *        【拼得好】亲爱的客官，欢迎选择淘竹马，您的包裹已送出，请您注意查收。如有疑问请与客服MM联系，QQ咨询：400503677；全国热线：400-1503-677
   *        淘竹马将竭诚为您服务。
   * 
   * @return
   * @throws IOException
   */
  public static String sendSms(String phone, Map<String, String> param, long tpId) {
    int count = 0;
    String key = "";
    String value = "";
    String params = "";
    if (param != null) {
      try {
        for (Map.Entry<String, String> entry : param.entrySet()) {
          key = entry.getKey();
          value = entry.getValue();
          value = URLEncoder.encode(value, ENCODING);

          if (count > 0) {
            params += "&";
          }
          params += "#" + key + "#=" + value;
          count++;
        }
      } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
      }
    }
    String rsp = "";
    try {
      rsp = tplSendSms(APIKEY, tpId, params, phone);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return rsp;
  }

  /**
   * 验证码参数发送短信
   * 
   * @param phone
   * @param code
   * @param tpId
   * @return
   */
  public static String sendSms(String phone, String code, long tpId) {
    Map<String, String> param = new HashMap<String, String>();
    if (StringUtils.isNotBlank(code)) {
      param.put("code", code);
    } else {
      param.put("ncode", "0");
    }
    return sendSms(phone, param, tpId);
  }

  /**
   * 取账户信息
   * 
   * @return json格式字符串
   * @throws java.io.IOException
   */
  public static String getUserInfo(String apikey) throws IOException, URISyntaxException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("apikey", apikey);
    return post(URI_GET_USER_INFO, params);
  }

  /**
   * 通用接口发短信
   * 
   * @param apikey apikey
   * @param text 　短信内容
   * @param mobile 　接受的手机号
   * @return json格式字符串
   * @throws IOException
   */
  public static String sendSms(String apikey, String text, String mobile) throws IOException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("apikey", apikey);
    params.put("text", text);
    params.put("mobile", mobile);
    return post(URI_SEND_SMS, params);
  }

  /**
   * 通过模板发送短信(不推荐)
   * 
   * @param apikey apikey
   * @param tpl_id 　模板id
   * @param tpl_value 　模板变量值
   * @param mobile 　接受的手机号
   * @return json格式字符串
   * @throws IOException
   */
  public static String tplSendSms(String apikey, long tpl_id, String tpl_value, String mobile)
      throws IOException {
    Map<String, String> params = new HashMap<String, String>();
    params.put("apikey", apikey);
    params.put("tpl_id", String.valueOf(tpl_id));
    params.put("tpl_value", tpl_value);
    params.put("mobile", mobile);
    return post(URI_TPL_SEND_SMS, params);
  }

  /**
   * 通过接口发送语音验证码
   * 
   * @param apikey apikey
   * @param mobile 接收的手机号
   * @param code 验证码
   * @return
   */
  public static String sendVoice(String apikey, String mobile, String code) {
    Map<String, String> params = new HashMap<String, String>();
    params.put("apikey", apikey);
    params.put("mobile", mobile);
    params.put("code", code);
    return post(URI_SEND_VOICE, params);
  }

  /**
   * 基于HttpClient 4.3的通用POST方法
   * 
   * @param url 提交的URL
   * @param paramsMap 提交<参数，值>Map
   * @return 提交响应
   */
  public static String post(String url, Map<String, String> paramsMap) {
    CloseableHttpClient client = HttpClients.createDefault();
    String responseText = "";
    CloseableHttpResponse response = null;
    try {
      HttpPost method = new HttpPost(url);
      if (paramsMap != null) {
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> param : paramsMap.entrySet()) {
          NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
          paramList.add(pair);
        }
        method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
      }
      response = client.execute(method);
      HttpEntity entity = response.getEntity();
      if (entity != null) {
        responseText = EntityUtils.toString(entity);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (response != null) {
          response.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return responseText;
  }
}
