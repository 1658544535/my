package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.ManufacturerWithdrawPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.ManufacturerWithdrawService;

/**
 * 后台商家提现
 */
public class ManufacturerWithdrawAction extends SuperAction {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  @Autowired
  ManufacturerWithdrawService manufacturerWithdrawService;
  @Autowired
  private ManufacturerService manufacturerService;

  private ManufacturerWithdrawPojo manufacturerWithdrawPojo;
  private ManufacturerPojo manufacturerPojo;
  private String beginDateStr;
  private String endDateStr;
  private String result;

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getBeginDateStr() {
    return beginDateStr;
  }

  public void setBeginDateStr(String beginDateStr) {
    this.beginDateStr = beginDateStr;
  }

  public String getEndDateStr() {
    return endDateStr;
  }

  public void setEndDateStr(String endDateStr) {
    this.endDateStr = endDateStr;
  }

  public ManufacturerWithdrawPojo getManufacturerWithdrawPojo() {
    return manufacturerWithdrawPojo;
  }

  public void setManufacturerWithdrawPojo(ManufacturerWithdrawPojo manufacturerWithdrawPojo) {
    this.manufacturerWithdrawPojo = manufacturerWithdrawPojo;
  }

  public ManufacturerPojo getManufacturerPojo() {
    return manufacturerPojo;
  }

  public void setManufacturerPojo(ManufacturerPojo manufacturerPojo) {
    this.manufacturerPojo = manufacturerPojo;
  }

  public String manufacturerWithdrawCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (manufacturerWithdrawPojo != null) {
      map.put("userId", manufacturerWithdrawPojo.getUserId());
      map.put("status", manufacturerWithdrawPojo.getStatus());
    }
    if (StringUtils.isNotEmpty(beginDateStr)) {
      map.put("beginDateStr", beginDateStr);
    }
    if (StringUtils.isNotEmpty(endDateStr)) {
      map.put("endDateStr", endDateStr);
    }
    int count = manufacturerWithdrawService.getManufacturerWithdrawCount(map);
    page.setRowCount(count);
    return SUCCESS;
  }

  public String manufacturerWithdrawList() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (manufacturerWithdrawPojo != null) {
      map.put("userId", manufacturerWithdrawPojo.getUserId());
      map.put("status", manufacturerWithdrawPojo.getStatus());
    }
    if (StringUtils.isNotEmpty(beginDateStr)) {
      map.put("beginDateStr", beginDateStr);
    }
    if (StringUtils.isNotEmpty(endDateStr)) {
      map.put("endDateStr", endDateStr);
    }
    map.put("pageNo", (page.getPageNo() - 1) * 10);
    map.put("pageSize", 10);
    List<ManufacturerWithdrawPojo> manufacturerWithdraws =
        manufacturerWithdrawService.getManufacturerWithdrawList(map);
    JSONArray json = JSONArray.fromObject(manufacturerWithdraws);
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String checkManufacturerWithdraw() throws SQLException {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && manufacturerWithdrawPojo != null
        && manufacturerWithdrawPojo.getId() != null && manufacturerWithdrawPojo.getId() > 0) {
      manufacturerWithdrawPojo =
          manufacturerWithdrawService.getManufacturerWithdrawById(manufacturerWithdrawPojo.getId());
      if (manufacturerWithdrawPojo != null && manufacturerWithdrawPojo.getStatus() == 0) {
        // 申请状态才能审核通过
        ManufacturerWithdrawPojo withdraw = new ManufacturerWithdrawPojo();
        withdraw.setStatus(2);
        withdraw.setUpdateDate(new Date());
        withdraw.setUpdateBy(user.getId());
        withdraw.setId(manufacturerWithdrawPojo.getId());
        try {
          manufacturerWithdrawService.updateWithdrawInfo(withdraw);
          this.result = "1";
        } catch (Exception e) {
          e.printStackTrace();
          this.result = "0";
        }
      }
    }
    return SUCCESS;
  }

  public String finishManufacturerWithdraw() throws SQLException {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && manufacturerWithdrawPojo != null
        && manufacturerWithdrawPojo.getId() != null && manufacturerWithdrawPojo.getId() > 0) {
      manufacturerWithdrawPojo.setStatus(4);
      manufacturerWithdrawPojo.setWithdrawDate(new Date());
      manufacturerWithdrawPojo.setUpdateDate(new Date());
      manufacturerWithdrawPojo.setUpdateBy(user.getId());
      try {
        manufacturerWithdrawService.updateWithdrawInfo(manufacturerWithdrawPojo);
        this.result = "1";
      } catch (Exception e) {
        e.printStackTrace();
        this.result = "0";
      }
    }
    return SUCCESS;
  }
}
