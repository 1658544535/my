package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.SysDictService;

public class SysDictAction extends SuperAction {

  @Autowired
  private SysDictService sysDictService;
  private SysDictPojo sysDict;
  private List<SysDictPojo> sysDictList;
  private String result;
  private String[] tids;

  public List<SysDictPojo> getSysDictList() {
    return sysDictList;
  }

  public void setSysDictList(List<SysDictPojo> sysDictList) {
    this.sysDictList = sysDictList;
  }

  public SysDictPojo getSysDict() {
    return sysDict;
  }

  public void setSysDict(SysDictPojo sysDict) {
    this.sysDict = sysDict;
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

  public String getSysDictCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(sysDictService.sysDictAllCount(sysDict));
    return SUCCESS;
  }

  public String sysDictAllList() {
    if (page == null) {
      page = new Pager();
    }
    JSONArray json = JSONArray.fromObject(sysDictService.sysDictAllList(sysDict, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String sysDictDeleteId() {
    if (tids != null) {
      sysDictService.sysDictDeleteId(tids);
      // FileUtil.alertMessageBySkip("删除成功", "gomanageTicketRule.do");
      FileUtil.alertMessageBySkip("删除成功！", "sysDict.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "sysDict.do");
    }

    return null;
  }

  public String deleSysDict() throws SQLException {
    try {
      sysDictService.delSysDict(sysDict.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  public String goFindSysDict() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("sysDictPojo", sysDictService.findSysDictById(sysDict.getId()));
    ac.put("status", sysDictService.getSysDictListByType("sys_dict_status"));
    return SUCCESS;
  }

  public String updateSysDict() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      sysDict.preUpdate(loginPojo);
    }

    sysDictService.updateSysDict(sysDict);
    FileUtil.alertMessageBySkip("修改成功！", "sysDict.do");

    return null;
  }

  public String addSysDict() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("status", sysDictService.getSysDictListByType("sys_dict_status"));
    return SUCCESS;
  }

  public String insertSysDict() throws Exception {


    if (sysDictService.getSysDictByTypeAndValue(sysDict) != null) {
      FileUtil.alertMessageBySkip("新增失败！", "sysDict.do");
    } else {
      SysLoginPojo loginPojo = UserUtil.getUser();
      if (UserUtil.getUser() == null) {
        return "loginpage";
      } else {
        sysDict.prePersist(loginPojo);
      }
      sysDictService.insertSysDict(sysDict);
      FileUtil.alertMessageBySkip("新增成功！", "sysDict.do");
    }

    return null;
  }

  public String getSysDictListByType() throws Exception {
    if (sysDict != null) {
      sysDictList = sysDictService.getSysDictListByType(sysDict.getType());
    }
    JSONArray json = JSONArray.fromObject(sysDictList);
    if (page == null) {
      page = new Pager();
    }
    page.setResult(json.toString());
    return SUCCESS;
  }
}
