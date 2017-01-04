package com.tzmb2c.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.ProductAccessLogDao;
import com.tzmb2c.web.pojo.ProductAccessLogPojo;
import com.tzmb2c.web.service.ProductAccessLogService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;

public class ProductAccessLogAction extends SuperAction {

  @Autowired
  private ProductAccessLogService productAccessLogService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private ProductAccessLogDao productAccessLogDao;
  @Autowired
  private SysLoginService sysLoginService;

  private ProductAccessLogPojo productAccessLog;
  private String result;
  private String[] tids;
  private String os;
  private String beganday = null, endday = null;


  public String getBeganday() {
    return beganday;
  }

  public void setBeganday(String beganday) {
    this.beganday = beganday;
  }

  public String getEndday() {
    return endday;
  }

  public void setEndday(String endday) {
    this.endday = endday;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }

  public ProductAccessLogPojo getProductAccessLog() {
    return productAccessLog;
  }

  public void setProductAccessLog(ProductAccessLogPojo productAccessLog) {
    this.productAccessLog = productAccessLog;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getProductAccessLogCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(productAccessLogService.productAccessLogAllCount(productAccessLog, beganday,
        endday));
    return SUCCESS;
  }

  public String productAccessLogAllList() {
    JSONArray json =
        JSONArray.fromObject(productAccessLogService.productAccessLogAllList(productAccessLog,
            page, beganday, endday));
    page.setResult(json.toString());

    return SUCCESS;
  }



  // //////////////////////////////////////////////分割线///////////////////////////////////////
  public String getProductAccessLogCountHot() throws Exception {
    if (page == null) {
      page = new Pager();
    }

    Map<String, Object> map = new HashMap<String, Object>();
    if (productAccessLog != null) {
      map.put("productName", productAccessLog.getProductName());
      map.put("userName", productAccessLog.getUserName());
    }
    if (beganday != null && !beganday.equals("")) {
      map.put("beganday", beganday);
    }
    if (endday != null && !endday.equals("")) {
      map.put("endday", endday);
    }
    if (os != null && !os.equals("")) {
      map.put("os", Integer.parseInt(os));
    }
    List<ProductAccessLogPojo> list = productAccessLogDao.productAccessLogAllListHot(map);
    page.setRowCount(list.size());
    return SUCCESS;
  }

  public String productAccessLogAllListHot() {
    JSONArray json =
        JSONArray.fromObject(productAccessLogService.productAccessLogAllListHot(productAccessLog,
            page, os, beganday, endday));
    page.setResult(json.toString());
    return SUCCESS;
  }

}
