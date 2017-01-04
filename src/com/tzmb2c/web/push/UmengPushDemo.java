package com.tzmb2c.web.push;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tzmb2c.web.push.android.AndroidBroadcast;
import com.tzmb2c.web.push.android.AndroidCustomizedcast;
import com.tzmb2c.web.push.android.AndroidFilecast;
import com.tzmb2c.web.push.android.AndroidGroupcast;
import com.tzmb2c.web.push.android.AndroidUnicast;
import com.tzmb2c.web.push.ios.IOSBroadcast;
import com.tzmb2c.web.push.ios.IOSCustomizedcast;
import com.tzmb2c.web.push.ios.IOSFilecast;
import com.tzmb2c.web.push.ios.IOSGroupcast;
import com.tzmb2c.web.push.ios.IOSListcast;
import com.tzmb2c.web.push.ios.IOSUnicast;

public class UmengPushDemo {
  private String appkey = null;
  private String appMasterSecret = null;
  private String timestamp = null;

  public UmengPushDemo(String key, String secret) {
    try {
      appkey = key;
      appMasterSecret = secret;
      timestamp = Integer.toString((int) (System.currentTimeMillis() / 1000));
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  public void sendAndroidBroadcast() throws Exception {
    AndroidBroadcast broadcast = new AndroidBroadcast();
    broadcast.setAppMasterSecret(appMasterSecret);
    broadcast.setPredefinedKeyValue("appkey", this.appkey);
    broadcast.setPredefinedKeyValue("timestamp", this.timestamp);
    broadcast.setPredefinedKeyValue("ticker", "Android broadcast ticker");
    broadcast.setPredefinedKeyValue("title", "中文的title");
    broadcast.setPredefinedKeyValue("text", "Android broadcast text");
    broadcast.setPredefinedKeyValue("after_open", "go_app");
    broadcast.setPredefinedKeyValue("display_type", "notification");
    // TODO Set 'production_mode' to 'false' if it's a test device.
    // For how to register a test device, please see the developer doc.
    broadcast.setPredefinedKeyValue("production_mode", "true");
    // Set customized fields
    broadcast.setExtraField("test", "helloworld");
    broadcast.send();
  }

  public void sendAndroidUnicast() throws Exception {
    AndroidUnicast unicast = new AndroidUnicast();
    unicast.setAppMasterSecret(appMasterSecret);
    unicast.setPredefinedKeyValue("appkey", this.appkey);
    unicast.setPredefinedKeyValue("timestamp", this.timestamp);
    // TODO Set your device token
    unicast.setPredefinedKeyValue("device_tokens", "AnWbWRLXvs6v8R_rJ3PwkI1anD6r0qHOx1aTxOc2WDyr");
    unicast.setPredefinedKeyValue("ticker", "赶紧点击");
    unicast.setPredefinedKeyValue("title", "陶竹马订单");
    unicast.setPredefinedKeyValue("text", "您有一条待接收订单，请注意查收.");
    unicast.setPredefinedKeyValue("after_open", "go_app");
    unicast.setPredefinedKeyValue("display_type", "notification");
    // TODO Set 'production_mode' to 'false' if it's a test device.
    // For how to register a test device, please see the developer doc.
    unicast.setPredefinedKeyValue("production_mode", "true");
    // Set customized fields
    unicast.setExtraField("test", "helloworld");
    unicast.send();
  }

  public void sendAndroidGroupcast() throws Exception {
    AndroidGroupcast groupcast = new AndroidGroupcast();
    groupcast.setAppMasterSecret(appMasterSecret);
    groupcast.setPredefinedKeyValue("appkey", this.appkey);
    groupcast.setPredefinedKeyValue("timestamp", this.timestamp);
    /*
     * TODO Construct the filter condition: "where": { "and": [ {"tag":"test"}, {"tag":"Test"} ] }
     */
    JSONObject filterJson = new JSONObject();
    JSONObject whereJson = new JSONObject();
    JSONArray tagArray = new JSONArray();
    JSONObject testTag = new JSONObject();
    JSONObject TestTag = new JSONObject();
    testTag.put("tag", "test");
    TestTag.put("tag", "Test");
    tagArray.put(testTag);
    tagArray.put(TestTag);
    whereJson.put("and", tagArray);
    filterJson.put("where", whereJson);
    System.out.println(filterJson.toString());

    groupcast.setPredefinedKeyValue("filter", filterJson);
    groupcast.setPredefinedKeyValue("ticker", "Android groupcast ticker");
    groupcast.setPredefinedKeyValue("title", "中文的title");
    groupcast.setPredefinedKeyValue("text", "Android groupcast text");
    groupcast.setPredefinedKeyValue("after_open", "go_app");
    groupcast.setPredefinedKeyValue("display_type", "notification");
    // TODO Set 'production_mode' to 'false' if it's a test device.
    // For how to register a test device, please see the developer doc.
    groupcast.setPredefinedKeyValue("production_mode", "true");
    groupcast.send();
  }

  public void sendAndroidCustomizedcast() throws Exception {
    AndroidCustomizedcast customizedcast = new AndroidCustomizedcast();
    customizedcast.setAppMasterSecret(appMasterSecret);
    customizedcast.setPredefinedKeyValue("appkey", this.appkey);
    customizedcast.setPredefinedKeyValue("timestamp", this.timestamp);
    // TODO Set your alias here, and use comma to split them if there are multiple alias.
    // And if you have many alias, you can also upload a file containing these alias, then
    // use file_id to send customized notification.
    customizedcast.setPredefinedKeyValue("alias", "xx");
    // TODO Set your alias_type here
    customizedcast.setPredefinedKeyValue("alias_type", "xx");
    customizedcast.setPredefinedKeyValue("ticker", "Android customizedcast ticker");
    customizedcast.setPredefinedKeyValue("title", "中文的title");
    customizedcast.setPredefinedKeyValue("text", "Android customizedcast text");
    customizedcast.setPredefinedKeyValue("after_open", "go_app");
    customizedcast.setPredefinedKeyValue("display_type", "notification");
    // TODO Set 'production_mode' to 'false' if it's a test device.
    // For how to register a test device, please see the developer doc.
    customizedcast.setPredefinedKeyValue("production_mode", "true");
    customizedcast.send();
  }

  public void sendAndroidFilecast() throws Exception {
    AndroidFilecast filecast = new AndroidFilecast();
    filecast.setAppMasterSecret(appMasterSecret);
    filecast.setPredefinedKeyValue("appkey", this.appkey);
    filecast.setPredefinedKeyValue("timestamp", this.timestamp);
    // TODO upload your device tokens, and use '\n' to split them if there are multiple tokens
    filecast.uploadContents("aa" + "\n" + "bb");
    filecast.setPredefinedKeyValue("ticker", "Android filecast ticker");
    filecast.setPredefinedKeyValue("title", "中文的title");
    filecast.setPredefinedKeyValue("text", "Android filecast text");
    filecast.setPredefinedKeyValue("after_open", "go_app");
    filecast.setPredefinedKeyValue("display_type", "notification");
    filecast.send();
  }

  // IOS 广播测试
  public void sendIOSBroadcast() throws Exception {
    IOSBroadcast broadcast = new IOSBroadcast();
    broadcast.setAppMasterSecret(appMasterSecret);
    broadcast.setPredefinedKeyValue("appkey", this.appkey);
    broadcast.setPredefinedKeyValue("timestamp", this.timestamp);

    broadcast.setPredefinedKeyValue("alert", "IOS 广播测试");
    broadcast.setPredefinedKeyValue("badge", 0);
    broadcast.setPredefinedKeyValue("sound", "chime");
    // TODO set 'production_mode' to 'true' if your app is under production mode
    broadcast.setPredefinedKeyValue("production_mode", "false");
    // Set customized fields
    broadcast.setCustomizedField("test", "helloworld");
    broadcast.send();
  }

  // IOS 单播测试
  public void sendIOSUnicast() throws Exception {
    IOSUnicast unicast = new IOSUnicast();
    unicast.setAppMasterSecret(appMasterSecret);
    unicast.setPredefinedKeyValue("appkey", this.appkey);
    unicast.setPredefinedKeyValue("timestamp", this.timestamp);
    // TODO Set your device token
    unicast.setPredefinedKeyValue("device_tokens",
        "8f042d3351dbcfd0bb011229145c492a0824a873812e7fbb99efaf6647caf4c2");
    unicast.setPredefinedKeyValue("alert", "您有一条待接收订单，请注意查收.");
    unicast.setPredefinedKeyValue("badge", 0);
    unicast.setPredefinedKeyValue("sound", "chime");
    // TODO set 'production_mode' to 'true' if your app is under production mode
    unicast.setPredefinedKeyValue("production_mode", "true");
    // Set customized fields
    unicast.setCustomizedField("oid", "1");
    unicast.send();
  }

  // IOS 列播测试
  public void sendIOSListcast() throws Exception {
    IOSListcast listcast = new IOSListcast();
    listcast.setAppMasterSecret(appMasterSecret);
    listcast.setPredefinedKeyValue("appkey", this.appkey);
    listcast.setPredefinedKeyValue("timestamp", this.timestamp);
    // TODO Set your device token
    listcast
        .setPredefinedKeyValue(
            "device_tokens",
            "8f042d3351dbcfd0bb011229145c492a0824a873812e7fbb99efaf6647caf4c2,00cb7bf94f2b18aaad555b402d05f07eb2684847b59d462b82d7ace5da684d39");
    listcast.setPredefinedKeyValue("alert", "您有一条待接收订单，请注意查收.");
    listcast.setPredefinedKeyValue("badge", 0);
    listcast.setPredefinedKeyValue("sound", "chime");
    // TODO set 'production_mode' to 'true' if your app is under production mode false
    listcast.setPredefinedKeyValue("production_mode", "true");
    // Set customized fields
    listcast.setCustomizedField("oid", "1");
    listcast.send();
  }

  // IOS 组播测试
  public void sendIOSGroupcast() throws Exception {
    IOSGroupcast groupcast = new IOSGroupcast();
    groupcast.setAppMasterSecret(appMasterSecret);
    groupcast.setPredefinedKeyValue("appkey", this.appkey);
    groupcast.setPredefinedKeyValue("timestamp", this.timestamp);
    /*
     * TODO Construct the filter condition: "where": { "and": [ {"tag":"iostest"} ] }
     */
    JSONObject filterJson = new JSONObject();
    JSONObject whereJson = new JSONObject();
    JSONArray tagArray = new JSONArray();
    JSONObject testTag = new JSONObject();
    testTag.put("tag", "iostest");
    tagArray.put(testTag);
    whereJson.put("and", tagArray);
    filterJson.put("where", whereJson);
    System.out.println(filterJson.toString());

    // Set filter condition into rootJson
    groupcast.setPredefinedKeyValue("filter", filterJson);
    groupcast.setPredefinedKeyValue("alert", "IOS 组播测试");
    groupcast.setPredefinedKeyValue("badge", 0);
    groupcast.setPredefinedKeyValue("sound", "chime");
    // TODO set 'production_mode' to 'true' if your app is under production mode
    groupcast.setPredefinedKeyValue("production_mode", "false");
    groupcast.send();
  }

  // IOS 个性化测试
  public void sendIOSCustomizedcast() throws Exception {
    IOSCustomizedcast customizedcast = new IOSCustomizedcast();
    customizedcast.setAppMasterSecret(appMasterSecret);
    customizedcast.setPredefinedKeyValue("appkey", this.appkey);
    customizedcast.setPredefinedKeyValue("timestamp", this.timestamp);
    // TODO Set your alias here, and use comma to split them if there are multiple alias.
    // And if you have many alias, you can also upload a file containing these alias, then
    // use file_id to send customized notification.
    customizedcast.setPredefinedKeyValue("alias", "xx");
    // TODO Set your alias_type here
    customizedcast.setPredefinedKeyValue("alias_type", "xx");
    customizedcast.setPredefinedKeyValue("alert", "IOS 个性化测试");
    customizedcast.setPredefinedKeyValue("badge", 0);
    customizedcast.setPredefinedKeyValue("sound", "chime");
    // TODO set 'production_mode' to 'true' if your app is under production mode
    customizedcast.setPredefinedKeyValue("production_mode", "false");
    customizedcast.send();
  }

  // IOS 文件播测试
  public void sendIOSFilecast() throws Exception {
    IOSFilecast filecast = new IOSFilecast();
    filecast.setAppMasterSecret(appMasterSecret);
    filecast.setPredefinedKeyValue("appkey", this.appkey);
    filecast.setPredefinedKeyValue("timestamp", this.timestamp);
    // TODO upload your device tokens, and use '\n' to split them if there are multiple tokens
    filecast.uploadContents("aa" + "\n" + "bb");
    filecast.setPredefinedKeyValue("alert", "IOS 文件播测试");
    filecast.setPredefinedKeyValue("badge", 0);
    filecast.setPredefinedKeyValue("sound", "chime");
    // TODO set 'production_mode' to 'true' if your app is under production mode
    filecast.setPredefinedKeyValue("production_mode", "false");
    filecast.send();
  }

  public static void main(String[] args) {
    // TODO set your appkey and master secret here
    UmengPushDemo demo =
        new UmengPushDemo("55937c5367e58ee4b300255e", "kmsqpnnoywiwfcvrpeod8qknujecwshf");// ios
    // UmengPushDemo demo = new UmengPushDemo("55c95bba67e58e443c003ff0",
    // "ei0atzhg93cmwnsihwnylv0uhxe6f30m");//sendAndroid
    try {
      // demo.sendAndroidUnicast();
      // demo.sendIOSUnicast();
      demo.sendIOSListcast();
      /*
       * TODO these methods are all available, just fill in some fields and do the test
       * demo.sendAndroidBroadcast(); demo.sendAndroidGroupcast(); demo.sendAndroidCustomizedcast();
       * demo.sendAndroidFilecast();
       * 
       * demo.sendIOSBroadcast(); demo.sendIOSUnicast(); demo.sendIOSGroupcast();
       * demo.sendIOSCustomizedcast(); demo.sendIOSFilecast();
       */
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }


}
