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
import com.tzmb2c.utils.CompressPicture;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.ProductStockPojo;
import com.tzmb2c.web.pojo.SkuAttributePojo;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.ProductStockService;
import com.tzmb2c.web.service.SkuAttributeService;
import com.tzmb2c.web.service.SysDictService;

/**
 * @author EricChen
 */
public class ProductSkuLinkAction extends SuperAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private ProductStockService productStockService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private SkuAttributeService skuAttributeService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private ProductService productService;

  private List<ProductStockPojo> productStockList;
  private List<ProductSkuLinkPojo> productSkuLinkList;
  private SkuAttributePojo skuAttributePojo, skuAttributePojo2;
  private ProductSkuLinkPojo productSkuLinkPojo;
  private String result;
  private String[] tids;
  private String[] min;
  private String[] max;
  private String[] prices;
  private File upfile, upfile2;

  public List<ProductStockPojo> getProductStockList() {
    return productStockList;
  }

  public void setProductStockList(List<ProductStockPojo> productStockList) {
    this.productStockList = productStockList;
  }

  public SkuAttributePojo getSkuAttributePojo() {
    return skuAttributePojo;
  }

  public void setSkuAttributePojo(SkuAttributePojo skuAttributePojo) {
    this.skuAttributePojo = skuAttributePojo;
  }

  public SkuAttributePojo getSkuAttributePojo2() {
    return skuAttributePojo2;
  }

  public void setSkuAttributePojo2(SkuAttributePojo skuAttributePojo2) {
    this.skuAttributePojo2 = skuAttributePojo2;
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

  public List<ProductSkuLinkPojo> getProductSkuLinkList() {
    return productSkuLinkList;
  }

  public void setProductSkuLinkList(List<ProductSkuLinkPojo> productSkuLinkList) {
    this.productSkuLinkList = productSkuLinkList;
  }

  public ProductSkuLinkPojo getProductSkuLinkPojo() {
    return productSkuLinkPojo;
  }

  public void setProductSkuLinkPojo(ProductSkuLinkPojo productSkuLinkPojo) {
    this.productSkuLinkPojo = productSkuLinkPojo;
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

  public String getProductSkuLinkCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    if (productSkuLinkPojo != null) {
      page.setRowCount(productSkuLinkService.getProductSkuLinkCount(productSkuLinkPojo));
    } else {
      page.setRowCount(0);
    }
    return SUCCESS;
  }

  public String getProductSkuLinkAll() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    if (productSkuLinkPojo != null) {
      productSkuLinkList = productSkuLinkService.getProductSkuLinkAll(productSkuLinkPojo, page);
    }
    JSONArray json = JSONArray.fromObject(productSkuLinkList);
    page.setResult(json.toString());
    return SUCCESS;

  }

  public String goproductSkuLinkAdd() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("productId", productSkuLinkPojo.getProductId());
    map.put("attribute", "颜色");
    Map<String, Object> map1 = new HashMap<String, Object>();
    map1.put("productId1", productSkuLinkPojo.getProductId());
    map1.put("attribute", "规格");
    ActionContext ac = ActionContext.getContext();
    ac.put("colorAttribute", skuAttributeService.getSkuAttributeByAttribute(map));
    ac.put("formatAttribute", skuAttributeService.getSkuAttributeByAttribute(map1));
    return SUCCESS;
  }

  public void productSkuLinkAdd() throws Exception {
    String rtnUrl =
        "productSkuLinkPojo.type=" + productSkuLinkPojo.getType()
            + "&productSkuLinkPojo.activityId=" + productSkuLinkPojo.getActivityId()
            + "&productSkuLinkPojo.productId=" + productSkuLinkPojo.getProductId();
    if (productSkuLinkPojo.getSkuColorId() == null && productSkuLinkPojo.getSkuFormatId() == null) {
      FileUtil.alertMessageBySkip("添加失败，颜色/规格不能为空！", "goproductSkuLinkAdd.do?" + rtnUrl);
      return;
    }
    int count = productSkuLinkService.getProductSkuLinkCount(productSkuLinkPojo);
    if (0 < count) {
      FileUtil.alertMessageBySkip("添加失败，已存在相同SKU！", "goproductSkuLinkAdd.do?" + rtnUrl);
      return;
    }
    productSkuLinkPojo.setStatus(0);
    /*
     * if (productSkuLinkPojo.getPrice() == null || "".equals(productSkuLinkPojo.getPrice())) {
     * ProductPojo productPojo = new ProductPojo();
     * productPojo.setId(productSkuLinkPojo.getProductId()); productPojo =
     * productService.findProduct(productPojo);
     * productSkuLinkPojo.setPrice(productPojo.getLadderPrice()); }
     */
    productSkuLinkService.addSkuLinkByProductId(productSkuLinkPojo);
    FileUtil.alertMessageBySkip("添加SKU成功！", "productSkuLinkManage.do?" + rtnUrl);
  }

  public String goFindProductSkuLink() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("productId", productSkuLinkPojo.getProductId());
    map.put("attribute", "颜色");
    Map<String, Object> map1 = new HashMap<String, Object>();
    map1.put("productId1", productSkuLinkPojo.getProductId());
    map1.put("attribute", "规格");
    ActionContext ac = ActionContext.getContext();
    ac.put("colorAttribute", skuAttributeService.getSkuAttributeByAttribute(map));
    ac.put("formatAttribute", skuAttributeService.getSkuAttributeByAttribute(map1));
    ac.put("status", sysDictService.getSysDictListByType("sys_dict_status"));
    productSkuLinkPojo = productSkuLinkService.findProductSkuLink(productSkuLinkPojo);
    return SUCCESS;
  }

  public void productSkuLinkUpdate() throws Exception {
    String rtnUrl =
        "productSkuLinkManage.do?productSkuLinkPojo.type=" + productSkuLinkPojo.getType()
            + "&productSkuLinkPojo.activityId=" + productSkuLinkPojo.getActivityId()
            + "&productSkuLinkPojo.productId=" + productSkuLinkPojo.getProductId();
    // ProductSkuLinkPojo oldSku = productSkuLinkService.findProductSkuLink(productSkuLinkPojo);
    ProductSkuLinkPojo skuLink = new ProductSkuLinkPojo();
    skuLink.setId(productSkuLinkPojo.getId());
    ProductSkuLinkPojo oldSku = productSkuLinkService.findProductSkuLink(skuLink);
    Long oldColor = oldSku.getSkuColorId();
    Long oldFormat = oldSku.getSkuFormatId();
    Long newColor = productSkuLinkPojo.getSkuColorId();
    Long newFormat = productSkuLinkPojo.getSkuFormatId();
    if (oldColor == null && newColor != null || newColor != null && oldColor != null
        && !newColor.equals(oldColor) || oldFormat == null && newFormat != null
        || oldFormat != null && newFormat != null && !newFormat.equals(oldFormat)) {
      ProductSkuLinkPojo temp = new ProductSkuLinkPojo();
      temp.setProductId(productSkuLinkPojo.getProductId());
      temp.setSkuColorId(productSkuLinkPojo.getSkuColorId());
      temp.setSkuFormatId(productSkuLinkPojo.getSkuFormatId());
      int count = productSkuLinkService.getProductSkuLinkCount(temp);
      if (0 < count) {
        FileUtil.alertMessageBySkip("修改失败，该SKU已存在！", rtnUrl);
        return;
      }
    }
    productSkuLinkService.productSkuLinkUpdate(productSkuLinkPojo);
    FileUtil.alertMessageBySkip("修改成功！", rtnUrl);
    return;
  }

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
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  public String productSkuLinkDeleteId() {
    if (tids != null) {
      ProductSkuLinkPojo productSku = null;
      for (String tid : tids) {
        try {
          productSku = delCheckSkuColor(Long.parseLong(tid));
          productSkuLinkService.deleProductSkuLink(Long.parseLong(tid));
          if (productSku != null && productSku.getSkuColorId() != null) {
            skuAttributeService.deleSkuAttribute(productSku.getSkuColorId());
          }
          if (productSku != null && productSku.getSkuFormatId() != null) {
            skuAttributeService.deleSkuAttribute(productSku.getSkuFormatId());
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      FileUtil.alertMessageBySkip("删除成功！",
          "productSkuLinkManage.do?productSkuLinkPojo.type=" + productSkuLinkPojo.getType()
              + "&productSkuLinkPojo.activityId=" + productSkuLinkPojo.getActivityId()
              + "&productSkuLinkPojo.productId=" + productSkuLinkPojo.getProductId());
    } else {
      FileUtil.alertMessageBySkip("删除失败！",
          "productSkuLinkManage.do?productSkuLinkPojo.type=" + productSkuLinkPojo.getType()
              + "&productSkuLinkPojo.activityId=" + productSkuLinkPojo.getActivityId()
              + "&productSkuLinkPojo.productId=" + productSkuLinkPojo.getProductId());
    }

    return null;
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

  public String checkProductSkuLink() throws SQLException {
    try {
      productSkuLinkPojo = productSkuLinkService.findProductSkuLink(productSkuLinkPojo);
      if (productSkuLinkPojo.getSkuColorId() == null || productSkuLinkPojo.getSkuFormatId() == null) {
        this.result = "0";
      } else {
        productSkuLinkService.checkProductSkuLink(productSkuLinkPojo.getId());
        this.result = "1";
      }
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  public String uncheckProductSkuLink() throws SQLException {
    try {
      productSkuLinkService.uncheckProductSkuLink(productSkuLinkPojo.getId());
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  public String productSkuLinkCheckId() {
    if (tids != null) {
      productSkuLinkService.productSkuLinkCheckId(tids);
      FileUtil.alertMessageBySkip("审核成功！",
          "productSkuLinkManage.do?productSkuLinkPojo.type=" + productSkuLinkPojo.getType()
              + "&productSkuLinkPojo.activityId=" + productSkuLinkPojo.getActivityId()
              + "&productSkuLinkPojo.productId=" + productSkuLinkPojo.getProductId());
    } else {
      FileUtil.alertMessageBySkip("审核失败！",
          "productSkuLinkManage.do?productSkuLinkPojo.type=" + productSkuLinkPojo.getType()
              + "&productSkuLinkPojo.activityId=" + productSkuLinkPojo.getActivityId()
              + "&productSkuLinkPojo.productId=" + productSkuLinkPojo.getProductId());
    }

    return null;
  }

  public String goAddSkuColor() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增颜色/规格
   * 
   * @throws Throwable
   */
  public void addSkuColor() throws Throwable {
    String url =
        "goAddSkuColor.do?productSkuLinkPojo.type=" + productSkuLinkPojo.getType()
            + "&productSkuLinkPojo.activityId=" + productSkuLinkPojo.getActivityId()
            + "&productSkuLinkPojo.productId=" + productSkuLinkPojo.getProductId();

    /*
     * List<ProductSkuLinkPojo> pList =
     * productSkuLinkService.getProductSkuLinkAll(productSkuLinkPojo, null); for (ProductSkuLinkPojo
     * pojo : pList) { if (pojo.getColorValue() != null &&
     * pojo.getColorValue().equals(skuAttributePojo.getValue())) {
     * FileUtil.alertMessageBySkip("添加失败，该颜色已存在！", url); return; } if (pojo.getFormatValue() != null
     * && pojo.getFormatValue().equals(skuAttributePojo2.getValue())) {
     * FileUtil.alertMessageBySkip("添加失败，该规格已存在！", url); return; } }
     */
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("value", skuAttributePojo.getValue().trim());
    map.put("productId", productSkuLinkPojo.getProductId());
    List<SkuAttributePojo> SkuAttributePojos = skuAttributeService.getSkuAttribute(map);
    if (SkuAttributePojos.size() != 0) {
      FileUtil.alertMessageBySkip("添加失败，该颜色已存在！", url);
      return;
    }
    Map<String, Object> map2 = new HashMap<String, Object>();
    map2.put("value", skuAttributePojo2.getValue().trim());
    map2.put("productId", productSkuLinkPojo.getProductId());
    List<SkuAttributePojo> SkuAttributePojos2 = skuAttributeService.getSkuAttribute(map2);
    if (SkuAttributePojos2.size() != 0) {
      FileUtil.alertMessageBySkip("添加失败，该规格已存在！", url);
      return;
    }
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
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 150, 150, true);
      skuAttributePojo.setImage(file_name);
    } else {
      skuAttributePojo.setImage("");
    }
    if (upfile2 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile);
      // 图片压缩
      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
              + File.separator;
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 150, 150, true);
      skuAttributePojo2.setImage(file_name);
    } else {
      skuAttributePojo2.setImage("");
    }
    skuAttributePojo.setAttribute("颜色");
    skuAttributePojo2.setAttribute("规格");
    if (skuAttributePojo != null && skuAttributePojo.getValue() != null
        && !"".equals(skuAttributePojo.getValue().trim())) {
      skuAttributePojo.setProductId(productSkuLinkPojo.getProductId());
      skuAttributeService.insertSkuAttribute(skuAttributePojo);
      Long i = skuAttributePojo.getId();
      productSkuLinkPojo.setSkuColorId(i);
    }
    if (skuAttributePojo2 != null && skuAttributePojo2.getValue() != null
        && !"".equals(skuAttributePojo2.getValue().trim())) {
      skuAttributePojo2.setProductId(productSkuLinkPojo.getProductId());
      skuAttributeService.insertSkuAttribute(skuAttributePojo2);
      Long t = skuAttributePojo2.getId();
      productSkuLinkPojo.setSkuFormatId(t);
    }
    if (productSkuLinkPojo != null) {
      productSkuLinkPojo.setStatus(0);
      productSkuLinkService.addSkuLinkByProductId(productSkuLinkPojo);
    }
    url =
        "goFindProductSkuLink.do?productSkuLinkPojo.id=" + productSkuLinkPojo.getId()
            + "&productSkuLinkPojo.type=" + productSkuLinkPojo.getType()
            + "&productSkuLinkPojo.activityId=" + productSkuLinkPojo.getActivityId()
            + "&productSkuLinkPojo.productId=" + productSkuLinkPojo.getProductId();
    FileUtil.alertMessageBySkip("添加SKU颜色/规格成功！", url);
  }

  public String goSkuAttributeUpdate() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("productId", productSkuLinkPojo.getProductId());
    map.put("attribute", "颜色");
    Map<String, Object> map1 = new HashMap<String, Object>();
    map1.put("productId1", productSkuLinkPojo.getProductId());
    map1.put("attribute", "规格");
    ActionContext ac = ActionContext.getContext();
    ac.put("colorAttribute", skuAttributeService.getSkuAttributeByAttribute(map));
    ac.put("formatAttribute", skuAttributeService.getSkuAttributeByAttribute(map1));
    return SUCCESS;
  }

  public void skuAttributeUpdate() throws Throwable {
    String url =
        "goSkuAttributeUpdate.do?productSkuLinkPojo.type=" + productSkuLinkPojo.getType()
            + "&productSkuLinkPojo.activityId=" + productSkuLinkPojo.getActivityId()
            + "&productSkuLinkPojo.productId=" + productSkuLinkPojo.getProductId();

    SkuAttributePojo oldAttr = skuAttributeService.getSkuAttributeById(skuAttributePojo.getId());
    SkuAttributePojo oldAttr2 = skuAttributeService.getSkuAttributeById(skuAttributePojo2.getId());
    String newValue = skuAttributePojo.getValue() == null ? "" : skuAttributePojo.getValue().trim();
    String newValue2 =
        skuAttributePojo2.getValue() == null ? "" : skuAttributePojo2.getValue().trim();
    ProductSkuLinkPojo psl = new ProductSkuLinkPojo();
    psl.setProductId(productSkuLinkPojo.getProductId());
    List<ProductSkuLinkPojo> pslList = productSkuLinkService.getProductSkuLinkAll(psl, null);
    for (int i = 0; i < pslList.size(); i++) {
      String temp = pslList.get(i).getColorValue();
      String temp2 = pslList.get(i).getFormatValue();
      if (temp != null && temp.equals(newValue) && !newValue.equals(oldAttr.getValue())) {
        FileUtil.alertMessageBySkip("修改失败，颜色名已存在！", url);
        return;
      }
      if (temp2 != null && temp2.equals(newValue2) && !newValue2.equals(oldAttr2.getValue())) {
        FileUtil.alertMessageBySkip("修改失败，规格名已存在！", url);
        return;
      }
    }
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
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 150, 150, true);
      skuAttributePojo.setImage(file_name);
    } else {
      skuAttributePojo.setImage("");
    }
    if (upfile2 != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/product/", upfile2);
      // 图片压缩
      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/product/small")
              + File.separator;
      cp.compressPic(upfile2, compressPath, "upfiles/product/small/", file_name, 150, 150, true);
      skuAttributePojo2.setImage(file_name);
    } else {
      skuAttributePojo2.setImage("");
    }
    skuAttributePojo.setAttribute("颜色");
    skuAttributePojo2.setAttribute("规格");

    if (skuAttributePojo != null && !"".equals(newValue)) {
      skuAttributeService.skuAttributeUpdateById(skuAttributePojo);
    }
    if (skuAttributePojo2 != null && !"".equals(newValue2)) {
      skuAttributeService.skuAttributeUpdateById(skuAttributePojo2);
    }
    url =
        "productSkuLinkManage.do?productSkuLinkPojo.type=" + productSkuLinkPojo.getType()
            + "&productSkuLinkPojo.activityId=" + productSkuLinkPojo.getActivityId()
            + "&productSkuLinkPojo.productId=" + productSkuLinkPojo.getProductId();
    FileUtil.alertMessageBySkip("修改SKU颜色/规格成功！", url);
  }

  public String goAddSkuFormat() throws Exception {
    return SUCCESS;
  }

  public void addSkuFormat() throws Throwable {
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
      cp.compressPic(upfile, compressPath, "upfiles/product/small/", file_name, 150, 150, true);
      skuAttributePojo.setImage(file_name);
    } else {
      skuAttributePojo.setImage("");
    }
    skuAttributePojo.setAttribute("规格");
    skuAttributeService.insertSkuAttribute(skuAttributePojo);
    Long i = skuAttributePojo.getId();
    productSkuLinkPojo.setSkuFormatId(i);
    if (productSkuLinkPojo.getPrice() == null || "".equals(productSkuLinkPojo.getPrice())) {
      ProductPojo productPojo = new ProductPojo();
      productPojo.setId(productSkuLinkPojo.getProductId());
      productPojo = productService.findProduct(productPojo);
      productSkuLinkPojo.setPrice(productPojo.getDistributionPrice());
    }
    productSkuLinkService.addSkuLinkByProductId(productSkuLinkPojo);
    // Long t =productSkuLinkPojo.getId();
    FileUtil.alertMessageBySkip("添加sku规格成功！",
        "goproductSkuLinkAdd.do?productSkuLinkPojo.type=" + productSkuLinkPojo.getType()
            + "&productSkuLinkPojo.activityId=" + productSkuLinkPojo.getActivityId()
            + "&productSkuLinkPojo.productId=" + productSkuLinkPojo.getProductId());
  }

}
