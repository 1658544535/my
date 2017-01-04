package com.tzmb2c.web.push;

import java.util.List;

import com.baidu.yun.push.model.AddDevicesToTagResponse;
import com.baidu.yun.push.model.CreateTagResponse;
import com.baidu.yun.push.model.DeviceInfo;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.baidu.yun.push.model.PushMsgToTagResponse;

/***
 * 百度SDK请求响应返回，输出日志类
 * 
 * @author creazylee
 */
public class LogUtils {

  /**
   * 添加设备到标签组的响应日志
   * 
   * @param response
   */
  public static void printResponse_AddDevicesToTag(AddDevicesToTagResponse response) {
    // Http请求结果解析打印
    if (null != response) {
      StringBuilder strBuilder = new StringBuilder();
      strBuilder.append("devicesInTag：{");
      List<?> devicesInfo = response.getDevicesInfoAfterAdded();
      for (int i = 0; i < devicesInfo.size(); i++) {
        Object object = devicesInfo.get(i);
        if (i != 0) {
          strBuilder.append(",");
        }
        if (object instanceof DeviceInfo) {
          DeviceInfo deviceInfo = (DeviceInfo) object;
          strBuilder.append("{channelId:" + deviceInfo.getChannelId() + ",result:"
              + deviceInfo.getResult() + "}");
        }
      }
      strBuilder.append("}");
      System.out.println(strBuilder.toString());
    }
  }

  /**
   * 创建标签组的响应日志
   * 
   * @param response
   */
  public static void printResponse_CreateTagResponse(CreateTagResponse response) {
    System.out.println(String.format("tagName: %s, result: %d", response.getTagName(),
        response.getResult()));
  }

  /**
   * 组播推送的响应日志
   * 
   * @param response
   */
  public static void printResponse_PushMsgToTagResponse(PushMsgToTagResponse response) {
    // Http请求结果解析打印
    System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime()
        + ",timerId: " + response.getTimerId());
  }

  /**
   * 单设备推送的响应日志
   * 
   * @param response
   */
  public static void printResponse_PushMsgToSingleDeviceResponse(
      PushMsgToSingleDeviceResponse response) {
    // Http请求结果解析打印
    System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime());
  }

}
// fuck 小浣熊
