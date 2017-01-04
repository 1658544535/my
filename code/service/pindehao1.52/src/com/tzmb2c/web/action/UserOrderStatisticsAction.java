package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ProductStockPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserOrderStatisticsPojo;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductStockService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserOrderStatisticsService;

public class UserOrderStatisticsAction extends SuperAction {

  @Autowired
  private UserOrderStatisticsService userOrderStatisticsService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private ProductStockService productStockService;
  @Autowired
  private OrderService orderService;


  private UserOrderStatisticsPojo userOrderStatistics;
  private ProductPojo productPojo;
  private SysLoginPojo sysLoginPojo;
  private ProductStockPojo productStockPojo;
  private String result;
  private String[] tids;
  private String strProductPrices;
  private Double buyPrice;
  private String firstDay, lastDay;
  private OrderPojo order;


  public OrderPojo getOrder() {
    return order;
  }

  public void setOrder(OrderPojo order) {
    this.order = order;
  }

  public UserOrderStatisticsPojo getUserOrderStatistics() {
    return userOrderStatistics;
  }

  public void setUserOrderStatistics(UserOrderStatisticsPojo userOrderStatistics) {
    this.userOrderStatistics = userOrderStatistics;
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

  public String getUserOrderStatisticsCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(userOrderStatisticsService.userOrderStatisticsAllCount(userOrderStatistics));

    return SUCCESS;
  }

  public String userOrderStatisticsAllList() {
    JSONArray json =
        JSONArray.fromObject(userOrderStatisticsService.userOrderStatisticsAllList(
            userOrderStatistics, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String jiesUserOrderStatistics() throws SQLException {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    // 获取前月的第一天
    Calendar cal_1 = Calendar.getInstance();// 获取当前日期
    cal_1.add(Calendar.MONTH, -1);
    cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
    firstDay = format.format(cal_1.getTime());
    // 获取前月的最后一天
    Calendar cale = Calendar.getInstance();
    cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
    lastDay = format.format(cale.getTime());
    // 分割年夜日
    String[] strarray = lastDay.split("-");
    userOrderStatistics.setYear(strarray[0]);
    userOrderStatistics.setMonth(strarray[1]);
    userOrderStatistics.setIsSettleAccounts("0");
    // 统计
    order = orderService.orderAllListSettleOne(firstDay, lastDay, userOrderStatistics.getRemarks());
    userOrderStatistics.setUserId(order.getUserId());
    userOrderStatistics.setMoney(order.getPayY());
    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      userOrderStatistics.prePersist(loginPojo);
    }

    if (order != null) {
      if (userOrderStatisticsService.userOrderStatisticsAllList(userOrderStatistics, page) != null) {
        FileUtil.alertMessageBySkip("该用户上月订单已经统计过了", "userOrderStatistics.do");
      } else {
        userOrderStatisticsService.insertUserOrderStatistics(userOrderStatistics);
        FileUtil.alertMessageBySkip("统计成功！", "userOrderStatistics.do");
      }
    } else {
      FileUtil.alertMessageBySkip("找不到该用户", "userOrderStatistics.do");
    }

    return null;
  }

  public String userOrderStatisticsDeleteId() {
    if (tids != null) {
      userOrderStatisticsService.userOrderStatisticsDeleteId(tids);
      // FileUtil.alertMessageBySkip("删除成功", "gomanageTicketRule.do");
      FileUtil.alertMessageBySkip("结算成功！", "userOrderStatistics.do");
    } else {
      FileUtil.alertMessageBySkip("结算失败！", "userOrderStatistics.do");
    }

    return null;
  }

  public String deleUserOrderStatistics() throws SQLException {
    try {
      userOrderStatisticsService.delUserOrderStatistics(userOrderStatistics.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String goFindUserOrderStatistics() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("userOrderStatisticsPojo",
        userOrderStatisticsService.findUserOrderStatisticsById(userOrderStatistics.getId()));
    ac.put("sysLoginPojo", sysLoginService.findSysLoginById(userOrderStatistics.getUserId()));
    return SUCCESS;
  }

  public String updateUserOrderStatistics() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      userOrderStatistics.preUpdate(loginPojo);
    }

    userOrderStatisticsService.updateUserOrderStatistics(userOrderStatistics);
    FileUtil.alertMessageBySkip("修改成功！", "userOrderStatistics.do");

    return null;
  }

  public String adduserOrderStatisticsWeb() throws Exception {
    //
    return SUCCESS;
  }

  public String userOrderStatisticsWeb() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("userOrderStatisticslist",
        userOrderStatisticsService.findUserOrderStatisticsByUserId(userOrderStatistics.getUserId()));
    return SUCCESS;
  }

  public String deluserOrderStatisticsWeb() throws Exception {
    try {
      userOrderStatisticsService.delUserOrderStatistics(userOrderStatistics.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public ProductPojo getProductPojo() {
    return productPojo;
  }

  public void setProductPojo(ProductPojo productPojo) {
    this.productPojo = productPojo;
  }

  public SysLoginPojo getSysLoginPojo() {
    return sysLoginPojo;
  }

  public void setSysLoginPojo(SysLoginPojo sysLoginPojo) {
    this.sysLoginPojo = sysLoginPojo;
  }

  public ProductStockPojo getProductStockPojo() {
    return productStockPojo;
  }

  public void setProductStockPojo(ProductStockPojo productStockPojo) {
    this.productStockPojo = productStockPojo;
  }

  public String getStrProductPrices() {
    return strProductPrices;
  }

  public void setStrProductPrices(String strProductPrices) {
    this.strProductPrices = strProductPrices;
  }

  public Double getBuyPrice() {
    return buyPrice;
  }

  public void setBuyPrice(Double buyPrice) {
    this.buyPrice = buyPrice;
  }
}
