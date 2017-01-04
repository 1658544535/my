package com.tzmb2c.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.UserScorePojo;
import com.tzmb2c.web.service.UserScoreService;

public class UserScoreAction extends SuperAction {

  @Autowired
  private UserScoreService userScoreService;
  private UserScorePojo userScorePojo;
  private String result;
  private String[] tids;
  private List<UserScorePojo> userScorePojoList;

  public List<UserScorePojo> getUserScoreList() {
    return userScorePojoList;
  }

  public void setUserScoreList(List<UserScorePojo> userScorePojoList) {
    this.userScorePojoList = userScorePojoList;
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

  public UserScorePojo getUserScorePojo() {
    return userScorePojo;
  }

  public void setUserScorePojo(UserScorePojo userScorePojo) {
    this.userScorePojo = userScorePojo;
  }

  /**
   * 用户积分条数目
   * 
   * @return
   * @throws Exception
   */
  public String userScoreListCount() throws Exception {

    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      // 触发更新摇一摇数据
      String date = StringUtil.dateToString(new Date());
      map.put("shakeDateStr", date);
      userScorePojoList = userScoreService.findUserScoreList(map);
      if (userScorePojoList != null && userScorePojoList.size() > 0) {
        UserScorePojo userScoreUpdate = null;
        for (UserScorePojo userScorePojo : userScorePojoList) {
          // if (!date.equals(userScorePojo.getShakeDateStr())) {
          userScoreUpdate = new UserScorePojo();
          userScoreUpdate.setShakeDateStr(date);
          if (userScorePojo.getShakeNum() != 3) {
            userScoreUpdate.setShakeNum(3);
          }
          userScoreUpdate.setUserId(userScorePojo.getUserId());
          userScoreService.updateUserScoreByUid(userScoreUpdate);
          // }
        }
      }
      map.clear();

      if (userScorePojo != null) {
        map.put("name", userScorePojo.getName().trim());
        map.put("shakeNum", userScorePojo.getShakeNum());
      }
      int i = userScoreService.findUserScoreCount(map);
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
  public String userScoreListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (userScorePojo != null) {
        map.put("name", userScorePojo.getName().trim());
        map.put("shakeNum", userScorePojo.getShakeNum());
      }
      userScorePojoList = userScoreService.findUserScoreList(map);
      Date date = new Date();
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      if (userScorePojoList != null) {
        for (UserScorePojo userScorePojo : userScorePojoList) {
          // if((date.compareTo(userScorePojo.getShakeDate())!=0)){
          if (!df.format(date).equals(userScorePojo.getShakeDateStr())) {
            userScorePojo.setShakeNum(3);
          }
        }
      }
      JSONArray json = JSONArray.fromObject(userScorePojoList);
      page.setRowCount(userScorePojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }



}
