package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.BrandDicPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.service.BrandDicService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.UserBrandService;

public class UserBrandWebAction extends SuperAction {

  @Autowired
  private UserBrandService userBrandService;
  @Autowired
  private BrandDicService brandDicService;
  @Autowired
  private ProductTypeService productTypeService;

  private UserBrandPojo userBrandPojo;
  private List<UserBrandPojo> userBrandPojos;
  private BrandDicPojo brandDicPojo;
  private List<BrandDicPojo> brandDicPojos;
  private ProductTypePojo productTypePojo;
  private List<ProductTypePojo> productTypePojos;
  private File file1, file2, file3;

  public UserBrandPojo getUserBrandPojo() {
    return userBrandPojo;
  }

  public void setUserBrandPojo(UserBrandPojo userBrandPojo) {
    this.userBrandPojo = userBrandPojo;
  }

  public List<UserBrandPojo> getUserBrandPojos() {
    return userBrandPojos;
  }

  public void setUserBrandPojos(List<UserBrandPojo> userBrandPojos) {
    this.userBrandPojos = userBrandPojos;
  }

  public BrandDicPojo getBrandDicPojo() {
    return brandDicPojo;
  }

  public void setBrandDicPojo(BrandDicPojo brandDicPojo) {
    this.brandDicPojo = brandDicPojo;
  }

  public List<BrandDicPojo> getBrandDicPojos() {
    return brandDicPojos;
  }

  public void setBrandDicPojos(List<BrandDicPojo> brandDicPojos) {
    this.brandDicPojos = brandDicPojos;
  }

  public ProductTypePojo getProductTypePojo() {
    return productTypePojo;
  }

  public void setProductTypePojo(ProductTypePojo productTypePojo) {
    this.productTypePojo = productTypePojo;
  }

  public List<ProductTypePojo> getProductTypePojos() {
    return productTypePojos;
  }

  public void setProductTypePojos(List<ProductTypePojo> productTypePojos) {
    this.productTypePojos = productTypePojos;
  }

  /**
   * 品牌列表前端显示
   * 
   * @return
   * @throws Exception
   */
  public String getBrandListWeb() throws Exception {
    ActionContext actionContext = ActionContext.getContext();
    Map<String, Object> map = new HashMap<String, Object>();
    SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
    int count = 0;
    if (logiPojo != null) {
      map.put("userId", logiPojo.getId());
      count = userBrandService.findUserBrandCount(map);
    }
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(count);
    return SUCCESS;
  }

  /**
   * 添加品牌前端显示
   * 
   * @return
   * @throws Exception
   */
  public String getBrandAddWeb() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("status", 1);
    brandDicPojos = brandDicService.findBrandDicList(map);
    ProductTypePojo productTypePojo = new ProductTypePojo();
    productTypePojo.setPid(-1l);
    productTypePojo.setStatus(1);
    productTypePojos = productTypeService.getProductTypeByPid(productTypePojo);
    for (int i = 0; i < productTypePojos.size(); i++) {
      if (productTypePojos.get(i).getName().equals("0-3岁婴幼儿玩具")
          || productTypePojos.get(i).getName().equals("3-6岁儿童玩具")
          || productTypePojos.get(i).getName().equals("6岁以上玩具")) {
        productTypePojos.remove(i);
      }
    }
    return SUCCESS;
  }

  /**
   * 品牌列表数据前端显示
   * 
   * @return
   * @throws SQLException
   */
  public String getBrandListData() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
    if (logiPojo != null) {
      map.put("userId", logiPojo.getId());
      userBrandPojos = userBrandService.findUserBrandByUserId(map);
    }
    JSONArray json = JSONArray.fromObject(userBrandPojos);
    // page.setRowCount(userBrandPojos.size());
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 质检结果前端显示
   * 
   * @return
   * @throws Exception
   */
  public String getQCResultWeb() throws Exception {

    return SUCCESS;
  }

  /**
   * 添加品牌提交
   * 
   * @return
   * @throws Exception
   */
  public String getBrandAddSubmit() throws Exception {
    if (userBrandPojo != null) {
      try {
        ActionContext actionContext = ActionContext.getContext();
        SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
        if (logiPojo != null) {
          userBrandPojo.setUserId(logiPojo.getId());
          userBrandPojo.setSorting(0);
          userBrandPojo.setStatus(0);
          userBrandPojo.setManageLevel("1");
          if (file1 != null) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/userBrand")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/userBrand/", file1);
            userBrandPojo.setImage1(file_name);
          }
          if (file2 != null) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/userBrand")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/userBrand/", file2);
            userBrandPojo.setImage2(file_name);
          }
          if (file3 != null) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/userBrand")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/userBrand/", file3);
            userBrandPojo.setImage3(file_name);
          }
          Map<String, Object> map = new HashMap<String, Object>();
          map.put("userId", logiPojo.getId());
          map.put("brandId", userBrandPojo.getBrandId());
          if (userBrandService.findUserBrandList(map).size() != 0) {
            FileUtil.alertMessageBySkip("该品牌已添加过！", "getBrandAddWeb.do");
            return null;
          }
          userBrandService.insertUserBrand(userBrandPojo);
          FileUtil.alertMessageBySkip("品牌添加成功，待运营人员审核！", "getBrandListWeb.do");
        }
      } catch (Exception e) {

        FileUtil.alertMessageBySkip("添加失败！", "getBrandAddWeb.do");
      }
    }
    return null;
  }

  public File getFile1() {
    return file1;
  }

  public void setFile1(File file1) {
    this.file1 = file1;
  }

  public File getFile2() {
    return file2;
  }

  public void setFile2(File file2) {
    this.file2 = file2;
  }

  public File getFile3() {
    return file3;
  }

  public void setFile3(File file3) {
    this.file3 = file3;
  }
}
