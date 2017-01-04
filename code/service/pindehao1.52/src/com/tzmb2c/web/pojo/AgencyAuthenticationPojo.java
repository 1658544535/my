package com.tzmb2c.web.pojo;


import java.util.Date;

import maowu.framework.utils.pojo.SuperPojo;

import com.tzmb2c.utils.StringUtil;


/**
 * 
 * 批发商信息-- user_agency by Lie
 * 
 */

public class AgencyAuthenticationPojo extends SuperPojo {
  private Long agency_id;
  private Long user_id;
  private String realName;// 实名
  private String id_num;// 身份证号
  private Long auto_userId;// 认证人编号（外键关联（sys_login.id））
  private String bus_licence;// 营业执照
  private String id_front_photo;// 身份证正面照
  private String id_back_photo;// 身份证反面照
  private String id_handle_photo;// 手持身份证照
  private String auth_licence;// 授权证书
  private Long auto_user_id;// 认证人编号
  private Integer auth_status;// 认证状态（取业务字典：0待认证1认证通过 2 认证未通过）
  private Long auth_manufacturer_id;// 授权厂家
  private Long create_by;
  private String create_dateStr;// 创建日期(字符串)
  private Date create_date;
  private Long update_by;
  private Date update_date;
  private String remarks;
  private Integer version;
  private String user_name;// 用户昵称
  private String statusName;// 显示状态字符



  public Long getAgency_id() {
    return agency_id;
  }

  public void setAgency_id(Long agency_id) {
    this.agency_id = agency_id;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public Long getCreate_by() {
    return create_by;
  }

  public void setCreate_by(Long create_by) {
    this.create_by = create_by;
  }

  public Date getCreate_date() {
    return create_date;
  }

  public void setCreate_date(Date create_date) {
    this.create_date = create_date;
    this.create_dateStr = StringUtil.dateToString(this.create_date);
  }

  public Long getUpdate_by() {
    return update_by;
  }

  public void setUpdate_by(Long update_by) {
    this.update_by = update_by;
  }

  public Date getUpdate_date() {
    return update_date;
  }

  public void setUpdate_date(Date update_date) {
    this.update_date = update_date;
  }

  @Override
  public String getRemarks() {
    return remarks;
  }

  @Override
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Override
  public Integer getVersion() {
    return version;
  }

  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getId_num() {
    return id_num;
  }

  public void setId_num(String id_num) {
    this.id_num = id_num;
  }

  public String getBus_licence() {
    return bus_licence;
  }

  public void setBus_licence(String bus_licence) {
    this.bus_licence = bus_licence;
  }

  public Long getAuto_userId() {
    return auto_userId;
  }

  public void setAuto_userId(Long auto_userId) {
    this.auto_userId = auto_userId;
  }

  public String getId_front_photo() {
    return id_front_photo;
  }

  public void setId_front_photo(String id_front_photo) {
    this.id_front_photo = id_front_photo;
  }

  public String getId_back_photo() {
    return id_back_photo;
  }

  public void setId_back_photo(String id_back_photo) {
    this.id_back_photo = id_back_photo;
  }

  public String getId_handle_photo() {
    return id_handle_photo;
  }

  public void setId_handle_photo(String id_handle_photo) {
    this.id_handle_photo = id_handle_photo;
  }

  public Integer getAuth_status() {
    return auth_status;
  }

  public void setAuth_status(Integer auth_status) {
    this.auth_status = auth_status;
  }

  public Long getAuto_user_id() {
    return auto_user_id;
  }

  public void setAuto_user_id(Long auto_user_id) {
    this.auto_user_id = auto_user_id;
  }

  /**
   * @return the user_name
   */
  public String getUser_name() {
    return user_name;
  }

  /**
   * @param user_name the user_name to set
   */
  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  /**
   * @return the statusName
   */
  public String getStatusName() {
    return statusName;
  }

  /**
   * @param statusName the statusName to set
   */
  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }


  public String getCreate_dateStr() {
    return create_dateStr;
  }

  public void setCreate_dateStr(String create_dateStr) {
    this.create_dateStr = create_dateStr;
  }

  /**
   * @return the auth_licence
   */
  public String getAuth_licence() {
    return auth_licence;
  }

  /**
   * @param auth_licence the auth_licence to set
   */
  public void setAuth_licence(String auth_licence) {
    this.auth_licence = auth_licence;
  }

  /**
   * @return the auth_manufacturer_id
   */
  public Long getAuth_manufacturer_id() {
    return auth_manufacturer_id;
  }

  /**
   * @param auth_manufacturer_id the auth_manufacturer_id to set
   */
  public void setAuth_manufacturer_id(Long auth_manufacturer_id) {
    this.auth_manufacturer_id = auth_manufacturer_id;
  }
}
