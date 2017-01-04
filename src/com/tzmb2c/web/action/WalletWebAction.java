package com.tzmb2c.web.action;

import java.util.List;

import maowu.framework.utils.web.SuperAction;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserWalletLogPojo;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserWalletLogService;
import com.tzmb2c.web.service.UserWalletService;


public class WalletWebAction extends SuperAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private UserWalletService userWalletService;
  @Autowired
  private UserWalletLogService userWalletLogService;
  @Autowired
  private WalletService walletService;
  @Autowired
  private SysLoginService sysLoginService;
  private SysLoginPojo sysLoginPojo;

  private Long uid;
  private Double ratio;// 分享当天的系数
  private Double amount;// 分享获取的金额

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public Double getRatio() {
    return ratio;
  }

  public void setRatio(Double ratio) {
    this.ratio = ratio;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public SysLoginPojo getSysLoginPojo() {
    return sysLoginPojo;
  }

  public void setSysLoginPojo(SysLoginPojo sysLoginPojo) {
    this.sysLoginPojo = sysLoginPojo;
  }

  /**
   * 钱包邀请好友赚钱
   **/
  public String goInviteFriend() throws Exception {
    /*
     * SysLoginPojo sysLogin = UserUtil.getWebUser(); if (sysLogin == null) {
     * FileUtil.alertMessageBySkip("请先登录", "doLoginWeb.do"); return null; } Long uid =
     * sysLogin.getId();
     */
    ActionContext ac = ActionContext.getContext();
    ac.put("userWalletPojo", userWalletService.findUserWalletByUserId(uid));
    List<UserWalletLogPojo> walletLogs = userWalletLogService.getUserWalletLogByUserId(uid);
    if (walletLogs != null && walletLogs.size() > 0) {
      for (UserWalletLogPojo userWalletLogPojo : walletLogs) {
        userWalletLogPojo.setSourceName(WalletService.enCodeString(userWalletLogPojo
            .getSourceName()));
      }
    }
    ac.put("userWalletLogPojos", walletLogs);
    ratio = walletService.getShareRatio();
    amount = walletService.getShareAmount(3);
    return SUCCESS;
  }

  /**
   * 钱包赚钱细则（跳转）
   **/
  public String goStep() throws Exception {
    return SUCCESS;
  }

  /**
   * 钱包邀请成功页面
   **/
  public String goShare() throws Exception {
    ActionContext ac = ActionContext.getContext();
    SysLoginPojo sysLogin = sysLoginService.getUserInfoById(uid);
    if (sysLogin != null) {
      sysLogin.setName(WalletService.enCodeString(sysLogin.getName()));
    }
    ac.put("sysLoginPojo", sysLogin);
    return SUCCESS;
  }
}
