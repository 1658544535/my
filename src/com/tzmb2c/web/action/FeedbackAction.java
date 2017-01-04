package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.Date;

import maowu.framework.utils.web.SuperAction;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.FeedBackPojo;
import com.tzmb2c.web.pojo.SysAreaPojo;
import com.tzmb2c.web.service.FeedbackService;
import com.tzmb2c.web.service.HelpService;
import com.tzmb2c.web.service.HelpTypeService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysDictService;

/**
 * 2014-12-06
 * 
 * @author creazylee 帮助/FAQ系统
 */
public class FeedbackAction extends SuperAction {


  @Autowired
  private HelpService helpService;
  @Autowired
  private HelpTypeService helpTypeService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private FeedbackService feedbackService;
  @Autowired
  private SysAreaService sysAreaService;


  private String type;
  private String[] tids;
  private String typeStr;
  private FeedBackPojo feedBackPojo;


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

  public String getTypeStr() {
    return typeStr;
  }

  public void setTypeStr(String typeStr) {
    this.typeStr = typeStr;
  }

  public FeedBackPojo getFeedBackPojo() {
    return feedBackPojo;
  }

  public void setFeedBackPojo(FeedBackPojo feedBackPojo) {
    this.feedBackPojo = feedBackPojo;
  }



  /***
   * 添加单条信息
   * 
   * @return
   */
  public String feedBackAdd() throws SQLException {
    String area = null;
    // long createBy = 8L;
    Date createDate = new Date();
    // helpPojo.setCreateBy(createBy);
    // helpPojo.setStatus(0);
    feedBackPojo.setCreateDate(createDate);
    if (feedBackPojo.getProvince() != null) {
      SysAreaPojo sysAreaPojo = sysAreaService.getNameById(feedBackPojo.getProvince());
      area = sysAreaPojo.getName();
    }
    if (feedBackPojo.getCity() != null) {
      SysAreaPojo sysAreaPojo = sysAreaService.getNameById(feedBackPojo.getCity());
      area = area + sysAreaPojo.getName();
    }
    if (feedBackPojo.getArea2() != null) {
      SysAreaPojo sysAreaPojo = sysAreaService.getNameById(feedBackPojo.getArea2());
      area = area + sysAreaPojo.getName();
    }
    feedBackPojo.setArea(area);
    feedbackService.feedBackAdd(feedBackPojo);
    // String code = SMSCaptchaUtil.SendCaptcha("13068904483");

    FileUtil.alertMessageBySkip("提交成功！我们的工作人员会在3个工作日内为你处理请求。", "goFeedBackWeb.do");
    return null;
  }

}
