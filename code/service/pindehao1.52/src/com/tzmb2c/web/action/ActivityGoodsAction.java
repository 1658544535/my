package com.tzmb2c.web.action;


import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.SpecialProductPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.SysDictService;

public class ActivityGoodsAction extends SuperAction {
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;

  private SpecialProductPojo specialProductPojo;
  private ActivityGoodsPojo activityGoodsPojo, activityGoods;
  private ActivityTimePojo activityTimePojo;
  private List<SysDictPojo> statusSysDictList = null;
  private Long timeId;
  private Long productId;
  private String productName;
  private double sellPrice;
  private String result;
  private String[] tids;
  private String activityTime;
  private Integer t;
  private List<ProductSkuLinkPojo> productSkuLinkPojos;
  private ProductSkuLinkPojo productSkuLinkPojo;
  private Integer op;
  private String typeName;
  private String activityDate;
  private List<ActivityTimePojo> activityTimeList;

  public List<ActivityTimePojo> getactivityTimeList() {
    return activityTimeList;
  }

  public void setActivityTimeList(List<ActivityTimePojo> activityTimeList) {
    this.activityTimeList = activityTimeList;
  }

  public ProductSkuLinkPojo getProductSkuLinkPojo() {
    return productSkuLinkPojo;
  }

  public void setProductSkuLinkPojo(ProductSkuLinkPojo productSkuLinkPojo) {
    this.productSkuLinkPojo = productSkuLinkPojo;
  }

  public List<ProductSkuLinkPojo> getProductSkuLinkPojos() {
    return productSkuLinkPojos;
  }

  public void setProductSkuLinkPojos(List<ProductSkuLinkPojo> productSkuLinkPojos) {
    this.productSkuLinkPojos = productSkuLinkPojos;
  }

  public String getActivityTime() {
    return activityTime;
  }

  public void setActivityTime(String activityTime) {
    this.activityTime = activityTime;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }



  public List<SysDictPojo> getStatusSysDictList() {
    return statusSysDictList;
  }

  public void setStatusSysDictList(List<SysDictPojo> statusSysDictList) {
    this.statusSysDictList = statusSysDictList;
  }

  public ActivityGoodsPojo getActivityGoodsPojo() {
    return activityGoodsPojo;
  }

  public void setActivityGoodsPojo(ActivityGoodsPojo activityGoodsPojo) {
    this.activityGoodsPojo = activityGoodsPojo;
  }

  public ActivityGoodsPojo getActivityGoods() {
    return activityGoods;
  }

  public void setActivityGoods(ActivityGoodsPojo activityGoods) {
    this.activityGoods = activityGoods;
  }

  public SpecialProductPojo getSpecialProductPojo() {
    return specialProductPojo;
  }

  public void setSpecialProductPojo(SpecialProductPojo specialProductPojo) {
    this.specialProductPojo = specialProductPojo;
  }

  public ActivityTimePojo getActivityTimePojo() {
    return activityTimePojo;
  }

  public void setActivityTimePojo(ActivityTimePojo activityTimePojo) {
    this.activityTimePojo = activityTimePojo;
  }

  public Long getTimeId() {
    return timeId;
  }

  public void setTimeId(Long timeId) {
    this.timeId = timeId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public double getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(double sellPrice) {
    this.sellPrice = sellPrice;
  }


  private void getDictList() {
    try {
      statusSysDictList = sysDictService.getSysDictListByType("status");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String findActivityGoodsList() throws SQLException {
    getDictList();
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("timeId", timeId);
    if (activityGoodsPojo != null) {
      map.put("productNum", activityGoodsPojo.getProductNum());
      map.put("productName", activityGoodsPojo.getProductName());
      map.put("status", activityGoodsPojo.getStatus());
      map.put("productNo", activityGoodsPojo.getProductNo());
      map.put("brandName", activityGoodsPojo.getBrandName());
    }
    List<ActivityGoodsPojo> list = activityGoodsService.findActivityGoodsList(map);
    if (list != null && list.size() > 0) {
      for (ActivityGoodsPojo p : list) {
        p.setProductImage(p.getProductImage());
      }
    }
    JSONArray json = JSONArray.fromObject(list);
    page.setRowCount(activityGoodsService.findActivityGoodsCount(map));
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String findActivityGoodsCount() throws SQLException {
    getDictList();
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("timeId", timeId);
    if (activityGoodsPojo != null) {
      map.put("productNum", activityGoodsPojo.getProductNum());
      map.put("productName", activityGoodsPojo.getProductName());
      map.put("status", activityGoodsPojo.getStatus());
      map.put("productNo", activityGoodsPojo.getProductNo());
      map.put("brandName", activityGoodsPojo.getBrandName());
    }
    int i = activityGoodsService.findActivityGoodsCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  public String findActivityGoodsById() throws SQLException {
    if (productSkuLinkPojo.getProductId() != null) {
      productSkuLinkPojos = productSkuLinkService.getProductSkuLinkAll(productSkuLinkPojo, null);
    }
    getDictList();
    activityGoodsPojo = activityGoodsService.findActivityGoodsById(activityGoodsPojo.getId());
    return SUCCESS;
  }

  public void updateActivityGoods() throws Exception {
    getDictList();
    if (activityGoodsPojo.getActivityStock() > activityGoodsPojo.getActivityNum()) {
      if (t == 2) {
        FileUtil.alertMessageBySkip("活动产品库存大于活动产品总量，修改失败！", "thematicActivityManage.do?op=2");
      } else {
        FileUtil.alertMessageBySkip("活动产品库存大于活动产品总量，修改失败！", "activityTimeManage.do?op=1");
      }
    } else {
      if (activityGoodsPojo != null) {
        /*
         * String d = new
         * DecimalFormat(".0").format(activityGoodsPojo.getActivePrice()/activityGoodsPojo
         * .getSellPrice()*10)+"折"; activityGoodsPojo.setTips(d);
         */
        String d =
            new BigDecimal(activityGoodsPojo.getActivePrice() / activityGoodsPojo.getSellPrice()
                * 10).setScale(1, BigDecimal.ROUND_HALF_UP)
                + "折";
        activityGoodsPojo.setTips(d);
      }
      activityGoodsService.updateActivityGoods(activityGoodsPojo);
      if (op == 2) {
        FileUtil.alertMessageBySkip("修改成功！", "activityGoodsManage.do?t=2&timeId="
            + activityGoodsPojo.getTimeId());
      } else {
        FileUtil.alertMessageBySkip("修改成功！", "activityGoodsManage.do?t=1&timeId="
            + activityGoodsPojo.getTimeId());
      }

    }
  }

  public String checkActivityGoods() throws SQLException {
    try {
      activityGoodsService.checkActivityGoods(activityGoodsPojo.getId());
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  public String uncheckActivityGoods() throws SQLException {
    try {
      activityGoodsService.uncheckActivityGoods(activityGoodsPojo.getId());
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  public String checkActivityGoodsAll() {
    StringBuffer url = new StringBuffer("activityGoodsManage.do?t=" + t + "&timeId=" + timeId);
    if (tids != null && tids.length > 0) {
      try {
        for (String tid : tids) {
          activityGoodsService.checkActivityGoods(Long.parseLong(tid));
        }
        FileUtil.alertMessageBySkip("审核成功！", url.toString());
      } catch (SQLException e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("审核失败！", url.toString());
      }
    } else {
      FileUtil.alertMessageBySkip("没有勾选！", url.toString());
    }
    return null;
  }

  public String delActivityGoods() throws SQLException {
    try {
      activityGoodsService.delActivityGoods(activityGoodsPojo.getId());
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  public String delActivityGoodsAll() {
    StringBuffer url = new StringBuffer("activityGoodsManage.do?t=" + t + "&timeId=" + timeId);
    if (tids != null && tids.length > 0) {
      try {
        for (String tid : tids) {
          activityGoodsService.delActivityGoods(Long.parseLong(tid));
        }
        FileUtil.alertMessageBySkip("删除成功！", url.toString());
      } catch (SQLException e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("删除失败！", url.toString());
      }
    } else {
      FileUtil.alertMessageBySkip("没有勾选！", url.toString());
    }
    return null;
  }

  public String activityGoodsAdd() throws Exception {
    if (op != null && op == 2) {
      activityTimePojo = new ActivityTimePojo();
      activityTimePojo.setType(1);
      activityTimePojo.setIsdelete("0");
      activityTimePojo.setChannel(1);
      activityTimeList = activityTimeService.getActivityTimeList(activityTimePojo);// 查找出专场标题
      ActionContext ac = ActionContext.getContext();
      ac.put("activityTimeList", activityTimeList);
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("timeId", timeId);
    map.put("productId", productId);
    List<ActivityGoodsPojo> activityGoods = activityGoodsService.findActivityGoodsByProductId(map);
    if (activityGoods != null && activityGoods.size() > 0) {
      if (op == 2) {
        FileUtil.alertMessageBySkip("该商品已经是钱包专区的产品，无法重复添加！", "addAgencyCollect.do?t=2&typeName="
            + typeName + "&timeId=" + timeId + "&activityTime=" + activityTime);
      } else {
        FileUtil.alertMessageBySkip("该商品已经是限时秒杀的产品，无法重复添加！", "addAgencyCollect.do?t=&activityDate="
            + activityDate + "&timeId=" + timeId + "&activityTime=" + activityTime);
      }
    } else {
      getDictList();
      activityTimePojo = activityTimeService.findActivityTimeById(timeId);
      return SUCCESS;
    }
    return null;
  }

  public String walletGoodsAdd() throws Exception {
    try {
      activityTimePojo = new ActivityTimePojo();
      activityTimePojo.setType(1);
      activityTimePojo.setIsdelete("0");
      activityTimePojo.setChannel(1);
      activityTimeList = activityTimeService.getActivityTimeList(activityTimePojo);// 查找出专区标题
      if (activityTimeList.size() == 0) {
        FileUtil.alertMessageBySkip("请创建当前有效的专区！", "thematicActivityManage.do?op=2");
        return null;
      }
      activityTimePojo.setProductId(activityGoodsPojo.getProductId());
      activityTimeList = activityTimeService.getActivityTimeList(activityTimePojo);
      if (activityTimeList.size() == 0) {
        FileUtil.alertMessageBySkip("此商品已经加入所有专区。请重新选择！", "specialShowList.do");
        return null;
      }
      ActionContext ac = ActionContext.getContext();
      ac.put("activityTimeList", activityTimeList);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String insertWalletGoods() throws Exception {
    try {
      activityGoodsPojo.setTimeId(activityTimePojo.getId());
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("productId", activityGoodsPojo.getProductId());
      map.put("timeId", activityGoodsPojo.getTimeId());
      List<ActivityGoodsPojo> activityGoodsPojoList =
          activityGoodsService.findWalletGoodsByProductId(map);
      if (activityGoodsPojoList.size() != 0) {
        FileUtil.alertMessageBySkip("此商品已添加至输入的专场中，请选择其他商品！", "specialShowList.do");
        return null;
      }
      if (activityGoodsPojo != null) {
        String d =
            new BigDecimal(activityGoodsPojo.getActivePrice() / activityGoodsPojo.getSellPrice()
                * 10).setScale(1, BigDecimal.ROUND_HALF_UP)
                + "折";
        if (activityGoodsPojo.getActivePrice() / activityGoodsPojo.getSellPrice() * 10 <= 0.0
            || activityGoodsPojo.getActivePrice() / activityGoodsPojo.getSellPrice() * 10 >= 10.0) {
          activityGoodsPojo.setTips("无折扣");
        } else {
          activityGoodsPojo.setTips(d);
        }
      }
      activityGoodsService.insertActivityGoods(activityGoodsPojo);
      FileUtil.alertMessageBySkip("添加成功！", "specialShowList.do");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public String insertActivityGoods() throws Exception {
    if (activityGoodsPojo.getActivityNum() < activityGoodsPojo.getActivityStock()) {
      if (op == 2) {
        FileUtil.alertMessageBySkip("活动产品库存大于活动产品总量，添加失败！", "thematicActivityManage.do?op=2");
      } else {
        FileUtil.alertMessageBySkip("活动产品库存大于活动产品总量，添加失败！", "activityTimeManage.do?op=1");
      }

    } else {

      if (activityGoodsPojo != null) {
        String d =
            new BigDecimal(activityGoodsPojo.getActivePrice() / activityGoodsPojo.getSellPrice()
                * 10).setScale(1, BigDecimal.ROUND_HALF_UP)
                + "折";
        if (activityGoodsPojo.getActivePrice() / activityGoodsPojo.getSellPrice() * 10 <= 0.0
            || activityGoodsPojo.getActivePrice() / activityGoodsPojo.getSellPrice() * 10 >= 10.0) {
          activityGoodsPojo.setTips("无折扣");
        } else {
          activityGoodsPojo.setTips(d);
        }

      }
      activityGoodsService.insertActivityGoods(activityGoodsPojo);
      if (op == 2) {
        FileUtil.alertMessageBySkip("添加成功！", "thematicActivityManage.do?op=2");
      } else {
        FileUtil.alertMessageBySkip("添加成功！", "activityTimeManage.do?op=1");
      }
    }
    return null;
  }

  public String insertActivityGoodsAll() throws Exception {
    ProductPojo product = new ProductPojo();
    if (tids != null) {
      for (String tid : tids) {
        try {
          Map<String, Object> map = new HashMap<String, Object>();
          map.put("timeId", timeId);
          map.put("productId", Long.valueOf(tid));
          List<ActivityGoodsPojo> activityGoods =
              activityGoodsService.findActivityGoodsByProductId(map);
          if (activityGoods == null || activityGoods.size() == 0) {
            ActivityGoodsPojo activityGoodsPojo = new ActivityGoodsPojo();
            activityGoodsPojo.setProductId(Long.valueOf(tid));
            activityGoodsPojo.setTimeId(timeId);
            activityGoodsPojo.setStatus(0);

            product.setId(activityGoodsPojo.getProductId());
            product = productService.findProduct(product);
            if (product != null) {
              // activityGoodsPojo.setSellPrice(product.getDistributionPrice());
              // 产品原价
              activityGoodsPojo.setSellPrice(product.getSellingPrice());
              activityGoodsPojo.setActivePrice(product.getDistributionPrice());
            }
            activityGoodsService.insertActivityGoods(activityGoodsPojo);
          }
        } catch (Exception e) {
          e.printStackTrace();
          // FileUtil.alertMessageBySkip("添加失败！",
          // "activityTimeManage.do");
        }
      }
      if (t == 2) {
        FileUtil.alertMessageBySkip("添加成功！", "addAgencyCollect.do?t=2&typeName=" + typeName
            + "&timeId=" + timeId + "&activityTime=" + activityTime);
      } else {
        FileUtil.alertMessageBySkip("添加成功！", "addAgencyCollect.do?t=1&timeId=" + timeId
            + "&activityTime=" + activityTime + "&activityDate=" + activityDate);
      }
    } else {
      if (t == 2) {
        FileUtil.alertMessageBySkip("请先勾选！", "addAgencyCollect.do?t=2&typeName=" + typeName
            + "&timeId=" + timeId + "&activityTime=" + activityTime);
      } else {
        FileUtil.alertMessageBySkip("请先勾选！", "addAgencyCollect.do?t=1&timeId=" + timeId
            + "&activityTime=" + activityTime + "&activityDate=" + activityDate);
      }
    }

    return null;
  }

  public Integer getT() {
    return t;
  }

  public void setT(Integer t) {
    this.t = t;
  }

  public Integer getOp() {
    return op;
  }

  public void setOp(Integer op) {
    this.op = op;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getActivityDate() {
    return activityDate;
  }

  public void setActivityDate(String activityDate) {
    this.activityDate = activityDate;
  }

}
