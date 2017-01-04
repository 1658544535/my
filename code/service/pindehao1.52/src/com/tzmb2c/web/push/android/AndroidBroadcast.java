package com.tzmb2c.web.push.android;

import com.tzmb2c.web.push.AndroidNotification;

public class AndroidBroadcast extends AndroidNotification {
  public AndroidBroadcast() {
    try {
      this.setPredefinedKeyValue("type", "broadcast");
    } catch (Exception ex) {
      ex.printStackTrace();
      System.exit(1);
    }
  }
}
