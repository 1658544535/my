package com.tzmb2c.web.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import maowu.framework.utils.web.SuperAction;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.MD5Util;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.LoginRecPojo;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserCertificatesPhotoPojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.pojo.UserVerifyPojo;
import com.tzmb2c.web.service.LoginRecService;
import com.tzmb2c.web.service.LoginService;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserCertificatesPhotoService;
import com.tzmb2c.web.service.UserInfoService;
import com.tzmb2c.web.service.UserVerifyService;

public class SellerRegWebAction extends SuperAction {

  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private UserVerifyService userVerifyService;
  @Autowired
  private LoginService loginService;
  @Autowired
  private LoginRecService loginRecService;
  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private SysAreaService sysAreaService;
  @Autowired
  private ProductTypeService productTypeService;
  @Autowired
  private UserCertificatesPhotoService userCertificatesPhotoService;

  private SysLoginPojo sysLoginPojo;
  private SysLoginPojo sysLogin, sysLogin1;
  private List<SysLoginPojo> sysLoginPojos;
  private String msg;
  private String verifyCode;
  private String externalCode;
  private File upfile, upfile2, image1, image2, image3, image4, image5, image6, image7, image8,
      qcImage1, qcImage2, qcImage3, qcImage4, qcImage5, qcImage6, blImage1, blImage2, blImage3,
      blImage4, blImage5, blImage6, blImage7, blImage8, blImage9, blImage10;
  private ManufacturerPojo manufacturer, manufacturerPojo;
  private ShopPojo shop;
  private ProductTypePojo productTypePojo;
  private List<ProductTypePojo> productTypePojos;
  private String phonecode;
  private UserCertificatesPhotoPojo userCertificatesPhotoPojo;

  public File getImage8() {
    return image8;
  }

  public void setImage8(File image8) {
    this.image8 = image8;
  }

  public File getQcImage1() {
    return qcImage1;
  }

  public void setQcImage1(File qcImage1) {
    this.qcImage1 = qcImage1;
  }

  public File getQcImage2() {
    return qcImage2;
  }

  public void setQcImage2(File qcImage2) {
    this.qcImage2 = qcImage2;
  }

  public File getQcImage3() {
    return qcImage3;
  }

  public void setQcImage3(File qcImage3) {
    this.qcImage3 = qcImage3;
  }

  public File getQcImage4() {
    return qcImage4;
  }

  public void setQcImage4(File qcImage4) {
    this.qcImage4 = qcImage4;
  }

  public File getQcImage5() {
    return qcImage5;
  }

  public void setQcImage5(File qcImage5) {
    this.qcImage5 = qcImage5;
  }

  public File getQcImage6() {
    return qcImage6;
  }

  public void setQcImage6(File qcImage6) {
    this.qcImage6 = qcImage6;
  }

  public File getBlImage1() {
    return blImage1;
  }

  public void setBlImage1(File blImage1) {
    this.blImage1 = blImage1;
  }

  public File getBlImage2() {
    return blImage2;
  }

  public void setBlImage2(File blImage2) {
    this.blImage2 = blImage2;
  }

  public File getBlImage3() {
    return blImage3;
  }

  public void setBlImage3(File blImage3) {
    this.blImage3 = blImage3;
  }

  public File getBlImage4() {
    return blImage4;
  }

  public void setBlImage4(File blImage4) {
    this.blImage4 = blImage4;
  }

  public File getBlImage5() {
    return blImage5;
  }

  public void setBlImage5(File blImage5) {
    this.blImage5 = blImage5;
  }

  public File getBlImage6() {
    return blImage6;
  }

  public void setBlImage6(File blImage6) {
    this.blImage6 = blImage6;
  }

  public File getBlImage7() {
    return blImage7;
  }

  public void setBlImage7(File blImage7) {
    this.blImage7 = blImage7;
  }

  public File getBlImage8() {
    return blImage8;
  }

  public void setBlImage8(File blImage8) {
    this.blImage8 = blImage8;
  }

  public File getBlImage9() {
    return blImage9;
  }

  public void setBlImage9(File blImage9) {
    this.blImage9 = blImage9;
  }

  public File getBlImage10() {
    return blImage10;
  }

  public void setBlImage10(File blImage10) {
    this.blImage10 = blImage10;
  }

  public File getImage1() {
    return image1;
  }

  public void setImage1(File image1) {
    this.image1 = image1;
  }

  public File getImage2() {
    return image2;
  }

  public void setImage2(File image2) {
    this.image2 = image2;
  }

  public File getImage3() {
    return image3;
  }

  public void setImage3(File image3) {
    this.image3 = image3;
  }

  public File getImage4() {
    return image4;
  }

  public void setImage4(File image4) {
    this.image4 = image4;
  }

  public File getImage5() {
    return image5;
  }

  public void setImage5(File image5) {
    this.image5 = image5;
  }

  public String getPhonecode() {
    return phonecode;
  }

  public void setPhonecode(String phonecode) {
    this.phonecode = phonecode;
  }

  public List<ProductTypePojo> getProductTypePojos() {
    return productTypePojos;
  }

  public void setProductTypePojos(List<ProductTypePojo> productTypePojos) {
    this.productTypePojos = productTypePojos;
  }

  public ManufacturerPojo getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(ManufacturerPojo manufacturer) {
    this.manufacturer = manufacturer;
  }

  public ShopPojo getShop() {
    return shop;
  }

  public void setShop(ShopPojo shop) {
    this.shop = shop;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public String getExternalCode() {
    return externalCode;
  }

  public void setExternalCode(String externalCode) {
    this.externalCode = externalCode;
  }

  public String getVerifyCode() {
    return verifyCode;
  }

  public void setVerifyCode(String verifyCode) {
    this.verifyCode = verifyCode;
  }

  /**
   * 商家注册前端显示
   * 
   * @return
   * @throws Exception
   */
  public String goRegWeb() throws Exception {

    return SUCCESS;
  }

  public String doRegWeb() {
    try {
      /*
       * if (UserUtil.getWebUser() == null) { return "loginweb"; }
       */
      if (sysLoginPojo == null) {
        return "registerweb";
      }

      ActionContext actionContext = ActionContext.getContext();
      SysLoginPojo s = sysLoginService.getSysLoginByLoginName(sysLoginPojo.getLoginname2());
      if (s != null) {
        setMsg("该手机号已经被注册了!");
        return "registerweb";
      }
      UserVerifyPojo userVerify = new UserVerifyPojo();
      userVerify.setLoginname(sysLoginPojo.getLoginname2());
      userVerify = userVerifyService.findNewestByPhone(userVerify);
      if (userVerify == null || userVerify.getCaptcha() == null
          || !phonecode.equals(userVerify.getCaptcha())) {
        setMsg("验证码错误！请重新输入！");
        return "registerweb";
      }
      sysLoginPojo.setType("2");
      sysLoginPojo.setStatus(1);
      sysLoginPojo.setSorting(0);
      sysLoginPojo.setPassword(MD5Util.MD5(sysLoginPojo.getPassword()));
      sysLoginPojo.setCreateDate(new Date());
      sysLoginPojo.setLoginname(sysLoginPojo.getLoginname2());// 账号改为手机号码
      loginService.insertLoginPojo(sysLoginPojo);
      if (loginService.loginCheckWeb(sysLoginPojo)) {
        SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
        if (logiPojo != null) {
          sysLoginPojo.prePersist(logiPojo);
          loginService.updateLoginPojo(sysLoginPojo);
          HttpServletRequest request = ServletActionContext.getRequest();

          LoginRecPojo loginRecPojo = new LoginRecPojo();
          loginRecPojo.setType(logiPojo.getType());
          loginRecPojo.setLoginDate(new Date());
          loginRecPojo.setLoginIp(getIpAddr(request));
          loginRecPojo.setUserId(logiPojo.getId());
          loginRecService.addLoginRec(loginRecPojo);
          // 注册同时userinfo表需插入一条数据
          sysLoginPojo = loginService.getLoginPojoByLoginnameAndPassword(sysLoginPojo);
          UserInfoPojo userInfoPojo = new UserInfoPojo();
          userInfoPojo.setUserId(sysLoginPojo.getId());
          userInfoPojo.setCreateDate(new Date());
          userInfoPojo.setStatus(1);
          userInfoService.insertUserInfo(userInfoPojo);

          FileUtil.alertMessageBySkip("注册成功！", "sellerLogin.do");
          return null;
        } else {
          setMsg("该手机号码已被注册，请直接登录");
          actionContext.put("loop", "false");
          return "loginweb";
        }
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return "registerweb";
  }

  public String getIpAddr(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  public String goInvoiceWeb() {

    return SUCCESS;
  }

  public List<SysLoginPojo> getSysLoginPojos() {
    return sysLoginPojos;
  }

  public void setSysLoginPojos(List<SysLoginPojo> sysLoginPojos) {
    this.sysLoginPojos = sysLoginPojos;
  }

  public SysLoginPojo getSysLoginPojo() {
    return sysLoginPojo;
  }

  public void setSysLoginPojo(SysLoginPojo sysLoginPojo) {
    this.sysLoginPojo = sysLoginPojo;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  /**
   * 商家详情信息填写
   * 
   * @return
   * @throws Exception
   */
  public String goInformationWeb() throws Exception {
    SysLoginPojo loginPojo = UserUtil.getWebUser();
    // if (!sysLoginService.findSysLoginById(loginPojo.getId()).getName().isEmpty()) {
    ManufacturerPojo m = manufacturerService.findManufacturerByUserId(loginPojo.getId());
    if (m != null) {
      FileUtil.alertMessageBySkip("详情信息已添加过！", "goSellerIndex.do");
      return null;
    }

    ActionContext ac = ActionContext.getContext();
    // ac.put("type", sysDictService.getSysDictListByType("sys_login_type"));
    // ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("scale", sysDictService.getSysDictListByType("scale"));
    // ac.put("channel", sysDictService.getSysDictListByType("channel"));
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
    // ac.put("isNew", sysDictService.getSysDictListByType("yes_no"));
    return SUCCESS;
  }

  /**
   * 商家详情信息填写操作
   * 
   * @return
   * @throws Throwable
   */
  public String doInformationWeb() throws Throwable {
    try {
      SysLoginPojo loginPojo = UserUtil.getWebUser();
      if (loginPojo == null) {
        FileUtil.alertMessageBySkip("请先登录！", "sellerLogin.do");
        return null;
      }
      ManufacturerPojo m = manufacturerService.findManufacturerByUserId(loginPojo.getId());
      if (m != null) {
        // if (!sysLoginService.findSysLoginById(loginPojo.getId()).getName().isEmpty()) {
        FileUtil.alertMessageBySkip("详情信息已添加过！", "goSellerIndex.do");
        return null;
      }
      SysLoginPojo sysLogin = new SysLoginPojo();
      sysLogin.setName(manufacturer.getCompany());
      sysLogin.prePersist(loginPojo);
      String externalCode = sysLoginService.genExternalSignCode(sysLogin.getLoginname());
      sysLogin.setExternalSignCode(externalCode);
      sysLogin.setId(loginPojo.getId());
      sysLoginService.updateSysLogin(sysLogin);

      // sysLogin1 = sysLoginService.sysLoginFindId(sysLogin);
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/manufacturer")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/manufacturer/", upfile);
        manufacturer.setLogo(file_name);
      }
      manufacturer.setUserId(sysLogin.getId());
      manufacturer.setCreateBy(sysLogin.getId());
      /*
       * SysAreaPojo sysArea = sysAreaService.getNameById(shop.getProvince()); String province =
       * sysArea == null ? "" : sysArea.getName(); sysArea =
       * sysAreaService.getNameById(shop.getCity()); String city = sysArea == null ? "" :
       * sysArea.getName(); sysArea = sysAreaService.getNameById(shop.getArea()); String area =
       * sysArea == null ? "" : sysArea.getName(); manufacturer.setAddress(province + city + area +
       * shop.getAddress());
       */
      manufacturer.setStatus(0);

      manufacturer.setVersion(0);
      manufacturer.setChannel(0);
      manufacturerService.insertManufacturer(manufacturer);
      ShopPojo shop = new ShopPojo();
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + "1.jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/shop") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/shop/", upfile);
        shop.setImages(file_name);
      }
      shop.setUserId(sysLogin.getId());
      shop.setCreateBy(sysLogin.getId());
      shop.setCreateDate(new Date());
      shop.setRecommendType(0l);
      shop.setLocationCertification("1");
      shop.setIdentityCertification("1");
      shop.setSorting(1000);
      shop.setMainCategory(manufacturer.getMainCategory());
      // shop.setSalesArea(manufacturer.getSalesArea());
      // shop.setMainProduct(manufacturer.getMainProduct());
      shop.setStatus(manufacturer.getStatus());
      shop.setName(manufacturer.getCompany());
      // shop.setContent(manufacturer.getContent());
      shop.setPhone(manufacturer.getPhone());

      shop.setIsNew("0");
      shopService.insertShop(shop);

      // 添加证书照
      if (image1 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image1);
        userCertificatesPhotoPojo.setImage1(file_name);
      }
      if (image2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image2);
        userCertificatesPhotoPojo.setImage2(file_name);
      }
      if (image3 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image3);
        userCertificatesPhotoPojo.setImage3(file_name);
      }
      if (image4 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image4);
        userCertificatesPhotoPojo.setImage4(file_name);
      }
      if (image5 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image5);
        userCertificatesPhotoPojo.setImage5(file_name);
      }
      if (image6 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image6);
        userCertificatesPhotoPojo.setImage6(file_name);
      }
      if (image7 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image7);
        userCertificatesPhotoPojo.setImage7(file_name);
      }
      if (image8 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image8);
        userCertificatesPhotoPojo.setImage8(file_name);
      }
      if (qcImage1 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", qcImage1);
        userCertificatesPhotoPojo.setQcImage1(file_name);
      }
      if (qcImage2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", qcImage2);
        userCertificatesPhotoPojo.setQcImage2(file_name);
      }
      if (qcImage3 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", qcImage3);
        userCertificatesPhotoPojo.setQcImage3(file_name);
      }
      if (qcImage4 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", qcImage4);
        userCertificatesPhotoPojo.setQcImage4(file_name);
      }
      if (qcImage5 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", qcImage5);
        userCertificatesPhotoPojo.setQcImage5(file_name);
      }
      if (qcImage6 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", qcImage6);
        userCertificatesPhotoPojo.setQcImage6(file_name);
      }
      if (blImage1 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage1);
        userCertificatesPhotoPojo.setBlImage1(file_name);
      }
      if (blImage2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage2);
        userCertificatesPhotoPojo.setBlImage2(file_name);
      }
      if (blImage3 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage3);
        userCertificatesPhotoPojo.setBlImage3(file_name);
      }
      if (blImage4 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage4);
        userCertificatesPhotoPojo.setBlImage4(file_name);
      }
      if (blImage5 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage5);
        userCertificatesPhotoPojo.setBlImage5(file_name);
      }
      if (blImage6 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage6);
        userCertificatesPhotoPojo.setBlImage6(file_name);
      }
      if (blImage7 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage7);
        userCertificatesPhotoPojo.setBlImage7(file_name);
      }
      if (blImage8 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage8);
        userCertificatesPhotoPojo.setBlImage8(file_name);
      }
      if (blImage9 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage9);
        userCertificatesPhotoPojo.setBlImage9(file_name);
      }
      if (blImage10 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage10);
        userCertificatesPhotoPojo.setBlImage10(file_name);
      }
      if (userCertificatesPhotoPojo != null) {
        // UserCertificatesPhotoPojo isexist =
        // userCertificatesPhotoService.findUserCertificatesPhotoByUid(sysLogin.getId());
        // if (isexist != null) {
        // userCertificatesPhotoService.delUserCertificatesPhotoById(isexist.getId());
        // }
        userCertificatesPhotoPojo.setSuserId(sysLogin.getId());
        userCertificatesPhotoPojo.setStatus(0);
        userCertificatesPhotoPojo.setCreateBy(sysLogin.getId());
        userCertificatesPhotoService.insertUserCertificatesPhoto(userCertificatesPhotoPojo);
      }

      FileUtil.alertMessageBySkip("添加成功！请等待管理员审核！", "goSellerIndex.do");
    } catch (Exception e) {

      e.printStackTrace();
      FileUtil.alertMessageBySkip("添加失败！", "goInformationWeb.do");
    }
    return null;
  }

  public SysLoginPojo getSysLogin() {
    return sysLogin;
  }

  public void setSysLogin(SysLoginPojo sysLogin) {
    this.sysLogin = sysLogin;
  }

  public SysLoginPojo getSysLogin1() {
    return sysLogin1;
  }

  public void setSysLogin1(SysLoginPojo sysLogin1) {
    this.sysLogin1 = sysLogin1;
  }

  public ProductTypePojo getProductTypePojo() {
    return productTypePojo;
  }

  public void setProductTypePojo(ProductTypePojo productTypePojo) {
    this.productTypePojo = productTypePojo;
  }

  public UserCertificatesPhotoPojo getUserCertificatesPhotoPojo() {
    return userCertificatesPhotoPojo;
  }

  public void setUserCertificatesPhotoPojo(UserCertificatesPhotoPojo userCertificatesPhotoPojo) {
    this.userCertificatesPhotoPojo = userCertificatesPhotoPojo;
  }

  /**
   * 商家信息完善提交
   * 
   * @return
   * @throws Throwable
   */
  public String doInformationPerfectWeb() throws Throwable {
    try {
      SysLoginPojo loginPojo = UserUtil.getWebUser();
      if (loginPojo == null) {
        FileUtil.alertMessageBySkip("请先登录！", "sellerLogin.do");
        return null;
      }
      SysLoginPojo sysLogin = new SysLoginPojo();
      sysLogin.setName(manufacturer.getCompany());
      sysLogin.prePersist(loginPojo);
      // String externalCode = sysLoginService.genExternalSignCode(sysLogin.getLoginname());
      // sysLogin.setExternalSignCode(externalCode);
      sysLogin.setId(loginPojo.getId());
      sysLoginService.updateSysLogin(sysLogin);

      // sysLogin1 = sysLoginService.sysLoginFindId(sysLogin);
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/manufacturer")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/manufacturer/", upfile);
        manufacturer.setLogo(file_name);
      }
      manufacturer.setUserId(sysLogin.getId());
      // manufacturer.setCreateBy(sysLogin.getId());
      /*
       * SysAreaPojo sysArea = sysAreaService.getNameById(shop.getProvince()); String province =
       * sysArea == null ? "" : sysArea.getName(); sysArea =
       * sysAreaService.getNameById(shop.getCity()); String city = sysArea == null ? "" :
       * sysArea.getName(); sysArea = sysAreaService.getNameById(shop.getArea()); String area =
       * sysArea == null ? "" : sysArea.getName(); manufacturer.setAddress(province + city + area +
       * shop.getAddress());
       */
      manufacturer.setStatus(0);

      // manufacturer.setChannel(0);
      // manufacturerService.insertManufacturer(manufacturer);
      manufacturerService.updateManufacturer(manufacturer);
      ShopPojo shop = new ShopPojo();
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + "1.jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/shop") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/shop/", upfile);
        shop.setImages(file_name);
      }
      shop.setUserId(sysLogin.getId());
      // shop.setCreateBy(sysLogin.getId());
      // shop.setCreateDate(new Date());
      // shop.setRecommendType(0l);
      // shop.setLocationCertification("1");
      // shop.setIdentityCertification("1");
      // shop.setSorting(1000);
      shop.setMainCategory(manufacturer.getMainCategory());
      // shop.setSalesArea(manufacturer.getSalesArea());
      // shop.setMainProduct(manufacturer.getMainProduct());
      shop.setStatus(manufacturer.getStatus());
      shop.setName(manufacturer.getCompany());
      // shop.setContent(manufacturer.getContent());
      shop.setPhone(manufacturer.getPhone());

      // shop.setIsNew("0");
      // shopService.insertShop(shop);

      shop.setUpdateBy(sysLogin.getId());
      shop.setUpdateDate(new Date());
      shopService.updateShop(shop);

      // 添加证书照
      if (image1 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image1);
        userCertificatesPhotoPojo.setImage1(file_name);
      }

      if (image2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image2);
        userCertificatesPhotoPojo.setImage2(file_name);
      }

      if (image3 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image3);
        userCertificatesPhotoPojo.setImage3(file_name);
      }

      if (image4 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image4);
        userCertificatesPhotoPojo.setImage4(file_name);
      }

      if (image5 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image5);
        userCertificatesPhotoPojo.setImage5(file_name);
      }

      if (image6 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image6);
        userCertificatesPhotoPojo.setImage6(file_name);
      }

      if (image7 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image7);
        userCertificatesPhotoPojo.setImage7(file_name);
      }
      if (image8 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", image8);
        userCertificatesPhotoPojo.setImage8(file_name);
      }
      if (qcImage1 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", qcImage1);
        userCertificatesPhotoPojo.setQcImage1(file_name);
      }
      if (qcImage2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", qcImage2);
        userCertificatesPhotoPojo.setQcImage2(file_name);
      }
      if (qcImage3 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", qcImage3);
        userCertificatesPhotoPojo.setQcImage3(file_name);
      }
      if (qcImage4 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", qcImage4);
        userCertificatesPhotoPojo.setQcImage4(file_name);
      }
      if (qcImage5 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", qcImage5);
        userCertificatesPhotoPojo.setQcImage5(file_name);
      }
      if (qcImage6 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", qcImage6);
        userCertificatesPhotoPojo.setQcImage6(file_name);
      }
      if (blImage1 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage1);
        userCertificatesPhotoPojo.setBlImage1(file_name);
      }
      if (blImage2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage2);
        userCertificatesPhotoPojo.setBlImage2(file_name);
      }
      if (blImage3 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage3);
        userCertificatesPhotoPojo.setBlImage3(file_name);
      }
      if (blImage4 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage4);
        userCertificatesPhotoPojo.setBlImage4(file_name);
      }
      if (blImage5 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage5);
        userCertificatesPhotoPojo.setBlImage5(file_name);
      }
      if (blImage6 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage6);
        userCertificatesPhotoPojo.setBlImage6(file_name);
      }
      if (blImage7 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage7);
        userCertificatesPhotoPojo.setBlImage7(file_name);
      }
      if (blImage8 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage8);
        userCertificatesPhotoPojo.setBlImage8(file_name);
      }
      if (blImage9 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage9);
        userCertificatesPhotoPojo.setBlImage9(file_name);
      }
      if (blImage10 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCertificatesPhoto")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCertificatesPhoto/", blImage10);
        userCertificatesPhotoPojo.setBlImage10(file_name);
      }
      if (userCertificatesPhotoPojo != null) {
        // UserCertificatesPhotoPojo isexist =
        // userCertificatesPhotoService.findUserCertificatesPhotoByUid(sysLogin.getId());
        // if (isexist != null) {
        // userCertificatesPhotoService.delUserCertificatesPhotoById(isexist.getId());
        // }
        userCertificatesPhotoPojo.setSuserId(sysLogin.getId());
        userCertificatesPhotoPojo.setStatus(0);
        UserCertificatesPhotoPojo isexist =
            userCertificatesPhotoService.findUserCertificatesPhotoByUid(sysLogin.getId());
        if (isexist == null) {
          userCertificatesPhotoPojo.setCreateBy(sysLogin.getId());
          userCertificatesPhotoService.insertUserCertificatesPhoto(userCertificatesPhotoPojo);
        } else {
          userCertificatesPhotoPojo.setUpdateBy(sysLogin.getId());
          userCertificatesPhotoService.updateUserCertificatesPhoto(userCertificatesPhotoPojo);
        }
      }

      FileUtil.alertMessageBySkip("添加成功！请等待管理员审核！", "getProfileWeb.do");
    } catch (Exception e) {

      e.printStackTrace();
      FileUtil.alertMessageBySkip("添加失败！", "goInformationPerfectWeb.do");
    }
    return null;
  }

  /**
   * 商家信息完善
   * 
   * @return
   * @throws Exception
   */
  public String goInformationPerfectWeb() throws Exception {
    ActionContext ac = ActionContext.getContext();
    // ac.put("type", sysDictService.getSysDictListByType("sys_login_type"));
    // ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("scale", sysDictService.getSysDictListByType("scale"));
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
    // ac.put("isNew", sysDictService.getSysDictListByType("yes_no"));

    SysLoginPojo loginPojo = UserUtil.getWebUser();
    if (loginPojo != null) {
      // sysLogin = new SysLoginPojo();
      // sysLogin.setName(loginPojo.getName());
      setManufacturerPojo(manufacturerService.findManufacturerByUserId(loginPojo.getId()));
      shop = shopService.getfindByIdShopWeb(loginPojo.getId());
      userCertificatesPhotoPojo =
          userCertificatesPhotoService.findUserCertificatesPhotoByUid(loginPojo.getId());
    }

    return SUCCESS;
  }

  public File getImage6() {
    return image6;
  }

  public void setImage6(File image6) {
    this.image6 = image6;
  }

  public ManufacturerPojo getManufacturerPojo() {
    return manufacturerPojo;
  }

  public void setManufacturerPojo(ManufacturerPojo manufacturerPojo) {
    this.manufacturerPojo = manufacturerPojo;
  }

  public File getImage7() {
    return image7;
  }

  public void setImage7(File image7) {
    this.image7 = image7;
  }
}
