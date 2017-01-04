package com.tzmb2c.web.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ManufacturerSettlePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.ManufacturerSettleService;

/**
 * 后台商家结算
 */
public class ManufacturerSettleAction extends SuperAction {

  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  @Autowired
  ManufacturerSettleService facturerSettleService;
  @Autowired
  SellerService sellerService;

  private ManufacturerSettlePojo facturerSettlePojo;
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

  public ManufacturerSettlePojo getFacturerSettlePojo() {
    return facturerSettlePojo;
  }

  public void setFacturerSettlePojo(ManufacturerSettlePojo facturerSettlePojo) {
    this.facturerSettlePojo = facturerSettlePojo;
  }

  public String generateSettleInfo() throws Exception {
    // 批量生成结算信息
    sellerService.batchGenSettleInfo();
    return SUCCESS;
  }

  /**
   * 对账单-导出订单结算详情
   * 
   * @return
   * @throws IOException
   */
  public void genOrderSetterExcel() throws IOException {
    sellerService.getOrderSetterExcel(facturerSettlePojo);
  }

  public String manufacturerSettleCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (facturerSettlePojo != null) {
      map.put("userId", facturerSettlePojo.getUserId());
      map.put("status", facturerSettlePojo.getStatus());
    }
    if (StringUtils.isNotEmpty(beginDateStr)) {
      map.put("beginDateStr", beginDateStr);
    }
    if (StringUtils.isNotEmpty(endDateStr)) {
      map.put("endDateStr", endDateStr);
    }
    int count = facturerSettleService.getManufacturerSettleCount(map);
    page.setRowCount(count);
    return SUCCESS;
  }

  public String manufacturerSettleList() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (facturerSettlePojo != null) {
      map.put("userId", facturerSettlePojo.getUserId());
      map.put("status", facturerSettlePojo.getStatus());
    }
    if (StringUtils.isNotEmpty(beginDateStr)) {
      map.put("beginDateStr", beginDateStr);
    }
    if (StringUtils.isNotEmpty(endDateStr)) {
      map.put("endDateStr", endDateStr);
    }
    map.put("pageNo", (page.getPageNo() - 1) * 10);
    map.put("pageSize", 10);
    List<ManufacturerSettlePojo> facturerSettles =
        facturerSettleService.getManufacturerSettleList(map);
    JSONArray json = JSONArray.fromObject(facturerSettles);
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String checkManufacturerSettle() throws SQLException {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && facturerSettlePojo != null && facturerSettlePojo.getId() != null
        && facturerSettlePojo.getId() > 0) {
      facturerSettlePojo =
          facturerSettleService.getManufacturerSettleById(facturerSettlePojo.getId());
      if (facturerSettlePojo != null && facturerSettlePojo.getStatus() == 0) {
        // 0-待审核可以审核通过
        ManufacturerSettlePojo manufacturerSettle = new ManufacturerSettlePojo();
        manufacturerSettle.setStatus(1);
        manufacturerSettle.setUpdateBy(user.getId());
        manufacturerSettle.setUpdateDate(new Date());
        manufacturerSettle.setId(facturerSettlePojo.getId());
        try {
          facturerSettleService.updateSettleInfo(manufacturerSettle);
          this.result = "1";
        } catch (Exception e) {
          e.printStackTrace();
          this.result = "0";
        }
      }
    }
    return SUCCESS;
  }

  public String uncheckManufacturerSettle() throws SQLException {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && facturerSettlePojo != null && facturerSettlePojo.getId() != null
        && facturerSettlePojo.getId() > 0) {
      facturerSettlePojo =
          facturerSettleService.getManufacturerSettleById(facturerSettlePojo.getId());
      if (facturerSettlePojo != null && facturerSettlePojo.getStatus() == 1) {
        // 1-审核通过可以取消
        ManufacturerSettlePojo manufacturerSettle = new ManufacturerSettlePojo();
        manufacturerSettle.setStatus(0);
        manufacturerSettle.setUpdateBy(user.getId());
        manufacturerSettle.setUpdateDate(new Date());
        manufacturerSettle.setId(facturerSettlePojo.getId());
        try {
          facturerSettleService.updateSettleInfo(manufacturerSettle);
          this.result = "1";
        } catch (Exception e) {
          e.printStackTrace();
          this.result = "0";
        }
      }
    }
    return SUCCESS;
  }

}
