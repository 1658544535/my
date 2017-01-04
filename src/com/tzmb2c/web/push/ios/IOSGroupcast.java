package com.tzmb2c.web.push.ios;

import com.tzmb2c.web.push.IOSNotification;

public class IOSGroupcast extends IOSNotification {
  public IOSGroupcast() {
    try {
      this.setPredefinedKeyValue("type", "groupcast");
    } catch (Exception ex) {
      ex.printStackTrace();
      System.exit(1);
    }
  }
}
