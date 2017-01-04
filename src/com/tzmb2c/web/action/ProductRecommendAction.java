package com.tzmb2c.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.ProductRecommendPojo;
import com.tzmb2c.web.service.ProductRecommendService;

public class ProductRecommendAction extends SuperAction {

  @Autowired
  private ProductRecommendService productRecommendService;

  /**
   * serialVersionUID:TODO<用一句话描述这个变量表示什么>
   */
  private static final long serialVersionUID = 1L;
  private ProductRecommendPojo productRecommendPojo;
  private String result;


  public String getResult() {
    return result;
  }


  public void setResult(String result) {
    this.result = result;
  }


  public ProductRecommendPojo getProductRecommendPojo() {
    return productRecommendPojo;
  }


  public void setProductRecommendPojo(ProductRecommendPojo productRecommendPojo) {
    this.productRecommendPojo = productRecommendPojo;
  }

  /**
   * 
   * 页面/条数
   * 
   * @return
   * @throw
   * @return String
   */
  public String goNewDaily() {
    try {
      if (page == null) {
        page = new Pager();
      }
      if (productRecommendPojo != null) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", productRecommendPojo.getType());
        int i = productRecommendService.countBy(params);
        page.setRowCount(i);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 
   * 列表
   * 
   * @return
   * @throw
   * @return String
   */
  public String goNewDailyList() {
    try {
      if (page == null) {
        page = new Pager();
      }
      if (productRecommendPojo != null) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pageSize", 10);
        params.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
        params.put("type", productRecommendPojo.getType());
        params.put("orderBy", "pr.sorting desc,pr.update_date desc,pr.create_date desc");
        List<ProductRecommendPojo> list = productRecommendService.listPage(params);
        JSONArray json = JSONArray.fromObject(list);
        page.setResult(json.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 
   * 根据ID删除记录
   * 
   * @return
   * @throw
   * @return String
   */
  public String delNewDailyOne() {
    try {
      if (productRecommendPojo != null) {
        productRecommendService.delete(productRecommendPojo.getId());
        this.result = "1";
      } else {
        this.result = "0";
      }
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  /**
   * 
   * 每日上新-商品列表添加
   * 
   * @return
   * @throw
   * @return String
   */
  public String doNewDailyAdd() {
    try {
      if (productRecommendPojo != null) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", productRecommendPojo.getType());
        params.put("productId", productRecommendPojo.getProductId());
        params.put("activityId", productRecommendPojo.getActivityId());
        int count = productRecommendService.countBy(params);
        if (count > 0) {
          FileUtil.alertMessageBySkip("该商品已添加过！", "goNewDailyAdd.do?productRecommendPojo.type="
              + productRecommendPojo.getType());
          return null;
        }
        productRecommendService.add(productRecommendPojo);
        FileUtil.alertMessageBySkip("选取成功！", "goNewDaily.do?productRecommendPojo.type="
            + productRecommendPojo.getType());
      }
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("选取失败！", "goNewDaily.do?productRecommendPojo.type="
          + productRecommendPojo.getType());
    }
    return null;
  }

  /**
   * 
   * 每日上新-修改序号
   * 
   * @return
   * @throw
   * @return String
   */
  public String doNewDailyUpdateSorting() {
    try {
      if (productRecommendPojo != null) {
        productRecommendService.update(productRecommendPojo);
        this.result = "1";
      } else {
        this.result = "0";
      }
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }
}
