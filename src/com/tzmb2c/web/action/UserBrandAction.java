package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.BrandDicPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.service.BrandDicService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.UserBrandService;

/**
 * 品牌管理 user_brand 2015-11-30
 * 
 * @author by zzh
 */
public class UserBrandAction extends SuperAction {
  @Autowired
  private UserBrandService userBrandService;
  @Autowired
  private ProductTypeService productTypeService;
  @Autowired
  private BrandDicService brandDicService;
  private UserBrandPojo userBrandPojo;
  private UserBrandPojo userBrandPojos;
  private ProductTypePojo productTypePojo;
  private String result;
  private String[] tids;
  private List<UserBrandPojo> userBrandPojoList;
  private File upfile1;
  private File upfile2;
  private File upfile3;
  private File upfile;
  private BrandDicPojo brandDicPojo;

  public BrandDicPojo getBrandDicPojo() {
    return brandDicPojo;
  }

  public void setBrandDicPojo(BrandDicPojo brandDicPojo) {
    this.brandDicPojo = brandDicPojo;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public UserBrandPojo getUserBrandPojo() {
    return userBrandPojo;
  }

  public void setUserBrandPojo(UserBrandPojo userBrandPojo) {
    this.userBrandPojo = userBrandPojo;
  }

  public UserBrandPojo getUserBrandPojos() {
    return userBrandPojos;
  }

  public void setUserBrandPojos(UserBrandPojo userBrandPojos) {
    this.userBrandPojos = userBrandPojos;
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

  public File getUpfile1() {
    return upfile1;
  }

  public void setUpfile1(File upfile1) {
    this.upfile1 = upfile1;
  }

  /**
   * 品牌表总数目
   * 
   * @return
   * @throws Exception
   */
  public String userBrandListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("isdelete", "0");
      if (userBrandPojo != null) {
        map.put("brandName", userBrandPojo.getBrandName().trim());
        map.put("startDateStr", userBrandPojo.getStartDateStr());
        map.put("endDateStr", userBrandPojo.getEndDateStr());
        map.put("status", userBrandPojo.getStatus());
      }
      int i = userBrandService.findUserBrandCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 品牌列表
   * 
   * @return
   * @throws Exception
   */
  public String userBrandListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      map.put("isdelete", "0");
      if (userBrandPojo != null) {
        map.put("brandName", userBrandPojo.getBrandName().trim());
        map.put("startDateStr", userBrandPojo.getStartDateStr());
        map.put("endDateStr", userBrandPojo.getEndDateStr());
        map.put("status", userBrandPojo.getStatus());
      }
      if (userBrandPojos != null) {
        map.put("userId", userBrandPojos.getUserId());
      }
      userBrandPojoList = userBrandService.findUserBrandList(map);
      for (UserBrandPojo userBrand : userBrandPojoList) {
        ProductTypePojo productTypePojo = new ProductTypePojo();
        if (userBrand.getMainCategory() != null) {
          productTypePojo.setId(Long.valueOf(userBrand.getMainCategory()));
        }
        ProductTypePojo productTypePojos = productTypeService.findProductType(productTypePojo);
        if (productTypePojos != null) {
          userBrand.setMainCategoryName(productTypePojos.getName());
        }
        /*
         * BrandDicPojo brandDicPojos = brandDicService.findBrandDicById(userBrand.getBrandId()); if
         * (brandDicPojos != null) { userBrand.setBrandName(brandDicPojos.getBrand()); }
         */
      }
      JSONArray json = JSONArray.fromObject(userBrandPojoList);
      page.setRowCount(userBrandPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 品牌列表
   * 
   * @return
   * @throws Exception
   */
  public String findUserBrandNameByUserId() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      String nowDate = StringUtil.dateToString(new Date());
      map.put("startDateStr", nowDate);
      map.put("endDateStr", nowDate);
      map.put("status", 1);
      if (userBrandPojo != null) {
        map.put("userId", userBrandPojo.getUserId());
      }
      userBrandPojoList = userBrandService.findUserBrandNameByUserId(map);
      JSONArray json = JSONArray.fromObject(userBrandPojoList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 根据id删除品牌
   * 
   * @return
   */
  public String delUserBrand() throws Exception {
    try {
      /*
       * Map<String, Object> map = new HashMap<String, Object>();
       * map.put("sceneId",scenePojo.getId());
       * sceneProductPojoList=sceneProductService.findSceneProductList(map); for(SceneProductPojo
       * SceneProduct:sceneProductPojoList){
       * activityGoodsService.delActivityGoods(SceneProduct.getGoodId()); }
       */
      /*
       * specialProductService.delSpecialProductBySpecialId(specialShowPojo.getId());
       * specialShowPojo = specialShowService.findSpecialShowById(specialShowPojo.getId());
       */
      userBrandService.delUserBrand(userBrandPojo.getId());
      // 删除专场产品设置
      /*
       * activityGoodsService.delActivityGoodsByTimeId(specialShowPojo.getActivityId());
       * activityTimeService.delActivityTime(specialShowPojo.getActivityId());
       */
      FileUtil.alertMessageBySkip("删除成功！", "userBrandList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("删除失败！", "userBrandList.do");
    }
    return null;
  }

  /**
   * 根据id批量删除品牌
   * 
   * @return
   */
  public String delUserBrandAll() {
    try {
      new HashMap<String, Object>();
      for (String id : tids) {
        /*
         * map.put("sceneId",Long.valueOf(id));
         * sceneProductPojoList=sceneProductService.findSceneProductList(map); for(SceneProductPojo
         * SceneProduct:sceneProductPojoList){
         * activityGoodsService.delActivityGoods(SceneProduct.getGoodId()); }
         */
        /*
         * specialProductService.delSpecialProductBySpecialId(Long.valueOf(id));
         * specialShowPojo=specialShowService.findSpecialShowById(Long.valueOf(id));
         */
        userBrandService.delUserBrand(Long.valueOf(id));
        // 删除活动产品设置
        /*
         * activityGoodsService.delActivityGoodsByTimeId(specialShowPojo.getActivityId());
         * activityTimeService.delActivityTime(specialShowPojo.getActivityId());
         */
      }
      FileUtil.alertMessageBySkip("全部删除成功！", "userBrandList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部删除失败！", "userBrandList.do");
    }
    return null;
  }

  /**
   * 跳转至编辑品牌页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateUserBrand() throws Exception {
    userBrandPojo = userBrandService.findUserBrandById(userBrandPojo.getId());
    return SUCCESS;
  }

  /**
   * 编辑品牌
   * 
   * @return
   * @throws Throwable
   */
  public String updateUserBrand() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext()
                .getRealPath("/upfiles/businessCenter/brandDic") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/businessCenter/brandDic", upfile);
        brandDicPojo.setLogo(file_name);
      }
      brandDicPojo.setBrand(userBrandPojo.getBrandName());
      brandDicService.updateBrandDic(brandDicPojo);
      if (upfile1 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userBrand")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userBrand/", upfile1);
        userBrandPojo.setImage1(file_name);
      }
      if (upfile2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userBrand")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userBrand/", upfile2);
        userBrandPojo.setImage2(file_name);
      }
      if (upfile3 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userBrand")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userBrand/", upfile3);
        userBrandPojo.setImage3(file_name);
      }
      userBrandService.updateUserBrand(userBrandPojo);
      FileUtil.alertMessageBySkip("提交成功！", "userBrandList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "userBrandList.do");
    }
    return null;
  }

  /**
   * 通过审核品牌
   * 
   * @return
   */
  public String checkUserBrand() throws SQLException {
    try {
      userBrandService.checkUserBrand(userBrandPojo.getId());
      FileUtil.alertMessageBySkip("通过审核成功！", "userBrandList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("通过审核失败！", "userBrandList.do");
    }
    return null;
  }

  /**
   * 取消审核品牌
   * 
   * @return
   */
  public String uncheckUserBrand() throws SQLException {
    try {
      userBrandService.uncheckUserBrand(userBrandPojo.getId());
      FileUtil.alertMessageBySkip("取消审核成功！", "userBrandList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("取消审核失败！", "userBrandList.do");
    }
    return null;
  }

  /**
   * 根据id批量通过审核品牌
   * 
   * @return
   */
  public String checkUserBrandAll() {
    try {
      for (String id : tids) {
        userBrandService.checkUserBrand(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部通过审核成功！", "userBrandList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部通过审核失败！", "userBrandList.do");
    }
    return null;
  }

  /**
   * 跳转至新增品牌页面
   * 
   * @return
   */
  public String goAddUserBrandList() {
    return SUCCESS;
  }

  /**
   * 提交新增品牌
   * 
   * @return
   * @throws Throwable
   */
  public String addUserBrandListOK() throws Throwable {
    try {
      /*
       * if (specialShowPojo.getBeginTimeStr()!=null && specialShowPojo.getEndTimeStr()!=null) {
       * Date endTime=new Date(); endTime= StringUtil.stringDate(specialShowPojo.getEndTimeStr());
       * activityTimePojo.setEndTime(specialShowPojo.getEndTimeStr());
       * specialShowPojo.setEndTime(endTime); Date beginTime=new Date(); beginTime =
       * StringUtil.stringDate(specialShowPojo.getBeginTimeStr());
       * activityTimePojo.setBeginTime(specialShowPojo.getBeginTimeStr());
       * specialShowPojo.setBeginTime(beginTime); }
       */
      // 新增品牌字典
      BrandDicPojo brandDicPojo = new BrandDicPojo();
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext()
                .getRealPath("/upfiles/businessCenter/brandDic") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/businessCenter/brandDic", upfile);
        brandDicPojo.setLogo(file_name);
      }
      brandDicPojo.setStatus(1);
      brandDicPojo.setBrand(userBrandPojo.getBrandName());
      brandDicService.insertBrandDic(brandDicPojo);
      // 新增用户品牌
      if (upfile1 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userBrand")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userBrand/", upfile1);
        userBrandPojo.setImage1(file_name);
      }
      if (upfile2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userBrand")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userBrand/", upfile2);
        userBrandPojo.setImage2(file_name);
      }
      if (upfile3 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userBrand")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userBrand/", upfile3);
        userBrandPojo.setImage3(file_name);
      }
      userBrandPojo.setBrandId(brandDicPojo.getId());
      // 默认用户id为3的供应商
      userBrandPojo.setUserId(3l);
      userBrandPojo.setMainCategory(String.valueOf(-7));
      userBrandService.insertUserBrand(userBrandPojo);
      FileUtil.alertMessageBySkip("新增品牌成功！", "userBrandList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增品牌失败！", "userBrandList.do");
    }
    return null;
  }

  public List<UserBrandPojo> getUserBrandPojoList() {
    return userBrandPojoList;
  }

  public void setUserBrandPojoList(List<UserBrandPojo> userBrandPojoList) {
    this.userBrandPojoList = userBrandPojoList;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public File getUpfile3() {
    return upfile3;
  }

  public void setUpfile3(File upfile3) {
    this.upfile3 = upfile3;
  }

  public ProductTypePojo getProductTypePojo() {
    return productTypePojo;
  }

  public void setProductTypePojo(ProductTypePojo productTypePojo) {
    this.productTypePojo = productTypePojo;
  }
}
