/*
 * 文 件 名: GroupFreeCouponAction.java 创 建 人: admin 创建时间: 2016-09-23
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
import com.tzmb2c.web.pojo.GroupFreeCouponPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.GroupFreeCouponService;

public class GroupFreeCouponAction extends SuperAction {
  @Autowired
  private GroupFreeCouponService groupFreeCouponService;
  private GroupFreeCouponPojo groupFreeCouponPojo;
  private Long id;
  private String[] tids;
  private String result;
  private Long num;
  private Long startUserId;
  private Long endUserId;
  private String userIds;

  public String getUserIds() {
    return userIds;
  }

  public void setUserIds(String userIds) {
    this.userIds = userIds;
  }

  public Long getStartUserId() {
    return startUserId;
  }

  public void setStartUserId(Long startUserId) {
    this.startUserId = startUserId;
  }

  public Long getEndUserId() {
    return endUserId;
  }

  public void setEndUserId(Long endUserId) {
    this.endUserId = endUserId;
  }

  public Long getNum() {
    return num;
  }

  public void setNum(Long num) {
    this.num = num;
  }

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

  public GroupFreeCouponPojo getGroupFreeCouponPojo() {
    return groupFreeCouponPojo;
  }

  public void setGroupFreeCouponPojo(GroupFreeCouponPojo groupFreeCouponPojo) {
    this.groupFreeCouponPojo = groupFreeCouponPojo;
  }

  /**
   * 查询全部条数
   */
  public String goGroupFreeCoupon() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      if (groupFreeCouponPojo != null) {
        map.put("used", groupFreeCouponPojo.getUsed());
        map.put("userName",
            groupFreeCouponPojo.getUserName().length() > 0 ? groupFreeCouponPojo.getUserName()
                : null);
      }
      map.put("status", 1);
      int i = groupFreeCouponService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String groupFreeCouponRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      if (groupFreeCouponPojo != null) {
        map.put("used", groupFreeCouponPojo.getUsed());
        map.put("userName",
            groupFreeCouponPojo.getUserName().length() > 0 ? groupFreeCouponPojo.getUserName()
                : null);
      }
      map.put("status", 1);
      int i = groupFreeCouponService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String groupFreeCouponList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    List<GroupFreeCouponPojo> groupFreeCouponList = null;
    try {
      if (groupFreeCouponPojo != null) {
        map.put("used", groupFreeCouponPojo.getUsed());
        map.put("userName",
            groupFreeCouponPojo.getUserName().length() > 0 ? groupFreeCouponPojo.getUserName()
                : null);
      }
      map.put("status", 1);
      groupFreeCouponList = groupFreeCouponService.listPage(map);
      JSONArray json = JSONArray.fromObject(groupFreeCouponList);
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
  public String goAddGroupFreeCoupon() throws Exception {
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
    if (user != null && groupFreeCouponPojo != null) {
      groupFreeCouponPojo.setCreateBy(user.getId());
      // groupFreeCouponPojo.setCreateDate(new Date());
      groupFreeCouponPojo.setUpdateBy(user.getId());
      // groupFreeCouponPojo.setUpdateDate(new Date());
      try {
        if (startUserId != null && startUserId > 0 && endUserId != null && endUserId > 0) {
          for (long i = startUserId; i <= endUserId; i++) {
            if (num != null && num > 0) {
              for (int j = 0; j < num; j++) {
                // groupFreeCouponPojo.setId(null);
                // groupFreeCouponPojo.setUserId(i);
                // groupFreeCouponPojo.setSource(2);// 后台发放
                // groupFreeCouponService.add(groupFreeCouponPojo);
                groupFreeCouponPojo.setUserId(i);
                groupFreeCouponPojo.setStatus(1);;// 激活
                groupFreeCouponPojo.setCurrentTime("当前时间");
                groupFreeCouponPojo.setUsed(0);
                groupFreeCouponPojo.setInvitationUserId(0l);
                groupFreeCouponService.update(groupFreeCouponPojo);
              }
            }
          }
        } else if (userIds != null && !"".equals(userIds)) {
          String[] uids = userIds.split(",");
          for (int i = 0; i < uids.length; i++) {
            if (num != null && num > 0 && uids[i].length() > 0) {
              for (int j = 0; j < num; j++) {
                // groupFreeCouponPojo.setId(null);
                // groupFreeCouponPojo.setUserId(new Long(uids[i]));
                // groupFreeCouponPojo.setSource(2);// 后台发放
                // groupFreeCouponService.add(groupFreeCouponPojo);
                groupFreeCouponPojo.setUserId(new Long(uids[i]));
                groupFreeCouponPojo.setStatus(1);;// 激活
                groupFreeCouponPojo.setCurrentTime("当前时间");
                groupFreeCouponPojo.setUsed(0);
                groupFreeCouponPojo.setInvitationUserId(0l);
                groupFreeCouponService.update(groupFreeCouponPojo);
              }
            }
          }
        } else {
          FileUtil.alertMessageBySkip("激活失败！", "goAddGroupFreeCoupon.do");
          return null;
        }
        FileUtil.alertMessageBySkip("激活成功！", "goGroupFreeCoupon.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("激活失败！", "goAddGroupFreeCoupon.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goGroupFreeCoupon.do");
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditGroupFreeCoupon() throws Exception {
    if (id != null && id > 0) {
      groupFreeCouponPojo = groupFreeCouponService.getById(id);
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
    if (user != null && groupFreeCouponPojo != null) {
      groupFreeCouponPojo.setUpdateBy(user.getId());
      groupFreeCouponPojo.setUpdateDate(new Date());
      try {
        groupFreeCouponService.update(groupFreeCouponPojo);
        FileUtil.alertMessageBySkip("编辑成功！", "goGroupFreeCoupon.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("编辑失败！",
            "goEditGroupFreeCoupon.do?id=" + groupFreeCouponPojo.getId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goGroupFreeCoupon.do");
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
      groupFreeCouponPojo = new GroupFreeCouponPojo();
      groupFreeCouponPojo.setId(id);
      groupFreeCouponPojo.setStatus(1);
      groupFreeCouponPojo.setUpdateBy(user.getId());
      groupFreeCouponPojo.setUpdateDate(new Date());
      try {
        groupFreeCouponService.update(groupFreeCouponPojo);
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
      groupFreeCouponPojo = new GroupFreeCouponPojo();
      groupFreeCouponPojo.setId(id);
      groupFreeCouponPojo.setStatus(0);
      groupFreeCouponPojo.setUpdateBy(user.getId());
      groupFreeCouponPojo.setUpdateDate(new Date());
      try {
        groupFreeCouponService.update(groupFreeCouponPojo);
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
        groupFreeCouponService.delete(id);
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
      groupFreeCouponPojo = new GroupFreeCouponPojo();
      groupFreeCouponPojo.setStatus(1);
      for (String tid : tids) {
        groupFreeCouponPojo.setId(Long.valueOf(tid));
        groupFreeCouponPojo.setUpdateBy(user.getId());
        groupFreeCouponPojo.setUpdateDate(new Date());
        try {
          groupFreeCouponService.update(groupFreeCouponPojo);
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
      groupFreeCouponPojo = new GroupFreeCouponPojo();
      groupFreeCouponPojo.setStatus(0);
      for (String tid : tids) {
        groupFreeCouponPojo.setId(Long.valueOf(tid));
        groupFreeCouponPojo.setUpdateBy(user.getId());
        groupFreeCouponPojo.setUpdateDate(new Date());
        try {
          groupFreeCouponService.update(groupFreeCouponPojo);
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
          groupFreeCouponService.delete(Long.valueOf(tid));
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
