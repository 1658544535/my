package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserDeductionScorePojo;
import com.tzmb2c.web.service.UserDeductionScoreService;

public class SellerDeductionScoreWebAction extends SuperAction {

  @Autowired
  private UserDeductionScoreService userDeductionScoreService;

  private List<UserDeductionScorePojo> userDeductionScorePojos;
  private UserDeductionScorePojo userDeductionScorePojo;

  public List<UserDeductionScorePojo> getUserDeductionScorePojos() {
    return userDeductionScorePojos;
  }

  public void setUserDeductionScorePojos(List<UserDeductionScorePojo> userDeductionScorePojos) {
    this.userDeductionScorePojos = userDeductionScorePojos;
  }

  public UserDeductionScorePojo getUserDeductionScorePojo() {
    return userDeductionScorePojo;
  }

  public void setUserDeductionScorePojo(UserDeductionScorePojo userDeductionScorePojo) {
    this.userDeductionScorePojo = userDeductionScorePojo;
  }

  /**
   * 首页扣分详情页面
   * 
   * @return
   * @throws SQLException
   */
  public String goDeductionScoreWeb() throws SQLException {
    SysLoginPojo loginPojo = UserUtil.getWebUser();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("status", 1);
    int count = 0;
    if (loginPojo != null) {
      map.put("suserId", loginPojo.getId());
      count = userDeductionScoreService.findUserDeductionScoreCount(map);
    }
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(count);
    return SUCCESS;
  }

  /**
   * 首页扣分详情列表
   * 
   * @return
   * @throws SQLException
   */
  public String getDeductionScoreListWeb() throws SQLException {
    SysLoginPojo loginPojo = UserUtil.getWebUser();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("status", 1);
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (loginPojo != null) {
      map.put("suserId", loginPojo.getId());
      userDeductionScorePojos = userDeductionScoreService.findUserDeductionScoreList(map);
    }
    for (UserDeductionScorePojo u : userDeductionScorePojos) {
      if (u.getUpdateDateStr() != null && u.getUpdateDateStr() != "") {
        u.setCreateDateStr(u.getUpdateDateStr());
      }
    }
    JSONArray json = JSONArray.fromObject(userDeductionScorePojos);
    page.setResult(json.toString());
    return SUCCESS;
  }


}
