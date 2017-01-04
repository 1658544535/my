package com.tzmb2c.web.action;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.AdSpreadPojo;
import com.tzmb2c.web.service.AdSpreadService;


public class AdSpreadAction extends SuperAction {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  @Autowired
  private AdSpreadService adSpreadService;
  private AdSpreadPojo adSpreadPojo;
  private List<AdSpreadPojo> adSpreadPojoList;

  private Integer adType;
  private Integer channel;
  private String mac;
  private String ifa;
  private String callback_url;
  private String imei;
  private String android_id;
  private String advertising_id;
  private Integer tracking;
  private String package_id;

  public List<AdSpreadPojo> getAdSpreadPojoList() {
    return adSpreadPojoList;
  }

  public void setAdSpreadPojoList(List<AdSpreadPojo> adSpreadPojoList) {
    this.adSpreadPojoList = adSpreadPojoList;
  }

  public AdSpreadPojo getAdSpreadPojo() {
    return adSpreadPojo;
  }

  public void setAdSpreadPojo(AdSpreadPojo adSpreadPojo) {
    this.adSpreadPojo = adSpreadPojo;
  }

  public Integer getAdType() {
    return adType;
  }

  public void setAdType(Integer adType) {
    this.adType = adType;
  }

  public Integer getChannel() {
    return channel;
  }

  public void setChannel(Integer channel) {
    this.channel = channel;
  }

  public String getMac() {
    return mac;
  }

  public void setMac(String mac) {
    this.mac = mac;
  }

  public String getIfa() {
    return ifa;
  }

  public void setIfa(String ifa) {
    this.ifa = ifa;
  }

  public String getCallback_url() {
    return callback_url;
  }

  public void setCallback_url(String callback_url) {
    this.callback_url = callback_url;
  }

  public String getImei() {
    return imei;
  }

  public void setImei(String imei) {
    this.imei = imei;
  }

  public String getAndroid_id() {
    return android_id;
  }

  public void setAndroid_id(String android_id) {
    this.android_id = android_id;
  }

  public String getAdvertising_id() {
    return advertising_id;
  }

  public void setAdvertising_id(String advertising_id) {
    this.advertising_id = advertising_id;
  }

  public Integer getTracking() {
    return tracking;
  }

  public void setTracking(Integer tracking) {
    this.tracking = tracking;
  }

  public String getPackage_id() {
    return package_id;
  }

  public void setPackage_id(String package_id) {
    this.package_id = package_id;
  }

  /**
   * 有米广告推广地址IOS：http://b2c.taozhuma.com/youmiad.do?adType=1&channel=1
   * 有米广告推广地址AND：http://b2c.taozhuma.com/youmiad.do?adType=1&channel=2
   * 
   * @return
   * @throws Exception
   */
  public String youmiad() {
    Boolean result = false;
    if (adType == null || adType != 1 || channel == null || channel != 1 && channel != 2) {

    } else if (channel == 1 && (StringUtils.isEmpty(ifa) || StringUtils.isEmpty(callback_url))) {

    } else if (channel == 2 && (StringUtils.isEmpty(imei) || StringUtils.isEmpty(callback_url))) {

    } else {
      if (channel == 1) {
        // IOS
        AdSpreadPojo ad = new AdSpreadPojo();
        ad.setAdType(1);
        ad.setChannel(1);
        ad.setMac(mac);
        ad.setImei(ifa);
        ad.setStatus(0);
        try {
          ad.setCallbackUrl(URLDecoder.decode(callback_url, "utf-8"));
          adSpreadService.insertAdSpread(ad);
          result = true;
        } catch (Exception e) {
          e.printStackTrace();
          result = false;
        }

      } else if (channel == 2) {
        // android
        AdSpreadPojo ad = new AdSpreadPojo();
        ad.setAdType(1);
        ad.setChannel(2);
        ad.setMac(mac);
        ad.setImei(imei);
        ad.setStatus(0);
        ad.setAndroidId(android_id);
        ad.setAdvertisingId(advertising_id);
        ad.setPackageId(package_id);
        ad.setTracking(tracking);
        try {
          ad.setCallbackUrl(URLDecoder.decode(callback_url, "utf-8"));
          adSpreadService.insertAdSpread(ad);
          result = true;
        } catch (Exception e) {
          e.printStackTrace();
          result = false;
        }
      }
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("success", result);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 查询全部条数
   */
  public String findAdSpreadCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (adSpreadPojo != null) {
        map.put("adType", adSpreadPojo.getAdType());
        map.put("channel", adSpreadPojo.getChannel());
        map.put("imei", adSpreadPojo.getImei());
        map.put("userId", adSpreadPojo.getUserId());
        map.put("createStartDateStr", adSpreadPojo.getCreateStartDateStr());
        map.put("createEndDateStr", adSpreadPojo.getCreateEndDateStr());
        map.put("status", adSpreadPojo.getStatus());
      }
      int i = adSpreadService.findAdSpreadCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String findAdSpreadList() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (adSpreadPojo != null) {
        map.put("adType", adSpreadPojo.getAdType());
        map.put("channel", adSpreadPojo.getChannel());
        map.put("imei", adSpreadPojo.getImei());
        map.put("userId", adSpreadPojo.getUserId());
        map.put("status", adSpreadPojo.getStatus());
        map.put("createStartDateStr", adSpreadPojo.getCreateStartDateStr());
        map.put("createEndDateStr", adSpreadPojo.getCreateEndDateStr());
      }
      adSpreadPojoList = adSpreadService.findAdSpreadList(map);
      JSONArray json = JSONArray.fromObject(adSpreadPojoList);
      page.setRowCount(adSpreadPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }
}
