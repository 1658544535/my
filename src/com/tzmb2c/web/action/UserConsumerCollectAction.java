package com.tzmb2c.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import maowu.framework.utils.web.SuperAction;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.CSVUtil;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.utils.ZipUtil;
import com.tzmb2c.utils.export.excel.ExcelProcessor;
import com.tzmb2c.web.pojo.ProductFocusImagesPojo;
import com.tzmb2c.web.pojo.ProductImagesPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserConsumerCollectPojo;
import com.tzmb2c.web.service.ProductFocusImagesService;
import com.tzmb2c.web.service.ProductImagesService;
import com.tzmb2c.web.service.ProductPrimaryImagesService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.UserConsumerCollectService;

/**
 * 
 * @author EricChen
 * 
 */
public class UserConsumerCollectAction extends SuperAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private UserConsumerCollectService userConsumerCollectService;
  @Autowired
  private ProductPrimaryImagesService productPrimaryImagesService;
  @Autowired
  private ProductImagesService productImagesService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductFocusImagesService productFocusImagesService;

  private UserConsumerCollectPojo userConsumerCollectPojo;
  private String result;
  private String[] tids;

  public String addFenxiao() throws Exception {
    try {
      SysLoginPojo user = UserUtil.getWebUser();
      if (user == null) {
        this.result = "3";
      } else {
        if (!"3".equals(user.getType())) {
          this.result = "5";
        } else {
          userConsumerCollectPojo.setUserId(user.getId());
          UserConsumerCollectPojo collect =
              userConsumerCollectService.findCollect(userConsumerCollectPojo);
          if (collect != null) {
            this.result = "4";
          } else {
            ProductPojo productPojo = new ProductPojo();
            productPojo.setId(userConsumerCollectPojo.getProductId());
            productPojo = productService.findProduct(productPojo);
            userConsumerCollectPojo.setSuserId(productPojo.getUserId());
            userConsumerCollectPojo.setUserId(user.getId());
            userConsumerCollectPojo.setIsExport("0");
            userConsumerCollectPojo.setCreateBy(user.getId());
            userConsumerCollectPojo.setCreateDate(new Date());
            userConsumerCollectService.insertUserConsumerCollect(userConsumerCollectPojo);
            this.result = "1";
          }
        }
      }
    } catch (Exception e) {
      this.result = "2";
    }
    return SUCCESS;
  }

  /* 前端 - 删除 */
  public String deluserConsumerCollectWeb() throws Exception {
    userConsumerCollectService.delUserConsumerCollect(userConsumerCollectPojo.getId());
    FileUtil.alertMessageBySkip("删除成功！", "goConsumerProductWeb.do");
    return null;
  }

  public String goConsumerProductWeb() throws SQLException {
    SysLoginPojo user = UserUtil.getWebUser();
    if (user == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
    }
    userConsumerCollectPojo = new UserConsumerCollectPojo();
    userConsumerCollectPojo.setUserId(user.getId());
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    page.setRowCount(userConsumerCollectService.findCountByUserId(user.getId()));
    List<UserConsumerCollectPojo> consumerProductList =
        userConsumerCollectService.findCollect2(userConsumerCollectPojo, page);
    ActionContext ac = ActionContext.getContext();
    ac.put("consumerProductList", consumerProductList);
    return SUCCESS;
  }

  public void exportZip() throws IOException {
    SysLoginPojo user = UserUtil.getWebUser();
    if (user == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
      return;
    }
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
    String fileName = df.format(new Date()) + "-" + user.getId();
    // String fileName = user.getId()+String.valueOf(new Date().getTime());
    if (tids == null || tids.length < 1) {
      FileUtil.alertMessageBySkip("请先勾选需要导出数据的产品", "goConsumerProductWeb.do");
      return;
    }
    List<ProductPojo> productList = new ArrayList<ProductPojo>();
    ProductPojo product;
    String filePath = ServletActionContext.getServletContext().getRealPath("/temp/" + fileName);
    for (int i = 0; i < tids.length; i++) {
      ProductPojo productInfo = new ProductPojo();
      productInfo.setId(Long.valueOf(tids[i]));
      productInfo = productService.findProduct(productInfo);
      // 获取产品原生图片
      // List<ProductPrimaryImagesPojo> imagesList =
      // productPrimaryImagesService.getPrimaryImagesByProduct(productInfo.getId());
      List<ProductImagesPojo> imagesList = productImagesService.productForId(productInfo.getId());
      // 获取产品焦点图
      List<ProductFocusImagesPojo> imagesPojos =
          productFocusImagesService.getProductFocusImagesByPid(productInfo.getId());
      // 获得产品信息并添加到集合中
      product = new ProductPojo();
      product.setId(Long.valueOf(tids[i]));
      productList.add(productService.findProduct(product));

      String productPath =
          ServletActionContext.getServletContext().getRealPath(
              "/temp/" + fileName + "/" + productInfo.getProductNum() + "/images");
      String productPath2 =
          ServletActionContext.getServletContext().getRealPath(
              "/temp/" + fileName + "/" + productInfo.getProductNum() + "/focusImages");
      // 创建目标文件夹
      new File(productPath).mkdirs();
      new File(productPath2).mkdirs();
      for (ProductImagesPojo image : imagesList) {
        String oldPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/product")
                + File.separator + image.getImages();
        String newPath =
            ServletActionContext.getServletContext().getRealPath(
                "/temp/" + fileName + "/" + productInfo.getProductNum())
                + "/images" + File.separator + image.getSorting() + "-" + image.getImages();
        FileUtil.copyFile(oldPath, newPath);
      }
      for (ProductFocusImagesPojo productFocusImagesPojo : imagesPojos) {
        String oldPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/productFocusImages")
                + File.separator + productFocusImagesPojo.getImages();
        String newPath =
            ServletActionContext.getServletContext().getRealPath(
                "/temp/" + fileName + "/" + productInfo.getProductNum())
                + "/focusImages"
                + File.separator
                + productFocusImagesPojo.getSorting()
                + "-"
                + productFocusImagesPojo.getImages();
        FileUtil.copyFile(oldPath, newPath);
      }
    }
    // 遍历产品集合添加到CSV文件中
    List<String> list = new ArrayList<String>();
    list.add("version 1.00");
    list.add("title,cid,seller_cids,stuff_status,location_state,location_city,item_type,price,auction_increment,num,valid_thru,freight_payer,post_fee,ems_fee,express_fee,has_invoice,has_warranty,approve_status,has_showcase,list_time,description,cateProps,postage_id,has_discount,modified,upload_fail_msg,picture_status,auction_point,picture,video,skuProps,inputPids,inputValues,outer_id,propAlias,auto_fill,num_id,local_cid,navigation_type,user_name,syncStatus,is_lighting_consigment,is_xinpin,foodparame,features,buyareatype,global_stock_type,global_stock_country,sub_stock_type,item_size,item_weight,sell_promise,custom_design_flag,wireless_desc,barcode,sku_barcode,newprepay,subtitle,cpv_memo,input_custom_cpv,qualification,add_qualification,o2o_bind_service");
    list.add("宝贝名称,宝贝类目,店铺类目,新旧程度,省,城市,出售方式,宝贝价格,加价幅度,宝贝数量,有效期,运费承担,平邮,EMS,快递,发票,保修,放入仓库,橱窗推荐,开始时间,宝贝描述,宝贝属性,邮费模版ID,会员打折,修改时间,上传状态,图片状态,返点比例,新图片,视频,销售属性组合,用户输入ID串,用户输入名-值对,商家编码,销售属性别名,代充类型,数字ID,本地ID,宝贝分类,用户名称,宝贝状态,闪电发货,新品,食品专项,尺码库,采购地,库存类型,国家地区,库存计数,物流体积,物流重量,退换货承诺,定制工具,无线详情,商品条形码,sku 条形码,7天退货,宝贝卖点,属性值备注,自定义属性值,商品资质,增加商品资质,关联线下服务");
    for (ProductPojo p : productList) {
      String name = p.getProductName();
      String price = p.getDistributionPrice().toString();
      String brand = p.getBrandName();
      String w = p.getWeight();
      list.add("" + name + ",50013172,,1,广东,汕头,1," + price
          + ",0,1,7,2,8.67704E-39,2.8026E-45,0,0,0,1,0," + df.format(new Date()) + ",,," + ""
          + ",0," + df.format(new Date()) + ",200,,0,,,,," + brand + ",,,0,,0,0,,1,,,,,0,-1,,2,,"
          + w + ",0,,,,,1,,,,,1,");
    }
    File file = new File("C:\\csv.csv");
    if (!file.isFile()) {
      file.createNewFile();
    }
    CSVUtil.exportCsv(file, list);
    FileUtil.copyFile("C:\\csv.csv",
        ServletActionContext.getServletContext().getRealPath("/temp/" + fileName + "/淘宝数据包.csv"));
    // 复制完删除生成的CSV文件
    File delfilecsv = new File("C:\\csv.csv");
    // 路径为文件且不为空则进行删除
    if (delfilecsv.isFile() && delfilecsv.exists()) {
      delfilecsv.delete();
    }
    HttpServletResponse response = ServletActionContext.getResponse();
    // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
    response.setContentType("application/octet-stream");

    String zipPath =
        ServletActionContext.getServletContext().getRealPath("/temp") + File.separator + fileName
            + ".zip";
    try {
      ZipUtil.zip(filePath, zipPath);
    } catch (Exception e) {
      e.printStackTrace();
    }

    response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ".zip");
    ServletOutputStream out;
    try {
      FileInputStream fileInputStream = new FileInputStream(zipPath);
      out = response.getOutputStream();
      int i = 0;
      while ((i = fileInputStream.read()) != -1) {
        out.write(i);
      }
      fileInputStream.close();
      out.close();

      // 下载完删除生成的压缩文件
      File delfile = new File(zipPath);
      // 路径为文件且不为空则进行删除
      if (delfile.isFile() && delfile.exists()) {
        delfile.delete();
      }

      FileUtil.delFolder(filePath);
    } catch (IOException e) {

      e.printStackTrace();
    }
  }

  /**
   * 导出产品EXCEL
   */
  public String getConsumerProductExcel() throws Exception {
    SysLoginPojo user = UserUtil.getWebUser();
    if (user == null) {
      FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do");
    }
    String simple = "yyyy-MM-dd HH:mm:ss";
    DateFormat df = new SimpleDateFormat(simple);
    String fileName = df.format(new Date()) + "-" + user.getId();
    // String fileName = user.getId()+String.valueOf(new Date().getTime());
    if (tids == null || tids.length < 1) {
      FileUtil.alertMessageBySkip("请先勾选需要导出数据的产品", "goConsumerProductWeb.do");
      return null;
    }
    List<ProductPojo> productList = new ArrayList<ProductPojo>();
    ProductPojo product;
    for (int i = 0; i < tids.length; i++) {
      product = new ProductPojo();
      product.setId(Long.valueOf(tids[i]));
      productList.add(productService.findProduct(product));
    }
    this.downloadFileName = fileName + ".xls";
    ExcelProcessor<ProductPojo> e =
        new ExcelProcessor<ProductPojo>(productList, ProductPojo.class, "产品");
    this.inputStream = e.generateExcelSteam();
    return SUCCESS;
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

  public UserConsumerCollectPojo getUserConsumerCollectPojo() {
    return userConsumerCollectPojo;
  }

  public void setUserConsumerCollectPojo(UserConsumerCollectPojo userConsumerCollectPojo) {
    this.userConsumerCollectPojo = userConsumerCollectPojo;
  }
}
