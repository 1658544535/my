package com.tzmb2c.web.push;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.AddDevicesToTagRequest;
import com.baidu.yun.push.model.AddDevicesToTagResponse;
import com.baidu.yun.push.model.CreateTagRequest;
import com.baidu.yun.push.model.CreateTagResponse;
import com.baidu.yun.push.model.DeleteDevicesFromTagRequest;
import com.baidu.yun.push.model.DeleteDevicesFromTagResponse;
import com.baidu.yun.push.model.DeviceInfo;
import com.baidu.yun.push.model.PushBatchUniMsgRequest;
import com.baidu.yun.push.model.PushBatchUniMsgResponse;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.baidu.yun.push.model.PushMsgToTagRequest;
import com.baidu.yun.push.model.PushMsgToTagResponse;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.AgencyPushPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.service.AgencyPushService;
import com.tzmb2c.web.service.AgencyService;
import com.tzmb2c.web.service.OrderService;

public class PushAction extends SuperAction {

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
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("userIdAgency", orderData.getShopId());
    map.put("city", orderData.getCityId());
    List<AgencyPojo> list;
    try {
      list = agencyService.agencyAllListForPush(map);
      JSONArray json = JSONArray.fromObject(list);
      page.setRowCount(agencyService.agencyCount(map));
      page.setResult(json.toString());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String agencyCountForPush() {
    orderId = order.getId();
    OrderPojo orderData = getOrderDataByOrderId(orderId);
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("userIdAgency", orderData.getAgencyUserId());
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
    String chanelID = agencyPojo6.getBaiduUid();
    System.out.println("<============orderId=" + orderId + "=========>");
    System.out.println("<============agencyId=" + agencyId + "=========>");
    OrderPojo orderData = getOrderDataByOrderId(orderId);
    try {
      pushMsgToSigleAndroidDevice(orderData, chanelID);// PUSH TO Android
      pushMsgToSigleIOSDevice(orderData, chanelID);// PUSH TO IOS
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
    } catch (PushServerException e) {
      e.printStackTrace();
      this.result = "0";
    } catch (PushClientException e) {
      e.printStackTrace();
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

      // CreateTagForPushAndroid(channelIds);
      CreateTagForPushIOS(channelIds);
      // PushBatchUniMsgIos(channelIds);//苹果批量单播无效
      PushBatchUniMsgAndroid(channelIds);
      // DeleteDevicesFromTag(channelIds);
      // for (String channelId : channelIds) {
      // OrderPojo orderPojo = new OrderPojo();
      // orderPojo.setId(order.getId());
      // pushMsgToSigleIOSDevice(orderPojo, channelId);//PUSH TO IOS
      // }
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
    } catch (PushServerException | PushClientException e) {
      FileUtil.alertMessageBySkip("推送失败！", "pushOrderManage.do?order.id=" + order.getId());
      e.printStackTrace();
    }
    return null;
    // return SUCCESS;
  }

  // PUSH订单:单台安卓设备
  public void pushMsgToSigleAndroidDevice(OrderPojo order, String chanelID)
      throws PushServerException, PushClientException {
    String apiKey = Constant.apiKey_android;
    String secretKey = Constant.secretKey_android;
    PushKeyPair pair = new PushKeyPair(apiKey, secretKey);
    BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);
    pushClient.setChannelLogHandler(new YunLogHandler() {
      @Override
      public void onHandle(YunLogEvent event) {
        System.out.println(event.getMessage());
      }
    });
    try {
      // 创建 Android的通知
      JSONObject notification = new JSONObject();
      notification.put("title", "陶竹马B2C订单");// title：通知标题，可以为空；如果为空则设为appid对应的应用名;
      notification.put("description", "您有一条待接收订单，请注意查收.");// description：通知文本内容，不能为空;
      notification.put("notification_builder_id", 0);// android客户端自定义通知样式，如果没有设置默认为0;
      notification.put("notification_basic_style", 4);// 只有notification_builder_id为0时有效，可以设置通知的基本样式包括(响铃：0x04;振动：0x02;可清除：0x01;),这是一个flag整形，每一位代表一种样式;
      notification.put("open_type", 3);// 点击通知后的行为(1：打开Url; 2：自定义行为；3：默认打开应用;);
      JSONObject jsonCustormCont = new JSONObject();
      // jsonCustormCont.put("key", "value"); 自定义内容，key-value
      jsonCustormCont.put("oid", order.getId()); // 订单ID
      // jsonCustormCont.put("收货人", order.getConsignee()); // 收货人
      notification.put("custom_content", jsonCustormCont);// 自定义内容，键值对，Json对象形式(可选)；在android客户端，这些键值对将以Intent中的extra进行传递。

      PushMsgToSingleDeviceRequest request =
          new PushMsgToSingleDeviceRequest().addChannelId(chanelID)// 唯一对应一台设备
              .addMsgExpires(new Integer(3600)) // message有效时间
              .addMessageType(1)// 1：通知,0:透传消息. 默认为0 注：IOS只有通知.
              .addMessage(notification.toString()).addDeviceType(3);// deviceType=>3:android,4:ios
      PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);
      LogUtils.printResponse_PushMsgToSingleDeviceResponse(response);
    } catch (PushClientException e) {
      if (BaiduPushConstants.ERROROPTTYPE) {
        throw e;
      } else {
        e.printStackTrace();
      }
    } catch (PushServerException e) {
      if (BaiduPushConstants.ERROROPTTYPE) {
        throw e;
      } else {
        System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s",
            e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
      }
    }
  }

  // PUSH订单:单台IOS设备
  public void pushMsgToSigleIOSDevice(OrderPojo order, String chanelID) throws PushClientException,
      PushServerException {
    // 1. get apiKey and secretKey from developer console "fpXXch7g53FGYSxNFiiTpvz1"
    // "9taQLixWgxSX27lyPLuycVpeh35Noyx8"

    String apiKey = Constant.apiKey_ios;
    String secretKey = Constant.secretKey_ios;
    PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

    // 2. build a BaidupushClient object to access released interfaces
    BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

    // 3. register a YunLogHandler to get detail interacting information
    // in this request.
    pushClient.setChannelLogHandler(new YunLogHandler() {
      @Override
      public void onHandle(YunLogEvent event) {
        System.out.println(event.getMessage());
      }
    });

    try {
      System.out.println("IOSPUSH++++++++++++++++++++++");
      // 4. specify request arguments
      // make IOS Notification
      JSONObject notification = new JSONObject();
      JSONObject jsonAPS = new JSONObject();
      jsonAPS.put("alert", "您有一条待接收订单，请注意查收");
      jsonAPS.put("sound", "ttt"); // 设置通知铃声样式，例如"ttt"，用户自定义。
      notification.put("aps", jsonAPS);
      notification.put("oid", "value1");
      notification.put("key2", "value2");

      PushMsgToSingleDeviceRequest request =
          new PushMsgToSingleDeviceRequest().addChannelId(chanelID)
              .addMsgExpires(new Integer(3600)) // 设置message的有效时间
              .addMessageType(1)// 1：通知,0:透传消息.默认为0 注：IOS只有通知.
              .addMessage(notification.toString()).addDeployStatus(1) // IOS,
              // DeployStatus
              // => 1: Developer
              // 2: Production.
              .addDeviceType(4);// deviceType => 3:android, 4:ios
      // 5. http request
      PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);
      // Http请求结果解析打印
      System.out.println("IOSmsgId: " + response.getMsgId() + ",sendTime: "
          + response.getSendTime());

    } catch (PushClientException e) {
      /*
       * ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,'true' 表示抛出, 'false' 表示捕获。
       */
      if (BaiduPushConstants.ERROROPTTYPE) {
        throw e;
      } else {
        e.printStackTrace();
      }
    } catch (PushServerException e) {
      if (BaiduPushConstants.ERROROPTTYPE) {
        throw e;
      } else {
        System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s",
            e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        // if(e.getErrorCode()==40004){
        // pushMsgToSigleIOSDevice(order, chanelID);//PUSH TO IOS
        // }

      }
    }


  }

  /**
   * PUSH：安卓的组播推送
   * 
   * @return
   * @throws PushServerException
   * @throws PushClientException
   */
  public void CreateTagForPushAndroid(String[] channelIds) throws PushServerException,
      PushClientException {
    String tagName = null;
    String apiKey = Constant.apiKey_android;
    String secretKey = Constant.secretKey_android;
    PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

    BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

    pushClient.setChannelLogHandler(new YunLogHandler() {
      @Override
      public void onHandle(YunLogEvent event) {
        System.out.println(event.getMessage());
      }
    });

    getOrderDataByOrderId(order.getId());
    JSONObject notification = new JSONObject();
    notification.put("title", "陶竹马B2C订单");// title：通知标题，可以为空；如果为空则设为appid对应的应用名;
    notification.put("description", "您有一条待接收订单，请注意查收.");// description：通知文本内容，不能为空;
    notification.put("notification_builder_id", 0);// android客户端自定义通知样式，如果没有设置默认为0;
    notification.put("notification_basic_style", 4);// 只有notification_builder_id为0时有效，可以设置通知的基本样式包括(响铃：0x04;振动：0x02;可清除：0x01;),这是一个flag整形，每一位代表一种样式;
    notification.put("open_type", 3);// 点击通知后的行为(1：打开Url; 2：自定义行为；3：默认打开应用;);
    JSONObject jsonCustormCont = new JSONObject();
    // jsonCustormCont.put("key", "value"); 自定义内容，key-value
    jsonCustormCont.put("oid", order.getId()); // 订单ID
    notification.put("custom_content", jsonCustormCont);// 自定义内容，键值对，Json对象形式(可选)；在android客户端，这些键值对将以Intent中的extra进行传递。

    try {
      // 创建标签
      CreateTagRequest createTagRequest = new CreateTagRequest().addTagName("agencyAndroid");
      CreateTagResponse createTagResponse = pushClient.createTag(createTagRequest);
      tagName = createTagResponse.getTagName();// 获取标签名
      LogUtils.printResponse_CreateTagResponse(createTagResponse);
      // 添加设备到标签
      AddDevicesToTagRequest addDevicesToTagRequest =
          new AddDevicesToTagRequest().addTagName(tagName).addChannelIds(channelIds)
              .addDeviceType(3);
      AddDevicesToTagResponse addDevicesToTagResponse =
          pushClient.addDevicesToTag(addDevicesToTagRequest);
      LogUtils.printResponse_AddDevicesToTag(addDevicesToTagResponse);
      // 组播推送
      PushMsgToTagRequest pushMsgToTagRequest = new PushMsgToTagRequest().addTagName(tagName)// 设置标签名
          .addMsgExpires(new Integer(3600))// 消息过期时间，单位为秒
          .addMessageType(1)// 消息类型:取值如下：0：透传消息， 1：通知；默认值为0
          // .addMessage("Hello Baidu push")//内容类型是透传的为字符串格式数据
          .addMessage(notification.toString())// 内容类型是通知的为json格式数据
          .addDeviceType(3);// 设备类型:3：Android，4：IOS
      PushMsgToTagResponse pushMsgToTagResponse = pushClient.pushMsgToTag(pushMsgToTagRequest);
      LogUtils.printResponse_PushMsgToTagResponse(pushMsgToTagResponse);
    } catch (PushClientException e) {
      if (BaiduPushConstants.ERROROPTTYPE) {
        throw e;
      } else {
        e.printStackTrace();
      }
    } catch (PushServerException e) {
      if (BaiduPushConstants.ERROROPTTYPE) {
        throw e;
      } else {
        System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s",
            e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
      }
    }
  }

  /**
   * PUSH：苹果的组播推送
   * 
   * @return
   * @throws PushServerException
   * @throws PushClientException
   */
  public void CreateTagForPushIOS(String[] channelIds) throws PushServerException,
      PushClientException {
    String tagName = null;
    String apiKey = Constant.apiKey_ios;
    String secretKey = Constant.secretKey_ios;
    PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

    BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

    pushClient.setChannelLogHandler(new YunLogHandler() {
      @Override
      public void onHandle(YunLogEvent event) {
        System.out.println(event.getMessage());
      }
    });

    getOrderDataByOrderId(order.getId());
    JSONObject notification = new JSONObject();
    JSONObject jsonAPS = new JSONObject();
    jsonAPS.put("alert", "您有一条待接收订单，请注意查收");
    // jsonAPS.put("sound", "ttt"); // 设置通知铃声样式，例如"ttt"，用户自定义。
    notification.put("aps", jsonAPS);
    notification.put("oid", order.getId());// 订单ID

    try {

      // 创建标签
      CreateTagRequest createTagRequest = new CreateTagRequest().addTagName("agencyIOS");
      CreateTagResponse createTagResponse = pushClient.createTag(createTagRequest);
      tagName = createTagResponse.getTagName();// 获取标签名
      LogUtils.printResponse_CreateTagResponse(createTagResponse);
      // 添加设备到标签
      AddDevicesToTagRequest addDevicesToTagRequest =
          new AddDevicesToTagRequest().addTagName(tagName).addChannelIds(channelIds)
              .addDeviceType(4);
      AddDevicesToTagResponse addDevicesToTagResponse =
          pushClient.addDevicesToTag(addDevicesToTagRequest);
      LogUtils.printResponse_AddDevicesToTag(addDevicesToTagResponse);
      // 组播推送
      PushMsgToTagRequest pushMsgToTagRequest = new PushMsgToTagRequest().addTagName(tagName)// 设置标签名
          .addMsgExpires(new Integer(3600))// 消息过期时间，单位为秒
          .addMessageType(1)// 消息类型:取值如下：0：透传消息， 1：通知；默认值为0
          .addDeployStatus(1)// IOS,DeployStatus => 1: Developer, 2: Production.
          // .addMessage("Hello Baidu push")//推送透传类型的内容，格式为字符串
          .addMessage(notification.toString())// 推送内容为通知，格式是json
          .addDeviceType(4);// 设备类型:3：Android，4：IOS
      PushMsgToTagResponse pushMsgToTagResponse = pushClient.pushMsgToTag(pushMsgToTagRequest);
      LogUtils.printResponse_PushMsgToTagResponse(pushMsgToTagResponse);
      // DeleteDevicesFromTag(channelIds);

    } catch (PushClientException e) {
      if (BaiduPushConstants.ERROROPTTYPE) {
        throw e;
      } else {
        e.printStackTrace();
      }
    } catch (PushServerException e) {
      if (BaiduPushConstants.ERROROPTTYPE) {
        throw e;
      } else {
        System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s",
            e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
      }
    }
  }

  /**
   * PUSH：安卓的推送消息给批量设备（批量单播）。
   * 
   * @return
   * @throws PushServerException
   * @throws PushClientException
   */
  public void PushBatchUniMsgAndroid(String[] channelIds) throws PushServerException,
      PushClientException {
    String apiKey = Constant.apiKey_android;
    String secretKey = Constant.secretKey_android;
    PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

    BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

    pushClient.setChannelLogHandler(new YunLogHandler() {
      @Override
      public void onHandle(YunLogEvent event) {
        System.out.println(event.getMessage());
      }
    });

    getOrderDataByOrderId(order.getId());
    JSONObject notification = new JSONObject();
    notification.put("title", "陶竹马B2C订单");// title：通知标题，可以为空；如果为空则设为appid对应的应用名;
    notification.put("description", "您有一条待接收订单，请注意查收.");// description：通知文本内容，不能为空;
    notification.put("notification_builder_id", 0);// android客户端自定义通知样式，如果没有设置默认为0;
    notification.put("notification_basic_style", 4);// 只有notification_builder_id为0时有效，可以设置通知的基本样式包括(响铃：0x04;振动：0x02;可清除：0x01;),这是一个flag整形，每一位代表一种样式;
    notification.put("open_type", 3);// 点击通知后的行为(1：打开Url; 2：自定义行为；3：默认打开应用;);
    JSONObject jsonCustormCont = new JSONObject();
    // jsonCustormCont.put("key", "value"); 自定义内容，key-value
    jsonCustormCont.put("oid", order.getId()); // 订单ID
    notification.put("custom_content", jsonCustormCont);// 自定义内容，键值对，Json对象形式(可选)；在android客户端，这些键值对将以Intent中的extra进行传递。

    try {
      // 4. specify request arguments

      PushBatchUniMsgRequest request =
          new PushBatchUniMsgRequest().addChannelIds(channelIds).addMsgExpires(new Integer(3600))// 消息过期时间，单位为秒
              .addMessageType(1)// 消息类型:取值如下：0：透传消息， 1：通知；默认值为0
              .addMessage(notification.toString())// 内容类型是通知的为json格式数据
              .addDeviceType(3).addTopicId("BaiduPush");// 设置类别主题
      // 5. http request
      PushBatchUniMsgResponse response = pushClient.pushBatchUniMsg(request);
      // Http请求返回值解析
      System.out.println(String.format("msgId: %s, sendTime: %d", response.getMsgId(),
          response.getSendTime()));
    } catch (PushClientException e) {
      if (BaiduPushConstants.ERROROPTTYPE) {
        throw e;
      } else {
        e.printStackTrace();
      }
    } catch (PushServerException e) {
      if (BaiduPushConstants.ERROROPTTYPE) {
        throw e;
      } else {
        System.out.println(String.format("requestId: %d, errorCode: %d, errorMsg: %s",
            e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
      }
    }
  }

  /**
   * PUSH：ios推送消息给批量设备（批量单播）。
   * 
   * @return
   * @throws PushServerException
   * @throws PushClientException
   */
  public void PushBatchUniMsgIos(String[] channelIds) throws PushServerException,
      PushClientException {
    String apiKey = Constant.apiKey_ios;
    String secretKey = Constant.secretKey_ios;
    PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

    BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

    pushClient.setChannelLogHandler(new YunLogHandler() {
      @Override
      public void onHandle(YunLogEvent event) {
        System.out.println(event.getMessage());
      }
    });

    getOrderDataByOrderId(order.getId());
    JSONObject notification = new JSONObject();
    JSONObject jsonAPS = new JSONObject();
    jsonAPS.put("alert", "您有一条待接收订单，请注意查收.");
    // jsonAPS.put("sound", "ttt"); // 设置通知铃声样式，例如"ttt"，用户自定义。
    notification.put("aps", jsonAPS);
    notification.put("oid", order.getId());// 订单ID
    try {
      // 4. specify request arguments

      PushBatchUniMsgRequest request =
          new PushBatchUniMsgRequest().addChannelIds(channelIds).addMsgExpires(new Integer(3600))// 消息过期时间，单位为秒
              .addMessageType(1)// 消息类型:取值如下：0：透传消息， 1：通知；默认值为0
              .addMessage(notification.toString())// 内容类型是通知的为json格式数据
              .addDeviceType(4).addTopicId("BaiduPush");// 设置类别主题
      // 5. http request
      PushBatchUniMsgResponse response = pushClient.pushBatchUniMsg(request);
      // Http请求返回值解析
      System.out.println(String.format("msgId: %s, sendTime: %d", response.getMsgId(),
          response.getSendTime()));
    } catch (PushClientException e) {
      if (BaiduPushConstants.ERROROPTTYPE) {
        throw e;
      } else {
        e.printStackTrace();
      }
    } catch (PushServerException e) {
      if (BaiduPushConstants.ERROROPTTYPE) {
        throw e;
      } else {
        System.out.println(String.format("requestId: %d, errorCode: %d, errorMsg: %s",
            e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
      }
    }
  }

  /**/
  public static void DeleteDevicesFromTag(String[] channelIds) throws PushClientException,
      PushServerException {
    // 1. get apiKey and secretKey from developer console
    String apiKey = Constant.apiKey_ios;
    String secretKey = Constant.secretKey_ios;
    PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

    // 2. build a BaidupushClient object to access released interfaces
    BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

    // 3. register a YunLogHandler to get detail interacting information
    // in this request.
    pushClient.setChannelLogHandler(new YunLogHandler() {
      @Override
      public void onHandle(YunLogEvent event) {
        System.out.println(event.getMessage());
      }
    });

    try {
      // 4. specify request arguments
      // String[] channelIds = { "xxxxxxxxxxxxxxxxx" };
      DeleteDevicesFromTagRequest request =
          new DeleteDevicesFromTagRequest().addTagName("agencyIOS").addChannelIds(channelIds)
              .addDeviceType(4);
      // 5. http request
      DeleteDevicesFromTagResponse response = pushClient.deleteDevicesFromTag(request);
      // Http请求结果解析打印
      if (null != response) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("devicesInfoAfterDel:{");
        List<?> list = response.getDevicesInfoAfterDel();
        for (int i = 0; i < list.size(); i++) {
          if (i != 0) {
            strBuilder.append(",");
          }
          Object object = list.get(i);
          if (object instanceof DeviceInfo) {
            DeviceInfo deviceInfo = (DeviceInfo) object;
            strBuilder.append("{channelId: " + deviceInfo.getChannelId() + ", result: "
                + deviceInfo.getResult() + "}");
          }
        }
        strBuilder.append("}");
        System.out.println(strBuilder.toString());
      }
    } catch (PushClientException e) {
      if (BaiduPushConstants.ERROROPTTYPE) {
        throw e;
      } else {
        e.printStackTrace();
      }
    } catch (PushServerException e) {
      if (BaiduPushConstants.ERROROPTTYPE) {
        throw e;
      } else {
        System.out.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s",
            e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
      }
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

}
