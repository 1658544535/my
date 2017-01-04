package com.tzmb2c.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
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

import com.tzmb2c.common.Pager;
import com.tzmb2c.web.dao.SysLoginLogDao;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginLogPojo;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.SysDictService;

/**
 * 商品销售记录，统计
 * 
 * @author creazylee
 * 
 */
public class OperationsAction extends SuperAction {

  private OrderPojo orderPojo;
  private String[] tids;
  private String type;
  private String result;
  private String beganday;
  private String endday;


  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private OrderService OrderService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private SysLoginLogDao sysLoginLogDao;

  private List<OrderPojo> productSaleRecordList = null;
  private List<SysDictPojo> statusSysDictList = null;
  private List<OrderPojo> orderPojos;
  private int pageNos;
  private SysLoginLogPojo sysLoginLog;
  private String os;
  private List<OrderPojo> orderPojoList;

  public List<OrderPojo> getOrderPojoList() {
    return orderPojoList;
  }

  public void setOrderPojoList(List<OrderPojo> orderPojoList) {
    this.orderPojoList = orderPojoList;
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

  public OrderService getOrderService() {
    return OrderService;
  }

  public void setOrderService(OrderService OrderService) {
    this.OrderService = OrderService;
  }

  public OrderPojo getOrderPojo() {
    return orderPojo;
  }

  public void setOrderPojo(OrderPojo OrderPojo) {
    this.orderPojo = OrderPojo;
  }

  public List<OrderPojo> getProductSaleRecordList() {
    return productSaleRecordList;
  }

  public void setProductSaleRecordList(List<OrderPojo> productSaleRecordList) {
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

  public SysLoginLogPojo getSysLoginLog() {
    return sysLoginLog;
  }

  public void setSysLoginLog(SysLoginLogPojo sysLoginLog) {
    this.sysLoginLog = sysLoginLog;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }


  /***
   * 产品销售统计count
   * 
   * @return
   * @throws Exception
   */
  public String getOperationsCount() throws Exception {

    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (orderPojo != null) {
      map.put("createDateStartStr", orderPojo.getCreateDateStartStr());
      map.put("createDateEndStr", orderPojo.getCreateDateEndStr());

    }
    int i = OrderService.getOrderAllCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  /***
   * 查找所有销售记录
   * 
   * @return
   * @throws SQLException
   */
  public String operationsAllList() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }

    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (orderPojo != null) {
      map.put("createDateStartStr", orderPojo.getCreateDateStartStr());
      map.put("createDateEndStr", orderPojo.getCreateDateEndStr());

    }
    List<OrderPojo> list = OrderService.getOrderAllList(map);
    JSONArray json = JSONArray.fromObject(list);
    page.setRowCount(OrderService.getOrderAllCount(map));
    page.setResult(json.toString());

    return SUCCESS;
  }

  /**
   * 后台订单导出Excel表格
   * 
   * @return
   * @throws Exception
   */
  public void getOperationsExcel() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    if (tids != null && !tids.equals("")) {
      map.put("tids", tids);
    }
    if (orderPojo != null) {
      map.put("createDateStartStr", orderPojo.getCreateDateStartStr());
      map.put("createDateEndStr", orderPojo.getCreateDateEndStr());

    }
    orderPojoList = OrderService.getOrderAllList(map);
    WritableWorkbook wwb = null;
    OutputStream ots = null;
    String filePath = null;
    String fileName = null;
    this.downloadFileName = "运营数据明细记录.xls";
    fileName = this.downloadFileName;
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
      sheet.addCell(new Label(0, 0, "订单日期"));
      sheet.addCell(new Label(1, 0, "订单号"));
      sheet.addCell(new Label(2, 0, "商品ID"));
      sheet.addCell(new Label(3, 0, "品牌名称"));
      sheet.addCell(new Label(4, 0, "商家名称"));
      sheet.addCell(new Label(5, 0, "商品名称"));
      sheet.addCell(new Label(6, 0, "商品货号"));
      sheet.addCell(new Label(7, 0, "单价"));
      sheet.addCell(new Label(8, 0, "数量"));
      sheet.addCell(new Label(9, 0, "省份"));
      for (int j = 1, i = 1; j <= orderPojoList.size(); j++) {
        OrderPojo orderPojo = orderPojoList.get(j - 1);

        sheet.addCell(new Label(0, i, orderPojo.getCreateDateStr()));
        sheet.addCell(new Label(1, i, orderPojo.getOrderNo()));
        sheet.addCell(new Label(2, i, orderPojo.getProductId()));
        sheet.addCell(new Label(3, i, orderPojo.getBrand()));
        sheet.addCell(new Label(4, i, orderPojo.getName()));
        sheet.addCell(new Label(5, i, orderPojo.getProductName()));
        sheet.addCell(new Label(6, i, orderPojo.getStockPrice()));
        sheet.addCell(new Label(7, i, String.valueOf(orderPojo.getNum())));
        sheet.addCell(new Label(8, i, String.valueOf(orderPojo.getOrderId())));
        sheet.addCell(new Label(9, i, orderPojo.getProvinces()));

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
    ServletOutputStream out;
    try {
      FileInputStream fileInputStream = new FileInputStream(filePath);
      out = response.getOutputStream();
      int i = 0;
      while ((i = fileInputStream.read()) != -1) {
        out.write(i);
      }
      fileInputStream.close();
      out.close();
      File delfile = new File(filePath);
      // 路径为文件且不为空则进行删除
      if (delfile.isFile() && delfile.exists()) {
        delfile.delete();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
