/*
 * Copyright(c) 2016 cncounter.com All rights reserved. distributed with this file and available
 * online at http://b2c.taozhuma.com/
 */
package com.tzmb2c.web.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringDate;
import com.tzmb2c.utils.StringUtil;

/**
 * 订单消息表; InnoDB free: 58368 kB
 * 
 * @version 1.0
 * @author
 */
public class UserOrderNoticePojo extends SuperPojo {

  /**
   * ID
   */
  private Long id;
  /**
   * 用户id
   */
  private Long userId;
  /**
   * 模板类型1-支付成功2-开团成功3-拼团成功4-抽奖未中奖5-抽奖已中奖6-发货7-退款
   */
  private Integer type;
  /**
   * 模板标题
   */
  private String title;
  /**
   * 信息内容
   */
  private String content;
  /**
   * 删除标识（0-否1-是）
   */
  private Integer isDelete;
  private Date createDate;
  private String createDateStr;

  public String getCreateDateStr() {
    return createDateStr;
  }

  public void setCreateDateStr(String createDateStr) {
    this.createDateStr = createDateStr;
  }

  @Override
  public Date getCreateDate() {
    return createDate;
  }

  @Override
  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
    if (StringDate.isToday(createDate)) {
      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
      createDateStr = sdf.format(this.createDate);
    } else if (StringDate.isThisWeek(createDate)) {
      createDateStr = StringDate.getWeekOfDate(this.createDate);
    } else {
      createDateStr = StringUtil.dateToString(this.createDate);
    }
  }

  public void setId(Long value) {
    id = value;
  }

  public Long getId() {
    return id;
  }

  public void setUserId(Long value) {
    userId = value;
  }

  public Long getUserId() {
    return userId;
  }

  public void setType(Integer value) {
    type = value;
  }

  public Integer getType() {
    return type;
  }

  public void setTitle(String value) {
    title = value;
  }

  public String getTitle() {
    return title;
  }

  public void setContent(String value) {
    content = value;
  }

  public String getContent() {
    return content;
  }

  public void setIsDelete(Integer value) {
    isDelete = value;
  }

  public Integer getIsDelete() {
    return isDelete;
  }
}
