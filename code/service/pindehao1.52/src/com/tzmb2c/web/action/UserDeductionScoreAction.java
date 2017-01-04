package com.tzmb2c.web.action;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserDeductionScorePojo;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.UserDeductionScoreService;

public class UserDeductionScoreAction extends SuperAction {

  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private UserDeductionScoreService userDeductionScoreService;
  @Autowired
  private SellerService sellerService;

  private ManufacturerPojo manufacturerPojo;
  private List<ManufacturerPojo> manufacturerPojos;
  private List<UserDeductionScorePojo> userDeductionScorePojos;
  private UserDeductionScorePojo userDeductionScorePojo;
  private String result;
  private Double scoreAll;// 总的分数

  public ManufacturerPojo getManufacturerPojo() {
    return manufacturerPojo;
  }

  public void setManufacturerPojo(ManufacturerPojo manufacturerPojo) {
    this.manufacturerPojo = manufacturerPojo;
  }

  public List<ManufacturerPojo> getManufacturerPojos() {
    return manufacturerPojos;
  }

  public List<UserDeductionScorePojo> getUserDeductionScorePojos() {
    return userDeductionScorePojos;
  }

  public void setUserDeductionScorePojos(List<UserDeductionScorePojo> userDeductionScorePojos) {
    this.userDeductionScorePojos = userDeductionScorePojos;
  }

  public UserDeductionScorePojo getUserDeductionScorePojo() {
    return userDeductionScorePojo;
  }

  public void setUserDeductionScorePojo(UserDeductionScorePojo userDeductionScorePojo) {
    this.userDeductionScorePojo = userDeductionScorePojo;
  }

  public void setManufacturerPojos(List<ManufacturerPojo> manufacturerPojos) {
    this.manufacturerPojos = manufacturerPojos;
  }

  /**
   * 商家信息页面
   * 
   * @return
   */
  public String userDeductionScoreManage() {
    if (page == null) {
      page = new Pager();
    }
    /*
     * if (manufacturerPojo != null && manufacturerPojo.getCreateDateStr() != "") {
     * manufacturerPojo.setCreateDateStr(manufacturerPojo.getCreateDateStr()+" 00:00:00"); }
     */
    page.setRowCount(manufacturerService.manufacturerAllCount(manufacturerPojo));
    return SUCCESS;
  }

  /**
   * 商家信息列表
   * 
   * @return
   * @throws SQLException
   */
  public String userDeductionScoreManageList() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    /*
     * if (manufacturerPojo != null && manufacturerPojo.getCreateDateStr() != "") {
     * manufacturerPojo.setCreateDateStr(manufacturerPojo.getCreateDateStr()+" 00:00:00"); }
     */
    manufacturerPojos = manufacturerService.manufacturerAllList(manufacturerPojo, page);
    for (ManufacturerPojo m : manufacturerPojos) {
      BigDecimal bd = new BigDecimal(sellerService.returnDeductScoreAllByUid(m.getUserId()));
      m.setScoreAll(48 - bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
    }
    JSONArray json = JSONArray.fromObject(manufacturerPojos);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 商家扣分详情条数
   * 
   * @return
   * @throws SQLException
   */
  public String userDeductionScoreManageDetails() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    int count = 0;
    if (userDeductionScorePojo != null) {
      map.put("status", userDeductionScorePojo.getStatus());
      map.put("suserId", userDeductionScorePojo.getSuserId());
      map.put("createDateStr", userDeductionScorePojo.getCreateDateStr());
      map.put("updateDateStr", userDeductionScorePojo.getUpdateDateStr());
      count = userDeductionScoreService.findUserDeductionScoreCount(map);
    }
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(count);
    return SUCCESS;
  }

  /**
   * 商家扣分详情列表
   * 
   * @return
   * @throws SQLException
   */
  public String userDeductionScoreList() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (userDeductionScorePojo != null) {
      map.put("status", userDeductionScorePojo.getStatus());
      map.put("suserId", userDeductionScorePojo.getSuserId());
      map.put("createDateStr", userDeductionScorePojo.getCreateDateStr());
      map.put("updateDateStr", userDeductionScorePojo.getUpdateDateStr());
      userDeductionScorePojos = userDeductionScoreService.findUserDeductionScoreList(map);
    }
    JSONArray json = JSONArray.fromObject(userDeductionScorePojos);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 删除商家扣分记录
   * 
   * @return
   */
  public String delUserDeductionScore() {
    try {
      if (userDeductionScorePojo != null) {
        userDeductionScoreService.delUserDeductionScoreById(userDeductionScorePojo.getId());
        result = "1";
      } else {
        result = "0";
      }
    } catch (Exception e) {
      result = "0";
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 审核商家扣分记录
   * 
   * @return
   */
  public String checkUserDeductionScore() {
    try {
      if (userDeductionScorePojo != null) {
        userDeductionScoreService.checkUserDeductionScore(userDeductionScorePojo.getId());
        result = "1";
      } else {
        result = "0";
      }
    } catch (Exception e) {
      result = "0";
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 取消审核商家扣分记录
   * 
   * @return
   */
  public String uncheckUserDeductionScore() {
    try {
      if (userDeductionScorePojo != null) {
        userDeductionScoreService.uncheckUserDeductionScore(userDeductionScorePojo.getId());
        result = "1";
      } else {
        result = "0";
      }
    } catch (Exception e) {
      result = "0";
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 添加商家扣分记录
   * 
   * @return
   * @throws SQLException
   */
  public String addUserDeductionScore() throws SQLException {
    if (userDeductionScorePojo != null) {
      BigDecimal bd =
          new BigDecimal(sellerService.returnDeductScoreAllByUid(userDeductionScorePojo
              .getSuserId()));
      setScoreAll(48 - bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());
    }
    return SUCCESS;
  }

  /**
   * 修改商家扣分记录
   * 
   * @return
   * @throws SQLException
   */
  public String updateUserDeductionScore() throws SQLException {
    if (userDeductionScorePojo != null) {
      BigDecimal bd =
          new BigDecimal(sellerService.returnDeductScoreAllByUid(userDeductionScorePojo
              .getSuserId()));
      setScoreAll(48 - bd.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue());

      Map<String, Object> map = new HashMap<String, Object>();
      map.put("id", userDeductionScorePojo.getId());
      userDeductionScorePojos = userDeductionScoreService.findUserDeductionScoreList(map);
      if (userDeductionScorePojos.size() != 0) {
        userDeductionScorePojo = userDeductionScorePojos.get(0);
      }
    }
    return SUCCESS;
  }

  /**
   * 添加商家扣分记录提交
   * 
   * @return
   */
  public String addUserDeductionScoreOk() {
    try {
      if (userDeductionScorePojo != null) {
        userDeductionScorePojo.setStatus(1);
        SysLoginPojo sysLoginPojo = UserUtil.getUser();
        if (sysLoginPojo != null) {
          userDeductionScorePojo.setCreateBy(sysLoginPojo.getId());
        }
        userDeductionScoreService.insertUserDeductionScore(userDeductionScorePojo);
        FileUtil.alertMessageBySkip("提交成功！",
            "userDeductionScoreManageDetails.do?userDeductionScorePojo.suserId="
                + userDeductionScorePojo.getSuserId());
      } else {
        FileUtil.alertMessageBySkip(
            "提交失败！",
            "addUserDeductionScore.do?userDeductionScorePojo.suserId="
                + userDeductionScorePojo.getSuserId());
      }
    } catch (Exception e) {

      e.printStackTrace();
      FileUtil.alertMessageBySkip(
          "提交失败！",
          "addUserDeductionScore.do?userDeductionScorePojo.suserId="
              + userDeductionScorePojo.getSuserId());
    }
    return null;
  }

  /**
   * 修改商家扣分记录提交
   * 
   * @return
   */
  public String updateUserDeductionScoreOk() {
    try {
      if (userDeductionScorePojo != null) {
        SysLoginPojo sysLoginPojo = UserUtil.getUser();
        if (sysLoginPojo != null) {
          userDeductionScorePojo.setUpdateBy(sysLoginPojo.getId());
        }
        userDeductionScoreService.updateUserDeductionScore(userDeductionScorePojo);
        FileUtil.alertMessageBySkip("修改成功！",
            "userDeductionScoreManageDetails.do?userDeductionScorePojo.suserId="
                + userDeductionScorePojo.getSuserId());
      } else {
        FileUtil.alertMessageBySkip(
            "修改失败！",
            "updateUserDeductionScore.do?userDeductionScorePojo.id="
                + userDeductionScorePojo.getId());
      }
    } catch (Exception e) {

      e.printStackTrace();
      FileUtil.alertMessageBySkip("修改失败！", "updateUserDeductionScore.do?userDeductionScorePojo.id="
          + userDeductionScorePojo.getId());
    }
    return null;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public Double getScoreAll() {
    return scoreAll;
  }

  public void setScoreAll(Double scoreAll) {
    this.scoreAll = scoreAll;
  }
}
