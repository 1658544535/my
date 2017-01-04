package com.tzmb2c.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserTalentAuthPojo;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserTalentAuthService;

public class UserTalentAuthAction extends SuperAction {
  @Autowired
  private UserTalentAuthService userTalentAuthService;
  @Autowired
  private SysLoginService sysLoginService;

  private UserTalentAuthPojo userTalentAuthPojo;
  private List<UserTalentAuthPojo> userTalentAuthPojoList;
  private Long status;

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public UserTalentAuthPojo getUserTalentAuthPojo() {
    return userTalentAuthPojo;
  }

  public List<UserTalentAuthPojo> getUserTalentAuthPojoList() {
    return userTalentAuthPojoList;
  }

  public void setUserTalentAuthPojoList(List<UserTalentAuthPojo> userTalentAuthPojoList) {
    this.userTalentAuthPojoList = userTalentAuthPojoList;
  }

  public void setUserTalentAuthPojo(UserTalentAuthPojo userTalentAuthPojo) {
    this.userTalentAuthPojo = userTalentAuthPojo;
  }

  /**
   * 查询全部条数
   */
  public String userTalentAuthListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (userTalentAuthPojo != null) {
        map.put("loginname", userTalentAuthPojo.getLoginname());
        map.put("userName", userTalentAuthPojo.getUserName());
        map.put("status", userTalentAuthPojo.getStatus());
      }
      int i = userTalentAuthService.findUserTalentAuthCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String userTalentAuthListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (userTalentAuthPojo != null) {
        map.put("loginname", userTalentAuthPojo.getLoginname());
        map.put("userName", userTalentAuthPojo.getUserName());
        map.put("status", userTalentAuthPojo.getStatus());
        map.put("all", userTalentAuthPojo.getAll() == null ? 0
            : userTalentAuthPojo.getAll());
      }
      userTalentAuthPojoList = userTalentAuthService
          .findUserTalentAuthList(map);
      for (UserTalentAuthPojo userTalentAuth : userTalentAuthPojoList) {
        userTalentAuth.setPlatformFansNum(userTalentAuth.getPlatform() + "--"
            + userTalentAuth.getFansNumName());
      }
      JSONArray json = JSONArray.fromObject(userTalentAuthPojoList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 跳转审核页面
   * 
   * @return
   * @throws Exception
   */
  public String goCheckUserTalentAuth() throws Exception {
    if (userTalentAuthPojo != null) {
      userTalentAuthPojo = userTalentAuthService
          .findUserTalentAuthById(userTalentAuthPojo
              .getId());
    }
    return SUCCESS;
  }

  /**
   * 根据id审核
   * 
   * @return
   */
  public String checkUserTalentAuth() throws Exception {
    try {
      Date now = new Date();
      userTalentAuthService.checkUserTalentAuth(userTalentAuthPojo
          .getId());
      Long userId =
          userTalentAuthService.findUserTalentAuthById(userTalentAuthPojo.getId()).getUserId();
      SysLoginPojo sysLoginPojo = new SysLoginPojo();
      sysLoginPojo.setType("11");
      sysLoginPojo.setId(userId);
      sysLoginPojo.setUpdateBy(1L);
      sysLoginPojo.setUpdateDate(now);
      sysLoginService.updateType(sysLoginPojo);
      FileUtil.alertMessageBySkip("此达人已审核成功！", "userTalentAuth.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("此条状态更改错误！", "userTalentAuth.do");
    }
    return null;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheckUserTalentAuth() throws Exception {
    try {
      Date now = new Date();
      userTalentAuthService.uncheckUserTalentAuth(userTalentAuthPojo
          .getId());
      Long userId =
          userTalentAuthService.findUserTalentAuthById(userTalentAuthPojo.getId()).getUserId();
      SysLoginPojo sysLoginPojo = new SysLoginPojo();
      sysLoginPojo.setType("1");
      sysLoginPojo.setId(userId);
      sysLoginPojo.setUpdateBy(1L);
      sysLoginPojo.setUpdateDate(now);
      sysLoginService.updateType(sysLoginPojo);
      FileUtil.alertMessageBySkip("此达人已审核失败！", "userTalentAuth.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("此条状态更改错误！", "userTalentAuth.do");
    }
    return null;
  }
}
