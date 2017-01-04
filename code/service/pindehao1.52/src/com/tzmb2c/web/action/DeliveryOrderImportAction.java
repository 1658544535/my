/*
 * 文 件 名: DeliveryOrderImportAction.java 创 建 人: admin 创建时间: 2016-09-22
 */
package com.tzmb2c.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.business.service.GrouponService;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.RandomUtils;
import com.tzmb2c.utils.SmsSendUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.DeliveryOrderImportPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.OrderShipPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.DeliveryOrderImportService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.OrderShipService;

public class DeliveryOrderImportAction extends SuperAction {
  @Autowired
  private DeliveryOrderImportService deliveryOrderImportService;
  @Autowired
  private OrderShipService orderShipService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private GrouponService grouponService;

  private OrderPojo orderPojo;
  private DeliveryOrderImportPojo deliveryOrderImportPojo;
  private List<DeliveryOrderImportPojo> deliveryOrderImportPojos;
  private Long id;
  private String[] tids;
  private String result;
  private int success;
  private int fail;
  private String importupfile;
  private int repeatImport;
  private List<OrderShipPojo> orderShipPojos;
  private OrderShipPojo orderShipPojo;
  /**
   * impBatchNo:本次导入批次号
   */
  private String impBatchNo;

  public String getImpBatchNo() {
    return impBatchNo;
  }

  public void setImpBatchNo(String impBatchNo) {
    this.impBatchNo = impBatchNo;
  }

  public OrderShipPojo getOrderShipPojo() {
    return orderShipPojo;
  }

  public void setOrderShipPojo(OrderShipPojo orderShipPojo) {
    this.orderShipPojo = orderShipPojo;
  }

  public List<OrderShipPojo> getOrderShipPojos() {
    return orderShipPojos;
  }

  public void setOrderShipPojos(List<OrderShipPojo> orderShipPojos) {
    this.orderShipPojos = orderShipPojos;
  }

  public int getRepeatImport() {
    return repeatImport;
  }

  public void setRepeatImport(int repeatImport) {
    this.repeatImport = repeatImport;
  }

  public String getImportupfile() {
    return importupfile;
  }

  public void setImportupfile(String importupfile) {
    this.importupfile = importupfile;
  }

  public int getFail() {
    return fail;
  }

  public void setFail(int fail) {
    this.fail = fail;
  }

  public int getSuccess() {
    return success;
  }

  public void setSuccess(int success) {
    this.success = success;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public DeliveryOrderImportPojo getDeliveryOrderImportPojo() {
    return deliveryOrderImportPojo;
  }

  public void setDeliveryOrderImportPojo(DeliveryOrderImportPojo deliveryOrderImportPojo) {
    this.deliveryOrderImportPojo = deliveryOrderImportPojo;
  }

  public List<DeliveryOrderImportPojo> getDeliveryOrderImportPojos() {
    return deliveryOrderImportPojos;
  }

  public void setDeliveryOrderImportPojos(List<DeliveryOrderImportPojo> deliveryOrderImportPojos) {
    this.deliveryOrderImportPojos = deliveryOrderImportPojos;
  }

  /**
   * 查询全部条数
   */
  public String goDeliveryOrderImport() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    try {
      int i = deliveryOrderImportService.countBy(null);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String deliveryOrderImportRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      int i = deliveryOrderImportService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String deliveryOrderImportList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    List<DeliveryOrderImportPojo> deliveryOrderImportList = null;
    try {
      deliveryOrderImportList = deliveryOrderImportService.listPage(map);
      JSONArray json = JSONArray.fromObject(deliveryOrderImportList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddDeliveryOrderImport() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Throwable
   */
  public String add() throws Throwable {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && deliveryOrderImportPojo != null) {
      deliveryOrderImportPojo.setCreateBy(user.getId());
      deliveryOrderImportPojo.setCreateDate(new Date());
      deliveryOrderImportPojo.setUpdateBy(user.getId());
      deliveryOrderImportPojo.setUpdateDate(new Date());
      try {
        deliveryOrderImportService.add(deliveryOrderImportPojo);
        FileUtil.alertMessageBySkip("新增成功！", "goDeliveryOrderImport.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goAddDeliveryOrderImport.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goDeliveryOrderImport.do");
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditDeliveryOrderImport() throws Exception {
    if (id != null && id > 0) {
      deliveryOrderImportPojo = deliveryOrderImportService.getById(id);
    }
    return SUCCESS;
  }

  /**
   * 编辑提交
   * 
   * @return
   * @throws Throwable
   */
  public String update() throws Throwable {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && deliveryOrderImportPojo != null) {
      deliveryOrderImportPojo.setUpdateBy(user.getId());
      deliveryOrderImportPojo.setUpdateDate(new Date());
      try {
        deliveryOrderImportService.update(deliveryOrderImportPojo);
        FileUtil.alertMessageBySkip("提交成功！", "goDeliveryOrderImport.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！", "goEditDeliveryOrderImport.do?id="
            + deliveryOrderImportPojo.getId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goDeliveryOrderImport.do");
    }

    return null;
  }

  /**
   * 根据id审核
   * 
   * @return
   */
  public String check() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && id != null && id > 0) {
      deliveryOrderImportPojo = new DeliveryOrderImportPojo();
      deliveryOrderImportPojo.setId(id);
      deliveryOrderImportPojo.setStatus(1);
      deliveryOrderImportPojo.setUpdateBy(user.getId());
      deliveryOrderImportPojo.setUpdateDate(new Date());
      try {
        deliveryOrderImportService.update(deliveryOrderImportPojo);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheck() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && id != null && id > 0) {
      deliveryOrderImportPojo = new DeliveryOrderImportPojo();
      deliveryOrderImportPojo.setId(id);
      deliveryOrderImportPojo.setStatus(0);
      deliveryOrderImportPojo.setUpdateBy(user.getId());
      deliveryOrderImportPojo.setUpdateDate(new Date());
      try {
        deliveryOrderImportService.update(deliveryOrderImportPojo);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  public String delete() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && id != null && id > 0) {
      try {
        deliveryOrderImportService.delete(id);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 审核选中
   * 
   * @return
   */
  public String checkAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      deliveryOrderImportPojo = new DeliveryOrderImportPojo();
      deliveryOrderImportPojo.setStatus(1);
      for (String tid : tids) {
        deliveryOrderImportPojo.setId(Long.valueOf(tid));
        deliveryOrderImportPojo.setUpdateBy(user.getId());
        deliveryOrderImportPojo.setUpdateDate(new Date());
        try {
          deliveryOrderImportService.update(deliveryOrderImportPojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 选中取消审核
   * 
   * @return
   */
  public String uncheckAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      deliveryOrderImportPojo = new DeliveryOrderImportPojo();
      deliveryOrderImportPojo.setStatus(0);
      for (String tid : tids) {
        deliveryOrderImportPojo.setId(Long.valueOf(tid));
        deliveryOrderImportPojo.setUpdateBy(user.getId());
        deliveryOrderImportPojo.setUpdateDate(new Date());
        try {
          deliveryOrderImportService.update(deliveryOrderImportPojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 删除选中
   * 
   * @return
   */
  public String deleteAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      for (String tid : tids) {
        try {
          deliveryOrderImportService.delete(Long.valueOf(tid));
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 跳转发货导入订单列表
   * 
   * @return
   * @throws Exception
   */
  public String goOrderDelivery() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getUser();
    if (sysLogin == null) {
      return "loginpage";
    }

    int count = 0;
    if (StringUtils.isNotBlank(impBatchNo)) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("orderNo", deliveryOrderImportPojo != null ? deliveryOrderImportPojo.getOrderNo()
          : "");
      map.put("batchNo", impBatchNo);
      count = deliveryOrderImportService.countBy(map);
    }
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(count);
    ActionContext ac = ActionContext.getContext();
    ac.put("success", success);
    ac.put("fail", fail);
    return SUCCESS;
  }

  /**
   * 发货订单列表
   * 
   * @return
   * @throws SQLException
   */
  public String orderDeliveryList() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    if (StringUtils.isNotBlank(impBatchNo)) {
      map.put("orderNo", deliveryOrderImportPojo != null ? deliveryOrderImportPojo.getOrderNo()
          : "");
      map.put("batchNo", impBatchNo);
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());

      deliveryOrderImportPojos = deliveryOrderImportService.listPage(map);
      JSONArray json = JSONArray.fromObject(deliveryOrderImportPojos);
      page.setResult(json.toString());
    } else {
      page.setResult("{}");
    }
    return SUCCESS;
  }

  /**
   * 下载导入模板
   * 
   * @return
   * @throws Exception
   */
  public void downloadTemplates() throws Exception {
    UserUtil.getWebUser();
    new HashMap<String, Object>();
    // 过滤记事本中的userId
    String filePath1 = "";
    String fileName1 = "userId.txt";
    List<Integer> a = new ArrayList<>();
    filePath1 =
        ServletActionContext.getServletContext().getRealPath("/temp") + File.separator + fileName1;
    File file1 = new File(filePath1);
    if (file1.isFile() && file1.exists()) { // 判断文件是否存在
      InputStreamReader read = new InputStreamReader(new FileInputStream(file1), "utf-8");// 编码格式
      BufferedReader bufferedReader = new BufferedReader(read);
      String lineTxt = null;
      while ((lineTxt = bufferedReader.readLine()) != null) {
        a.add(Integer.parseInt(lineTxt));
      }
      read.close();
    }
    downloadFileName = "发货模板.xls";
    WritableWorkbook wwb = null;
    OutputStream ots = null;
    String filePath = null;
    String fileName = null;
    fileName = downloadFileName;

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
      String[] cellStr = {"订单号", "快递公司", "快递单号"};
      for (int i = 0; i < cellStr.length; i++) {
        sheet.addCell(new Label(i, 0, cellStr[i]));
        if (i == cellStr.length - 1) {
          sheet.addCell(new Label(i, 0, cellStr[i]));
        }
      }
      wwb.write();
      ots.flush();
    } catch (Exception e) {
      // TODO: handle exception
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

  /**
   * 订单导入Excel表格
   * 
   * @return
   * @throws Exception
   */
  public String importOrder() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getUser();
    if (sysLogin == null) {
      return "loginpage";
    }
    int successCnt = 0;
    int failCnt = 0;
    if (importupfile != null) {
      FileInputStream fis = null;
      Workbook workBook = null;
      Sheet sheet = null;
      String orderNo = "";
      String logisName = "";
      String logisCName = "";
      String logisNo = "";
      String remarks = "";
      String content = "";
      boolean valid = true;// 数据检查状态；
      int status = 0;// 导入状态（0为失败，1为成功）；
      String batchNo = "";// 导入批次
      DeliveryOrderImportPojo deliveryOrderImportPojo = null;
      OrderShipPojo orderShipPojo = null;
      OrderShipPojo updOrderShip = null;
      try {
        fis = new FileInputStream(importupfile);
        workBook = Workbook.getWorkbook(fis);
        sheet = workBook.getSheet(0);

        // cell(i,j) i列 j行
        if (sheet != null) {
          int row = sheet.getRows();// 行
          if (row >= 2) {
            // 本次批次号
            batchNo = System.nanoTime() + RandomUtils.runVerifyCode(3);
            for (int j = 1; j < row; j++) {
              remarks = "";
              orderNo = "";
              logisName = "";
              logisCName = "";
              logisNo = "";
              status = 0;
              valid = true;
              if (sheet.getCell(0, j) != null
                  && StringUtils.isNotBlank(sheet.getCell(0, j).getContents())) {
                orderNo = sheet.getCell(0, j).getContents().trim();
              }
              // 获取快递名称
              if (sheet.getCell(1, j) != null
                  && StringUtils.isNotBlank(sheet.getCell(1, j).getContents())) {
                logisCName = sheet.getCell(1, j).getContents().trim();
                // 快递名称
                logisName = SellerService.logisticsNameTrans(logisCName);
              }
              // 获取快递单号
              if (sheet.getCell(2, j) != null
                  && StringUtils.isNotBlank(sheet.getCell(2, j).getContents())) {
                logisNo = sheet.getCell(2, j).getContents().trim();
              }
              if (StringUtils.isBlank(orderNo) && StringUtils.isBlank(logisCName)
                  && StringUtils.isBlank(logisNo)) {
                // 过滤空行
                continue;
              }
              if (StringUtils.isBlank(orderNo)) {
                valid = false;
                remarks += "订单号不能为空；";
              }
              if (StringUtils.isBlank(logisCName)) {
                valid = false;
                remarks += "快递名称不能为空；";
              }
              if (StringUtils.isBlank(logisNo)) {
                valid = false;
                remarks += "快递单号不能为空；";
              }
              try {
                if (valid) {
                  // 判断重复导入订单
                  if (StringUtils.isNotBlank(orderNo)) {
                    orderPojo = orderService.findOrderByOrderNo(orderNo);
                    if (orderPojo == null) {
                      remarks += "找不到该订单号；";
                      failCnt++;
                    } else if (!(orderPojo.getOrderStatus() == 2 || repeatImport == 1
                        && orderPojo.getOrderStatus() == 3)) {
                      if (orderPojo.getOrderStatus() == 3) {
                        remarks += "该订单已发货,请选择重复导入；";
                      } else {
                        remarks += "该订单状态非已付款待发货；";
                      }
                      failCnt++;
                    } else {
                      orderShipPojo = new OrderShipPojo();
                      orderShipPojo.setOrderId(orderPojo.getId());
                      orderShipPojos = orderShipService.orderShipAllList(orderShipPojo, page);
                      if (orderShipPojos != null && orderShipPojos.size() > 0) {
                        orderShipPojo = orderShipPojos.get(0);
                      } else {
                        orderShipPojo = null;
                      }
                      if (orderShipPojo != null && repeatImport == 0) {
                        remarks += "该订单已发货,请选择重复导入；";
                        failCnt++;
                      } else {
                        if (orderShipPojo != null) {
                          updOrderShip = new OrderShipPojo();
                          updOrderShip.setLogisticsName(logisName);
                          updOrderShip.setLogisticsNo(logisNo);
                          updOrderShip.setId(orderShipPojo.getId());
                          status = orderShipService.updateOrderShip(updOrderShip);
                        } else {
                          updOrderShip = new OrderShipPojo();
                          updOrderShip.setOrderNo(orderPojo.getOrderNo());
                          updOrderShip.setOrderId(orderPojo.getId());
                          updOrderShip.setUserId(orderPojo.getUserId());
                          updOrderShip.setOrderStatus(2);
                          updOrderShip.setStatus(1);
                          updOrderShip.setConsignee(orderPojo.getConsignee());
                          updOrderShip.setConsigneeAddress(orderPojo.getConsigneeAddress());
                          updOrderShip.setConsigneePhone(orderPojo.getConsigneePhone());
                          updOrderShip.setConsigneeType(orderPojo.getConsigneeType());
                          updOrderShip.setBuyerMessage(orderPojo.getBuyerMessage());
                          updOrderShip.setConsignor("拼得好");
                          updOrderShip.setConsignorAddress("汕头市澄海区");
                          updOrderShip.setLogisticsNo(logisNo);
                          updOrderShip.setLogisticsName(logisName);
                          updOrderShip.setCreateBy(sysLogin.getId());
                          updOrderShip.setUpdateBy(sysLogin.getId());
                          updOrderShip.setCreateDate(new Date());
                          updOrderShip.setUpdateDate(new Date());
                          status = orderShipService.insertOrderShip(updOrderShip);
                          if (status > 0) {
                            // 更新订单发货状态
                            orderService.updateOrderStatus(orderPojo.getId());
                          }
                        }

                        if (status > 0) {
                          successCnt++;
                          status = 1;
                          remarks += "导入成功；";
                          // 发送短信
                          content = "【拼得好】您购买的宝贝已由" + logisCName + "快递发出，正在奔向您的途中，快递单号：" + logisNo;
                          SmsSendUtil.sendSMS(orderPojo.getConsigneePhone(), content);

                          // 添加订单消息
                          try {
                            grouponService.addUserOrderNotice(6, orderPojo.getUserId(),
                                orderPojo.getId());
                          } catch (Exception e) {
                            e.printStackTrace();
                          }
                        } else {
                          failCnt++;
                          remarks += "导入失败；";
                        }
                      }
                    }
                  }
                } else {
                  failCnt++;
                }
                deliveryOrderImportPojo = new DeliveryOrderImportPojo();
                deliveryOrderImportPojo.setBatchNo(batchNo);
                deliveryOrderImportPojo.setOrderNo(orderNo);
                deliveryOrderImportPojo.setLogisticsName(logisName);
                deliveryOrderImportPojo.setLogisticsNo(logisNo);
                deliveryOrderImportPojo.setStatus(status);
                deliveryOrderImportPojo.setRemarks(remarks);
                deliveryOrderImportPojo.setCreateDate(new Date());
                deliveryOrderImportPojo.setUpdateDate(new Date());
                deliveryOrderImportService.add(deliveryOrderImportPojo);
              } catch (Exception ex) {
                ex.printStackTrace();
                failCnt++;
                remarks += "导入异常；";
              }
            }
            FileUtil.alertMessageBySkip("导入完成", "goOrderDelivery.do?impBatchNo=" + batchNo
                + "&success=" + successCnt + "&fail=" + failCnt);
          } else {
            FileUtil.alertMessageBySkip("导入的文件没有数据哦！", "goOrderDelivery.do");
          }
        } else {
          FileUtil.alertMessageBySkip("导入文件不能为空哦！", "goOrderDelivery.do");
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (fis != null) {
          fis.close();
        }
        if (workBook != null) {
          workBook.close();// 记得关闭
        }
      }
    } else {
      FileUtil.alertMessageBySkip("导入文件不能为空哦！", "goOrderDelivery.do");
    }
    return null;
  }
}
