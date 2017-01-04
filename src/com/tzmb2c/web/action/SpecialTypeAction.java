/*
 * 文 件 名: SpecialTypeAction.java 创 建 人: admin 创建时间: 2016-10-14
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
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.SpecialTypePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.SpecialTypeService;

public class SpecialTypeAction extends SuperAction {
  @Autowired
  private SpecialTypeService specialTypeService;
  private SpecialTypePojo specialTypePojo;
  private Long id;
  private String[] tids;
  private String result;
  private Integer statusDisplay;

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

  public Integer getStatusDisplay() {
    return statusDisplay;
  }

  public void setStatusDisplay(Integer statusDisplay) {
    this.statusDisplay = statusDisplay;
  }

  public SpecialTypePojo getSpecialTypePojo() {
    return specialTypePojo;
  }

  public void setSpecialTypePojo(SpecialTypePojo specialTypePojo) {
    this.specialTypePojo = specialTypePojo;
  }

  /**
   * 查询全部条数
   */
  public String goSpecialType() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      map.put("status", specialTypePojo.getStatus());
      map.put("statusDisplay", specialTypePojo.getStatusDisplay());
      int i = specialTypeService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String specialTypeRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      map.put("status", specialTypePojo.getStatus());
      map.put("statusDisplay", specialTypePojo.getStatusDisplay());
      int i = specialTypeService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String specialTypeList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("orderBy", "sorting desc");
    map.put("status", specialTypePojo.getStatus());
    map.put("statusDisplay", specialTypePojo.getStatusDisplay());
    List<SpecialTypePojo> specialTypeList = null;
    try {
      specialTypeList = specialTypeService.listPage(map);
      JSONArray json = JSONArray.fromObject(specialTypeList);
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
  public String goAddSpecialType() throws Exception {
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
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", specialTypePojo.getName());
    int i = specialTypeService.countBy(map);
    if (i > 0) {
      FileUtil.alertMessageBySkip("该分类名称已存在！", "goAddSpecialType.do");
      return null;
    }
    if (user != null && specialTypePojo != null) {
      specialTypePojo.setCreateBy(user.getId());
      specialTypePojo.setCreateDate(new Date());
      specialTypePojo.setUpdateBy(user.getId());
      specialTypePojo.setUpdateDate(new Date());
      try {
        specialTypeService.add(specialTypePojo);
        FileUtil.alertMessageBySkip("新增成功！", "goSpecialType.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goAddSpecialType.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goSpecialType.do");
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditSpecialType() throws Exception {
    if (id != null && id > 0) {
      specialTypePojo = specialTypeService.getById(id);
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
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", specialTypePojo.getName());
    List<SpecialTypePojo> specialTypePojos = specialTypeService.listPage(map);
    if (specialTypePojos.size() > 0 && specialTypePojos.get(0).getId() != specialTypePojo.getId()) {
      FileUtil
          .alertMessageBySkip("该分类名称已存在！", "goEditSpecialType.do?id=" + specialTypePojo.getId());
      return null;
    }
    if (user != null && specialTypePojo != null) {
      specialTypePojo.setUpdateBy(user.getId());
      specialTypePojo.setUpdateDate(new Date());
      try {
        specialTypeService.update(specialTypePojo);
        FileUtil.alertMessageBySkip("提交成功！", "goSpecialType.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！", "goEditSpecialType.do?id=" + specialTypePojo.getId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goSpecialType.do");
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
      specialTypePojo = new SpecialTypePojo();
      specialTypePojo.setId(id);
      specialTypePojo.setStatus(1);
      specialTypePojo.setUpdateBy(user.getId());
      specialTypePojo.setUpdateDate(new Date());
      try {
        specialTypeService.update(specialTypePojo);
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
      specialTypePojo = new SpecialTypePojo();
      specialTypePojo.setId(id);
      specialTypePojo.setStatus(0);
      specialTypePojo.setUpdateBy(user.getId());
      specialTypePojo.setUpdateDate(new Date());
      try {
        specialTypeService.update(specialTypePojo);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }
  
  /**
   * 根据id显示隐藏
   * 
   * @return
   */
  public String updateStatusDisplay() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && id != null && id > 0) {
      specialTypePojo = new SpecialTypePojo();
      specialTypePojo.setId(id);
      specialTypePojo.setStatusDisplay(statusDisplay);
      try {
        specialTypeService.update(specialTypePojo);
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
        specialTypeService.delete(id);
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
      specialTypePojo = new SpecialTypePojo();
      for (String tid : tids) {
        specialTypePojo.setId(Long.valueOf(tid));
        specialTypePojo.setUpdateBy(user.getId());
        specialTypePojo.setUpdateDate(new Date());
        try {
          specialTypeService.update(specialTypePojo);
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
      specialTypePojo = new SpecialTypePojo();
      for (String tid : tids) {
        specialTypePojo.setId(Long.valueOf(tid));
        specialTypePojo.setUpdateBy(user.getId());
        specialTypePojo.setUpdateDate(new Date());
        try {
          specialTypeService.update(specialTypePojo);
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
          specialTypeService.delete(Long.valueOf(tid));
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
}
