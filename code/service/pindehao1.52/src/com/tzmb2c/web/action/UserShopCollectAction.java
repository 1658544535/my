package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserShopCollectPojo;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserShopCollectService;

public class UserShopCollectAction extends SuperAction {

  @Autowired
  private UserShopCollectService userShopCollectService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ShopService shopService;

  private UserShopCollectPojo userShopCollect;
  private String result;
  private String[] tids;
  private String jsonStr;

  public UserShopCollectPojo getUserShopCollect() {
    return userShopCollect;
  }

  public void setUserShopCollect(UserShopCollectPojo userShopCollect) {
    this.userShopCollect = userShopCollect;
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

  public String getUserShopCollectCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(userShopCollectService.userShopCollectAllCount(userShopCollect));
    ActionContext ac = ActionContext.getContext();
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("channel", sysDictService.getSysDictListByType("channel"));

    return SUCCESS;
  }

  public String userShopCollectAllList() {
    JSONArray json =
        JSONArray.fromObject(userShopCollectService.userShopCollectAllList(userShopCollect, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String userShopCollectDeleteId() {
    if (tids != null) {
      userShopCollectService.userShopCollectDeleteId(tids);
      // FileUtil.alertMessageBySkip("删除成功", "gomanageTicketRule.do");
      FileUtil.alertMessageBySkip("删除成功！", "userShopCollect.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "userShopCollect.do");
    }

    return null;
  }

  public String deleUserShopCollect() throws SQLException {
    try {
      userShopCollectService.delUserShopCollect(userShopCollect.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String goFindUserShopCollect() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("userShopCollectPojo",
        userShopCollectService.findUserShopCollectById(userShopCollect.getId()));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("channel", sysDictService.getSysDictListByType("channel"));
    ac.put("sysLoginPojo", sysLoginService.findSysLoginById(userShopCollect.getUserId()));
    return SUCCESS;
  }

  public String updateUserShopCollect() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      userShopCollect.preUpdate(loginPojo);
    }

    userShopCollectService.updateUserShopCollect(userShopCollect);
    FileUtil.alertMessageBySkip("修改成功！", "userShopCollect.do");

    return null;
  }

  public String addUserShopCollect() throws Exception {

    SysLoginPojo loginPojo = UserUtil.getWebUser();
    if (loginPojo == null) {
      return "doLoginWeb.do";
    } else {
      userShopCollect.preUpdate(loginPojo);
      userShopCollect.setUserId(loginPojo.getId());
    }
    ShopPojo pojo = new ShopPojo();
    pojo = shopService.findShopById(userShopCollect.getShopId());
    userShopCollect.setSuserId(pojo.getUserId());
    if (userShopCollectService.userShopCollectAllCount(userShopCollect) > 0) {
      this.result = "0";
    } else {
      userShopCollectService.insertUserShopCollect(userShopCollect);
      this.result = "1";
    }
    return SUCCESS;
  }

  public String goUserShopCollectWeb() throws SQLException {
    ActionContext ac = ActionContext.getContext();
    ac.put("productlist", productService.productAll());
    return SUCCESS;
  }

  public String userShopCollectWeb() throws Exception {
    SysLoginPojo loginUser = UserUtil.getWebUser();
    List<UserShopCollectPojo> userShopCollect2 =
        userShopCollectService.findUserShopCollectByUserId(loginUser.getId());
    JSONArray json = JSONArray.fromObject(userShopCollect2);
    this.jsonStr = json.toString();
    return SUCCESS;
  }

  public String getShopProduct() {
    List<ProductPojo> productListYes =
        productService.productForUserYes5(userShopCollect.getSuserId());
    JSONArray json = JSONArray.fromObject(productListYes);
    this.jsonStr = json.toString();
    return SUCCESS;

  }

  public String deluserShopCollectWeb() throws Exception {
    try {
      userShopCollectService.delUserShopCollect(userShopCollect.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String getJsonStr() {
    return jsonStr;
  }

  public void setJsonStr(String jsonStr) {
    this.jsonStr = jsonStr;
  }
}
