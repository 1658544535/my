/*
 * 文 件 名: SeckillGoodsAction.java 创 建 人: admin 创建时间: 2016-10-26
 */
package com.tzmb2c.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tencent.common.Util;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.GrouponActivityPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;
import com.tzmb2c.web.pojo.SeckillGoodsPojo;
import com.tzmb2c.web.pojo.SeckillPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.GrouponUserRecordService;
import com.tzmb2c.web.service.SeckillGoodsService;
import com.tzmb2c.web.service.SeckillService;

public class SeckillGoodsAction extends SuperAction {
  @Autowired
  private SeckillGoodsService seckillGoodsService;
  @Autowired
  private GrouponActivityService grouponActivityService;
  @Autowired
  private SeckillService seckillService;
  @Autowired
  private GrouponUserRecordService grouponUserRecordService;
  private GrouponUserRecordPojo grouponUserRecordPojo;
  private SeckillGoodsPojo seckillGoodsPojo;
  private SeckillPojo seckillPojo;
  private GrouponActivityPojo grouponActivityPojo;
  private Long id;
  private String[] tids;
  private String result;
  private Long activityId;
  private String keywords;
  private Date beginTime;
  private Date endTime;
  /**
   * 活动id
   */
  private Long groActId;
  private Integer isHome;


  public Integer getIsHome() {
    return isHome;
  }

  public void setIsHome(Integer isHome) {
    this.isHome = isHome;
  }

  public Date getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(Date beginTime) {
    this.beginTime = beginTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Long getGroActId() {
    return groActId;
  }

  public void setGroActId(Long groActId) {
    this.groActId = groActId;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

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

  public SeckillGoodsPojo getSeckillGoodsPojo() {
    return seckillGoodsPojo;
  }

  public void setSeckillGoodsPojo(SeckillGoodsPojo seckillGoodsPojo) {
    this.seckillGoodsPojo = seckillGoodsPojo;
  }


  public GrouponUserRecordPojo getGrouponUserRecordPojo() {
    return grouponUserRecordPojo;
  }

  public void setGrouponUserRecordPojo(GrouponUserRecordPojo grouponUserRecordPojo) {
    this.grouponUserRecordPojo = grouponUserRecordPojo;
  }

  public GrouponActivityPojo getGrouponActivityPojo() {
    return grouponActivityPojo;
  }

  public void setGrouponActivityPojo(GrouponActivityPojo grouponActivityPojo) {
    this.grouponActivityPojo = grouponActivityPojo;
  }

  public SeckillPojo getSeckillPojo() {
    return seckillPojo;
  }

  public void setSeckillPojo(SeckillPojo seckillPojo) {
    this.seckillPojo = seckillPojo;
  }

  /**
   * 查询全部条数
   */
  public String goSeckillGoods() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("seckillId", seckillGoodsPojo.getSeckillId());
    try {
      GrouponActivityPojo grouponActivity = new GrouponActivityPojo();
      Date date = new java.util.Date();
      grouponActivity.setCurrentTime(StringUtil.dateString(date));
      grouponActivity.setActivityStatus(1);
      grouponActivityService.update(grouponActivity);
      grouponActivity = new GrouponActivityPojo();
      grouponActivity.setCurrentTime2(StringUtil.dateString(date));
      grouponActivity.setActivityStatus(2);
      grouponActivityService.update(grouponActivity);
      int i = seckillGoodsService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String seckillGoodsList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("orderBy", "sg.sorting desc,sg.id desc");
    map.put("seckillId", seckillGoodsPojo.getSeckillId());
    List<SeckillGoodsPojo> seckillGoodsList = null;
    try {
      seckillGoodsList = seckillGoodsService.listPage(map);
      JSONArray json = JSONArray.fromObject(seckillGoodsList);
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
  public String goAddSeckillGoods() throws Exception {
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
    map.put("seckillId", seckillGoodsPojo.getSeckillId());
    map.put("productId", seckillGoodsPojo.getProductId());
    int i = seckillGoodsService.countBy(map);
    if (i > 0) {
      FileUtil.alertMessageBySkip(
          "该商品已添加过！",
          "goGrouponActivityProduct.do?seckill=1&seckillGoodsPojo.seckillId="
              + seckillGoodsPojo.getSeckillId());
      return null;
    }
    if (user != null && seckillGoodsPojo != null) {
      grouponActivityPojo = grouponActivityService.getById(seckillGoodsPojo.getActivityId());
      seckillPojo = seckillService.getById(seckillGoodsPojo.getSeckillId());
      GrouponActivityPojo grouponActivity = new GrouponActivityPojo();
      grouponActivity.setProductId(grouponActivityPojo.getProductId());
      grouponActivity.setBeginTime(seckillPojo.getBeginTime());
      grouponActivity.setEndTime(seckillPojo.getEndTime());
      grouponActivity.setNum(grouponActivityPojo.getNum());
      grouponActivity.setPrice(grouponActivityPojo.getPrice());
      grouponActivity.setIsDefault(0);
      grouponActivity.setStatus(1);
      grouponActivity.setActivityStatus(0);
      grouponActivity.setType(6);
      grouponActivity.setSorting(grouponActivityPojo.getSorting());
      grouponActivity.setTitle(grouponActivityPojo.getTitle());
      grouponActivity.setBanner(grouponActivityPojo.getBanner());
      grouponActivity.setIsDelete(0);
      grouponActivity.setCreateBy(user.getId());
      grouponActivity.setCreateDate(new Date());
      grouponActivity.setUpdateBy(user.getId());
      grouponActivity.setUpdateDate(new Date());
      grouponActivity.setLimitNum(grouponActivityPojo.getLimitNum());
      grouponActivity.setSurplusNum(grouponActivityPojo.getSurplusNum());
      grouponActivityService.add(grouponActivity);
      seckillGoodsPojo.setActivityId(grouponActivity.getId());
      seckillGoodsPojo.setCreateBy(user.getId());
      seckillGoodsPojo.setCreateDate(new Date());
      seckillGoodsPojo.setUpdateBy(user.getId());
      seckillGoodsPojo.setUpdateDate(new Date());
      seckillGoodsPojo.setStatus(1);
      seckillGoodsPojo.setSorting(grouponActivity.getSorting());
      try {
        seckillGoodsService.add(seckillGoodsPojo);
        FileUtil.alertMessageBySkip("新增成功！", "goSeckillGoods.do?seckillGoodsPojo.seckillId="
            + seckillGoodsPojo.getSeckillId());
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip(
            "新增失败！",
            "goGrouponActivityProduct.do?seckill=1&seckillGoodsPojo.seckillId="
                + seckillGoodsPojo.getSeckillId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goSeckillGoods.do?seckillGoodsPojo.seckillId="
          + seckillGoodsPojo.getSeckillId());
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditSeckillGoods() throws Exception {
    if (id != null && id > 0) {
      seckillGoodsPojo = seckillGoodsService.getById(id);
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
    if (user != null && seckillGoodsPojo != null) {
      seckillGoodsPojo.setUpdateBy(user.getId());
      seckillGoodsPojo.setUpdateDate(new Date());
      try {
        seckillGoodsService.update(seckillGoodsPojo);
        FileUtil.alertMessageBySkip("提交成功！", "goSeckillGoods.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil
            .alertMessageBySkip("提交失败！", "goEditSeckillGoods.do?id=" + seckillGoodsPojo.getId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goSeckillGoods.do");
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
      seckillGoodsPojo = new SeckillGoodsPojo();
      seckillGoodsPojo.setId(id);
      seckillGoodsPojo.setStatus(1);
      seckillGoodsPojo.setUpdateBy(user.getId());
      seckillGoodsPojo.setUpdateDate(new Date());
      GrouponActivityPojo grouponActivity = new GrouponActivityPojo();
      grouponActivity.setId(activityId);
      grouponActivity.setStatus(1);
      grouponActivity.setUpdateBy(user.getId());
      grouponActivity.setUpdateDate(new Date());
      try {
        seckillGoodsService.update(seckillGoodsPojo);
        grouponActivityService.update(grouponActivity);
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
      seckillGoodsPojo = new SeckillGoodsPojo();
      seckillGoodsPojo.setId(id);
      seckillGoodsPojo.setStatus(0);
      seckillGoodsPojo.setUpdateBy(user.getId());
      seckillGoodsPojo.setUpdateDate(new Date());
      GrouponActivityPojo grouponActivity = new GrouponActivityPojo();
      grouponActivity.setId(activityId);
      grouponActivity.setStatus(0);
      grouponActivity.setUpdateBy(user.getId());
      grouponActivity.setUpdateDate(new Date());
      try {
        seckillGoodsService.update(seckillGoodsPojo);
        grouponActivityService.update(grouponActivity);
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
    if (user != null && id != null && id > 0 && activityId != null && activityId > 0) {
      try {
        GrouponActivityPojo grouponActivity = new GrouponActivityPojo();
        grouponActivity.setId(activityId);
        grouponActivity.setIsDelete(1);
        grouponActivityService.update(grouponActivity);
        seckillGoodsService.delete(id);
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
      SeckillGoodsPojo seckillGoodsPojo = null;
      GrouponActivityPojo grouponActivity = null;
      for (String tid : tids) {
        seckillGoodsPojo = seckillGoodsService.getById(Long.valueOf(tid));
        if (seckillGoodsPojo != null) {
          // 活动
          grouponActivity = new GrouponActivityPojo();
          grouponActivity.setId(seckillGoodsPojo.getActivityId());
          grouponActivity.setStatus(1);
          grouponActivity.setUpdateBy(user.getId());
          grouponActivity.setUpdateDate(new Date());
          // 秒杀商品
          seckillGoodsPojo = new SeckillGoodsPojo();
          seckillGoodsPojo.setStatus(1);
          seckillGoodsPojo.setId(Long.valueOf(tid));
          seckillGoodsPojo.setUpdateBy(user.getId());
          seckillGoodsPojo.setUpdateDate(new Date());
          try {
            seckillGoodsService.update(seckillGoodsPojo);
            grouponActivityService.update(grouponActivity);
          } catch (Exception e) {
            part = true;
            e.printStackTrace();
          }
        } else {
          Util.log("查询不到数据!");
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
      SeckillGoodsPojo seckillGoodsPojo = null;
      GrouponActivityPojo grouponActivity = null;
      for (String tid : tids) {
        seckillGoodsPojo = seckillGoodsService.getById(Long.valueOf(tid));
        if (seckillGoodsPojo != null) {
          // 活动
          grouponActivity = new GrouponActivityPojo();
          grouponActivity.setId(seckillGoodsPojo.getActivityId());
          grouponActivity.setStatus(0);
          grouponActivity.setUpdateBy(user.getId());
          grouponActivity.setUpdateDate(new Date());
          // 秒杀商品
          seckillGoodsPojo = new SeckillGoodsPojo();
          seckillGoodsPojo.setStatus(0);
          seckillGoodsPojo.setId(Long.valueOf(tid));
          seckillGoodsPojo.setUpdateBy(user.getId());
          seckillGoodsPojo.setUpdateDate(new Date());
          try {
            seckillGoodsService.update(seckillGoodsPojo);
            grouponActivityService.update(grouponActivity);
          } catch (Exception e) {
            part = true;
            e.printStackTrace();
          }
        } else {
          Util.log("查询不到数据!");
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
          seckillGoodsService.delete(Long.valueOf(tid));
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
   * 修改排序
   * 
   * @return
   * @throws Throwable
   */
  public String updateSorting() throws Throwable {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && seckillGoodsPojo != null) {
      seckillGoodsPojo.setUpdateBy(user.getId());
      seckillGoodsPojo.setUpdateDate(new Date());
      if (seckillGoodsPojo.getActivityId() != null) {
        GrouponActivityPojo grouponActivity = new GrouponActivityPojo();
        grouponActivity.setId(seckillGoodsPojo.getActivityId());
        grouponActivity.setSorting(seckillGoodsPojo.getSorting());
        grouponActivityService.update(grouponActivity);
      }
      try {
        seckillGoodsService.update(seckillGoodsPojo);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
        result = "0";
      }
    } else {
      result = "0";
    }

    return SUCCESS;
  }

  /**
   * 开团列表 查询全部条数
   */
  public String seckillGroRecCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (beginTime != null) {
      map.put("beginTimeStr", StringUtil.dateString(beginTime));
    }
    if (endTime != null) {
      map.put("endTimeStr", StringUtil.dateString(endTime));
    }
    map.put("keywords", grouponUserRecordPojo.getKeywords());
    map.put("groupStatus", grouponUserRecordPojo.getGroupStatus());
    map.put("activityType", 6);
    map.put("id", grouponUserRecordPojo.getId());
    try {
      int i = grouponUserRecordService.countBy3(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 开团列表 查询全部记录
   */
  public String seckillGroRecList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (beginTime != null) {
      map.put("beginTimeStr", StringUtil.dateString(beginTime));
    }
    if (endTime != null) {
      map.put("endTimeStr", StringUtil.dateString(endTime));
    }
    map.put("keywords", grouponUserRecordPojo.getKeywords());
    map.put("groupStatus", grouponUserRecordPojo.getGroupStatus());
    map.put("activityType", 6);
    map.put("id", grouponUserRecordPojo.getId());
    List<GrouponUserRecordPojo> grouponRecordList = null;
    try {
      grouponRecordList = grouponUserRecordService.listPage3(map);
      JSONArray json = JSONArray.fromObject(grouponRecordList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 参团用户列表 查询全部条数
   */
  public String seckillGroUserRecCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("attendId", grouponUserRecordPojo.getAttendId());
    map.put("keywords", keywords);
    map.put("source", 6);
    try {
      int i = grouponUserRecordService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 参团用户列表 查询全部记录
   */
  public String seckillGroUserRecList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("attendId", grouponUserRecordPojo.getAttendId());
    map.put("keywords", keywords);
    map.put("source", 6);
    map.put("orderBy", " gur.is_head desc");
    List<GrouponUserRecordPojo> grouponUserRecordList = null;
    try {
      grouponUserRecordList = grouponUserRecordService.listPage(map);
      JSONArray json = JSONArray.fromObject(grouponUserRecordList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 更新上首页状态
   * 
   * @return
   */
  public String updateIsHome() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && id != null && id > 0 && activityId != null && activityId != 0
        && isHome != null) {
      seckillGoodsPojo = new SeckillGoodsPojo();
      seckillGoodsPojo.setId(id);
      seckillGoodsPojo.setActivityId(activityId);
      seckillGoodsPojo.setIsHome(isHome);
      seckillGoodsPojo.setUpdateBy(user.getId());
      seckillGoodsPojo.setUpdateDate(new Date());
      try {
        seckillGoodsService.update(seckillGoodsPojo);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }
}
