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
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserDistributionInfoPojo;
import com.tzmb2c.web.service.UserDistributionInfoService;

public class UserDistributionInfoAction extends SuperAction {

  private UserDistributionInfoPojo userDistributionInfoPojo;
  private List<UserDistributionInfoPojo> userDistributionInfoPojos;
  @Autowired
  private UserDistributionInfoService userDistributionInfoService;

  public UserDistributionInfoPojo getUserDistributionInfoPojo() {
    return userDistributionInfoPojo;
  }

  public void setUserDistributionInfoPojo(UserDistributionInfoPojo userDistributionInfoPojo) {
    this.userDistributionInfoPojo = userDistributionInfoPojo;
  }

  public List<UserDistributionInfoPojo> getUserDistributionInfoPojos() {
    return userDistributionInfoPojos;
  }

  public void setUserDistributionInfoPojos(List<UserDistributionInfoPojo> userDistributionInfoPojos) {
    this.userDistributionInfoPojos = userDistributionInfoPojos;
  }

  /**
   * 分销商账户信息
   * 
   * @return
   */
  public String userDistributionInfo() {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      if (userDistributionInfoPojo != null) {
        map.put("userName", userDistributionInfoPojo.getUserName());
        map.put("name", userDistributionInfoPojo.getName());
        map.put("loginname", userDistributionInfoPojo.getLoginname());
        map.put("sex", userDistributionInfoPojo.getSex());
        map.put("personCode", userDistributionInfoPojo.getPersonCode());
        if (userDistributionInfoPojo.getCreateDateStr().length() >= 10) {
          Date c = sdf.parse(userDistributionInfoPojo.getCreateDateStr() + " 00:00:00");
          userDistributionInfoPojo.setCreateDate(c);
          map.put("createDate", userDistributionInfoPojo.getCreateDate());
        }
        map.put("status", userDistributionInfoPojo.getStatus());
      }
      page.setRowCount(userDistributionInfoService.userDistributionInfoAll(map).size());
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 分销商账户信息列表
   * 
   * @return
   */
  public String userDistributionInfoAll() {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (userDistributionInfoPojo != null) {
        map.put("userName", userDistributionInfoPojo.getUserName());
        map.put("name", userDistributionInfoPojo.getName());
        map.put("loginname", userDistributionInfoPojo.getLoginname());
        map.put("sex", userDistributionInfoPojo.getSex());
        map.put("personCode", userDistributionInfoPojo.getPersonCode());
        if (userDistributionInfoPojo.getCreateDateStr().length() >= 10) {
          Date c = sdf.parse(userDistributionInfoPojo.getCreateDateStr() + " 00:00:00");
          userDistributionInfoPojo.setCreateDate(c);
          map.put("createDate", userDistributionInfoPojo.getCreateDate());
        }
        map.put("status", userDistributionInfoPojo.getStatus());
      }
      userDistributionInfoPojos = userDistributionInfoService.userDistributionInfoAll(map);
      if (userDistributionInfoPojos != null) {
        for (UserDistributionInfoPojo u : userDistributionInfoPojos) {
          if (u.getCreateDate() != null) {
            u.setCreateDateStr(sdf.format(u.getCreateDate()));
          }
        }
      }
      JSONArray json = JSONArray.fromObject(userDistributionInfoPojos);
      page.setResult(json.toString());
      page.setRowCount(userDistributionInfoPojos.size());
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 编辑分销商账户信息
   * 
   * @return
   * @throws Exception
   */
  public String userDistributionInfoUpdate() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (userDistributionInfoPojo != null) {
      map.put("id", userDistributionInfoPojo.getId());
    }
    userDistributionInfoPojos = userDistributionInfoService.userDistributionInfoAll(map);
    if (userDistributionInfoPojos != null) {
      userDistributionInfoPojo = userDistributionInfoPojos.get(0);
      if (userDistributionInfoPojo.getCreateDate() != null) {
        userDistributionInfoPojo.setCreateDateStr(sdf.format(userDistributionInfoPojo
            .getCreateDate()));
      }
    }
    return SUCCESS;
  }

  /**
   * 编辑分销商账户信息提交
   * 
   * @return
   */
  public String userDistributionInfoUpdateSub() {
    try {
      if (userDistributionInfoPojo != null) {
        userDistributionInfoService.userDistributionInfoUpdate(userDistributionInfoPojo);
        FileUtil.alertMessageBySkip("编辑成功！", "userDistributionInfo.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("编辑失败！", "userDistributionInfo.do");
    }
    return null;
  }

  /**
   * 审核分销商账户信息
   * 
   * @return
   */
  public String userDistributionInfoCheckById() {
    try {
      if (userDistributionInfoPojo != null) {
        userDistributionInfoService.userDistributionInfoCheckById(userDistributionInfoPojo.getId());
        setResult("1");
      }
    } catch (Exception e) {
      // TODO: handle exception
      setResult("0");
    }
    return SUCCESS;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  private String result;

  /**
   * 批量审核分销商账户信息
   * 
   * @return
   */
  public String userDistributionInfoChecks() {
    try {
      for (String id : tids) {
        userDistributionInfoService.userDistributionInfoCheckById(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("审核成功！", "userDistributionInfo.do");
    } catch (Exception e) {
      // TODO: handle exception
      FileUtil.alertMessageBySkip("审核失败！", "userDistributionInfo.do");
    }
    return null;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  private String[] tids;

  private SysLoginPojo sysLoginPojo;
  private List<SysLoginPojo> sysLoginPojos;

  public SysLoginPojo getSysLoginPojo() {
    return sysLoginPojo;
  }

  public void setSysLoginPojo(SysLoginPojo sysLoginPojo) {
    this.sysLoginPojo = sysLoginPojo;
  }

  public List<SysLoginPojo> getSysLoginPojos() {
    return sysLoginPojos;
  }

  public void setSysLoginPojos(List<SysLoginPojo> sysLoginPojos) {
    this.sysLoginPojos = sysLoginPojos;
  }

  /**
   * 分销商信息管理
   * 
   * @return
   */
  public String userDistributionInfoManage() {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      if (sysLoginPojo != null) {
        map.put("name", sysLoginPojo.getName());
        map.put("loginname", sysLoginPojo.getLoginname());
        // map.put("username", sysLoginPojo.getUsername());
        map.put("inviterName", sysLoginPojo.getInviterName());
      }
      page.setRowCount(userDistributionInfoService.userDistributionInfoManage(map).size());
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 分销商信息管理列表
   * 
   * @return
   */
  public String userDistributionInfoManageAll() {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (sysLoginPojo != null) {
        map.put("name", sysLoginPojo.getName());
        map.put("loginname", sysLoginPojo.getLoginname());
        // map.put("username", sysLoginPojo.getUsername());
        map.put("inviterName", sysLoginPojo.getInviterName());
      }
      sysLoginPojos = userDistributionInfoService.userDistributionInfoManage(map);
      JSONArray json = JSONArray.fromObject(sysLoginPojos);
      page.setResult(json.toString());
      page.setRowCount(sysLoginPojos.size());
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return SUCCESS;
  }


}
