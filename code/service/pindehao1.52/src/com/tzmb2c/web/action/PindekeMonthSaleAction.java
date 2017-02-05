/*
 * 文 件 名: PindekeMonthSaleAction.java 创 建 人: admin 创建时间: 2017-01-10
 */
package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.util.UtilDate;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.OrderPojo;
import com.tzmb2c.web.pojo.PindekeMonthSalePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserDealLogPojo;
import com.tzmb2c.web.pojo.UserPindekeInfoPojo;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.PindekeMonthSaleService;
import com.tzmb2c.web.service.UserDealLogService;
import com.tzmb2c.web.service.UserPindekeInfoService;

public class PindekeMonthSaleAction extends SuperAction {
  @Autowired
  private PindekeMonthSaleService pindekeMonthSaleService;
  @Autowired
  private UserPindekeInfoService userPindekeInfoService;
  @Autowired
  private UserDealLogService userDealLogService;
  @Autowired
  private OrderService orderService;
  private PindekeMonthSalePojo pindekeMonthSalePojo;
  private Long id;
  private String[] tids;
  private String result;
  private String nameOrPhone, beginTimeStr, endTimeStr, yearStr, monthStr, thirtyDayStr;

  public String getThirtyDayStr() {
    return thirtyDayStr;
  }

  public void setThirtyDayStr(String thirtyDayStr) {
    this.thirtyDayStr = thirtyDayStr;
  }

  public String getNameOrPhone() {
    return nameOrPhone;
  }

  public void setNameOrPhone(String nameOrPhone) {
    this.nameOrPhone = nameOrPhone;
  }

  public String getBeginTimeStr() {
    return beginTimeStr;
  }

  public void setBeginTimeStr(String beginTimeStr) {
    this.beginTimeStr = beginTimeStr;
  }

  public String getEndTimeStr() {
    return endTimeStr;
  }

  public void setEndTimeStr(String endTimeStr) {
    this.endTimeStr = endTimeStr;
  }

  public String getYearStr() {
    return yearStr;
  }

  public void setYearStr(String yearStr) {
    this.yearStr = yearStr;
  }

  public String getMonthStr() {
    return monthStr;
  }

  public void setMonthStr(String monthStr) {
    this.monthStr = monthStr;
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

  public PindekeMonthSalePojo getPindekeMonthSalePojo() {
    return pindekeMonthSalePojo;
  }

  public void setPindekeMonthSalePojo(PindekeMonthSalePojo pindekeMonthSalePojo) {
    this.pindekeMonthSalePojo = pindekeMonthSalePojo;
  }

  /**
   * 查询全部条数
   */
  public String goPindekeMonthSale() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (nameOrPhone != null && !"".equals(nameOrPhone.trim())) {
        map.put("nameOrPhone", nameOrPhone);
      }
      if (beginTimeStr != null && !"".equals(beginTimeStr.trim()) && endTimeStr != null
          && !"".equals(endTimeStr.trim())) {
        map.put("beginTimeStr", beginTimeStr);
        map.put("endTimeStr", endTimeStr);
      }
      if (yearStr != null && !"".equals(yearStr.trim()) && monthStr != null
          && !"".equals(monthStr.trim())) {
        map.put("sectionTime", yearStr + monthStr);
      }
      map.put("type", 1);
      int i = pindekeMonthSaleService.countBy2(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String pindekeMonthSaleRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (nameOrPhone != null && !"".equals(nameOrPhone.trim())) {
      map.put("nameOrPhone", nameOrPhone);
    }
    if (beginTimeStr != null && !"".equals(beginTimeStr.trim()) && endTimeStr != null
        && !"".equals(endTimeStr.trim())) {
      map.put("beginTimeStr", beginTimeStr);
      map.put("endTimeStr", endTimeStr);
    }
    if (yearStr != null && !"".equals(yearStr.trim()) && monthStr != null
        && !"".equals(monthStr.trim())) {
      map.put("sectionTime", yearStr + monthStr);
    } else {
      map.put("sectionTime", UtilDate.getDate().substring(0, 6));
    }
    map.put("type", 1);
    try {
      int i = pindekeMonthSaleService.countBy2(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String pindekeMonthSaleList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (nameOrPhone != null && !"".equals(nameOrPhone.trim())) {
      map.put("nameOrPhone", nameOrPhone);
    }
    if (beginTimeStr != null && !"".equals(beginTimeStr.trim()) && endTimeStr != null
        && !"".equals(endTimeStr.trim())) {
      map.put("beginTimeStr", beginTimeStr);
      map.put("endTimeStr", endTimeStr);
    }
    if (yearStr != null && !"".equals(yearStr.trim()) && monthStr != null
        && !"".equals(monthStr.trim())) {
      map.put("sectionTime", yearStr + monthStr);
    } else {
      map.put("sectionTime", UtilDate.getDate().substring(0, 6));
    }
    map.put("type", 1);
    map.put("orderBy", " pms.ranking asc ");
    List<PindekeMonthSalePojo> pindekeMonthSaleList = null;
    try {
      pindekeMonthSaleList = pindekeMonthSaleService.listPage2(map);
      JSONArray json = JSONArray.fromObject(pindekeMonthSaleList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String goPindekeMonthSaleInviter() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (nameOrPhone != null && !"".equals(nameOrPhone.trim())) {
        map.put("nameOrPhone", nameOrPhone);
      }
      if (beginTimeStr != null && !"".equals(beginTimeStr.trim()) && endTimeStr != null
          && !"".equals(endTimeStr.trim())) {
        map.put("beginTimeStr", beginTimeStr);
        map.put("endTimeStr", endTimeStr);
      }
      Date date = new Date();
      if (thirtyDayStr != null && "1".equals(thirtyDayStr.trim())) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -30);
        String beginTimeStr = StringUtil.dateString(c.getTime());

        // beginTimeStr = StringUtil.dateString(DateUtil.addDay(date, -30));
        String endTimeStr = StringUtil.dateString(date);
        map.put("beginTimeStr", beginTimeStr);
        map.put("endTimeStr", endTimeStr);
      }
      map.put("type", 2);
      // Calendar c = Calendar.getInstance();
      // c = Calendar.getInstance();
      // c.setTime(date);
      // c.add(Calendar.MONTH, -1);
      // map.put("createDateStr", StringUtil.dateString(c.getTime()));
      int i = pindekeMonthSaleService.countBy2(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String pindekeMonthSaleRowCntInviter() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (nameOrPhone != null && !"".equals(nameOrPhone.trim())) {
      map.put("nameOrPhone", nameOrPhone);
    }
    if (beginTimeStr != null && !"".equals(beginTimeStr.trim()) && endTimeStr != null
        && !"".equals(endTimeStr.trim())) {
      map.put("beginTimeStr", beginTimeStr);
      map.put("endTimeStr", endTimeStr);
    }
    Date date = new Date();
    if (thirtyDayStr != null && "1".equals(thirtyDayStr.trim())) {
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      c.add(Calendar.DATE, -30);
      String beginTimeStr = StringUtil.dateString(c.getTime());

      // beginTimeStr = StringUtil.dateString(DateUtil.addDay(date, -30));
      String endTimeStr = StringUtil.dateString(date);
      map.put("beginTimeStr", beginTimeStr);
      map.put("endTimeStr", endTimeStr);
    }
    map.put("type", 2);
    // Calendar c = Calendar.getInstance();
    // c = Calendar.getInstance();
    // c.setTime(date);
    // c.add(Calendar.MONTH, -1);
    // map.put("createDateStr", StringUtil.dateString(c.getTime()));
    try {
      int i = pindekeMonthSaleService.countBy2(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String pindekeMonthSaleListInviter() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (nameOrPhone != null && !"".equals(nameOrPhone.trim())) {
      map.put("nameOrPhone", nameOrPhone);
    }
    if (beginTimeStr != null && !"".equals(beginTimeStr.trim()) && endTimeStr != null
        && !"".equals(endTimeStr.trim())) {
      map.put("beginTimeStr", beginTimeStr);
      map.put("endTimeStr", endTimeStr);
    }
    Date date = new Date();
    if (thirtyDayStr != null && "1".equals(thirtyDayStr.trim())) {
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      c.add(Calendar.DATE, -30);
      String beginTimeStr = StringUtil.dateString(c.getTime());

      // beginTimeStr = StringUtil.dateString(DateUtil.addDay(date, -30));
      String endTimeStr = StringUtil.dateString(date);
      map.put("beginTimeStr", beginTimeStr);
      map.put("endTimeStr", endTimeStr);
    }
    map.put("type", 2);
    // Calendar c = Calendar.getInstance();
    // c = Calendar.getInstance();
    // c.setTime(date);
    // c.add(Calendar.MONTH, -1);
    // map.put("createDateStr", StringUtil.dateString(c.getTime()));
    map.put("orderBy", " pms.total desc,pms.id asc ");
    List<PindekeMonthSalePojo> pindekeMonthSaleList = null;
    try {
      pindekeMonthSaleList = pindekeMonthSaleService.listPage2(map);
      JSONArray json = JSONArray.fromObject(pindekeMonthSaleList);
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
  public String goAddPindekeMonthSale() throws Exception {
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
    if (user != null && pindekeMonthSalePojo != null) {
      pindekeMonthSalePojo.setCreateBy(user.getId());
      pindekeMonthSalePojo.setCreateDate(new Date());
      pindekeMonthSalePojo.setUpdateBy(user.getId());
      pindekeMonthSalePojo.setUpdateDate(new Date());
      try {
        pindekeMonthSaleService.add(pindekeMonthSalePojo);
        FileUtil.alertMessageBySkip("新增成功！", "goPindekeMonthSale.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goAddPindekeMonthSale.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goPindekeMonthSale.do");
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditPindekeMonthSale() throws Exception {
    if (id != null && id > 0) {
      pindekeMonthSalePojo = pindekeMonthSaleService.getById(id);
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
    if (user != null && pindekeMonthSalePojo != null) {
      pindekeMonthSalePojo.setUpdateBy(user.getId());
      pindekeMonthSalePojo.setUpdateDate(new Date());
      try {
        pindekeMonthSaleService.update(pindekeMonthSalePojo);
        FileUtil.alertMessageBySkip("提交成功！", "goPindekeMonthSale.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！",
            "goEditPindekeMonthSale.do?id=" + pindekeMonthSalePojo.getId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goPindekeMonthSale.do");
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
      pindekeMonthSalePojo = new PindekeMonthSalePojo();
      pindekeMonthSalePojo.setId(id);
      pindekeMonthSalePojo.setUpdateBy(user.getId());
      pindekeMonthSalePojo.setUpdateDate(new Date());
      try {
        pindekeMonthSaleService.update(pindekeMonthSalePojo);
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
      pindekeMonthSalePojo = new PindekeMonthSalePojo();
      pindekeMonthSalePojo.setId(id);
      pindekeMonthSalePojo.setUpdateBy(user.getId());
      pindekeMonthSalePojo.setUpdateDate(new Date());
      try {
        pindekeMonthSaleService.update(pindekeMonthSalePojo);
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
        pindekeMonthSaleService.delete(id);
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
      pindekeMonthSalePojo = new PindekeMonthSalePojo();
      for (String tid : tids) {
        pindekeMonthSalePojo.setId(Long.valueOf(tid));
        pindekeMonthSalePojo.setUpdateBy(user.getId());
        pindekeMonthSalePojo.setUpdateDate(new Date());
        try {
          pindekeMonthSaleService.update(pindekeMonthSalePojo);
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
      pindekeMonthSalePojo = new PindekeMonthSalePojo();
      for (String tid : tids) {
        pindekeMonthSalePojo.setId(Long.valueOf(tid));
        pindekeMonthSalePojo.setUpdateBy(user.getId());
        pindekeMonthSalePojo.setUpdateDate(new Date());
        try {
          pindekeMonthSaleService.update(pindekeMonthSalePojo);
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
          pindekeMonthSaleService.delete(Long.valueOf(tid));
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
   * 根据id返佣
   * 
   * @return
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String settlePindekeMonthSale() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    try {
      if (user != null && id != null && id > 0) {
        pindekeMonthSalePojo = pindekeMonthSaleService.getById(id);
        if (pindekeMonthSalePojo != null) {
          PindekeMonthSalePojo pindekeMonthSale = new PindekeMonthSalePojo();
          pindekeMonthSale.setId(id);
          pindekeMonthSale.setIsSettle(1);
          pindekeMonthSale.setUpdateBy(user.getId());
          UserPindekeInfoPojo userPindekeInfo =
              userPindekeInfoService.findByUserId(pindekeMonthSalePojo.getUserId());
          if (userPindekeInfo != null) {
            pindekeMonthSaleService.update(pindekeMonthSale);

            UserPindekeInfoPojo userPindekeInfoPojo = new UserPindekeInfoPojo();
            userPindekeInfoPojo.setId(userPindekeInfo.getId());
            // userPindekeInfoPojo.setUserId(pindekeMonthSalePojo.getUserId());
            // userPindekeInfoPojo.setFreezingPriceMinus();
            if (pindekeMonthSalePojo.getRanking() == 1) {
              pindekeMonthSalePojo.setSettleAmt(1000.00);
            } else if (pindekeMonthSalePojo.getRanking() == 2) {
              pindekeMonthSalePojo.setSettleAmt(500.00);
            } else if (pindekeMonthSalePojo.getRanking() == 3) {
              pindekeMonthSalePojo.setSettleAmt(300.00);
            }
            userPindekeInfoPojo.setSurpluPriceAdd(pindekeMonthSalePojo.getSettleAmt());
            userPindekeInfoPojo.setRebatePriceAdd(pindekeMonthSalePojo.getSettleAmt());
            userPindekeInfoService.update(userPindekeInfoPojo);

            UserDealLogPojo userDealLogPojo = new UserDealLogPojo();
            userDealLogPojo.setUserId(pindekeMonthSalePojo.getUserId());
            Date date = new Date();
            userDealLogPojo.setDealDate(date);
            userDealLogPojo.setDealAmount(pindekeMonthSalePojo.getSettleAmt());
            userDealLogPojo.setDealType(1);
            userDealLogPojo.setStatus(0);
            // userDealLogPojo.setGroupId();
            userDealLogPojo.setRemark(1);
            userDealLogPojo.setSurplusPrice(userPindekeInfo.getSurpluPrice() == null ? 0.0
                : userPindekeInfo.getSurpluPrice() + pindekeMonthSalePojo.getSettleAmt());
            userDealLogPojo.setCreateBy(user.getId());
            userDealLogPojo.setUpdateBy(user.getId());
            userDealLogService.add(userDealLogPojo);

            result = "1";
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      // result = "2";
    }
    return SUCCESS;
  }

  /**
   * 
   * 批量返佣
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String settlePindekeMonthSaleAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    try {
      if (user != null && tids != null && tids.length >= 1) {
        for (String i : tids) {
          Long id = new Long(i);
          pindekeMonthSalePojo = pindekeMonthSaleService.getById(id);
          if (pindekeMonthSalePojo != null) {
            PindekeMonthSalePojo pindekeMonthSale = new PindekeMonthSalePojo();
            pindekeMonthSale.setId(id);
            pindekeMonthSale.setIsSettle(1);
            pindekeMonthSale.setUpdateBy(user.getId());

            UserPindekeInfoPojo userPindekeInfo =
                userPindekeInfoService.findByUserId(pindekeMonthSalePojo.getUserId());
            if (userPindekeInfo != null) {
              pindekeMonthSaleService.update(pindekeMonthSale);

              UserPindekeInfoPojo userPindekeInfoPojo = new UserPindekeInfoPojo();
              userPindekeInfoPojo.setId(userPindekeInfo.getId());
              // userPindekeInfoPojo.setUserId(pindekeMonthSalePojo.getUserId());
              // userPindekeInfoPojo.setFreezingPriceMinus();
              if (pindekeMonthSalePojo.getTotal() >= 9000) {
                pindekeMonthSalePojo.setSettleAmt(450.00);
              } else if (pindekeMonthSalePojo.getTotal() >= 5000) {
                pindekeMonthSalePojo.setSettleAmt(300.00);
              } else if (pindekeMonthSalePojo.getTotal() >= 3000) {
                pindekeMonthSalePojo.setSettleAmt(100.00);
              } else if (pindekeMonthSalePojo.getTotal() >= 1000) {
                pindekeMonthSalePojo.setSettleAmt(50.00);
              }
              userPindekeInfoPojo.setSurpluPriceAdd(pindekeMonthSalePojo.getSettleAmt());
              userPindekeInfoPojo.setRebatePriceAdd(pindekeMonthSalePojo.getSettleAmt());
              userPindekeInfoService.update(userPindekeInfoPojo);

              UserDealLogPojo userDealLogPojo = new UserDealLogPojo();
              userDealLogPojo.setUserId(pindekeMonthSalePojo.getUserId());
              Date date = new Date();
              userDealLogPojo.setDealDate(date);
              userDealLogPojo.setDealAmount(pindekeMonthSalePojo.getSettleAmt());
              userDealLogPojo.setDealType(1);
              userDealLogPojo.setStatus(0);
              // userDealLogPojo.setGroupId();
              userDealLogPojo.setRemark(1);
              userDealLogPojo.setSurplusPrice(userPindekeInfo.getSurpluPrice() == null ? 0.0
                  : userPindekeInfo.getSurpluPrice() + pindekeMonthSalePojo.getSettleAmt());
              userDealLogPojo.setCreateBy(user.getId());
              userDealLogPojo.setUpdateBy(user.getId());
              userDealLogService.add(userDealLogPojo);
            }
          }
        }

        result = "1";
      }
    } catch (Exception e) {
      e.printStackTrace();
      // result = "2";
    }
    return SUCCESS;
  }

  public void pindekeRanking() throws SQLException {
    try {
      DecimalFormat df = new DecimalFormat("#.##");
      // 获取当前日期年月日
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMM");
      String date2 = sdf.format(new Date());
      // 获取当前日期减去一天的年月份
      Calendar c = Calendar.getInstance();
      c.add(Calendar.DATE, -1);
      String date1 =
          String.valueOf(c.get(Calendar.YEAR)) + "-" + String.valueOf(c.get(Calendar.MONTH) + 1)
              + "-01";
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("paymentDateStart", date1);
      param.put("paymentDateEnd", date2);
      param.put("status", 1);
      param.put("isPindeke", 1);
      List<OrderPojo> orderPojos = orderService.getPindekeRanking(param);
      int i = 1;// 用来排名的
      if (orderPojos != null && orderPojos.size() > 0) {
        for (OrderPojo order : orderPojos) {
          // 把字符串转日期再把日期转为格式化字符串是为了保证月份为两位数
          String dateString =
              sdf1.format(sdf1.parse(String.valueOf(c.get(Calendar.YEAR))
                  + String.valueOf(c.get(Calendar.MONTH) + 1)));
          Map<String, Object> map = new HashMap<String, Object>();
          map.put("userId", order.getPdkUid());
          map.put("type", 1);
          map.put("sectionTime", dateString);
          List<PindekeMonthSalePojo> pindekeMonthSalePojos = pindekeMonthSaleService.listPage(map);
          PindekeMonthSalePojo pindekeMonthSalePojo = new PindekeMonthSalePojo();
          // 插入或修改拼得客销售排名表
          if (pindekeMonthSalePojos.size() > 0) {
            pindekeMonthSalePojo.setId(pindekeMonthSalePojos.get(0).getId());
            pindekeMonthSalePojo.setTotal(Double.parseDouble(df.format(order.getFactPriceAll())));
            pindekeMonthSalePojo.setRanking(i);
            pindekeMonthSaleService.update(pindekeMonthSalePojo);
          } else {
            pindekeMonthSalePojo.setUserId(order.getPdkUid());
            pindekeMonthSalePojo.setType(1);
            pindekeMonthSalePojo.setSectionTime(dateString);
            pindekeMonthSalePojo.setTotal(Double.parseDouble(df.format(order.getFactPriceAll())));
            pindekeMonthSalePojo.setIsSettle(0);
            pindekeMonthSalePojo.setRanking(i);
            pindekeMonthSalePojo.setInviterId(order.getInviterId());
            pindekeMonthSaleService.add(pindekeMonthSalePojo);
          }
          i++;
        }
      }
      FileUtil.alertMessageBySkip("更新成功！", "goPindekeMonthSale.do");
    } catch (ParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void pindekeMonthSale() throws SQLException {
    DecimalFormat df = new DecimalFormat("#.##");
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("status", 1);
    param.put("isPindeke", 1);
    List<OrderPojo> orderPojos = orderService.getPindekeMonthSale(param);
    if (orderPojos != null) {
      for (OrderPojo order : orderPojos) {
        PindekeMonthSalePojo pindekeMonthSalePojo = new PindekeMonthSalePojo();
        if (order.getFactPriceAll() != null) {
          Double priceAll = order.getFactPriceAll();
          pindekeMonthSalePojo.setTotal(Double.parseDouble(df.format(priceAll)));
        } else {
          pindekeMonthSalePojo.setTotal(0.0);
        }
        map.put("userId", order.getPdkUid());
        map.put("type", 2);
        List<PindekeMonthSalePojo> pindekeMonthSalePojos = pindekeMonthSaleService.listPage(map);
        // 插入或修改拼得客月销售表
        if (pindekeMonthSalePojos.size() > 0) {
          pindekeMonthSalePojo.setId(pindekeMonthSalePojos.get(0).getId());
          pindekeMonthSaleService.update(pindekeMonthSalePojo);
        } else {
          pindekeMonthSalePojo.setUserId(order.getPdkUid());
          pindekeMonthSalePojo.setType(2);
          // 若天数不为整数，则向上取整
          pindekeMonthSalePojo.setSectionTime("30");
          pindekeMonthSalePojo.setIsSettle(0);
          pindekeMonthSalePojo.setInviterId(order.getInviterId());
          pindekeMonthSaleService.add(pindekeMonthSalePojo);
        }
      }
    }
    FileUtil.alertMessageBySkip("更新成功！", "goPindekeMonthSaleInviter.do");
  }
}
