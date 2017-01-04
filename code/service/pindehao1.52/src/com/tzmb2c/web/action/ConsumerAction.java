package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.Date;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ConsumerPojo;
import com.tzmb2c.web.pojo.HelpPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.ConsumerService;
import com.tzmb2c.web.service.HelpService;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;

public class ConsumerAction extends SuperAction {

  @Autowired
  private ConsumerService consumerService;
  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private HelpService helpService;
  private HelpPojo helpPojo;

  private ConsumerPojo consumer, goUpdateConsumer;
  private String result;
  private String[] tids;
  private String os;

  public ConsumerPojo getGoUpdateConsumer() {
    return goUpdateConsumer;
  }

  public void setGoUpdateConsumer(ConsumerPojo goUpdateConsumer) {
    this.goUpdateConsumer = goUpdateConsumer;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }

  public ConsumerPojo getConsumer() {
    return consumer;
  }

  public void setConsumer(ConsumerPojo consumer) {
    this.consumer = consumer;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getConsumerCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    ActionContext ac = ActionContext.getContext();
    ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
    page.setRowCount(consumerService.consumerAllCount(consumer));
    return SUCCESS;
  }

  public String consumerAllList() {
    JSONArray json = JSONArray.fromObject(consumerService.consumerAllList(consumer, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String consumerChecks() {
    if (tids != null) {
      consumerService.consumerChecks(tids);
      for (String tid : tids) {
        try {
          ConsumerPojo consumerPojo = consumerService.getfindByIdConsumer(Long.parseLong(tid));
          SysLoginPojo sysLoginPojo = new SysLoginPojo();
          SysLoginPojo loginPojo = UserUtil.getUser();
          if (UserUtil.getUser() == null) {
            return "loginpage";
          } else {
            sysLoginPojo.preUpdate(loginPojo);
          }
          sysLoginPojo.setId(consumerPojo.getUserId());
          sysLoginPojo.setType("3");
          sysLoginService.updateType(sysLoginPojo);
        } catch (SQLException e) {

          e.printStackTrace();
        }
      }
      FileUtil.alertMessageBySkip("审核成功！", "consumer.do");
    } else {
      FileUtil.alertMessageBySkip("审核失败！", "consumer.do");
    }

    return null;
  }

  public String checkConsumer() throws SQLException {
    try {
      consumerService.checkConsumer(consumer.getId());
      ConsumerPojo consumerPojo = consumerService.getfindByIdConsumer(consumer.getId());
      SysLoginPojo sysLoginPojo = new SysLoginPojo();
      SysLoginPojo loginPojo = UserUtil.getUser();
      if (UserUtil.getUser() == null) {
        return "loginpage";
      } else {
        sysLoginPojo.preUpdate(loginPojo);
      }
      sysLoginPojo.setId(consumerPojo.getUserId());
      sysLoginPojo.setType("3");
      sysLoginService.updateType(sysLoginPojo);
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String goFindConsumer() throws Exception {

    ActionContext ac = ActionContext.getContext();
    // if (os != null && os.equals("2")) {
    if (os != null) {
      ac.put("consumerPojo", consumerService.findConsumerByUserId(consumer.getUserId()));
    } else {
      ac.put("consumerPojo", consumerService.findConsumerById(consumer.getId()));
    }

    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("groups", sysDictService.getSysDictListByType("groups"));
    ac.put("channel", sysDictService.getSysDictListByType("channel"));
    ac.put("isread", sysDictService.getSysDictListByType("isread"));
    ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
    return SUCCESS;
  }

  public String updateConsumer() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      consumer.preUpdate(loginPojo);
    }
    consumerService.updateConsumer(consumer);
    if (os != null && os.equals("2")) {
      FileUtil.alertMessageBySkip("修改成功！", "sysLoginCS.do?os=" + os);
    } else {
      FileUtil.alertMessageBySkip("修改成功！", "consumer.do");
    }
    return null;
  }

  public String delConsumer() throws Exception {

    consumerService.delConsumer(consumer.getId());
    FileUtil.alertMessageBySkip("删除成功！", "consumer.do");

    return null;
  }

  public String applyConsumer() throws Exception {
    SysLoginPojo loginPojo = UserUtil.getWebUser();
    if (loginPojo != null) {
      if (loginPojo.getType().equals("1")) {
        if (manufacturerService.findManufacturerByUserId(loginPojo.getId()) != null) {
          FileUtil.alertMessageBySkip("您已提交过供应商的申请，请不要重复申请！", "goIndexWeb.do");
          return null;
        } else if (consumerService.findConsumerByUserId(loginPojo.getId()) != null) {
          FileUtil.alertMessageBySkip("您已提交过分销商的申请，请不要重复申请！", "goIndexWeb.do");
          return null;
        } else {
          return SUCCESS;
        }
      } else if (loginPojo.getType().equals("3")) {
        FileUtil.alertMessageBySkip("欢迎采购！", "goProductListWeb.do");
        return null;
      } else {
        FileUtil.alertMessageBySkip("您当前登录的用户类型无法进行该操作！", "goIndexWeb.do");
        return null;
      }
    }

    return "loginweb";

  }

  public String addConsumer() throws Exception {
    SysLoginPojo loginPojo = UserUtil.getWebUser();
    if (loginPojo == null) {
      return "goIndexWeb.do";
    } else {
      consumer.preUpdate(loginPojo);
    }
    if (manufacturerService.findManufacturerByUserId(loginPojo.getId()) != null) {
      FileUtil.alertMessageBySkip("您已提交过供应商的申请，请不要重复申请！", "goIndexWeb.do");
    } else if (consumerService.findConsumerByUserId(loginPojo.getId()) != null) {
      FileUtil.alertMessageBySkip("您已提交过分销商的申请，请不要重复申请！", "goIndexWeb.do");
    } else {
      consumer.setCreateDate(new Date());
      consumer.setCreateBy(loginPojo.getId());
      consumerService.insertConsumer(consumer);
      FileUtil.alertMessageBySkip("申请已提交，您的申请我们的工作人员将会在3个工作日内审核", "goIndexWeb.do");
    }
    return null;
  }

  /***
   * go修改分销商个人信息
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateConsumerWeb() throws Exception {
    SysLoginPojo loginPojo = UserUtil.getWebUser();
    if (loginPojo == null) {
      return "goIndexWeb.do";
    } else {
      goUpdateConsumer = consumerService.findConsumerByUserId(loginPojo.getId());
    }
    return SUCCESS;
  }

  public String updateConsumerWeb() throws Exception {
    SysLoginPojo loginPojo = UserUtil.getWebUser();
    if (loginPojo == null) {
      return "goIndexWeb.do";
    } else if (consumerService.findConsumerByUserId(loginPojo.getId()) != null) {
      consumerService.updateConsumerWeb(goUpdateConsumer);
    } else {
      consumerService.insertConsumer(goUpdateConsumer);
    }
    FileUtil.alertMessageBySkip("操作成功！", "goConsumerWeb.do");
    return null;
  }

  public String consumerAgreement() {
    HelpPojo pojo = new HelpPojo();
    pojo.setId(236L);
    helpPojo = helpService.goHelpUpdate(pojo);
    ActionContext ac = ActionContext.getContext();
    ac.put("tittle", helpPojo.getTitle());
    ac.put("content", helpPojo.getContent());
    return SUCCESS;
  }

  public String consumerAgreements() {
    HelpPojo pojo = new HelpPojo();
    pojo.setId(248L);
    helpPojo = helpService.goHelpUpdate(pojo);
    ActionContext ac = ActionContext.getContext();
    ac.put("tittle", helpPojo.getTitle());
    ac.put("content", helpPojo.getContent());
    return SUCCESS;
  }

}
