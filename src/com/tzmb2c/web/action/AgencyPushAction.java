package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.AgencyPojo;
import com.tzmb2c.web.pojo.AgencyPushPojo;
import com.tzmb2c.web.service.AgencyPushService;
import com.tzmb2c.web.service.SysDictService;

public class AgencyPushAction extends SuperAction {

  @Autowired
  private SysDictService sysDictService;

  @Autowired
  private AgencyPushService agencyPushService;

  private AgencyPushPojo agencyPush;
  private AgencyPojo agency;
  private Long agencyId;


  public void setAgency(AgencyPojo agency) {
    this.agency = agency;
  }

  public AgencyPojo getAgency() {
    return agency;
  }

  public Long getAgencyId() {

    return agencyId;
  }

  public void setAgencyId(Long agencyId) {
    this.agencyId = agencyId;
  }

  /**
   * @return the agencyPush
   */
  public AgencyPushPojo getAgencyPush() {
    return agencyPush;
  }

  /**
   * @param agencyPush the agencyPush to set
   */
  public void setAgencyPush(AgencyPushPojo agencyPush) {
    this.agencyPush = agencyPush;
  }



  public String goFindPushOrder() throws Exception {
    // ActionContext ac = ActionContext.getContext();
    // AgencyPushPojo ap= new AgencyPushPojo();
    // if(agency!=null){
    // ap.setAgencyId(agencyId);
    // }
    // if(agencyPush!=null){
    // ap.setOrderNo(agencyPush.getOrderNo());
    // ap.setPushStatus(agencyPush.getPushStatus());
    // ap.setBeganday(agencyPush.getBeganday());g
    // ap.setEndday(agencyPush.getEndday());
    // ap.setBeganday2(agencyPush.getBeganday2());
    // ap.setEndday2(agencyPush.getEndday2());
    // }
    Map<String, Object> map = new HashMap<String, Object>();

    map.put("agencyId", agencyId);

    if (agencyPush != null) {
      // map.put("orderNo",agencyPush.getOrderNo());
      map.put("pushStatus", agencyPush.getPushStatus());
      map.put("beganday", agencyPush.getBeganday());
      map.put("endday", agencyPush.getEndday());
      map.put("beganday2", agencyPush.getBeganday2());
      map.put("endday2", agencyPush.getEndday2());
    }
    List<AgencyPushPojo> p = agencyPushService.findAgencyPushOrder(map);
    // ac.put("agencyPushPojo",p);
    JSONArray json = JSONArray.fromObject(p);
    if (page == null) {
      page = new Pager();
    }
    page.setResult(json.toString());
    page.setRowCount(p == null ? 0 : p.size());
    return SUCCESS;
  }

  public String goFindPushCount() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (agency != null) {
      map.put("agencyId", agency.getAgencyId());
    }
    if (agencyPush != null) {
      // map.put("orderNo",agencyPush.getOrderNo());
      map.put("pushStatus", agencyPush.getPushStatus());
      map.put("beganday", agencyPush.getBeganday());
      map.put("endday", agencyPush.getEndday());
      map.put("beganday2", agencyPush.getBeganday2());
      map.put("endday2", agencyPush.getEndday2());
    }
    int i = agencyPushService.findAgencyPushCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }



}
