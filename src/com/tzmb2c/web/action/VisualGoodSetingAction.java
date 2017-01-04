package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.SpecialProductPojo;
import com.tzmb2c.web.service.VisualGoodSetingService;

/**
 * 视觉商品设置 2015-11-24
 * 
 * @author by zhuzehuang
 */
public class VisualGoodSetingAction extends SuperAction {
  @Autowired
  private VisualGoodSetingService visualGoodSetingService;
  private SpecialProductPojo specialProductPojo;
  private String result;
  private String[] tids;
  private List<SpecialProductPojo> specialProductPojoList;

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

  public SpecialProductPojo getSpecialProductPojo() {
    return specialProductPojo;
  }

  public void setSpecialProductPojo(SpecialProductPojo specialProductPojo) {
    this.specialProductPojo = specialProductPojo;
  }

  public List<SpecialProductPojo> getSpecialProductPojoList() {
    return specialProductPojoList;
  }

  public void setSpecialProductPojoList(List<SpecialProductPojo> specialProductPojoList) {
    this.specialProductPojoList = specialProductPojoList;
  }

  /**
   * 数目
   * 
   * @return
   * @throws Exception
   */
  public String visualGoodSetingCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (specialProductPojo != null) {
        map.put("productName", specialProductPojo.getProductName());
        map.put("title", specialProductPojo.getTitle());
        map.put("visualGoods", specialProductPojo.getVisualGoods());
      }
      map.put("proStatus", 1);
      map.put("status", 1);
      int i = visualGoodSetingService.findVisualGoodSetingCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 列表
   * 
   * @return
   * @throws Exception
   */
  public String visualGoodSetingAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (specialProductPojo != null) {
        map.put("productName", specialProductPojo.getProductName());
        map.put("title", specialProductPojo.getTitle());
        map.put("visualGoods", specialProductPojo.getVisualGoods());
      }
      map.put("proStatus", 1);
      map.put("status", 1);
      specialProductPojoList = visualGoodSetingService.findVisualGoodSetingList(map);
      JSONArray json = JSONArray.fromObject(specialProductPojoList);
      page.setRowCount(specialProductPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 通过设置
   * 
   * @return
   */
  public String setSpecialProduct() throws SQLException {
    try {
      visualGoodSetingService.setSpecialProduct(specialProductPojo.getId());
      FileUtil.alertMessageBySkip("设置成功！", "visualGoodSeting.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("设置失败！", "visualGoodSeting.do");
    }
    return null;
  }

  /**
   * 取消资格
   * 
   * @return
   */
  public String unsetSpecialProduct() throws SQLException {
    try {
      visualGoodSetingService.unsetSpecialProduct(specialProductPojo.getId());
      FileUtil.alertMessageBySkip("取消资格成功！", "visualGoodSeting.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("取消资格失败！", "visualGoodSeting.do");
    }
    return null;
  }

  /**
   * 根据id批量设置
   * 
   * @return
   */
  public String setSpecialProductAll() {
    try {
      if (tids == null || "".equals(tids)) {
        FileUtil.alertMessageBySkip("请勾选需要设置为视觉商品的商品！", "visualGoodSeting.do");
        return null;
      } else {
        for (String id : tids) {
          visualGoodSetingService.setSpecialProduct(Long.valueOf(id));
        }
        FileUtil.alertMessageBySkip("批量设置成功！", "visualGoodSeting.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("批量设置失败！", "visualGoodSeting.do");
    }
    return null;
  }
}
