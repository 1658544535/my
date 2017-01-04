package com.tzmb2c.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.util.StringUtils;

/**
 * 
 * @Title:系统配置资源类
 * @Description:
 * @Author:fengjianshe
 * @Since:2013年8月8日
 * @Version:1.1.0
 */
public class PropertiesHelper {
  private static final String fileName = "sysconfig";
  private static final Map<String, String> CACHE = new HashMap<String, String>();

  private PropertiesHelper() {}

  private static String processProperties(String keyName) {
    Map<String, ResourceBundle> bundleMaps = new HashMap<String, ResourceBundle>();
    ResourceBundle resourceBundle = bundleMaps.get(fileName);
    if (resourceBundle == null) {
      bundleMaps.put(fileName, ResourceBundle.getBundle(fileName));
      resourceBundle = bundleMaps.get(fileName);
    }
    String value = null;
    value = resourceBundle.getString(keyName);
    return value;

  }

  public static String getValue(String key) {
    String value = CACHE.get(key);
    if (StringUtils.isEmpty(value)) {
      value = processProperties(key);
      CACHE.put(key, value);
    }
    return value;

  }
}
