package com.tzmb2c.web.action;

import java.sql.SQLException;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.SysRolePojo;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysRoleService;

public class SysRoleAction extends SuperAction {

  @Autowired
  private SysRoleService sysRoleService;
  @Autowired
  private SysDictService sysDictService;
  private SysRolePojo sysRole;
  private String result;
  private String[] tids;

  public SysRolePojo getSysRole() {
    return sysRole;
  }

  public void setSysRole(SysRolePojo sysRole) {
    this.sysRole = sysRole;
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

  public String getSysRoleCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(sysRoleService.sysRoleAllCount(sysRole));
    return SUCCESS;
  }

  public String sysRoleAllList() {
    JSONArray json = JSONArray.fromObject(sysRoleService.sysRoleAllList(sysRole, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String sysRoleDeleteId() {
    if (tids != null) {
      sysRoleService.sysRoleDeleteId(tids);
      // FileUtil.alertMessageBySkip("删除成功", "gomanageTicketRule.do");
      FileUtil.alertMessageBySkip("删除成功！", "sysRole.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "sysRole.do");
    }

    return null;
  }

  public String deleSysRole() throws SQLException {
    try {
      sysRoleService.delSysRole(sysRole.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String goFindSysRole() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("sysRolePojo", sysRoleService.findSysRoleById(sysRole.getId()));
    ac.put("status", sysDictService.getSysDictListByType("sys_role_status"));
    return SUCCESS;
  }

  public String updateSysRole() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      sysRole.preUpdate(loginPojo);
    }
    sysRoleService.updateSysRole(sysRole);
    FileUtil.alertMessageBySkip("修改成功！", "sysRole.do");

    return null;
  }

  public String addSysRole() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("status", sysDictService.getSysDictListByType("sys_role_status"));
    return SUCCESS;
  }

  public String insertSysRole() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      sysRole.prePersist(loginPojo);
    }
    sysRoleService.insertSysRole(sysRole);
    FileUtil.alertMessageBySkip("新增成功！", "sysRole.do");

    return null;
  }
}
