package com.tzmb2c.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.EleorderPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductSellPojo;
import com.tzmb2c.web.service.EleorderService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductSellService;
import com.tzmb2c.web.service.ProductService;

/**
 * 电商订单Action 2015-4-15
 * 
 * @author chenzhipeng
 */
public class EleorderAction extends SuperAction {
  @Autowired
  private EleorderService eleorderService;
  @Autowired
  private ProductService productService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private ProductSellService productSellService;

  private File importupfile;
  private String beganday;
  private String endday;

  private List<EleorderPojo> eleorderList = null;
  private List<OrderPojo> orderlist = null;
  private String id;
  private List<Integer> testUsers;
  ActionContext ac = null;



  public List<Integer> getTestUsers() {
    return testUsers;
  }

  public void setTestUsers(List<Integer> testUsers) {
    this.testUsers = testUsers;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBeganday() {
    return beganday;
  }

  public void setBeganday(String beganday) {
    this.beganday = beganday;
  }

  public String getEndday() {
    return endday;
  }

  public void setEndday(String endday) {
    this.endday = endday;
  }

  public File getImportupfile() {
    return importupfile;
  }

  public void setImportupfile(File importupfile) {
    this.importupfile = importupfile;
  }

  public void ImportEleExcel() throws Exception {
    // if(upfile!=null){
    // FileInputStream fin = new FileInputStream(upfile);
    // String file_name = "123.xls";
    // String uploadPath =
    // ServletActionContext.getServletContext().getRealPath("/temp")+File.separator+file_name;
    // FileUtil.copyFile(fin, uploadPath);
    // }
    if (importupfile != null) {
      FileInputStream fis = new FileInputStream(importupfile);
      Workbook workBook = Workbook.getWorkbook(fis);
      Sheet sheet = workBook.getSheet(0);
      sheet.getColumns();
      int row = sheet.getRows();// 行
      for (int j = 1; j < row; j++) {
        // 插入ele_order
        Map<String, Object> map = new HashMap<String, Object>();
        if (sheet.getCell(0, j).getContents() != null && sheet.getCell(0, j).getContents() != "") {
          map.put("orderNo", sheet.getCell(0, j).getContents());
        }
        if (sheet.getCell(1, j).getContents() != null && sheet.getCell(1, j).getContents() != "") {
          map.put("productId", sheet.getCell(1, j).getContents());
        }
        if (sheet.getCell(2, j).getContents() != null && sheet.getCell(2, j).getContents() != "") {
          map.put("productName", sheet.getCell(2, j).getContents());
        }
        if (sheet.getCell(3, j).getContents() != null && sheet.getCell(3, j).getContents() != "") {
          map.put("pay", sheet.getCell(3, j).getContents());
        }
        if (sheet.getCell(4, j).getContents() != null && sheet.getCell(4, j).getContents() != "") {
          map.put("num", sheet.getCell(4, j).getContents());
        }
        if (sheet.getCell(5, j).getContents() != null && sheet.getCell(5, j).getContents() != "") {
          DateCell dc = (DateCell) sheet.getCell(5, j);
          Date jxlDate = dc.getDate();
          map.put("createDate", jxlDate);
        }
        if (sheet.getCell(6, j).getContents() != null && sheet.getCell(6, j).getContents() != "") {
          map.put("consignee", sheet.getCell(6, j).getContents());
        }
        if (sheet.getCell(7, j).getContents() != null && sheet.getCell(7, j).getContents() != "") {
          map.put("consigneeAddress", sheet.getCell(7, j).getContents());
        }
        if (sheet.getCell(8, j).getContents() != null && sheet.getCell(8, j).getContents() != "") {
          map.put("consigneePhone", sheet.getCell(8, j).getContents());
        }
        if (sheet.getCell(9, j).getContents() != null && sheet.getCell(9, j).getContents() != "") {
          map.put("weight", sheet.getCell(9, j).getContents());
        }
        if (sheet.getCell(10, j).getContents() != null && sheet.getCell(10, j).getContents() != "") {
          map.put("espressPrice", sheet.getCell(10, j).getContents());
        }
        if (sheet.getCell(11, j).getContents() != null && sheet.getCell(11, j).getContents() != "") {
          map.put("espress", sheet.getCell(11, j).getContents());
        }
        if (sheet.getCell(12, j).getContents() != null && sheet.getCell(12, j).getContents() != "") {
          map.put("espressNo", sheet.getCell(12, j).getContents());
        }
        eleorderService.insertEleorder(map);
        // 判断该商品是否存在 （存在修改销售量）
        if (sheet.getCell(1, j).getContents() != null && sheet.getCell(1, j).getContents() != "") {
          ProductPojo productPojo = new ProductPojo();
          productPojo.setId((long) Integer.parseInt(sheet.getCell(1, j).getContents()));
          productPojo = productService.findProduct(productPojo);
          if (productPojo != null) {
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("id", productPojo.getId());
            map1.put("sellNumber",
                productPojo.getSellNumber() + Integer.parseInt(sheet.getCell(4, j).getContents()));
            productService.updateProductsellNumber(map1);
            ProductSellPojo productSellPojo = new ProductSellPojo();
            productSellPojo.setProductId(productPojo.getId());
            productSellPojo.setSellNumber(productPojo.getSellNumber() + Integer.parseInt(sheet.getCell(4, j).getContents()));
            // 根据商品id查找对应商品当日销售量
            ProductSellPojo productSell = productSellService.getById(productPojo.getId());
            productSellPojo.setDaySell(productSell.getDaySell() + Integer.parseInt(sheet.getCell(4, j).getContents()));
            productSellPojo.setUpdateDate(new Date());
            productSellService.update(productSellPojo);
          }
        }
      }
      fis.close();
      workBook.close();// 记得关闭
      FileUtil.alertMessageBySkip("导入成功", "eleAllorderlist.do");
    } else {
      FileUtil.alertMessageBySkip("导入失败", "eleAllorderlist.do");
    }
  }

  // public String insertEleOrder() throws Exception {
  // return SUCCESS;
  // }
  public String getEleAllorderlistCount() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
    // userOrderDetailPojo=new UserOrderDetailPojo();
    // userOrderDetailPojo.setBeganday(beganday);
    // userOrderDetailPojo.setEndday(endday);
    // int x=userOrderDetailService.userOrderDetailAllCount(userOrderDetailPojo);
    Map<String, Object> elemap = new HashMap<String, Object>();
    if (beganday != "" && endday != "") {
      elemap.put("beganday", beganday);
      elemap.put("endday", endday);
    }
    testUsers = SellerService.getTestUsers();
    if (testUsers != null && testUsers.size() > 0) {
      elemap.put("notuserIds", getTestUsers());
    }
    int x = orderService.orderEleAllListCount(elemap);
    int y = eleorderService.EleorderAllCount(elemap);
    if (x > y) {
      page.setRowCount(x);
    } else {
      page.setRowCount(y);
    }
    ac = ActionContext.getContext();
    ac.put("count", x + y);
    ac.put("taozhumacount", x);
    ac.put("dianshangcount", y);
    return SUCCESS;
  }

  public String getEleAllorderlist() throws SQLException {
    Map<String, Object> map = null;
    Map<String, Object> map1 = null;
    Map<String, Object> eleordermap = new HashMap<String, Object>();
    List list = new ArrayList();
    List list2 = new ArrayList();
    if (page == null) {
      page = new Pager();
    }

    eleordermap.put("pageSize", 10);
    eleordermap.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (beganday != "" && endday != "") {
      eleordermap.put("beganday", beganday);
      eleordermap.put("endday", endday);
    }
    orderlist = orderService.orderEleAllList(eleordermap);
    for (OrderPojo p : orderlist) {
      map = new HashMap<String, Object>();
      map.put("type", "淘竹马订单");
      map.put("id", p.getId());
      map.put("time", p.getCreateDateString());
      map.put("orderNo", p.getOrderNo());
      map.put("pay", p.getAllPrice());
      map.put("count", p.getCount());
      map.put("consignee", p.getConsignee());
      map.put("consigneeAddress", p.getConsigneeAddress());
      map.put("consigneePhone", p.getConsigneePhone());
      list.add(map);
    }
    eleorderList = eleorderService.findEleorderAll(eleordermap);
    for (EleorderPojo p1 : eleorderList) {
      map1 = new HashMap<String, Object>();
      map1.put("type", "电商订单");
      map1.put("id", p1.getOrderNo());
      map1.put("time", p1.getCreateDateStr());
      map1.put("orderNo", p1.getOrderNo());
      map1.put("pay", p1.getAllpay());
      map1.put("count", p1.getAllnum());
      map1.put("consignee", p1.getConsignee());
      map1.put("consigneeAddress", p1.getConsigneeAddress());
      map1.put("consigneePhone", p1.getConsigneePhone());
      list2.add(map1);
    }
    Map<String, Object> elemap = new HashMap<String, Object>();
    if (beganday != "" && endday != "") {
      elemap.put("beganday", beganday);
      elemap.put("endday", endday);
    }
    testUsers = SellerService.getTestUsers();
    if (testUsers != null && testUsers.size() > 0) {
      elemap.put("notuserIds", getTestUsers());
    }
    int x = orderService.orderEleAllListCount(elemap);
    int y = eleorderService.EleorderAllCount(elemap);
    new HashMap<String, Object>();
    if (x > y) {
      page.setRowCount(x);
    } else {
      page.setRowCount(y);
    }
    list.addAll(list2);
    JSONArray json = JSONArray.fromObject(list);
    page.setResult(json.toString());
    ac = ActionContext.getContext();
    ac.put("count", x + y);
    ac.put("taozhumacount", x);
    ac.put("dianshangcount", y);
    return SUCCESS;
  }

  public String dianshangDetail() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("orderNo", id);
    ac = ActionContext.getContext();
    ac.put("eleorderPojo", eleorderService.findEleorderByorderNos(map));
    ac.put("eleorderPojos", eleorderService.findEleorderByorderNo(map));
    return SUCCESS;
  }

  public String taozhumaDetail() throws SQLException {
    long s = Integer.parseInt(id);
    ac = ActionContext.getContext();
    ac.put("orderPojo", orderService.findOrderById(s));
    ac.put("orderDetails", orderDetailService.getOrderDetail(s));
    return SUCCESS;
  }
}
