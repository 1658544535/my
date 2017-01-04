package com.tzmb2c.web.action;


import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.SpecialDiscountPojo;
import com.tzmb2c.web.pojo.SpecialProductPojo;
import com.tzmb2c.web.pojo.SpecialShowPojo;
import com.tzmb2c.web.pojo.SyntheticalDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.BrandDicService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.SpecialProductService;
import com.tzmb2c.web.service.SpecialShowService;
import com.tzmb2c.web.service.SyntheticalDictService;
import com.tzmb2c.web.service.UserBrandService;

public class MartShowWebAction extends SuperAction {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Autowired
  SpecialShowService specialShowService;
  @Autowired
  SpecialProductService specialProductService;
  @Autowired
  BrandDicService brandDicService;
  @Autowired
  UserBrandService userBrandService;
  @Autowired
  ProductTypeService productTypeService;
  @Autowired
  SyntheticalDictService syntheticalDictService;
  @Autowired
  SellerService sellerService;
  @Autowired
  ActivityTimeService activityTimeService;
  @Autowired
  ProductService productService;
  @Autowired
  ProductSkuLinkService productSkuLinkService;
  @Autowired
  ActivityGoodsService activityGoodsService;

  private SpecialShowPojo specialShowPojo;
  private UserBrandPojo userBrandPojo;
  private File banner;
  private String[] conditionmj;
  private String[] denominationmj;
  private String[] conditionmz;
  private String[] denominationmz;
  private String[] batchPrice;
  private String[] batchStock;
  private String[] batchBusCode;
  // 用户品牌ID
  private Long userBrandId;
  private ProductPojo productPojo;
  private String[] tids;
  private String result;
  private String skuResult;
  // 专场ID
  private Long specialId;
  private Long pid;
  private Integer size;
  /**
   * channelIds:多选频道ID
   */
  private String[] channelIds;

  private List<UserBrandPojo> userBrandLt;
  private List<SyntheticalDictPojo> ageRangeLt;
  private List<ProductTypePojo> productTypeLt;
  private List<SpecialShowPojo> addressRangeLt;

  public List<SpecialShowPojo> getAddressRangeLt() {
    return addressRangeLt;
  }

  public List<SyntheticalDictPojo> getAgeRangeLt() {
    return ageRangeLt;
  }

  public File getBanner() {
    return banner;
  }

  public String[] getBatchBusCode() {
    return batchBusCode;
  }

  public String[] getBatchPrice() {
    return batchPrice;
  }

  public String[] getBatchStock() {
    return batchStock;
  }

  public String[] getChannelIds() {
    return channelIds;
  }

  /**
   * 查看商家专场活动承诺书.
   * 
   * @return
   * @throws Exception
   */
  public String getCommitment() throws Exception {
    return SUCCESS;
  }

  public String[] getConditionmj() {
    return conditionmj;
  }

  public String[] getConditionmz() {
    return conditionmz;
  }

  public String[] getDenominationmj() {
    return denominationmj;
  }

  public String[] getDenominationmz() {
    return denominationmz;
  }

  public Long getPid() {
    return pid;
  }

  public ProductPojo getProductPojo() {
    return productPojo;
  }

  public List<ProductTypePojo> getProductTypeLt() {
    return productTypeLt;
  }

  public String getResult() {
    return result;
  }

  public Integer getSize() {
    return size;
  }

  public String getSkuResult() {
    return skuResult;
  }

  public Long getSpecialId() {
    return specialId;
  }

  public SpecialShowPojo getSpecialShowPojo() {
    return specialShowPojo;
  }

  public String[] getTids() {
    return tids;
  }

  public Long getUserBrandId() {
    return userBrandId;
  }

  public List<UserBrandPojo> getUserBrandLt() {
    return userBrandLt;
  }

  public UserBrandPojo getUserBrandPojo() {
    return userBrandPojo;
  }

  public void setAddressRangeLt(List<SpecialShowPojo> addressRangeLt) {
    this.addressRangeLt = addressRangeLt;
  }

  public void setAgeRangeLt(List<SyntheticalDictPojo> ageRangeLt) {
    this.ageRangeLt = ageRangeLt;
  }

  public void setBanner(File banner) {
    this.banner = banner;
  }

  public void setBatchBusCode(String[] batchBusCode) {
    this.batchBusCode = batchBusCode;
  }

  public void setBatchPrice(String[] batchPrice) {
    this.batchPrice = batchPrice;
  }

  public void setBatchStock(String[] batchStock) {
    this.batchStock = batchStock;
  }

  public void setChannelIds(String[] channelIds) {
    this.channelIds = channelIds;
  }

  public void setConditionmj(String[] conditionmj) {
    this.conditionmj = conditionmj;
  }

  public void setConditionmz(String[] conditionmz) {
    this.conditionmz = conditionmz;
  }

  public void setDenominationmj(String[] denominationmj) {
    this.denominationmj = denominationmj;
  }

  public void setDenominationmz(String[] denominationmz) {
    this.denominationmz = denominationmz;
  }

  public void setPid(Long pid) {
    this.pid = pid;
  }

  public void setProductPojo(ProductPojo productPojo) {
    this.productPojo = productPojo;
  }

  public void setProductTypeLt(List<ProductTypePojo> productTypeLt) {
    this.productTypeLt = productTypeLt;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public void setSkuResult(String skuResult) {
    this.skuResult = skuResult;
  }

  public void setSpecialId(Long specialId) {
    this.specialId = specialId;
  }

  public void setSpecialShowPojo(SpecialShowPojo specialShowPojo) {
    this.specialShowPojo = specialShowPojo;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public void setUserBrandId(Long userBrandId) {
    this.userBrandId = userBrandId;
  }

  public void setUserBrandLt(List<UserBrandPojo> userBrandLt) {
    this.userBrandLt = userBrandLt;
  }

  public void setUserBrandPojo(UserBrandPojo userBrandPojo) {
    this.userBrandPojo = userBrandPojo;
  }

  /**
   * 添加专场商品.
   * 
   * @return
   * @throws Exception
   */
  public String addMartShowItems() throws Exception {
    this.result = "1";
    Long pid = 0l;
    SpecialShowPojo specialShowPojo = specialShowService.findSpecialShowById(specialId);
    ProductPojo productParam = new ProductPojo();
    SpecialProductPojo specialProduct = null;
    ProductPojo productPojo = null;
    ActivityGoodsPojo activityGoodPojo = null;

    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
    }
    Long uid = sysLogin.getId();

    List<ProductSkuLinkPojo> skuList = null;
    ProductSkuLinkPojo skuParam = new ProductSkuLinkPojo();
    ProductSkuLinkPojo skuActivity = null;
    Map<String, Object> param = new HashMap<String, Object>();
    if (specialShowPojo != null && tids != null && tids.length > 0) {
      // Map<String, Object> param2 = new HashMap<String, Object>();
      // param2.put("specialId", specialShowPojo.getId());
      // List<SpecialProductPojo> list = specialProductService.findSpecialProductList(param2);
      // List<String> ids = new ArrayList<String>();
      // for (int i = 0; i < list.size(); i++) {
      // long pid1 = list.get(i).getProductId();
      // for (int j = 0; j < tids.length; j++) {
      // long pid2 = Long.valueOf(tids[j]);
      // if (pid1 == pid2) {
      // ids.add(tids[j]);
      // }
      // }
      // }
      // if(ids.size() != 0){
      // String m = "";
      // for (String s : ids) {
      // m += s+" ";
      // }
      // FileUtil.confirmMessage("商品ID："+m+"已经在专场中，是否要继续添加？", "window.location.href='#';",
      // "window.location.href='#';");
      // }else{
      for (int i = 0; i < tids.length; i++) {
        try {
          pid = Long.valueOf(tids[i]);
          // 查询商品是否已添加活动
          param.put("specialId", specialShowPojo.getId());
          param.put("productId", pid);
          int count = specialProductService.findSpecialProductCount(param);
          if (count > 0) {
            // 已添加
            this.result = "2";
            continue;
          }

          productParam.setId(pid);
          productPojo = productService.findProduct(productParam);
          // 插入活动商品表
          activityGoodPojo = new ActivityGoodsPojo();
          activityGoodPojo.setProductId(productPojo.getId());
          activityGoodPojo.setSorting(0);
          activityGoodPojo.setTimeId(specialShowPojo.getActivityId());
          activityGoodPojo.setSellPrice(productPojo.getSellingPrice());
          activityGoodPojo.setActivePrice(productPojo.getDistributionPrice());
          BigDecimal fact = new BigDecimal(String.valueOf(productPojo.getDistributionPrice()));
          BigDecimal sell = new BigDecimal(String.valueOf(productPojo.getSellingPrice()));
          BigDecimal tips =
              fact.divide(sell, 3, RoundingMode.HALF_UP).multiply(new BigDecimal(10))
                  .setScale(1, RoundingMode.HALF_UP);
          if (tips.doubleValue() < 10) {
            activityGoodPojo.setTips(tips.toPlainString() + "折");
          } else {
            activityGoodPojo.setTips("无折扣");
          }
          activityGoodPojo.setStatus(0);
          activityGoodPojo.setActivityNum(100L);
          activityGoodPojo.setActivityStock(activityGoodPojo.getActivityNum());
          activityGoodPojo.setCreateBy(uid);
          activityGoodPojo.setUpdateBy(uid);
          activityGoodsService.insertActivityGoods(activityGoodPojo);
          // 插入专场商品表
          specialProduct = new SpecialProductPojo();
          specialProduct.setSpecialId(specialShowPojo.getId());
          specialProduct.setProductId(productPojo.getId());
          specialProduct.setGoodId(activityGoodPojo.getId());
          specialProductService.insertSpecialProduct(specialProduct);

          // 插入sku
          skuParam.setActivityId(0L);
          skuParam.setProductId(productPojo.getId());
          skuParam.setStatus(1);
          skuParam.setType(0);
          skuList = productSkuLinkService.getProductSkuLinkAll(skuParam, null);
          if (skuList != null && skuList.size() > 0) {
            for (ProductSkuLinkPojo productSkuLinkPojo : skuList) {
              skuActivity = new ProductSkuLinkPojo();
              BeanUtils.copyProperties(skuActivity, productSkuLinkPojo);
              skuActivity.setActivityId(specialShowPojo.getActivityId());
              skuActivity.setType(1);
              productSkuLinkService.addSkuLinkByProductId(skuActivity);
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
          this.result = "0";
        }
      }
      // }
    }
    return SUCCESS;
  }

  /**
   * 专场申请前端显示
   * 
   * @return
   * @throws Exception
   */
  public String applyMartShowWeb() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
    }
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("userId", sysLogin.getId());
    param.put("status", 1);
    // 品牌列表
    userBrandLt = userBrandService.findUserBrandByUserId(param);
    // 一级分类列表
    /*
     * ProductTypePojo productTypePojo = new ProductTypePojo(); productTypePojo.setPid(0l);
     * List<ProductTypePojo> productTypes =
     * productTypeService.getProductTypeByPids(productTypePojo); productTypeLt = new
     * ArrayList<ProductTypePojo>(); for (ProductTypePojo proType : productTypes) { if
     * (!("0-3岁婴幼儿玩具".equals(proType.getName()) || "3-6岁儿童玩具".equals(proType.getName()) ||
     * "6岁以上玩具".equals(proType.getName()))) { productTypeLt.add(proType); } } productTypes.clear();
     */
    ProductTypePojo productTypePojo = new ProductTypePojo();
    productTypePojo.setPid(-1l);
    productTypeLt = productTypeService.getProductTypeByPids(productTypePojo);

    // 使用年龄列表
    param.clear();
    param.put("type", "age_range");
    ageRangeLt = syntheticalDictService.getSyntheticalDictListByType(param);

    // 退货地址列表
    addressRangeLt = specialShowService.findSpecialShowByUid(sysLogin.getId());

    // 修改时 查询专场信息
    if (specialId != null) {
      specialShowPojo = specialShowService.findSpecialShowById(specialId);
      if (specialShowPojo != null && specialShowPojo.getDiscountType() != null
          && specialShowPojo.getDiscountType() > 0) {
        List<SpecialDiscountPojo> specialDiscounts =
            sellerService.transJsonToDiscount(specialShowPojo.getDiscountContext());
        if (specialDiscounts != null && specialDiscounts.size() > 0) {
          size = specialDiscounts.size();
          Integer type = specialShowPojo.getDiscountType();
          SpecialDiscountPojo specialDiscount = null;
          if (type != null && type == 1) {
            conditionmj = new String[size];
            denominationmj = new String[size];
            for (int i = 0; i < size; i++) {
              specialDiscount = specialDiscounts.get(i);
              conditionmj[i] = specialDiscount.getOm();
              denominationmj[i] = specialDiscount.getM();
            }
          } else if (type != null && type == 2) {
            conditionmz = new String[size];
            denominationmz = new String[size];
            for (int i = 0; i < size; i++) {
              specialDiscount = specialDiscounts.get(i);
              conditionmz[i] = specialDiscount.getOm();
              denominationmz[i] = specialDiscount.getM();
            }
          }
        }
      }
    }

    return SUCCESS;
  }

  /**
   * 批量修改库存信息.
   * 
   * @return
   * @throws Exception
   */
  public String batchModifySku() throws Exception {
    this.result = "1";
    Long skuId = 0l;
    ProductSkuLinkPojo skuLink = null;
    if (tids != null && tids.length > 0 && batchPrice != null && batchPrice.length > 0
        && batchStock != null && batchStock.length > 0 && batchBusCode != null
        && batchBusCode.length > 0) {
      for (int i = 0; i < tids.length; i++) {
        try {
          skuId = Long.valueOf(tids[i]);
          skuLink = new ProductSkuLinkPojo();
          skuLink.setId(skuId);
          skuLink.setPrice(Double.valueOf(batchPrice[i]));
          skuLink.setStock(Integer.valueOf(batchStock[i]));
          skuLink.setStockNum(skuLink.getStock());
          skuLink.setBusinessCode(batchBusCode[i]);
          productSkuLinkService.productSkuLinkUpdate(skuLink);
        } catch (Exception e) {
          e.printStackTrace();
          this.result = "0";
        }
      }
    }
    return SUCCESS;
  }

  /**
   * 删除活动商品. pid:专场商品ID
   * 
   * @return
   * @throws Exception
   */
  public String delMartShowProduct() throws Exception {
    this.result = "1";
    if (specialId != null) {
      try {
        SpecialShowPojo specialShowPojo = specialShowService.findSpecialShowById(specialId);
        SpecialProductPojo specialProduct = null;
        ActivityGoodsPojo activityGood = null;
        if (specialShowPojo != null && pid != null) {
          specialProduct = specialProductService.findSpecialProductById(pid);
          if (specialProduct != null) {
            activityGood = activityGoodsService.findActivityGoodsById(specialProduct.getGoodId());
            if (activityGood != null && activityGood.getStatus() != null
                && activityGood.getStatus() == 1) {
              this.result = "0";
            } else {
              // 删除专场商品
              specialProductService.delSpecialProduct(specialProduct.getId());
              // 删除活动商品信息

              activityGoodsService.delActivityGoods(specialProduct.getGoodId());
              // 删除商品活动sku
              ProductSkuLinkPojo skuParam = new ProductSkuLinkPojo();
              skuParam.setActivityId(specialShowPojo.getActivityId());
              skuParam.setProductId(specialProduct.getProductId());
              skuParam.setType(1);
              List<ProductSkuLinkPojo> skus =
                  productSkuLinkService.getProductSkuLinkAll(skuParam, null);
              if (skus != null && skus.size() > 0) {
                for (ProductSkuLinkPojo sku : skus) {
                  productSkuLinkService.deleProductSkuLink(sku.getId());
                }
              }
            }

          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        this.result = "0";
      }
    }
    return SUCCESS;
  }



  /**
   * 专场列表前端显示
   * 
   * @return
   * @throws Exception
   */
  public String getMartShowCountWeb() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
    }
    // 更新已结束专场状态
    specialShowService.updateSpecialShowEnd();

    Map<String, Object> param = new HashMap<String, Object>();
    param.put("userId", sysLogin.getId());
    param.put("isdelete", "0");
    param.put("checkstatus", 1);
    if (specialShowPojo != null) {
      param.put("title", specialShowPojo.getTitle());
      param.put("status", specialShowPojo.getStatus());
      param.put("id", specialShowPojo.getId());
    }
    int rowCount = specialShowService.findSpecialShowCount(param);
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(rowCount);
    return SUCCESS;
  }

  /**
   * 查询专场产品列表.
   * 
   * @return
   * @throws Exception
   */
  public String getMartShowItemList() throws Exception {
    List<SpecialProductPojo> specialProducts = new ArrayList<SpecialProductPojo>();
    if (specialId != null) {
      SpecialShowPojo specialShowPojo = specialShowService.findSpecialShowById(specialId);
      if (specialShowPojo != null) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("specialId", specialId);
        // param.put("status", 1);
        specialProducts = specialProductService.findSpecialProductList(param);
      }
    }
    JSONArray json = JSONArray.fromObject(specialProducts);
    this.result = json.toString();
    return SUCCESS;
  }

  /**
   * 查询专场产品SKU列表.
   * 
   * @return
   * @throws Exception
   */
  public String getMartShowItemSkuList() throws Exception {
    List<ProductSkuLinkPojo> skus = new ArrayList<ProductSkuLinkPojo>();
    if (specialId != null) {
      SpecialShowPojo specialShowPojo = specialShowService.findSpecialShowById(specialId);
      if (specialShowPojo != null && pid != null) {
        ProductSkuLinkPojo skuParam = new ProductSkuLinkPojo();
        skuParam.setActivityId(specialShowPojo.getActivityId());
        skuParam.setProductId(pid);
        skuParam.setStatus(1);
        skuParam.setType(1);
        skus = productSkuLinkService.getProductSkuLinkAll(skuParam, null);
      }
    }
    JSONArray json = JSONArray.fromObject(skus);
    this.skuResult = json.toString();
    return SUCCESS;
  }

  /**
   * 查询专场信息.
   * 
   * @return
   * @throws Exception
   */
  public String getMartShowItemWeb() throws Exception {
    if (specialId != null) {
      specialShowPojo = specialShowService.findSpecialShowById(specialId);
      if (specialShowPojo != null) {
        String discountContext =
            sellerService.transJsonToDiscountStr(specialShowPojo.getDiscountType(),
                specialShowPojo.getDiscountContext());
        specialShowPojo.setDiscountContext(discountContext);
        userBrandPojo = userBrandService.findUserBrandById(specialShowPojo.getUserBrandId());
      }
    }
    return SUCCESS;
  }

  /**
   * 专场列表前端显示
   * 
   * @return
   * @throws Exception
   */
  public String getMartShowListWeb() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
    }
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("pageSize", 10);
    param.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    param.put("userId", sysLogin.getId());
    param.put("isdelete", "0");
    param.put("checkstatus", 1);
    param.put("houtai", 1);
    if (specialShowPojo != null) {
      param.put("title", specialShowPojo.getTitle());
      param.put("status", specialShowPojo.getStatus());
      param.put("id", specialShowPojo.getId());
    }
    List<SpecialShowPojo> specialShowPojos = specialShowService.findSpecialShowList(param);
    for (SpecialShowPojo specialShowPojo : specialShowPojos) {
      if (specialShowPojo.getDiscountContext() != null
          && !"".equals(specialShowPojo.getDiscountContext())) {
        specialShowPojo.setDiscountContext(sellerService.transJsonToDiscountStr(
            specialShowPojo.getDiscountType(), specialShowPojo.getDiscountContext()));
      }
    }
    JSONArray json = JSONArray.fromObject(specialShowPojos);
    page.setResult(json.toString());
    return SUCCESS;
  }



  /**
   * 返回商品信息.
   * 
   * @return
   * @throws Exception
   */
  public String getUserBrandProducts() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
    }
    Long uid = sysLogin.getId();
    SpecialShowPojo specialShowPojo = specialShowService.findSpecialShowById(specialId);
    if (specialShowPojo != null) {
      userBrandId = specialShowPojo.getUserBrandId();
    }
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    List<ProductPojo> products = new ArrayList<ProductPojo>();
    if (uid != null && userBrandId != null) {
      page.setPageSize(10);
      ProductPojo product = new ProductPojo();

      /*
       * Map<String, Object> param = new HashMap<String, Object>(); param.put("specialId",
       * specialId); List<SpecialProductPojo> list =
       * specialProductService.findSpecialProductList(param); pids = new String[list.size()]; for
       * (int i = 0; i < list.size(); i++) { pids[i] = list.get(i).getProductId().toString(); }
       * product.setFilter(pids);
       */

      // List<SpecialProductPojo> list = null;
      // SpecialShowPojo specialShow = specialShowService.findSpecialShowById(specialId);
      // if (specialShow != null) {
      // Map<String, Object> param = new HashMap<String, Object>();
      // param.put("specialId", specialId);
      // list = specialProductService.findSpecialProductList(param);
      // }

      /*
       * Map<String, Object> param = new HashMap<String, Object>(); param.put("type", 3);
       * List<ActivityGoodsPojo> list = activityGoodsService.checkActivityGoodsByPid(param); if
       * (list.size() != 0) { pids = new String[list.size()]; for (int i = 0; i < list.size(); i++)
       * { pids[i] = list.get(i).getProductId().toString(); } product.setFilter(pids); }
       */
      product.setFilter(new String[] {"1"});
      product.setUserId(uid);
      product.setUserBrandId(userBrandId);
      if (productPojo != null) {
        product.setProductName(productPojo.getProductName());
        product.setProductNum(productPojo.getProductNum());
      }
      products = productService.getAgencyProductAll(product, page);
      UserBrandPojo userBrand = null;
      if (products.size() != 0) {
        for (ProductPojo productPojo : products) {
          userBrand = userBrandService.findUserBrandById(productPojo.getUserBrandId());
          if (userBrand != null && userBrand.getBrandName() != null) {
            productPojo.setBrandName(userBrand.getBrandName());
          }

          // if (list != null) {
          // long pid1 = productPojo.getId();
          // for (SpecialProductPojo s : list) {
          // long pid2 = s.getProductId();
          // if (pid1 == pid2) {
          // productPojo.setIsAdd(true);
          // }
          // }
          // }

          // Map<String, Object> param = new HashMap<String, Object>();
          // param.put("productId", productPojo.getId());
          // param.put("type", 3);
          // int count = activityGoodsService.checkActivityGoodsByPidCount(param);
          // if (count > 0) {
          // productPojo.setIsAdd(true);
          // }
        }
      }
    }
    JSONArray json = JSONArray.fromObject(products);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 返回商品信息行数.
   * 
   * @return
   * @throws Exception
   */
  public String getUserBrandProductsCount() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
    }
    Long uid = sysLogin.getId();
    SpecialShowPojo specialShowPojo = specialShowService.findSpecialShowById(specialId);
    if (specialShowPojo != null) {
      userBrandId = specialShowPojo.getUserBrandId();
    }
    int count = 0;
    if (page == null) {
      page = new Pager();
    }
    if (uid != null && userBrandId != null) {
      ProductPojo product = new ProductPojo();

      /*
       * Map<String, Object> param = new HashMap<String, Object>(); param.put("specialId",
       * specialId); List<SpecialProductPojo> list =
       * specialProductService.findSpecialProductList(param); pids = new String[list.size()]; for
       * (int i = 0; i < list.size(); i++) { pids[i] = list.get(i).getProductId().toString(); }
       * product.setFilter(pids);
       */

      /*
       * Map<String, Object> param = new HashMap<String, Object>(); param.put("type", 3);
       * List<ActivityGoodsPojo> list = activityGoodsService.checkActivityGoodsByPid(param); if
       * (list.size() != 0) { pids = new String[list.size()]; for (int i = 0; i < list.size(); i++)
       * { pids[i] = list.get(i).getProductId().toString(); } product.setFilter(pids); }
       */
      product.setFilter(new String[] {"1"});
      product.setUserId(uid);
      product.setUserBrandId(userBrandId);
      if (productPojo != null) {
        product.setProductName(productPojo.getProductName());
        product.setProductNum(productPojo.getProductNum());
      }
      count = productService.getAgencyProductAllCount(product);
    }
    page.setRowCount(count);
    return SUCCESS;
  }

  /**
   * 专场预览.
   * 
   * @return
   * @throws Exception
   */
  public String goMartShowPreview() throws Exception {
    if (specialId != null) {
      specialShowPojo = specialShowService.findSpecialShowById(specialId);
      if (specialShowPojo != null) {
        String discountContext =
            sellerService.transJsonToDiscountStr(specialShowPojo.getDiscountType(),
                specialShowPojo.getDiscountContext());
        specialShowPojo.setDiscountContext(discountContext);
        userBrandPojo = userBrandService.findUserBrandById(specialShowPojo.getUserBrandId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("specialId", specialId);
        List<SpecialProductPojo> specialProductList =
            specialProductService.findSpecialProductList(map);
        ActionContext ac = ActionContext.getContext();
        ac.put("specialProductList", specialProductList);
      }
    }
    return SUCCESS;
  }



  /**
   * 活动专场提交审核.
   * 
   * @return
   * @throws Exception
   */
  public String submitMartShow() throws Exception {
    this.result = "1";
    if (specialId != null) {
      try {
        SpecialShowPojo specialShowPojo = specialShowService.findSpecialShowById(specialId);
        if (specialShowPojo != null) {
          // 检查活动商品是否添加
          Map<String, Object> param = new HashMap<String, Object>();
          param.put("proStatus", 1);
          param.put("timeId", specialShowPojo.getActivityId());
          int checknum = activityGoodsService.findActivityGoodsCount(param);
          if (checknum > 0) {
            // 1-待审核
            specialShowPojo.setStatus(1l);
            specialShowService.updateSpecialShow(specialShowPojo);
          } else {
            this.result = "0";
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        this.result = "0";
      }
    }
    return SUCCESS;
  }

  /**
   * 活动商品提交审核. pid:专场商品ID
   * 
   * @return
   * @throws Exception
   */
  public String submitMartShowProduct() throws Exception {
    this.result = "1";
    if (specialId != null) {
      try {
        SpecialShowPojo specialShowPojo = specialShowService.findSpecialShowById(specialId);
        SpecialProductPojo specialProduct = null;
        ActivityGoodsPojo activityGood = null;
        if (specialShowPojo != null && pid != null) {
          specialProduct = specialProductService.findSpecialProductById(pid);
          if (specialProduct != null) {
            activityGood = activityGoodsService.findActivityGoodsById(specialProduct.getGoodId());
            if (activityGood != null && activityGood.getStatus() != null
                && activityGood.getStatus() == 1) {
              this.result = "0";
            } else {
              // 0-待审核 1-审核通过 2-待提交
              activityGood.setStatus(0);
              activityGoodsService.updateActivityGoods(activityGood);
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        this.result = "0";
      }
    }
    return SUCCESS;
  }

  public String submitMartShowWeb() throws Throwable {
    if (specialShowPojo != null) {
      if (banner != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/specialShow")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/specialShow/", banner);
        specialShowPojo.setBanner(file_name);
      }
      if (specialShowPojo.getId() == null) {
        UserBrandPojo userBrand =
            userBrandService.findUserBrandById(specialShowPojo.getUserBrandId());
        String specialName = userBrand == null ? "专场特卖" : userBrand.getBrandName() + "专场特卖";
        ActivityTimePojo activityTimePojo = new ActivityTimePojo();
        activityTimePojo.setTitle(specialName);
        // 1-APP 2-微商城
        activityTimePojo.setChannel(1);
        activityTimePojo.setCreateBy(1L);
        activityTimePojo.setType(3);
        activityTimeService.insertActivityTime(activityTimePojo);
        Long t = activityTimePojo.getId();
        specialShowPojo.setActivityId(t);
        specialShowPojo.setName(specialName);

        if (specialShowPojo.getDiscountType() != null && specialShowPojo.getDiscountType() == 1) {
          // 满减
          if (conditionmj != null && conditionmj.length > 0 && denominationmj != null
              && denominationmj.length == conditionmj.length) {
            specialShowPojo.setDiscountContext(sellerService.transDiscountToJson(conditionmj,
                denominationmj));
          }

        } else if (specialShowPojo.getDiscountType() != null
            && specialShowPojo.getDiscountType() == 2) {
          // 满折
          if (conditionmz != null && conditionmz.length > 0 && denominationmz != null
              && denominationmz.length == conditionmz.length) {
            specialShowPojo.setDiscountContext(sellerService.transDiscountToJson(conditionmz,
                denominationmz));
          }
        } else {
          specialShowPojo.setDiscountType(0);
        }
        // 多选频道，以":"分隔
        if (channelIds != null && channelIds.length > 0) {
          specialShowPojo.setChannelId(":" + StringUtils.join(channelIds, ":") + ":");
        } else {
          specialShowPojo.setChannelId("");
        }

        // 0-待提交
        specialShowPojo.setStatus(0l);
        Long userId = userBrand == null ? null : userBrand.getUserId();
        specialShowPojo.setUserId(userId);
        specialShowPojo.setCreateBy(userId);
        specialShowPojo.setUpdateBy(userId);

        specialShowService.insertSpecialShow(specialShowPojo);
      } else {
        SpecialShowPojo specialShow = new SpecialShowPojo();
        specialShow.setId(specialShowPojo.getId());
        specialShow.setTitle(specialShowPojo.getTitle());
        specialShow.setBanner(specialShowPojo.getBanner());
        specialShow.setMainCategory1(specialShowPojo.getMainCategory1());
        specialShow.setMainCategory2(specialShowPojo.getMainCategory2());
        specialShow.setAgeRange(specialShowPojo.getAgeRange());

        if (specialShowPojo.getDiscountType() != null && specialShowPojo.getDiscountType() == 1) {
          // 满减
          if (conditionmj != null && conditionmj.length > 0 && denominationmj != null
              && denominationmj.length == conditionmj.length) {
            specialShowPojo.setDiscountContext(sellerService.transDiscountToJson(conditionmj,
                denominationmj));
          }
        } else if (specialShowPojo.getDiscountType() != null
            && specialShowPojo.getDiscountType() == 2) {
          // 满折
          if (conditionmz != null && conditionmz.length > 0 && denominationmz != null
              && denominationmz.length == conditionmz.length) {
            specialShowPojo.setDiscountContext(sellerService.transDiscountToJson(conditionmz,
                denominationmz));
          }
        } else {
          specialShowPojo.setDiscountType(0);
          specialShowPojo.setDiscountContext("");
        }
        if (channelIds != null && channelIds.length > 0) {
          specialShowPojo.setChannelId(":" + StringUtils.join(channelIds, ":") + ":");
        } else {
          specialShowPojo.setChannelId("");
        }
        specialShowService.updateSpecialShow(specialShowPojo);
      }
    }
    return SUCCESS;
  }

}
