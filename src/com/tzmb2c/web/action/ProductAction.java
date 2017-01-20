package com.tzmb2c.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.opensymphony.xwork2.ActionContext;
import com.tencent.common.Util;
import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.CompressPicture;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.MatrixToImageWriter;
import com.tzmb2c.utils.RandomUtils;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.utils.export.excel.ExcelProcessor;
import com.tzmb2c.web.mapper.GrouponActivityMapper;
import com.tzmb2c.web.pojo.EleorderPojo;
import com.tzmb2c.web.pojo.GrouponActivityPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;
import com.tzmb2c.web.pojo.LadderPricePojo;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.PagePushPojo;
import com.tzmb2c.web.pojo.ProductCommentPojo;
import com.tzmb2c.web.pojo.ProductExcelPojo;
import com.tzmb2c.web.pojo.ProductFocusImagesPojo;
import com.tzmb2c.web.pojo.ProductImagesPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductRecommendPojo;
import com.tzmb2c.web.pojo.ProductSellPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.ProductStockPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SkuAttributePojo;
import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserCollectPojo;
import com.tzmb2c.web.pojo.YourFavouritesPojo;
import com.tzmb2c.web.service.EleorderService;
import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.GrouponActivityService;
import com.tzmb2c.web.service.GrouponUserRecordService;
import com.tzmb2c.web.service.HistoryService;
import com.tzmb2c.web.service.IpService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.PagePushService;
import com.tzmb2c.web.service.ProductCommentService;
import com.tzmb2c.web.service.ProductFocusImagesService;
import com.tzmb2c.web.service.ProductImagesService;
import com.tzmb2c.web.service.ProductSellService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.ProductStockService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.SearchKeyService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SkuAttributeService;
import com.tzmb2c.web.service.SpecialShowService;
import com.tzmb2c.web.service.SyntheticalDictService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserBrandService;
import com.tzmb2c.web.service.UserCollectService;
import com.tzmb2c.web.service.UserOrderDetailService;

/**
 * @author EricChen
 */
public class ProductAction extends SuperAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private ProductFocusImagesService productFocusImagesService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private SysAreaService sysAreaService;
  @Autowired
  private ProductTypeService productTypeService;
  @Autowired
  private ProductImagesService productImagesService;
  @Autowired
  private UserCollectService UserCollectService;
  @Autowired
  private ProductStockService productStockService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SyntheticalDictService syntheticalDictService;
  @Autowired
  private ProductCommentService productCommentService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private PagePushService pagePushService;
  @Autowired
  private HistoryService historyService;
  @Autowired
  private EleorderService eleorderService;
  @Autowired
  private UserOrderDetailService userOrderDetailService;
  @Autowired
  private IpService ipService;
  @Autowired
  private SearchKeyService searchKeyService;
  @Autowired
  private SpecialShowService specialShowService;
  @Autowired
  private UserBrandService userBrandService;
  @Autowired
  private GrouponActivityService grouponActivityService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private GrouponActivityRecordService grouponActivityRecordService;
  @Autowired
  private GrouponUserRecordService grouponUserRecordService;
  @Autowired
  private GrouponActivityMapper grouponActivityMapper;
  @Autowired
  private ProductSellService productSellService;
  @Autowired
  private SkuAttributeService skuAttributeService;
  private List<SysAreaPojo> sysAreas;
  private SysAreaPojo sysAreaPojo;
  private List<ShopPojo> shopPojos;
  private SysLoginPojo sysLogin;
  private String msg;
  private List<ProductPojo> productList, shopProductList, hotProductList, hotAllProductList,
      hotProductListSupplyWeb;
  private List<ProductImagesPojo> productImagesList;
  private ProductPojo productPojo;
  private ShopPojo shopPojo;
  private OrderPojo orderPojo;
  private ProductStockPojo productStockPojo;
  private UserCollectPojo userCollectPojo;
  private ProductImagesPojo productImagesPojo;
  private GrouponUserRecordPojo grouponUserRecordPojo;
  private List<OrderPojo> webSupplyOrderList, webConsumerOrderList;
  private String result;
  private String[] tids;
  private File upfile, upfile1, upfile2, upfiles1;
  private File[] upfiles, upfiles2;
  private String[] min;
  private String[] max;
  private String[] prices;
  private String pPrices;
  private String typeStr;
  private String typeIdsStr;
  private String typeIdStr;
  private String productName;
  private String productStatus;
  private Long oldId;
  private String productNo;
  private String orderNo;// 订单号
  private String consignee;// 收货人
  private int orderStatus;// 订单状态
  private List<ProductCommentPojo> pclist;
  private ProductCommentPojo productCommentPojo;
  private OrderDetailPojo orderDetailPojo;
  private List<OrderDetailPojo> odlist;
  private List<EleorderPojo> elelist;
  private List<ProductPojo> productPojoList;
  private Pager page2;
  private Integer adtype;
  private List<PagePushPojo> pagePushPojos;
  private List<ProductTypePojo> productTypePojos;
  private Long ageType1 = 0L;
  private Long ageType2 = 0L;
  private Long ageType3 = 0L;
  private List productTypeList = new ArrayList();
  private Long timeId;// 限时秒杀活动时间
  private Integer isProType;// 是否分类时显示
  private String text;// 品牌查询
  private String jsonStr;
  private ProductTypePojo productTypePojo;
  private Long titleId;
  private YourFavouritesPojo yourFavouritesPojo;
  private ProductRecommendPojo productRecommendPojo;
  private Long id;
  private String upfileFileName;
  private String upfile1FileName;
  private String upfile2FileName;
  private String upfiles1FileName;
  private String[] groupNums;
  private String[] groupPrices;
  private String[] groupStatuss;
  private String[] skuTypes;
  private String[] skuColors;
  private String[] skuStocks;
  private String[] skuStatuss;
  private String[] businessCodes;
  private File[] skuFiles, skuFiles2;
  private ProductFocusImagesPojo productFocusImages;
  private String[] skuPrices;
  private long[] upfilesId;
  private List<ProductFocusImagesPojo> productFocusImagesList;
  private List<GrouponActivityPojo> grouponActivityPojos;
  private List<ProductSkuLinkPojo> productSkuLinkPojos;
  private long[] groupIds;
  private long[] skuIds;
  private GrouponActivityPojo grouponActivityPojo;
  private String[] stockArray;
  private String stockSkuId;
  private String stock;
  private Long activityId;
  private String keywords;
  private Integer status;
  private Integer source;
  private Long gaActivityId;
  private Integer activityType;
  private Integer[] isSkuFiles;
  private Integer[] isUpFiles;
  private String doMain;
  private Date beginTime;
  private Date endTime;
  private String skuListStr;
  private String attrListStr;
  private String delskuStr;
  private Integer norm;
  private Integer sxType;
  private Integer gsType;
  private File skuImage;



  public File getSkuImage() {
    return skuImage;
  }

  public void setSkuImage(File skuImage) {
    this.skuImage = skuImage;
  }

  public Integer getSxType() {
    return sxType;
  }

  public void setSxType(Integer sxType) {
    this.sxType = sxType;
  }

  public Integer getGsType() {
    return gsType;
  }

  public void setGsType(Integer gsType) {
    this.gsType = gsType;
  }

  public String getAttrListStr() {
    return attrListStr;
  }

  public void setAttrListStr(String attrListStr) {
    this.attrListStr = attrListStr;
  }

  public Integer getNorm() {
    return norm;
  }

  public void setNorm(Integer norm) {
    this.norm = norm;
  }

  public String getSkuListStr() {
    return skuListStr;
  }

  public void setSkuListStr(String skuListStr) {
    this.skuListStr = skuListStr;
  }

  public String getDelskuStr() {
    return delskuStr;
  }

  public void setDelskuStr(String delskuStr) {
    this.delskuStr = delskuStr;
  }

  public Date getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(Date beginTime) {
    this.beginTime = beginTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public String getDoMain() {
    return doMain;
  }

  public void setDoMain(String doMain) {
    this.doMain = doMain;
  }

  public Integer[] getIsUpFiles() {
    return isUpFiles;
  }

  public void setIsUpFiles(Integer[] isUpFiles) {
    this.isUpFiles = isUpFiles;
  }

  public Integer[] getIsSkuFiles() {
    return isSkuFiles;
  }

  public void setIsSkuFiles(Integer[] isSkuFiles) {
    this.isSkuFiles = isSkuFiles;
  }

  public String[] getBusinessCodes() {
    return businessCodes;
  }

  public void setBusinessCodes(String[] businessCodes) {
    this.businessCodes = businessCodes;
  }

  public Long getGaActivityId() {
    return gaActivityId;
  }

  public void setGaActivityId(Long gaActivityId) {
    this.gaActivityId = gaActivityId;
  }

  public Integer getSource() {
    return source;
  }

  public void setSource(Integer source) {
    this.source = source;
  }

  public Integer getActivityType() {
    return activityType;
  }

  public void setActivityType(Integer activityType) {
    this.activityType = activityType;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public String[] getStockArray() {
    return stockArray;
  }

  public void setStockArray(String[] stockArray) {
    this.stockArray = stockArray;
  }

  public String getStockSkuId() {
    return stockSkuId;
  }

  public void setStockSkuId(String stockSkuId) {
    this.stockSkuId = stockSkuId;
  }

  public String getStock() {
    return stock;
  }

  public void setStock(String stock) {
    this.stock = stock;
  }

  public GrouponActivityPojo getGrouponActivityPojo() {
    return grouponActivityPojo;
  }

  public void setGrouponActivityPojo(GrouponActivityPojo grouponActivityPojo) {
    this.grouponActivityPojo = grouponActivityPojo;
  }

  public long[] getGroupIds() {
    return groupIds;
  }

  public void setGroupIds(long[] groupIds) {
    this.groupIds = groupIds;
  }

  public long[] getSkuIds() {
    return skuIds;
  }

  public void setSkuIds(long[] skuIds) {
    this.skuIds = skuIds;
  }

  public List<ProductSkuLinkPojo> getProductSkuLinkPojos() {
    return productSkuLinkPojos;
  }

  public void setProductSkuLinkPojos(List<ProductSkuLinkPojo> productSkuLinkPojos) {
    this.productSkuLinkPojos = productSkuLinkPojos;
  }

  public List<GrouponActivityPojo> getGrouponActivityPojos() {
    return grouponActivityPojos;
  }

  public void setGrouponActivityPojos(List<GrouponActivityPojo> grouponActivityPojos) {
    this.grouponActivityPojos = grouponActivityPojos;
  }

  public GrouponUserRecordPojo getGrouponUserRecordPojo() {
    return grouponUserRecordPojo;
  }

  public void setGrouponUserRecordPojo(GrouponUserRecordPojo grouponUserRecordPojo) {
    this.grouponUserRecordPojo = grouponUserRecordPojo;
  }

  public List<ProductFocusImagesPojo> getProductFocusImagesList() {
    return productFocusImagesList;
  }

  public void setProductFocusImagesList(List<ProductFocusImagesPojo> productFocusImagesList) {
    this.productFocusImagesList = productFocusImagesList;
  }

  public long[] getUpfilesId() {
    return upfilesId;
  }

  public void setUpfilesId(long[] upfilesId) {
    this.upfilesId = upfilesId;
  }

  public String[] getSkuPrices() {
    return skuPrices;
  }

  public void setSkuPrices(String[] skuPrices) {
    this.skuPrices = skuPrices;
  }

  public List<ProductPojo> getProductPojoList() {
    return productPojoList;
  }

  public void setProductPojoList(List<ProductPojo> productPojoList) {
    this.productPojoList = productPojoList;
  }

  public ProductFocusImagesPojo getProductFocusImages() {
    return productFocusImages;
  }

  public void setProductFocusImages(ProductFocusImagesPojo productFocusImages) {
    this.productFocusImages = productFocusImages;
  }

  public File[] getSkuFiles() {
    return skuFiles;
  }

  public void setSkuFiles(File[] skuFiles) {
    this.skuFiles = skuFiles;
  }

  public File[] getSkuFiles2() {
    return skuFiles2;
  }

  public void setSkuFiles2(File[] skuFiles2) {
    this.skuFiles2 = skuFiles2;
  }

  public String[] getSkuStatuss() {
    return skuStatuss;
  }

  public void setSkuStatuss(String[] skuStatuss) {
    this.skuStatuss = skuStatuss;
  }

  public String[] getSkuStocks() {
    return skuStocks;
  }

  public void setSkuStocks(String[] skuStocks) {
    this.skuStocks = skuStocks;
  }

  public String[] getSkuColors() {
    return skuColors;
  }

  public void setSkuColors(String[] skuColors) {
    this.skuColors = skuColors;
  }

  public String[] getSkuTypes() {
    return skuTypes;
  }

  public void setSkuTypes(String[] skuTypes) {
    this.skuTypes = skuTypes;
  }

  public String[] getGroupStatuss() {
    return groupStatuss;
  }

  public void setGroupStatuss(String[] groupStatuss) {
    this.groupStatuss = groupStatuss;
  }

  public String[] getGroupNums() {
    return groupNums;
  }

  public void setGroupNums(String[] groupNums) {
    this.groupNums = groupNums;
  }

  public String[] getGroupPrices() {
    return groupPrices;
  }

  public void setGroupPrices(String[] groupPrices) {
    this.groupPrices = groupPrices;
  }

  public String getUpfiles1FileName() {
    return upfiles1FileName;
  }

  public void setUpfiles1FileName(String upfiles1FileName) {
    this.upfiles1FileName = upfiles1FileName;
  }

  public String getUpfile2FileName() {
    return upfile2FileName;
  }

  public void setUpfile2FileName(String upfile2FileName) {
    this.upfile2FileName = upfile2FileName;
  }

  public String getUpfile1FileName() {
    return upfile1FileName;
  }

  public void setUpfile1FileName(String upfile1FileName) {
    this.upfile1FileName = upfile1FileName;
  }

  public String getUpfileFileName() {
    return upfileFileName;
  }

  public void setUpfileFileName(String upfileFileName) {
    this.upfileFileName = upfileFileName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTitleId() {
    return titleId;
  }

  public void setTitleId(Long titleId) {
    this.titleId = titleId;
  }

  public ProductRecommendPojo getProductRecommendPojo() {
    return productRecommendPojo;
  }

  public void setProductRecommendPojo(ProductRecommendPojo productRecommendPojo) {
    this.productRecommendPojo = productRecommendPojo;
  }

  public YourFavouritesPojo getYourFavouritesPojo() {
    return yourFavouritesPojo;
  }

  public void setYourFavouritesPojo(YourFavouritesPojo yourFavouritesPojo) {
    this.yourFavouritesPojo = yourFavouritesPojo;
  }

  public ProductTypePojo getProductTypePojo() {
    return productTypePojo;
  }

  public void setProductTypePojo(ProductTypePojo productTypePojo) {
    this.productTypePojo = productTypePojo;
  }

  public String getJsonStr() {
    return jsonStr;
  }

  public void setJsonStr(String jsonStr) {
    this.jsonStr = jsonStr;
  }

  public Integer getIsProType() {
    return isProType;
  }

  public void setIsProType(Integer isProType) {
    this.isProType = isProType;
  }

  public Long getOldId() {
    return oldId;
  }

  public void setOldId(Long oldId) {
    this.oldId = oldId;
  }

  public String getProductStatus() {
    return productStatus;
  }

  public void setProductStatus(String productStatus) {
    this.productStatus = productStatus;
  }

  public List<ProductTypePojo> getProductTypePojos() {
    return productTypePojos;
  }

  public void setProductTypePojos(List<ProductTypePojo> productTypePojos) {
    this.productTypePojos = productTypePojos;
  }

  public List<OrderPojo> getWebConsumerOrderList() {
    return webConsumerOrderList;
  }

  public void setWebConsumerOrderList(List<OrderPojo> webConsumerOrderList) {
    this.webConsumerOrderList = webConsumerOrderList;
  }

  public Integer getAdtype() {
    return adtype;
  }

  public void setAdtype(Integer adtype) {
    this.adtype = adtype;
  }

  public Pager getPage2() {
    return page2;
  }

  public void setPage2(Pager page2) {
    this.page2 = page2;
  }

  public OrderDetailPojo getOrderDetailPojo() {
    return orderDetailPojo;
  }

  public void setOrderDetailPojo(OrderDetailPojo orderDetailPojo) {
    this.orderDetailPojo = orderDetailPojo;
  }

  public List<OrderDetailPojo> getOdlist() {
    return odlist;
  }

  public void setOdlist(List<OrderDetailPojo> odlist) {
    this.odlist = odlist;
  }

  public List<ProductCommentPojo> getPclist() {
    return pclist;
  }

  public void setPclist(List<ProductCommentPojo> pclist) {
    this.pclist = pclist;
  }

  public SysLoginPojo getSysLogin() {
    return sysLogin;
  }

  public void setSysLogin(SysLoginPojo sysLogin) {
    this.sysLogin = sysLogin;
  }

  public OrderPojo getOrderPojo() {
    return orderPojo;
  }

  public void setOrderPojo(OrderPojo orderPojo) {
    this.orderPojo = orderPojo;
  }

  public List<ShopPojo> getShopPojos() {
    return shopPojos;
  }

  public void setShopPojos(List<ShopPojo> shopPojos) {
    this.shopPojos = shopPojos;
  }

  public List<ProductPojo> getHotProductListSupplyWeb() {
    return hotProductListSupplyWeb;
  }

  public void setHotProductListSupplyWeb(List<ProductPojo> hotProductListSupplyWeb) {
    this.hotProductListSupplyWeb = hotProductListSupplyWeb;
  }

  public List<OrderPojo> getWebSupplyOrderList() {
    return webSupplyOrderList;
  }

  public void setWebSupplyOrderList(List<OrderPojo> webSupplyOrderList) {
    this.webSupplyOrderList = webSupplyOrderList;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public String getConsignee() {
    return consignee;
  }

  public void setConsignee(String consignee) {
    this.consignee = consignee;
  }

  public int getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(int orderStatus) {
    this.orderStatus = orderStatus;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public List<ProductPojo> getProductList() {
    return productList;
  }

  public void setProductList(List<ProductPojo> productList) {
    this.productList = productList;
  }

  public ProductPojo getProductPojo() {
    return productPojo;
  }

  public void setProductPojo(ProductPojo productPojo) {
    this.productPojo = productPojo;
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

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
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

  public File getUpfiles1() {
    return upfiles1;
  }

  public void setUpfiles1(File upfiles1) {
    this.upfiles1 = upfiles1;
  }

  public File[] getUpfiles() {
    return upfiles;
  }

  public void setUpfiles(File[] upfiles) {
    this.upfiles = upfiles;
  }

  public File[] getUpfiles2() {
    return upfiles2;
  }

  public void setUpfiles2(File[] upfiles2) {
    this.upfiles2 = upfiles2;
  }

  public String[] getMin() {
    return min;
  }

  public void setMin(String[] min) {
    this.min = min;
  }

  public String[] getMax() {
    return max;
  }

  public void setMax(String[] max) {
    this.max = max;
  }

  public String[] getPrices() {
    return prices;
  }

  public void setPrices(String[] prices) {
    this.prices = prices;
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

  public String getTypeStr() {
    return typeStr;
  }

  public void setTypeStr(String typeStr) {
    this.typeStr = typeStr;
  }

  public List<ProductImagesPojo> getProductImagesList() {
    return productImagesList;
  }

  public void setProductImagesList(List<ProductImagesPojo> productImagesList) {
    this.productImagesList = productImagesList;
  }

  public ProductImagesPojo getProductImagesPojo() {
    return productImagesPojo;
  }

  public void setProductImagesPojo(ProductImagesPojo productImagesPojo) {
    this.productImagesPojo = productImagesPojo;
  }

  public List<ProductPojo> getShopProductList() {
    return shopProductList;
  }

  public void setShopProductList(List<ProductPojo> shopProductList) {
    this.shopProductList = shopProductList;
  }

  public List<ProductPojo> getHotProductList() {
    return hotProductList;
  }

  public void setHotProductList(List<ProductPojo> hotProductList) {
    this.hotProductList = hotProductList;
  }

  public ShopPojo getShopPojo() {
    return shopPojo;
  }

  public void setShopPojo(ShopPojo shopPojo) {
    this.shopPojo = shopPojo;
  }

  public UserCollectPojo getUserCollectPojo() {
    return userCollectPojo;
  }

  public String getPPrices() {
    return pPrices;
  }

  public void setPPrices(String pPrices) {
    this.pPrices = pPrices;
  }

  public List<SysAreaPojo> getSysAreas() {
    return sysAreas;
  }

  public void setSysAreas(List<SysAreaPojo> sysAreas) {
    this.sysAreas = sysAreas;
  }

  public List<ProductPojo> getHotAllProductList() {
    return hotAllProductList;
  }

  public void setHotAllProductList(List<ProductPojo> hotAllProductList) {
    this.hotAllProductList = hotAllProductList;
  }

  public SysAreaPojo getSysAreaPojo() {
    return sysAreaPojo;
  }

  public void setSysAreaPojo(SysAreaPojo sysAreaPojo) {
    this.sysAreaPojo = sysAreaPojo;
  }

  public Long getTimeId() {
    return timeId;
  }

  public void setTimeId(Long timeId) {
    this.timeId = timeId;
  }

  public String searchWeb() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    page.setRowCount(productService.getAllCount(productPojo));
    productList = productService.getProductAll(productPojo, page);
    ActionContext ac = ActionContext.getContext();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("type", "brand");
    ac.put("brand", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "texture");
    ac.put("texture", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("type", "age");
    ac.put("age", syntheticalDictService.getSyntheticalDictListByType(map));
    ac.put("productList", productList);
    ac.put("productPojo", productPojo);
    return SUCCESS;

  }

  /**
   * 前台商品搜索方法
   * 
   * @return
   * @throws Exception
   */
  public String searchWebByName() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }

    page.setPageSize(10);
    page.setRowCount(productService.getCountStatus(productPojo));
    if (productPojo.getProductName() == "搜索 商品") {
      productPojo.setProductName(null);
    }
    productList = productService.getProductAllByName(productPojo, page);

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("type", 1);
    map.put("keyword", productPojo.getProductName());

    if (searchKeyService.selectKeyword(map).isEmpty() != true) {
      map.put("hits", productPojo.getHits());
      searchKeyService.updateKeyword(map);
    } else {
      map.put("hits", 1);
      searchKeyService.insertKeyword(map);
    }
    // 销售量加上电商的订单
    // for(ProductPojo p:productList){
    // int s=eleorderService.sellSumNum(p.getId());
    // p.setSellNumber(p.getSellNumber()+s);
    // }
    ActionContext ac = ActionContext.getContext();
    ac.put("brand", syntheticalDictService.getSyntheticalDictListByTypeStatus("brand"));
    ac.put("texture", syntheticalDictService.getSyntheticalDictListByTypeStatus("texture"));
    ac.put("age", syntheticalDictService.getSyntheticalDictListByTypeStatus("age"));
    ac.put("productList", productList);
    ac.put("productPojo", productPojo);
    return SUCCESS;

  }

  /**
   * 前台货号搜索方法
   * 
   * @return
   * @throws Exception
   */
  public String searchProductNo() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }

    page.setPageSize(10);
    page.setRowCount(productService.getCountStatus(productPojo));
    if (productPojo.getProductNum() == "搜索 货号") {
      productPojo.setProductNum(null);
    }
    productList = productService.getProductAllByName(productPojo, page);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("type", 3);
    map.put("keyword", productPojo.getProductNum());
    map.put("hits", productPojo.getHits());
    if (searchKeyService.selectKeyword(map).isEmpty() != true) {
      searchKeyService.updateKeyword(map);
    } else {
      map.put("hits", 1);
      searchKeyService.insertKeyword(map);
    }
    // 销售量加上电商的订单
    // for(ProductPojo p:productList){
    // int s=eleorderService.sellSumNum(p.getId());
    // p.setSellNumber(p.getSellNumber()+s);
    // }
    ActionContext ac = ActionContext.getContext();
    ac.put("brand", syntheticalDictService.getSyntheticalDictListByTypeStatus("brand"));
    ac.put("texture", syntheticalDictService.getSyntheticalDictListByTypeStatus("texture"));
    ac.put("age", syntheticalDictService.getSyntheticalDictListByTypeStatus("age"));
    ac.put("productList", productList);
    ac.put("productPojo", productPojo);
    return SUCCESS;

  }

  public String productListWeb() throws Exception {
    page.setPageSize(20);
    JSONArray json =
        JSONArray.fromObject(productService.getProductAllByParameter(productPojo, page));
    page.setResult(json.toString());

    return SUCCESS;

  }

  public String getCountAllProduct() throws Exception {
    List<ProductPojo> hotAllProductList;
    hotAllProductList = productService.productForHotSupplyWeb(productPojo);
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(20);
    page.setRowCount(productService.getCountStatus(productPojo));
    productList = productService.getProductAllStatus(productPojo, page);
    ProductTypePojo fatherType = new ProductTypePojo();
    fatherType.setPid(0l);

    ActionContext ac = ActionContext.getContext();
    ac.put("hotAllProductList", hotAllProductList);
    ac.put("productList", productList);
    ac.put("productPojo", productPojo);
    ac.put("brand", syntheticalDictService.getSyntheticalDictListByTypeStatus("brand"));
    ac.put("texture", syntheticalDictService.getSyntheticalDictListByTypeStatus("texture"));
    ac.put("age", syntheticalDictService.getSyntheticalDictListByTypeStatus("age"));
    ac.put("productTypeIds", productTypeService.getProductTypeByPid(fatherType));

    // 广告图//
    pagePushPojos = pagePushService.findAdByType(7);
    if (pagePushPojos != null && pagePushPojos.size() > 0) {
      ac.put("adimages", pagePushPojos.get(0).getImages());
      ac.put("backgroundColor", pagePushPojos.get(0).getBackgroundColor());
    }
    return SUCCESS;
  }

  public ProductStockPojo getProductStockPojo() {
    return productStockPojo;
  }

  public void setProductStockPojo(ProductStockPojo productStockPojo) {
    this.productStockPojo = productStockPojo;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductNo() {
    return productNo;
  }

  public void setProductNo(String productNo) {
    this.productNo = productNo;
  }

  public String getCountProduct() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    if (productPojo != null) {
      if (productPojo.getTongji() == null) {
        productPojo.setStatus(1);
        page.setRowCount(productService.getCount(productPojo, page));
      } else {
        page.setRowCount(productService.getCount(productPojo, page));
      }
    } else {
      ProductPojo productPojo = new ProductPojo();
      productPojo.setStatus(1);
      page.setRowCount(productService.getCount(productPojo, page));

    }
    productTypePojos = productTypeService.getProductTypeSecondLevel();
    ProductTypePojo productTypePojo = new ProductTypePojo();
    productTypePojo.setPid(-1l);
    List<ProductTypePojo> productType1List =
        productTypeService.getProductTypeByPids(productTypePojo);
    ActionContext ac = ActionContext.getContext();
    ac.put("timeId", timeId);
    ac.put("productTypePojos", productTypePojos);
    ac.put("isNew", sysDictService.getSysDictListByType("yes_no"));
    ac.put("titleId", titleId);
    ac.put("productType1List", productType1List);
    return SUCCESS;
  }

  /**
   * 
   * 首页推荐产品
   * */
  public String getIndexProduct() throws Exception {
    ActionContext ac = ActionContext.getContext();
    // 查询首页新品推荐5条数据
    productList = productService.indexProductTopFive();
    ac.put("productNewList", productList);
    ac.put("productTypeList1", productService.indexShowByFloor("1"));
    ac.put("productTypeList2", productService.indexShowByFloor("2"));
    ac.put("productTypeList3", productService.indexShowByFloor("3"));
    ac.put("productTypeList4", productService.indexShowByFloor("4"));
    ac.put("productTypeList5", productService.indexShowByFloor("5"));
    ac.put("productTypeList6", productService.indexShowByFloor("6"));
    ac.put("productTypeList7", productList);
    return SUCCESS;
  }


  public String getProductAll() throws Exception {
    if (productPojo.getTongji() == null) {
      productPojo.setStatus(1);
    }
    productList = productService.getProductAll(productPojo, page);
    for (ProductPojo productPojo : productList) {
      productPojo.setImageCount(productService.imagesCount(productPojo.getId()));
      productPojo.setFocusImageCount(productService.focusImageCount(productPojo.getId()));
    }
    JSONArray json = JSONArray.fromObject(productList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String getProductAllNoPage() throws Exception {
    productList = productService.getProductAll(productPojo, page);
    return SUCCESS;

  }

  public String goproductAdd() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    if (text != null && text != "") {
      map.put("type", "brand");
      map.put("text", text);
      JSONArray json =
          JSONArray.fromObject(syntheticalDictService.getSyntheticalDictListByType(map));
      jsonStr = json.toString();
    } else {
      // getProductType(0l);
      // getProductTypeWithAge(0l);
      ActionContext ac = ActionContext.getContext();
      map.put("type", "unit");
      ac.put("unit", syntheticalDictService.getSyntheticalDictListByType(map));
      // map.put("type", "brand");
      // ac.put("brand", syntheticalDictService.getSyntheticalDictListByType(map));
      map.put("userId", productPojo.getUserId());
      ac.put("brandList", userBrandService.findUserBrandByUserId(map));
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
      // ac.put("MFlist", sysLoginService.sysLoginMFService());
      // ac.put("productTypes", productTypeList);
      ProductTypePojo productTypePojo = new ProductTypePojo();
      productTypePojo.setPid(-1L);
      ac.put("productType1List", productTypeService.getProductTypeByPids(productTypePojo));
      List<SysDictPojo> skuTypeList = sysDictService.getSysDictListByType("sku_attr_type");
      ac.put("skuTypeList", skuTypeList);
      SysAreaPojo sysAreaPojo = new SysAreaPojo();
      sysAreaPojo.setPid(0L);
      List<SysAreaPojo> sysAreaPojos = sysAreaService.getSysAreaByPid(sysAreaPojo);
      ac.put("sysAreaPojos", sysAreaPojos);
    }
    return SUCCESS;
  }


  /**
   * 添加商品
   * 
   * @return
   * @throws Exception
   */
  public String productAdd() throws Exception {
    SysLoginPojo loginPojo = null;
    loginPojo = UserUtil.getUser();
    if (productPojo == null || productPojo.getUserId() == null || productPojo.getUserId() == 0) {
      if (loginPojo != null) {
        productPojo.setUserId(loginPojo.getId());
      }
    }
    // norm(1-规格1;2-规格2)
    productPojo.setNormType(norm);
    productPojo.setColorValue(sxType);
    productPojo.setFormatValue(gsType);
    // 查找字典对应的sku属性值
    SysDictPojo sysDict = new SysDictPojo();
    sysDict.setValue(String.valueOf(sxType));
    sysDict.setType("sku_attr_type");
    SysDictPojo sxType = sysDictService.getSysDictByTypeAndValue(sysDict);// 属性
    sysDict.setValue(String.valueOf(gsType));
    SysDictPojo gsType = sysDictService.getSysDictByTypeAndValue(sysDict);// 格式
    if (norm == 1) {
      if (sxType == null) {
        FileUtil.alertMessageBySkip("sku属性有误！", "productListManage.do");
        return null;
      }
    } else {
      if (sxType == null || gsType == null) {
        FileUtil.alertMessageBySkip("sku属性或格式有误！", "productListManage.do");
        return null;
      }
    }
    if (upfile != null) {
      String orgfileName = getUpfile1FileName();
      String filename =
          FileUtil.uploadFile(upfile, orgfileName, "upfiles/product", false, 300, 300, true, true,
              "");
      productPojo.setImageMain(filename);
    } else {
      productPojo.setImageMain("");
    }

    if (upfile1 != null) {
      String orgfileName = getUpfile1FileName();
      String filename =
          FileUtil.uploadFile(upfile1, orgfileName, "upfiles/product", false, 300, 300, true, true,
              "");
      productPojo.setImage(filename);
    } else {
      productPojo.setImage("");
    }

    if (upfile2 != null) {
      String orgfileName = getUpfile2FileName();
      String filename =
          FileUtil.uploadFile(upfile2, orgfileName, "upfiles/product", false, 300, 300, true, true,
              "");
      productPojo.setImageSmall(filename);
    } else {
      productPojo.setImageSmall("");
    }

    productPojo.setStatus(1);
    productPojo.setIsNew("0");
    productPojo.setIsHotsale("0");
    productPojo.setRecommend(0);
    if (productPojo.getBaseNumber() != null) {
      productPojo.setBaseNumber(productPojo.getBaseNumber());
    } else {
      productPojo.setBaseNumber(50);
    }
    StringBuffer sbf = new StringBuffer(RandomUtils.runVerifyCode(6));
    // sbf.append(new Date().getTime());
    //

    productPojo.setProductNo(sbf.toString());
    productPojo.setLadderPrice("[]");
    /*
     * ProductTypePojo type = new ProductTypePojo();
     * type.setId(Long.parseLong(productPojo.getProductTypeId())); type =
     * productTypeService.findProductType(type);
     * 
     * String typeIdStr = ":"+String.valueOf(type.getPid())+":"; String value = ""; ProductTypePojo
     * type2 = new ProductTypePojo(); type2.setPid(0L); List<ProductTypePojo> productTypes = null;
     * if ("1".equals(productPojo.getAgeType())) { type2.setName("0-3岁婴幼儿玩具"); productTypes =
     * productTypeService.getProductTypeByPidName(type2); } else if
     * ("2".equals(productPojo.getAgeType())) { type2.setName("3-6岁儿童玩具"); productTypes =
     * productTypeService.getProductTypeByPidName(type2);
     * 
     * } else if ("3".equals(productPojo.getAgeType())) { type2.setName("6岁以上玩具"); productTypes =
     * productTypeService.getProductTypeByPidName(type2); } if (productTypes!=null &&
     * productTypes.size()>0) { value = String.valueOf(productTypes.get(0).getId()); }
     * if(!"".equals(value)){ typeIdStr += value +":"; }
     * 
     * productPojo.setProductTypeIds(typeIdStr);
     */
    String typeIds = ":" + String.valueOf(productPojo.getProductTypeIds()) + ":";
    productPojo.setProductTypeIds(typeIds);
    BigDecimal factPrice = new BigDecimal(String.valueOf(productPojo.getDistributionPrice()));
    BigDecimal sellPrice = new BigDecimal(String.valueOf(productPojo.getSellingPrice()));
    BigDecimal discount =
        factPrice.divide(sellPrice, 3, RoundingMode.HALF_UP).multiply(new BigDecimal(10))
            .setScale(1, RoundingMode.HALF_UP);
    if (discount.doubleValue() < 10) {
      productPojo.setDiscount(discount.toPlainString());
    } else {
      productPojo.setDiscount("0.0");
    }

    // productPojo.setBaseNumber(0);
    // 商品偏远地区
    // try {
    // if (productPojo.getFaraway() != null && !"".equals(productPojo.getFaraway())) {
    // String[] farawayArr = productPojo.getFaraway().split(",");
    // List<String> faraway = new ArrayList<String>();
    // for (int i = 0; i < farawayArr.length; i++) {
    // faraway.add(farawayArr[i]);
    // }
    // JSONArray json = JSONArray.fromObject(faraway);
    // productPojo.setFaraway(json.toString());
    // }
    // } catch (Exception e1) {
    // Util.log("添加偏远地区出现异常");
    // e1.printStackTrace();
    // }
    productService.addProduct(productPojo);

    // String content = "F***";
    String content = ConstParam.WX_URL + "/groupon.php?id=" + productPojo.getId();
    String path =
        ServletActionContext.getServletContext().getRealPath("/upfiles/qrcode") + File.separator;
    File creapath = new File(path);
    if (!creapath.isDirectory()) {
      creapath.mkdirs();
    }
    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    Hashtable hints = new Hashtable();
    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
    BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    String img = df.format(new Date());
    File file1 = new File(path, img + ".jpg");
    MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);

    ProductPojo productPojoQc = new ProductPojo();
    productPojoQc.setQrcode(img + ".jpg");
    productPojoQc.setId(productPojo.getId());
    productService.productUpdate(productPojoQc);

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
    long uid = productPojo.getUserId();
    // if (upfile != null) {
    // String orgfileName = getUpfileFileName();
    // String filename =
    // FileUtil.uploadFile(upfile, orgfileName, "/upfiles/product", false, 300, 300, true, true,
    // "");
    // productImagesPojo = new ProductImagesPojo();
    // productImagesPojo.setProductId(productPojo.getId());
    // productImagesPojo.setImages(filename);
    // productImagesPojo.setUserId(uid);
    // productImagesPojo.setCreateBy(uid);
    // productImagesPojo.setUpdateBy(uid);
    // productImagesPojo.setStatus(1);
    // productImagesPojo.setSorting(1);
    // productImagesService.addProductImages(productImagesPojo);
    // }

    if (upfiles != null && upfiles.length > 0 && upfiles[0].length() > 0) {
      for (int i = 0; i < upfiles.length; i++) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";

        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfiles[i]);

        // 图片压缩
        String compressPath =
            ServletActionContext.getServletContext().getRealPath(
                "/upfiles/productFocusImages/small")
                + File.separator;

        CompressPicture cp = new CompressPicture();
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

    if (groupNums != null && groupPrices != null) {
      for (int i = 0; i < groupNums.length; i++) {
        GrouponActivityPojo grouponActivityPojo = new GrouponActivityPojo();
        grouponActivityPojo.setProductId(productPojo.getId());
        grouponActivityPojo.setNum(Integer.parseInt(groupNums[i]));
        grouponActivityPojo.setPrice(Double.parseDouble(groupPrices[i]));
        if (i == 0) {
          grouponActivityPojo.setIsDefault(1);
        } else {
          grouponActivityPojo.setIsDefault(0);
        }
        grouponActivityPojo.setStatus(Integer.parseInt(groupStatuss[i]));
        grouponActivityPojo.setSorting(i + 1);
        grouponActivityPojo.setActivityStatus(1);
        grouponActivityPojo.setType(1);
        // grouponActivityPojo.setCurrentTime("当前时间");
        grouponActivityService.add(grouponActivityPojo);
      }
    }

    // if (skuTypes != null && skuColors != null && skuStocks != null && businessCodes != null) {
    // for (int i = 0; i < skuTypes.length; i++) {
    // ProductSkuLinkPojo productSkuLinkPojo = new ProductSkuLinkPojo();
    // productSkuLinkPojo.setProductId(productPojo.getId());
    // productSkuLinkPojo.setSkuFormat(skuTypes[i]);
    // productSkuLinkPojo.setSkuColor(skuColors[i]);
    // if (skuFiles != null && skuFiles.length > 0 && skuFiles[i].length() > 0) {
    // String file_name = StringUtil.getCurrentDateStr() + ".jpg";
    // String uploadPath =
    // ServletActionContext.getServletContext().getRealPath("/upfiles/productSkuLink")
    // + File.separator;
    // FileUtil.uploadFile(file_name, uploadPath, "upfiles/productSkuLink/", skuFiles[i]);
    // productSkuLinkPojo.setImage(file_name);
    // } else {
    // productSkuLinkPojo.setImage("");
    // }
    // productSkuLinkPojo.setStock(Integer.parseInt(skuStocks[i]));
    // productSkuLinkPojo.setStockNum(Integer.parseInt(skuStocks[i]));
    // productSkuLinkPojo.setBusinessCode(businessCodes[i]);
    // productSkuLinkPojo.setSorting(i + 1);
    // productSkuLinkPojo.setStatus(Integer.parseInt(skuStatuss[i]));
    // // productSkuLinkPojo.setStatus(1);
    // // productSkuLinkPojo.setPrice(Double.parseDouble(skuPrices[i]));
    // productSkuLinkService.addSkuLinkByProductId(productSkuLinkPojo);
    // }
    // }

    // 新增sku
    JSONObject skuList = null, attrList = null, attrObject = null;
    Map<String, Long> attrMap = new HashMap<String, Long>();
    try {
      // 添加sku_attr
      if (!"".equals(attrListStr)) {
        attrList = JSONObject.fromObject(attrListStr);
        Iterator iterator = attrList.keys();
        String key = null;
        String value = null;
        SkuAttributePojo skuAttributePojo = null;
        Date nowDate = new Date();
        while (iterator.hasNext()) {
          key = (String) iterator.next();
          value = attrList.getString(key);

          if (value != null && !"".equals(value) && !"[]".equals(value)) {
            Object[] attrArray = getJsonToArray(value);
            for (int i = 0; i < attrArray.length; i++) {
              if (attrArray[i] != null) {
                attrObject = JSONObject.fromObject(attrArray[i]);
                if (attrObject != null) {
                  Iterator attrObj = attrObject.keys();
                  // 遍历jsonObject数据，添加到Map对象
                  while (attrObj.hasNext()) {
                    String k = String.valueOf(attrObj.next());
                    String v = attrObject.getString(k);
                    // 添加sku属性表
                    skuAttributePojo = new SkuAttributePojo();
                    if ("1".equals(key)) {
                      skuAttributePojo.setAttribute(sxType.getName());
                      skuAttributePojo.setDictValue(Integer.valueOf(sxType.getValue()));
                    } else {
                      skuAttributePojo.setAttribute(gsType.getName());
                      skuAttributePojo.setDictValue(Integer.valueOf(gsType.getValue()));
                    }
                    skuAttributePojo.setValue(v);
                    skuAttributePojo.setProductId(productPojo.getId());
                    skuAttributePojo.setCreateDate(nowDate);
                    skuAttributePojo.setCreateBy(loginPojo.getId());
                    skuAttributePojo.setUpdateDate(nowDate);
                    skuAttributePojo.setUpdateBy(loginPojo.getId());
                    skuAttributeService.insertSkuAttribute(skuAttributePojo);
                    // k按钮的键,id添加数据库的id
                    attrMap.put(k, skuAttributePojo.getId());
                  }
                }
              }
            }
          }
        }
      }
    } catch (Exception e) {
      Util.log("新增sku_attr出现异常!");
      e.printStackTrace();
    }
    try {
      // 添加sku
      if (!"".equals(skuListStr)) {
        skuList = JSONObject.fromObject(skuListStr);
        Iterator iterator = skuList.keys();
        String key = null;
        String value = null;
        JSONObject sku = null;
        Date nowDate = new Date();
        while (iterator.hasNext()) {
          key = (String) iterator.next();
          value = skuList.getString(key);
          sku = JSONObject.fromObject(value);
          ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();
          // 根据attrMap获取attr_id
          String[] k = key.split("_");
          for (int i = 0; i < k.length; i++) {
            if (i == 0) {// 属性key
              Long v = attrMap.get(k[0]);
              productSkuLink.setSkuColorId(v);
            } else {// 格式key
              Long v = attrMap.get(k[1]);
              productSkuLink.setSkuFormatId(v);
            }
          }
          productSkuLink.setProductId(productPojo.getId());
          productSkuLink.setSkuColor(sku.getString("color"));
          productSkuLink.setSkuFormat(sku.getString("format"));
          productSkuLink.setStock(sku.getString("stock") == null ? null : Integer.valueOf(sku
              .getString("stock")));
          productSkuLink.setStockNum(sku.getString("stock") == null ? null : Integer.valueOf(sku
              .getString("stock")));
          // productSkuLink.setPrice(sku.getString("price") == null ? null : Double.valueOf(sku
          // .getString("price")));
          productSkuLink.setBusinessCode(sku.getString("businessCodes"));
          productSkuLink.setImage(sku.getString("skuImage"));
          productSkuLink.setStatus(sku.getString("skuStatuss") == null ? 0 : Integer.valueOf(sku
              .getString("skuStatuss")));
          productSkuLink.setCreateDate(nowDate);
          productSkuLink.setCreateBy(loginPojo.getId());
          productSkuLink.setUpdateDate(nowDate);
          productSkuLink.setUpdateBy(loginPojo.getId());
          productSkuLinkService.addSkuLinkByProductId(productSkuLink);
        }
      }
    } catch (Exception e) {
      Util.log("新增sku出现异常!");
      e.printStackTrace();
    }
    FileUtil.alertMessageBySkip("新增成功！", "productListManage.do");
    return null;
  }

  /**
   * 
   * @param arrayStr json数组字符串
   * @return
   */
  public static Object[] getJsonToArray(String arrayStr) {
    JSONArray jsonArray = JSONArray.fromObject(arrayStr);
    return jsonArray.toArray();
  }

  /**
   * 
   * 上传sku图片接口
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String upProductSkuImage() throws Exception {
    try {
      if (skuImage != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        Util.log(">>>>> skuImage:" + file_name);
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/productSkuLink")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/productSkuLink/", skuImage);
        // result = ConstParam.URL + "/upfiles/product" + File.separator + file_name;
        result = file_name;
      } else {
        result = "";
      }
    } catch (Exception e) {
      result = "0";
      e.printStackTrace();
    }
    return SUCCESS;
  }


  public String goFindProduct() throws Exception {
    SysLoginPojo loginPojo = UserUtil.getUser();
    if (loginPojo == null) {
      FileUtil.alertMessageBySkip("请先登录！", "productListManage.do");
      return null;
    }
    productPojo = productService.findProduct(productPojo);
    if (productPojo == null) {
      FileUtil.alertMessageBySkip("查询不到该商品!", "productListManage.do");
      return null;
    }
    // getProductType(0l);
    // getProductTypeWithAge(0l);
    ActionContext ac = ActionContext.getContext();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("type", "unit");
    ac.put("unit", syntheticalDictService.getSyntheticalDictListByType(map));
    // map.put("type", "brand");
    // ac.put("brand", syntheticalDictService.getSyntheticalDictListByType(map));
    map.put("userId", productPojo.getUserId());
    map.put("status", 1);
    ac.put("brandList", userBrandService.findUserBrandByUserId(map));
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
    // ac.put("MFlist", sysLoginService.sysLoginMFService());
    // ac.put("productTypes", productTypeList);
    productTypePojo = new ProductTypePojo();
    productTypePojo.setPid(-1L);
    ac.put("productType1List", productTypeService.getProductTypeByPids(productTypePojo));
    if (productPojo != null && productPojo.getProductType1() != null) {
      productTypePojo = new ProductTypePojo();
      productTypePojo.setLevel(1);
      productTypePojo.setTopLevel(productPojo.getProductType1());
      getProductType2();
      productTypePojo.setPid(Long.parseLong(productPojo.getProductTypeIds()));
      productTypePojo.setTopLevel(null);
      getProductType3();
    }

    productFocusImages = new ProductFocusImagesPojo();
    productFocusImages.setProductId(productPojo.getId());
    productFocusImagesList =
        productFocusImagesService.productFocusImagesAllList(productFocusImages, null);
    if (productFocusImagesList.size() > 0) {
      upfilesId = new long[productFocusImagesList.size()];
      for (int i = 0; i < productFocusImagesList.size(); i++) {
        upfilesId[i] = productFocusImagesList.get(i).getId();
      }
    }
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("productId", productPojo.getId());
    productSkuLinkPojos = productSkuLinkService.listPage(params);
    params.put("type", 1);
    grouponActivityPojos = grouponActivityService.listPage(params);
    // sku属性类型
    List<SysDictPojo> skuTypeList = sysDictService.getSysDictListByType("sku_attr_type");
    ac.put("skuTypeList", skuTypeList);
    // sku列表
    try {
      params = new HashMap<String, Object>();
      new HashMap<String, Object>();
      Map<String, Object> item = null;
      Map<String, Object> items = new HashMap<String, Object>();
      params.put("productId", productPojo.getId());
      List<ProductSkuLinkPojo> productSkuLinkList = productSkuLinkService.listPage(params);
      for (ProductSkuLinkPojo productSkuLink : productSkuLinkList) {
        item = new HashMap<String, Object>();
        item.put("stock", StringUtil.checkVal(productSkuLink.getStock()));
        item.put("color", StringUtil.checkVal(productSkuLink.getSkuColor()));
        item.put("format", StringUtil.checkVal(productSkuLink.getSkuFormat()));
        item.put("businessCodes", StringUtil.checkVal(productSkuLink.getBusinessCode()));
        item.put("skuImage", StringUtil.checkVal(productSkuLink.getImage()));
        item.put("skuStatuss", StringUtil.checkVal(productSkuLink.getStatus()));
        item.put("rcdid", StringUtil.checkVal(productSkuLink.getId()));
        items.put(
            StringUtil.checkVal(productSkuLink.getSkuColorId()) + "_"
                + StringUtil.checkVal(productSkuLink.getSkuFormatId()), item);
      }
      JSONArray json = JSONArray.fromObject(items);
      ac.put("skuListData", json.toString());
      // 偏远地区判断
      sysAreaPojo = new SysAreaPojo();
      sysAreaPojo.setPid(0L);
      List<SysAreaPojo> sysAreaPojos = sysAreaService.getSysAreaByPid(sysAreaPojo);
      if (sysAreaPojos != null && sysAreaPojos.size() > 0) {
        String[] farawayArr = null;
        List<Long> farawayList = new ArrayList<>();
        if (productPojo.getFaraway() != null && !"".equals(productPojo.getFaraway())) {
          farawayArr = productPojo.getFaraway().split(",");
          if (farawayArr != null && farawayArr.length > 0) {
            for (String f : farawayArr) {
              farawayList.add(Long.valueOf(f));
            }
          }
        }
        if (farawayList != null && farawayList.size() > 0) {
          for (SysAreaPojo sysAreaPojo : sysAreaPojos) {
            if (farawayList.contains(sysAreaPojo.getId())) {
              sysAreaPojo.setIsOften(0);
            } else {
              sysAreaPojo.setIsOften(1);
            }
          }
        }
      }
      ac.put("sysAreaPojos", sysAreaPojos);
    } catch (Exception e) {
      Util.log("查询sku列表出现异常");
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 修改商品名称
   * 
   * @return
   * @throws Exception
   */
  public String goFindProductName() throws Exception {
    productPojo = productService.findProductName(id);
    return SUCCESS;
  }

  public String productNameUpdate() throws Exception {
    productService.productNameUpdate(productPojo);
    ProductSellPojo productSellPojo = new ProductSellPojo();
    productSellPojo.setProductId(productPojo.getId());
    productSellPojo.setProductName(productPojo.getProductName());
    productSellPojo.setUpdateDate(new Date());
    productSellService.update(productSellPojo);
    FileUtil.alertMessageBySkip("修改成功！", "productListManage.do?page.pageNo=" + page.getPageNo()
        + "");
    return null;

  }

  /**
   * 修改商品详情
   * 
   * @return
   * @throws Exception
   */
  public String goFindProductContent() throws Exception {
    productPojo = productService.findProductContent(id);
    return SUCCESS;
  }

  public String productContentUpdate() throws Exception {
    productService.productContentUpdate(productPojo);
    FileUtil.alertMessageBySkip("修改成功！", "productManage.do");
    return null;

  }

  /***
   * 修改商品上架下架
   * 
   * @return
   */
  public String productStatusUpdate() {
    try {
      if (grouponActivityPojo != null) {
        grouponActivityService.update(grouponActivityPojo);
        FileUtil.alertMessageBySkip("修改成功！", "productListManage.do");
      } else {
        FileUtil.alertMessageBySkip("修改失败！", "productListManage.do");
      }
    } catch (SQLException e) {
      FileUtil.alertMessageBySkip("修改失败！", "productListManage.do");
      e.printStackTrace();
    }
    return null;

  }

  /**
   * 修改商品库存信息
   */
  public String findProductStockCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      new HashMap<String, Object>();
      int i = productService.findProductStockCount(getId());
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String findProductStockList() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      productPojoList = productService.findProductStockList(getId());
      JSONArray json = JSONArray.fromObject(productPojoList);
      page.setRowCount(productPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  public String productStockUpdate() throws Exception {
    try {
      if (stockArray[0] != null) {
        for (int i = 0; i < stockArray.length; i++) {
          String s = stockArray[i];
          String a[] = s.split(":");
          stock = a[0];
          stockSkuId = a[1];
          productService.productStockUpdate(stock, stockSkuId);
        }
        FileUtil.alertMessageBySkip("修改成功！", "productListManage.do?page.pageNo=" + page.getPageNo()
            + "");
      } else {
        FileUtil.alertMessageBySkip("修改失败！", "productListManage.do?page.pageNo=" + page.getPageNo()
            + "");
      }
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("修改失败！", "productListManage.do?page.pageNo=" + page.getPageNo()
          + "");
      e.printStackTrace();
    }
    return null;
  }



  /***
   * 获取二级类目
   * 
   * @return
   */
  public String getProductType2() throws Exception {
    typeIdsStr = "";
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

  public String productUpdate() throws Exception {
    SysLoginPojo loginPojo = UserUtil.getUser();
    if (loginPojo == null) {
      FileUtil.alertMessageBySkip("请先登录！", "productListManage.do");
    }
    // norm(1-规格1;2-规格2)
    productPojo.setNormType(norm);
    productPojo.setColorValue(sxType);
    productPojo.setFormatValue(gsType);
    // 查找字典对应的sku属性值
    SysDictPojo sysDict = new SysDictPojo();
    sysDict.setValue(String.valueOf(sxType));
    sysDict.setType("sku_attr_type");
    SysDictPojo sxType = sysDictService.getSysDictByTypeAndValue(sysDict);// 属性
    sysDict.setValue(String.valueOf(gsType));
    SysDictPojo gsType = sysDictService.getSysDictByTypeAndValue(sysDict);// 格式
    if (norm == 1) {
      if (sxType == null) {
        FileUtil.alertMessageBySkip("sku属性有误！", "productListManage.do");
        return null;
      }
    } else {
      if (sxType == null || gsType == null) {
        FileUtil.alertMessageBySkip("sku属性或格式有误！", "productListManage.do");
        return null;
      }
    }
    // 修改图片和其他信息
    try {
      if (upfile != null) {
        String file_name = getUpfileFileName();
        String image =
            FileUtil.uploadFile(upfile, file_name, "upfiles/product", false, 300, 300, true, true,
                "");
        if (image == null) {
          FileUtil.alertMessageBySkip("修改失败！", "productListManage.do");
          return null;
        } else {
          productPojo.setImageMain(image);
        }
      }

      if (upfile1 != null) {
        String file_name = getUpfile1FileName();
        String image =
            FileUtil.uploadFile(upfile1, file_name, "upfiles/product", false, 300, 300, true, true,
                "");
        if (image == null) {
          FileUtil.alertMessageBySkip("修改失败！", "productListManage.do");
          return null;
        } else {
          productPojo.setImage(image);
        }
      }

      if (upfile2 != null) {
        String file_name = getUpfile2FileName();
        String image =
            FileUtil.uploadFile(upfile2, file_name, "upfiles/product", false, 300, 300, true, true,
                "");
        if (image == null) {
          FileUtil.alertMessageBySkip("修改失败！", "productListManage.do");
          return null;
        } else {
          productPojo.setImageSmall(image);
        }
      }

      String typeIds = ":" + String.valueOf(productPojo.getProductTypeIds()) + ":";
      productPojo.setProductTypeIds(typeIds);
      BigDecimal factPrice = new BigDecimal(String.valueOf(productPojo.getDistributionPrice()));
      BigDecimal sellPrice = new BigDecimal(String.valueOf(productPojo.getSellingPrice()));
      BigDecimal discount =
          factPrice.divide(sellPrice, 3, RoundingMode.HALF_UP).multiply(new BigDecimal(10))
              .setScale(1, RoundingMode.HALF_UP);
      if (discount.doubleValue() < 10) {
        productPojo.setDiscount(discount.toPlainString());
      } else {
        productPojo.setDiscount("0.0");
      }
      productPojo.setHoutai(1);
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
      // 删除
      if (upfilesId != null && upfilesId.length > 0 && upfilesId[0] != 0) {
        productFocusImages = new ProductFocusImagesPojo();
        productFocusImages.setProductId(productPojo.getId());
        productFocusImages.setCids(upfilesId);
        productFocusImagesList =
            productFocusImagesService.productFocusImagesAllList(productFocusImages, null);
        if (productFocusImagesList.size() > 0) {
          for (ProductFocusImagesPojo p : productFocusImagesList) {
            productFocusImagesService.deleteProductFocusImages(p.getId());
          }
        }
      }
      // 修改
      if (upfilesId != null && upfilesId.length > 0) {
        int sorting = 0;
        int fileCount = 0;
        for (int i = 0; i < upfilesId.length; i++) {
          if (upfiles2 != null && upfiles2.length > 0 && upfiles2[0].length() > 0) {
            if (isUpFiles != null && isUpFiles[i] == 1) {
              String file_name = StringUtil.getCurrentDateStr() + ".jpg";
              String uploadPath =
                  ServletActionContext.getServletContext().getRealPath(
                      "/upfiles/productFocusImages")
                      + File.separator;
              FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/",
                  upfiles2[fileCount]);
              // // 图片压缩
              // CompressPicture cp = new CompressPicture();
              // String compressPath =
              // ServletActionContext.getServletContext().getRealPath(
              // "/upfiles/productFocusImages/small")
              // + File.separator;
              // cp.compressPic(upfiles2[fileCount], compressPath,
              // "upfiles/productFocusImages/small/", file_name, 300, 300, true);
              productFocusImages = new ProductFocusImagesPojo();
              productFocusImages.setId(upfilesId[i]);
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
        long uid = productPojo.getUserId();
        int sorting = 0;
        if (upfilesId != null) {
          sorting = upfilesId.length;
        }
        for (int i = 0; i < upfiles.length; i++) {
          if (upfiles[i].length() > 0) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/productFocusImages/", upfiles[i]);
            // // 图片压缩
            // CompressPicture cp = new CompressPicture();
            // String compressPath =
            // ServletActionContext.getServletContext().getRealPath(
            // "/upfiles/productFocusImages/small")
            // + File.separator;
            // cp.compressPic(upfiles[i], compressPath, "upfiles/productFocusImages/small/",
            // file_name, 300, 300, true);
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

      Map<String, Object> params = new HashMap<String, Object>();
      params.put("productId", productPojo.getId());
      // 1-普通拼团
      params.put("type", 1);
      if (groupIds != null) {
        params.put("cids", groupIds);

        grouponActivityPojos = grouponActivityService.listPage(params);
        if (grouponActivityPojos.size() > 0) {
          for (GrouponActivityPojo g : grouponActivityPojos) {
            grouponActivityMapper.deleteById(g.getId());
          }
        }
      }

      if (skuIds != null) {
        params.remove("type");
        params.put("cids", skuIds);
        productSkuLinkPojos = productSkuLinkService.listPage(params);
        if (productSkuLinkPojos.size() > 0) {
          for (ProductSkuLinkPojo p : productSkuLinkPojos) {
            productSkuLinkService.deleProductSkuLink(p.getId());
          }
        }
      }

      if (groupIds != null && groupNums != null && groupPrices != null) {
        for (int i = 0; i < groupIds.length; i++) {
          GrouponActivityPojo grouponActivityPojo = new GrouponActivityPojo();
          // grouponActivityPojo.setProductId(productPojo.getId());
          grouponActivityPojo.setNum(Integer.parseInt(groupNums[i]));
          grouponActivityPojo.setPrice(Double.parseDouble(groupPrices[i]));
          grouponActivityPojo.setStatus(Integer.parseInt(groupStatuss[i]));
          // grouponActivityPojo.setSorting(i + 1);
          // grouponActivityPojo.setActivityStatus(1);
          // grouponActivityPojo.setType(1);
          // grouponActivityPojo.setCurrentTime("当前时间");

          grouponActivityPojo.setId(groupIds[i]);
          grouponActivityService.update(grouponActivityPojo);
        }
      }

      if (groupNums != null && groupPrices != null) {
        for (int i = groupIds.length; i < groupNums.length; i++) {
          GrouponActivityPojo grouponActivityPojo = new GrouponActivityPojo();
          grouponActivityPojo.setProductId(productPojo.getId());
          grouponActivityPojo.setNum(Integer.parseInt(groupNums[i]));
          grouponActivityPojo.setPrice(Double.parseDouble(groupPrices[i]));
          grouponActivityPojo.setStatus(1);
          grouponActivityPojo.setSorting(i + 1);
          grouponActivityPojo.setActivityStatus(1);
          grouponActivityPojo.setType(1);
          grouponActivityPojo.setCurrentTime("当前时间");
          grouponActivityService.add(grouponActivityPojo);
        }
      }

      // if (skuIds != null && skuTypes != null && skuColors != null && skuStocks != null
      // && businessCodes != null) {
      // int fileCount = 0;
      // for (int i = 0; i < skuIds.length; i++) {
      // ProductSkuLinkPojo productSkuLinkPojo = new ProductSkuLinkPojo();
      // productSkuLinkPojo.setSkuFormat(skuTypes[i]);
      // productSkuLinkPojo.setSkuColor(skuColors[i]);
      // if (skuFiles != null && skuFiles.length > 0 && skuFiles[0].length() > 0) {
      // if (isSkuFiles != null && isSkuFiles[i] == 1) {
      // String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      // String uploadPath =
      // ServletActionContext.getServletContext().getRealPath("/upfiles/productSkuLink")
      // + File.separator;
      // FileUtil.uploadFile(file_name, uploadPath, "upfiles/productSkuLink/",
      // skuFiles[fileCount]);
      // productSkuLinkPojo.setImage(file_name);
      // fileCount++;
      // }
      // }
      // productSkuLinkPojo.setStock(Integer.parseInt(skuStocks[i]));
      // productSkuLinkPojo.setStockNum(Integer.parseInt(skuStocks[i]));
      // productSkuLinkPojo.setStatus(Integer.parseInt(skuStatuss[i]));
      // productSkuLinkPojo.setId(skuIds[i]);
      // productSkuLinkPojo.setBusinessCode(businessCodes[i]);
      // productSkuLinkService.update(productSkuLinkPojo);
      // }
      // }

      // if (skuTypes != null && skuColors != null && skuStocks != null && businessCodes != null) {
      // int fileCount = 0;
      // for (int i = skuIds.length; i < skuTypes.length; i++) {
      // ProductSkuLinkPojo productSkuLinkPojo = new ProductSkuLinkPojo();
      // productSkuLinkPojo.setProductId(productPojo.getId());
      // productSkuLinkPojo.setSkuFormat(skuTypes[i]);
      // productSkuLinkPojo.setSkuColor(skuColors[i]);
      // productSkuLinkPojo.setBusinessCode(businessCodes[i]);
      // if (skuFiles2 != null && skuFiles2.length > 0 && skuFiles2[0].length() > 0) {
      // String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      // String uploadPath =
      // ServletActionContext.getServletContext().getRealPath("/upfiles/productSkuLink")
      // + File.separator;
      // FileUtil.uploadFile(file_name, uploadPath, "upfiles/productSkuLink/",
      // skuFiles2[fileCount]);
      // productSkuLinkPojo.setImage(file_name);
      // fileCount++;
      // } else {
      // productSkuLinkPojo.setImage("");
      // }
      // productSkuLinkPojo.setStock(Integer.parseInt(skuStocks[i]));
      // productSkuLinkPojo.setStockNum(Integer.parseInt(skuStocks[i]));
      // productSkuLinkPojo.setSorting(i + 1);
      // productSkuLinkPojo.setStatus(1);
      // // productSkuLinkPojo.setPrice(Double.parseDouble(skuPrices[i]));
      //
      // productSkuLinkPojo.setBusinessCode(businessCodes[i]);
      // productSkuLinkService.addSkuLinkByProductId(productSkuLinkPojo);
      // }
      // }
      FileUtil.alertMessageBySkip("修改成功！", "productListManage.do");
    } catch (Exception e) {
      e.printStackTrace();
    }

    // 新增sku
    JSONObject skuList = null, attrList = null, attrObject = null;
    Map<String, Long> attrMap = new HashMap<String, Long>();
    try {
      // 添加sku_attr
      if (!"".equals(attrListStr)) {
        attrList = JSONObject.fromObject(attrListStr);
        Iterator iterator = attrList.keys();
        String key = null;
        String value = null;
        SkuAttributePojo skuAttributePojo = null;
        Date nowDate = new Date();
        while (iterator.hasNext()) {
          key = (String) iterator.next();
          value = attrList.getString(key);
          if (value != null && !"".equals(value) && !"[]".equals(value)) {
            Object[] attrArray = getJsonToArray(value);
            for (int i = 0; i < attrArray.length; i++) {
              if (attrArray[i] != null) {
                attrObject = JSONObject.fromObject(attrArray[i]);
                if (attrObject != null) {
                  Iterator attrObj = attrObject.keys();
                  // 遍历jsonObject数据，添加到Map对象
                  while (attrObj.hasNext()) {
                    String k = String.valueOf(attrObj.next());
                    String v = attrObject.getString(k);
                    // 判断是否是新增的
                    if (Integer.valueOf(k) <= 0) {
                      // 添加sku属性表
                      skuAttributePojo = new SkuAttributePojo();
                      if ("1".equals(key)) {
                        skuAttributePojo.setAttribute(sxType.getName());
                        skuAttributePojo.setDictValue(sxType.getValue() == null ? 0 : Integer
                            .valueOf(sxType.getValue()));
                      } else {
                        skuAttributePojo.setAttribute(gsType.getName());
                        skuAttributePojo.setDictValue(gsType.getValue() == null ? 0 : Integer
                            .valueOf(gsType.getValue()));
                      }
                      skuAttributePojo.setValue(v);
                      skuAttributePojo.setProductId(productPojo.getId());
                      skuAttributePojo.setCreateDate(nowDate);
                      skuAttributePojo.setCreateBy(loginPojo.getId());
                      skuAttributePojo.setUpdateDate(nowDate);
                      skuAttributePojo.setUpdateBy(loginPojo.getId());
                      skuAttributeService.insertSkuAttribute(skuAttributePojo);
                      // k按钮的键,id添加数据库的id
                      attrMap.put(k, skuAttributePojo.getId());
                    } else {
                      attrMap.put(k, Long.valueOf(k));
                    }
                  }
                }
              }
            }
          }
        }
      }
    } catch (Exception e) {
      Util.log("新增sku_attr出现异常!");
      e.printStackTrace();
    }
    // 删除sku
    if (delskuStr != null || !"".equals(delskuStr)) {
      JSONObject delSkuList = null;
      delSkuList = JSONObject.fromObject(delskuStr);
      Iterator iterator = delSkuList.keys();
      String key = null;
      String value = null;
      while (iterator.hasNext()) {
        key = (String) iterator.next();
        if ("attr".equals(key)) {// 删除属性
          value = delSkuList.getString(key);
          Object[] attrArray = getJsonToArray(value);
          for (int i = 0; i < attrArray.length; i++) {
            if (Long.valueOf(StringUtil.checkVal(attrArray[i])) != null) {
              skuAttributeService.deleSkuAttribute(Long.valueOf(StringUtil.checkVal(attrArray[i])));
            }
          }
        } else {// 删除sku
          value = delSkuList.getString(key);
          Object[] attrArray = getJsonToArray(value);
          for (int i = 0; i < attrArray.length; i++) {
            if (Long.valueOf(StringUtil.checkVal(attrArray[i])) != null) {
              productSkuLinkService.deleProductSkuLink(Long.valueOf(StringUtil
                  .checkVal(attrArray[i])));
            }
          }
        }
      }
    }
    // 更新/新增sku
    try {
      if (!"".equals(skuListStr)) {
        skuList = JSONObject.fromObject(skuListStr);
        Iterator iterator = skuList.keys();
        String key = null;
        String value = null;
        JSONObject sku = null;
        Date nowDate = new Date();
        while (iterator.hasNext()) {
          key = (String) iterator.next();
          value = skuList.getString(key);
          sku = JSONObject.fromObject(value);
          ProductSkuLinkPojo productSkuLink = new ProductSkuLinkPojo();

          // 根据attrMap获取attr_id
          String[] k = key.split("_");
          for (int i = 0; i < k.length; i++) {
            if (i == 0) {// 属性key
              if ("0".equals(sku.getString("rcdid"))) {
                Long v = attrMap.get(k[0]);
                productSkuLink.setSkuColorId(v);
              } else {
                productSkuLink.setSkuColorId(Long.valueOf(k[0]));
              }
            } else {// 格式key
              if ("0".equals(sku.getString("rcdid"))) {
                Long v = attrMap.get(k[1]);
                productSkuLink.setSkuFormatId(v);
              } else {
                productSkuLink.setSkuColorId(Long.valueOf(k[0]));
              }
            }
          }
          productSkuLink.setProductId(productPojo.getId());
          productSkuLink.setSkuColor(sku.getString("color"));
          productSkuLink.setSkuFormat(sku.getString("format"));
          productSkuLink.setStock(sku.getString("stock") == null ? null : Integer.valueOf(sku
              .getString("stock")));
          productSkuLink.setStockNum(sku.getString("stock") == null ? null : Integer.valueOf(sku
              .getString("stock")));
          productSkuLink.setBusinessCode(sku.getString("businessCodes"));
          productSkuLink.setImage(sku.getString("skuImage"));
          productSkuLink.setStatus(sku.getString("skuStatuss") == null ? 0 : Integer.valueOf(sku
              .getString("skuStatuss")));
          productSkuLink.setCreateDate(nowDate);
          productSkuLink.setCreateBy(loginPojo.getId());
          productSkuLink.setUpdateDate(nowDate);
          productSkuLink.setUpdateBy(loginPojo.getId());
          // 新增或更新sku
          if ("0".equals(sku.getString("rcdid"))) {
            productSkuLinkService.addSkuLinkByProductId(productSkuLink);
          } else {
            productSkuLink.setId(Long.valueOf(sku.getString("rcdid")));
            productSkuLinkService.update(productSkuLink);
          }
        }
      }
    } catch (Exception e) {
      Util.log("新增sku出现异常!");
      e.printStackTrace();
    }

    return null;
  }

  public String deleProduct() throws SQLException {
    try {
      productService.uncheckProduct(productPojo.getId());
      ProductSellPojo productSell = new ProductSellPojo();
      productSell.setProductId(productPojo.getId());
      productSell.setStatus(0);
      productSellService.update(productSell);
      grouponActivityPojo.setIsDelete(1);
      grouponActivityPojo.setStatus(0);
      grouponActivityService.update(grouponActivityPojo);
      // productService.deleProduct(productPojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  public String productDeleteId() {
    if (tids != null) {
      productService.productDeleteId(tids);
      FileUtil.alertMessageBySkip("删除成功！",
          "productManage.do?productPojo.userId=" + productPojo.getUserId());
    } else {
      FileUtil.alertMessageBySkip("删除失败！",
          "productManage.do?productPojo.userId=" + productPojo.getUserId());
    }

    return null;
  }

  /**
   * 审核全部
   * 
   * @return
   * @throws SQLException
   */
  public String checkProductAll() throws SQLException {
    try {
      if (tids == null || "".equals(tids)) {
        FileUtil.alertMessageBySkip("请勾选需要审核的商品！", "productManage.do");
        return null;
      } else {
        ProductSellPojo productSell = new ProductSellPojo();
        for (String id : tids) {
          productService.checkProduct(Long.valueOf(id));
          productSell.setProductId(Long.valueOf(id));
          productSell.setStatus(1);
          productSellService.update(productSell);
        }
        FileUtil.alertMessageBySkip("批量审核成功！", "productManage.do");
      }
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("批量审核失败！", "productManage.do");
    }
    return null;
  }

  /**
   * 商家商品-审核全部
   * 
   * @return
   * @throws SQLException
   */
  public String sellerCheckProductAll() throws SQLException {
    try {
      ProductSellPojo productSell = new ProductSellPojo();
      for (String id : tids) {
        productService.checkProduct(Long.valueOf(id));
        productSell.setProductId(Long.valueOf(id));
        productSell.setStatus(1);
        productSellService.update(productSell);
      }
      FileUtil.alertMessageBySkip("批量审核成功！", "sellerProductManage.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("批量审核失败！", "sellerProductManage.do");
    }
    return null;
  }

  public String checkProduct() throws SQLException {
    try {
      productService.checkProduct(productPojo.getId());
      ProductSellPojo productSell = new ProductSellPojo();
      productSell.setProductId(Long.valueOf(id));
      productSell.setStatus(1);
      productSellService.update(productSell);
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /**
   * 取消审核
   */
  public String uncheckProduct() throws SQLException {
    try {
      productService.uncheckProduct(productPojo.getId());
      ProductSellPojo productSell = new ProductSellPojo();
      productSell.setProductId(productPojo.getId());
      productSell.setStatus(0);
      productSellService.update(productSell);
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  public String goSetLadderPrice() {
    productPojo = productService.findProduct(productPojo);
    return SUCCESS;
  }

  public String setLadderPrice() throws SQLException {
    productPojo = productService.findProduct(productPojo);
    List<LadderPricePojo> pList = new ArrayList<LadderPricePojo>();
    if (min != null && prices != null && min.length == prices.length) {
      for (int i = 0; i < min.length; i++) {
        LadderPricePojo lp = new LadderPricePojo();
        lp.setMin(min[i]);
        if (max[i] == null || max[i] != null && max[i].equals("")) {
          lp.setMax("0");
        } else {
          lp.setMax(max[i]);
        }
        lp.setPrice(prices[i]);
        pList.add(lp);
      }
    }
    JSONArray json = JSONArray.fromObject(pList);
    productPojo.setLadderPrice(json.toString());
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
    return SUCCESS;
  }

  public String goSetMinimum() {
    productPojo = productService.findProduct(productPojo);
    return SUCCESS;
  }

  public String setMinimum() {
    int minimum = productPojo.getMinimum();
    productPojo = productService.findProduct(productPojo);
    productPojo.setMinimum(minimum);
    productService.productUpdate(productPojo);
    return SUCCESS;
  }

  /**
   * 
   * 根据首页推荐查询相应的数据
   * */
  public String goSetIntroduce() throws SQLException {
    ActionContext ac = ActionContext.getContext();
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    // 根据传值查询相应的数据
    if (typeStr.equals("1")) {
      // 查询所有recommend为0的数据
      productList = productService.indexProductLists(null, page);
    } else if (typeStr.equals("11")) {
      // 查询所有product_type_ids为1recommend为0的数据
      productList = productService.indexProductLists("1", page);
    } else if (typeStr.equals("22")) {
      // 查询所有product_type_ids为2recommend为0的数据
      productList = productService.indexProductLists("2", page);
    } else if (typeStr.equals("33")) {
      // 查询所有product_type_ids为3recommend为0的数据
      productList = productService.indexProductLists("3", page);
    } else if (typeStr.equals("44")) {
      // 查询所有product_type_ids为4recommend为0的数据
      productList = productService.indexProductLists("4", page);
    } else if (typeStr.equals("55")) {
      // 查询所有product_type_ids为5recommend为0的数据
      productList = productService.indexProductLists("5", page);
    } else if (typeStr.equals("66")) {
      // 查询所有product_type_ids为6recommend为0的数据
      productList = productService.indexProductLists("6", page);
    }
    ac.put("typeStr", typeStr);
    ac.put("oldId", oldId);
    JSONArray json = JSONArray.fromObject(productList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 
   * 查询无推荐的商品
   * */
  public String getCountIndexProduct() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    if (typeStr.equals("11")) {
      page.setRowCount(productService.indexProductListsCount("1"));
    } else if (typeStr.equals("22")) {
      page.setRowCount(productService.indexProductListsCount("2"));
    } else if (typeStr.equals("33")) {
      page.setRowCount(productService.indexProductListsCount("3"));
    } else if (typeStr.equals("44")) {
      page.setRowCount(productService.indexProductListsCount("4"));
    } else if (typeStr.equals("55")) {
      page.setRowCount(productService.indexProductListsCount("5"));
    } else if (typeStr.equals("66")) {
      page.setRowCount(productService.indexProductListsCount("6"));
    } else {
      page.setRowCount(productService.indexProductListsCount(null));
    }
    return SUCCESS;
  }

  /**
   * 
   * 修改首页新品推荐
   * */
  public String updateTopFive() {
    ActionContext ac = ActionContext.getContext();
    if (typeStr.equals("1")) {// 首页新品推荐
      productService.indexProductTopFiveUpdateNew(productPojo);// 设置新的新品推荐
      productPojo.setId(oldId);
      productService.indexProductTopFiveUpdate(productPojo);// 将旧的新品推荐设置为未推荐
      // 查询首页新品推荐5条数据
      productList = productService.indexProductTopFive();
    } else {// 首页楼层推荐
      productService.indexProductFloorUpdateNew(productPojo);
      productPojo.setId(oldId);
      productService.indexProductTopFiveUpdate(productPojo);
      // 查询首页楼层推荐6条数据
      productList = productService.indexShowByFloor(typeStr);
    }
    ac.put("productNewList", productList);
    return SUCCESS;
  }

  public String setIntroduce() {
    productService.productUpdateIng(productPojo);
    return SUCCESS;
  }

  public void setAgeType() {
    ProductTypePojo productTypeqry = new ProductTypePojo();
    productTypeqry.setPid(0L);
    productTypeqry.setName("0-3岁婴幼儿玩具");
    List<ProductTypePojo> list1 = productTypeService.getProductTypeByPidName(productTypeqry);
    if (list1 != null && list1.size() > 0) {
      ageType1 = list1.get(0).getId();
    }
    productTypeqry.setName("3-6岁儿童玩具");
    list1 = productTypeService.getProductTypeByPidName(productTypeqry);
    if (list1 != null && list1.size() > 0) {
      ageType2 = list1.get(0).getId();
    }
    productTypeqry.setName("6岁以上玩具");
    list1 = productTypeService.getProductTypeByPidName(productTypeqry);
    if (list1 != null && list1.size() > 0) {
      ageType3 = list1.get(0).getId();
    }
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
        // 年龄段玩具过滤
        if (child.getPid() == 0
            && (7 == child.getSorting() || 8 == child.getSorting() || 9 == child.getSorting())) {
          continue;
        }
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

  public void getProductTypeWithAge(Long pid) throws Exception {
    ProductTypePojo fatherType = new ProductTypePojo();
    fatherType.setId(pid);
    fatherType = productTypeService.findProductType(fatherType);
    ProductTypePojo pojo = new ProductTypePojo();
    pojo.setPid(pid);
    List<ProductTypePojo> childList = productTypeService.getProductTypeByPids(pojo);

    String typeTemp = fatherType == null ? "" : fatherType.getName() + "--";
    if (!childList.isEmpty()) {
      Map<String, Object> proTypeMap = null;
      for (ProductTypePojo child : childList) {
        // 年龄段玩具过滤
        if (child.getPid() == 0
            && (7 == child.getSorting() || 8 == child.getSorting() || 9 == child.getSorting())) {
          continue;
        }
        proTypeMap = new HashMap<String, Object>();
        proTypeMap.put("value", child.getId());
        proTypeMap.put("name", typeTemp + child.getName());
        if (child.getAgeType() == null || "".equals(child.getAgeType())) {
          proTypeMap.put("ageType", "0");
        } else {
          proTypeMap.put("ageType", child.getAgeType());
        }
        productTypeList.add(proTypeMap);
        getProductTypeWithAge(child.getId());
      }
    }
  }

  /*
   * 添加浏览记录
   * 
   * @id产品编号
   */
  public void historyed(long id) {
    SysLoginPojo loginPojo = UserUtil.getWebUser();
    if (loginPojo != null) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("userId", loginPojo.getId());
      map.put("type", 1);
      map.put("businessId", id);
      map.put("createBy", loginPojo.getId());
      map.put("createDate", new Date());
      map.put("updateDate", new Date());
      int s = historyService.checkhistory(map);
      if (s == 0) {
        historyService.inserthistory(map);
      } else {
        historyService.updatehistory(map);
      }
    }
  }

  /**
   * 产品详情页--彬
   */
  public String goProductDetail() throws Exception {
    historyed(productPojo.getId());
    productService.updateHits(productPojo.getId());
    String loginPojotype = "-1";
    SysLoginPojo loginPojo = UserUtil.getWebUser();
    if (loginPojo != null) {
      loginPojotype = loginPojo.getType();
    }

    productPojo = productService.findProduct(productPojo);

    /*
     * if (productPojo == null||productPojo.getStatus()==0) {
     * FileUtil.alertMessageBySkip("该商品已经下架！", "goProductListWeb.do"); return null; }
     */
    if (productPojo == null) {
      FileUtil.alertMessageBySkip("该商品已经下架！", "productManageSellerWeb.do");
      return null;
    }
    // 是否包邮
    if (productPojo.getPostageType() == 1) {
      productPojo.setPostageTypeName("是");
    } else if (productPojo.getPostageType() == 0) {
      productPojo.setPostageTypeName("否");
    }
    shopPojo = new ShopPojo();
    shopPojo.setUserId(productPojo.getUserId());
    shopPojo = shopService.findShop(shopPojo);
    List<ProductImagesPojo> productImagesList;
    productImagesList = productImagesService.productForId(productPojo.getId());

    List<ProductPojo> shopProductList;
    shopProductList = productService.productForShopId(productPojo.getUserId());

    productPojo.setId(productPojo.getId());
    productPojo.setUserId(productPojo.getUserId());
    productPojo.setProductTypeId(productPojo.getProductTypeId());
    List<ProductPojo> hotProductList;
    hotProductList = productService.productForHot(productPojo);
    userCollectPojo = new UserCollectPojo();
    userCollectPojo.setProductId(productPojo.getId());
    int fcount = UserCollectService.productCollectAllCount(userCollectPojo);

    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(18);
    productCommentPojo = new ProductCommentPojo();
    productCommentPojo.setProductId(productPojo.getId());
    pclist = productCommentService.productCommentAllListWeb(productCommentPojo, page);
    if (page2 == null) {
      page2 = new Pager();
      page2.setPageNo(1);
    }
    page2.setPageSize(10);
    orderDetailPojo = new OrderDetailPojo();
    orderDetailPojo.setProductId(productPojo.getId());
    odlist = orderDetailService.orderDetailAllList(orderDetailPojo, page2);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("productId", productPojo.getId());
    map.put("pageSize", 10);
    map.put("pageNo", (page2.getPageNo() - 1) * page2.getPageSize());
    elelist = eleorderService.findEleorderByorderNo(map);

    sysAreaPojo = new SysAreaPojo();
    sysAreaPojo.setPid(0l);
    List<SysAreaPojo> sysAreas = sysAreaService.getSysAreaByPid(sysAreaPojo);


    Double pweight = Double.valueOf(productPojo.getWeight());
    int intPweight = (int) Math.ceil(pweight);
    for (SysAreaPojo sysArea : sysAreas) {
      double fare = sysArea.getPostage() + sysArea.getAddPostage() * (intPweight - 1);
      double fare2 = sysArea.getPostage2() + sysArea.getAddPostage2() * (intPweight - 1);
      sysArea.setFare(fare);
      sysArea.setFare2(fare2);
    }

    ActionContext ac = ActionContext.getContext();
    ac.put("loginPojotype", loginPojotype);
    ac.put("productImagesList", productImagesList);
    ac.put("shopProductList", shopProductList);
    ac.put("hotProductList", hotProductList);
    ac.put("fcount", fcount);
    ac.put("pclist", pclist);
    ac.put("pcnum", productCommentService.productCommentAllCountWe(productCommentPojo));
    ac.put("productPojo", productPojo);
    ac.put("odlist", odlist);
    ac.put("elelist", elelist);
    int o = userOrderDetailService.sellSumNum(productPojo.getId());// 淘竹马订单成交数量，订单状态>=2
    int e = eleorderService.sellSumNum(productPojo.getId());// 电商订单成交数量
    // int pSaleNum = productService.findProduct(productPojo).getSellNumber(); //产品销量
    // ac.put("odnum", pSaleNum);
    ac.put("odnum", o + e);
    if (o > e) {
      ac.put("count", orderDetailService.orderDetailAllCount(orderDetailPojo));
    } else {
      Map<String, Object> map1 = new HashMap<String, Object>();
      map1.put("productId", productPojo.getId());
      ac.put("count", eleorderService.EleorderAllCount(map1));
    }
    ac.put("productFocusImageslist",
        productFocusImagesService.findProductFocusImagesByPid(productPojo.getId()));

    ac.put("sysAreas", sysAreas);
    return SUCCESS;
  }

  public String getProductPrices() throws SQLException {
    productPojo = productService.findProduct(productPojo);
    // String prices = productPojo.getLadderPrice();
    pPrices = productPojo.getLadderPrice();
    // setpPrices(productPojo.getLadderPrice());
    return SUCCESS;
  }

  /**
   * 供应商->我的->订单管理--By杨峰
   * 
   * @return
   * @throws Exception
   */
  public String getProductCountWeb() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(20);
    // if (page == null)
    // page = new Pager();
    // page.setPageSize(4);
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
    }
    Long uID = sysLogin.getId();
    if (productPojo == null) {
      productPojo = new ProductPojo();
    }
    productPojo.setUserId(uID);
    // page.setRowCount(productService.getCount(productPojo,page));
    // Long uID = orderPojo.getUserId();
    // orderPojo.setSuserId(uID);

    // Long suerID=0L;
    // 取到店铺的id，即供应商的id
    // shopPojo = shopService.getfindByIdShopWeb(uID);
    // if(shopPojo!=null){
    // suerID = shopPojo.getId();
    // }
    // Long suerID = shopPojo.getId();
    Map<String, Object> map = new HashMap<String, Object>();
    if (orderStatus > 0) {
      map.put("orderStatus", orderStatus);
    }
    map.put("orderNo", orderNo);
    map.put("consignee", consignee);
    // map.put("uID", uID);
    map.put("suerID", uID);
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    try {
      if (orderNo == null && consignee == null && orderStatus > 3) {
        map.put("statusok", 998);
        map.put("orderStatus", 4);
        webSupplyOrderList = orderService.supplyOrderListWeb(map);
        // map.put("orderStatus", 5);
        // webSupplyOrderList.addAll(orderService.supplyOrderListWeb(map));
      } else {
        webSupplyOrderList = orderService.supplyOrderListWeb(map);
      }
      page.setRowCount(orderService.supplyOrderListWebCount(map));
    } catch (SQLException e) {

      e.printStackTrace();
    }
    // 热卖单品
    hotProductListSupplyWeb = productService.productForHotSupplyWeb(productPojo);
    // 产品销售统计
    productList = productService.productSellCountSupplyWeb(productPojo);
    ActionContext ac = ActionContext.getContext();
    ac.put("orderStatus", orderStatus);
    return SUCCESS;
  }

  /***
   * 采购商：退货
   * 
   * @return
   * @throws Exception
   */
  public String userOrderRefundWeb() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
    }
    Long uID = sysLogin.getId();
    Map<String, Object> map = new HashMap<String, Object>();
    if (orderStatus > 0) {
      map.put("orderStatus", orderStatus);
    }
    map.put("orderNo", orderNo);
    map.put("userId", uID);
    try {
      webConsumerOrderList = orderService.consumerOrderListWeb(map);
    } catch (SQLException e) {

      e.printStackTrace();
    }
    return SUCCESS;
  }

  /***
   * 产品信息管理---杨峰
   * 
   * @return
   * @throws Exception
   */
  public String productAllListWeb() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    Long uID = sysLogin.getId();
    page.setPageSize(4);
    productPojo.setUserId(uID);
    productList = productService.getProductAll(productPojo, page);
    JSONArray json = JSONArray.fromObject(productList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /***
   * 订单管理
   */
  public String gomySupplier() {

    // 热卖单品
    hotProductListSupplyWeb = productService.productForHotSupplyWeb(productPojo);
    ActionContext ac = ActionContext.getContext();
    ac.put("hotProductListSupplyWeb", hotProductListSupplyWeb);
    return SUCCESS;
  }

  /***
   * 删除产品信息---杨峰
   * 
   * @return
   * @throws SQLException
   */
  public String deleProductWeb() throws SQLException {
    try {
      productService.deleProduct(productPojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /***
   * 批量删除---杨峰
   * 
   * @return
   */
  public String productDeleteIdWeb() {
    if (tids != null) {
      productService.productDeleteId(tids);
      FileUtil.alertMessageBySkip("删除成功！", "productManageWeb.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "productManageWeb.do");
    }
    return null;
  }

  /***
   * 跳到添加产品添加---杨峰
   * 
   * @return
   * @throws Exception
   */
  public String goProductAddWeb() throws Exception {
    getProductType(0l);
    return SUCCESS;
  }

  /***
   * 添加产品信息---杨峰
   * 
   * @return
   * @throws Exception
   */
  public String productAddWeb() throws Exception {
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
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 120, 120, true);
      productPojo.setImage(file_name);
    } else {
      productPojo.setImage("");
    }
    productPojo.setStatus(0);
    StringBuffer sbf = new StringBuffer(RandomUtils.runVerifyCode(6));
    // sbf.append(new Date().getTime());
    // 生成二维码
    // String content = "F***";
    // String path =
    // ServletActionContext.getServletContext().getRealPath("/upfiles/qrcode") + File.separator;
    // File creapath = new File(path);
    // if (!creapath.isDirectory()) {
    // creapath.mkdirs();
    // }
    // MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
    // Hashtable hints = new Hashtable();
    // hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
    // BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,
    // hints);
    // SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    // String img = df.format(new Date());
    // File file1 = new File(path, img + ".jpg");
    // MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
    //
    // productPojo.setQrcode(img + ".jpg");
    productPojo.setProductNo(sbf.toString());
    productPojo.setLadderPrice("[]");
    productService.addProduct(productPojo);
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
    // SKU插入
    productStockPojo.setStatus(0);
    productStockService.addProductStock(productStockPojo);

    return SUCCESS;
  }

  /***
   * 跳转到更新产品信息页面---杨峰
   * 
   * @return
   * @throws Exception
   */
  public String goFindProductWeb() throws Exception {
    productPojo = productService.findProduct(productPojo);
    getProductType(0l);
    // 查询SKU
    productStockPojo = productStockService.findProductStock(productStockPojo);
    return SUCCESS;
  }

  /***
   * 更新产品信息
   * 
   * @return
   * @throws Exception
   */
  public String productUpdateWeb() throws Exception {
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
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 120, 120, true);
      productPojo.setImage(file_name);
    } else {
      productPojo.setImage("");
    }
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
    // 更新SKU
    productStockService.productStockUpdate(productStockPojo);
    return SUCCESS;
  }

  /**
   * /** 导出产品EXCEL
   */
  public String getProductExcel() throws Exception {
    List<ProductPojo> productList;
    productList = productService.getProductAll(productPojo, null);
    downloadFileName = "产品.xls";
    ExcelProcessor<ProductPojo> e =
        new ExcelProcessor<ProductPojo>(productList, ProductPojo.class, "产品");
    inputStream = e.generateExcelSteam();
    return SUCCESS;
  }

  public String getProductExcel2() throws Exception {
    List<ProductExcelPojo> productList;
    try {
      ProductExcelPojo productExcelPojo = new ProductExcelPojo();
      productExcelPojo.setProductStatus(1);
      if (productPojo != null) {
        if (productPojo.getProductType1() != null && productPojo.getProductType1() != "") {
          productExcelPojo.setProductType1(productPojo.getProductType1());
        }
        if (productPojo.getProductTypeIds() != null && productPojo.getProductTypeIds() != "") {
          productExcelPojo.setProductTypeIds(productPojo.getProductTypeIds());
        }
        if (productPojo.getProductTypeId() != null && productPojo.getProductTypeId() != "") {
          productExcelPojo.setProductTypeId(productPojo.getProductTypeId());
        }
        if (productPojo.getProductNameOrId() != null && productPojo.getProductNameOrId() != "") {
          productExcelPojo.setProductNameOrId(productPojo.getProductNameOrId());
        }
        if (productPojo.getActivityStatus() != null) {
          productExcelPojo.setActivityStatus(productPojo.getActivityStatus());
        }
      }
      productExcelPojo.setOrderBy("t.id asc");
      productList = productService.getProductAll2(productExcelPojo, null);
      downloadFileName = "产品拼团.xls";
      ExcelProcessor<ProductExcelPojo> e =
          new ExcelProcessor<ProductExcelPojo>(productList, ProductExcelPojo.class, "产品拼团");
      inputStream = e.generateExcelSteam();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 
   * 将地址接收到的json转成str
   * */
  public static String loadJson(String url) {
    StringBuilder json = new StringBuilder();
    try {
      URL urlObject = new URL(url);
      URLConnection uc = urlObject.openConnection();
      BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
      String inputLine = null;
      while ((inputLine = in.readLine()) != null) {
        json.append(inputLine);
      }
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return json.toString();
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

  public void webd() throws IOException, SQLException, DocumentException {
    HttpServletRequest request = ServletActionContext.getRequest();
    // if(cookies!=null){
    // for (int i = 0; i < cookies.length; i++)
    // {
    //
    // Cookie cookie=cookies[i];
    // if (cookie.getName().equals("ipcookie")) {
    // s=false;
    // }
    // }
    //
    // }
    // if(s){
    String city = "";
    String ip = getIpAddr(request);
    // ip写入记事本中
    // String
    // filePath=ServletActionContext.getServletContext().getRealPath("/temp")+File.separator+"ip.txt";
    // FileWriter fileWriter=new FileWriter(filePath,true);
    // fileWriter.write(ip+"\r\n");
    // fileWriter.flush();
    // fileWriter.close();

    long times = new Date().getTime() / 1000;
    String url = "http://ip.taobao.com/service/getIpInfo.php?ip=" + ip;
    String str = loadJson(url);
    if (StringUtils.isNotBlank(str) && str.startsWith("{")) {
      JSONObject jsonobject = JSONObject.fromObject(str);
      String jsonString = jsonobject.get("data").toString();

      JSONObject json = JSONObject.fromObject(jsonString);
      if (jsonobject.get("code").equals(0)) {
        city =
            json.get("region").toString() + json.get("city").toString()
                + json.get("isp").toString();
        Map<String, Object> map = new HashMap<String, Object>();
        if (!city.equals("")) {
          map.put("ipDesc", city);
          map.put("ip", ip);
          map.put("visitTime", times);
          ipService.insertIP(map);
        } else {
          map.put("ipDesc", "广东省汕头市铁通");
          map.put("ip", ip);
          map.put("visitTime", times);
          ipService.insertIP(map);
        }
        // Cookie cooki=new Cookie("ipcookie","123");//用户ID
        // cooki.setMaxAge(60*60*1);//cookie时间
        // response.addCookie(cooki);
      }
    } else {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("ipDesc", "广东省汕头市铁通");
      map.put("ip", ip);
      map.put("visitTime", times);
      ipService.insertIP(map);
    }
  }

  public String goIndexWeb() throws SQLException, IOException, DocumentException {
    webd();
    ActionContext ac = ActionContext.getContext();
    // 查询首页新品推荐5条数据
    productList = productService.indexProductTopFive();
    ac.put("productNewList", productList);
    productList = productService.indexShowByFloor("1");
    ac.put("productTypeList1", productList);
    productList = productService.indexShowByFloor("2");
    ac.put("productTypeList2", productList);
    productList = productService.indexShowByFloor("3");
    ac.put("productTypeList3", productList);
    productList = productService.indexShowByFloor("4");
    ac.put("productTypeList4", productList);
    productList = productService.indexShowByFloor("5");
    ac.put("productTypeList5", productList);
    productList = productService.indexShowByFloor("6");
    ac.put("productTypeList6", productList);

    ac.put("productTypeIds1", productTypeService.getProductTypeByPid8(1l));
    ac.put("productTypeIds2", productTypeService.getProductTypeByPid8(2l));
    ac.put("productTypeIds3", productTypeService.getProductTypeByPid8(3l));
    ac.put("productTypeIds4", productTypeService.getProductTypeByPid8(4l));
    ac.put("productTypeIds5", productTypeService.getProductTypeByPid8(5l));
    ac.put("productTypeIds6", productTypeService.getProductTypeByPid8(6l));

    ShopPojo shopPojo = new ShopPojo();
    shopPojo.setRecommendType(1l);
    shopPojo.setStatus(1);
    shopPojos = shopService.findShopIndex(shopPojo);
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < shopPojos.size(); i++) {
      sb.append("{src:'/upfiles/shop/" + shopPojos.get(i).getImages() + "',shopId:'"
          + shopPojos.get(i).getId() + "',alt:'',breviary:'#',type:'img'},");
    }
    String str = sb.toString();
    if (str.length() > 0) {
      str = str.substring(0, str.length() - 1);
    }
    ac.put("shopImages1", "[" + str + "]");

    shopPojo = new ShopPojo();
    shopPojo.setRecommendType(2l);
    shopPojo.setStatus(1);
    shopPojos = shopService.findShopIndex(shopPojo);
    StringBuffer sb2 = new StringBuffer();
    for (int i = 0; i < shopPojos.size(); i++) {
      sb2.append("{src:'/upfiles/shop/" + shopPojos.get(i).getImages() + "',shopId:'"
          + shopPojos.get(i).getId() + "',alt:'',breviary:'#',type:'img'},");
    }
    ac.put("shopImages2", "[" + sb2.toString() + "]");

    shopPojo = new ShopPojo();
    shopPojo.setRecommendType(3l);
    shopPojo.setStatus(1);
    shopPojos = shopService.findShopIndex(shopPojo);
    StringBuffer sb3 = new StringBuffer();
    for (int i = 0; i < shopPojos.size(); i++) {
      sb3.append("{src:'/upfiles/shop/" + shopPojos.get(i).getImages() + "',shopId:'"
          + shopPojos.get(i).getId() + "',alt:'',breviary:'#',type:'img'},");
    }
    ac.put("shopImages3", "[" + sb3.toString() + "]");

    shopPojo = new ShopPojo();
    shopPojo.setRecommendType(4l);
    shopPojo.setStatus(1);
    shopPojos = shopService.findShopIndex(shopPojo);
    StringBuffer sb4 = new StringBuffer();
    for (int i = 0; i < shopPojos.size(); i++) {
      sb4.append("{src:'/upfiles/shop/" + shopPojos.get(i).getImages() + "',shopId:'"
          + shopPojos.get(i).getId() + "',alt:'',breviary:'#',type:'img'},");
    }
    ac.put("shopImages4", "[" + sb4.toString() + "]");

    shopPojo = new ShopPojo();
    shopPojo.setRecommendType(5l);
    shopPojo.setStatus(1);
    shopPojos = shopService.findShopIndex(shopPojo);
    StringBuffer sb5 = new StringBuffer();
    for (int i = 0; i < shopPojos.size(); i++) {
      sb5.append("{src:'/upfiles/shop/" + shopPojos.get(i).getImages() + "',shopId:'"
          + shopPojos.get(i).getId() + "',alt:'',breviary:'#',type:'img'},");
    }
    ac.put("shopImages5", "[" + sb5.toString() + "]");

    shopPojo = new ShopPojo();
    shopPojo.setRecommendType(6l);
    shopPojo.setStatus(1);
    shopPojos = shopService.findShopIndex(shopPojo);
    StringBuffer sb6 = new StringBuffer();
    for (int i = 0; i < shopPojos.size(); i++) {
      sb6.append("{src:'/upfiles/shop/" + shopPojos.get(i).getImages() + "',shopId:'"
          + shopPojos.get(i).getId() + "',alt:'',breviary:'#',type:'img'},");
    }
    ac.put("shopImages6", "[" + sb6.toString() + "]");

    // 首页广告图//
    pagePushPojos = pagePushService.findAdByType(1);
    for (int i = 0; i < pagePushPojos.size(); i++) {
      ac.put("adimages" + i, pagePushPojos.get(i).getImages());
      ac.put("imageUrl" + i, pagePushPojos.get(i).getUrl());
      ac.put("backgroundColor" + i, pagePushPojos.get(i).getBackgroundColor());
    }

    return SUCCESS;
  }

  public String goNewWeb() throws SQLException {
    shopPojos = shopService.getShopNew();
    for (int i = 0; i < shopPojos.size(); i++) {
      shopPojos.get(i).setProductPojos(
          productService.productForUserNew(shopPojos.get(i).getUserId()));
    }
    ActionContext ac = ActionContext.getContext();
    ac.put("shopPojos", shopPojos);

    // 新品快递广告图//
    pagePushPojos = pagePushService.findAdByType(2);
    for (int i = 0; i < pagePushPojos.size(); i++) {
      ac.put("adimages" + i, pagePushPojos.get(i).getImages());
      ac.put("backgroundColor" + i, pagePushPojos.get(i).getBackgroundColor());
    }
    return SUCCESS;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  /**
   * 商家产品列表
   * 
   * @return
   * @throws Exception
   */
  public String getCountSellerProduct() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(productService.getCount(productPojo, page));
    productTypePojos = productTypeService.getProductTypeSecondLevel();
    ActionContext ac = ActionContext.getContext();
    ac.put("timeId", timeId);
    ac.put("productTypePojos", productTypePojos);
    ac.put("isNew", sysDictService.getSysDictListByType("yes_no"));
    return SUCCESS;
  }

  /**
   * 专场活动商品库条数
   * 
   * @throws Exception
   * @return String
   */
  public String getActProBucketCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("timeIdIsN", "1");
    map.put("activityType", 3);
    if (productPojo != null) {
      if (StringUtils.isNotBlank(productPojo.getShopName())) {
        map.put("shopName", productPojo.getShopName());
      }
      if (StringUtils.isNotBlank(productPojo.getProductNum())) {
        map.put("productNum", productPojo.getProductNum());
      }
      if (StringUtils.isNotBlank(productPojo.getProductName())) {
        map.put("productName", productPojo.getProductName());
      }
      if (StringUtils.isNotBlank(productPojo.getProductNo())) {
        map.put("productNo", productPojo.getProductNo());
      }
      map.put("status", productPojo.getStatus());
    }
    page.setRowCount(productService.getProductByActivityCount(map));
    return SUCCESS;
  }


  /**
   * 专场活动商品库
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String getActProBucket() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    ProductPojo product = new ProductPojo();
    product.setTimeIdIsN("1");
    product.setActivityType("3");
    product.setIsNew("1");
    product.setShopName(productPojo.getShopName());
    product.setProductNum(productPojo.getProductNum());
    product.setProductName(productPojo.getProductName());
    product.setProductNo(productPojo.getProductNo());
    product.setStatus(productPojo.getStatus());
    productList = productService.getProductByActivity(product, page);
    JSONArray json = JSONArray.fromObject(productList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 根据ID商品详情 webview
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String getProductInfoView() throws Exception {
    if (id == null || id == 0) {
      System.out.println("商品id不能为空!");
    } else {
      productPojo = new ProductPojo();
      productPojo.setId(id);
      Thread.sleep(1000);
      productPojo = productService.findProduct(productPojo);

      doMain = ConstParam.URL.substring(ConstParam.URL.indexOf(".") + 1);
      if (productPojo == null) {
        System.out.println("查询不到商品");
      }
    }
    return SUCCESS;
  }

  /**
   * 开团列表查询全部条数
   */
  public String groRecCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (beginTime != null) {
      map.put("beginTimeStr", StringUtil.dateString(beginTime));
    }
    if (endTime != null) {
      map.put("endTimeStr", StringUtil.dateString(endTime));
    }
    if (grouponUserRecordPojo != null) {
      map.put("keywords", grouponUserRecordPojo.getKeywords());
      map.put("groupStatus", grouponUserRecordPojo.getGroupStatus());
    }
    if (activityType != null) {
      map.put("activityType", activityType);
    }
    if (gaActivityId != null) {
      map.put("id", gaActivityId);
    }
    try {
      int i = grouponUserRecordService.countBy3(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 开团列表查询全部记录
   */
  public String groRecList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (beginTime != null) {
      map.put("beginTimeStr", StringUtil.dateString(beginTime));
    }
    if (endTime != null) {
      map.put("endTimeStr", StringUtil.dateString(endTime));
    }
    if (grouponUserRecordPojo != null) {
      map.put("keywords", grouponUserRecordPojo.getKeywords());
      map.put("groupStatus", grouponUserRecordPojo.getGroupStatus());
    }
    if (activityType != null) {
      map.put("activityType", activityType);
    }
    if (gaActivityId != null) {
      map.put("id", gaActivityId);
    }
    List<GrouponUserRecordPojo> grouponRecordList = null;
    try {
      grouponRecordList = grouponUserRecordService.listPage3(map);
      JSONArray json = JSONArray.fromObject(grouponRecordList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 团参加的人查询全部条数
   */
  public String groUserRecCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("attendId", grouponUserRecordPojo.getAttendId());
    map.put("keywords", keywords);
    if (status != null) {
      map.put("recStatus", status);
    }
    if (source != null) {
      map.put("orderSource", source);
    }
    map.put("source", 1);
    try {
      int i = grouponUserRecordService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 团参加的人查询全部记录
   */
  public String groUserRecList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("attendId", grouponUserRecordPojo.getAttendId());
    map.put("keywords", keywords);
    if (status != null) {
      map.put("recStatus", status);
    }
    if (source != null) {
      map.put("orderSource", source);
    }
    map.put("source", 1);
    map.put("orderBy", " gur.is_head desc");
    List<GrouponUserRecordPojo> grouponUserRecordList = null;
    try {
      grouponUserRecordList = grouponUserRecordService.listPage(map);
      JSONArray json = JSONArray.fromObject(grouponUserRecordList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 
   * 二维码下载
   * 
   * @throws Exception
   * @throw
   * @return void
   */
  public void qrcodeDownload() throws Exception {
    if (productPojo != null) {
      ProductPojo productPojoQr = productService.getById(productPojo.getId());
      if (productPojoQr != null) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String img = df.format(new Date());
        if (productPojoQr.getQrcode() == null || "".endsWith(productPojoQr.getQrcode())) {
          // String content = "F***";
          String content = ConstParam.WX_URL + "/groupon.php?id=" + productPojo.getId();
          String path =
              ServletActionContext.getServletContext().getRealPath("/upfiles/qrcode")
                  + File.separator;
          File creapath = new File(path);
          if (!creapath.isDirectory()) {
            creapath.mkdirs();
          }
          MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
          Hashtable hints = new Hashtable();
          hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
          BitMatrix bitMatrix =
              multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);

          File file1 = new File(path, img + ".jpg");
          MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);

          ProductPojo productPojoQc = new ProductPojo();
          productPojoQc.setQrcode(img + ".jpg");
          productPojoQc.setId(productPojo.getId());
          productService.productUpdate(productPojoQc);
        }

        String uploadPath =
            ServletActionContext.getServletContext().getRealPath(
                "/upfiles/qrcode/" + productPojoQr.getQrcode());
        HttpServletResponse response = ServletActionContext.getResponse();
        InputStream input = null;
        OutputStream output = null;
        try {
          File file = new File(uploadPath);
          if (file.length() > 0) {

            String name =
                productPojoQr.getQrcode() == null || "".endsWith(productPojoQr.getQrcode()) ? img
                    + ".jpg" : productPojoQr.getQrcode();
            response.setContentType("application/x-msdownload; charset=UTF-8");
            response.setHeader("Content-Disposition",
                "attachment;filename=" + java.net.URLEncoder.encode(name, "UTF-8"));
            response.addHeader("Content-Length", file.length() + "");

            output = response.getOutputStream();
            input = new FileInputStream(file);
            byte[] temp = new byte[1024 * 10];
            while (input.read(temp) != -1) {
              output.write(temp);
            }
            output.flush();
          } else {

          }
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          if (input != null) {
            try {
              input.close();
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }
  }

  /**
   * 获取格式按钮
   * 
   * @return
   * @throws Exception
   */
  public String getAttrSxBtn() throws Exception {
    result = "";
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = null;
    List<Map<String, Object>> sxList = new ArrayList<Map<String, Object>>();
    params.put("productId", id);
    params.put("dictValue", sxType);
    params.put("isDelete", 0);
    List<SkuAttributePojo> skuAttributeList = skuAttributeService.getSkuAttribute(params);
    for (SkuAttributePojo skuAttribute : skuAttributeList) {
      item = new HashMap<String, Object>();
      item.put("sxId", StringUtil.checkVal(skuAttribute.getId()));
      item.put("sxName", StringUtil.checkVal(skuAttribute.getValue()));
      sxList.add(item);
    }
    try {
      JSONArray json = JSONArray.fromObject(sxList);
      result = json.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 获取格式按钮
   * 
   * @return
   * @throws Exception
   */
  public String getAttrGsBtn() throws Exception {
    result = "";
    Map<String, Object> params = new HashMap<String, Object>();
    Map<String, Object> item = null;
    List<Map<String, Object>> gsList = new ArrayList<Map<String, Object>>();
    params.put("productId", id);
    params.put("dictValue", gsType);
    params.put("isDelete", 0);
    List<SkuAttributePojo> skuAttributeList = skuAttributeService.getSkuAttribute(params);
    for (SkuAttributePojo skuAttribute : skuAttributeList) {
      item = new HashMap<String, Object>();
      item.put("gsId", StringUtil.checkVal(skuAttribute.getId()));
      item.put("gsName", StringUtil.checkVal(skuAttribute.getValue()));
      gsList.add(item);
    }
    try {
      JSONArray json = JSONArray.fromObject(gsList);
      result = json.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }
}
