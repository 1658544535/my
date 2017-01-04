package com.tzmb2c.web.push.android;

import com.tzmb2c.web.push.AndroidNotification;

public class AndroidUnicast extends AndroidNotification {
  public AndroidUnicast() {
    try {
      this.setPredefinedKeyValue("type", "unicast");
    } catch (Exception ex) {
      ex.printStackTrace();
      System.exit(1);
    }
  }
}
