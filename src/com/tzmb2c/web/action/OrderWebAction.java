package com.tzmb2c.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
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
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.OrderDetailPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.OrderShipPojo;
import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserOrderRefundPojo;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.OrderShipService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserOrderRefundService;

public class OrderWebAction extends SuperAction {

  @Autowired
  private OrderService orderService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private OrderShipService orderShipService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private ProductService productService;
  @Autowired
  private SysAreaService sysAreaService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private UserOrderRefundService userOrderRefundService;

  private OrderPojo orderPojo;
  private List<OrderPojo> orderPojos;
  private OrderDetailPojo orderDetailPojo;
  private List<OrderDetailPojo> orderDetailPojos;
  private OrderShipPojo orderShipPojo;
  private List<OrderShipPojo> orderShipPojos;
  private String[] tids;
  private String testcount;
  private String isAll;
  private String importupfile;
  private SysAreaPojo sysArea;
  private List<Integer> testUsers;
  private UserOrderRefundPojo userOrderRefundPojo;
  private File upfile;

  /**
   * 商家中心-我的订单前端页面
   * 
   * @return
   * @throws SQLException
   */
  public String getMyOrderWeb() throws SQLException {
    try {
      ActionContext actionContext = ActionContext.getContext();
      Map<String, Object> map = new HashMap<String, Object>();
      SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
      int count = 0;
      ActionContext ac = ActionContext.getContext();
      ac.put("orderStatus", sysDictService.getSysDictListByType("order_status"));
      if (orderPojo != null) {
        map.put("option", 1);
        map.put("channel", orderPojo.getChannel());
        map.put("orderNo", orderPojo.getOrderNo());
        map.put("consigneePhone", orderPojo.getConsigneePhone());
        map.put("beganday", orderPojo.getBeganday());
        map.put("endday", orderPojo.getEndday());
        map.put("activityId", orderPojo.getActivityId());
        map.put("orderStatus", orderPojo.getOrderStatus());
        map.put("consignee", StringUtil.checkVal(orderPojo.getConsignee()).trim());
        map.put("logisticsNo", orderPojo.getLogisticsNo());
        map.put("logisticsName", orderPojo.getLogisticsName());
        map.put("groupBeginDateStr", orderPojo.getGroupBeginDateStr());
        map.put("groupEndDateStr", orderPojo.getGroupEndDateStr());
        map.put("productName", StringUtil.checkVal(orderPojo.getProductName()).trim());
      }
      testUsers = SellerService.getTestUsers();
      if (testUsers != null && testUsers.size() > 0) {
        map.put("notuserIds", testUsers);
      }
      if (logiPojo != null) {
        map.put("orderType", 0);
        map.put("suserId", logiPojo.getId());
        count = orderService.orderAllCount2(map);
      }
      if (page == null) {
        page = new Pager();
      }
      page.setRowCount(count);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return SUCCESS;
  }

  /**
   * 商家中心-我的订单列表
   * 
   * @return
   * @throws SQLException
   */
  public String getMyOrderListWeb() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
    if (orderPojo != null) {
      map.put("option", 1);
      map.put("channel", orderPojo.getChannel());
      map.put("orderNo", orderPojo.getOrderNo());
      map.put("consigneePhone", orderPojo.getConsigneePhone());
      map.put("beganday", orderPojo.getBeganday());
      map.put("endday", orderPojo.getEndday());
      map.put("activityId", orderPojo.getActivityId());
      map.put("orderStatus", orderPojo.getOrderStatus());
      map.put("consignee", StringUtil.checkVal(orderPojo.getConsignee()).trim());
      map.put("logisticsNo", orderPojo.getLogisticsNo());
      map.put("logisticsName", orderPojo.getLogisticsName());
      map.put("groupBeginDateStr", orderPojo.getGroupBeginDateStr());
      map.put("groupEndDateStr", orderPojo.getGroupEndDateStr());
      map.put("productName", StringUtil.checkVal(orderPojo.getProductName()).trim());
    }
    testUsers = SellerService.getTestUsers();
    if (testUsers != null && testUsers.size() > 0) {
      map.put("notuserIds", testUsers);
    }
    if (logiPojo != null) {
      map.put("orderType", 0);
      map.put("suserId", logiPojo.getId());
      orderPojos = orderService.orderAllList2(map);
    }
    JSONArray json = JSONArray.fromObject(orderPojos);
    // page.setRowCount(userBrandPojos.size());
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 商家中心-我的订单详情前端页面
   * 
   * @return
   * @throws SQLException
   */
  public String getMyOrderDetailWeb() throws SQLException {
    if (orderPojo != null) {
      orderPojo = orderService.findOrderById(orderPojo.getId());
      orderShipPojo = orderShipService.findByIdOrderShip(orderPojo.getId());
      orderDetailPojos = orderDetailService.getOrderDetail(orderPojo.getId());
    }
    return SUCCESS;
  }

  /**
   * 商家中心订单导出Excel表格
   * 
   * @return
   * @throws Exception
   */
  public void getOrderExcel2() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      return;
    }
    List<OrderPojo> orderList = null;
    String oos = orderPojo.getOs();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("suserId", sysLogin.getId());
    if (isAll != null && isAll.equals("1")) {
      if (orderPojo != null) {
        map.put("orderNo", orderPojo.getOrderNo());
        map.put("consignee", orderPojo.getConsignee());
        map.put("consigneePhone", orderPojo.getConsigneePhone());
        map.put("consigneeAddress", orderPojo.getConsigneeAddress());
        map.put("payStatus", orderPojo.getPayStatus());
        map.put("refundStatus", orderPojo.getRefundStatus());
        map.put("payWay", orderPojo.getPayMethod());
        map.put("overdue", orderPojo.getOverdue());
        map.put("createDate", orderPojo.getCreateDateString());
        map.put("sendDate", orderPojo.getSendTimes());
        map.put("tids", orderPojo.getTids());
        map.put("userName", orderPojo.getUserName());
        map.put("remarks", orderPojo.getRemarks());
        map.put("logisticsName", orderPojo.getLogisticsName());
        map.put("consigneeType", orderPojo.getConsigneeType());
        map.put("channel", orderPojo.getChannel());
        map.put("activityId", orderPojo.getActivityId());
        map.put("orderStatus", orderPojo.getOrderStatus());
        if (orderPojo.getBeganday() != null) {
          map.put("beganday", orderPojo.getBeganday());
        }
        if (orderPojo.getEndday() != null) {
          map.put("endday", orderPojo.getEndday());
        }
      }
    } else {
      if (tids != null && !tids.equals("")) {
        map.put("tids", tids);
      }
    }
    testUsers = SellerService.getTestUsers();
    if (testUsers != null && testUsers.size() > 0) {
      map.put("notuserIds", testUsers);
    }
    map.put("orderType", 0);
    orderList = orderService.orderAllList2(map);
    if (orderList.size() > 0 && orderList.size() * 1.5 > 4500) {
      FileUtil.alertMessageBySkip("数据过多，请先筛选再导出!", "getMyOrderWeb.do");
      return;
    }
    switch (oos) {
      case "1":
        this.downloadFileName = "待付款订单.xls";
        break;
      case "2":
        this.downloadFileName = "待发货单.xls";
        break;
      case "3":
        this.downloadFileName = "已发货订单.xls";
        break;
      case "4":
        this.downloadFileName = "已确认订单.xls";
        break;
      case "5":
        this.downloadFileName = "已评论订单.xls";
        break;
      default:
        this.downloadFileName = "订单信息.xls";
        break;
    }
    WritableWorkbook wwb = null;
    OutputStream ots = null;
    String filePath = null;
    String fileName = null;
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
      sheet.addCell(new Label(0, 0, "订单编号"));
      // sheet.addCell(new Label(1, 0, "淘宝订单号"));
      sheet.addCell(new Label(1, 0, "订单日期"));
      sheet.addCell(new Label(2, 0, "用户呢称"));
      sheet.addCell(new Label(3, 0, "账号"));
      sheet.addCell(new Label(4, 0, "买家备注"));
      sheet.addCell(new Label(5, 0, "货到付款"));
      sheet.addCell(new Label(6, 0, "结算金额"));
      sheet.addCell(new Label(7, 0, "订单状态"));
      sheet.addCell(new Label(8, 0, "支付状态"));
      sheet.addCell(new Label(9, 0, "支付方式"));
      sheet.addCell(new Label(10, 0, "省份"));
      sheet.addCell(new Label(11, 0, "市"));
      sheet.addCell(new Label(12, 0, "区"));
      sheet.addCell(new Label(13, 0, "收货人"));
      sheet.addCell(new Label(14, 0, "收货手机"));
      sheet.addCell(new Label(15, 0, "收货地址"));
      sheet.addCell(new Label(16, 0, "店铺名称"));
      sheet.addCell(new Label(17, 0, "产品名称"));
      sheet.addCell(new Label(18, 0, "产品货号"));
      // sheet.addCell(new Label(15, 0, "商品sku"));
      // sheet.addCell(new Label(19, 0, "商家编码"));
      sheet.addCell(new Label(19, 0, "数量"));
      sheet.addCell(new Label(20, 0, "单价"));
      sheet.addCell(new Label(21, 0, "买家运费"));
      sheet.addCell(new Label(22, 0, "快递公司"));
      sheet.addCell(new Label(23, 0, "物流单号"));
      sheet.addCell(new Label(24, 0, "收货人方式"));
      sheet.addCell(new Label(25, 0, "来源"));
      sheet.addCell(new Label(26, 0, "支付流水号"));
      sheet.addCell(new Label(27, 0, "付款时间"));
      sheet.addCell(new Label(28, 0, "商家留言"));
      String sku = "";
      for (int j = 1, i = 1; j <= orderList.size(); j++) {
        OrderPojo orderPojo = orderList.get(j - 1);
        System.out.println(orderPojo.getId());
        List<OrderDetailPojo> list2 =
            orderDetailService.getfindByUserIdOrderDetail(orderPojo.getId());
        for (int s = 1; s <= list2.size(); s++) {
          sheet.addCell(new Label(0, i, orderPojo.getOrderNo()));
          // sheet.addCell(new Label(1, i,
          // orderPojo.getTaobaoOrderNo()));
          sheet.addCell(new Label(1, i, orderPojo.getCreateDateString()));
          sheet.addCell(new Label(2, i, orderPojo.getUserName()));
          sheet.addCell(new Label(3, i, orderPojo.getLoginname()));
          sheet.addCell(new Label(4, i, orderPojo.getBuyerMessage()));
          sheet.addCell(new Label(5, i, ""));
          sheet.addCell(new Label(6, i, orderPojo.getFactPrice() + ""));
          sheet.addCell(new Label(7, i, orderPojo.getOrderStatusName()));
          sheet.addCell(new Label(8, i, orderPojo.getPayStatusName()));
          sheet.addCell(new Label(9, i, orderPojo.getPayMethodName()));
          sheet.addCell(new Label(10, i, orderPojo.getProvince()));
          sheet.addCell(new Label(11, i, orderPojo.getCity()));
          sheet.addCell(new Label(12, i, orderPojo.getArea()));
          sheet.addCell(new Label(13, i, orderPojo.getConsignee()));
          sheet.addCell(new Label(14, i, orderPojo.getConsigneePhone()));
          sheet.addCell(new Label(15, i, orderPojo.getConsigneeAddress()));
          OrderDetailPojo p = list2.get(s - 1);
          sheet.addCell(new Label(16, i, p.getShopName()));
          sku = "";
          if (StringUtils.isNotEmpty(p.getProductSku())) {
            sku = "(" + p.getProductSku() + ")";
          }
          sheet.addCell(new Label(17, i, p.getProductName() + sku));
          sheet.addCell(new Label(18, i, p.getProductNum()));
          // sheet.addCell(new Label(15, i, p.getProductSku()));
          // sheet.addCell(new Label(19, i, p.getBusinessCode()));
          sheet.addCell(new Label(19, i, p.getNum() + ""));
          sheet.addCell(new Label(20, i, p.getStockPrice() + ""));
          sheet.addCell(new Label(21, i, orderPojo.getEspressPrice() + ""));
          sheet.addCell(new Label(22, i, orderPojo.getLogisticsName() == null ? "" : orderPojo
              .getLogisticsName()));
          sheet.addCell(new Label(23, i, orderPojo.getLogisticsNo() == null ? "" : orderPojo
              .getLogisticsNo() + ""));
          sheet.addCell(new Label(24, i, orderPojo.getConsigneeTypeName() == null ? "" : orderPojo
              .getConsigneeTypeName() + ""));
          sheet.addCell(new Label(25, i, orderPojo.getChannelName()));
          sheet.addCell(new Label(26, i, p.getOutTradeNo()));
          sheet.addCell(new Label(27, i, p.getSentTime()));
          sheet.addCell(new Label(28, i, orderPojo.getSellerMessage() == null ? "" : orderPojo
              .getSellerMessage()));
          i++;
        }
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

  /**
   * 商家中心订单导入Excel表格
   * 
   * @return
   * @throws Exception
   */
  public void importOrderSeller() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      return;
    }
    int repeat = 0;
    String errorMsg = null;
    if (importupfile != null) {
      FileInputStream fis = null;
      Workbook workBook = null;
      Sheet sheet = null;
      String orderNo = "";
      String logisName = "";
      String logisNo = "";
      try {
        fis = new FileInputStream(importupfile);
        workBook = Workbook.getWorkbook(fis);
        sheet = workBook.getSheet(0);
        // cell(i,j) i列 j行
        if (sheet != null) {
          int row = sheet.getRows();// 行
          for (int j = 1; j < row; j++) {
            if (sheet.getCell(0, j) != null
                && StringUtils.isNotEmpty(sheet.getCell(0, j).getContents())) {
              orderNo = sheet.getCell(0, j).getContents();
            } else {
              continue;
            }
            if (sheet.getCell(1, j) != null
                && StringUtils.isNotEmpty(sheet.getCell(1, j).getContents())) {
              logisNo = sheet.getCell(1, j).getContents();
            } else {
              continue;
            }
            if (sheet.getCell(2, j) != null
                && StringUtils.isNotEmpty(sheet.getCell(2, j).getContents())) {
              logisName = sheet.getCell(2, j).getContents();
              logisName = SellerService.logisticsNameTrans(logisName);
            } else {
              continue;
            }

            orderPojo = orderService.findOrderByOrderNo(orderNo);
            if (orderPojo != null) {
              if (orderPojo.getOrderStatus() != 2) {
                repeat++;
                continue;
              }
              orderShipPojo = new OrderShipPojo();
              orderShipPojo.setOrderNo(orderNo);
              orderShipPojo.setOrderId(orderPojo.getId());
              orderShipPojo.setUserId(orderPojo.getUserId());
              orderShipPojo.setOrderStatus(3);
              orderShipPojo.setStatus(1);
              orderShipPojo.setConsignee(orderPojo.getConsignee());
              orderShipPojo.setConsigneeAddress(orderPojo.getConsigneeAddress());
              orderShipPojo.setConsigneePhone(orderPojo.getConsigneePhone());
              orderShipPojo.setConsigneeType(orderPojo.getConsigneeType());
              orderShipPojo.setBuyerMessage(orderPojo.getBuyerMessage());
              orderShipPojo.setConsignor(orderPojo.getUserName());
              orderShipPojo.setConsignorAddress(orderPojo.getConsignorAddress());
              orderShipPojo.setShipPhone(orderPojo.getShipPhone());
              orderShipPojo.setLogisticsNo(logisNo);
              orderShipPojo.setLogisticsName(logisName);
              orderShipPojo.setCreateBy(sysLogin.getId());
              orderShipPojo.setUpdateBy(sysLogin.getId());
              orderShipPojo.setCreateDate(new Date());
              orderShipPojo.setUpdateDate(new Date());

              orderPojo.setOrderStatus(3);
              orderPojo.setUpdateBy(sysLogin.getId());
              orderPojo.setUpdateDate(new Date());

              try {
                // 生成物流订单
                orderShipService.insertOrderShip(orderShipPojo);
                // 修改订单状态
                orderService.updateOrderStatusDelivery(orderPojo);
              } catch (Exception e) {
                e.printStackTrace();
                if (errorMsg == null) {
                  errorMsg = "导入异常，异常行数：" + (j + 1);
                } else {
                  errorMsg += "," + (j + 1);
                }
              }
            }
          }
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
      if (errorMsg != null) {
        FileUtil.alertMessageBySkip(errorMsg, "getMyOrderWeb.do");
      } else {
        FileUtil.alertMessageBySkip("导入成功,其中重复订单数" + repeat + "条", "getMyOrderWeb.do");
      }
    } else {
      FileUtil.alertMessageBySkip("导入失败", "getMyOrderWeb.do");
    }
  }

  // 跳转到添加发货信息
  public String goOrderShipAdd() throws Exception {
    String ads = null;
    orderPojo = orderService.findOrderById(orderPojo.getId());
    ActionContext ac = ActionContext.getContext();
    List<SysDictPojo> sysDicPojoList = sysDictService.getSysDictListByType("logistics_type");
    ac.put("sysDicPojoList", sysDicPojoList);
    ac.put("orderPojo", orderPojo);
    ac.put("consigneeType", sysDictService.getSysDictListByType("consignee_type"));
    if (orderPojo.getOrderStatus() == 1) {
      FileUtil.alertMessageBySkip("未付款，不能给予发货！", "getMyOrderWeb.do");
    } else if (orderPojo.getOrderStatus() == 2) {
      ads = SUCCESS;
    } else {
      FileUtil.alertMessageBySkip("已发货，不能重复发货！", "getMyOrderWeb.do");
    }
    return ads;
  }

  // 添加发货信息
  public String addOrderShip() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      return null;
    } else {
      orderShipPojo.prePersist(sysLogin);
    }
    if (orderShipService.findByIdOrderShip(orderShipPojo.getOrderId()) != null) {

      orderShipService.updateOrderShip(orderShipPojo);
      OrderPojo order = new OrderPojo();
      order.setId(orderShipPojo.getOrderId());
      order.setUpdateBy(sysLogin.getId());
      order.setOrderStatus(3);
      order.setSendDate(new Date());
      order.setRemarks(orderShipPojo.getRemarks());
      orderService.updateOrderStatus(order);
      FileUtil.alertMessageBySkip("该发货信息修改成功！", "getMyOrderWeb.do");
      return null;
    } else {
      orderShipService.insertOrderShip(orderShipPojo);
      OrderPojo order = new OrderPojo();
      order.setId(orderShipPojo.getOrderId());
      order.setUpdateBy(sysLogin.getId());
      order.setOrderStatus(3);
      order.setSendDate(new Date());
      order.setRemarks(orderShipPojo.getRemarks());
      orderService.updateOrderStatus(order);
      FileUtil.alertMessageBySkip("发货成功！", "getMyOrderWeb.do");
      return null;
    }
  }

  /**
   * 商家中心-售后详情前端页面
   * 
   * @return
   * @throws SQLException
   */
  public String goRefundDetailWeb() throws SQLException {
    ActionContext ac = ActionContext.getContext();
    try {
      if (orderDetailPojo != null && !"".equals(orderDetailPojo.getId())) {
        userOrderRefundPojo =
            userOrderRefundService.findUserOrderRefundByOid(orderDetailPojo.getId());
        ac.put("userOrderRefundPojo", userOrderRefundPojo);
        orderDetailPojo = orderDetailService.getfindByIdOrderDetail(orderDetailPojo.getId());
        ac.put("orderDetailPojo", orderDetailPojo);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return SUCCESS;
  }

  /**
   * 商家中心售后详情-上传凭证
   * 
   * @return
   * @throws Throwable
   */
  public String submitRejectWeb() throws Throwable {
    try {
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
        return null;
      }
      if (userOrderRefundPojo != null) {
        if (upfile != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/orderRefund")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/orderRefund/", upfile);
          /*
           * // 图片压缩 CompressPicture cp = new CompressPicture(); String compressPath =
           * ServletActionContext.getServletContext() .getRealPath("/upfiles/product/small") +
           * File.separator; cp.compressPic(uploadPath, compressPath, file_name, file_name, 300,
           * 300, true);
           */
          userOrderRefundPojo.setRejectImages(file_name);
        } else {
          userOrderRefundPojo.setRejectImages("");
        }
        userOrderRefundService.updateOrderRefundRemarks(userOrderRefundPojo);
        FileUtil.alertMessageBySkip("上传凭证成功！", "goRefundDetailWeb.do?orderDetailPojo.id="
            + userOrderRefundPojo.getDetailId());
      } else {
        FileUtil.alertMessageBySkip("上传凭证失败！", "goRefundWeb.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
    return null;
  }


  public OrderPojo getOrderPojo() {
    return orderPojo;
  }

  public void setOrderPojo(OrderPojo orderPojo) {
    this.orderPojo = orderPojo;
  }

  public List<OrderPojo> getOrderPojos() {
    return orderPojos;
  }

  public void setOrderPojos(List<OrderPojo> orderPojos) {
    this.orderPojos = orderPojos;
  }

  public OrderDetailPojo getOrderDetailPojo() {
    return orderDetailPojo;
  }

  public void setOrderDetailPojo(OrderDetailPojo orderDetailPojo) {
    this.orderDetailPojo = orderDetailPojo;
  }

  public List<OrderDetailPojo> getOrderDetailPojos() {
    return orderDetailPojos;
  }

  public void setOrderDetailPojos(List<OrderDetailPojo> orderDetailPojos) {
    this.orderDetailPojos = orderDetailPojos;
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

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getTestcount() {
    return testcount;
  }

  public void setTestcount(String testcount) {
    this.testcount = testcount;
  }

  public String getIsAll() {
    return isAll;
  }

  public void setIsAll(String isAll) {
    this.isAll = isAll;
  }

  public String getImportupfile() {
    return importupfile;
  }

  public void setImportupfile(String importupfile) {
    this.importupfile = importupfile;
  }

  public SysAreaPojo getSysArea() {
    return sysArea;
  }

  public void setSysArea(SysAreaPojo sysArea) {
    this.sysArea = sysArea;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public UserOrderRefundPojo getUserOrderRefundPojo() {
    return userOrderRefundPojo;
  }

  public void setUserOrderRefundPojo(UserOrderRefundPojo userOrderRefundPojo) {
    this.userOrderRefundPojo = userOrderRefundPojo;
  }

}
