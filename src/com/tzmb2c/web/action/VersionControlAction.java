/*
 * 文 件 名: VersionControlAction.java 创 建 人: admin 创建时间: 2016-12-01
 */
package com.tzmb2c.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.VersionControlPojo;
import com.tzmb2c.web.service.VersionControlService;

public class VersionControlAction extends SuperAction {
  @Autowired
  private VersionControlService versionControlService;
  private VersionControlPojo versionControlPojo;
  private VersionControlPojo versionControlPojo2;
  private Long id;
  private String[] tids;
  private String result;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public VersionControlPojo getVersionControlPojo() {
    return versionControlPojo;
  }

  public void setVersionControlPojo(VersionControlPojo versionControlPojo) {
    this.versionControlPojo = versionControlPojo;
  }

  /**
   * 查询全部条数
   */
  public String goVersionControl() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    try {
      int i = versionControlService.countBy(null);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String versionControlRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      int i = versionControlService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String versionControlList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    List<VersionControlPojo> versionControlList = null;
    try {
      versionControlList = versionControlService.listPage(map);
      JSONArray json = JSONArray.fromObject(versionControlList);
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
  public String goAddVersionControl() throws Exception {
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
    if (user != null && versionControlPojo != null) {
      versionControlPojo.setCreateBy(user.getId());
      versionControlPojo.setCreateDate(new Date());
      versionControlPojo.setUpdateBy(user.getId());
      versionControlPojo.setUpdateDate(new Date());
      try {
        versionControlService.add(versionControlPojo);
        FileUtil.alertMessageBySkip("新增成功！", "goVersionControl.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goAddVersionControl.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goVersionControl.do");
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditVersionControl() throws Exception {
    versionControlPojo = versionControlService.getById(1L);
    versionControlPojo2 = versionControlService.getById(2L);
    ActionContext ac = ActionContext.getContext();
    ac.put("openGeekValid", SellerService.getOpenGeekValid());
    ac.put("openDrawValid", SellerService.getOpenDrawValid());
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
    if (user != null && versionControlPojo != null) {
      versionControlPojo.setUpdateBy(user.getId());
      versionControlPojo.setUpdateDate(new Date());
      try {
        versionControlService.update(versionControlPojo);
        FileUtil.alertMessageBySkip("提交成功！", "goEditVersionControl.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！", "goEditVersionControl.do");
      }
    } else if (user != null && versionControlPojo2 != null) {
      versionControlPojo2.setUpdateBy(user.getId());
      versionControlPojo2.setUpdateDate(new Date());
      try {
        versionControlService.update(versionControlPojo2);
        FileUtil.alertMessageBySkip("提交成功！", "goEditVersionControl.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！", "goEditVersionControl.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goEditVersionControl.do");
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
    result = "0";
    if (user != null && id != null && id > 0) {
      versionControlPojo = new VersionControlPojo();
      versionControlPojo.setId(id);
      versionControlPojo.setUpdateBy(user.getId());
      versionControlPojo.setUpdateDate(new Date());
      try {
        versionControlService.update(versionControlPojo);
        result = "1";
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
    result = "0";
    if (user != null && id != null && id > 0) {
      versionControlPojo = new VersionControlPojo();
      versionControlPojo.setId(id);
      versionControlPojo.setUpdateBy(user.getId());
      versionControlPojo.setUpdateDate(new Date());
      try {
        versionControlService.update(versionControlPojo);
        result = "1";
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
    result = "0";
    if (user != null && id != null && id > 0) {
      try {
        versionControlService.delete(id);
        result = "1";
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
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      versionControlPojo = new VersionControlPojo();
      for (String tid : tids) {
        versionControlPojo.setId(Long.valueOf(tid));
        versionControlPojo.setUpdateBy(user.getId());
        versionControlPojo.setUpdateDate(new Date());
        try {
          versionControlService.update(versionControlPojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
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
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      versionControlPojo = new VersionControlPojo();
      for (String tid : tids) {
        versionControlPojo.setId(Long.valueOf(tid));
        versionControlPojo.setUpdateBy(user.getId());
        versionControlPojo.setUpdateDate(new Date());
        try {
          versionControlService.update(versionControlPojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
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
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      for (String tid : tids) {
        try {
          versionControlService.delete(Long.valueOf(tid));
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
      }
    }
    return SUCCESS;
  }

  public VersionControlPojo getVersionControlPojo2() {
    return versionControlPojo2;
  }

  public void setVersionControlPojo2(VersionControlPojo versionControlPojo2) {
    this.versionControlPojo2 = versionControlPojo2;
  }
}
