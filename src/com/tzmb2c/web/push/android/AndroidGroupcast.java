package com.tzmb2c.web.push.android;

import com.tzmb2c.web.push.AndroidNotification;

public class AndroidGroupcast extends AndroidNotification {
  public AndroidGroupcast() {
    try {
      this.setPredefinedKeyValue("type", "groupcast");
    } catch (Exception ex) {
      ex.printStackTrace();
      System.exit(1);
    }
  }
}
