package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * brand_dic表
 */
public class HotPlayerPojo extends SuperPojo {

  /**
   * id:玩家ID
   */
  private Long id;
  /**
   * userName:玩家名称
   */
  private String userName;
  /**
   * userImage:玩家logo
   */
  private String userImage;
  /**
   * type:玩家类型 1-达人 2-创客
   */
  private Integer type;
  /**
   * followNum:关注数
   */
  private Integer followNum;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserImage() {
    return userImage;
  }

  public void setUserImage(String userImage) {
    this.userImage = userImage;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getFollowNum() {
    return followNum;
  }

  public void setFollowNum(Integer followNum) {
    this.followNum = followNum;
  }

}
