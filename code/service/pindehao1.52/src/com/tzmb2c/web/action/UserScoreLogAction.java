package com.tzmb2c.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.UserScoreLogPojo;
import com.tzmb2c.web.service.UserScoreLogService;

/**
 * 安全问题Action 2014-12-15
 * 
 * @author creazylee
 */
public class UserScoreLogAction extends SuperAction {

  @Autowired
  private UserScoreLogService userScoreLogService;
  private UserScoreLogPojo userScoreLogPojo;
  private String result;
  private String[] tids;
  private List<UserScoreLogPojo> userScoreLogPojoList;

  public List<UserScoreLogPojo> getUserScoreLogList() {
    return userScoreLogPojoList;
  }

  public void setUserScoreLogList(List<UserScoreLogPojo> userScoreLogPojoList) {
    this.userScoreLogPojoList = userScoreLogPojoList;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public UserScoreLogPojo getUserScoreLogPojo() {
    return userScoreLogPojo;
  }

  public void setUserScoreLogPojo(UserScoreLogPojo userScoreLogPojo) {
    this.userScoreLogPojo = userScoreLogPojo;
  }

  /**
   * 用户积分条数目
   * 
   * @return
   * @throws Exception
   */
  public String userScoreLogListCount() throws Exception {

    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (userScoreLogPojo != null) {
        map.put("name", userScoreLogPojo.getName().trim());
        map.put("tradeSource", userScoreLogPojo.getTradeSource());
        map.put("begandayStr", userScoreLogPojo.getBegandayStr());
        map.put("enddayStr", userScoreLogPojo.getEnddayStr());
      }
      int i = userScoreLogService.findUserScoreLogCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 用户积分列表
   * 
   * @return
   * @throws Exception
   */
  public String userScoreLogListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (userScoreLogPojo != null) {
        map.put("name", userScoreLogPojo.getName().trim());
        map.put("tradeSource", userScoreLogPojo.getTradeSource());
        map.put("begandayStr", userScoreLogPojo.getBegandayStr());
        map.put("enddayStr", userScoreLogPojo.getEnddayStr());
      }
      userScoreLogPojoList = userScoreLogService.findUserScoreLogList(map);
      JSONArray json = JSONArray.fromObject(userScoreLogPojoList);
      page.setRowCount(userScoreLogPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }


}
