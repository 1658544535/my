package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserToyLogPojo;
import com.tzmb2c.web.service.UserToyLogService;

/**
 * 
 * 游戏工厂玩具生成记录日志
 * 
 * @author linyuting
 */
public class UserToyLogAction extends SuperAction {
  @Autowired
  private UserToyLogService userToyLogService;

  private UserToyLogPojo userToyLogPojo;

  /**
   * 
   * 玩具记录条数
   * 
   * @throws SQLException
   * @return String
   */
  public String UserToyLogListCount() throws SQLException {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (userToyLogPojo != null) {
        map.put("username", userToyLogPojo.getUsername());
        map.put("telphone", userToyLogPojo.getTelphone());
        map.put("toyName", userToyLogPojo.getToyName());
        map.put("beginday", userToyLogPojo.getBeginday());
        map.put("endday", userToyLogPojo.getEndday());
      }
      int i = userToyLogService.findAllCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 
   * 玩具记录列表
   * 
   * @throws SQLException
   * @return String
   */
  public String UserToyLogfindAll() throws SQLException {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      if (page != null) {
        map.put("pageSize", page.getPageSize());
        map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      }
      if (userToyLogPojo != null) {
        map.put("loginName", userToyLogPojo.getLoginName());
        map.put("telphone", userToyLogPojo.getTelphone());
        map.put("toyName", userToyLogPojo.getToyName());
        map.put("beginday", userToyLogPojo.getBeginday());
        map.put("endday", userToyLogPojo.getEndday());
      }
      JSONArray json = JSONArray.fromObject(userToyLogService.findAll(map));
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 
   * 跳转到玩具记录发货信息页面
   * 
   * @throws SQLException
   * @return String
   */
  public String ToySendOut() throws SQLException {
    ActionContext ac = ActionContext.getContext();
    SysLoginPojo loginPojo = (SysLoginPojo) ac.getSession().get("user");
    if (userToyLogPojo != null) {
      userToyLogPojo = userToyLogService.findUserToyLogById(userToyLogPojo.getId());
    }
    if (loginPojo != null) {
      if (loginPojo.getType() != null && loginPojo.getType() != "" && userToyLogPojo != null) {
        if (loginPojo.getType().equals("0")) {
          userToyLogPojo.setLoginType("true");
        } else {
          userToyLogPojo.setLoginType("false");
        }
      }
    }
    ac.put("", userToyLogPojo);
    return SUCCESS;
  }

  /**
   * 
   * 修改玩具记录发货信息
   * 
   * @throws SQLException
   * @return void
   */
  public String updateToySendOut() throws SQLException {
    try {
      userToyLogService.updateUserToyLog(userToyLogPojo);
      System.out.println("这是userToyLogPojo" + userToyLogPojo);
      FileUtil.alertMessageBySkip("提交成功！", "userToyLogList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "userToyLogList.do");
    }
    return null;
  }



  public UserToyLogPojo getUserToyLogPojo() {
    return userToyLogPojo;
  }

  public void setUserToyLogPojo(UserToyLogPojo userToyLogPojo) {
    this.userToyLogPojo = userToyLogPojo;
  }


}
