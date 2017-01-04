package com.tzmb2c.web.push.ios;

import com.tzmb2c.web.push.IOSNotification;

public class IOSListcast extends IOSNotification {
  public IOSListcast() {
    try {
      this.setPredefinedKeyValue("type", "listcast");
    } catch (Exception ex) {
      ex.printStackTrace();
      System.exit(1);
    }
  }
}
