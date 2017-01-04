package com.tzmb2c.web.push.android;

import com.tzmb2c.web.push.AndroidNotification;

public class AndroidListcast extends AndroidNotification {
  public AndroidListcast() {
    try {
      this.setPredefinedKeyValue("type", "listcast");
    } catch (Exception ex) {
      ex.printStackTrace();
      System.exit(1);
    }
  }
}
