package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

public class LotteryLogPojo extends SuperPojo {

  private Long lotteryLogId;// 抽奖结果id
  private Long userId;// 会员id
  private Long time;// 抽奖时间
  private String prize;// 奖品
  private Integer prizeType;// 奖品类型，-1不中，0微信红包，1优惠券，2转发攒运气
  private String prizeTypeName;// 奖品类型名称
  private String prizeVal;// 奖品值，根据prize_type值而定，0红包金额，1券码

  private String timeDStr;// 抽奖时间

  private String userName;// 会员名称

  public Long getLotteryLogId() {
    return lotteryLogId;
  }

  public void setLotteryLogId(Long lotteryLogId) {
    this.lotteryLogId = lotteryLogId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public String getPrize() {
    return prize;
  }

  public void setPrize(String prize) {
    this.prize = prize;
  }

  public Integer getPrizeType() {
    return prizeType;
  }

  public void setPrizeType(Integer prizeType) {
    this.prizeType = prizeType;
  }

  public String getPrizeTypeName() {
    return prizeTypeName;
  }

  public void setPrizeTypeName(String prizeTypeName) {
    this.prizeTypeName = prizeTypeName;
  }

  public String getPrizeVal() {
    return prizeVal;
  }

  public void setPrizeVal(String prizeVal) {
    this.prizeVal = prizeVal;
  }

  public String getTimeDStr() {
    return timeDStr;
  }

  public void setTimeDStr(String timeDStr) {
    this.timeDStr = timeDStr;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
