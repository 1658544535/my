/*
 * 文 件 名: SpecialGoodsAction.java 创 建 人: admin 创建时间: 2016-10-14
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
import com.tzmb2c.web.pojo.SpecialGoodsPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.SpecialGoodsService;

public class SpecialGoodsAction extends SuperAction {
  @Autowired
  private SpecialGoodsService specialGoodsService;
  private SpecialGoodsPojo specialGoodsPojo;
  private Long id;
  private String[] tids;
  private String result;
  private String activityId;
  private String productId;

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

  public String getActivityId() {
    return activityId;
  }

  public void setActivityId(String activityId) {
    this.activityId = activityId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public SpecialGoodsPojo getSpecialGoodsPojo() {
    return specialGoodsPojo;
  }

  public void setSpecialGoodsPojo(SpecialGoodsPojo specialGoodsPojo) {
    this.specialGoodsPojo = specialGoodsPojo;
  }

  /**
   * 查询全部条数
   */
  public String goSpecialGoods() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("specialId", specialGoodsPojo.getSpecialId());
    try {
      int i = specialGoodsService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String specialGoodsList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("orderBy", "sg.sorting desc,sg.id desc");
    map.put("specialId", specialGoodsPojo.getSpecialId());
    List<SpecialGoodsPojo> specialGoodsList = null;
    try {
      specialGoodsList = specialGoodsService.listPage(map);
      JSONArray json = JSONArray.fromObject(specialGoodsList);
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
  public String goAddSpecialGoods() throws Exception {
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
    map.put("specialId", specialGoodsPojo.getSpecialId());
    map.put("activityId", specialGoodsPojo.getActivityId());
    int i = specialGoodsService.countBy(map);
    if (i > 0) {
      FileUtil.alertMessageBySkip(
          "该商品已添加过！",
          "goGrouponActivityProduct.do?special=1&specialGoodsPojo.specialId="
              + specialGoodsPojo.getSpecialId());
      return null;
    }
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && specialGoodsPojo != null) {
      specialGoodsPojo.setCreateBy(user.getId());
      specialGoodsPojo.setCreateDate(new Date());
      specialGoodsPojo.setUpdateBy(user.getId());
      specialGoodsPojo.setUpdateDate(new Date());
      try {
        specialGoodsService.add(specialGoodsPojo);
        FileUtil.alertMessageBySkip("新增成功！", "goSpecialGoods.do?specialGoodsPojo.specialId="
            + specialGoodsPojo.getSpecialId());
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip(
            "新增失败！",
            "goGrouponActivityProduct.do?special=1&specialGoodsPojo.specialId="
                + specialGoodsPojo.getSpecialId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goSpecialGoods.do?specialGoodsPojo.specialId="
          + specialGoodsPojo.getSpecialId());
    }
    return null;
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
        specialGoodsPojo.setActivityId(Long.valueOf(a[0]));
        specialGoodsPojo.setProductId(Long.valueOf(a[1]));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("specialId", specialGoodsPojo.getSpecialId());
        map.put("activityId", Long.valueOf(a[0]));
        int i = specialGoodsService.countBy(map);
        if (i > 0) {
          Util.log("该商品已添加过！");
        } else {
          SysLoginPojo user = UserUtil.getUser();
          if (user != null && specialGoodsPojo != null) {
            SpecialGoodsPojo specialGoods = new SpecialGoodsPojo();
            specialGoods.setActivityId(specialGoodsPojo.getActivityId());
            specialGoods.setProductId(specialGoodsPojo.getProductId());
            specialGoods.setSpecialId(specialGoodsPojo.getSpecialId());
            specialGoods.setCreateBy(user.getId());
            specialGoods.setCreateDate(new Date());
            specialGoods.setUpdateBy(user.getId());
            specialGoods.setUpdateDate(new Date());
            try {
              specialGoodsService.add(specialGoods);
            } catch (Exception e) {
              Util.log("新增失败！");
              e.printStackTrace();
            }
          }
        }
      }
      this.result = "1";
    } else {
      this.result = "0";
    }
    return SUCCESS;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditSpecialGoods() throws Exception {
    if (id != null && id > 0) {
      specialGoodsPojo = specialGoodsService.getById(id);
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
    if (user != null && specialGoodsPojo != null) {
      specialGoodsPojo.setUpdateBy(user.getId());
      specialGoodsPojo.setUpdateDate(new Date());
      try {
        specialGoodsService.update(specialGoodsPojo);
        FileUtil.alertMessageBySkip("提交成功！", "goSpecialGoods.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil
            .alertMessageBySkip("提交失败！", "goEditSpecialGoods.do?id=" + specialGoodsPojo.getId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goSpecialGoods.do");
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
      specialGoodsPojo = new SpecialGoodsPojo();
      specialGoodsPojo.setId(id);
      specialGoodsPojo.setStatus(1);
      specialGoodsPojo.setUpdateBy(user.getId());
      specialGoodsPojo.setUpdateDate(new Date());
      try {
        specialGoodsService.update(specialGoodsPojo);
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
      specialGoodsPojo = new SpecialGoodsPojo();
      specialGoodsPojo.setId(id);
      specialGoodsPojo.setStatus(0);
      specialGoodsPojo.setUpdateBy(user.getId());
      specialGoodsPojo.setUpdateDate(new Date());
      try {
        specialGoodsService.update(specialGoodsPojo);
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
        specialGoodsService.delete(id);
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
      specialGoodsPojo = new SpecialGoodsPojo();
      specialGoodsPojo.setStatus(1);
      for (String tid : tids) {
        specialGoodsPojo.setId(Long.valueOf(tid));
        specialGoodsPojo.setUpdateBy(user.getId());
        specialGoodsPojo.setUpdateDate(new Date());
        try {
          specialGoodsService.update(specialGoodsPojo);
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
      specialGoodsPojo = new SpecialGoodsPojo();
      specialGoodsPojo.setStatus(0);
      for (String tid : tids) {
        specialGoodsPojo.setId(Long.valueOf(tid));
        specialGoodsPojo.setUpdateBy(user.getId());
        specialGoodsPojo.setUpdateDate(new Date());
        try {
          specialGoodsService.update(specialGoodsPojo);
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
          specialGoodsService.delete(Long.valueOf(tid));
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
    if (user != null && specialGoodsPojo != null) {
      specialGoodsPojo.setUpdateBy(user.getId());
      specialGoodsPojo.setUpdateDate(new Date());
      try {
        specialGoodsService.update(specialGoodsPojo);
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

}
