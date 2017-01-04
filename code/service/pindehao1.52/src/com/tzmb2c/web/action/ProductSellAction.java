/*
 * 文 件 名: ProductSellAction.java 创 建 人: admin 创建时间: 2016-11-19
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
import com.tzmb2c.web.pojo.ProductSellPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.ProductSellService;

public class ProductSellAction extends SuperAction {
  @Autowired
  private ProductSellService productSellService;
  private ProductSellPojo productSellPojo;
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

  public ProductSellPojo getProductSellPojo() {
    return productSellPojo;
  }

  public void setProductSellPojo(ProductSellPojo productSellPojo) {
    this.productSellPojo = productSellPojo;
  }

  /**
   * 查询全部条数
   */
  public String goProductSell() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    try {
      int i = productSellService.countBy(null);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String productSellRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      int i = productSellService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String productSellList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    List<ProductSellPojo> productSellList = null;
    try {
      productSellList = productSellService.listPage(map);
      JSONArray json = JSONArray.fromObject(productSellList);
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
  public String goAddProductSell() throws Exception {
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
    if (user != null && productSellPojo != null) {
      productSellPojo.setCreateBy(user.getId());
      productSellPojo.setCreateDate(new Date());
      productSellPojo.setUpdateBy(user.getId());
      productSellPojo.setUpdateDate(new Date());
      try {
        productSellService.add(productSellPojo);
        FileUtil.alertMessageBySkip("新增成功！", "goProductSell.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goAddProductSell.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goProductSell.do");
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditProductSell() throws Exception {
    if (id != null && id > 0) {
      productSellPojo = productSellService.getById(id);
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
    if (user != null && productSellPojo != null) {
      productSellPojo.setUpdateBy(user.getId());
      productSellPojo.setUpdateDate(new Date());
      try {
        productSellService.update(productSellPojo);
        FileUtil.alertMessageBySkip("提交成功！", "goProductSell.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！", "goEditProductSell.do?id=" + productSellPojo.getId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goProductSell.do");
    }

    return null;
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
        productSellService.delete(id);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
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
          productSellService.delete(Long.valueOf(tid));
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
