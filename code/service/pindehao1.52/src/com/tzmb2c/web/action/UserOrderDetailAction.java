package com.tzmb2c.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.UserOrderDetailPojo;
import com.tzmb2c.web.service.EleorderService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.UserOrderDetailService;

/**
 * 商品销售记录，统计
 * 
 * @author creazylee
 * 
 */
public class UserOrderDetailAction extends SuperAction {

  private String[] tids;
  private String type;
  private String result;
  private String beganday;
  private String endday;

  @Autowired
  private UserOrderDetailService userOrderDetailService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private EleorderService eleorderService;
  @Autowired
  private OrderDetailService orderDetailService;

  private UserOrderDetailPojo userOrderDetailPojo;
  private List<UserOrderDetailPojo> productSaleRecordList = null;
  private List<SysDictPojo> statusSysDictList = null;
  private List<OrderPojo> orderPojos;
  private int pageNos;
  private List<Integer> testUsers;


  public List<Integer> getTestUsers() {
    return testUsers;
  }

  public void setTestUsers(List<Integer> testUsers) {
    this.testUsers = testUsers;
  }

  public int getPageNos() {
    return pageNos;
  }

  public void setPageNos(int pageNos) {
    this.pageNos = pageNos;
  }

  public List<OrderPojo> getOrderPojos() {
    return orderPojos;
  }

  public void setOrderPojos(List<OrderPojo> orderPojos) {
    this.orderPojos = orderPojos;
  }

  public String getEndday() {
    return endday;
  }

  public void setEndday(String endday) {
    this.endday = endday;
  }

  public String getBeganday() {
    return beganday;
  }

  public void setBeganday(String beganday) {
    this.beganday = beganday;
  }

  public List<SysDictPojo> getStatusSysDictList() {
    return statusSysDictList;
  }

  public void setStatusSysDictList(List<SysDictPojo> statusSysDictList) {
    this.statusSysDictList = statusSysDictList;
  }

  public UserOrderDetailService getUserOrderDetailService() {
    return userOrderDetailService;
  }

  public void setUserOrderDetailService(UserOrderDetailService userOrderDetailService) {
    this.userOrderDetailService = userOrderDetailService;
  }

  public UserOrderDetailPojo getUserOrderDetailPojo() {
    return userOrderDetailPojo;
  }

  public void setUserOrderDetailPojo(UserOrderDetailPojo userOrderDetailPojo) {
    this.userOrderDetailPojo = userOrderDetailPojo;
  }

  public List<UserOrderDetailPojo> getProductSaleRecordList() {
    return productSaleRecordList;
  }

  public void setProductSaleRecordList(List<UserOrderDetailPojo> productSaleRecordList) {
    this.productSaleRecordList = productSaleRecordList;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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

  /***
   * 获取业务字典
   */
  private void getList() {
    try {
      statusSysDictList = sysDictService.getSysDictListByType("status");
    } catch (Exception e) {

      e.printStackTrace();
    }
  }



  /**
   * 查找单条销售记录
   * 
   * @return
   * @throws Exception
   */
  public String findOneUserOrderDetailById() throws Exception {
    getList();
    userOrderDetailPojo =
        userOrderDetailService.findUserOrderDetailById(userOrderDetailPojo.getId());
    return SUCCESS;
  }

  /***
   * 审核单条销售记录
   * 
   * @return
   * @throws SQLException
   */
  public String checkUserOrderDetail() throws SQLException {
    userOrderDetailService.checkUserOrderDetail(userOrderDetailPojo);
    FileUtil.alertMessageBySkip("审核成功！", "userOrderDetailManage.do");
    return null;
  }

  /***
   * 批量审核
   * 
   * @return
   */
  public String checkAllUserOrderDetailById() {
    userOrderDetailService.checkAllUserOrderDetailById(tids);
    FileUtil.alertMessageBySkip("审核成功！", "userOrderDetailManage.do");
    return null;
  }

  /***
   * 每月商家销售统计
   * 
   * @return
   * @throws SQLException
   */
  public String productSaleAllList() throws SQLException {
    getList();
    orderPojos = orderService.productMonthSaleList(userOrderDetailPojo, page, beganday, endday);
    // productSaleRecordList = userOrderDetailService.productSaleCountAllList(
    // userOrderDetailPojo, page);
    JSONArray json = JSONArray.fromObject(orderPojos);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /***
   * 每月商家销售统计
   * 
   * @return
   * @throws Exception
   */
  public String getProductSaleCount() throws Exception {
    getList();
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(orderService.productMonthSaleCount(userOrderDetailPojo, beganday, endday));
    ActionContext ac = ActionContext.getContext();
    ac.put("userOrderDetailPojo", userOrderDetailPojo);
    map.put("month", 1000);
    ac.put("mouthcount", orderService.orderCount(map));
    ac.put("allcount", orderService.orderCount(null));
    // productSaleRecordCount = userOrderDetailService
    // .productSaleCountAllList(userOrderDetailPojo, page);
    // Map<String, Object> map = new HashMap<String, Object>();
    // List<UserOrderDetailPojo> list = userOrderDetailDao
    // .productSaleCountAllList(map);
    // page.setRowCount(productSaleRecordCount.size());
    return SUCCESS;
  }


  /***
   * 每月商家销售统计 二级页面
   * 
   * @return
   * @throws SQLException
   */
  public String shopSaleList() throws SQLException {
    // getList();
    // orderPojos = orderService.productMonthSaleList(
    // userOrderDetailPojo, page,beganday,endday);
    productSaleRecordList =
        userOrderDetailService.shopSaleList(userOrderDetailPojo, page, beganday, endday);
    JSONArray json = JSONArray.fromObject(productSaleRecordList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /***
   * 每月商家销售统计 二级页面
   * 
   * @return
   * @throws Exception
   */
  public String shopSaleListCount() throws Exception {
    // getList();
    if (page == null) {
      page = new Pager();
    }

    page.setRowCount(userOrderDetailService
        .shopSaleListCount(userOrderDetailPojo, beganday, endday));
    // productSaleRecordCount = userOrderDetailService
    // .productSaleCountAllList(userOrderDetailPojo, page);
    // Map<String, Object> map = new HashMap<String, Object>();
    // List<UserOrderDetailPojo> list = userOrderDetailDao
    // .productSaleCountAllList(map);
    // page.setRowCount(productSaleRecordCount.size());
    return SUCCESS;
  }

  /***
   * 产品销售统计count
   * 
   * @return
   * @throws Exception
   */
  public String getUserOrderDetailCount() throws Exception {
    try {
      getList();
      if (page == null) {
        page = new Pager();
      }
      testUsers = SellerService.getTestUsers();
      if (userOrderDetailPojo == null) {
        userOrderDetailPojo = new UserOrderDetailPojo();
      }
      if (testUsers != null && testUsers.size() > 0) {
        userOrderDetailPojo.setNotuserIds(getTestUsers());
      }
      page.setRowCount(userOrderDetailService.userOrderDetailAllCount(userOrderDetailPojo));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /***
   * 查找所有销售记录
   * 
   * @return
   */
  public String userOrderDetailAllList() {
    getList();
    testUsers = SellerService.getTestUsers();
    if (userOrderDetailPojo == null) {
      userOrderDetailPojo = new UserOrderDetailPojo();
    }
    if (testUsers != null && testUsers.size() > 0) {
      userOrderDetailPojo.setNotuserIds(getTestUsers());
    }
    productSaleRecordList =
        userOrderDetailService.userOrderDetailAllList(userOrderDetailPojo, page);
    JSONArray json = JSONArray.fromObject(productSaleRecordList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 后台订单导出Excel表格
   * 
   * @return
   * @throws Exception
   */
  public void getSellExcel() throws Exception {

    WritableWorkbook wwb = null;
    OutputStream ots = null;
    String filePath = null;
    String fileName = null;
    fileName = "商品当日销量统计" + StringUtil.getCurrentDateStrByfu() + ".xls";
    DecimalFormat df = new DecimalFormat("#.##");

    // filePath = "/home"+fileName;
    // 这里直接找到项目的路径，liunx和window路径不同，不能混淆在一起!!!
    filePath =
        ServletActionContext.getServletContext().getRealPath("/temp") + File.separator + fileName;
    File file = new File(filePath);
    if (!file.isFile()) {
      file.createNewFile();
    }
    try {
      ots = new FileOutputStream(file);
      wwb = Workbook.createWorkbook(ots);
      WritableSheet sheet = wwb.createSheet("sheet1", 0);
      sheet.addCell(new Label(0, 0, "产品ID"));
      sheet.addCell(new Label(1, 0, "产品名称"));
      sheet.addCell(new Label(2, 0, "产品货号"));
      sheet.addCell(new Label(3, 0, "产品单价"));
      sheet.addCell(new Label(4, 0, "当日销售数量"));
      List<OrderDetailPojo> orderDetails = orderDetailService.statisticalSell();
      int i = 1;
      for (OrderDetailPojo orderDetailPojo : orderDetails) {
        sheet.addCell(new Label(0, i, String.valueOf(orderDetailPojo.getProductId())));
        sheet.addCell(new Label(1, i, orderDetailPojo.getProductName()));
        sheet.addCell(new Label(2, i, orderDetailPojo.getProductNum()));
        sheet.addCell(new Label(3, i, df.format(orderDetailPojo.getStockPrice())));
        sheet.addCell(new Label(4, i, String.valueOf(orderDetailPojo.getNum())));
        i++;
      }
      wwb.write();
      ots.flush();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (wwb != null) {
          wwb.close();
        }
        if (ots != null) {
          ots.close();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }

    HttpServletResponse response = ServletActionContext.getResponse();
    // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
    response.setContentType("application/vnd.ms-excel");
    String fileNames = new String(fileName.getBytes("GB2312"), "ISO_8859_1");
    response.setHeader("Content-Disposition", "attachment;fileName=" + fileNames);
    FileInputStream fileInputStream = null;
    ServletOutputStream out = null;
    try {
      fileInputStream = new FileInputStream(filePath);
      out = response.getOutputStream();
      int i = 0;
      while ((i = fileInputStream.read()) != -1) {
        out.write(i);
      }
      out.flush();

      File delfile = new File(filePath);
      // 路径为文件且不为空则进行删除
      if (delfile.isFile() && delfile.exists()) {
        delfile.delete();
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (out != null) {
        out.close();
      }
      if (fileInputStream != null) {
        fileInputStream.close();
      }
    }
  }

}
