/*
 * 文 件 名: SeckillAction.java 创 建 人: admin 创建时间: 2016-10-26
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
import com.tzmb2c.web.pojo.GrouponActivityPojo;
import com.tzmb2c.web.pojo.SeckillGoodsPojo;
import com.tzmb2c.web.pojo.SeckillPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.SeckillGoodsService;
import com.tzmb2c.web.service.SeckillService;

public class SeckillAction extends SuperAction {
  @Autowired
  private SeckillService seckillService;
  @Autowired
  private SeckillGoodsService seckillGoodsService;
  @Autowired
  private GrouponActivityService grouponActivityService;
  private SeckillPojo seckillPojo;
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

  public SeckillPojo getSeckillPojo() {
    return seckillPojo;
  }

  public void setSeckillPojo(SeckillPojo seckillPojo) {
    this.seckillPojo = seckillPojo;
  }


  /**
   * 查询全部条数
   */
  public String goSeckill() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("isDelete", 0);
    if (seckillPojo != null) {
      if (seckillPojo.getBeginTime() != null) {
        map.put("beginTimeStr2", StringUtil.dateString(seckillPojo.getBeginTime()));
      }
      if (seckillPojo.getEndTime() != null) {
        map.put("endTimeStr2", StringUtil.dateString(seckillPojo.getEndTime()));
      }
      map.put("status", seckillPojo.getStatus());
    }
    try {
      int i = seckillService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String seckillList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("isDelete", 0);
    map.put("orderBy", "begin_time desc,sorting desc,id desc");
    if (seckillPojo != null) {
      if (seckillPojo.getBeginTime() != null) {
        map.put("beginTimeStr2", StringUtil.dateString(seckillPojo.getBeginTime()));
      }
      if (seckillPojo.getEndTime() != null) {
        map.put("endTimeStr2", StringUtil.dateString(seckillPojo.getEndTime()));
      }
      map.put("status", seckillPojo.getStatus());
    }
    List<SeckillPojo> seckillList = null;
    try {
      seckillList = seckillService.listPage(map);
      JSONArray json = JSONArray.fromObject(seckillList);
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
  public String goAddSeckill() throws Exception {
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
    if (user != null && seckillPojo != null) {
      seckillPojo.setCreateBy(user.getId());
      seckillPojo.setCreateDate(new Date());
      seckillPojo.setUpdateBy(user.getId());
      seckillPojo.setUpdateDate(new Date());
      seckillPojo.setType(1);
      try {
        seckillService.add(seckillPojo);
        FileUtil.alertMessageBySkip("新增成功！", "goSeckillGoods.do?seckillGoodsPojo.seckillId="
            + seckillPojo.getId());
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goAddSeckill.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goSeckill.do");
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditSeckill() throws Exception {
    if (id != null && id > 0) {
      seckillPojo = seckillService.getById(id);
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
    if (user != null && seckillPojo != null) {
      seckillPojo.setUpdateBy(user.getId());
      seckillPojo.setUpdateDate(new Date());
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("seckillId", seckillPojo.getId());
      List<SeckillGoodsPojo> seckillGoodsPojos = seckillGoodsService.listPage(map);
      if (seckillGoodsPojos != null && seckillGoodsPojos.size() > 0) {
        GrouponActivityPojo grouponActivity = new GrouponActivityPojo();
        for (SeckillGoodsPojo p : seckillGoodsPojos) {
          grouponActivity.setId(p.getActivityId());
          grouponActivity.setBeginTime(seckillPojo.getBeginTime());
          grouponActivity.setEndTime(seckillPojo.getEndTime());
          grouponActivityService.update(grouponActivity);
        }
      }
      try {
        seckillService.update(seckillPojo);
        FileUtil.alertMessageBySkip("提交成功！", "goSeckill.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！", "goEditSeckill.do?id=" + seckillPojo.getId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goSeckill.do");
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
      seckillPojo = new SeckillPojo();
      seckillPojo.setId(id);
      seckillPojo.setStatus(1);
      seckillPojo.setUpdateBy(user.getId());
      seckillPojo.setUpdateDate(new Date());
      try {
        seckillService.update(seckillPojo);
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
      seckillPojo = new SeckillPojo();
      seckillPojo.setId(id);
      seckillPojo.setStatus(0);
      seckillPojo.setUpdateBy(user.getId());
      seckillPojo.setUpdateDate(new Date());
      try {
        seckillService.update(seckillPojo);
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
        SeckillPojo seckill = new SeckillPojo();
        seckill.setId(id);
        seckill.setIsDelete(1);
        seckillService.update(seckill);
        // seckillService.delete(id);
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
      seckillPojo = new SeckillPojo();
      seckillPojo.setStatus(1);
      for (String tid : tids) {
        seckillPojo.setId(Long.valueOf(tid));
        seckillPojo.setUpdateBy(user.getId());
        seckillPojo.setUpdateDate(new Date());
        try {
          seckillService.update(seckillPojo);
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
      seckillPojo = new SeckillPojo();
      seckillPojo.setStatus(0);
      for (String tid : tids) {
        seckillPojo.setId(Long.valueOf(tid));
        seckillPojo.setUpdateBy(user.getId());
        seckillPojo.setUpdateDate(new Date());
        try {
          seckillService.update(seckillPojo);
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
          SeckillPojo seckill = new SeckillPojo();
          seckill.setId(Long.valueOf(tid));
          seckill.setIsDelete(1);
          seckillService.update(seckill);
          // seckillService.delete(Long.valueOf(tid));
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
