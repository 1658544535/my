package com.tzmb2c.web.push.ios;

import com.tzmb2c.web.push.IOSNotification;

public class IOSUnicast extends IOSNotification {
  public IOSUnicast() {
    try {
      this.setPredefinedKeyValue("type", "unicast");
    } catch (Exception ex) {
      ex.printStackTrace();
      System.exit(1);
    }
  }
}
