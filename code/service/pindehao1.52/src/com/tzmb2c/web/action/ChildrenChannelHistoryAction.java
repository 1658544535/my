package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.ChildrenChannelHistoryPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ChildrenChannelHistoryService;
import com.tzmb2c.web.service.ChildrenChannelService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.SpecialShowService;
import com.tzmb2c.web.service.SyntheticalDictService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;

public class ChildrenChannelHistoryAction extends SuperAction {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  @Autowired
  private ChildrenChannelHistoryService childrenChannelHistoryService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private SyntheticalDictService syntheticalDictService;
  @Autowired
  private ChildrenChannelService childrenChannelService;
  @Autowired
  private SpecialShowService specialShowService;
  @Autowired
  private ProductService productService;

  private ChildrenChannelHistoryPojo childrenChannelHistoryPojo;
  private SysLoginPojo sysLogin, sysLogin1;
  private String result;
  private String[] tids;
  private Long type;

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  public ChildrenChannelHistoryPojo getChildrenChannelHistoryPojo() {
    return childrenChannelHistoryPojo;
  }

  public void setChildrenChannelHistoryPojo(ChildrenChannelHistoryPojo childrenChannelHistoryPojo) {
    this.childrenChannelHistoryPojo = childrenChannelHistoryPojo;
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

  public SysLoginPojo getSysLogin() {
    return sysLogin;
  }

  public void setSysLogin1(SysLoginPojo sysLogin1) {
    this.sysLogin1 = sysLogin1;
  }

  public SysLoginPojo getSysLogin1() {
    return sysLogin1;
  }

  public void setSysLogin(SysLoginPojo sysLogin) {
    this.sysLogin = sysLogin;
  }

  public String findChildrenChannelHistoryList() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (type != null) {
      map.put("type", getType());
    }
    if (childrenChannelHistoryPojo != null) {
      map.put("userName", childrenChannelHistoryPojo.getUserName());
      map.put("businessName", childrenChannelHistoryPojo.getBusinessName());
      map.put("businessNames", childrenChannelHistoryPojo.getBusinessNames());
      map.put("paixu", childrenChannelHistoryPojo.getPaixu());
    }
    List<ChildrenChannelHistoryPojo> list =
        childrenChannelHistoryService.findChildrenChannelHistoryList(map);
    for (ChildrenChannelHistoryPojo childrenChannelHistory : list) {
      if (childrenChannelHistory.getUserName() == null) {
        childrenChannelHistory.setUserName("无");
      }
    }
    JSONArray json = JSONArray.fromObject(list);
    page.setRowCount(childrenChannelHistoryService.findChildrenChannelHistoryCount(map));
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String findChildrenChannelHistoryCount() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (type != null) {
      map.put("type", getType());
    }
    if (childrenChannelHistoryPojo != null) {
      map.put("userName", childrenChannelHistoryPojo.getUserName());
      map.put("businessName", childrenChannelHistoryPojo.getBusinessName());
      map.put("businessNames", childrenChannelHistoryPojo.getBusinessNames());
    }
    int i = childrenChannelHistoryService.findChildrenChannelHistoryCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }
}
