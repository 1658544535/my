package com.tzmb2c.web.push;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.AgencyPushPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.push.android.AndroidListcast;
import com.tzmb2c.web.push.android.AndroidUnicast;
import com.tzmb2c.web.push.ios.IOSListcast;
import com.tzmb2c.web.push.ios.IOSUnicast;
import com.tzmb2c.web.service.AgencyPushService;
import com.tzmb2c.web.service.AgencyService;
import com.tzmb2c.web.service.OrderService;

public class UmengPushAction extends SuperAction {

  @Autowired
  private OrderService orderService;
  @Autowired
  private AgencyService agencyService;
  @Autowired
  private AgencyPushService agencyPushService;

  private AgencyPojo agencyPojo;
  private Long agencyId;// 批发商id
  private OrderPojo order;
  private Long orderId;// 订单id
  private String result;
  private String[] tids;
  private String[] channelIds;// 设备id

  private String appkey;
  private String appMasterSecret;
  private String timestamp;

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public OrderPojo getOrder() {
    return order;
  }

  public void setOrder(OrderPojo order) {
    this.order = order;
  }

  public AgencyPojo getAgencyPojo() {
    return agencyPojo;
  }

  public void setAgencyPojo(AgencyPojo agencyPojo) {
    this.agencyPojo = agencyPojo;
  }


  public String agencyAllListForPush() {
    orderId = order.getId();
    OrderPojo orderData = getOrderDataByOrderId(orderId);
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("shopId", orderData.getShopId());
    map.put("city", orderData.getCityId());
    List<AgencyPojo> list;
    try {
      list = agencyService.agencyAllListForPush(map);
      JSONArray json = JSONArray.fromObject(list);
      page.setRowCount(list.size());
      page.setResult(json.toString());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String agencyCountForPush() throws SQLException {
    orderId = order.getId();
    OrderPojo orderData = getOrderDataByOrderId(orderId);
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("shopId", orderData.getShopId());
    map.put("city", orderData.getCityId());
    int i;
    try {
      i = agencyService.agencyCountForPush(map);
      page.setRowCount(i);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  // 直接推送
  public String updatePushName() {
    orderId = order.getId();
    try {
      agencyService.updatePushName(orderId);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    FileUtil.alertMessageBySkip("直发成功！", "order.do?os=2");

    return null;
  }

  // PUSH订单:批发商单个推送
  public String startPushForSingleAgency() {
    orderId = order.getId();
    agencyId = agencyPojo.getAgencyId();
    AgencyPojo agencyPojo6 = getAgencyPojoDataByAgencyId(agencyId);
    String DeviceTokens = agencyPojo6.getBaiduUid();
    System.out.println("<============orderId=" + orderId + "=========>");
    System.out.println("<============agencyId=" + agencyId + "=========>");
    OrderPojo orderData = getOrderDataByOrderId(orderId);
    try {
      String ret = sendIOSUnicast(orderData, DeviceTokens);// ios 推送
      String ret0 = sendAndroidUnicast(orderData, DeviceTokens);// Android 推送
      if (ret == "1" || ret0 == "1") {
        // 保存推送记录，将你想要保存的内容装进Map
        AgencyPushPojo agencyPush = new AgencyPushPojo();
        agencyPush.setOrderId(orderId);
        agencyPush.setAgencyId(agencyId);
        AgencyPushPojo agencyPushdetail = agencyPushService.findAgencyPush(agencyPush);
        if (agencyPushdetail == null) {
          Map<String, Object> map = new HashMap<String, Object>();
          map.put("orderId", orderId);
          map.put("agencyId", agencyId);
          map.put("userId", agencyPojo6.getUserId());
          saveAgencyPushRecord(map);
        }
        this.result = "1";
      } else {
        // 保存推送记录，将你想要保存的内容装进Map
        AgencyPushPojo agencyPush = new AgencyPushPojo();
        agencyPush.setOrderId(orderId);
        agencyPush.setAgencyId(agencyId);
        AgencyPushPojo agencyPushdetail = agencyPushService.findAgencyPush(agencyPush);
        if (agencyPushdetail == null) {
          Map<String, Object> map = new HashMap<String, Object>();
          map.put("orderId", orderId);
          map.put("agencyId", agencyId);
          map.put("userId", agencyPojo6.getUserId());
          saveAgencyPushRecord(map);
        }
        this.result = "0";
      }

    } catch (Exception ex) {
      ex.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  // PUSH订单:批发商组播推送
  public String startPushForTag() {
    channelIds = new String[] {};
    for (String tid : tids) {

      AgencyPojo agencyPojo3 = getAgencyPojoDataByAgencyId(Long.valueOf(tid));
      channelIds = insert(channelIds, agencyPojo3.getBaiduUid());
    }
    System.out.println(channelIds.length + "+++++++++++++++++++++++++++++++++++++/n");
    try {

      String ret = sendIOSListcast(channelIds);// ios 推送
      String ret0 = sendAndroidListcast(channelIds);// Android 推送
      if (ret == "1" || ret0 == "1") {
        for (String tid : tids) {
          AgencyPojo agencyPojo4 = getAgencyPojoDataByAgencyId(Long.valueOf(tid));

          AgencyPushPojo agencyPush = new AgencyPushPojo();
          agencyPush.setOrderId(order.getId());
          agencyPush.setAgencyId(agencyPojo4.getAgencyId());
          AgencyPushPojo agencyPushdetail = agencyPushService.findAgencyPush(agencyPush);
          if (agencyPushdetail == null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("orderId", order.getId());
            map.put("agencyId", agencyPojo4.getAgencyId());
            map.put("userId", agencyPojo4.getUserId());
            saveAgencyPushRecord(map);
          }

        }
        FileUtil.alertMessageBySkip("推送成功！", "pushOrderManage.do?order.id=" + order.getId());
      } else {
        FileUtil.alertMessageBySkip("推送失败！", "pushOrderManage.do?order.id=" + order.getId());
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      FileUtil.alertMessageBySkip("推送失败！", "pushOrderManage.do?order.id=" + order.getId());
    }
    return null;
    // return SUCCESS;
  }


  // IOS 单播测试
  public String sendIOSUnicast(OrderPojo order, String DeviceToken) throws Exception {

    String apiKey = Constant.apiKey_uMios;
    String secretKey = Constant.secretKey_uMios;
    String timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));

    IOSUnicast unicast = new IOSUnicast();
    unicast.setAppMasterSecret(secretKey);
    unicast.setPredefinedKeyValue("appkey", apiKey);
    unicast.setPredefinedKeyValue("timestamp", timestamp);
    // TODO Set your device token
    unicast.setPredefinedKeyValue("device_tokens", DeviceToken);
    unicast.setPredefinedKeyValue("alert", "您有一条待接收订单，请注意查收.");
    unicast.setPredefinedKeyValue("badge", 0);
    unicast.setPredefinedKeyValue("sound", "chime");
    // TODO set 'production_mode' to 'true' if your app is under production mode
    unicast.setPredefinedKeyValue("production_mode", "true");
    // Set customized fields
    unicast.setCustomizedField("oid", String.valueOf(order.getId()));
    // unicast.send();
    if (unicast.send()) {
      return "1";
    } else {
      return "0";
    }
  }

  // IOS 列播测试
  public String sendIOSListcast(String[] DeviceTokens) throws Exception {
    String strDeviceToken = "";
    int i = 0;
    for (String DeviceToken : DeviceTokens) {
      i++;
      if (i == 1) {
        strDeviceToken = DeviceToken;
      } else if (i <= DeviceTokens.length) {
        strDeviceToken = strDeviceToken + "," + DeviceToken;
      }
    }
    OrderPojo orderContentIOS = getOrderDataByOrderId(order.getId());

    String apiKey = Constant.apiKey_uMios;
    String secretKey = Constant.secretKey_uMios;
    String timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));

    IOSListcast listcast = new IOSListcast();
    listcast.setAppMasterSecret(secretKey);
    listcast.setPredefinedKeyValue("appkey", apiKey);
    listcast.setPredefinedKeyValue("timestamp", timestamp);
    // TODO Set your device token
    listcast.setPredefinedKeyValue("device_tokens", strDeviceToken);
    listcast.setPredefinedKeyValue("alert", "您有一条待接收订单，请注意查收.");
    listcast.setPredefinedKeyValue("badge", 0);
    listcast.setPredefinedKeyValue("sound", "chime");
    // TODO set 'production_mode' to 'true' if your app is under production mode
    listcast.setPredefinedKeyValue("production_mode", "true");
    // Set customized fields
    listcast.setCustomizedField("oid", String.valueOf(orderContentIOS.getId()));
    // listcast.send();
    if (listcast.send()) {
      return "1";
    } else {
      return "0";
    }
  }

  /* Android 单播 */
  public String sendAndroidUnicast(OrderPojo order, String DeviceToken) throws Exception {

    String apiKey = Constant.apiKey_uMandroid;
    String secretKey = Constant.secretKey_uMandroid;
    String timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));



    AndroidUnicast unicast = new AndroidUnicast();
    unicast.setAppMasterSecret(secretKey);
    unicast.setPredefinedKeyValue("appkey", apiKey);
    unicast.setPredefinedKeyValue("timestamp", timestamp);
    // TODO Set your device token
    unicast.setPredefinedKeyValue("device_tokens", DeviceToken);
    unicast.setPredefinedKeyValue("ticker", "赶紧点击");
    unicast.setPredefinedKeyValue("title", "陶竹马订单");
    unicast.setPredefinedKeyValue("text", "您有一条待接收订单，请注意查收.");
    unicast.setPredefinedKeyValue("after_open", "go_app");
    unicast.setPredefinedKeyValue("display_type", "notification");
    // TODO Set 'production_mode' to 'false' if it's a test device.
    // For how to register a test device, please see the developer doc.
    unicast.setPredefinedKeyValue("production_mode", "true");
    // Set customized fields
    // unicast.setExtraField("test", "helloworld");
    unicast.setExtraField("oid", String.valueOf(order.getId()));
    // unicast.send();
    if (unicast.send()) {
      return "1";
    } else {
      return "0";
    }
  }


  /* Android 列播 */
  public String sendAndroidListcast(String[] DeviceTokens) throws Exception {


    String strDeviceToken = "";
    int i = 0;
    for (String DeviceToken : DeviceTokens) {
      i++;
      if (i == 1) {
        strDeviceToken = DeviceToken;
      } else if (i < DeviceTokens.length) {
        strDeviceToken = strDeviceToken + "," + DeviceToken;
      }
    }
    getOrderDataByOrderId(order.getId());

    String apiKey = Constant.apiKey_uMandroid;
    String secretKey = Constant.secretKey_uMandroid;
    String timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));



    AndroidListcast listcast = new AndroidListcast();
    listcast.setAppMasterSecret(secretKey);
    listcast.setPredefinedKeyValue("appkey", apiKey);
    listcast.setPredefinedKeyValue("timestamp", timestamp);
    // TODO Set your device token
    listcast.setPredefinedKeyValue("device_tokens", strDeviceToken);
    listcast.setPredefinedKeyValue("ticker", "赶紧点击");
    listcast.setPredefinedKeyValue("title", "陶竹马订单");
    listcast.setPredefinedKeyValue("text", "您有一条待接收订单，请注意查收.");
    listcast.setPredefinedKeyValue("after_open", "go_app");
    listcast.setPredefinedKeyValue("display_type", "notification");
    // TODO Set 'production_mode' to 'false' if it's a test device.
    // For how to register a test device, please see the developer doc.
    listcast.setPredefinedKeyValue("production_mode", "true");
    // Set customized fields
    // unicast.setExtraField("test", "helloworld");
    listcast.setExtraField("oid", String.valueOf(order.getId()));
    // unicast.send();
    if (listcast.send()) {
      return "1";
    } else {
      return "0";
    }
  }

  /***
   * 根据订单id返回数据
   * 
   * @param orderId 订单id
   * @return 订单详情数据
   */
  public OrderPojo getOrderDataByOrderId(Long orderId) {
    OrderPojo order1 = null;
    try {
      order1 = orderService.getfindByIdOrderToPush(orderId);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return order1;
  }

  /***
   * 根据订单号返回订单数据
   * 
   * @param orderNO 订单号
   * @return 订单详情数据
   */
  public OrderPojo getOrderDataByOrderNO(String orderNO) {
    OrderPojo order2 = null;
    order2 = orderService.findOrderByOrderNo(orderNO);
    return order2;
  }

  /***
   * 根据批发商id返回批发商信息
   * 
   * @param agencyId
   * @return 批发商信息
   */
  public AgencyPojo getAgencyPojoDataByAgencyId(Long agencyId) {
    AgencyPojo agencyPojo1 = null;
    try {
      agencyPojo1 = agencyService.getfindByAgencyId(agencyId);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return agencyPojo1;
  }

  /**
   * 保存推送记录
   * 
   * @param map
   */
  public void saveAgencyPushRecord(Map<String, Object> map) {
    Long orderId = (Long) map.get("orderId");
    Long agencyId = (Long) map.get("agencyId");
    Long userId = (Long) map.get("userId");
    AgencyPushPojo agencyPushPojo = new AgencyPushPojo();
    agencyPushPojo.setStatus(0);
    agencyPushPojo.setType(0);
    agencyPushPojo.setOrderId(orderId);
    agencyPushPojo.setAgencyId(agencyId);
    agencyPushPojo.setUserId(userId);
    agencyPushPojo.setCreateDate(new Date());
    try {
      agencyPushService.insertAgencyPushRecord(agencyPushPojo);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * 字符串数组动态添加元素
   * 
   * @param arr
   * @param str
   * @return
   */
  private static String[] insert(String[] arr, String str) {
    int size = arr.length;
    String[] tmp = new String[size + 1];
    System.arraycopy(arr, 0, tmp, 0, size);
    tmp[size] = str;
    return tmp;
  }

  public String getAppkey() {
    return appkey;
  }

  public void setAppkey(String appkey) {
    this.appkey = appkey;
  }

  public String getAppMasterSecret() {
    return appMasterSecret;
  }

  public void setAppMasterSecret(String appMasterSecret) {
    this.appMasterSecret = appMasterSecret;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }



}
