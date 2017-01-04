package com.tzmb2c.web.action;

import java.sql.SQLException;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.SysUserRolePojo;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.SysRoleService;
import com.tzmb2c.web.service.SysUserRoleService;

public class SysUserRoleAction extends SuperAction {

  @Autowired
  private SysUserRoleService sysUserRoleService;
  @Autowired
  private SysRoleService sysRoleService;
  @Autowired
  private SysLoginService sysLoginService;
  private SysUserRolePojo sysUserRole;
  private String result;
  private String[] tids;

  public SysUserRolePojo getSysUserRole() {
    return sysUserRole;
  }

  public void setSysUserRole(SysUserRolePojo sysUserRole) {
    this.sysUserRole = sysUserRole;
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

  public String getSysUserRoleCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(sysUserRoleService.sysUserRoleAllCount(sysUserRole));
    return SUCCESS;
  }

  public String sysUserRoleAllList() {
    JSONArray json = JSONArray.fromObject(sysUserRoleService.sysUserRoleAllList(sysUserRole, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String sysUserRoleDeleteId() {
    if (tids != null) {
      sysUserRoleService.sysUserRoleDeleteId(tids);
      // FileUtil.alertMessageBySkip("删除成功", "gomanageTicketRule.do");
      FileUtil.alertMessageBySkip("删除成功！", "sysUserRole.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "sysUserRole.do");
    }

    return null;
  }

  public String deleSysUserRole() throws SQLException {
    try {
      sysUserRoleService.delSysUserRole(sysUserRole.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String goFindSysUserRole() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("sysUserRolePojo", sysUserRoleService.findSysUserRoleById(sysUserRole.getId()));
    ac.put("sysRolePojo", sysRoleService.sysRoleAllService());
    ac.put("sysLoginPojo", sysLoginService.sysLoginAllService());
    return SUCCESS;
  }

  public String updateSysUserRole() throws Exception {

    sysUserRoleService.updateSysUserRole(sysUserRole);
    FileUtil.alertMessageBySkip("修改成功！", "sysUserRole.do");

    return null;
  }

  public String addSysUserRole() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("sysRolePojo", sysRoleService.sysRoleAllService());
    ac.put("sysLoginPojo", sysLoginService.sysLoginAllService());
    return SUCCESS;
  }

  public String insertSysUserRole() throws Exception {

    sysUserRoleService.insertSysUserRole(sysUserRole);
    FileUtil.alertMessageBySkip("新增成功！", "sysUserRole.do");

    return null;
  }
}
