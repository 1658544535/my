/*
 * 文 件 名: UserDealLogAction.java 创 建 人: admin 创建时间: 2016-10-15
 */
package com.tzmb2c.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.GrouponActivityRecordPojo;
import com.tzmb2c.web.pojo.GrouponUserRecordPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserDealLogPojo;
import com.tzmb2c.web.pojo.UserPindekeInfoPojo;
import com.tzmb2c.web.service.GrouponActivityRecordService;
import com.tzmb2c.web.service.GrouponUserRecordService;
import com.tzmb2c.web.service.UserDealLogService;
import com.tzmb2c.web.service.UserPindekeInfoService;

public class UserDealLogAction extends SuperAction {
  @Autowired
  private UserDealLogService userDealLogService;
  @Autowired
  private GrouponUserRecordService grouponUserRecordService;
  @Autowired
  private GrouponActivityRecordService grouponActivityRecordService;
  @Autowired
  private UserPindekeInfoService userPindekeInfoService;
  private UserDealLogPojo userDealLogPojo;
  private Long id;
  private String[] tids;
  private String result;
  private Integer status;
  private String returnMsg;
  private String orderNo;
  private GrouponUserRecordPojo grouponUserRecordPojo;
  private UserPindekeInfoPojo userPindekeInfoPojo;

  public UserPindekeInfoPojo getUserPindekeInfoPojo() {
    return userPindekeInfoPojo;
  }

  public void setUserPindekeInfoPojo(UserPindekeInfoPojo userPindekeInfoPojo) {
    this.userPindekeInfoPojo = userPindekeInfoPojo;
  }

  public GrouponUserRecordPojo getGrouponUserRecordPojo() {
    return grouponUserRecordPojo;
  }

  public void setGrouponUserRecordPojo(GrouponUserRecordPojo grouponUserRecordPojo) {
    this.grouponUserRecordPojo = grouponUserRecordPojo;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public String getReturnMsg() {
    return returnMsg;
  }

  public void setReturnMsg(String returnMsg) {
    this.returnMsg = returnMsg;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String[] getTids() {
    return this.tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getResult() {
    return this.result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public UserDealLogPojo getUserDealLogPojo() {
    return userDealLogPojo;
  }

  public void setUserDealLogPojo(UserDealLogPojo userDealLogPojo) {
    this.userDealLogPojo = userDealLogPojo;
  }

  /**
   * 查询全部条数
   */
  public String goUserDealLog() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    try {
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("dealType", 2);
      if (userDealLogPojo != null) {
        param.put("status", userDealLogPojo.getStatus());
        param.put("nameAndTypeNo", userDealLogPojo.getName());
        param.put("dealDateStr", userDealLogPojo.getDealDateStr());
        param.put("dealDateEndStr", userDealLogPojo.getDealDateEndStr());
      }
      int i = userDealLogService.countBy(param);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String userDealLogRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("dealType", 2);
    try {
      if (userDealLogPojo != null) {
        param.put("status", userDealLogPojo.getStatus());
        param.put("nameAndTypeNo", userDealLogPojo.getName());
        param.put("dealDateStr", userDealLogPojo.getDealDateStr());
        param.put("dealDateEndStr", userDealLogPojo.getDealDateEndStr());
      }
      int i = userDealLogService.countBy(param);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String userDealLogList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("dealType", 2);
    param.put("pageSize", page.getPageSize());
    param.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    param.put("orderBy", "udl.id desc");
    List<UserDealLogPojo> userDealLogList = null;
    try {
      if (userDealLogPojo != null) {
        param.put("status", userDealLogPojo.getStatus());
        param.put("nameAndTypeNo", userDealLogPojo.getName());
        param.put("dealDateStr", userDealLogPojo.getDealDateStr());
        param.put("dealDateEndStr", userDealLogPojo.getDealDateEndStr());
      }
      userDealLogList = userDealLogService.listPage(param);
      JSONArray json = JSONArray.fromObject(userDealLogList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String goPindekeUserDealLog() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    try {
      Map<String, Object> param = new HashMap<String, Object>();
      if (userDealLogPojo != null && userDealLogPojo.getUserId() != null
          && userDealLogPojo.getUserId() > 0) {
        param.put("userId", userDealLogPojo.getUserId());
        param.put("dealType", userDealLogPojo.getDealType());
        param.put("orderNo", userDealLogPojo.getOrderNo());
        param.put("dealDateStr", userDealLogPojo.getDealDateStr());
        param.put("dealDateEndStr", userDealLogPojo.getDealDateEndStr());
        int i = userDealLogService.countBy(param);
        page.setRowCount(i);

        userPindekeInfoPojo = userPindekeInfoService.findByUserId(userDealLogPojo.getUserId());
        if (userPindekeInfoPojo == null) {
          userPindekeInfoPojo = new UserPindekeInfoPojo();
          userPindekeInfoPojo.setRebatePrice(0.00);
          userPindekeInfoPojo.setWithdrawPrice(0.00);
          userPindekeInfoPojo.setSurpluPrice(0.00);
        }

      } else {
        page.setRowCount(0);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String pindekeUserDealLogRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> param = new HashMap<String, Object>();
    try {
      if (userDealLogPojo != null && userDealLogPojo.getUserId() != null
          && userDealLogPojo.getUserId() > 0) {
        param.put("userId", userDealLogPojo.getUserId());
        param.put("dealType", userDealLogPojo.getDealType());
        param.put("orderNo", userDealLogPojo.getOrderNo());
        param.put("dealDateStr", userDealLogPojo.getDealDateStr());
        param.put("dealDateEndStr", userDealLogPojo.getDealDateEndStr());
        int i = userDealLogService.countBy(param);
        page.setRowCount(i);

        userPindekeInfoPojo = userPindekeInfoService.findByUserId(userDealLogPojo.getUserId());
        if (userPindekeInfoPojo == null) {
          userPindekeInfoPojo = new UserPindekeInfoPojo();
          userPindekeInfoPojo.setRebatePrice(0.00);
          userPindekeInfoPojo.setWithdrawPrice(0.00);
          userPindekeInfoPojo.setSurpluPrice(0.00);
        }

      } else {
        page.setRowCount(0);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String pindekeUserDealLogList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("pageSize", page.getPageSize());
    param.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    List<UserDealLogPojo> userDealLogList = null;
    try {
      if (userDealLogPojo != null && userDealLogPojo.getUserId() != null
          && userDealLogPojo.getUserId() > 0) {
        param.put("userId", userDealLogPojo.getUserId());
        param.put("dealType", userDealLogPojo.getDealType());
        param.put("orderNo", userDealLogPojo.getOrderNo());
        param.put("dealDateStr", userDealLogPojo.getDealDateStr());
        param.put("dealDateEndStr", userDealLogPojo.getDealDateEndStr());
        param.put("orderBy", "udl.id desc");
        userDealLogList = userDealLogService.listPage(param);
        JSONArray json = JSONArray.fromObject(userDealLogList);
        page.setResult(json.toString());
      } else {
        page.setResult("[]");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String goUserEndGroupSettle() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("invitationUserIdStr", " gar.invitation_user_id != 0");
    param.put("recordStatusStr", " gar.status != 0");
    // param.put("status", 1);
    param.put("activityType", 2);
    param.put("groupBy", " gur.attend_id");
    param.put("pdkStatus", 1);
    param.put("isRebate", 0);
    try {
      if (grouponUserRecordPojo != null) {
        param.put("recordStatus", grouponUserRecordPojo.getRecordStatus());
        param.put("loginName", grouponUserRecordPojo.getLoginName());
        param.put("beginTimeStr", grouponUserRecordPojo.getBeginTimeStr());
        param.put("endTimeStr", grouponUserRecordPojo.getEndTimeStr());
      }
      int i = grouponUserRecordService.countBy2(param);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String userEndGroupSettleRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("invitationUserIdStr", " gar.invitation_user_id != 0");
    // param.put("status", 1);
    param.put("activityType", 2);
    param.put("recordStatusStr", " gar.status != 0");
    param.put("groupBy", " gur.attend_id");
    param.put("pdkStatus", 1);
    param.put("isRebate", 0);
    try {
      if (grouponUserRecordPojo != null) {
        param.put("recordStatus", grouponUserRecordPojo.getRecordStatus());
        param.put("loginName", grouponUserRecordPojo.getLoginName());
        param.put("beginTimeStr", grouponUserRecordPojo.getBeginTimeStr());
        param.put("endTimeStr", grouponUserRecordPojo.getEndTimeStr());
      }
      int i = grouponUserRecordService.countBy2(param);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String userEndGroupSettleList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("invitationUserIdStr", " gar.invitation_user_id != 0");
    // param.put("status", 1);
    param.put("activityType", 2);
    param.put("recordStatusStr", " gar.status != 0");
    param.put("groupBy", " gur.attend_id");
    param.put("pdkStatus", 1);
    param.put("isRebate", 0);
    param.put("pageSize", page.getPageSize());
    param.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    try {
      if (grouponUserRecordPojo != null) {
        param.put("recordStatus", grouponUserRecordPojo.getRecordStatus());
        param.put("loginName", grouponUserRecordPojo.getLoginName());
        param.put("beginTimeStr", grouponUserRecordPojo.getBeginTimeStr());
        param.put("endTimeStr", grouponUserRecordPojo.getEndTimeStr());
      }
      List<GrouponUserRecordPojo> grouponUserRecordPojos =
          grouponUserRecordService.listPage2(param);
      JSONArray json = JSONArray.fromObject(grouponUserRecordPojos);
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
  public String goAddUserDealLog() throws Exception {
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
    if (user != null) {
      userDealLogPojo = new UserDealLogPojo();
      userDealLogPojo.setCreateBy(user.getId());
      userDealLogPojo.setUpdateBy(user.getId());
      try {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("invitationUserIdStr", " gar.invitation_user_id != 0");
        // param.put("status", 1);
        param.put("activityType", 2);
        param.put("recordStatusStr", " gar.status != 0");
        param.put("groupBy", " gur.attend_id");
        param.put("pdkStatus", 1);
        param.put("isRebate", 0);
        List<GrouponUserRecordPojo> grouponUserRecordPojos =
            grouponUserRecordService.listPage2(param);
        if (grouponUserRecordPojos.size() > 0) {
          for (GrouponUserRecordPojo gur : grouponUserRecordPojos) {
            userDealLogPojo.setUserId(gur.getUserId());
            userDealLogPojo.setDealDate(new Date());
            userDealLogPojo.setDealType(1);
            // userDealLogPojo.setStatus(0);
            userDealLogPojo.setGroupId(gur.getId());
            userDealLogPojo.setRemark(1);
            if (gur.getRecordStatus() == 1) {
              userDealLogPojo.setDealAmount(1.5);
              userDealLogService.add(userDealLogPojo);
            } else if (gur.getRecordStatus() == 2 && gur.getCounts() >= 2) {
              userDealLogPojo.setDealAmount(0.3);
              userDealLogService.add(userDealLogPojo);
            }

            GrouponActivityRecordPojo grouponActivityRecord = new GrouponActivityRecordPojo();
            grouponActivityRecord.setId(gur.getId());
            grouponActivityRecord.setIsRebate(1);
            grouponActivityRecord.setRebateTime(new Date());
            grouponActivityRecordService.update(grouponActivityRecord);

            UserPindekeInfoPojo userPindekeInfo = new UserPindekeInfoPojo();
            userPindekeInfo.setId(gur.getPdkId());
            if (gur.getRecordStatus() == 1) {
              userPindekeInfo.setRebatePriceStr(" rebate_price + 1.5");
              userPindekeInfo.setSurpluPriceStr(" surplu_price + 1.5");
              userPindekeInfoService.update(userPindekeInfo);
            } else if (gur.getRecordStatus() == 2 && gur.getCounts() >= 2) {
              userPindekeInfo.setRebatePriceStr(" rebate_price + 0.3");
              userPindekeInfo.setSurpluPriceStr(" surplu_price + 0.3");
              userPindekeInfoService.update(userPindekeInfo);
            }
          }
        }
        this.result = "1";
      } catch (Exception e) {
        e.printStackTrace();
        this.result = "0";
      }
    } else {
      this.result = "0";
    }
    return SUCCESS;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditUserDealLog() throws Exception {
    if (id != null && id > 0) {
      userDealLogPojo = userDealLogService.getById(id);
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
    if (user != null && userDealLogPojo != null) {
      userDealLogPojo.setUpdateBy(user.getId());
      userDealLogPojo.setUpdateDate(new Date());
      try {
        userDealLogService.update(userDealLogPojo);
        FileUtil.alertMessageBySkip("提交成功！", "goUserDealLog.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！", "goEditUserDealLog.do?id=" + userDealLogPojo.getId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goUserDealLog.do");
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
    this.result = "0";
    if (user != null && id != null && id > 0 && status != null) {
      userDealLogPojo = new UserDealLogPojo();
      userDealLogPojo.setId(id);
      userDealLogPojo.setStatus(status);
      userDealLogPojo.setUpdateBy(user.getId());
      try {
        if (status == 2) {
          userDealLogPojo.setReturnMsg(returnMsg);
          userDealLogPojo.setFalseDate(new Date());
          userDealLogService.update(userDealLogPojo);
        } else if (status == 3) {
          userDealLogPojo.setOrderNo(orderNo);
          userDealLogPojo.setOverTime(new Date());
          UserDealLogPojo userDealLog = userDealLogService.getById(id);
          if (userDealLog != null) {
            UserPindekeInfoPojo userPindekeInfo = new UserPindekeInfoPojo();
            userPindekeInfo.setId(userDealLog.getPdkId());
            userPindekeInfo.setWithdrawPriceStr(" withdraw_price + " + userDealLog.getDealAmount());
            userPindekeInfo.setSurpluPriceStr(" surplu_price - " + userDealLog.getDealAmount());
            userPindekeInfoService.update(userPindekeInfo);
          }
        }
        userDealLogService.update(userDealLogPojo);
        this.result = "1";
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
    this.result = "0";
    if (user != null && id != null && id > 0) {
      userDealLogPojo = new UserDealLogPojo();
      userDealLogPojo.setId(id);
      userDealLogPojo.setStatus(0);
      userDealLogPojo.setUpdateBy(user.getId());
      userDealLogPojo.setUpdateDate(new Date());
      try {
        userDealLogService.update(userDealLogPojo);
        this.result = "1";
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
    this.result = "0";
    if (user != null && id != null && id > 0) {
      try {
        userDealLogService.delete(id);
        this.result = "1";
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
    this.result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      userDealLogPojo = new UserDealLogPojo();
      userDealLogPojo.setStatus(1);
      for (String tid : tids) {
        userDealLogPojo.setId(Long.valueOf(tid));
        userDealLogPojo.setUpdateBy(user.getId());
        userDealLogPojo.setUpdateDate(new Date());
        try {
          userDealLogService.update(userDealLogPojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        this.result = "2";
      } else {
        this.result = "1";
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
    this.result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      userDealLogPojo = new UserDealLogPojo();
      userDealLogPojo.setStatus(0);
      for (String tid : tids) {
        userDealLogPojo.setId(Long.valueOf(tid));
        userDealLogPojo.setUpdateBy(user.getId());
        userDealLogPojo.setUpdateDate(new Date());
        try {
          userDealLogService.update(userDealLogPojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        this.result = "2";
      } else {
        this.result = "1";
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
    this.result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      for (String tid : tids) {
        try {
          userDealLogService.delete(Long.valueOf(tid));
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        this.result = "2";
      } else {
        this.result = "1";
      }
    }
    return SUCCESS;
  }
}
