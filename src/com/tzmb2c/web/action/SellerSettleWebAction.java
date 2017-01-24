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

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.ManufacturerSettlePojo;
import com.tzmb2c.web.pojo.ManufacturerWithdrawPojo;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.SellerBankPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserVerifyPojo;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.ManufacturerSettleService;
import com.tzmb2c.web.service.ManufacturerWithdrawService;
import com.tzmb2c.web.service.OrderDetailService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.SellerBankService;
import com.tzmb2c.web.service.UserVerifyService;

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
  @Autowired
  private SellerBankService sellerBankService;
  @Autowired
  private UserVerifyService userVerifyService;
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
  private SellerBankPojo sellerBankPojo;
  private String phonecode;

  public String getPhonecode() {
    return phonecode;
  }

  public void setPhonecode(String phonecode) {
    this.phonecode = phonecode;
  }

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
      if (manufacturerWithdrawPojo != null) {
        map.put("number", manufacturerWithdrawPojo.getNumber());
      }
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
      if (manufacturerWithdrawPojo != null) {
        map.put("number", manufacturerWithdrawPojo.getNumber());
      }
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
          Map<String, Object> map1 = new HashMap<String, Object>();
          map1.put("userId", uid);
          List<SellerBankPojo> sellerBankPojos = sellerBankService.listPage(map1);
          if (sellerBankPojos != null && sellerBankPojos.size() > 0) {
            SellerBankPojo sellerBank = sellerBankPojos.get(0);
            if (sellerBank.getStatus() == 1) {
              manufacturerWithdrawPojo.setUserId(uid);
              manufacturerWithdrawPojo
                  .setNumber(String.valueOf((int) ((Math.random() * 9 + 1) * 100000)));
              // manufacturerWithdrawPojo.setWithdrawDate(new Date());
              manufacturerWithdrawPojo.setStatus(0);
              manufacturerWithdrawPojo.setBankName(sellerBank.getBankName());
              manufacturerWithdrawPojo.setBankCardNo(sellerBank.getBankCardNo());
              manufacturerWithdrawPojo.setUserName(sellerBank.getUserName());
              manufacturerWithdrawPojo.setCreateBy(uid);
              manufacturerWithdrawPojo.setUpdateBy(uid);
              try {
                int i =
                    manufacturerWithdrawService
                        .insertManufacturerWithdraw(manufacturerWithdrawPojo);
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
              result = "4";
            }
          } else {
            result = "3";
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
   * 账户提现列表导出
   * 
   * @return
   * @throws Exception
   */
  public void manufacturerWithdrawExcel() throws Exception {
    UserUtil.getWebUser();
    SysLoginPojo sysLoginPojo = UserUtil.getWebUser();
    Map<String, Object> map = new HashMap<String, Object>();
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
    /*
     * if (testcount != null && !testcount.equals("")) { map.put("userIds", a); } else {
     * map.put("notuserIds", a); }
     */
    // 获取列表
    map.put("userId", sysLoginPojo.getId());
    if (manufacturerWithdrawPojo != null) {
      map.put("number", manufacturerWithdrawPojo.getNumber());
    }
    map.put("orderBy", "a.update_date desc");
    List<ManufacturerWithdrawPojo> manufacturerWithdrawPojos =
        manufacturerWithdrawService.getManufacturerWithdrawList(map);
    WritableWorkbook wwb = null;
    OutputStream ots = null;
    String filePath = null;
    String fileName = null;
    fileName = "提现记录.xls";

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
      String[] cellStr = {"编号", "状态", "提现金额", "提现手续费", "银行名", "银行卡号", "开户人姓名", "提现日", "申请时间"};
      for (int i = 0; i < cellStr.length; i++) {
        sheet.addCell(new Label(i, 0, cellStr[i]));
        if (i == cellStr.length - 1) {
          sheet.addCell(new Label(i, 0, cellStr[i]));

        }
      }
      for (int j = 1, i = 1; j <= manufacturerWithdrawPojos.size(); j++) {
        ManufacturerWithdrawPojo manufacturerWithdrawPojo = manufacturerWithdrawPojos.get(j - 1);
        // System.out.println(order.getId());
        // List<OrderDetailPojo> list2 =
        // orderDetailService.getfindByUserIdOrderDetail(order.getId());
        // for (int s = 1; s <= list2.size(); s++) {
        int celli = 0;
        sheet.addCell(new Label(celli, i, manufacturerWithdrawPojo.getNumber()));
        sheet.addCell(new Label(++celli, i, manufacturerWithdrawPojo.getStatusName()));
        sheet.addCell(new Label(++celli, i, String.valueOf(manufacturerWithdrawPojo
            .getWithdrawAmount())));
        sheet.addCell(new Label(++celli, i, String.valueOf(manufacturerWithdrawPojo
            .getWithdrawalFee())));
        sheet.addCell(new Label(++celli, i, manufacturerWithdrawPojo.getBankName()));
        sheet.addCell(new Label(++celli, i, manufacturerWithdrawPojo.getBankCardNo()));
        sheet.addCell(new Label(++celli, i, manufacturerWithdrawPojo.getUserName()));
        sheet.addCell(new Label(++celli, i, manufacturerWithdrawPojo.getWithdrawDateStr()));
        sheet.addCell(new Label(++celli, i, manufacturerWithdrawPojo.getCreateDateStr()));
        i++;
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

    // FileUtil.copyFile(filePath,
    // ServletActionContext.getServletContext().getRealPath("/temp/"+fileName));
    // File delfile = new File(filePath);
    // if (delfile.isFile() && delfile.exists()) {
    // delfile.delete();
    // }
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
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditSellerBank() throws Exception {
    SysLoginPojo sysLoginPojo = UserUtil.getWebUser();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("userId", sysLoginPojo.getId());
    List<SellerBankPojo> sellerBankPojos = sellerBankService.listPage(map);
    if (sellerBankPojos.size() > 0) {
      sellerBankPojo = sellerBankPojos.get(0);
    }
    return SUCCESS;
  }


  public String goWithdrawals() throws Exception {
    SysLoginPojo sysLoginPojo = UserUtil.getWebUser();
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      map.put("userId", sysLoginPojo.getId());
      List<SellerBankPojo> sellerBankList = sellerBankService.listPage(map);
      if (sellerBankList.size() > 0) {
        if (sellerBankList.get(0).getStatus() == 1) {
          sellerBankPojo = sellerBankList.get(0);
        } else {
          FileUtil.alertMessageBySkip("你的账户还未通过审核哦！", "goWithdrawWeb.do");
          return null;
        }
      } else {
        FileUtil.alertMessageBySkip("你还未绑定银行卡哦！", "goWithdrawWeb.do");
        return null;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 编辑提交
   * 
   * @return
   * @throws Throwable
   */
  public String editSellerBank() throws Throwable {
    SysLoginPojo user = UserUtil.getWebUser();
    UserVerifyPojo userVerify = new UserVerifyPojo();
    userVerify.setLoginname(sellerBankPojo.getPhone());
    userVerify = userVerifyService.findNewestByPhone(userVerify);
    if (userVerify == null || userVerify.getCaptcha() == null
        || !phonecode.equals(userVerify.getCaptcha())) {
      FileUtil.alertMessageBySkip("验证码错误！请重新输入！", "goWithdrawals.do");
      return null;
    }
    if (user != null && sellerBankPojo != null) {
      if (sellerBankPojo.getId() != null && sellerBankPojo.getId() > 0) {
        sellerBankPojo.setUserId(user.getId());
        sellerBankPojo.setStatus(0);
        sellerBankPojo.setUpdateBy(user.getId());
        sellerBankPojo.setUpdateDate(new Date());
        try {
          sellerBankService.update(sellerBankPojo);
          FileUtil.alertMessageBySkip("提交成功！", "goEditSellerBank.do");
        } catch (Exception e) {
          e.printStackTrace();
          FileUtil.alertMessageBySkip("提交失败！", "goWithdrawals.do");
        }
      } else {
        sellerBankPojo.setUserId(user.getId());
        sellerBankPojo.setStatus(0);
        sellerBankPojo.setCreateBy(user.getId());
        sellerBankPojo.setCreateDate(new Date());
        sellerBankPojo.setUpdateBy(user.getId());
        sellerBankPojo.setUpdateDate(new Date());
        try {
          sellerBankService.add(sellerBankPojo);
          FileUtil.alertMessageBySkip("新增成功！", "goEditSellerBank.do");
        } catch (Exception e) {
          e.printStackTrace();
          FileUtil.alertMessageBySkip("新增失败！", "goWithdrawals.do");
        }

      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goSellerBank.do");
    }

    return null;
  }

  public SellerBankPojo getSellerBankPojo() {
    return sellerBankPojo;
  }

  public void setSellerBankPojo(SellerBankPojo sellerBankPojo) {
    this.sellerBankPojo = sellerBankPojo;
  }
}
