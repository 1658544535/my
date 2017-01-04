/*
 * 文 件 名: GroupFreeCouponSettingAction.java 创 建 人: admin 创建时间: 2016-09-23
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
import com.tzmb2c.web.pojo.GroupFreeCouponSettingPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.GroupFreeCouponSettingService;

public class GroupFreeCouponSettingAction extends SuperAction {
  @Autowired
  private GroupFreeCouponSettingService groupFreeCouponSettingService;
  private GroupFreeCouponSettingPojo groupFreeCouponSettingPojo;
  private Long id;
  private String[] tids;
  private String result;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public GroupFreeCouponSettingPojo getGroupFreeCouponSettingPojo() {
    return groupFreeCouponSettingPojo;
  }

  public void setGroupFreeCouponSettingPojo(GroupFreeCouponSettingPojo groupFreeCouponSettingPojo) {
    this.groupFreeCouponSettingPojo = groupFreeCouponSettingPojo;
  }

  /**
   * 查询全部条数
   */
  public String goGroupFreeCouponSetting() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      int i = groupFreeCouponSettingService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String groupFreeCouponSettingRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      int i = groupFreeCouponSettingService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String groupFreeCouponSettingList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    List<GroupFreeCouponSettingPojo> groupFreeCouponSettingList = null;
    try {
      groupFreeCouponSettingList = groupFreeCouponSettingService.listPage(map);
      JSONArray json = JSONArray.fromObject(groupFreeCouponSettingList);
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
  public String goAddGroupFreeCouponSetting() throws Exception {
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
    if (user != null && groupFreeCouponSettingPojo != null) {
      groupFreeCouponSettingPojo.setCreateBy(user.getId());
      // groupFreeCouponSettingPojo.setCreateDate(new Date());
      groupFreeCouponSettingPojo.setUpdateBy(user.getId());
      // groupFreeCouponSettingPojo.setUpdateDate(new Date());
      try {
        groupFreeCouponSettingPojo.setStatus(1);
        groupFreeCouponSettingPojo.setSurplusNum(groupFreeCouponSettingPojo.getNum());
        groupFreeCouponSettingService.add(groupFreeCouponSettingPojo);
        FileUtil.alertMessageBySkip("新增成功！", "goGroupFreeCouponSetting.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goAddGroupFreeCouponSetting.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goGroupFreeCouponSetting.do");
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditGroupFreeCouponSetting() throws Exception {
    if (id != null && id > 0) {
      groupFreeCouponSettingPojo = groupFreeCouponSettingService.getById(id);
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
    if (user != null && groupFreeCouponSettingPojo != null) {
      groupFreeCouponSettingPojo.setUpdateBy(user.getId());
      // groupFreeCouponSettingPojo.setUpdateDate(new Date());
      try {
        groupFreeCouponSettingService.update(groupFreeCouponSettingPojo);
        FileUtil.alertMessageBySkip("提交成功！", "goGroupFreeCouponSetting.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！", "goEditGroupFreeCouponSetting.do?id="
            + groupFreeCouponSettingPojo.getId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goGroupFreeCouponSetting.do");
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
    if (user != null && id != null && id > 0) {
      groupFreeCouponSettingPojo = new GroupFreeCouponSettingPojo();
      groupFreeCouponSettingPojo.setId(id);
      groupFreeCouponSettingPojo.setStatus(1);
      groupFreeCouponSettingPojo.setUpdateBy(user.getId());
      groupFreeCouponSettingPojo.setUpdateDate(new Date());
      try {
        groupFreeCouponSettingService.update(groupFreeCouponSettingPojo);
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
    if (user != null && id != null && id > 0) {
      groupFreeCouponSettingPojo = new GroupFreeCouponSettingPojo();
      groupFreeCouponSettingPojo.setId(id);
      groupFreeCouponSettingPojo.setStatus(0);
      groupFreeCouponSettingPojo.setUpdateBy(user.getId());
      groupFreeCouponSettingPojo.setUpdateDate(new Date());
      try {
        groupFreeCouponSettingService.update(groupFreeCouponSettingPojo);
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
    if (user != null && id != null && id > 0) {
      try {
        groupFreeCouponSettingService.delete(id);
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
      groupFreeCouponSettingPojo = new GroupFreeCouponSettingPojo();
      groupFreeCouponSettingPojo.setStatus(1);
      for (String tid : tids) {
        groupFreeCouponSettingPojo.setId(Long.valueOf(tid));
        groupFreeCouponSettingPojo.setUpdateBy(user.getId());
        groupFreeCouponSettingPojo.setUpdateDate(new Date());
        try {
          groupFreeCouponSettingService.update(groupFreeCouponSettingPojo);
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
      groupFreeCouponSettingPojo = new GroupFreeCouponSettingPojo();
      groupFreeCouponSettingPojo.setStatus(0);
      for (String tid : tids) {
        groupFreeCouponSettingPojo.setId(Long.valueOf(tid));
        groupFreeCouponSettingPojo.setUpdateBy(user.getId());
        groupFreeCouponSettingPojo.setUpdateDate(new Date());
        try {
          groupFreeCouponSettingService.update(groupFreeCouponSettingPojo);
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
          groupFreeCouponSettingService.delete(Long.valueOf(tid));
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
