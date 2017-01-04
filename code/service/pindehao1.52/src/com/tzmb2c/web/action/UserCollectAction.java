package com.tzmb2c.web.action;

import java.sql.SQLException;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserCollectPojo;
import com.tzmb2c.web.pojo.UserConsumerCollectPojo;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserCollectService;
import com.tzmb2c.web.service.UserConsumerCollectService;
import com.tzmb2c.web.service.UserShopCollectService;

public class UserCollectAction extends SuperAction {

  @Autowired
  private UserCollectService userCollectService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private ProductService productService;
  @Autowired
  private UserConsumerCollectService userConsumerCollectService;
  @Autowired
  private UserShopCollectService userShopCollectService;
  private UserConsumerCollectPojo userConsumerCollectPojo;
  private UserCollectPojo userCollect;
  private ProductPojo productPojo;
  private String result;
  private String[] tids;

  public UserCollectPojo getUserCollect() {
    return userCollect;
  }

  public void setUserCollect(UserCollectPojo userCollect) {
    this.userCollect = userCollect;
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

  public String getUserCollectCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(userCollectService.userCollectAllCount(userCollect));
    ActionContext ac = ActionContext.getContext();
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("channel", sysDictService.getSysDictListByType("channel"));

    return SUCCESS;
  }

  public String userCollectAllList() {
    JSONArray json = JSONArray.fromObject(userCollectService.userCollectAllList(userCollect, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String userCollectDeleteId() {
    if (tids != null) {
      userCollectService.userCollectDeleteId(tids);
      // FileUtil.alertMessageBySkip("删除成功", "gomanageTicketRule.do");
      FileUtil.alertMessageBySkip("删除成功！", "userCollect.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "userCollect.do");
    }

    return null;
  }

  public String deleUserCollect() throws SQLException {
    try {
      userCollectService.delUserCollect(userCollect.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String goFindUserCollect() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("userCollectPojo", userCollectService.findUserCollectById(userCollect.getId()));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("channel", sysDictService.getSysDictListByType("channel"));
    ac.put("sysLoginPojo", sysLoginService.findSysLoginById(userCollect.getUserId()));
    return SUCCESS;
  }

  public String updateUserCollect() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      userCollect.preUpdate(loginPojo);
    }

    userCollectService.updateUserCollect(userCollect);
    FileUtil.alertMessageBySkip("修改成功！", "userCollect.do");

    return null;
  }

  public String userCollectWeb() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(14);
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    ActionContext ac = ActionContext.getContext();
    UserCollectPojo u = new UserCollectPojo();
    u.setUserId(sysLogin.getId());
    page.setRowCount(userCollectService.productCollectAllCount(u));
    ac.put("userCollectlist", userCollectService.findUserCollectByUserIdPage(u, page));
    ac.put("productlist", productService.productAll());
    return SUCCESS;
  }

  public String deluserCollectWeb() throws Exception {
    try {
      userCollectService.delUserCollect(userCollect.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String addProductCollect() throws Exception {


    SysLoginPojo sysLogin = UserUtil.getWebUser();
    if (sysLogin == null) {
      this.result = "3";
    } else {
      productPojo = productService.findProduct(productPojo);
      userCollect = new UserCollectPojo();
      userCollect.setUserId(sysLogin.getId());
      userCollect.setProductId(productPojo.getId());
      userCollect = userCollectService.findUserCollectByProductId(userCollect);
      if (userCollect == null) {
        userCollect = new UserCollectPojo();
        userCollect.prePersist(sysLogin);
        userCollect.setUserId(sysLogin.getId());
        userCollect.setProductId(productPojo.getId());
        userCollectService.insertUserCollect(userCollect);
        this.result = "1";
      } else {
        this.result = "4";
      }

    }

    return SUCCESS;
  }

  public UserConsumerCollectPojo getUserConsumerCollectPojo() {
    return userConsumerCollectPojo;
  }

  public void setUserConsumerCollectPojo(UserConsumerCollectPojo userConsumerCollectPojo) {
    this.userConsumerCollectPojo = userConsumerCollectPojo;
  }

  public ProductPojo getProductPojo() {
    return productPojo;
  }

  public void setProductPojo(ProductPojo productPojo) {
    this.productPojo = productPojo;
  }

}
