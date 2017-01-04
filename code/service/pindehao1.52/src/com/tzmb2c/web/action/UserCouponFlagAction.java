/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.UserCouponFlagPojo;
import com.tzmb2c.web.service.UserCouponFlagService;

/**
 * @version 1.0
 * @author
 */
public class UserCouponFlagAction extends SuperAction {

  // service
  @Autowired
  private UserCouponFlagService userCouponFlagService;

  // variable
  private UserCouponFlagPojo userCouponFlagPojo;
  private List<UserCouponFlagPojo> userCouponFlagPojoList;
  private String[] tids;

  // getset
  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public UserCouponFlagPojo getUserCouponFlagPojo() {
    return userCouponFlagPojo;
  }

  public void setUserCouponFlagPojo(UserCouponFlagPojo userCouponFlagPojo) {
    this.userCouponFlagPojo = userCouponFlagPojo;
  }

  public List<UserCouponFlagPojo> getUserCouponFlagPojoList() {
    return userCouponFlagPojoList;
  }

  public void setUserCouponFlagPojoList(List<UserCouponFlagPojo> userCouponFlagPojoList) {
    this.userCouponFlagPojoList = userCouponFlagPojoList;
  }

  /**
   * findCount
   */
  public String userCouponFlagListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (userCouponFlagPojo != null) {

      }
      int i = userCouponFlagService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * findAll
   */
  public String userCouponFlagListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (userCouponFlagPojo != null) {

      }
      userCouponFlagPojoList = userCouponFlagService.listPage(map);
      JSONArray json = JSONArray.fromObject(userCouponFlagPojoList);
      page.setRowCount(userCouponFlagPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * delById
   * 
   * @return
   */
  public String delUserCouponFlagById() throws Exception {
    try {
      userCouponFlagService.delete(userCouponFlagPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "goUserCouponFlagList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "goUserCouponFlagList.do");
    }
    return null;
  }

  /**
   * delByIds
   * 
   * @return
   */
  public String delUserCouponFlagByIds() {
    try {
      for (String id : tids) {
        userCouponFlagService.delete(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部删除成功！", "goUserCouponFlagList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部删除失败！", "goUserCouponFlagList.do");
    }
    return null;
  }

  /**
   * checkById
   * 
   * @return
   */
  public String checkUserCouponFlagById() throws Exception {
    try {
      // userCouponFlagService.checkUserCouponFlag(userCouponFlagPojo.getId());
      FileUtil.alertMessageBySkip("审核成功！", "goUserCouponFlagList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("审核失败！", "UserCouponFlagList.do");
    }
    return null;
  }

  /**
   * unCheckById
   * 
   * @return
   */
  public String unCheckUserCouponFlagById() throws Exception {
    try {
      // userCouponFlagService.uncheckUserCouponFlag(userCouponFlagPojo.getId());
      FileUtil.alertMessageBySkip("取消审核成功！", "goUserCouponFlagList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("取消审核失败！", "goUserCouponFlagList.do");
    }
    return null;
  }

  /**
   * goupdate
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateUserCouponFlagById() throws Exception {
    try {
      userCouponFlagPojo = userCouponFlagService.getById(userCouponFlagPojo.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * update
   * 
   * @return
   * @throws Exception
   */
  public String updateUserCouponFlagById() throws Exception {
    try {
      if (userCouponFlagPojo != null && userCouponFlagPojo.getId() != null) {
        userCouponFlagService.update(userCouponFlagPojo);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }
}
