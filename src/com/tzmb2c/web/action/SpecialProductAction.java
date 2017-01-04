package com.tzmb2c.web.action;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.SpecialProductPojo;
import com.tzmb2c.web.pojo.SpecialShowPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.SpecialProductService;
import com.tzmb2c.web.service.SpecialShowService;

/**
 * 专场产品Action 2015-11-24
 * 
 * @author zzh
 */
public class SpecialProductAction extends SuperAction {
  @Autowired
  private SpecialProductService specialProductService;
  @Autowired
  private SpecialShowService specialShowService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  private SpecialProductPojo specialProductPojo;
  private SpecialShowPojo specialShowPojo;
  private ActivityGoodsPojo activityGoodsPojo;
  private String result;
  private String[] tids;
  private List<SpecialProductPojo> specialProductPojoList;
  private File upfile;
  private Long id;
  private Long activityId;

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

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  /**
   * 该专场产品条数目
   * 
   * @return
   * @throws Exception
   */
  public String specialProductListCount() throws Exception {

    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (specialProductPojo != null) {
        map.put("title", specialProductPojo.getTitle());
        map.put("specialId", specialProductPojo.getSpecialId());
        map.put("specialName", specialProductPojo.getSpecialName());
        map.put("productName", specialProductPojo.getProductName());
      }
      int i = specialProductService.findSpecialProductCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 该专场产品列表
   * 
   * @return
   * @throws Exception
   */
  public String specialProductListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (specialProductPojo != null) {
        map.put("title", specialProductPojo.getTitle());
        map.put("specialId", specialProductPojo.getSpecialId());
        map.put("specialName", specialProductPojo.getSpecialName());
        map.put("productName", specialProductPojo.getProductName());
      }
      specialProductPojoList = specialProductService.findSpecialProductList(map);
      for (SpecialProductPojo specialProductPojo : specialProductPojoList) {
        BigDecimal fact = new BigDecimal(String.valueOf(specialProductPojo.getSpecialPrice()));
        BigDecimal sell = new BigDecimal(String.valueOf(specialProductPojo.getSellPrice()));
        BigDecimal tips =
            fact.divide(sell, 3, RoundingMode.HALF_UP).multiply(new BigDecimal(10))
                .setScale(1, RoundingMode.HALF_UP);
        if (tips.doubleValue() < 10) {
          specialProductPojo.setTips(tips.toPlainString() + "折");
        } else {
          specialProductPojo.setTips("无折扣");
        }
      }
      JSONArray json = JSONArray.fromObject(specialProductPojoList);
      page.setRowCount(specialProductPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 专场活动详情页面.
   * 
   * @return
   * @throws SQLException
   */
  // public String goSceneProductWeb() throws SQLException {
  // ActionContext ac = ActionContext.getContext();
  // List<SceneProductPojo> sceneProducts1 = new ArrayList<SceneProductPojo>();
  // List<SceneProductPojo> sceneProducts0 = new ArrayList<SceneProductPojo>();
  // if (id == null || id == 0L) {
  // return SUCCESS;
  // }
  // scenePojo = sceneService.findSceneById(id);
  // if (scenePojo != null) {
  // Map<String, Object> map1 = new HashMap<String, Object>();
  // map1.put("sceneId", scenePojo.getId());
  // map1.put("status", 1);
  // map1.put("isIntroduce", 1);
  // sceneProducts1 = sceneProductService.findSceneProductList(map1);
  // if (sceneProducts1 != null && sceneProducts1.size() > 0) {
  // ac.put("sceneProductPojos1", sceneProducts1);
  /*
   * for(SceneProductPojo u:sceneProducts1){ ac.put("title"+u.getSorting(), u.getTitle());
   * ac.put("image"+u.getSorting(), u.getImage()); ac.put("ProductImage"+u.getSorting(),
   * u.getImage()); ac.put("introduction"+u.getSorting(), u.getIntroduction());
   * ac.put("productId"+u.getSorting(), u.getProductId()); ac.put("productName"+u.getSorting(),
   * u.getProductName()); ac.put("sellPrice"+u.getSorting(), u.getSellPrice());
   * ac.put("scenePrice"+u.getSorting(), u.getScenePrice()); }
   */
  // Map<String, Object> map0 = new HashMap<String, Object>();
  // map0.put("sceneId", scenePojo.getId());
  // map0.put("status", 1);
  // map0.put("isIntroduce", 0);
  // sceneProducts0 = sceneProductService.findSceneProductList(map0);
  // if (sceneProducts0 != null && sceneProducts0.size() > 0) {
  // ac.put("sceneProductPojos0", sceneProducts0);
  // }
  // }
  // } else {
  // FileUtil.alertMessageBySkip("场景活动已结束", "goIndexWeb.do");
  // return null;
  // }
  // return SUCCESS;
  // }
  /**
   * 根据id删除专场产品
   * 
   * @return
   */
  public String delSpecialProduct() throws Exception {
    try {
      specialProductPojo = specialProductService.findSpecialProductById(specialProductPojo.getId());
      specialProductService.delSpecialProduct(specialProductPojo.getId());
      activityGoodsService.delActivityGoods(specialProductPojo.getGoodId());
      FileUtil.alertMessageBySkip("删除成功！", "specialProductList.do?specialProductPojo.specialId="
          + specialProductPojo.getSpecialId());
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "specialProductList.do?specialProductPojo.specialId="
          + specialProductPojo.getSpecialId());
    }
    return null;
  }

  /**
   * 根据id批量删除专场产品
   * 
   * @return
   */
  public String delSpecialProductAll() {
    try {
      for (String id : tids) {
        specialProductPojo = specialProductService.findSpecialProductById(Long.valueOf(id));
        specialProductService.delSpecialProduct(Long.valueOf(id));
        activityGoodsService.delActivityGoods(specialProductPojo.getGoodId());
      }
      FileUtil.alertMessageBySkip("全部删除成功！", "specialProductList.do?specialProductPojo.specialId="
          + specialProductPojo.getSpecialId());
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("全部删除失败！", "specialProductList.do?specialProductPojo.specialId="
          + specialProductPojo.getSpecialId());
    }
    return null;
  }

  /**
   * 跳转至编辑专场产品页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateSpecialProduct() throws Exception {
    specialProductPojo = specialProductService.findSpecialProductById(specialProductPojo.getId());
    BigDecimal fact = new BigDecimal(String.valueOf(specialProductPojo.getSpecialPrice()));
    BigDecimal sell = new BigDecimal(String.valueOf(specialProductPojo.getSellPrice()));
    BigDecimal tips =
        fact.divide(sell, 3, RoundingMode.HALF_UP).multiply(new BigDecimal(10))
            .setScale(1, RoundingMode.HALF_UP);
    if (tips.doubleValue() < 10) {
      specialProductPojo.setTips(tips.toPlainString() + "折");
    } else {
      specialProductPojo.setTips("无折扣");
    }
    return SUCCESS;
  }

  /**
   * 编辑专场产品
   * 
   * @return
   * @throws Throwable
   */
  public String updateSpecialProduct() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
        specialProductPojo.setImage(file_name);
      }
      activityGoodsPojo = new ActivityGoodsPojo();
      Date time = new Date();
      activityGoodsPojo.setSorting(specialProductPojo.getSorting());
      activityGoodsPojo.setSellPrice(specialProductPojo.getSellPrice());
      activityGoodsPojo.setActivePrice(specialProductPojo.getSpecialPrice());
      BigDecimal fact = new BigDecimal(String.valueOf(specialProductPojo.getSpecialPrice()));
      BigDecimal sell = new BigDecimal(String.valueOf(specialProductPojo.getSellPrice()));
      BigDecimal tips =
          fact.divide(sell, 3, RoundingMode.HALF_UP).multiply(new BigDecimal(10))
              .setScale(1, RoundingMode.HALF_UP);
      if (tips.doubleValue() < 10) {
        activityGoodsPojo.setTips(tips.toPlainString() + "折");
      } else {
        activityGoodsPojo.setTips("无折扣");
      }
      activityGoodsPojo.setStatus(specialProductPojo.getStatus());
      activityGoodsPojo.setUpdateDate(time);
      activityGoodsPojo.setActivityStock(specialProductPojo.getSpecialStock());
      activityGoodsPojo.setActivityNum(specialProductPojo.getSpecialNum());
      specialProductService.updateSpecialProduct(specialProductPojo);
      specialProductPojo = specialProductService.findSpecialProductById(specialProductPojo.getId());
      activityGoodsPojo.setId(specialProductPojo.getGoodId());
      activityGoodsService.updateActivityGoods(activityGoodsPojo);
      FileUtil.alertMessageBySkip("编辑成功！", "specialProductList.do?specialProductPojo.specialId="
          + specialProductPojo.getSpecialId());
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("编辑失败！", "specialProductList.do?specialProductPojo.specialId="
          + specialProductPojo.getSpecialId());
    }
    return null;
  }

  /**
   * 通过审核专场产品
   * 
   * @return
   */
  public String checkSpecialProduct() throws SQLException {
    try {
      specialProductPojo = specialProductService.findSpecialProductById(specialProductPojo.getId());
      activityGoodsService.checkActivityGoods(specialProductPojo.getGoodId());
      // sceneProductService.checkSceneProductById(sceneProductPojo.getId());
      FileUtil.alertMessageBySkip("通过审核成功！", "specialProductList.do?specialProductPojo.specialId="
          + specialProductPojo.getSpecialId());
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("通过审核失败！", "specialProductList.do?specialProductPojo.specialId="
          + specialProductPojo.getSpecialId());
    }
    return null;
  }

  /**
   * 取消审核专场产品
   * 
   * @return
   */
  public String uncheckSpecialProduct() throws SQLException {
    try {
      specialProductPojo = specialProductService.findSpecialProductById(specialProductPojo.getId());
      activityGoodsService.uncheckActivityGoods(specialProductPojo.getGoodId());
      // sceneProductService.uncheckSceneProductById(sceneProductPojo.getId());
      FileUtil.alertMessageBySkip("取消审核成功！", "specialProductList.do?specialProductPojo.specialId="
          + specialProductPojo.getSpecialId());
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("取消审核失败！", "specialProductList.do?specialProductPojo.specialId="
          + specialProductPojo.getSpecialId());
    }
    return null;
  }

  /**
   * 根据id批量通过审核专场产品
   * 
   * @return
   */
  public String checkSpecialProductAll() {
    try {
      for (String id : tids) {
        specialProductPojo = specialProductService.findSpecialProductById(Long.valueOf(id));
        activityGoodsService.checkActivityGoods(specialProductPojo.getGoodId());
        // sceneProductService.checkSceneProductById(Long.valueOf(id));
      }
      FileUtil
          .alertMessageBySkip("全部通过审核成功！", "specialProductList.do?specialProductPojo.specialId="
              + specialProductPojo.getSpecialId());
    } catch (Exception e) {
      FileUtil
          .alertMessageBySkip("全部通过审核失败！", "specialProductList.do?specialProductPojo.specialId="
              + specialProductPojo.getSpecialId());
    }
    return null;
  }

  /**
   * 跳转至新增场景产品页面
   * 
   * @return
   */
  public String goAddSpecialProductList() {
    return SUCCESS;
  }

  /**
   * 提交新增专场产品
   * 
   * @return
   */
  public String addSpecialProductListOk() {
    try {
      if (specialProductPojo != null) {
        Map<String, Object> param = new HashMap<String, Object>();
        JSONObject.fromObject(param);
        activityGoodsPojo = new ActivityGoodsPojo();
        Date time = new Date();
        activityGoodsPojo.setSorting(specialProductPojo.getSorting());
        activityGoodsPojo.setSellPrice(specialProductPojo.getSellPrice());
        activityGoodsPojo.setActivePrice(specialProductPojo.getSpecialPrice());
        BigDecimal fact = new BigDecimal(String.valueOf(specialProductPojo.getSpecialPrice()));
        BigDecimal sell = new BigDecimal(String.valueOf(specialProductPojo.getSellPrice()));
        BigDecimal tips =
            fact.divide(sell, 3, RoundingMode.HALF_UP).multiply(new BigDecimal(10))
                .setScale(1, RoundingMode.HALF_UP);
        if (tips.doubleValue() < 10) {
          activityGoodsPojo.setTips(tips.toPlainString() + "折");
        } else {
          activityGoodsPojo.setTips("无折扣");
        }
        activityGoodsPojo.setStatus(specialProductPojo.getStatus());
        activityGoodsPojo.setUpdateDate(time);
        activityGoodsPojo.setActivityStock(specialProductPojo.getSpecialStock());
        activityGoodsPojo.setActivityNum(specialProductPojo.getSpecialNum());
        specialProductService.insertSpecialProduct(specialProductPojo);
        specialProductPojo =
            specialProductService.findSpecialProductById(specialProductPojo.getId());
        activityGoodsPojo.setId(specialProductPojo.getGoodId());
        activityGoodsService.insertActivityGoods(activityGoodsPojo);
      }
      FileUtil.alertMessageBySkip("新增成功！", "specialProductList.do?specialProductPojo.specialId="
          + specialProductPojo.getSpecialId());
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("新增失败！", "specialProductList.do?specialProductPojo.specialId="
          + specialProductPojo.getSpecialId());
    }
    return null;
  }

  public SpecialProductPojo getSpecialProductPojo() {
    return specialProductPojo;
  }

  public void setSpecialProductPojo(SpecialProductPojo specialProductPojo) {
    this.specialProductPojo = specialProductPojo;
  }

  public SpecialShowPojo getSpecialShowPojo() {
    return specialShowPojo;
  }

  public void setSpecialShowPojo(SpecialShowPojo specialShowPojo) {
    this.specialShowPojo = specialShowPojo;
  }

  public List<SpecialProductPojo> getSpecialProductPojoList() {
    return specialProductPojoList;
  }

  public void setSpecialProductPojoList(List<SpecialProductPojo> specialProductPojoList) {
    this.specialProductPojoList = specialProductPojoList;
  }
}
