package com.tzmb2c.web.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.ManufacturerSettlePojo;
import com.tzmb2c.web.pojo.ManufacturerWithdrawPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.ManufacturerSettleService;
import com.tzmb2c.web.service.ManufacturerWithdrawService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductSkuLinkService;

public class SellerSettleWebAction extends SuperAction {

  @Autowired
  private ManufacturerSettleService manufacturerSettleService;
  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private OrderDetailService orderDetailService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private SellerService sellerService;
  @Autowired
  private ManufacturerWithdrawService manufacturerWithdrawService;

  private ManufacturerSettlePojo manufacturerSettlePojo;
  private List<ManufacturerSettlePojo> manufacturerSettlePojos;
  private ManufacturerPojo manufacturerPojo;
  private List<ManufacturerPojo> manufacturerPojos;
  private Double amount;// 结算总额
  private Double balance;// 结算余额/可提现金额
  private String result;
  private OrderPojo orderPojo;
  private List<OrderPojo> orderPojos;
  private ManufacturerWithdrawPojo manufacturerWithdrawPojo;
  private List<ManufacturerWithdrawPojo> manufacturerWithdrawPojos;

  /**
   * 商家结算前端页面
   * 
   * @throws SQLException
   */
  public String goSettleWeb() throws SQLException {
    SysLoginPojo sysLoginPojo = UserUtil.getWebUser();
    int count = 0;
    if (sysLoginPojo != null) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("userId", sysLoginPojo.getId());
      map.put("statusAll", 1);
      count = manufacturerSettleService.getManufacturerSettleCount(map);

      manufacturerPojo = manufacturerService.findManufacturerByUserId(sysLoginPojo.getId());
      if (manufacturerPojo != null) {
        amount = manufacturerPojo.getAmount();
        balance = manufacturerPojo.getBalance();
        manufacturerWithdrawPojos = manufacturerWithdrawService.getManufacturerWithdrawList(map);
        /*
         * if(manufacturerWithdrawPojos!=null){ double sumWithdraw=0.00;
         * for(ManufacturerWithdrawPojo manufacturerWithdrawPojo:manufacturerWithdrawPojos){
         * if(manufacturerWithdrawPojo.getStatus()!=1 && manufacturerWithdrawPojo.getStatus()!=3 &&
         * manufacturerWithdrawPojo.getStatus()!=4){
         * sumWithdraw=sumWithdraw+manufacturerWithdrawPojo.getWithdrawAmount(); } }
         * if(sumWithdraw!=0.00){ balance=balance-sumWithdraw; } }
         */
      }
    }
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(count);

    return SUCCESS;
  }

  public String goSettleWebList() throws SQLException {
    SysLoginPojo sysLoginPojo = UserUtil.getWebUser();
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (sysLoginPojo != null) {
      map.put("userId", sysLoginPojo.getId());
      map.put("statusAll", 1);
      manufacturerSettlePojos = manufacturerSettleService.getManufacturerSettleList(map);
    }
    JSONArray json = JSONArray.fromObject(manufacturerSettlePojos);
    page.setResult(json.toString());
    return SUCCESS;
  }

  public ManufacturerSettlePojo getManufacturerSettlePojo() {
    return manufacturerSettlePojo;
  }

  public void setManufacturerSettlePojo(ManufacturerSettlePojo manufacturerSettlePojo) {
    this.manufacturerSettlePojo = manufacturerSettlePojo;
  }

  public List<ManufacturerSettlePojo> getManufacturerSettlePojos() {
    return manufacturerSettlePojos;
  }

  public void setManufacturerSettlePojos(List<ManufacturerSettlePojo> manufacturerSettlePojos) {
    this.manufacturerSettlePojos = manufacturerSettlePojos;
  }

  public List<ManufacturerWithdrawPojo> getManufacturerWithdrawPojos() {
    return manufacturerWithdrawPojos;
  }

  public void setManufacturerWithdrawPojos(List<ManufacturerWithdrawPojo> manufacturerWithdrawPojos) {
    this.manufacturerWithdrawPojos = manufacturerWithdrawPojos;
  }

  public ManufacturerPojo getManufacturerPojo() {
    return manufacturerPojo;
  }

  public void setManufacturerPojo(ManufacturerPojo manufacturerPojo) {
    this.manufacturerPojo = manufacturerPojo;
  }

  public List<ManufacturerPojo> getManufacturerPojos() {
    return manufacturerPojos;
  }

  public void setManufacturerPojos(List<ManufacturerPojo> manufacturerPojos) {
    this.manufacturerPojos = manufacturerPojos;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  /**
   * 商家确认结算
   * 
   * @return
   * @throws SQLException
   */
  public synchronized String doSettleWebCheck() throws SQLException {
    SysLoginPojo sysLoginPojo = UserUtil.getWebUser();
    result = "0";

    if (sysLoginPojo != null && manufacturerSettlePojo != null
        && manufacturerSettlePojo.getId() != null && manufacturerSettlePojo.getId() > 0) {
      manufacturerSettlePojo =
          manufacturerSettleService.getManufacturerSettleById(manufacturerSettlePojo.getId());
      if (manufacturerSettlePojo != null && manufacturerSettlePojo.getStatus() == 1) {
        // 1-审核通过才能确认
        ManufacturerSettlePojo manufacturerSettle = new ManufacturerSettlePojo();
        manufacturerSettle.setStatus(2);
        manufacturerSettle.setUpdateBy(sysLoginPojo.getId());
        manufacturerSettle.setUpdateDate(new Date());
        manufacturerSettle.setId(manufacturerSettlePojo.getId());
        try {
          int i = manufacturerSettleService.updateSettleInfo(manufacturerSettle);
          if (i > 0) {
            double amount = manufacturerSettlePojo.getSettleAmount();
            ManufacturerPojo manufacturer = new ManufacturerPojo();
            manufacturer.setUserId(sysLoginPojo.getId());
            manufacturer.setAddAmount(amount);
            manufacturer.setAddBalance(amount);
            manufacturerService.updateManufacturer(manufacturer);
            result = "1";
          }
        } catch (Exception e) {
          e.printStackTrace();
          result = "0";
        }
      }
    }

    return SUCCESS;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  /**
   * 对账单-导出订单结算详情
   * 
   * @return
   * @throws IOException
   */
  public void getOrderSetterExcel() throws IOException {
    sellerService.getOrderSetterExcel(manufacturerSettlePojo);
  }

  public OrderPojo getOrderPojo() {
    return orderPojo;
  }

  public void setOrderPojo(OrderPojo orderPojo) {
    this.orderPojo = orderPojo;
  }

  public ManufacturerWithdrawPojo getManufacturerWithdrawPojo() {
    return manufacturerWithdrawPojo;
  }

  public void setManufacturerWithdrawPojo(ManufacturerWithdrawPojo manufacturerWithdrawPojo) {
    this.manufacturerWithdrawPojo = manufacturerWithdrawPojo;
  }

  public List<OrderPojo> getOrderPojos() {
    return orderPojos;
  }

  public void setOrderPojos(List<OrderPojo> orderPojos) {
    this.orderPojos = orderPojos;
  }


  /**
   * 商家提现前端页面
   * 
   * @throws SQLException
   */
  public String goWithdrawWebCount() throws SQLException {
    SysLoginPojo sysLoginPojo = UserUtil.getWebUser();
    int count = 0;
    if (sysLoginPojo != null) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("userId", sysLoginPojo.getId());
      manufacturerWithdrawPojos = manufacturerWithdrawService.getManufacturerWithdrawList(map);
      count = manufacturerWithdrawPojos.size();
      manufacturerPojo = manufacturerService.findManufacturerByUserId(sysLoginPojo.getId());
      if (manufacturerPojo != null) {
        balance = manufacturerPojo.getBalance();
      }
    }
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(count);

    return SUCCESS;
  }

  public String goWithdrawWebList() throws SQLException {
    SysLoginPojo sysLoginPojo = UserUtil.getWebUser();
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (sysLoginPojo != null) {
      map.put("userId", sysLoginPojo.getId());
      manufacturerWithdrawPojos = manufacturerWithdrawService.getManufacturerWithdrawList(map);
    }
    JSONArray json = JSONArray.fromObject(manufacturerWithdrawPojos);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 商家申请提现
   * 
   * @return
   * @throws SQLException
   */
  public synchronized String doWithdrawWebCheck() throws SQLException {
    SysLoginPojo sysLoginPojo = UserUtil.getWebUser();
    result = "0";
    if (sysLoginPojo != null && manufacturerWithdrawPojo != null
        && manufacturerWithdrawPojo.getWithdrawAmount() != null
        && manufacturerWithdrawPojo.getWithdrawAmount() >= 100.00) {
      long uid = sysLoginPojo.getId();
      ManufacturerPojo manufacturerPojo = manufacturerService.findManufacturerByUserId(uid);
      if (manufacturerPojo != null
          && manufacturerWithdrawPojo.getWithdrawAmount().doubleValue() <= manufacturerPojo
              .getBalance().doubleValue()) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", uid);
        List<ManufacturerWithdrawPojo> manufacturerWithdraws =
            manufacturerWithdrawService.getManufacturerWithdrawByUserId(map);
        if (manufacturerWithdraws.size() == 0 || manufacturerWithdraws.get(0).getPeriod() >= 1.00) {
          manufacturerWithdrawPojo.setUserId(uid);
          // manufacturerWithdrawPojo.setWithdrawDate(new Date());
          manufacturerWithdrawPojo.setStatus(0);
          manufacturerWithdrawPojo.setCreateBy(uid);
          manufacturerWithdrawPojo.setUpdateBy(uid);
          try {
            int i =
                manufacturerWithdrawService.insertManufacturerWithdraw(manufacturerWithdrawPojo);
            if (i > 0) {
              manufacturerPojo = new ManufacturerPojo();
              manufacturerPojo.setAddBalance(-manufacturerWithdrawPojo.getWithdrawAmount());
              manufacturerPojo.setUserId(uid);
              manufacturerService.updateManufacturer(manufacturerPojo);
              result = "1";
            }
          } catch (Exception e) {
            e.printStackTrace();
            result = "0";
          }
        } else {
          result = "2";
        }
      }
    }
    return SUCCESS;
  }

  /**
   * 商家取消申请提现
   * 
   * @return
   * @throws SQLException
   */
  public synchronized String doWithdrawWebUncheck() throws SQLException {
    SysLoginPojo sysLoginPojo = UserUtil.getWebUser();
    result = "0";

    if (sysLoginPojo != null && manufacturerWithdrawPojo != null
        && manufacturerWithdrawPojo.getId() != null && manufacturerWithdrawPojo.getId() > 0) {
      manufacturerWithdrawPojo =
          manufacturerWithdrawService.getManufacturerWithdrawById(manufacturerWithdrawPojo.getId());
      if (manufacturerWithdrawPojo != null && manufacturerWithdrawPojo.getStatus() == 0) {
        // 申请状态才能取消
        ManufacturerWithdrawPojo withdraw = new ManufacturerWithdrawPojo();
        withdraw.setStatus(1);
        withdraw.setUpdateBy(sysLoginPojo.getId());
        withdraw.setUpdateDate(new Date());
        withdraw.setId(manufacturerWithdrawPojo.getId());
        try {
          int i = manufacturerWithdrawService.updateWithdrawInfo(withdraw);
          if (i > 0) {
            manufacturerPojo = new ManufacturerPojo();
            manufacturerPojo.setUserId(sysLoginPojo.getId());
            manufacturerPojo.setAddBalance(manufacturerWithdrawPojo.getWithdrawAmount());
            manufacturerService.updateManufacturer(manufacturerPojo);
            result = "1";
          }
        } catch (Exception e) {
          e.printStackTrace();
          result = "0";
        }
      }
    }
    return SUCCESS;
  }

  /**
   * 后台运营不通过商家提现申请
   * 
   * @return
   * @throws SQLException
   */
  public synchronized String uncheckManufacturerWithdraw() throws SQLException {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && manufacturerWithdrawPojo != null
        && manufacturerWithdrawPojo.getId() != null && manufacturerWithdrawPojo.getId() > 0) {
      manufacturerWithdrawPojo =
          manufacturerWithdrawService.getManufacturerWithdrawById(manufacturerWithdrawPojo.getId());
      if (manufacturerWithdrawPojo != null
          && (manufacturerWithdrawPojo.getStatus() == 0 || manufacturerWithdrawPojo.getStatus() == 2)) {
        // 0-待审核 2-待提现 可取消审核
        ManufacturerWithdrawPojo withdraw = new ManufacturerWithdrawPojo();
        withdraw.setStatus(3);
        withdraw.setUpdateDate(new Date());
        withdraw.setUpdateBy(user.getId());
        withdraw.setId(manufacturerWithdrawPojo.getId());
        try {
          int i = manufacturerWithdrawService.updateWithdrawInfo(withdraw);
          if (i > 0) {
            manufacturerPojo = new ManufacturerPojo();
            manufacturerPojo.setUserId(manufacturerWithdrawPojo.getUserId());
            manufacturerPojo.setAddBalance(manufacturerWithdrawPojo.getWithdrawAmount());
            manufacturerService.updateManufacturer(manufacturerPojo);
            this.result = "1";
          }
        } catch (Exception e) {
          e.printStackTrace();
          this.result = "0";
        }
      }
    }
    return SUCCESS;
  }

}
