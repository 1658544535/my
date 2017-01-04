package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.opensymphony.xwork2.ActionContext;
import com.tencent.common.MD5;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.CompressPicture;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.MD5Util;
import com.tzmb2c.utils.MatrixToImageWriter;
import com.tzmb2c.utils.RandomUtils;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.mapper.ManufacturerMapper;
import com.tzmb2c.web.mapper.SysAreaMapper;
import com.tzmb2c.web.mapper.UserMakerShopMapper;
import com.tzmb2c.web.pojo.BrandDicPojo;
import com.tzmb2c.web.pojo.CouponPojo;
import com.tzmb2c.web.pojo.HelpPojo;
import com.tzmb2c.web.pojo.HelpTypePojo;
import com.tzmb2c.web.pojo.LoginRecPojo;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.ProductFocusImagesPojo;
import com.tzmb2c.web.pojo.ProductImagesPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSellPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SkuAttributePojo;
import com.tzmb2c.web.pojo.SocialCirclePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.TemplatePageDataPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.pojo.UserCirclePostPojo;
import com.tzmb2c.web.pojo.UserGrowthLinePojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.pojo.UserMakerBrandPojo;
import com.tzmb2c.web.pojo.UserMakerShopPojo;
import com.tzmb2c.web.pojo.UserMakerThemePojo;
import com.tzmb2c.web.pojo.UserVerifyPojo;
import com.tzmb2c.web.service.BrandDicService;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.LoginRecService;
import com.tzmb2c.web.service.LoginService;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.ProductFocusImagesService;
import com.tzmb2c.web.service.ProductImagesService;
import com.tzmb2c.web.service.ProductSellService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SkuAttributeService;
import com.tzmb2c.web.service.SocialCircleService;
import com.tzmb2c.web.service.SyntheticalDictService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.TemplatePageDataService;
import com.tzmb2c.web.service.UserBrandService;
import com.tzmb2c.web.service.UserCirclePostService;
import com.tzmb2c.web.service.UserGrowthLineService;
import com.tzmb2c.web.service.UserInfoService;
import com.tzmb2c.web.service.UserMakerBrandService;
import com.tzmb2c.web.service.UserMakerShopService;
import com.tzmb2c.web.service.UserMakerThemeService;
import com.tzmb2c.web.service.UserVerifyService;


public class GeekWebAction extends SuperAction {
  @Autowired
  private UserCirclePostService userCirclePostService;
  @Autowired
  private UserMakerThemeService userMakerThemeService;
  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private UserMakerShopService userMakerShopService;
  @Autowired
  private UserMakerBrandService userMakerBrandService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private LoginRecService loginRecService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private UserVerifyService userVerifyService;
  @Autowired
  private LoginService loginService;
  @Autowired
  private WalletService walletService;
  @Autowired
  private SysAreaService sysAreaService;
  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private SysAreaMapper sysAreaMapper;
  @Autowired
  private ManufacturerMapper manufacturerMapper;
  @Autowired
  private UserMakerShopMapper userMakerShopMapper;
  @Autowired
  private UserGrowthLineService userGrowthLineService;
  @Autowired
  private UserBrandService userBrandService;
  @Autowired
  private BrandDicService brandDicService;
  @Autowired
  private ProductTypeService productTypeService;

  @Autowired
  private TemplatePageDataService templatePageDataService;
  @Autowired
  private ProductService productService;
  @Autowired
  private CouponService couponService;
  @Autowired
  private SocialCircleService socialCircleService;
  @Autowired
  private SyntheticalDictService syntheticalDictService;
  @Autowired
  private ProductFocusImagesService productFocusImagesService;
  @Autowired
  private ProductImagesService productImagesService;
  @Autowired
  private SkuAttributeService skuAttributeService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private ProductSellService productSellService;
  private List<ProductTypePojo> productTypePojos;
  private UserBrandPojo userBrandPojo;
  private BrandDicPojo brandDicPojo;
  private TemplatePageDataPojo templatePageDataPojo;
  private UserInfoPojo userInfoPojo, userInfoPojo1;
  private UserMakerShopPojo userMakerShopPojo;
  private UserMakerBrandPojo userMakerBrandPojo;
  private ShopPojo shopPojo, shopPojo1;
  private Integer userPostCount;
  private Integer geekThemeCount;
  private Integer productCount;
  private File logo, registrationCertificate, banner;
  private String username;// 用户名称
  private String passwd;// 用户密码
  private String url;// 前端传的返回连接
  private String msg;// 提示
  private String phonecode;
  private SysLoginPojo sysLoginPojo;
  private UserMakerThemePojo userMakerThemePojo;
  private ManufacturerPojo manufacturerPojo;
  private String uLoginname;
  private String oldPass;
  private String oldPasswd;
  private String newPasswd;
  private String newPasswdRepeat;
  private File upfile;
  private List<UserMakerThemePojo> userMakerThemePojoList;
  private List<UserMakerBrandPojo> userMakerBrandPojos;
  private File upfile1;
  private File upfile2;
  private File upfile3;
  private File upfile4;
  private File upfile5;
  private File upfile6;
  private File upfile7;
  private File upfile8;
  private String jsonStr;
  // 自定义专题详情json
  private String saveData;
  private Long dataId;
  private File files;
  private Map<String, Object> dataMap;
  private Map<String, Object> productData;
  private String filePath;
  // 商品列表
  private ProductPojo productPojo;
  private List<ProductPojo> productList;
  // 优惠劵列表
  private List<CouponPojo> couponPojos;
  private CouponPojo couponPojo;
  private String result;
  private Long geekThemeId;
  private List<SocialCirclePojo> socialCirclePojoList;
  private UserCirclePostPojo userCirclePostPojo;



  public UserCirclePostPojo getUserCirclePostPojo() {
    return userCirclePostPojo;
  }

  public void setUserCirclePostPojo(UserCirclePostPojo userCirclePostPojo) {
    this.userCirclePostPojo = userCirclePostPojo;
  }

  public List<SocialCirclePojo> getSocialCirclePojoList() {
    return socialCirclePojoList;
  }

  public void setSocialCirclePojoList(List<SocialCirclePojo> socialCirclePojoList) {
    this.socialCirclePojoList = socialCirclePojoList;
  }

  private ProductTypePojo productTypePojo;
  private String typeStr;
  private ProductFocusImagesPojo productFocusImages, productFocusImages2, productFocusImages3,
      productFocusImages4, productFocusImages5;

  public ProductFocusImagesPojo getProductFocusImages5() {
    return productFocusImages5;
  }

  public void setProductFocusImages5(ProductFocusImagesPojo productFocusImages5) {
    this.productFocusImages5 = productFocusImages5;
  }

  public ProductFocusImagesPojo getProductFocusImages4() {
    return productFocusImages4;
  }

  public void setProductFocusImages4(ProductFocusImagesPojo productFocusImages4) {
    this.productFocusImages4 = productFocusImages4;
  }

  public ProductFocusImagesPojo getProductFocusImages3() {
    return productFocusImages3;
  }

  public void setProductFocusImages3(ProductFocusImagesPojo productFocusImages3) {
    this.productFocusImages3 = productFocusImages3;
  }

  public ProductFocusImagesPojo getProductFocusImages2() {
    return productFocusImages2;
  }

  public void setProductFocusImages2(ProductFocusImagesPojo productFocusImages2) {
    this.productFocusImages2 = productFocusImages2;
  }

  private String typeIdsStr;
  private String typeIdStr;
  private String[] tids;
  private String[] colors;
  private String[] formats;
  private String[] upfileSkus;// 原有的SKU图片
  private String[] upfileSku;// 新加的SKU图片
  private String upfileSku1;// 新加的SKU图片
  private String[] images;
  private String[] stocks;
  private String[] prices;
  private String[] businessCodes;
  private String[] status;
  private String skuImagesUrl;
  private HelpTypePojo helpTypePojo;
  private List<HelpTypePojo> helpTypePojos;
  private HelpPojo helpPojo;
  private List<HelpPojo> helpPojos;
  private Double deductScoreAll;// 总的扣减分数
  private List<ProductSkuLinkPojo> productSkuLinkPojos;
  private String is;
  private File[] upfiles, upfiles2;
  private File upfile9, upfile10;

  public File getUpfile10() {
    return upfile10;
  }

  public void setUpfile10(File upfile10) {
    this.upfile10 = upfile10;
  }

  public File getUpfile9() {
    return upfile9;
  }

  public void setUpfile9(File upfile9) {
    this.upfile9 = upfile9;
  }

  public File[] getUpfiles2() {
    return upfiles2;
  }

  public void setUpfiles2(File[] upfiles2) {
    this.upfiles2 = upfiles2;
  }

  private long[] proImgId;
  private long proImgId1;
  private Integer[] isUpFiles;

  public Integer[] getIsUpFiles() {
    return isUpFiles;
  }

  public void setIsUpFiles(Integer[] isUpFiles) {
    this.isUpFiles = isUpFiles;
  }

  public long getProImgId1() {
    return proImgId1;
  }

  public void setProImgId1(long proImgId1) {
    this.proImgId1 = proImgId1;
  }

  private List<ProductFocusImagesPojo> productFocusImagesList;
  private List<ProductImagesPojo> productImagesList;

  public List<ProductImagesPojo> getProductImagesList() {
    return productImagesList;
  }

  public void setProductImagesList(List<ProductImagesPojo> productImagesList) {
    this.productImagesList = productImagesList;
  }

  public List<ProductFocusImagesPojo> getProductFocusImagesList() {
    return productFocusImagesList;
  }

  public void setProductFocusImagesList(List<ProductFocusImagesPojo> productFocusImagesList) {
    this.productFocusImagesList = productFocusImagesList;
  }

  public long[] getProImgId() {
    return proImgId;
  }

  public void setProImgId(long[] proImgId) {
    this.proImgId = proImgId;
  }

  private ProductImagesPojo productImagesPojo, productImagesPojo2, productImagesPojo3,
      productImagesPojo4, productImagesPojo5, productImagesPojo6, productImagesPojo7,
      productImagesPojo8, productImagesPojo9, productImagesPojo10;

  public ProductImagesPojo getProductImagesPojo10() {
    return productImagesPojo10;
  }

  public void setProductImagesPojo10(ProductImagesPojo productImagesPojo10) {
    this.productImagesPojo10 = productImagesPojo10;
  }

  public ProductImagesPojo getProductImagesPojo9() {
    return productImagesPojo9;
  }

  public void setProductImagesPojo9(ProductImagesPojo productImagesPojo9) {
    this.productImagesPojo9 = productImagesPojo9;
  }

  public ProductImagesPojo getProductImagesPojo8() {
    return productImagesPojo8;
  }

  public void setProductImagesPojo8(ProductImagesPojo productImagesPojo8) {
    this.productImagesPojo8 = productImagesPojo8;
  }

  public ProductImagesPojo getProductImagesPojo7() {
    return productImagesPojo7;
  }

  public void setProductImagesPojo7(ProductImagesPojo productImagesPojo7) {
    this.productImagesPojo7 = productImagesPojo7;
  }

  public ProductImagesPojo getProductImagesPojo6() {
    return productImagesPojo6;
  }

  public void setProductImagesPojo6(ProductImagesPojo productImagesPojo6) {
    this.productImagesPojo6 = productImagesPojo6;
  }

  public ProductImagesPojo getProductImagesPojo5() {
    return productImagesPojo5;
  }

  public void setProductImagesPojo5(ProductImagesPojo productImagesPojo5) {
    this.productImagesPojo5 = productImagesPojo5;
  }

  public ProductImagesPojo getProductImagesPojo4() {
    return productImagesPojo4;
  }

  public void setProductImagesPojo4(ProductImagesPojo productImagesPojo4) {
    this.productImagesPojo4 = productImagesPojo4;
  }

  public ProductImagesPojo getProductImagesPojo3() {
    return productImagesPojo3;
  }

  public void setProductImagesPojo3(ProductImagesPojo productImagesPojo3) {
    this.productImagesPojo3 = productImagesPojo3;
  }

  public ProductImagesPojo getProductImagesPojo2() {
    return productImagesPojo2;
  }

  public void setProductImagesPojo2(ProductImagesPojo productImagesPojo2) {
    this.productImagesPojo2 = productImagesPojo2;
  }

  private ProductSkuLinkPojo productSkuLinkPojo;

  public ProductSkuLinkPojo getProductSkuLinkPojo() {
    return productSkuLinkPojo;
  }

  public void setProductSkuLinkPojo(ProductSkuLinkPojo productSkuLinkPojo) {
    this.productSkuLinkPojo = productSkuLinkPojo;
  }

  private SkuAttributePojo skuAttributePojo, skuAttributePojo2;

  public ProductImagesPojo getProductImagesPojo() {
    return productImagesPojo;
  }

  public void setProductImagesPojo(ProductImagesPojo productImagesPojo) {
    this.productImagesPojo = productImagesPojo;
  }

  public File[] getUpfiles() {
    return upfiles;
  }

  public void setUpfiles(File[] upfiles) {
    this.upfiles = upfiles;
  }

  public String getTypeIdsStr() {
    return typeIdsStr;
  }

  public void setTypeIdsStr(String typeIdsStr) {
    this.typeIdsStr = typeIdsStr;
  }

  public String getTypeIdStr() {
    return typeIdStr;
  }

  public void setTypeIdStr(String typeIdStr) {
    this.typeIdStr = typeIdStr;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String[] getColors() {
    return colors;
  }

  public void setColors(String[] colors) {
    this.colors = colors;
  }

  public String[] getFormats() {
    return formats;
  }

  public void setFormats(String[] formats) {
    this.formats = formats;
  }

  public String[] getUpfileSku() {
    return upfileSku;
  }

  public void setUpfileSku(String[] upfileSku) {
    this.upfileSku = upfileSku;
  }

  public String[] getImages() {
    return images;
  }

  public void setImages(String[] images) {
    this.images = images;
  }

  public String[] getStocks() {
    return stocks;
  }

  public void setStocks(String[] stocks) {
    this.stocks = stocks;
  }

  public String[] getPrices() {
    return prices;
  }

  public void setPrices(String[] prices) {
    this.prices = prices;
  }

  public String[] getBusinessCodes() {
    return businessCodes;
  }

  public void setBusinessCodes(String[] businessCodes) {
    this.businessCodes = businessCodes;
  }

  public ProductFocusImagesPojo getProductFocusImages() {
    return productFocusImages;
  }

  public void setProductFocusImages(ProductFocusImagesPojo productFocusImages) {
    this.productFocusImages = productFocusImages;
  }

  public String getTypeStr() {
    return typeStr;
  }

  public void setTypeStr(String typeStr) {
    this.typeStr = typeStr;
  }

  public ProductTypePojo getProductTypePojo() {
    return productTypePojo;
  }

  public void setProductTypePojo(ProductTypePojo productTypePojo) {
    this.productTypePojo = productTypePojo;
  }

  public TemplatePageDataPojo getTemplatePageDataPojo() {
    return templatePageDataPojo;
  }

  public void setTemplatePageDataPojo(TemplatePageDataPojo templatePageDataPojo) {
    this.templatePageDataPojo = templatePageDataPojo;
  }

  public Long getGeekThemeId() {
    return geekThemeId;
  }

  public void setGeekThemeId(Long geekThemeId) {
    this.geekThemeId = geekThemeId;
  }

  public String getSaveData() {
    return saveData;
  }

  public void setSaveData(String saveData) {
    this.saveData = saveData;
  }

  public Long getDataId() {
    return dataId;
  }

  public void setDataId(Long dataId) {
    this.dataId = dataId;
  }

  public File getFiles() {
    return files;
  }

  public void setFiles(File files) {
    this.files = files;
  }

  public Map<String, Object> getDataMap() {
    return dataMap;
  }

  public void setDataMap(Map<String, Object> dataMap) {
    this.dataMap = dataMap;
  }

  public Map<String, Object> getProductData() {
    return productData;
  }

  public void setProductData(Map<String, Object> productData) {
    this.productData = productData;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public ProductPojo getProductPojo() {
    return productPojo;
  }

  public void setProductPojo(ProductPojo productPojo) {
    this.productPojo = productPojo;
  }

  public List<ProductPojo> getProductList() {
    return productList;
  }

  public void setProductList(List<ProductPojo> productList) {
    this.productList = productList;
  }

  public List<CouponPojo> getCouponPojos() {
    return couponPojos;
  }

  public void setCouponPojos(List<CouponPojo> couponPojos) {
    this.couponPojos = couponPojos;
  }

  public CouponPojo getCouponPojo() {
    return couponPojo;
  }

  public void setCouponPojo(CouponPojo couponPojo) {
    this.couponPojo = couponPojo;
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

  public File getUpfile4() {
    return upfile4;
  }

  public void setUpfile4(File upfile4) {
    this.upfile4 = upfile4;
  }

  public File getUpfile5() {
    return upfile5;
  }

  public void setUpfile5(File upfile5) {
    this.upfile5 = upfile5;
  }

  public File getUpfile6() {
    return upfile6;
  }

  public void setUpfile6(File upfile6) {
    this.upfile6 = upfile6;
  }

  public File getUpfile7() {
    return upfile7;
  }

  public void setUpfile7(File upfile7) {
    this.upfile7 = upfile7;
  }

  public File getUpfile8() {
    return upfile8;
  }

  public void setUpfile8(File upfile8) {
    this.upfile8 = upfile8;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public String getuLoginname() {
    return uLoginname;
  }

  public void setuLoginname(String uLoginname) {
    this.uLoginname = uLoginname;
  }

  public String getOldPass() {
    return oldPass;
  }

  public void setOldPass(String oldPass) {
    this.oldPass = oldPass;
  }

  public String getOldPasswd() {
    return oldPasswd;
  }

  public void setOldPasswd(String oldPasswd) {
    this.oldPasswd = oldPasswd;
  }

  public String getNewPasswd() {
    return newPasswd;
  }

  public void setNewPasswd(String newPasswd) {
    this.newPasswd = newPasswd;
  }

  public String getNewPasswdRepeat() {
    return newPasswdRepeat;
  }

  public void setNewPasswdRepeat(String newPasswdRepeat) {
    this.newPasswdRepeat = newPasswdRepeat;
  }

  public ShopPojo getShopPojo() {
    return shopPojo;
  }

  public void setShopPojo(ShopPojo shopPojo) {
    this.shopPojo = shopPojo;
  }

  public ShopPojo getShopPojo1() {
    return shopPojo1;
  }

  public void setShopPojo1(ShopPojo shopPojo1) {
    this.shopPojo1 = shopPojo1;
  }

  public UserMakerBrandPojo getUserMakerBrandPojo() {
    return userMakerBrandPojo;
  }

  public void setUserMakerBrandPojo(UserMakerBrandPojo userMakerBrandPojo) {
    this.userMakerBrandPojo = userMakerBrandPojo;
  }

  public UserMakerThemePojo getUserMakerThemePojo() {
    return userMakerThemePojo;
  }

  public void setUserMakerThemePojo(UserMakerThemePojo userMakerThemePojo) {
    this.userMakerThemePojo = userMakerThemePojo;
  }

  public ManufacturerPojo getManufacturerPojo() {
    return manufacturerPojo;
  }

  public void setManufacturerPojo(ManufacturerPojo manufacturerPojo) {
    this.manufacturerPojo = manufacturerPojo;
  }

  public UserInfoPojo getUserInfoPojo() {
    return userInfoPojo;
  }

  public void setUserInfoPojo(UserInfoPojo userInfoPojo) {
    this.userInfoPojo = userInfoPojo;
  }

  public UserInfoPojo getUserInfoPojo1() {
    return userInfoPojo1;
  }

  public void setUserInfoPojo1(UserInfoPojo userInfoPojo1) {
    this.userInfoPojo1 = userInfoPojo1;
  }

  public Integer getUserPostCount() {
    return userPostCount;
  }


  public void setUserPostCount(Integer userPostCount) {
    this.userPostCount = userPostCount;
  }

  public Integer getGeekThemeCount() {
    return geekThemeCount;
  }

  public void setGeekThemeCount(Integer geekThemeCount) {
    this.geekThemeCount = geekThemeCount;
  }

  public File getLogo() {
    return logo;
  }

  public void setLogo(File logo) {
    this.logo = logo;
  }

  public File getRegistrationCertificate() {
    return registrationCertificate;
  }

  public void setRegistrationCertificate(File registrationCertificate) {
    this.registrationCertificate = registrationCertificate;
  }

  public UserMakerShopPojo getUserMakerShopPojo() {
    return userMakerShopPojo;
  }

  public void setUserMakerShopPojo(UserMakerShopPojo userMakerShopPojo) {
    this.userMakerShopPojo = userMakerShopPojo;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getPhonecode() {
    return phonecode;
  }

  public void setPhonecode(String phonecode) {
    this.phonecode = phonecode;
  }

  public SysLoginPojo getSysLoginPojo() {
    return sysLoginPojo;
  }

  public void setSysLoginPojo(SysLoginPojo sysLoginPojo) {
    this.sysLoginPojo = sysLoginPojo;
  }


  public List<UserMakerThemePojo> getUserMakerThemePojoList() {
    return userMakerThemePojoList;
  }

  public void setUserMakerThemePojoList(List<UserMakerThemePojo> userMakerThemePojoList) {
    this.userMakerThemePojoList = userMakerThemePojoList;
  }


  public File getBanner() {
    return banner;
  }

  public void setBanner(File banner) {
    this.banner = banner;
  }

  public List<ProductTypePojo> getProductTypePojos() {
    return productTypePojos;
  }

  public void setProductTypePojos(List<ProductTypePojo> productTypePojos) {
    this.productTypePojos = productTypePojos;
  }

  public BrandDicPojo getBrandDicPojo() {
    return brandDicPojo;
  }

  public void setBrandDicPojo(BrandDicPojo brandDicPojo) {
    this.brandDicPojo = brandDicPojo;
  }

  /**
   * 创客退出
   * 
   * @return
   */
  public String doGeekLogoutWeb() {
    Map<String, Object> session = ActionContext.getContext().getSession();
    session.clear();
    return "gkloginweb";
  }

  public String geekWebHomePage() throws SQLException {
    SysLoginPojo sysLogin = UserUtil.getGeekUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
      return null;
    }
    Long uid = sysLogin.getId();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("userId", uid);
    map.put("status", 1);
    userPostCount = userCirclePostService.userCirclePostCount(map);
    geekThemeCount = userMakerThemeService.userMakerThemeCount(map);
    ProductPojo productPojo = new ProductPojo();
    productPojo.setUserId(uid);
    productPojo.setStatus(1);
    productCount = productService.getAllCount(productPojo);
    return SUCCESS;
  }

  // 获取创客基本信息
  public String baseinfo() throws Exception {
    SysLoginPojo logiPojo = UserUtil.getGeekUser();
    if (logiPojo == null) {
      FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
      return null;
    }
    userInfoPojo = userInfoService.findUserInfoByUserId(logiPojo.getId());
    // ShopPojo shopPojo = new ShopPojo();
    // shopPojo.setUserId(id);
    // shopPojo = shopService.findShop(shopPojo);
    manufacturerPojo = manufacturerService.findManufacturerByUserId(logiPojo.getId());
    sysLoginPojo = sysLoginService.findSysLoginById(logiPojo.getId());
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("userId", logiPojo.getId());
    shopPojo = shopService.selectShopInfo(map);
    return SUCCESS;

  }

  // 跳转至创客基本信息页面
  public String addGeekInfo() throws Exception {
    SysLoginPojo logiPojo = UserUtil.getGeekUser();
    if (logiPojo == null) {
      FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
      return null;
    }
    userInfoPojo = userInfoService.findUserInfoByUserId(logiPojo.getId());
    manufacturerPojo = manufacturerService.findManufacturerByUserId(logiPojo.getId());
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("userId", logiPojo.getId());
    shopPojo = shopService.selectShopInfo(map);
    sysLoginPojo = sysLoginService.findSysLoginById(logiPojo.getId());
    if (userInfoPojo == null) {
      userInfoPojo = new UserInfoPojo();
    }
    return SUCCESS;
  }


  // 新增信息提交
  public String insertGeekInfo() throws Exception {
    SysLoginPojo logiPojo = UserUtil.getGeekUser();
    if (logiPojo == null) {
      FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
      return null;
    }
    // 修改用户表
    sysLoginPojo.setId(logiPojo.getId());
    if (upfile1 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/userlogo")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/userlogo/", upfile1);
      sysLoginPojo.setImage(file_name);
    }
    sysLoginService.updateSysLogin(sysLoginPojo);
    // 添加到创客品牌表
    userMakerBrandPojo.setUserId(logiPojo.getId());
    if (upfile7 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerBrand")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerBrand/", upfile7);
      userMakerBrandPojo.setLogo(file_name);
    }
    if (upfile8 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerBrand")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerBrand/", upfile8);
      userMakerBrandPojo.setRegistrationCertificate(file_name);
    }

    userMakerBrandPojo.setStatus(0);
    // 添加到品牌字典表
    BrandDicPojo brandDicPojo = new BrandDicPojo();
    brandDicPojo.setBrand(userMakerBrandPojo.getBrandName());
    if (upfile7 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/businessCenter/brandDic")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/businessCenter/brandDic/small/", upfile7);
      brandDicPojo.setLogo(file_name);
    }
    brandDicPojo.setStatus(1);
    brandDicPojo.setCreateDate(new Date());
    brandDicPojo.setUpdateDate(new Date());
    brandDicService.insertBrandDic(brandDicPojo);
    Map<String, Object> map1 = new HashMap<String, Object>();
    map1.put("panduan", 1);
    List<BrandDicPojo> brandDicPojoPojos = brandDicService.findBrandDicList(map1);
    // 添加到用户品牌表
    UserBrandPojo userBrandPojo = new UserBrandPojo();
    userBrandPojo.setUserId(logiPojo.getId());
    userBrandPojo.setBrandId(brandDicPojoPojos.get(0).getId());
    userBrandPojo.setBrandName(userMakerBrandPojo.getBrandName());
    userBrandPojo.setBrandDisc(userMakerBrandPojo.getContent());
    userBrandPojo.setMainCategory(userMakerBrandPojo.getMainCategory());
    userBrandPojo.setStatus(1);
    userBrandPojo.setCreateDate(new Date());
    userBrandPojo.setUpdateDate(new Date());
    userBrandService.insertUserBrand(userBrandPojo);
    List<UserBrandPojo> userBrandPojos = userBrandService.findUserBrandList(map1);
    userMakerBrandPojo.setBrandId(userBrandPojos.get(0).getId());
    userMakerBrandService.addUserMakerBrand(userMakerBrandPojo);



    // 更新到用户信息表

    userInfoPojo1 = userInfoService.findUserInfoByUserId(logiPojo.getId());
    if (userInfoPojo1 != null) {
      userInfoPojo.setId(userInfoPojo1.getId());
      // userInfoPojo.setUserId(logiPojo.getId());
      userInfoPojo.setStatus(0);
      userInfoService.updateUserInfo(userInfoPojo);
    }

    // 添加到店铺表
    Date now = new Date();
    shopPojo.setUserId(logiPojo.getId());
    if (upfile2 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/shop") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/shop/", upfile2);
      shopPojo.setImages(file_name);
    }
    if (upfile3 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/shop") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/shop/", upfile3);
      shopPojo.setTopImage(file_name);
    }
    shopPojo.setStatus(0);
    shopPojo.setUpdateDate(now);
    shopPojo.setCreateDate(now);
    shopService.insertShop(shopPojo);
    // 查出userId对应的创客店铺id
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("userId", logiPojo.getId());
    shopPojo1 = shopService.selectShopInfo(map);


    // 添加到创客店铺表

    userMakerShopPojo.setStatus(0L);
    userMakerShopPojo.setShopId(shopPojo1.getId());
    userMakerShopPojo.setUserId(logiPojo.getId());
    if (userMakerShopPojo.getShopType() == 0) {
      if (upfile4 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerShop")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerShop/", upfile4);
        userMakerShopPojo.setImageOriginal(file_name);
      }
      if (upfile5 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerShop")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerShop/", upfile5);
        userMakerShopPojo.setImageCopy(file_name);
      }
      userMakerShopPojo.setShopTypeName(userMakerShopPojo.getShopTypeName1());
    } else if (userMakerShopPojo.getShopType() == 1) {
      if (upfile6 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerShop")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerShop/", upfile6);
        userMakerShopPojo.setImageOriginal(file_name);
      }
      userMakerShopPojo.setShopTypeName(userMakerShopPojo.getShopTypeName2());
    }


    userMakerShopService.insertUserMakerShop(userMakerShopPojo);
    // 添加到创客表（原来的供应商表）
    ManufacturerPojo manufacturerPojo = new ManufacturerPojo();
    manufacturerPojo.setUserId(logiPojo.getId());
    manufacturerPojo.setStatus(0);
    manufacturerPojo.setType(1L);
    String provinceName = sysAreaService.getNameById(shopPojo.getProvince()).getName();
    String cityName = sysAreaService.getNameById(shopPojo.getCity()).getName();
    String areaName = sysAreaService.getNameById(shopPojo.getArea()).getName();
    manufacturerPojo.setAddress(provinceName + cityName + areaName + shopPojo.getAddress());
    manufacturerService.insertManufacturer(manufacturerPojo);
    FileUtil.alertMessageBySkip("提交成功！", "geekWeb.do");

    return null;
  }



  // 修改编辑提交
  public String updateBaseInfo() throws Exception {
    SysLoginPojo logiPojo = UserUtil.getGeekUser();
    if (logiPojo == null) {
      FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
      return null;
    }
    // 用户表
    sysLoginPojo.setId(logiPojo.getId());
    if (upfile1 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/userlogo")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/userlogo/", upfile1);
      sysLoginPojo.setImage(file_name);
    }
    sysLoginService.updateSysLogin(sysLoginPojo);
    // 修改用户信息表
    userInfoPojo.setUserId(logiPojo.getId());
    userInfoPojo.setStatus(0);
    userInfoService.updateUserInfo(userInfoPojo);
    // 修改店铺表
    // Date now = new Date();
    // shopPojo.setUserId(logiPojo.getId());
    // shopPojo.setStatus(0);
    // shopPojo.setUpdateDate(now);
    // shopService.updateShop(shopPojo);
    // 修改创客表（原来的供应商表）
    // manufacturerPojo.setUserId(logiPojo.getId());
    // manufacturerPojo.setStatus(0);
    // manufacturerPojo.setType(1L);
    // String provinceName = sysAreaService.getNameById(shopPojo.getProvince()).getName();
    // String cityName = sysAreaService.getNameById(shopPojo.getCity()).getName();
    // String areaName = sysAreaService.getNameById(shopPojo.getArea()).getName();
    // / manufacturerPojo.setAddress(provinceName + cityName + areaName + shopPojo.getAddress());
    // manufacturerService.updateManufacturer(manufacturerPojo);
    FileUtil.alertMessageBySkip("修改成功！", "addGeekInfo.do");

    return null;
  }



  // 跳转至密码修改页面
  public String baseInfoPasswd() throws Exception {

    return SUCCESS;

  }

  public String updateBaseInfoPasswd() throws Throwable {
    SysLoginPojo logiPojo = UserUtil.getGeekUser();
    if (logiPojo == null) {
      FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
      return null;
    }
    userMakerBrandPojo.setUserId(logiPojo.getId());
    // 添加到品牌字典表
    BrandDicPojo brandDicPojo = new BrandDicPojo();
    brandDicPojo.setBrand(userMakerBrandPojo.getBrandName());
    if (logo != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/businessCenter/brandDic")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/businessCenter/brandDic/", logo);
      // 图片压缩
      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath(
              "/upfiles/businessCenter/brandDic/small")
              + File.separator;
      cp.compressPic(logo, compressPath, "upfiles/businessCenter/brandDic/small/", file_name, 300,
          300, true);
      brandDicPojo.setLogo(file_name);
    } else {
      brandDicPojo.setLogo("");
    }
    brandDicPojo.setStatus(1);
    brandDicPojo.setCreateDate(new Date());
    brandDicPojo.setUpdateDate(new Date());
    brandDicService.insertBrandDic(brandDicPojo);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("panduan", 1);
    List<BrandDicPojo> brandDicPojoPojos = brandDicService.findBrandDicList(map);
    // 添加到用户品牌表
    UserBrandPojo userBrandPojo = new UserBrandPojo();
    userBrandPojo.setUserId(logiPojo.getId());
    userBrandPojo.setBrandId(brandDicPojoPojos.get(0).getId());
    userBrandPojo.setBrandName(userMakerBrandPojo.getBrandName());
    userBrandPojo.setBrandDisc(userMakerBrandPojo.getContent());
    userBrandPojo.setMainCategory(userMakerBrandPojo.getMainCategory());
    userBrandPojo.setStatus(1);
    userBrandPojo.setCreateDate(new Date());
    userBrandPojo.setUpdateDate(new Date());
    userBrandService.insertUserBrand(userBrandPojo);
    List<UserBrandPojo> userBrandPojos = userBrandService.findUserBrandList(map);
    userMakerBrandPojo.setBrandId(userBrandPojos.get(0).getId());
    userMakerBrandService.addUserMakerBrand(userMakerBrandPojo);


    FileUtil.alertMessageBySkip("修改！", "addGeekInfo.do");

    return SUCCESS;
  }

  /**
   * 保存修改的密码
   * 
   * @return
   * @throws SQLException
   */
  public String passChangeGeekWeb() throws SQLException {
    try {
      SysLoginPojo sysLogin = UserUtil.getGeekUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
      }
      uLoginname = sysLogin.getLoginname();
      SysLoginPojo sysLoginPojo = new SysLoginPojo();
      sysLoginPojo.setLoginname(uLoginname);
      oldPass = sysLoginService.sysLoginFindId(sysLoginPojo).getPassword();
      if (oldPasswd == null || "".equals(oldPasswd.trim())) {
        FileUtil.alertMessageBySkip("输入的旧密码不能为空！", "baseInfoPasswd.do");
      } else if (oldPasswd != null && !"".equals(oldPasswd)
          && !MD5Util.MD5(oldPasswd).equals(oldPass)) {
        FileUtil.alertMessageBySkip("请输入正确的旧密码！", "baseInfoPasswd.do");
      } else if (newPasswd == null || "".equals(newPasswd.trim())) {
        FileUtil.alertMessageBySkip("请输入新密码！", "baseInfoPasswd.do");
      } else if (newPasswdRepeat == null || "".equals(newPasswdRepeat.trim())) {
        FileUtil.alertMessageBySkip("请输入两次新密码！", "baseInfoPasswd.do");
      } else if (newPasswd != null && !"".equals(newPasswd.trim()) && newPasswdRepeat != null
          && !"".equals(newPasswdRepeat.trim()) && !newPasswd.equals(newPasswdRepeat)) {
        FileUtil.alertMessageBySkip("两次输入的新密码必须一致！", "baseInfoPasswd.do");
      } else if (newPasswd != null && newPasswd.length() < 6) {
        FileUtil.alertMessageBySkip("新密码强度太弱，请换个！", "baseInfoPasswd.do");
      } else if (newPasswd != null && oldPasswd != null && oldPasswd.equals(newPasswd)) {
        FileUtil.alertMessageBySkip("新密码与旧密码相同，请换个！", "baseInfoPasswd.do");
      } else if (newPasswd != null && !"".equals(newPasswd.trim()) && newPasswdRepeat != null
          && !"".equals(newPasswdRepeat.trim()) && newPasswd.equals(newPasswdRepeat)) {
        sysLogin.setPassword(MD5Util.MD5(newPasswd));
        sysLoginService.updatePassword(sysLogin);
        FileUtil.alertMessageBySkip("修改成功！", "geekLogin.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 跳转店铺管理页面
   */
  public String geekShopWeb() throws Exception {
    try {
      SysLoginPojo sysLogin = UserUtil.getGeekUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录！", "geekLogin.do");
        return null;
      }
      userMakerShopPojo = userMakerShopService.findUserMakerShopByUid(sysLogin.getId());
      if (userMakerShopPojo == null) {
        FileUtil.alertMessageBySkip("检测到您尚未创建店铺，请移步至跳转的新页面创建您的店铺！", "goAddGeekShopWeb.do");
        return null;
      }
      userMakerShopPojo.setAddress(userMakerShopPojo.getProvinceName()
          + userMakerShopPojo.getCityName() + userMakerShopPojo.getAreaName()
          + userMakerShopPojo.getDetailedAddress());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 跳转店铺新增页面
   */
  public String goAddGeekShopWeb() throws Exception {
    try {
      SysLoginPojo sysLogin = UserUtil.getGeekUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录！", "geekLogin.do");
        return null;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 跳转店铺编辑页面
   */
  public String goUpdateGeekShopWeb() throws Exception {
    try {
      SysLoginPojo sysLogin = UserUtil.getGeekUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录！", "geekLogin.do");
        return null;
      }
      userMakerShopPojo = userMakerShopService.findUserMakerShopByUid(sysLogin.getId());
      if (userMakerShopPojo == null) {
        FileUtil.alertMessageBySkip("检测到您尚未创建店铺，请移步至跳转的新页面创建您的店铺！", "goAddGeekShopWeb.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 提交新增的店铺管理页面
   */
  public String addGeekShopWeb() throws Exception {
    try {
      SysLoginPojo sysLogin = UserUtil.getGeekUser();
      shopPojo = new ShopPojo();
      manufacturerPojo = new ManufacturerPojo();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录！", "geekLogin.do");
        return null;
      }
      if (userMakerShopPojo == null || upfile1 == null || upfile2 == null) {
        FileUtil.alertMessageBySkip("请检查是否有必需的数据尚未填写或者有必需的图片没有上传！", "goAddGeekShopWeb.do");
        return null;
      } else if (userMakerShopPojo.getShopType() == 0 && (upfile3 == null || upfile4 == null)) {
        FileUtil.alertMessageBySkip("请检查营业执照正/副本图片或者法人身份证正反面图是否有上传！", "goAddGeekShopWeb.do");
        return null;
      } else if (userMakerShopPojo.getShopType() == 1 && upfile5 == null) {
        FileUtil.alertMessageBySkip("请检查店主手持身份证照片是否有上传！", "goAddGeekShopWeb.do");
        return null;
      } else {
        if (upfile1 != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/shop")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/shop/", upfile1);
          shopPojo.setImages(file_name);
        }
        if (upfile2 != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/shop")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/shop/", upfile2);
          shopPojo.setTopImage(file_name);
        }
        if (userMakerShopPojo.getShopType() == 0) {
          if (upfile3 != null) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerShop")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerShop/", upfile3);
            userMakerShopPojo.setImageOriginal(file_name);
          }
          if (upfile4 != null) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerShop")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerShop/", upfile4);
            userMakerShopPojo.setImageCopy(file_name);
          }
        } else if (userMakerShopPojo.getShopType() == 1) {
          if (upfile5 != null) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerShop")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerShop/", upfile5);
            userMakerShopPojo.setImageOriginal(file_name);
          }
        }
      }
      // 新增至user_shop表
      Date now = new Date();
      shopPojo.setName(userMakerShopPojo.getShopName());
      shopPojo.setPhone(userMakerShopPojo.getPhone());
      shopPojo.setProvince(Integer.valueOf(Long.toString(userMakerShopPojo.getProvince())));
      shopPojo.setCity(Integer.valueOf(Long.toString(userMakerShopPojo.getCity())));
      shopPojo.setArea(Integer.valueOf(Long.toString(userMakerShopPojo.getArea())));
      shopPojo.setAddress(userMakerShopPojo.getDetailedAddress());
      shopPojo.setStatus(0);
      shopPojo.setUpdateDate(now);
      shopPojo.setCreateDate(now);
      shopPojo.setUserId(sysLogin.getId());
      shopService.insertShop(shopPojo);
      // 新增至user_manufacturer表
      manufacturerPojo.setUserId(sysLogin.getId());
      manufacturerPojo.setCompany(userMakerShopPojo.getShopName());
      String provinceName =
          sysAreaMapper
              .getNameById(Integer.valueOf(Long.toString(userMakerShopPojo.getProvince())))
              .getName();
      String cityName =
          sysAreaMapper.getNameById(Integer.valueOf(Long.toString(userMakerShopPojo.getCity())))
              .getName();
      String areaName =
          sysAreaMapper.getNameById(Integer.valueOf(Long.toString(userMakerShopPojo.getArea())))
              .getName();
      manufacturerPojo.setAddress(provinceName + cityName + areaName
          + userMakerShopPojo.getDetailedAddress());
      manufacturerPojo.setType(1L);
      manufacturerPojo.setStatus(0);
      manufacturerMapper.insertManufacturer(manufacturerPojo);
      // 新增至user_maker_shop表
      userMakerShopPojo.setUserId(sysLogin.getId());
      userMakerShopPojo.setShopId(shopPojo.getId());
      userMakerShopPojo.setStatus(0L);
      if (userMakerShopPojo.getShopType() == 0) {
        userMakerShopPojo.setShopTypeName(userMakerShopPojo.getShopTypeName1());
      } else if (userMakerShopPojo.getShopType() == 1) {
        userMakerShopPojo.setShopTypeName(userMakerShopPojo.getShopTypeName2());
      }
      userMakerShopService.insertUserMakerShop(userMakerShopPojo);
      FileUtil.alertMessageBySkip("新增店铺成功，请等待后台人员审核！", "geekShopWeb.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增店铺失败！", "goAddGeekShopWeb.do");
    }
    return null;
  }

  /**
   * 提交编辑的店铺管理页面
   */
  public String updateGeekShopWeb() throws Exception {
    try {
      SysLoginPojo sysLogin = UserUtil.getGeekUser();
      shopPojo = new ShopPojo();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录！", "geekLogin.do");
        return null;
      }
      if (userMakerShopPojo == null) {
        FileUtil.alertMessageBySkip("请检查是否有必需的数据尚未填写！", "goUpdateGeekShopWeb.do");
        return null;
      } else {
        if (upfile1 != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/shop")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/shop/", upfile1);
          shopPojo.setImages(file_name);
        }
        if (upfile2 != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/shop")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/shop/", upfile2);
          shopPojo.setTopImage(file_name);
        }
        if (userMakerShopPojo.getShopType() == 0) {
          if (upfile3 != null) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerShop")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerShop/", upfile3);
            userMakerShopPojo.setImageOriginal(file_name);
          }
          if (upfile4 != null) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerShop")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerShop/", upfile4);
            userMakerShopPojo.setImageCopy(file_name);
          }
        } else if (userMakerShopPojo.getShopType() == 1) {
          if (upfile5 != null) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerShop")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerShop/", upfile5);
            userMakerShopPojo.setImageOriginal(file_name);
          }
        }
      }
      // 更新至user_shop表
      Date now = new Date();
      shopPojo.setName(userMakerShopPojo.getShopName());
      shopPojo.setPhone(userMakerShopPojo.getPhone());
      shopPojo.setProvince(Integer.valueOf(Long.toString(userMakerShopPojo.getProvince())));
      shopPojo.setCity(Integer.valueOf(Long.toString(userMakerShopPojo.getCity())));
      shopPojo.setArea(Integer.valueOf(Long.toString(userMakerShopPojo.getArea())));
      shopPojo.setAddress(userMakerShopPojo.getDetailedAddress());
      shopPojo.setStatus(0);
      shopPojo.setUpdateDate(now);
      shopPojo.setCreateDate(now);
      shopPojo.setId(userMakerShopPojo.getShopId());
      shopService.updateShop(shopPojo);
      // 更新或者新增至user_manufacturer表
      manufacturerPojo = manufacturerMapper.getfindByUserIdManufacturer(sysLogin.getId());
      if (manufacturerPojo != null) {
        manufacturerPojo.setCompany(userMakerShopPojo.getShopName());
        manufacturerPojo.setAddress(userMakerShopPojo.getProvinceName()
            + userMakerShopPojo.getCityName() + userMakerShopPojo.getAreaName()
            + userMakerShopPojo.getDetailedAddress());
        manufacturerPojo.setType(1L);
        manufacturerPojo.setStatus(0);
        manufacturerMapper.updateManufacturer(manufacturerPojo);
      } else {
        manufacturerPojo.setUserId(sysLogin.getId());
        manufacturerPojo.setCompany(userMakerShopPojo.getShopName());
        manufacturerPojo.setAddress(userMakerShopPojo.getProvinceName()
            + userMakerShopPojo.getCityName() + userMakerShopPojo.getAreaName()
            + userMakerShopPojo.getDetailedAddress());
        manufacturerPojo.setType(1L);
        manufacturerPojo.setStatus(0);
        manufacturerMapper.insertManufacturer(manufacturerPojo);
      }
      // 更新至user_maker_shop表
      userMakerShopPojo.setStatus(0L);
      if (userMakerShopPojo.getShopType() == 0) {
        userMakerShopPojo.setShopTypeName(userMakerShopPojo.getShopTypeName1());
      } else if (userMakerShopPojo.getShopType() == 1) {
        userMakerShopPojo.setShopTypeName(userMakerShopPojo.getShopTypeName2());
      }
      userMakerShopMapper.updateUserMakerShop(userMakerShopPojo);
      FileUtil.alertMessageBySkip("修改店铺成功，请等待后台人员审核！", "geekShopWeb.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("修改店铺失败！", "goAddGeekShopWeb.do");
    }
    return null;
  }

  /**
   * 
   * 创客品牌页面选择 1、当用户注册第一次登陆而没有品牌信息时，程序返回input，显示增加品牌页面 2、当用户品牌信息表数据有仅只有一条时，程序返回success，显示创客品牌页面
   * 3、当用户品牌信息表数据有多条时，程序返回error，显示程序错误
   * 
   * @throws SQLException
   * @return String
   */
  public String geekBrandManage() throws SQLException {

    // 获取登录用户信息
    SysLoginPojo logiPojo = UserUtil.getGeekUser();

    if (logiPojo == null) {
      FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
      return null;
    }
    // 构造一个map将用户的id保存在其中
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("userId", logiPojo.getId());
    // 通过用户id查询数据库表中符合要求的列表
    List<UserMakerBrandPojo> userMakerBrandList = userMakerBrandService.userMakerBrandList(map);
    // 如果列表长度为0则显示增加品牌页面；
    // 如果列表长度为1则显示创客品牌页面。
    if (userMakerBrandList.size() == 0) {
      return INPUT;
    } else if (userMakerBrandList.size() == 1) {
      userMakerBrandPojo =
          userMakerBrandService.getUserMakerBrandById(userMakerBrandList.get(0).getId());
      return SUCCESS;
    } else {
      return ERROR;
    }
  }



  /**
   * 跳转至编辑品牌页面 通过页面传过来的userMakerBrandPojo中的ID来查询数据库表中的相关记录
   * 
   * @throws Exception
   * @return String
   */
  public String goEditGeekBrand() throws Exception {
    try {
      userMakerBrandPojo = userMakerBrandService.getUserMakerBrandById(userMakerBrandPojo.getId());
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 编辑品牌
   * 
   * @throws Exception
   * @return String
   */
  public String editGeekBrand() throws Exception {
    try {
      if (userMakerBrandPojo != null) {
        // LOGO操作
        if (logo != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerBrand")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerBrand/", logo);
          // 图片压缩
          CompressPicture cp = new CompressPicture();
          String compressPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerBrand/small")
                  + File.separator;
          cp.compressPic(logo, compressPath, "upfiles/userMakerBrand/small/", file_name, 300, 300,
              true);
          userMakerBrandPojo.setLogo(file_name);
        }

        // 商标注册证明操作
        if (registrationCertificate != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerBrand")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerBrand/",
              registrationCertificate);
          // 图片压缩
          CompressPicture cp = new CompressPicture();
          String compressPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerBrand/small")
                  + File.separator;
          cp.compressPic(registrationCertificate, compressPath, "upfiles/userMakerBrand/small/",
              file_name, 300, 300, true);
          userMakerBrandPojo.setRegistrationCertificate(file_name);
        }

        // 设置新增时间
        userMakerBrandPojo.setUpdateDate(new Date());
        // 设置状态为待审核
        userMakerBrandPojo.setStatus(0);

        // 更新数据
        userMakerBrandService.updateUserMakerBrand(userMakerBrandPojo);
        // 更新品牌字典表
        brandDicPojo.setBrand(userMakerBrandPojo.getBrandName());
        if (logo != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath(
                  "/upfiles/businessCenter/brandDic")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/businessCenter/brandDic/", logo);
          // 图片压缩
          CompressPicture cp = new CompressPicture();
          String compressPath =
              ServletActionContext.getServletContext().getRealPath(
                  "/upfiles/businessCenter/brandDic/small")
                  + File.separator;
          cp.compressPic(logo, compressPath, "upfiles/businessCenter/brandDic/small/", file_name,
              300, 300, true);
          brandDicPojo.setLogo(file_name);
        }
        brandDicService.updateBrandDic(brandDicPojo);
        // 更新用户品牌表
        userBrandPojo.setBrandName(userMakerBrandPojo.getBrandName());
        userBrandPojo.setBrandDisc(userMakerBrandPojo.getContent());
        userBrandPojo.setMainCategory(userMakerBrandPojo.getMainCategory());
        userBrandService.updateUserBrand(userBrandPojo);
        FileUtil.alertMessageBySkip("编辑成功！", "geekBrandManage.do");
      }

    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("编辑失败！", "geekBrandManage.do");
    }
    return null;
  }


  /**
   * 新增品牌 通过页面传过来的userMakerBrandPojo将数据插入到数据库表中，设置uid为当前登录用户id，品牌状态为0
   * 
   * @throws Exception
   * @return String
   */
  public String addGeekBrand() throws Exception {
    try {
      // 获取登录用户信息
      SysLoginPojo logiPojo = UserUtil.getGeekUser();
      if (logiPojo == null) {
        FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
        return null;
      }
      if (userMakerBrandPojo != null) {
        // LOGO操作
        if (logo != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerBrand")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerBrand/", logo);
          // 图片压缩
          CompressPicture cp = new CompressPicture();
          String compressPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerBrand/small")
                  + File.separator;
          cp.compressPic(logo, compressPath, "upfiles/userMakerBrand/small/", file_name, 300, 300,
              true);
          userMakerBrandPojo.setLogo(file_name);
        } else {
          userMakerBrandPojo.setLogo("");
        }

        // 商标注册证明操作
        if (registrationCertificate != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerBrand")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerBrand/",
              registrationCertificate);
          // 图片压缩
          CompressPicture cp = new CompressPicture();
          String compressPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerBrand/small")
                  + File.separator;
          cp.compressPic(registrationCertificate, compressPath, "upfiles/userMakerBrand/small/",
              file_name, 300, 300, true);
          userMakerBrandPojo.setRegistrationCertificate(file_name);
        } else {
          userMakerBrandPojo.setRegistrationCertificate("");
        }

        // 设置新增时间
        userMakerBrandPojo.setCreateDate(new Date());

        // 设置用户id
        userMakerBrandPojo.setUserId(logiPojo.getId());
        // 设置状态
        userMakerBrandPojo.setStatus(0);
        // 添加到品牌字典表
        BrandDicPojo brandDicPojo = new BrandDicPojo();
        brandDicPojo.setBrand(userMakerBrandPojo.getBrandName());
        if (logo != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath(
                  "/upfiles/businessCenter/brandDic")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/businessCenter/brandDic/", logo);
          // 图片压缩
          CompressPicture cp = new CompressPicture();
          String compressPath =
              ServletActionContext.getServletContext().getRealPath(
                  "/upfiles/businessCenter/brandDic/small")
                  + File.separator;
          cp.compressPic(logo, compressPath, "upfiles/businessCenter/brandDic/small/", file_name,
              300, 300, true);
          brandDicPojo.setLogo(file_name);
        } else {
          brandDicPojo.setLogo("");
        }
        brandDicPojo.setStatus(1);
        brandDicPojo.setCreateDate(new Date());
        brandDicPojo.setUpdateDate(new Date());
        brandDicService.insertBrandDic(brandDicPojo);
        // Map<String, Object> map = new HashMap<String, Object>();
        // map.put("panduan", 1);
        // List<BrandDicPojo> brandDicPojoPojos = brandDicService.findBrandDicList(map);
        // 添加到用户品牌表
        UserBrandPojo userBrandPojo = new UserBrandPojo();
        userBrandPojo.setUserId(logiPojo.getId());
        userBrandPojo.setBrandId(brandDicPojo.getId());
        userBrandPojo.setBrandName(userMakerBrandPojo.getBrandName());
        userBrandPojo.setBrandDisc(userMakerBrandPojo.getContent());
        userBrandPojo.setMainCategory(userMakerBrandPojo.getMainCategory());
        userBrandPojo.setStatus(0);
        Calendar cal = Calendar.getInstance();
        userBrandPojo.setStartDateStr(StringUtil.dateToString(cal.getTime()));
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 10);
        userBrandPojo.setEndDateStr(StringUtil.dateToString(cal.getTime()));
        userBrandPojo.setSorting(0);
        userBrandPojo.setCreateDate(new Date());
        userBrandPojo.setUpdateDate(new Date());
        userBrandService.insertUserBrand(userBrandPojo);
        // List<UserBrandPojo> userBrandPojos = userBrandService.findUserBrandList(map);
        userMakerBrandPojo.setBrandId(userBrandPojo.getId());

        // 插入数据
        userMakerBrandService.addUserMakerBrand(userMakerBrandPojo);

        FileUtil.alertMessageBySkip("添加成功！", "geekBrandManage.do");
      }

    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("添加失败！", "geekBrandManage.do");
    }
    return null;
  }

  /**
   * 创客中心登录前端显示
   * 
   * @return String
   */
  public String geekLogin() {
    return SUCCESS;
  }

  /**
   * 创客中心登陆验证
   * 
   * @throws Exception
   * @return String
   */
  public String getLoginValidate() throws Exception {
    // 创建一个上下文容器
    ActionContext actionContext = ActionContext.getContext();
    // 判断前端获取的参数
    if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(passwd)) {
      // 将获取到用户名密码保存到POJO中，用以查询数据库表中是否有该记录
      SysLoginPojo loginPojo = new SysLoginPojo();
      loginPojo.setLoginname(username);
      loginPojo.setPassword(MD5Util.MD5(passwd));
      // 设置用户类型为创客，对应值为12
      loginPojo.setUserType("12");
      // 检测数据库表中是否有该用户
      if (loginService.loginCheckWeb(loginPojo)) {
        // 将获取到的数据保存在上下文容器中
        // 上下文容器中的geekBrand数据来源：LoginServiceImol.java中的loginCheckWeb方法
        SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("geekuser");
        // 判断数据库记录中的用户状态
        if (logiPojo != null && logiPojo.getStatus() == 1) {
          HttpServletRequest request = ServletActionContext.getRequest();
          // 构造一个用户登录日志对象，用来存放用户登录的信息
          LoginRecPojo loginRecPojo = new LoginRecPojo();
          loginRecPojo.setType(logiPojo.getType());// 设置日志中用户的类型
          loginRecPojo.setLoginDate(new Date());// 设置日志中用户登录时间
          loginRecPojo.setLoginIp(getIpAddr(request));// 设置日志中用户登录时所用的IP
          loginRecPojo.setUserId(logiPojo.getId());// 设置日志中用户的ID
          loginRecService.addLoginRec(loginRecPojo);// 将数据插入进日志表中，用以记录用户轨迹
          if (url != null && !url.equals("")) {
            FileUtil.alertMessageBySkip("登录成功", url);
            return null;
          } else {
            return SUCCESS;
          }

        } else {
          setMsg("您的帐号暂时不能登录，请联系管理员！");
          actionContext.put("loop", "false");
          return "gkloginweb";
        }
      }
      setMsg("用户名或密码错误！");
    }
    actionContext.put("loop", "false");
    return "gkloginweb";
  }

  /**
   * 跳转到创客中心用户注册
   * 
   * @throws Exception
   * @return String
   */
  public String goGeekRegWeb() throws Exception {
    return SUCCESS;
  }

  /**
   * 创客中心用户注册
   * 
   * @throw
   * @return String
   */
  public String doGeekRegWeb() {
    try {
      UserGrowthLinePojo userGrowthLinePojo = new UserGrowthLinePojo();
      // 判断前端页面传过来的数据
      if (sysLoginPojo == null) {
        return "gkregweb";
      }
      // 构建一个上下文容器
      ActionContext actionContext = ActionContext.getContext();
      // 检索数据库表中是否已有该用户记录
      SysLoginPojo s = sysLoginService.getSysLoginByLoginName(sysLoginPojo.getLoginname());
      if (s != null) {
        setMsg("该手机号码已经被注册了!");
        return "gkregweb";
      }
      // 用户验证码判断
      UserVerifyPojo userVerify = new UserVerifyPojo();
      userVerify.setLoginname(sysLoginPojo.getLoginname());// 获取验证码
      userVerify = userVerifyService.findNewestByPhone(userVerify);// 验证码数据库表检索
      if (userVerify == null || userVerify.getCaptcha() == null
          || !phonecode.equals(userVerify.getCaptcha())) {
        setMsg("验证码错误！请重新输入！");
        return "gkregweb";
      }
      // 设置新增用户昵称
      sysLoginPojo.setName("tzm" + sysLoginPojo.getLoginname());
      // 设置新增用户类型（创客类型的值为12）
      sysLoginPojo.setType("12");
      // 设置新增用户状态（审核通过的值为1，这里的目的是使用户注册后可以使用账户进行登录）
      sysLoginPojo.setStatus(1);
      // 设置新增用户排序
      sysLoginPojo.setSorting(0);
      // 设置新增用户密码（通过MD5加密）
      sysLoginPojo.setPassword(MD5Util.MD5(sysLoginPojo.getPassword()));
      // 设置创建时间
      sysLoginPojo.setCreateDate(new Date());
      String externalSignCode =
          sysLoginPojo.getName() + new Date().getTime() / 1000 + StringUtil.genRandomStr(4);
      externalSignCode = MD5.MD5Encode(externalSignCode);
      sysLoginPojo.setExternalSignCode(externalSignCode);
      sysLoginPojo.setInvitationCode(walletService.genInviteCode());
      // 将用户信息插入进数据库表
      int flag = loginService.insertLoginPojo(sysLoginPojo);
      if (flag == 1) {
        // 注册同时userinfo表需插入一条数据
        // sysLoginPojo = loginService.getLoginPojoByLoginnameAndPassword(sysLoginPojo);
        UserInfoPojo userInfoPojo = new UserInfoPojo();
        userInfoPojo.setUserId(sysLoginPojo.getId());
        userInfoPojo.setCreateDate(new Date());
        userInfoPojo.setStatus(1);
        userInfoService.insertUserInfo(userInfoPojo);
        // 注册同时usergrowthline表需插入一条数据
        userGrowthLinePojo.setUserId(sysLoginPojo.getId());
        userGrowthLineService.addUserGrowthLine(userGrowthLinePojo);
      }

      if (loginService.loginCheckWeb(sysLoginPojo)) {
        // 将获取到的数据保存在上下文容器中
        // 上下文容器中的geekuser数据来源：LoginServiceImol.java中的loginCheckWeb方法
        SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("geekuser");
        if (logiPojo != null) {
          sysLoginPojo.prePersist(logiPojo);
          loginService.updateLoginPojo(sysLoginPojo);
          HttpServletRequest request = ServletActionContext.getRequest();
          // 构造一个用户登录日志对象，用来存放用户登录的信息
          LoginRecPojo loginRecPojo = new LoginRecPojo();
          loginRecPojo.setType(logiPojo.getType());// 设置日志中用户的类型
          loginRecPojo.setLoginDate(new Date());// 设置日志中的登录时间
          loginRecPojo.setLoginIp(getIpAddr(request));// 设置日志中用户登录IP
          loginRecPojo.setUserId(logiPojo.getId());// 设置日志中用户ID
          loginRecService.addLoginRec(loginRecPojo);// 将数据插入到数据库表中

          FileUtil.alertMessageBySkip("注册成功！", "geekWeb.do");
          return null;
        } else {
          setMsg("注册失败，请重新注册或登录!");
          actionContext.put("loop", "false");
          return "gkloginweb";
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "gkregweb";
  }

  /**
   * 专题列表前端显示
   * 
   * @return
   * @throws Exception
   */
  public String getGeekThemeListWeb() throws Exception {
    ActionContext actionContext = ActionContext.getContext();
    Map<String, Object> map = new HashMap<String, Object>();
    SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("geekuser");
    int count = 0;
    if (logiPojo != null) {
      map.put("userId", logiPojo.getId());
      count = userMakerThemeService.userMakerThemeCount(map);
    }
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(count);
    return SUCCESS;
  }

  /**
   * 专题列表数据前端显示
   * 
   * @return
   * @throws SQLException
   */
  public String getGeekThemeListData() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("geekuser");
    if (logiPojo != null) {
      map.put("userId", logiPojo.getId());
      userMakerThemePojoList = userMakerThemeService.userMakerThemeList(map);
    }
    JSONArray json = JSONArray.fromObject(userMakerThemePojoList);
    page.setResult(json.toString());
    return SUCCESS;
  }



  /**
   * 跳转到专题列表前端显示
   * 
   * @return
   * @throws Exception
   */
  public String goAddGeekTheme() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增专题
   * 
   * @throws Exception
   * @return String
   */
  public String addGeekTheme() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      // 获取登录用户信息
      SysLoginPojo logiPojo = UserUtil.getGeekUser();
      if (logiPojo == null) {
        result = "0";
        // FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
        return SUCCESS;
      }
      if (userMakerThemePojo != null) {
        // 专题主图操作
        if (banner != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerTheme")
                  + File.separator;
          // FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerTheme/", banner);
          // // 图片压缩
          // CompressPicture cp = new CompressPicture();
          // String compressPath =
          // ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerTheme/small")
          // + File.separator;
          // cp.compressPic(banner, compressPath, "upfiles/userMakerTheme/small/", file_name, 300,
          // 300, true);

          // 图片压缩
          CompressPicture cp = new CompressPicture();
          cp.compressPic(banner, uploadPath, uploadPath, file_name, 0, 0, false);
          userMakerThemePojo.setBanner(file_name);
        } else {
          result = "noImg";
          return SUCCESS;
          // FileUtil.alertMessageBySkip("专题主图不能为空！", "goAddGeekTheme.do");
        }
        /*
         * if (userMakerThemePojo.getContent() == null || userMakerThemePojo.getContent() == "") {
         * FileUtil.alertMessageBySkip("内容不能为空！", "goAddGeekTheme.do"); }
         */
        // 设置新增时间
        userMakerThemePojo.setCreateDate(new Date());
        // 设置用户id
        userMakerThemePojo.setUserId(logiPojo.getId());
        // 设置用户昵称
        userMakerThemePojo.setMakerName(logiPojo.getName());
        // 设置状态
        userMakerThemePojo.setStatus(0);
        map.put("userId", logiPojo.getId());
        UserMakerBrandPojo userMakerBrand = userMakerBrandService.findMakerBrandByParams(map);
        if (userMakerBrand != null) {
          userMakerThemePojo.setMakerBrandId(userMakerBrand.getId());
          userMakerThemePojo.setMakerBrandName(userMakerBrand.getBrandName());
        } else {
          result = "noUser";
          // FileUtil.alertMessageBySkip("未能查询到该用户的创客品牌信息，请向后台技术人员咨询！", "getGeekThemeListWeb.do");
          return SUCCESS;
        }
        // 插入数据
        userMakerThemeService.addUserMakerTheme(userMakerThemePojo);
        // FileUtil.alertMessageBySkip("添加成功！", "getGeekThemeListWeb.do");
        result = String.valueOf(userMakerThemePojo.getId());
      }
    } catch (Exception e) {
      e.printStackTrace();
      result = null;
      // FileUtil.alertMessageBySkip("添加失败！", "getGeekThemeListWeb.do");
    }
    return SUCCESS;
  }


  /**
   * 删除专题
   * 
   * @throws Exception
   * @return String
   */
  public String delGeekTheme() throws Exception {
    try {
      // 获取登录用户信息
      SysLoginPojo logiPojo = UserUtil.getGeekUser();
      if (logiPojo == null) {
        FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
        return null;
      }
      if (userMakerThemePojo != null) {

        // 删除专题前判断该专题状态
        if (userMakerThemeService.getUserMakerThemeById(userMakerThemePojo.getId()).getIsDelete() == 1) {
          FileUtil.alertMessageBySkip("该记录已是删除状态，无法进行此操作！", "getGeekThemeListWeb.do");
        } else {
          userMakerThemePojo.setIsDelete(1L);// 设置为删除状态
          userMakerThemeService.updateUserMakerTheme(userMakerThemePojo);
          FileUtil.alertMessageBySkip("删除成功！", "getGeekThemeListWeb.do");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("操作失败！", "getGeekThemeListWeb.do");
    }
    return null;
  }


  /**
   * 跳转到修改专题
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateGeekTheme() throws Exception {

    try {
      // 获取登录用户信息
      SysLoginPojo logiPojo = UserUtil.getGeekUser();
      if (logiPojo == null) {
        FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
        return null;
      }
      if (userMakerThemePojo != null) {
        userMakerThemePojo =
            userMakerThemeService.getUserMakerThemeById(userMakerThemePojo.getId());
        // 查询自定义专题详情页面数据
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("pageId", userMakerThemePojo.getId());
        params.put("type", 2);
        templatePageDataPojo = templatePageDataService.findByParams(params);
        if (templatePageDataPojo == null) {
          templatePageDataPojo = new TemplatePageDataPojo();
          templatePageDataPojo.setData("{}");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 编辑专题
   * 
   * @throws Exception
   * @return String
   */
  public String updateGeekTheme() throws Exception {
    try {
      // 获取登录用户信息
      SysLoginPojo logiPojo = UserUtil.getGeekUser();
      if (logiPojo == null) {
        // FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
        result = "0";
        return SUCCESS;
      }
      if (userMakerThemePojo != null) {
        // 专题主图操作
        if (banner != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerTheme")
                  + File.separator;
          // FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerTheme/", banner);
          // // 图片压缩
          // CompressPicture cp = new CompressPicture();
          // String compressPath =
          // ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerTheme/small")
          // + File.separator;
          // cp.compressPic(banner, compressPath, "upfiles/userMakerTheme/small/", file_name, 300,
          // 300, true);

          // 图片压缩
          CompressPicture cp = new CompressPicture();
          cp.compressPic(banner, uploadPath, uploadPath, file_name, 0, 0, false);
          userMakerThemePojo.setBanner(file_name);
        }
        // 设置更新时间
        userMakerThemePojo.setUpdateDate(new Date());
        // 设置更新后专题状态
        userMakerThemePojo.setStatus(0);
        // 插入数据
        userMakerThemeService.updateUserMakerTheme(userMakerThemePojo);
        // FileUtil.alertMessageBySkip("修改成功！", "getGeekThemeListWeb.do");
        result = String.valueOf(userMakerThemePojo.getId());
      }
    } catch (Exception e) {
      e.printStackTrace();
      result = null;
    }
    return SUCCESS;
  }



  /**
   * 获取用户登录时IP地址
   * 
   * @param request
   * @return String
   */
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


  public void martshowApply() throws Exception {
    try {
      if (userMakerThemePojo != null) {
        if (upfile != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerTheme")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerTheme/", upfile);
          userMakerThemePojo.setBanner(file_name);
        }
        userMakerThemeService.addUserMakerTheme(userMakerThemePojo);
        FileUtil.alertMessageBySkip("添加成功！", "goMartshowApply.do");
      } else {

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 微页面添加
   * 
   * @return
   * @throw
   * @return String
   */
  public String addGeekThemeTemplatePageData() {
    SysLoginPojo sysLogin = UserUtil.getUser();
    if (sysLogin == null) {
      result = String.valueOf(0);
    } else {
      if (saveData != null && geekThemeId != null) {
        templatePageDataPojo = new TemplatePageDataPojo();
        templatePageDataPojo.setData(saveData);
        templatePageDataPojo.setType(2);
        templatePageDataPojo.setPageId(geekThemeId);
        templatePageDataPojo.setCreateBy(sysLogin.getId());
        templatePageDataPojo.setCreateDate(new Date());
        templatePageDataPojo.setUpdateBy(sysLogin.getId());
        templatePageDataPojo.setUpdateDate(new Date());
        templatePageDataService.add(templatePageDataPojo);
        result = String.valueOf(templatePageDataPojo.getId());
      } else {
        result = null;
      }
    }
    return SUCCESS;
  }

  /**
   * 
   * 修改自定义专题详情
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String updateGeekThemeTemplatePageData() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getGeekUser();
    if (sysLogin == null) {
      result = String.valueOf(0);
    } else {
      if (saveData != null && geekThemeId != null && dataId != null) {
        templatePageDataPojo = new TemplatePageDataPojo();
        templatePageDataPojo.setId(dataId);
        templatePageDataPojo.setData(saveData);
        templatePageDataPojo.setPageId(geekThemeId);
        templatePageDataPojo.setUpdateBy(sysLogin.getId());
        templatePageDataPojo.setUpdateDate(new Date());
        templatePageDataService.update(templatePageDataPojo);
        result = String.valueOf(templatePageDataPojo.getId());
      } else {
        // 第一次添加微页面时没保存执行添加
        if (dataId == null && geekThemeId != null) {
          templatePageDataPojo = new TemplatePageDataPojo();
          templatePageDataPojo.setData(saveData);
          templatePageDataPojo.setType(2);
          templatePageDataPojo.setPageId(geekThemeId);
          templatePageDataPojo.setUpdateBy(sysLogin.getId());
          templatePageDataPojo.setUpdateDate(new Date());
          templatePageDataService.add(templatePageDataPojo);
          result = String.valueOf(templatePageDataPojo.getId());
        } else {
          result = null;
        }
      }
    }
    return SUCCESS;
  }

  /**
   * 品牌列表前端显示
   * 
   * @return
   * @throws Exception
   */
  public String geekBrandManageCount() throws Exception {
    // 获取登录用户信息
    SysLoginPojo logiPojo = UserUtil.getGeekUser();

    if (logiPojo == null) {
      FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
      return null;
    }
    Map<String, Object> map = new HashMap<String, Object>();
    int count = 0;
    map.put("userId", logiPojo.getId());
    count = userMakerBrandService.userMakerBrandCount(map);
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(count);
    return SUCCESS;
  }

  /**
   * 品牌列表数据前端显示
   * 
   * @return
   * @throws SQLException
   */
  public String geekBrandManageList() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("geekuser");
    if (logiPojo != null) {
      map.put("userId", logiPojo.getId());
      userMakerBrandPojos = userMakerBrandService.userMakerBrandList(map);
    }
    JSONArray json = JSONArray.fromObject(userMakerBrandPojos);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 跳转添加品牌页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddGeekBrand() throws Exception {
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
   * 删除品牌
   * 
   * @throws Exception
   * @return String
   */
  public String delGeekBrand() throws Exception {
    try {
      // 获取登录用户信息
      SysLoginPojo logiPojo = UserUtil.getGeekUser();
      if (logiPojo == null) {
        FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
        return null;
      }
      userMakerBrandService.deleteUserMakerBrandById(userMakerBrandPojo.getId());
      userBrandPojo = userBrandService.findUserBrandById(userMakerBrandPojo.getBrandId());
      BrandDicPojo brandDicPojo = new BrandDicPojo();
      brandDicPojo.setId(userBrandPojo.getBrandId());
      brandDicService.delBrandDic(brandDicPojo);
      userBrandService.delUserBrand(userMakerBrandPojo.getBrandId());
      FileUtil.alertMessageBySkip("删除成功！", "geekBrandManage.do");

    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("操作失败！", "geekBrandManage.do");
    }
    return null;
  }

  public String getMainCategory() {
    try {
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

    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    JSONArray json = JSONArray.fromObject(productTypePojos);
    jsonStr = json.toString();
    return SUCCESS;
  }



  /***
   * 商品库商品总数
   * 
   * @return
   * @throws Exception
   */
  public String productAllCountWeb() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    ActionContext ac = ActionContext.getContext();
    // getProductType(0l);
    ProductTypePojo productTypePojo = new ProductTypePojo();
    productTypePojo.setPid(-1L);
    ac.put("productType1List", productTypeService.getProductTypeByPids(productTypePojo));
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
      return null;
    }
    Long uID = sysLogin.getId();
    map.put("userId", uID);
    ac.put("brandList", userBrandService.findUserBrandByUserId(map));
    if (page == null) {
      page = new Pager();
    }
    if (productPojo == null) {
      productPojo = new ProductPojo();
    }
    productPojo.setUserId(uID);
    productList = productService.getProductAll(productPojo, null);
    page.setRowCount(productList.size());
    return SUCCESS;
  }

  /***
   * 商品库商品列表
   * 
   * @return
   * @throws Exception
   */
  public String productAllListWeb() throws Exception {
    if (productPojo != null) {
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      Long uID = sysLogin.getId();
      page.setPageSize(10);
      productPojo.setUserId(uID);
      productList = productService.getProductAll(productPojo, page);
    }
    JSONArray json = JSONArray.fromObject(productList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /***
   * 跳到添加商品
   * 
   * @return
   * @throws Exception
   */
  public String goProductAddWeb() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    // getProductType(0l);
    ActionContext ac = ActionContext.getContext();
    map.put("type", "unit");
    ac.put("unit", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "brand");
    ac.put("brand", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "texture");
    ac.put("texture", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "age");
    ac.put("age", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "product_function");
    ac.put("productFunction", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "is_power");
    ac.put("isPower", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "pack");
    ac.put("pack", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "is_hotsale");
    ac.put("isHotsale", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "is_introduce");
    ac.put("isIntroduce", syntheticalDictService.getSyntheticalDictListByType(map));
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    Long uID = sysLogin.getId();
    map.put("userId", uID);
    map.put("status", 1);
    ac.put("brandList", userBrandService.findUserBrandByUserId(map));
    ProductTypePojo productTypePojo = new ProductTypePojo();
    productTypePojo.setPid(-1L);
    ac.put("productType1List", productTypeService.getProductTypeByPids(productTypePojo));
    return SUCCESS;
  }

  public void getProductType(Long pid) throws Exception {
    ProductTypePojo fatherType = new ProductTypePojo();
    fatherType.setId(pid);
    fatherType = productTypeService.findProductType(fatherType);
    ProductTypePojo pojo = new ProductTypePojo();
    if (productTypePojo != null && productTypePojo.getLevel() != null) {
      pojo.setTopLevel(productTypePojo.getTopLevel());
      productTypePojo.setLevel(null);
    } else {
      pojo.setPid(pid);
    }
    List<ProductTypePojo> childList = productTypeService.getProductTypeByPids(pojo);

    String typeTemp = fatherType == null ? "" : fatherType.getName() + "--";

    if (!childList.isEmpty()) {
      for (ProductTypePojo child : childList) {
        typeStr = typeStr + "<option value='" + child.getId() + "' ";
        if (productPojo != null && productPojo.getProductTypeId() != null
            && productPojo.getProductTypeId().equals(String.valueOf(child.getId()))) {
          typeStr = typeStr + " selected='selected'";
        }
        typeStr = typeStr + ">" + typeTemp + child.getName() + "</option>";
        getProductType(child.getId());
      }
    }
  }

  /***
   * 添加产品信息
   * 
   * @return
   * @throws Throwable
   */
  public String productAddWeb() throws Throwable {
    try {
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
        return null;
      }
      productPojo.setUserId(sysLogin.getId());
      productPojo.setCreateBy(sysLogin.getId());
      productPojo.setUpdateBy(sysLogin.getId());
      long uid = sysLogin.getId();
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
        // 给图片加水印
        // String waterPic =
        // ServletActionContext.getServletContext().getRealPath("/images")+File.separator+"water_logo.png";
        // ImageUtils.pressImageganji(uploadPath+file_name, waterPic, 50,
        // 50, 0.5f,false);
        // ImageUtils.pressImages(waterPic,uploadPath+file_name,4,0.5f);
        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
        productPojo.setImage(file_name);
      }
      productPojo.setIsNew("0");
      productPojo.setRecommend(0);
      StringBuffer sbf = new StringBuffer(RandomUtils.runVerifyCode(6));
      // sbf.append(new Date().getTime());
      // 生成二维码
      String content = "F***";
      String path =
          ServletActionContext.getServletContext().getRealPath("/upfiles/qrcode") + File.separator;
      File creapath = new File(path);
      if (!creapath.isDirectory()) {
        creapath.mkdirs();
      }
      MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
      Hashtable hints = new Hashtable();
      hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
      BitMatrix bitMatrix =
          multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
      SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
      String img = df.format(new Date());
      File file1 = new File(path, img + ".jpg");
      MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
      productPojo.setStatus(0);
      productPojo.setQrcode(img + ".jpg");
      productPojo.setProductNo(sbf.toString());
      productPojo.setLadderPrice("[]");
      /*
       * ProductTypePojo type = new ProductTypePojo();
       * type.setId(Long.parseLong(productPojo.getProductTypeId())); type =
       * productTypeService.findProductType(type);
       * //productPojo.setProductTypeIds(String.valueOf(type.getPid())); String typeIdStr =
       * ":"+String.valueOf(type.getPid())+":"; String value = ""; ProductTypePojo type2 = new
       * ProductTypePojo(); type2.setPid(0L); List<ProductTypePojo> productTypes = null; if
       * ("1".equals(productPojo.getAgeType())) { type2.setName("0-3岁婴幼儿玩具"); productTypes =
       * productTypeService.getProductTypeByPidName(type2); } else if
       * ("2".equals(productPojo.getAgeType())) { type2.setName("3-6岁儿童玩具"); productTypes =
       * productTypeService.getProductTypeByPidName(type2);
       * 
       * } else if ("3".equals(productPojo.getAgeType())) { type2.setName("6岁以上玩具"); productTypes =
       * productTypeService.getProductTypeByPidName(type2); } if (productTypes!=null &&
       * productTypes.size()>0) { value = String.valueOf(productTypes.get(0).getId()); }
       * if(!"".equals(value)){ typeIdStr += value +":"; } productPojo.setProductTypeIds(typeIdStr);
       */
      String typeIds = ":" + String.valueOf(productPojo.getProductTypeIds()) + ":";
      productPojo.setProductTypeIds(typeIds);
      productService.addProduct(productPojo);// 商品插入
      ProductSellPojo productSellPojo = new ProductSellPojo();
      productSellPojo.setProductId(productPojo.getId());
      productSellPojo.setProductName(productPojo.getProductName());
      productSellPojo.setProductImage(productPojo.getImage());
      productSellPojo.setPrice(productPojo.getDistributionPrice());
      productSellPojo.setSellNumber(productPojo.getSellNumber());
      productSellPojo.setCreateDate(new Date());
      productSellPojo.setCreateBy(productPojo.getCreateBy());
      productSellPojo.setUpdateDate(new Date());
      productSellPojo.setUpdateBy(productPojo.getUpdateBy());
      productSellPojo.setProductType1(productPojo.getProductType1());
      productSellPojo.setProductTypeIds(productPojo.getProductTypeIds());
      productSellPojo.setStatus(productPojo.getStatus());
      productSellService.add(productSellPojo);
      if (upfile != null) {
        // 商品图片1张
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
        productImagesPojo = new ProductImagesPojo();
        productImagesPojo.setProductId(productPojo.getId());
        productImagesPojo.setImages(file_name);
        productImagesPojo.setUserId(uid);
        productImagesPojo.setCreateBy(uid);
        productImagesPojo.setUpdateBy(uid);
        productImagesPojo.setStatus(1);
        productImagesPojo.setSorting(1);
        productImagesService.addProductImages(productImagesPojo);
      } else {
        productPojo.setImage("");
      }
      // 焦点图片9张
      if (upfiles != null && upfiles.length > 0) {
        for (int i = 0; i < upfiles.length; i++) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfiles[i]);
          // 图片压缩
          CompressPicture cp = new CompressPicture();
          String compressPath =
              ServletActionContext.getServletContext().getRealPath(
                  "/upfiles/productFocusImages/small")
                  + File.separator;
          cp.compressPic(upfiles[i], compressPath, "upfiles/productFocusImages/small/", file_name,
              300, 300, true);
          productFocusImages = new ProductFocusImagesPojo();
          productFocusImages.setProductId(productPojo.getId());
          productFocusImages.setImages(file_name);
          productFocusImages.setStatus(1);
          productFocusImages.setSorting(i + 1);
          productFocusImages.setUserId(uid);
          productFocusImages.setCreateBy(uid);
          productFocusImages.setUpdateBy(uid);
          productFocusImagesService.insertProductFocusImages(productFocusImages);
        }
      }
      // ProductPojo product = new ProductPojo();
      // product.setProductNameEn("5");
      // List<ProductPojo> productPojos = productService.getProductAll(product, page);
      // SKU插入
      // String message = "新增商品成功";
      result = "1" + productPojo.getId();
      Map<String, Object> map = new HashMap<String, Object>();
      Map<String, Object> map2 = new HashMap<String, Object>();
      if (productSkuLinkPojo == null) {
        productSkuLinkPojo = new ProductSkuLinkPojo();
      }
      if (skuAttributePojo == null) {
        skuAttributePojo = new SkuAttributePojo();
      }
      if (skuAttributePojo2 == null) {
        skuAttributePojo2 = new SkuAttributePojo();
      }
      if (tids != null) {
        for (int i = 0; i < tids.length; i++) {
          if (businessCodes != null && !"".equals(businessCodes[i])) {
            productSkuLinkPojo.setBusinessCode(businessCodes[i]);
          } else {
            // message = "新增商品成功，但sku商家编码不能为空！";
            result = "2" + productPojo.getId();
            continue;
          }
          if (stocks != null) {
            if ("".equals(stocks[i])) {
              // message = "新增商品成功，但sku库存不能为空！";
              result = "3" + productPojo.getId();
              continue;
            } else if (Integer.parseInt(stocks[i]) < 0) {
              // message = "新增商品成功，但sku库存不能小于0！";
              result = "4" + productPojo.getId();
              continue;
            } else {
              productSkuLinkPojo.setStock(Integer.parseInt(stocks[i]));
              productSkuLinkPojo.setStockNum(Integer.parseInt(stocks[i]));
            }
          }
          if (prices != null && !"".equals(prices[i])) {
            productSkuLinkPojo.setPrice(Double.parseDouble(prices[i]));
          } else {
            // message = "新增商品成功，但sku价格不能为空！";
            result = "5" + productPojo.getId();
            continue;
          }
          /*
           * if (status != null && !"".equals(status[i])) {
           * productSkuLinkPojo.setStatus(Integer.parseInt(status[i])); } else { // message =
           * "修改失败，sku审核状态不能为空！"; this.result = "6"; continue; }
           */
          if (formats != null && !"".equals(formats[i])) {
            map2.put("value", formats[i]);
            map2.put("productId", productPojo.getId());
            map2.put("attribute", "规格");
            List<SkuAttributePojo> SkuAttributePojos2 = skuAttributeService.getSkuAttribute(map2);
            if (SkuAttributePojos2.size() != 0) {
              productSkuLinkPojo.setSkuFormatId(SkuAttributePojos2.get(0).getId());
            } else {
              skuAttributePojo2.setValue(formats[i]);
              skuAttributePojo2.setAttribute("规格");
              skuAttributePojo2.setProductId(productPojo.getId());
              skuAttributePojo2.setStatus(1);
              skuAttributePojo2.setCreateDate(new Date());
              skuAttributePojo2.setUpdateDate(new Date());
              skuAttributeService.insertSkuAttribute(skuAttributePojo2);// 插入sku属性表
              productSkuLinkPojo.setSkuFormatId(skuAttributePojo2.getId());
            }
          } else {
            // message = "新增商品成功，但sku规格不能为空！";
            result = "7" + productPojo.getId();
            continue;
          }
          if (colors != null && !"".equals(colors[i])) {
            if (upfileSku != null && !"".equals(upfileSku[i])) {
              String file_name = StringUtil.getCurrentDateStr() + ".jpg";
              String uploadPath =
                  ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                      + File.separator;
              FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
              // 图片压缩
              CompressPicture cp = new CompressPicture();
              String compressPath =
                  ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                      + File.separator;
              cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 150, 150,
                  true);
              skuAttributePojo.setImage(file_name);
            }
            // else {
            // skuAttributePojo.setImage("");
            // message = "新增商品成功，但sku图片不能为空！";
            // continue;
            // }
            map.put("value", colors[i]);
            map.put("productId", productPojo.getId());
            map.put("attribute", "颜色");
            List<SkuAttributePojo> SkuAttributePojos = skuAttributeService.getSkuAttribute(map);
            if (SkuAttributePojos.size() != 0) {
              productSkuLinkPojo.setSkuColorId(SkuAttributePojos.get(0).getId());
            } else {
              skuAttributePojo.setValue(colors[i]);
              skuAttributePojo.setAttribute("颜色");
              skuAttributePojo.setProductId(productPojo.getId());
              skuAttributePojo.setStatus(1);
              skuAttributePojo.setCreateDate(new Date());
              skuAttributePojo.setUpdateDate(new Date());
              skuAttributeService.insertSkuAttribute(skuAttributePojo);// 插入sku属性表
              productSkuLinkPojo.setSkuColorId(skuAttributePojo.getId());
            }
            productSkuLinkPojo.setProductId(productPojo.getId());
            // productSkuLinkPojo.setStatus(1);
            productSkuLinkPojo.setType(0);
            productSkuLinkPojo.setActivityId(0L);
            ProductSkuLinkPojo productSkuLink =
                productSkuLinkService.findProductSkuLink(productSkuLinkPojo);
            if (productSkuLink != null) {
              productSkuLinkPojo.setId(productSkuLink.getId());
              productSkuLinkService.productSkuLinkUpdate(productSkuLinkPojo);// 修改产品sku表
              // message = "新增商品成功！";
              result = "1" + productPojo.getId();
            } else {
              productSkuLinkService.addSkuLinkByProductId(productSkuLinkPojo);// 插入产品sku表
              // message = "新增商品成功！";
              result = "1" + productPojo.getId();
            }
          } else {
            // message = "新增商品成功，但sku颜色不能为空！";
            result = "8" + productPojo.getId();
            continue;
          }
        }
      }
      // FileUtil.alertMessageBySkip(message, "productManageGeekWeb.do");
      // return null;
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /***
   * 获取二级类目
   * 
   * @return
   */
  public String getProductType2() throws Exception {
    /*
     * typeStr = ""; getProductType(0l); System.out.println(typeStr); Map<String, String> map = new
     * HashMap<String,String>(); map.put("data", typeStr); JSONArray json =
     * JSONArray.fromObject(map); result=json.toString();
     */
    typeIdsStr = "";
    if (productPojo != null && productPojo.getProductType1() != null) {
      productTypePojo = new ProductTypePojo();
      productTypePojo.setLevel(1);
      productTypePojo.setTopLevel(productPojo.getProductType1());
    }
    List<ProductTypePojo> productTypeIdsList =
        productTypeService.getProductTypeByPids(productTypePojo);
    for (ProductTypePojo productTypeIds : productTypeIdsList) {
      typeIdsStr = typeIdsStr + "<option value='" + productTypeIds.getId() + "' ";
      if (productPojo != null && productPojo.getProductTypeIds() != null
          && productPojo.getProductTypeIds().equals(String.valueOf(productTypeIds.getId()))) {
        typeIdsStr = typeIdsStr + " selected='selected'";
      }
      typeIdsStr = typeIdsStr + ">" + productTypeIds.getName() + "</option>";
    }
    Map<String, String> map = new HashMap<String, String>();
    map.put("data", typeIdsStr);
    JSONArray json = JSONArray.fromObject(map);
    result = json.toString();
    return SUCCESS;
  }

  /***
   * 获取三级类目
   * 
   * @return
   */
  public String getProductType3() throws Exception {
    typeIdStr = "";
    if (productPojo != null && productPojo.getProductTypeIds() != null) {
      productTypePojo = new ProductTypePojo();
      productTypePojo.setPid(Long.parseLong(productPojo.getProductTypeIds()));
    }
    List<ProductTypePojo> productTypeIdList =
        productTypeService.getProductTypeByPids(productTypePojo);
    for (ProductTypePojo productTypeId : productTypeIdList) {
      typeIdStr = typeIdStr + "<option value='" + productTypeId.getId() + "' ";
      if (productPojo != null && productPojo.getProductTypeId() != null
          && productPojo.getProductTypeId().equals(String.valueOf(productTypeId.getId()))) {
        typeIdStr = typeIdStr + " selected='selected'";
      }
      typeIdStr = typeIdStr + ">" + productTypeId.getName() + "</option>";
    }
    Map<String, String> map = new HashMap<String, String>();
    map.put("data", typeIdStr);
    JSONArray json = JSONArray.fromObject(map);
    result = json.toString();
    return SUCCESS;
  }

  /***
   * 跳转到修改产品
   * 
   * @return
   */
  public String goFindProduct() throws Exception {
    if (productPojo != null) {
      productPojo = productService.findProduct(productPojo);
      if (productPojo != null) {
        Map<String, Object> map = new HashMap<String, Object>();
        // getProductType(0l);
        ActionContext ac = ActionContext.getContext();
        map.put("type", "unit");
        ac.put("unit", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "brand");
        ac.put("brand", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "texture");
        ac.put("texture", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "age");
        ac.put("age", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "product_function");
        ac.put("productFunction", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "is_power");
        ac.put("isPower", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "pack");
        ac.put("pack", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "is_hotsale");
        ac.put("isHotsale", syntheticalDictService.getSyntheticalDictListByType(map));
        map.put("type", "is_introduce");
        ac.put("isIntroduce", syntheticalDictService.getSyntheticalDictListByType(map));
        SysLoginPojo sysLogin = UserUtil.getWebUser();
        Long uID = sysLogin.getId();
        map.put("userId", uID);
        ac.put("brandList", userBrandService.findUserBrandByUserId(map));
        ProductTypePojo productTypePojo = new ProductTypePojo();
        productTypePojo.setPid(-1L);
        ac.put("productType1List", productTypeService.getProductTypeByPids(productTypePojo));
        if (productPojo != null && productPojo.getProductType1() != null) {
          getProductType2();
          getProductType3();
        }
        if (productSkuLinkPojo == null) {
          productSkuLinkPojo = new ProductSkuLinkPojo();
        }
        productSkuLinkPojo.setProductId(productPojo.getId());
        productSkuLinkPojo.setType(0);
        List<ProductSkuLinkPojo> productSkuLinkList =
            productSkuLinkService.getProductSkuLinkAll(productSkuLinkPojo, null);
        JSONArray json = JSONArray.fromObject(productSkuLinkList);
        productSkuLinkPojo.setLadderSku(json.toString());

        // 商品图片1张
        proImgId = new long[10];
        images = new String[10];
        productImagesPojo = new ProductImagesPojo();
        productImagesPojo.setProductId(productPojo.getId());
        productImagesList = productImagesService.getProductImagesAll(productImagesPojo, null);
        if (productImagesList.size() > 0) {
          proImgId[0] = productImagesList.get(0).getId();
        }

        // 焦点图片9张
        productFocusImages = new ProductFocusImagesPojo();
        productFocusImages.setProductId(productPojo.getId());
        productFocusImagesList =
            productFocusImagesService.productFocusImagesAllList(productFocusImages, null);
        if (productFocusImagesList.size() > 0) {
          int n = 1;
          for (ProductFocusImagesPojo p : productFocusImagesList) {
            if (n <= 9) {
              // ac.put("image" + n, p.getImages());
              images[n] = p.getImages();
              proImgId[n] = p.getId();
              n++;
            }
          }

          // List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
          // Map<String, Object> item = new HashMap<String, Object>();
          // item.put("images", images);
          // item.put("proImgId", proImgId);
          // list.add(item);

          // List<Object> list = new ArrayList<Object>();
          // list.add(images);
          // list.add(proImgId);
          // ac.put("proImages", list);

          ac.put("images", images);
        }
      }
    }
    return SUCCESS;
  }

  /***
   * 删除
   * 
   * @return
   */
  public String deleProduct() throws SQLException {
    try {
      productService.deleProduct(productPojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /***
   * 批量删除
   * 
   * @return
   */
  public String productDeleteIdWeb() {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      return null;
    }
    if (productPojo == null) {
      productPojo = new ProductPojo();
    }
    productPojo.setUserId(sysLogin.getId());
    if (tids != null) {
      productService.productDeleteId(tids);
      FileUtil.alertMessageBySkip("删除成功！", "productManageGeekWeb.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "productManageGeekWeb.do");
    }
    return null;
  }

  /***
   * 添加产品信息
   * 
   * @return
   * @throws Throwable
   */
  public String productAddContentGeek() throws Throwable {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
      return null;
    }
    try {
      productPojo.setVersion(1);
      productService.productUpdate(productPojo);
      FileUtil.alertMessageBySkip("编辑成功", "productManageGeekWeb.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("编辑失败", "goProductAddGeekWeb.do");
    }
    return null;
  }

  /***
   * 修改商品
   * 
   * @return
   * @throws Throwable
   */
  public String productUpdate() throws Throwable {
    SysLoginPojo sysLogin = UserUtil.getGeekUser();
    if (sysLogin == null || productPojo == null) {
      FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
      return null;
    }
    // if (productPojo != null) {
    productPojo.setUserId(sysLogin.getId());
    productPojo.setUpdateBy(sysLogin.getId());
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
      // 图片压缩
      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
              + File.separator;
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
      productPojo.setImage(file_name);

      // 商品图片1张
      file_name = StringUtil.getCurrentDateStr() + ".jpg";
      uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
      productImagesPojo = new ProductImagesPojo();
      productImagesPojo.setImages(file_name);
      productImagesPojo.setId(proImgId1);
      productImagesService.productImagesUpdate(productImagesPojo);
    } else {
      productPojo.setImage("");
    }

    String typeIds = ":" + String.valueOf(productPojo.getProductTypeIds()) + ":";
    productPojo.setProductTypeIds(typeIds);
    // 商品修改
    productService.productUpdate(productPojo);
    ProductSellPojo productSellPojo = new ProductSellPojo();
    productSellPojo.setProductId(productPojo.getId());
    productSellPojo.setProductName(productPojo.getProductName());
    productSellPojo.setProductImage(productPojo.getImage());
    productSellPojo.setPrice(productPojo.getDistributionPrice());
    productSellPojo.setSellNumber(productPojo.getSellNumber());
    productSellPojo.setUpdateDate(new Date());
    productSellPojo.setProductType1(productPojo.getProductType1());
    productSellPojo.setProductTypeIds(productPojo.getProductTypeIds());
    productSellPojo.setStatus(productPojo.getStatus());
    productSellService.update(productSellPojo);
    // 焦点图片9张
    // 删除
    if (proImgId != null && proImgId.length > 0 && proImgId[0] != 0) {
      productFocusImages = new ProductFocusImagesPojo();
      productFocusImages.setProductId(productPojo.getId() == null ? 0l : productPojo.getId());
      productFocusImages.setCids(proImgId);
      productFocusImagesList =
          productFocusImagesService.productFocusImagesAllList(productFocusImages, null);
      if (productFocusImagesList.size() > 0) {
        for (ProductFocusImagesPojo p : productFocusImagesList) {
          productFocusImagesService.deleteProductFocusImages(p.getId());
        }
      }
    }
    // 修改
    if (proImgId != null && proImgId.length > 0) {
      int sorting = 0;
      int fileCount = 0;
      for (int i = 0; i < proImgId.length; i++) {
        if (upfiles2 != null && upfiles2.length > 0 && upfiles2[0].length() > 0) {
          if (isUpFiles != null && isUpFiles[i] == 1) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/",
                upfiles2[fileCount]);
            // 图片压缩
            CompressPicture cp = new CompressPicture();
            String compressPath =
                ServletActionContext.getServletContext().getRealPath(
                    "/upfiles/productFocusImages/small")
                    + File.separator;
            cp.compressPic(upfiles2[fileCount], compressPath, "upfiles/productFocusImages/small/",
                file_name, 300, 300, true);
            productFocusImages = new ProductFocusImagesPojo();
            productFocusImages.setId(proImgId[i]);
            productFocusImages.setImages(file_name);
            sorting++;
            productFocusImages.setSorting(sorting);
            productFocusImagesService.updateProductFocusImages(productFocusImages);
            fileCount++;
          }
        }
      }
    }
    // 添加
    if (upfiles != null && upfiles.length > 0) {
      long uid = sysLogin.getId();
      int sorting = 0;
      if (upfiles2 != null) {
        sorting = upfiles2.length;
      }
      for (int i = 0; i < upfiles.length; i++) {
        if (upfiles[i].length() > 0) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfiles[i]);
          // 图片压缩
          CompressPicture cp = new CompressPicture();
          String compressPath =
              ServletActionContext.getServletContext().getRealPath(
                  "/upfiles/productFocusImages/small")
                  + File.separator;
          cp.compressPic(upfiles[i], compressPath, "upfiles/productFocusImages/small/", file_name,
              300, 300, true);
          productFocusImages = new ProductFocusImagesPojo();
          productFocusImages.setProductId(productPojo.getId());
          productFocusImages.setImages(file_name);
          productFocusImages.setUserId(uid);
          productFocusImages.setCreateBy(uid);
          productFocusImages.setUpdateBy(uid);
          productFocusImages.setStatus(1);
          sorting++;
          productFocusImages.setSorting(sorting);
          productFocusImagesService.addProductFocusImagesService(productFocusImages);
        }
      }
    }

    // SKU修改
    String message = "";
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> map2 = new HashMap<String, Object>();
    if (productSkuLinkPojo == null) {
      productSkuLinkPojo = new ProductSkuLinkPojo();
    }
    if (skuAttributePojo == null) {
      skuAttributePojo = new SkuAttributePojo();
    }
    if (skuAttributePojo2 == null) {
      skuAttributePojo2 = new SkuAttributePojo();
    }
    if (tids != null) {
      for (int i = 0; i < tids.length; i++) {
        if (!tids[i].equals("on")) {
          productSkuLinkPojo.setId(Long.parseLong(tids[i]));
        } else {
          productSkuLinkPojo.setId(null);
        }
        if (businessCodes != null && !"".equals(businessCodes[i])) {
          productSkuLinkPojo.setBusinessCode(businessCodes[i]);
        } else {
          message = "修改失败，sku商家编码不能为空！";
          continue;
        }
        if (stocks != null) {
          if ("".equals(stocks[i])) {
            message = "修改失败，sku库存不能为空！";
            continue;
          } else if (Integer.parseInt(stocks[i]) < 0) {
            message = "修改失败，sku库存不能小于0！";
            continue;
          } else {
            productSkuLinkPojo.setStock(Integer.parseInt(stocks[i]));
            productSkuLinkPojo.setStockNum(Integer.parseInt(stocks[i]));
          }
        }
        if (prices != null && !"".equals(prices[i])) {
          productSkuLinkPojo.setPrice(Double.parseDouble(prices[i]));
        } else {
          message = "修改失败，sku价格不能为空！";
          continue;
        }
        if (status != null && !"".equals(status[i])) {
          productSkuLinkPojo.setStatus(Integer.parseInt(status[i]));
        } else {
          message = "修改失败，sku审核状态不能为空！";
          continue;
        }
        if (formats != null && formats[i] != "") {
          skuAttributePojo2.setValue(formats[i]);
          map2.put("value", formats[i]);
          map2.put("productId", productPojo.getId());
          map2.put("attribute", "规格");
          List<SkuAttributePojo> SkuAttributePojos2 = skuAttributeService.getSkuAttribute(map2);// 查询sku属性表规格
          if (SkuAttributePojos2.size() != 0) {
            productSkuLinkPojo.setSkuFormatId(SkuAttributePojos2.get(0).getId());
            // skuAttributePojo2.setId(SkuAttributePojos2.get(0).getId());
            // skuAttributeService.skuAttributeUpdateById(skuAttributePojo2);//修改sku属性表规格
          } else {
            skuAttributePojo2.setValue(formats[i]);
            skuAttributePojo2.setAttribute("规格");
            skuAttributePojo2.setProductId(productPojo.getId());
            skuAttributePojo2.setStatus(1);
            skuAttributePojo2.setCreateDate(new Date());
            skuAttributePojo2.setUpdateDate(new Date());
            skuAttributeService.insertSkuAttribute(skuAttributePojo2);// 插入sku属性表
            productSkuLinkPojo.setSkuFormatId(skuAttributePojo2.getId());
          }
        } else {
          message = "修改失败，sku规格不能为空！";
          continue;
        }
        if (colors != null && !"".equals(colors[i])) {
          skuAttributePojo.setValue(colors[i]);
          SkuAttributePojo skuAttributePojo3 = new SkuAttributePojo();
          if (images != null && !"".equals(images[i])) {
            skuAttributePojo.setImage(images[i]);
            skuAttributePojo3.setImage(images[i]);
          }
          // else {
          // skuAttributePojo.setImage("");
          // skuAttributePojo3.setImage("");
          // message = "修改失败，sku图片不能为空！";
          // continue;
          // }
          /*
           * if(images != null ){ if( !"".equals(images[i])){ if(upfileSkus != null){ for (int s=i ;
           * s <upfileSkus.length; s++) { if (upfileSkus[s]!=null && ""!=upfileSkus[s]) {
           * FileInputStream fin = new FileInputStream(upfileSkus[i]); String file_name =
           * StringUtil.getCurrentDateStr() + ".jpg"; String uploadPath =
           * ServletActionContext.getServletContext().getRealPath( "/upfiles/product") +
           * File.separator; File dirfile = new File(uploadPath); // 是否存在目录 如果不存在创建文件 if
           * (!dirfile.exists() || null != dirfile) { dirfile.mkdirs(); } FileUtil.copyFile(fin,
           * uploadPath + file_name); // 图片压缩 CompressPicture cp = new CompressPicture(); String
           * compressPath = ServletActionContext.getServletContext().getRealPath(
           * "/upfiles/product/small") + File.separator; cp.compressPic(uploadPath, compressPath,
           * file_name, file_name, 150, 150, true); skuAttributePojo.setImage(file_name);
           * skuAttributePojo3.setImage(file_name); } else { skuAttributePojo.setImage("");
           * skuAttributePojo3.setImage(images[i]); } break; } } }else{ if(upfileSku != null){ for
           * (int t = d; t <upfileSku.length; t++) { if (upfileSku[t]!=null &&
           * !"".equals(upfileSku[t]) ) { FileInputStream fin = new FileInputStream(upfileSku[t]);
           * String file_name = StringUtil.getCurrentDateStr() + ".jpg"; String uploadPath =
           * ServletActionContext.getServletContext().getRealPath( "/upfiles/product") +
           * File.separator; File dirfile = new File(uploadPath); // 是否存在目录 如果不存在创建文件 if
           * (!dirfile.exists() || null != dirfile) { dirfile.mkdirs(); } FileUtil.copyFile(fin,
           * uploadPath + file_name); // 图片压缩 CompressPicture cp = new CompressPicture(); String
           * compressPath = ServletActionContext.getServletContext().getRealPath(
           * "/upfiles/product/small") + File.separator; cp.compressPic(uploadPath, compressPath,
           * file_name, file_name, 150, 150, true); skuAttributePojo.setImage(file_name);
           * skuAttributePojo3.setImage(file_name); } else { skuAttributePojo.setImage("");
           * skuAttributePojo3.setImage(""); } d++; break; }
           * if("".equals(skuAttributePojo3.getImage())){ message="修改失败，新加sku图片不能为空！"; continue; } }
           * } }
           */
          map.put("value", colors[i]);
          map.put("productId", productPojo.getId());
          map.put("attribute", "颜色");
          List<SkuAttributePojo> SkuAttributePojos = skuAttributeService.getSkuAttribute(map);// 查询sku属性表颜色
          if (SkuAttributePojos.size() != 0) {
            productSkuLinkPojo.setSkuColorId(SkuAttributePojos.get(0).getId());
            skuAttributePojo3.setId(SkuAttributePojos.get(0).getId());
            skuAttributeService.skuAttributeUpdateById(skuAttributePojo3);// 修改sku属性表颜色
          } else {
            skuAttributePojo.setValue(colors[i]);
            skuAttributePojo.setAttribute("颜色");
            skuAttributePojo.setProductId(productPojo.getId());
            skuAttributePojo.setStatus(1);
            skuAttributePojo.setCreateDate(new Date());
            skuAttributePojo.setUpdateDate(new Date());
            skuAttributeService.insertSkuAttribute(skuAttributePojo);// 插入sku属性表
            productSkuLinkPojo.setSkuColorId(skuAttributePojo.getId());
          }
          if (productSkuLinkPojo.getId() != null) {
            productSkuLinkService.productSkuLinkUpdate(productSkuLinkPojo);// 修改产品sku表
            message = "修改成功！";
          } else {
            productSkuLinkPojo.setProductId(productPojo.getId());
            // productSkuLinkPojo.setStatus(1);
            productSkuLinkPojo.setType(0);
            productSkuLinkPojo.setActivityId(0L);
            ProductSkuLinkPojo productSkuLink =
                productSkuLinkService.findProductSkuLink(productSkuLinkPojo);
            if (productSkuLink == null) {
              if (stocks != null && !"".equals(stocks[i])) {
                productSkuLinkPojo.setStockNum(Integer.parseInt(stocks[i]));
              }
              productSkuLinkService.addSkuLinkByProductId(productSkuLinkPojo);// 插入产品sku表
              message = "修改成功！";
            }
          }
        } else {
          message = "修改失败，sku颜色不能为空！";
          continue;
        }
      }
    }
    FileUtil.alertMessageBySkip(message, "productManageGeekWeb.do");
    // } else {
    // FileUtil.alertMessageBySkip("请先登录", "geekLogin.do");
    // return null;
    // }
    return null;
  }

  /***
   * 上传SKU图片
   * 
   * @return
   * @throws Throwable
   */
  public String uploadSkuImages() throws Throwable {
    try {
      if (upfileSku1 != null) {
        File fin = new File(upfileSku1);
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", fin);
        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 150, 150, true);
        // skuImagesUrl=compressPath+file_name;
        skuImagesUrl = file_name;
      } else {
        skuImagesUrl = "";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /***
   * 删除商品
   * 
   * @return
   */
  public String deleProductSkuLink() throws SQLException {
    ProductSkuLinkPojo productSku = null;
    try {
      productSku = delCheckSkuColor(productSkuLinkPojo.getId());
      productSkuLinkService.deleProductSkuLink(productSkuLinkPojo.getId());
      if (productSku != null && productSku.getSkuColorId() != null) {
        skuAttributeService.deleSkuAttribute(productSku.getSkuColorId());
      }
      if (productSku != null && productSku.getSkuFormatId() != null) {
        skuAttributeService.deleSkuAttribute(productSku.getSkuFormatId());
      }
      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  /**
   * 删除SKU时，查询颜色/规格是否有其他SKU关联
   * 
   * @param id
   * @return
   * @throws SQLException
   */
  public ProductSkuLinkPojo delCheckSkuColor(long id) throws SQLException {
    ProductSkuLinkPojo productSku = new ProductSkuLinkPojo();
    productSku.setId(id);
    productSku = productSkuLinkService.findProductSkuLink(productSku);
    if (productSku != null) {
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("id", productSku.getId());
      param.put("skuColorId", productSku.getSkuColorId());
      int count = productSkuLinkService.findSkuLinkByOtherSku(param);
      if (count > 0) {
        productSku.setSkuColorId(null);
      }
      param.clear();
      param.put("id", productSku.getId());
      param.put("skuFormatId", productSku.getSkuFormatId());
      count = productSkuLinkService.findSkuLinkByOtherSku(param);
      if (count > 0) {
        productSku.setSkuFormatId(null);
      }
    }
    return productSku;
  }

  // 产品图片集列表
  public String getCountProductImages() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(productImagesService.getCount(productImagesPojo));
    // ActionContext ac = ActionContext.getContext();
    // ac.put("productImagesPojo2", productImagesPojo);
    return SUCCESS;
  }

  // 产品图片集列表
  public String getProductImagesAll() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    productImagesList = productImagesService.getProductImagesAll(productImagesPojo, page);
    JSONArray json = JSONArray.fromObject(productImagesList);
    page.setResult(json.toString());
    return SUCCESS;

  }

  // 产品图片集审核
  public String checkProductImages() throws SQLException {
    try {
      productImagesService.checkProductImages(productImagesPojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  // 产品图片集取消审核
  public String unCheckProductImages() throws SQLException {
    try {
      productImagesService.unCheckProductImages(productImagesPojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  // 产品图片集删除
  public String deleProductImages() throws SQLException {
    try {
      productImagesService.deleProductImages(productImagesPojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  // 产品图片集批量删除
  public String productImagesDeleteId() {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      return null;
    }
    productImagesPojo.setUserId(sysLogin.getId());
    StringBuffer url = new StringBuffer("productImagesManageGeekWeb.do");
    if (productImagesPojo != null && productImagesPojo.getProductId() != null) {
      url.append("?productImagesPojo.productId=" + productImagesPojo.getProductId()
          + "&productImagesPojo.userId=" + productImagesPojo.getUserId());
    }
    if (tids != null) {
      productImagesService.productImagesDeleteId(tids);
      FileUtil.alertMessageBySkip("删除成功！", url.toString());
    } else {
      FileUtil.alertMessageBySkip("删除失败！", url.toString());
    }
    return null;
  }

  // 产品图片集批量审核
  public String productImagescheckAll() {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      return null;
    }
    productImagesPojo.setUserId(sysLogin.getId());
    StringBuffer url = new StringBuffer("productImagesManageGeekWeb.do");
    if (productImagesPojo != null && productImagesPojo.getProductId() != null) {
      url.append("?productImagesPojo.productId=" + productImagesPojo.getProductId()
          + "&productImagesPojo.userId=" + productImagesPojo.getUserId());
    }
    for (String tid : tids) {
      try {
        productImagesService.checkProductImages(Long.parseLong(tid));
        FileUtil.alertMessageBySkip("审核成功！", url.toString());
      } catch (SQLException e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("审核失败！", url.toString());
      }
    }
    return null;
  }

  // 跳转到产品图片集修改图片
  public String goFindProductImages() {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      return null;
    }
    Long puserId = sysLogin.getId();
    productImagesPojo.setUserId(puserId);
    productImagesPojo = productImagesService.findProductImages(productImagesPojo);
    return SUCCESS;
  }

  // 产品图片集修改图片
  public String productImagesUpdate() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
      // 给图片加水印
      // String waterPic =
      // ServletActionContext.getServletContext().getRealPath("/images")+File.separator+"water_logo.png";
      // ImageUtils.pressImageganji(uploadPath+file_name, waterPic, 50,
      // 50, 0.5f,false);
      // ImageUtils.pressImages(waterPic,uploadPath+file_name,4,0.5f);

      // 图片压缩
      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
              + File.separator;
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
      productImagesPojo.setImages(file_name);
      // productImagesPojo.setStatus(0);
    }
    productImagesService.productImagesUpdate(productImagesPojo);
    return SUCCESS;
  }

  // 跳转到产品图片集添加图片
  public String goproductImagesAddWeb() {
    return SUCCESS;
  }

  // 产品图片集添加图片
  public String productImagesAddWeb() throws Throwable {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      return null;
    }
    if (productImagesPojo != null && !"".equals(productImagesPojo.getProductId())) {
      long uid = sysLogin.getId();
      int max = 0;
      List<ProductImagesPojo> productImagesPojos =
          productImagesService.getProductImagesAll(productImagesPojo, null);
      if (productImagesPojos.size() > 0) {
        max = productImagesPojos.get(productImagesPojos.size() - 1).getSorting();
      }
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
        // 给图片加水印
        // String waterPic =
        // ServletActionContext.getServletContext().getRealPath("/images")+File.separator+"water_logo.png";
        // ImageUtils.pressImageganji(uploadPath+file_name, waterPic,
        // 50, 50, 0.5f,false);
        // ImageUtils.pressImages(waterPic,uploadPath+file_name,4,0.5f);
        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
        productImagesPojo.setImages(file_name);
        productImagesPojo.setUserId(uid);
        productImagesPojo.setCreateBy(uid);
        productImagesPojo.setUpdateBy(uid);
        productImagesPojo.setStatus(1);
        productImagesPojo.setSorting(max + 1);
        productImagesService.addProductImages(productImagesPojo);
      }
      if (upfile2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile2);

        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile2, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
        productImagesPojo2.setImages(file_name);
        productImagesPojo2.setUserId(uid);
        productImagesPojo2.setCreateBy(uid);
        productImagesPojo2.setUpdateBy(uid);
        productImagesPojo2.setStatus(1);
        productImagesPojo2.setSorting(max + 2);
        productImagesService.addProductImages(productImagesPojo2);
      }
      if (upfile3 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile3);

        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile3, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
        productImagesPojo3.setImages(file_name);
        productImagesPojo3.setUserId(uid);
        productImagesPojo3.setCreateBy(uid);
        productImagesPojo3.setUpdateBy(uid);
        productImagesPojo3.setStatus(1);
        productImagesPojo3.setSorting(max + 3);
        productImagesService.addProductImages(productImagesPojo3);
      }
      if (upfile4 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile4);
        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile4, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
        productImagesPojo4.setImages(file_name);
        productImagesPojo4.setUserId(uid);
        productImagesPojo4.setCreateBy(uid);
        productImagesPojo4.setUpdateBy(uid);
        productImagesPojo4.setStatus(1);
        productImagesPojo4.setSorting(max + 4);
        productImagesService.addProductImages(productImagesPojo4);
      }
      if (upfile5 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile5);
        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile5, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
        productImagesPojo5.setImages(file_name);
        productImagesPojo5.setUserId(uid);
        productImagesPojo5.setCreateBy(uid);
        productImagesPojo5.setUpdateBy(uid);
        productImagesPojo5.setStatus(1);
        productImagesPojo5.setSorting(max + 5);
        productImagesService.addProductImages(productImagesPojo5);
      }
      if (upfile6 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile6);
        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile6, compressPath, "upfiles/product/small/", file_name, 300, 300, true);

        productImagesPojo6.setImages(file_name);
        productImagesPojo6.setUserId(uid);
        productImagesPojo6.setCreateBy(uid);
        productImagesPojo6.setUpdateBy(uid);
        productImagesPojo6.setStatus(1);
        productImagesPojo6.setSorting(max + 6);
        productImagesService.addProductImages(productImagesPojo6);
      }
      if (upfile7 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile7);

        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile7, compressPath, "upfiles/product/small/", file_name, 300, 300, true);

        productImagesPojo7.setImages(file_name);
        productImagesPojo7.setUserId(uid);
        productImagesPojo7.setCreateBy(uid);
        productImagesPojo7.setUpdateBy(uid);
        productImagesPojo7.setStatus(1);
        productImagesPojo7.setSorting(max + 7);
        productImagesService.addProductImages(productImagesPojo7);
      }

      if (upfile8 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile8);
        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile8, compressPath, "upfiles/product/small/", file_name, 300, 300, true);

        productImagesPojo8.setImages(file_name);
        productImagesPojo8.setUserId(uid);
        productImagesPojo8.setCreateBy(uid);
        productImagesPojo8.setUpdateBy(uid);
        productImagesPojo8.setStatus(1);
        productImagesPojo8.setSorting(max + 8);
        productImagesService.addProductImages(productImagesPojo8);
      }

      if (upfile9 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile9);

        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile9, compressPath, "upfiles/product/small/", file_name, 300, 300, true);

        productImagesPojo9.setImages(file_name);
        productImagesPojo9.setUserId(uid);
        productImagesPojo9.setCreateBy(uid);
        productImagesPojo9.setUpdateBy(uid);
        productImagesPojo9.setStatus(1);
        productImagesPojo9.setSorting(max + 9);
        productImagesService.addProductImages(productImagesPojo9);
      }

      if (upfile10 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile10);

        // 图片压缩
        CompressPicture cp = new CompressPicture();
        String compressPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                + File.separator;
        cp.compressPic(upfile10, compressPath, "upfiles/product/small/", file_name, 300, 300, true);

        productImagesPojo10.setImages(file_name);
        productImagesPojo10.setUserId(uid);
        productImagesPojo10.setCreateBy(uid);
        productImagesPojo10.setUpdateBy(uid);
        productImagesPojo10.setStatus(1);
        productImagesPojo10.setSorting(max + 10);
        productImagesService.addProductImages(productImagesPojo10);
      }

    }
    return SUCCESS;
  }

  /**
   * 商品图片集批量添加.
   * 
   * @return
   * @throws Throwable
   */
  public String productImgBatchAddWeb() throws Throwable {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      return null;
    }
    if (productImagesPojo != null && !"".equals(productImagesPojo.getProductId())) {
      synchronized (GeekWebAction.class) {
        long uid = sysLogin.getId();
        int maxCount = productImagesService.getCount(productImagesPojo);
        if (upfile != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
          // 图片压缩
          CompressPicture cp = new CompressPicture();
          String compressPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
                  + File.separator;
          cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 300, 300, true);
          productImagesPojo.setImages(file_name);
          productImagesPojo.setUserId(uid);
          productImagesPojo.setCreateBy(uid);
          productImagesPojo.setUpdateBy(uid);
          productImagesPojo.setStatus(1);
          productImagesPojo.setSorting(maxCount + 1);
          productImagesService.addProductImages(productImagesPojo);
        }
      }

    }
    return SUCCESS;
  }



  /**
   * 商品预览页面
   * 
   * @return
   * @throws Exception
   */
  public String goPreviewWeb() throws Exception {
    if (productPojo != null) {
      productList = productService.getProductAll(productPojo, page);
      if (productList.size() != 0) {
        productPojo = productList.get(0);
        productImagesPojo = new ProductImagesPojo();
        productImagesPojo.setProductId(productPojo.getId());
        productImagesList = productImagesService.getProductImagesAll(productImagesPojo, page);
        productFocusImagesList =
            productFocusImagesService.getProductFocusImagesByPid(productPojo.getId());
        productSkuLinkPojo = new ProductSkuLinkPojo();
        productSkuLinkPojo.setType(0);
        productSkuLinkPojo.setProductId(productPojo.getId());
        productSkuLinkPojo.setStatus(1);
        productSkuLinkPojos = productSkuLinkService.getProductSkuLinkAll(productSkuLinkPojo, page);
        List<ProductSkuLinkPojo> skuColorList =
            productSkuLinkService.findSkuColorByProductId(productSkuLinkPojo);
        List<ProductSkuLinkPojo> skuFormatList =
            productSkuLinkService.findSkuFormatByProductId(productSkuLinkPojo);
        userBrandPojo = userBrandService.findUserBrandById(productPojo.getUserBrandId());
        List<ProductSkuLinkPojo> pslList = productSkuLinkPojos;
        Map<Long, SkuAttributePojo> colorMap = new LinkedHashMap<Long, SkuAttributePojo>();
        Map<Long, SkuAttributePojo> fromatMap = new LinkedHashMap<Long, SkuAttributePojo>();
        for (int i = 0; i < pslList.size(); i++) {
          SkuAttributePojo colors =
              skuAttributeService.getSkuAttributeById(pslList.get(i).getSkuColorId());
          SkuAttributePojo formats =
              skuAttributeService.getSkuAttributeById(pslList.get(i).getSkuFormatId());
          pslList.get(i).setColorAttr(colors);
          pslList.get(i).setFormatAttr(formats);
          colorMap.put(pslList.get(i).getSkuColorId(), colors);
          fromatMap.put(pslList.get(i).getSkuFormatId(), formats);
        }
        JSONArray json = JSONArray.fromObject(pslList);
        String pslListJson = json.toString();

        Map skuStockMap = new LinkedHashMap<>();
        Map skuPriceMap = new LinkedHashMap<>();
        Map skuIdMap = new LinkedHashMap<>();

        for (ProductSkuLinkPojo temp : pslList) {
          skuStockMap.put(temp.getSkuColorId() + "_" + temp.getSkuFormatId(), temp.getStock());
          skuPriceMap.put(temp.getSkuColorId() + "_" + temp.getSkuFormatId(), temp.getPrice());
          skuIdMap.put(temp.getSkuColorId() + "_" + temp.getSkuFormatId(), temp.getId());
        }
        JSONArray skuStockJson = JSONArray.fromObject(skuStockMap);
        String skuStockStr = skuStockJson.toString();

        JSONArray skuPriceJson = JSONArray.fromObject(skuPriceMap);
        String skuPriceStr = skuPriceJson.toString();

        JSONArray skuIdJson = JSONArray.fromObject(skuIdMap);
        String skuIdStr = skuIdJson.toString();

        Comparator<SkuAttributePojo> comparator = new Comparator<SkuAttributePojo>() {
          @Override
          public int compare(SkuAttributePojo s1, SkuAttributePojo s2) {
            return s2.getSorting() - s1.getSorting();
          }
        };

        ActionContext ac = ActionContext.getContext();
        ac.put("skuStockStr", skuStockStr);
        ac.put("skuPriceStr", skuPriceStr);
        ac.put("skuIdStr", skuIdStr);
        ac.put("productImagesList", productImagesList);
        ac.put("productFocusImagesList", productFocusImagesList);
        ac.put("pslListJson", pslListJson);
        ac.put("pslList", pslList);
        List<SkuAttributePojo> skuColors = new ArrayList<SkuAttributePojo>(colorMap.values());
        List<SkuAttributePojo> skuFormats = new ArrayList<SkuAttributePojo>(fromatMap.values());
        Collections.sort(skuColors, comparator);
        Collections.sort(skuFormats, comparator);
        ac.put("skuColors", skuColors);
        ac.put("skuFormats", skuFormats);
        ac.put("skuColorList", skuColorList);
        ac.put("skuFormatList", skuFormatList);
      }
    }
    return SUCCESS;
  }



  public List<UserMakerBrandPojo> getUserMakerBrandPojos() {
    return userMakerBrandPojos;
  }

  public void setUserMakerBrandPojos(List<UserMakerBrandPojo> userMakerBrandPojos) {
    this.userMakerBrandPojos = userMakerBrandPojos;
  }

  public UserBrandPojo getUserBrandPojo() {
    return userBrandPojo;
  }

  public void setUserBrandPojo(UserBrandPojo userBrandPojo) {
    this.userBrandPojo = userBrandPojo;
  }

  public String getJsonStr() {
    return jsonStr;
  }

  public void setJsonStr(String jsonStr) {
    this.jsonStr = jsonStr;
  }

  public String[] getUpfileSkus() {
    return upfileSkus;
  }

  public void setUpfileSkus(String[] upfileSkus) {
    this.upfileSkus = upfileSkus;
  }

  public String getUpfileSku1() {
    return upfileSku1;
  }

  public void setUpfileSku1(String upfileSku1) {
    this.upfileSku1 = upfileSku1;
  }

  public String[] getStatus() {
    return status;
  }

  public void setStatus(String[] status) {
    this.status = status;
  }

  public String getSkuImagesUrl() {
    return skuImagesUrl;
  }

  public void setSkuImagesUrl(String skuImagesUrl) {
    this.skuImagesUrl = skuImagesUrl;
  }

  public HelpTypePojo getHelpTypePojo() {
    return helpTypePojo;
  }

  public void setHelpTypePojo(HelpTypePojo helpTypePojo) {
    this.helpTypePojo = helpTypePojo;
  }

  public List<HelpTypePojo> getHelpTypePojos() {
    return helpTypePojos;
  }

  public void setHelpTypePojos(List<HelpTypePojo> helpTypePojos) {
    this.helpTypePojos = helpTypePojos;
  }

  public HelpPojo getHelpPojo() {
    return helpPojo;
  }

  public void setHelpPojo(HelpPojo helpPojo) {
    this.helpPojo = helpPojo;
  }

  public List<HelpPojo> getHelpPojos() {
    return helpPojos;
  }

  public void setHelpPojos(List<HelpPojo> helpPojos) {
    this.helpPojos = helpPojos;
  }

  public Double getDeductScoreAll() {
    return deductScoreAll;
  }

  public void setDeductScoreAll(Double deductScoreAll) {
    this.deductScoreAll = deductScoreAll;
  }

  public List<ProductSkuLinkPojo> getProductSkuLinkPojos() {
    return productSkuLinkPojos;
  }

  public void setProductSkuLinkPojos(List<ProductSkuLinkPojo> productSkuLinkPojos) {
    this.productSkuLinkPojos = productSkuLinkPojos;
  }

  public String getIs() {
    return is;
  }

  public void setIs(String is) {
    this.is = is;
  }



  /**
   * 笔记列表前端显示
   * 
   * @return
   * @throws Exception
   */
  public String getUserCirclePostListWebGeek() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    SysLoginPojo logiPojo = UserUtil.getGeekUser();
    int count = 0;
    if (logiPojo != null) {
      map.put("userId", logiPojo.getId());
      map.put("isDelete", 0);
      count = userCirclePostService.userCirclePostCount(map);
    } else {
      FileUtil.alertMessageBySkip("请先登录", "tarentoLogin.do");
      return null;
    }
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(count);
    return SUCCESS;
  }

  /**
   * 笔记列表数据前端显示
   * 
   * @return
   * @throws SQLException
   */
  public String getUserCirclePostListDataGeek() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("isDelete", 0);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    SysLoginPojo logiPojo = UserUtil.getGeekUser();
    List<UserCirclePostPojo> userCirclePostPojoList = null;
    if (logiPojo != null) {
      map.put("userId", logiPojo.getId());
      userCirclePostPojoList = userCirclePostService.userCirclePostList(map);
    }
    JSONArray json = JSONArray.fromObject(userCirclePostPojoList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 跳转到新增笔记页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddUCPostGeek() throws Exception {
    // Map<String, Object> paramsMap = new HashMap<String, Object>();
    // 获取登录用户信息
    // SysLoginPojo logiPojo = UserUtil.getTarentoUser();
    // paramsMap.put("userId", logiPojo.getId());
    // paramsMap.put("type", 2);
    // paramsMap.put("isFollow", 1);
    // paramsMap.put("status", 1);

    // socialCirclePojoList = socialCircleService.findSocialCircleByTarentoId(paramsMap);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("status", 1);
    socialCirclePojoList = socialCircleService.findSocialCircleList(map);
    return SUCCESS;
  }

  /**
   * 新增笔记
   * 
   * @throws Exception
   * @return String
   */
  public String addUCPostGeek() throws Exception {
    try {
      // 获取登录用户信息
      SysLoginPojo logiPojo = UserUtil.getGeekUser();
      if (logiPojo == null) {
        return "gkloginweb";
        // FileUtil.alertMessageBySkip("请先登录", "tarentoLogin.do");
        // return null;
      }
      if (userCirclePostPojo != null) {
        // banner图操作
        if (banner != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
                  + File.separator;
          // FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", banner);
          // // 图片压缩
          // CompressPicture cp = new CompressPicture();
          // String compressPath =
          // ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost/small")
          // + File.separator;
          // cp.compressPic(banner, compressPath, "upfiles/userCirclePost/small/", file_name, 300,
          // 300, true);

          // 图片压缩
          CompressPicture cp = new CompressPicture();
          cp.compressPic(banner, uploadPath, uploadPath, file_name, 0, 0, false);
          userCirclePostPojo.setBanner(file_name);
          // } else {
          // // FileUtil.alertMessageBySkip("图片不能为空！", "goAddUCPost.do");
          // // return null;
        }
        // if (image != null) {
        // String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        // String uploadPath =
        // ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
        // + File.separator;
        // FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", image);
        // // 图片压缩
        // CompressPicture cp = new CompressPicture();
        // String compressPath =
        // ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost/small")
        // + File.separator;
        // cp.compressPic(image, compressPath, "upfiles/userCirclePost/small/", file_name, 300, 300,
        // true);
        // userCirclePostPojo.setImage(file_name);
        // } else {
        // userCirclePostPojo.setImage("");
        // }
        // if (userCirclePostPojo.getContent() == null || userCirclePostPojo.getContent() == "") {
        // // FileUtil.alertMessageBySkip("内容不能为空！", "goAddUserCirclePost.do");
        // // return null;
        // }
        // 设置新增时间
        // userCirclePostPojo.setCreateDate(new Date());
        // 设置用户id
        userCirclePostPojo.setUserId(logiPojo.getId());
        // 设置状态
        // userCirclePostPojo.setStatus(0);

        // 插入数据
        userCirclePostService.addUserCirclePost(userCirclePostPojo);
        // FileUtil.alertMessageBySkip("添加成功！", "getUserCirclePostListWeb.do");
        result = userCirclePostPojo.getId().toString();
      }
    } catch (Exception e) {
      e.printStackTrace();
      // FileUtil.alertMessageBySkip("添加失败！", "getUserCirclePostListWeb.do");
      result = null;
    }
    // return null;
    return SUCCESS;
  }

  /**
   * 跳转到编辑笔记页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateUCPostGeek() throws Exception {
    try {
      // 获取登录用户信息
      SysLoginPojo logiPojo = UserUtil.getGeekUser();
      if (logiPojo == null) {
        FileUtil.alertMessageBySkip("请先登录", "tarentoLogin.do");
        return null;
      }
      if (userCirclePostPojo != null) {
        userCirclePostPojo =
            userCirclePostService.getUserCirclePostById(userCirclePostPojo.getId());
        // Map<String, Object> paramsMap = new HashMap<String, Object>();
        // paramsMap.put("userId", logiPojo.getId());
        // paramsMap.put("type", 2);
        // paramsMap.put("isFollow", 1);
        // paramsMap.put("status", 1);

        // socialCirclePojoList = socialCircleService.findSocialCircleByTarentoId(paramsMap);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 1);
        socialCirclePojoList = socialCircleService.findSocialCircleList(map);
        if (userCirclePostPojo != null && userCirclePostPojo.getVersion() == 1) {
          // 查询自定义笔记详情页面数据
          Map<String, Object> params = new HashMap<String, Object>();
          if (userCirclePostPojo.getId() != null) {
            params.put("pageId", userCirclePostPojo.getId());
            params.put("type", 3);
            templatePageDataPojo = templatePageDataService.findByParams(params);
            if (templatePageDataPojo == null) {
              templatePageDataPojo = new TemplatePageDataPojo();
              templatePageDataPojo.setData("{}");
            }
          }
          return SUCCESS;
        } else {
          return "oldVersion";
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 编辑笔记
   * 
   * @throws Exception
   * @return String
   */
  public String updateUCPostGeek() throws Exception {
    try {
      // 获取登录用户信息
      SysLoginPojo logiPojo = UserUtil.getGeekUser();
      if (logiPojo == null) {
        return "gkloginweb";
        // FileUtil.alertMessageBySkip("请先登录", "tarentoLogin.do");
        // return null;
      }
      if (userCirclePostPojo != null) {
        // banner图操作
        if (banner != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", banner);
          // // 图片压缩
          // CompressPicture cp = new CompressPicture();
          // String compressPath =
          // ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost/small")
          // + File.separator;
          // cp.compressPic(banner, compressPath, "upfiles/userCirclePost/small/", file_name, 300,
          // 300, true);

          // 图片压缩
          CompressPicture cp = new CompressPicture();
          cp.compressPic(banner, uploadPath, uploadPath, file_name, 0, 0, false);
          userCirclePostPojo.setBanner(file_name);
        }
        // if (image != null) {
        // String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        // String uploadPath =
        // ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
        // + File.separator;
        // FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", image);
        // // 图片压缩
        // CompressPicture cp = new CompressPicture();
        // String compressPath =
        // ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost/small")
        // + File.separator;
        // cp.compressPic(image, compressPath, "upfiles/userCirclePost/small/", file_name, 300, 300,
        // true);
        // userCirclePostPojo.setImage(file_name);
        // } else {
        // userCirclePostPojo.setImage("");
        // }
        // if (userCirclePostPojo.getContent() == null || userCirclePostPojo.getContent() == "") {
        // // FileUtil.alertMessageBySkip("内容不能为空！", "goAddUserCirclePost.do");
        // // return null;
        // }
        // 设置新增时间
        // userCirclePostPojo.setCreateDate(new Date());
        // 设置用户id
        // userCirclePostPojo.setUserId(logiPojo.getId());
        // 设置状态
        userCirclePostPojo.setStatus(0);

        // 插入数据
        userCirclePostService.updateUserCirclePost(userCirclePostPojo);
        // FileUtil.alertMessageBySkip("修改成功！", "getUserCirclePostListWeb.do");
        result = userCirclePostPojo.getId().toString();
      }
    } catch (Exception e) {
      e.printStackTrace();
      // FileUtil.alertMessageBySkip("修改失败！", "getUserCirclePostListWeb.do");
      result = null;
    }
    return SUCCESS;
  }

  public Integer getProductCount() {
    return productCount;
  }

  public void setProductCount(Integer productCount) {
    this.productCount = productCount;
  }

}
