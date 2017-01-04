/*
 * 文 件 名: UserRedeemCodeAction.java 创 建 人: admin 创建时间: 2016-12-27
 */
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
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserRedeemCodePojo;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserRedeemCodeService;

public class UserRedeemCodeAction extends SuperAction {
  @Autowired
  private UserRedeemCodeService userRedeemCodeService;
  @Autowired
  private SysLoginService sysLoginService;
  private UserRedeemCodePojo userRedeemCodePojo;
  private SysLoginPojo sysLoginPojo;
  private Long id;
  private String code;
  private String[] tids;
  private String result;
  private String keywords;
  private Integer status, used;
  private Date genTime, useTime;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String[] getTids() {
    return this.tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getResult() {
    return this.result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Integer getUsed() {
    return used;
  }

  public void setUsed(Integer used) {
    this.used = used;
  }

  public Date getGenTime() {
    return genTime;
  }

  public void setGenTime(Date genTime) {
    this.genTime = genTime;
  }

  public Date getUseTime() {
    return useTime;
  }

  public void setUseTime(Date useTime) {
    this.useTime = useTime;
  }

  public UserRedeemCodePojo getUserRedeemCodePojo() {
    return userRedeemCodePojo;
  }

  public void setUserRedeemCodePojo(UserRedeemCodePojo userRedeemCodePojo) {
    this.userRedeemCodePojo = userRedeemCodePojo;
  }

  public SysLoginPojo getSysLoginPojo() {
    return sysLoginPojo;
  }

  public void setSysLoginPojo(SysLoginPojo sysLoginPojo) {
    this.sysLoginPojo = sysLoginPojo;
  }

  /**
   * 查询全部条数
   */
  public String goUserRedeemCode() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    try {
      int i = userRedeemCodeService.countBy(null);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String userRedeemCodeRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (status != null) {
      map.put("status", status);
    }
    if (used != null) {
      map.put("used", used);
    }
    if (keywords != null) {
      map.put("keywords", keywords);
    }
    if (genTime != null) {
      map.put("genTime", genTime);
    }
    if (useTime != null) {
      map.put("useTime", useTime);
    }
    try {
      int i = userRedeemCodeService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String userRedeemCodeList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (status != null) {
      map.put("status", status);
    }
    if (used != null) {
      map.put("used", used);
    }
    if (keywords != null) {
      map.put("keywords", keywords);
    }
    if (genTime != null) {
      map.put("genTime", genTime);
    }
    if (useTime != null) {
      map.put("useTime", useTime);
    }
    List<UserRedeemCodePojo> userRedeemCodeList = null;
    try {
      map.put("orderBy", " urc.gen_time desc");
      userRedeemCodeList = userRedeemCodeService.listPage(map);
      JSONArray json = JSONArray.fromObject(userRedeemCodeList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddUserRedeemCode() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Throwable
   */
  public String add() throws Throwable {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && userRedeemCodePojo != null) {
      try {
        for (int i = 1; i <= userRedeemCodePojo.getNum(); i++) {
          userRedeemCodePojo.setGenTime(new Date());
          userRedeemCodePojo.setCode(StringUtil.getRedeemCode());
          userRedeemCodeService.add(userRedeemCodePojo);
        }
        FileUtil.alertMessageBySkip("新增成功！", "goUserRedeemCode.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goAddUserRedeemCode.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goUserRedeemCode.do");
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditUserRedeemCode() throws Exception {
    if (code != null) {
      userRedeemCodePojo = userRedeemCodeService.getByCode(code);
      if (userRedeemCodePojo != null) {
        sysLoginPojo = sysLoginService.findSysLoginById(userRedeemCodePojo.getUserId());
        if (sysLoginPojo != null) {
          userRedeemCodePojo.setLoginname(sysLoginPojo.getLoginname());
        } else {
          userRedeemCodePojo.setLoginname("");
        }
      }
    }
    return SUCCESS;
  }

  /**
   * 编辑提交
   * 
   * @return
   * @throws Throwable
   */
  public String update() throws Throwable {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && userRedeemCodePojo != null) {
      // 获取用户id
      sysLoginPojo = sysLoginService.getSysLoginByLoginName(userRedeemCodePojo.getLoginname());
      if (sysLoginPojo != null) {
        userRedeemCodePojo.setUserId(sysLoginPojo.getId());
      } else {
        FileUtil.alertMessageBySkip("用户名不存在！请输入正确用户名。", "goEditUserRedeemCode.do?code="
            + userRedeemCodePojo.getCode());
      }
      try {
        userRedeemCodeService.update(userRedeemCodePojo);
        FileUtil.alertMessageBySkip("提交成功！", "goUserRedeemCode.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！",
            "goEditUserRedeemCode.do?code=" + userRedeemCodePojo.getCode());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goUserRedeemCode.do");
    }

    return null;
  }

  /**
   * 根据id审核
   * 
   * @return
   */
  public String check() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && code != null) {
      userRedeemCodePojo = new UserRedeemCodePojo();
      userRedeemCodePojo.setStatus(1);
      userRedeemCodePojo.setCode(code);
      try {
        userRedeemCodeService.update(userRedeemCodePojo);
        this.result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheck() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && code != null) {
      userRedeemCodePojo = new UserRedeemCodePojo();
      userRedeemCodePojo.setStatus(0);
      userRedeemCodePojo.setCode(code);
      try {
        userRedeemCodeService.update(userRedeemCodePojo);
        this.result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  public String delete() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && code != null) {
      try {
        userRedeemCodeService.delete(code);
        this.result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 审核选中
   * 
   * @return
   */
  public String checkAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      userRedeemCodePojo = new UserRedeemCodePojo();
      userRedeemCodePojo.setStatus(1);
      for (String tid : tids) {
        userRedeemCodePojo.setCode(tid);
        try {
          userRedeemCodeService.update(userRedeemCodePojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        this.result = "2";
      } else {
        this.result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 选中取消审核
   * 
   * @return
   */
  public String uncheckAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      userRedeemCodePojo = new UserRedeemCodePojo();
      userRedeemCodePojo.setStatus(0);
      for (String tid : tids) {
        userRedeemCodePojo.setCode(tid);
        try {
          userRedeemCodeService.update(userRedeemCodePojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        this.result = "2";
      } else {
        this.result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 删除选中
   * 
   * @return
   */
  public String deleteAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      for (String tid : tids) {
        try {
          userRedeemCodeService.delete(String.valueOf(tid));
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        this.result = "2";
      } else {
        this.result = "1";
      }
    }
    return SUCCESS;
  }
}
