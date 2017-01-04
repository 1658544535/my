package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.HelpPojo;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserAttestationPojo;
import com.tzmb2c.web.service.ConsumerService;
import com.tzmb2c.web.service.HelpService;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserAttestationService;

public class ManufacturerAction extends SuperAction {

  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private ConsumerService consumerService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private HelpService helpService;
  @Autowired
  private UserAttestationService userAttestationService;
  @Autowired
  private ProductTypeService productTypeService;

  private HelpPojo helpPojo;

  private ManufacturerPojo manufacturer, goUpdateManufacturer;
  private ShopPojo shop;
  private String result;
  private String[] tids;
  private File upfile, upfile2, upfile3;
  private String os;

  private UserAttestationPojo userAttestationPojo;
  private List<ProductTypePojo> productTypePojos;

  public List<ProductTypePojo> getProductTypePojos() {
    return productTypePojos;
  }

  public void setProductTypePojos(List<ProductTypePojo> productTypePojos) {
    this.productTypePojos = productTypePojos;
  }

  public ManufacturerPojo getGoUpdateManufacturer() {
    return goUpdateManufacturer;
  }

  public void setGoUpdateManufacturer(ManufacturerPojo goUpdateManufacturer) {
    this.goUpdateManufacturer = goUpdateManufacturer;
  }

  public UserAttestationPojo getUserAttestationPojo() {
    return userAttestationPojo;
  }

  public void setUserAttestationPojo(UserAttestationPojo userAttestationPojo) {
    this.userAttestationPojo = userAttestationPojo;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public HelpPojo getHelpPojo() {
    return helpPojo;
  }

  public void setHelpPojo(HelpPojo helpPojo) {
    this.helpPojo = helpPojo;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public ManufacturerPojo getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(ManufacturerPojo manufacturer) {
    this.manufacturer = manufacturer;
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

  public ShopPojo getShop() {
    return shop;
  }

  public void setShop(ShopPojo shop) {
    this.shop = shop;
  }

  public File getUpfile3() {
    return upfile3;
  }

  public void setUpfile3(File upfile3) {
    this.upfile3 = upfile3;
  }

  public String getManufacturerCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    ActionContext ac = ActionContext.getContext();
    // ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
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
    ac.put("mainCategory", productTypePojos);
    page.setRowCount(manufacturerService.manufacturerAllCount(manufacturer));
    return SUCCESS;
  }

  public String manufacturerAllList() {
    JSONArray json =
        JSONArray.fromObject(manufacturerService.manufacturerAllList(manufacturer, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String manufacturerChecks() {
    if (tids != null) {
      manufacturerService.manufacturerChecks(tids);
      for (String tid : tids) {
        try {
          ManufacturerPojo manufacturerPojo =
              manufacturerService.findManufacturerById(Long.parseLong(tid));
          SysLoginPojo sysLoginPojo = new SysLoginPojo();
          SysLoginPojo loginPojo = UserUtil.getUser();
          if (UserUtil.getUser() == null) {
            return "loginpage";
          } else {
            sysLoginPojo.preUpdate(loginPojo);
          }
          sysLoginPojo.setId(manufacturerPojo.getUserId());
          sysLoginPojo.setType("2");
          sysLoginService.updateType(sysLoginPojo);
        } catch (SQLException e) {

          e.printStackTrace();
        }
      }
      FileUtil.alertMessageBySkip("审核成功！", "manufacturer.do");
    } else {
      FileUtil.alertMessageBySkip("审核失败！", "manufacturer.do");
    }

    return null;
  }

  public String checkManufacturer() throws SQLException {
    try {
      manufacturerService.checkManufacturer(manufacturer.getId());
      ManufacturerPojo manufacturerPojo =
          manufacturerService.findManufacturerById(manufacturer.getId());
      SysLoginPojo sysLoginPojo = new SysLoginPojo();
      SysLoginPojo loginPojo = UserUtil.getUser();
      if (UserUtil.getUser() == null) {
        return "loginpage";
      } else {
        sysLoginPojo.preUpdate(loginPojo);
      }
      sysLoginPojo.setId(manufacturerPojo.getUserId());
      sysLoginPojo.setType("2");
      sysLoginService.updateType(sysLoginPojo);
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  public String goFindManufacturer() throws Exception {
    ActionContext ac = ActionContext.getContext();
    // if (os != null || os.equals("3")) {
    if (os != null) {
      ac.put("manufacturerPojo",
          manufacturerService.findManufacturerByUserId(manufacturer.getUserId()));
    } else {
      ac.put("manufacturerPojo", manufacturerService.findManufacturerById(manufacturer.getId()));
    }

    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("scale", sysDictService.getSysDictListByType("scale"));
    ac.put("channel", sysDictService.getSysDictListByType("channel"));
    ac.put("isread", sysDictService.getSysDictListByType("isread"));
    // ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
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
    ac.put("mainCategory", productTypePojos);
    return SUCCESS;
  }

  public String updateManufacturer() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/manufacturer")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/manufacturer/", upfile);
      manufacturer.setLogo(file_name);
    }
    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      manufacturer.preUpdate(loginPojo);
    }
    manufacturerService.updateManufacturer(manufacturer);
    if (os != null && os.equals("3")) {
      FileUtil.alertMessageBySkip("修改成功！", "sysLoginMF.do?os=" + os);
    } else {
      FileUtil.alertMessageBySkip("修改成功！", "manufacturer.do");
    }

    return null;
  }

  public String delManufacturer() throws Exception {

    manufacturerService.delManufacturer(manufacturer.getId());
    FileUtil.alertMessageBySkip("删除成功！", "manufacturer.do");

    return null;
  }

  public String applyManufacturer() throws Exception {
    SysLoginPojo loginPojo = UserUtil.getWebUser();
    if (loginPojo != null) {

      if (loginPojo.getType().equals("1")) {
        if (manufacturerService.findManufacturerByUserId(loginPojo.getId()) != null) {
          FileUtil.alertMessageBySkip("您已提交过供应商的申请，请不要重复申请！", "goIndexWeb.do");
          return null;
        } else if (consumerService.findConsumerByUserId(loginPojo.getId()) != null) {
          FileUtil.alertMessageBySkip("您已提交过分销商的申请，请不要重复申请！", "goIndexWeb.do");
          return null;
        } else {
          return SUCCESS;
        }
      } else if (loginPojo.getType().equals("2")) {
        FileUtil.alertMessageBySkip("欢迎分销！", "gomySupplier.do");
        return null;
      } else {
        FileUtil.alertMessageBySkip("您当前登录的用户类型无法进行该操作！", "goIndexWeb.do");
        return null;
      }
    }

    return "loginweb";
  }

  public String addManufacturer() throws Throwable {

    SysLoginPojo loginPojo = UserUtil.getWebUser();
    if (loginPojo == null) {
      return "goIndexWeb.do";
    } else {
      manufacturer.preUpdate(loginPojo);
    }
    if (manufacturerService.findManufacturerByUserId(loginPojo.getId()) != null) {
      FileUtil.alertMessageBySkip("您已提交过供应商的申请，请不要重复申请！", "goIndexWeb.do");
    } else if (consumerService.findConsumerByUserId(loginPojo.getId()) != null) {
      FileUtil.alertMessageBySkip("您已提交过分销商的申请，请不要重复申请！", "goIndexWeb.do");
    } else {
      if (upfile != null || upfile2 != null) {
        UserAttestationPojo attestationPojo = new UserAttestationPojo();
        try {
          if (upfile != null) {
            String file_name = StringUtil.getCurrentDateStrByfu() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/attestation")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/attestation/", upfile);
            attestationPojo.setAttestationImage(file_name);
          } else {
            attestationPojo.setAttestationImage("");
          }
          if (upfile2 != null) {
            String file_name2 = StringUtil.getCurrentDateStrByfu() + ".jpg";
            String uploadPath2 =
                ServletActionContext.getServletContext().getRealPath("/upfiles/attestation")
                    + File.separator;
            FileUtil.uploadFile(file_name2, uploadPath2, "upfiles/attestation/", upfile2);
            attestationPojo.setUser3cImage(file_name2);
          } else {
            attestationPojo.setUser3cImage("");
          }
          Date createDate = new Date();
          attestationPojo.setUserId(loginPojo.getId());
          attestationPojo.setType(2);
          attestationPojo.setStatus(0);
          attestationPojo.setCreateDate(createDate);
          attestationPojo.setCreateBy(loginPojo.getId());
          userAttestationService.addAttestationInfo(attestationPojo);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      manufacturer.setCreateDate(new Date());
      manufacturer.setCreateBy(loginPojo.getId());
      manufacturerService.insertManufacturer(manufacturer);
      FileUtil.alertMessageBySkip("申请已提交，您的申请我们的工作人员将会在3个工作日内审核", "goIndexWeb.do");
    }
    return null;
  }

  /***
   * go修改供应商个人信息
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateManufacturerWeb() throws Exception {
    SysLoginPojo loginPojo = UserUtil.getWebUser();
    if (loginPojo == null) {
      return "goIndexWeb.do";
    } else {
      goUpdateManufacturer = manufacturerService.findManufacturerByUserId(loginPojo.getId());
    }
    return SUCCESS;
  }

  /***
   * 修改供应商个人信息
   * 
   * @return
   * @throws Exception
   */
  public String updateManufacturerWeb() throws Exception {
    SysLoginPojo loginPojo = UserUtil.getWebUser();
    if (loginPojo == null) {
      return "goIndexWeb.do";
    } else if (manufacturerService.findManufacturerByUserId(loginPojo.getId()) != null) {
      manufacturerService.updateManufacturerWeb(goUpdateManufacturer);
    } else {
      manufacturerService.insertManufacturer(goUpdateManufacturer);
    }
    FileUtil.alertMessageBySkip("操作成功！", "goManufacturerWeb.do");
    return null;
  }

  public String manufacturerAgreement() {
    HelpPojo pojo = new HelpPojo();
    pojo.setId(236L);
    helpPojo = helpService.goHelpUpdate(pojo);
    ActionContext ac = ActionContext.getContext();
    ac.put("tittle", helpPojo.getTitle());
    ac.put("content", helpPojo.getContent());
    return SUCCESS;
  }

  public String addManufacturerInfoAndShop() throws Exception {
    ActionContext ac = ActionContext.getContext();
    shop = new ShopPojo();
    shop.setUserId(manufacturer.getUserId());
    if (shopService.findShop(shop) != null) {
      FileUtil.alertMessageBySkip("该品牌下已有店铺了！", "shop.do");
      return null;
    }
    ac.put("manufacturerPojo",
        manufacturerService.findManufacturerByUserId(manufacturer.getUserId()));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("scale", sysDictService.getSysDictListByType("scale"));
    ac.put("channel", sysDictService.getSysDictListByType("channel"));
    ac.put("isread", sysDictService.getSysDictListByType("isread"));
    ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
    ac.put("shopPojo", shop);
    ac.put("isNew", sysDictService.getSysDictListByType("yes_no"));
    return SUCCESS;
  }

  /**
   * 品牌新增，增加账户信息和店铺信息
   * 
   * @return
   * @throws Throwable
   */
  public String insertManufacturerInfoAndShop() throws Throwable {
    if (upfile3 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/manufacturer")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/manufacturer/", upfile3);
      manufacturer.setLogo(file_name);
    }
    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      manufacturer.preUpdate(loginPojo);
    }
    manufacturerService.updateManufacturer(manufacturer);
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + "1.jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/shop") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/shop/", upfile);
      shop.setTopImage(file_name);
    }
    if (upfile2 != null) {
      String file_name = StringUtil.getCurrentDateStr() + "2.jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/shop") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/shop/", upfile2);
      shop.setImages(file_name);
    }
    shop.setRecommendType(0l);
    shop.setLocationCertification("1");
    shop.setIdentityCertification("1");
    shop.setSorting(1000);
    shop.setMainCategory(manufacturer.getMainCategory());
    shop.setSalesArea(manufacturer.getSalesArea());
    shop.setMainProduct(manufacturer.getMainProduct());
    shop.setStatus(manufacturer.getStatus());
    shopService.insertShop(shop);

    FileUtil.alertMessageBySkip("新增成功！", "manufacturer.do");

    return null;
  }

}
