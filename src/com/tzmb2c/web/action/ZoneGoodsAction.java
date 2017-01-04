/*
 * 文 件 名: ZoneGoodsAction.java 创 建 人: admin 创建时间: 2016-10-18
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
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.ZoneGoodsPojo;
import com.tzmb2c.web.service.ZoneGoodsService;

public class ZoneGoodsAction extends SuperAction {
  @Autowired
  private ZoneGoodsService zoneGoodsService;
  private ZoneGoodsPojo zoneGoodsPojo;
  private Long id;
  private String[] tids;
  private String result;
  private Integer zones;


  public Integer getZones() {
    return zones;
  }

  public void setZones(Integer zones) {
    this.zones = zones;
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

  public ZoneGoodsPojo getZoneGoodsPojo() {
    return zoneGoodsPojo;
  }

  public void setZoneGoodsPojo(ZoneGoodsPojo zoneGoodsPojo) {
    this.zoneGoodsPojo = zoneGoodsPojo;
  }


  /**
   * 查询全部条数
   */
  public String goZoneGoods() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("zoneId", zoneGoodsPojo.getZoneId());
    try {
      int i = zoneGoodsService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String zoneGoodsList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("orderBy", "zg.sorting desc,zg.id desc");
    map.put("zoneId", zoneGoodsPojo.getZoneId());
    List<ZoneGoodsPojo> zoneGoodsList = null;
    try {
      zoneGoodsList = zoneGoodsService.listPage(map);
      JSONArray json = JSONArray.fromObject(zoneGoodsList);
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
  public String goAddZoneGoods() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Throwable
   */
  public String add() throws Throwable {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("zoneId", zoneGoodsPojo.getZoneId());
    map.put("activityId", zoneGoodsPojo.getActivityId());
    int i = zoneGoodsService.countBy(map);
    if (i > 0) {
      FileUtil.alertMessageBySkip("该商品已添加过！", "goGrouponActivityProduct.do?zones=" + zones
          + "&zoneGoodsPojo.zoneId=" + zoneGoodsPojo.getZoneId());
      return null;
    }
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && zoneGoodsPojo != null) {
      zoneGoodsPojo.setCreateBy(user.getId());
      zoneGoodsPojo.setCreateDate(new Date());
      zoneGoodsPojo.setUpdateBy(user.getId());
      zoneGoodsPojo.setUpdateDate(new Date());
      try {
        zoneGoodsService.add(zoneGoodsPojo);
        FileUtil.alertMessageBySkip("新增成功！", "goZoneGoods.do?zones=" + zones
            + "&zoneGoodsPojo.zoneId=" + zoneGoodsPojo.getZoneId());
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goGrouponActivityProduct.do?zones=" + zones
            + "&zoneGoodsPojo.zoneId=" + zoneGoodsPojo.getZoneId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goZoneGoods.do?zones=" + zones
          + "&zoneGoodsPojo.zoneId=" + zoneGoodsPojo.getZoneId());
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditZoneGoods() throws Exception {
    if (id != null && id > 0) {
      zoneGoodsPojo = zoneGoodsService.getById(id);
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
    if (user != null && zoneGoodsPojo != null) {
      zoneGoodsPojo.setUpdateBy(user.getId());
      zoneGoodsPojo.setUpdateDate(new Date());
      try {
        zoneGoodsService.update(zoneGoodsPojo);
        FileUtil.alertMessageBySkip("提交成功！", "goZoneGoods.do?zones" + zones
            + "&zoneGoodsPojo.zoneId=" + zoneGoodsPojo.getZoneId());
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！", "goEditZoneGoods.do?id=" + zoneGoodsPojo.getId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goZoneGoods.do?zones" + zones
          + "&zoneGoodsPojo.zoneId=" + zoneGoodsPojo.getZoneId());
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
      zoneGoodsPojo = new ZoneGoodsPojo();
      zoneGoodsPojo.setId(id);
      zoneGoodsPojo.setStatus(1);
      zoneGoodsPojo.setUpdateBy(user.getId());
      zoneGoodsPojo.setUpdateDate(new Date());
      try {
        zoneGoodsService.update(zoneGoodsPojo);
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
      zoneGoodsPojo = new ZoneGoodsPojo();
      zoneGoodsPojo.setId(id);
      zoneGoodsPojo.setStatus(0);
      zoneGoodsPojo.setUpdateBy(user.getId());
      zoneGoodsPojo.setUpdateDate(new Date());
      try {
        zoneGoodsService.update(zoneGoodsPojo);
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
        zoneGoodsService.delete(id);
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
      zoneGoodsPojo = new ZoneGoodsPojo();
      zoneGoodsPojo.setStatus(1);
      for (String tid : tids) {
        zoneGoodsPojo.setId(Long.valueOf(tid));
        zoneGoodsPojo.setUpdateBy(user.getId());
        zoneGoodsPojo.setUpdateDate(new Date());
        try {
          zoneGoodsService.update(zoneGoodsPojo);
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
      zoneGoodsPojo = new ZoneGoodsPojo();
      zoneGoodsPojo.setStatus(0);
      for (String tid : tids) {
        zoneGoodsPojo.setId(Long.valueOf(tid));
        zoneGoodsPojo.setUpdateBy(user.getId());
        zoneGoodsPojo.setUpdateDate(new Date());
        try {
          zoneGoodsService.update(zoneGoodsPojo);
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
          zoneGoodsService.delete(Long.valueOf(tid));
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
    if (user != null && zoneGoodsPojo != null) {
      zoneGoodsPojo.setUpdateBy(user.getId());
      zoneGoodsPojo.setUpdateDate(new Date());
      try {
        zoneGoodsService.update(zoneGoodsPojo);
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
   * 批量新增提交
   * 
   * @return
   * @throws Throwable
   */
  public String addAll() throws Throwable {
    result = "0";
    if (tids != null) {
      for (String id : tids) {
        String a[] = id.split(":");
        zoneGoodsPojo.setActivityId(Long.valueOf(a[0]));
        zoneGoodsPojo.setProductId(Long.valueOf(a[1]));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("zoneId", zoneGoodsPojo.getZoneId());
        map.put("activityId", Long.valueOf(a[0]));
        int i = zoneGoodsService.countBy(map);
        if (i <= 0) {
          SysLoginPojo user = UserUtil.getUser();
          if (user != null && zoneGoodsPojo != null) {
            ZoneGoodsPojo zoneGoods = new ZoneGoodsPojo();
            zoneGoods.setActivityId(zoneGoodsPojo.getActivityId());
            zoneGoods.setProductId(zoneGoodsPojo.getProductId());
            zoneGoods.setZoneId(zoneGoodsPojo.getZoneId());
            zoneGoods.setCreateBy(user.getId());
            zoneGoods.setCreateDate(new Date());
            zoneGoods.setUpdateBy(user.getId());
            zoneGoods.setUpdateDate(new Date());
            try {
              zoneGoodsService.add(zoneGoods);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        } else {
          Util.log("该商品已添加过！");
        }
      }
      this.result = "1";
    } else {
      this.result = "0";
    }
    return SUCCESS;
  }

}
