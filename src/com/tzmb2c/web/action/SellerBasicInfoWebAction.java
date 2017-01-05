package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.MD5Util;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.DeliveryAddressPojo;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserVerifyPojo;
import com.tzmb2c.web.service.DeliveryAddressService;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserVerifyService;

public class SellerBasicInfoWebAction extends SuperAction {

  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private DeliveryAddressService deliveryAddressService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private UserVerifyService userVerifyService;

  private ManufacturerPojo manufacturerPojo;
  private List<ManufacturerPojo> manufacturerPojos;
  private SysLoginPojo sysLoginPojo;
  private String uLoginname;
  private String oldPass;
  private String oldPasswd;
  private String newPasswd;
  private String newPasswdRepeat;
  private Long uid;
  private DeliveryAddressPojo deliveryAddressPojo;
  private DeliveryAddressPojo deliveryAddressPojo1;
  private DeliveryAddressPojo deliveryAddressPojo2;
  private Long t;
  private String phonecode;

  public String getPhonecode() {
    return phonecode;
  }

  public void setPhonecode(String phonecode) {
    this.phonecode = phonecode;
  }

  public Long getT() {
    return t;
  }

  public void setT(Long t) {
    this.t = t;
  }

  public DeliveryAddressPojo getDeliveryAddressPojo() {
    return deliveryAddressPojo;
  }

  public void setDeliveryAddressPojo(DeliveryAddressPojo deliveryAddressPojo) {
    this.deliveryAddressPojo = deliveryAddressPojo;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public String getNewPasswdRepeat() {
    return newPasswdRepeat;
  }

  public void setNewPasswdRepeat(String newPasswdRepeat) {
    this.newPasswdRepeat = newPasswdRepeat;
  }

  public String getNewPasswd() {
    return newPasswd;
  }

  public void setNewPasswd(String newPasswd) {
    this.newPasswd = newPasswd;
  }

  public String getOldPasswd() {
    return oldPasswd;
  }

  public void setOldPasswd(String oldPasswd) {
    this.oldPasswd = oldPasswd;
  }

  public String getOldPass() {
    return oldPass;
  }

  public void setOldPass(String oldPass) {
    this.oldPass = oldPass;
  }

  public String getuLoginname() {
    return uLoginname;
  }

  public void setuLoginname(String uLoginname) {
    this.uLoginname = uLoginname;
  }

  public SysLoginPojo getSysLoginPojo() {
    return sysLoginPojo;
  }

  public void setSysLoginPojo(SysLoginPojo sysLoginPojo) {
    this.sysLoginPojo = sysLoginPojo;
  }

  public DeliveryAddressPojo getDeliveryAddressPojo1() {
    return deliveryAddressPojo1;
  }

  public void setDeliveryAddressPojo1(DeliveryAddressPojo deliveryAddressPojo1) {
    this.deliveryAddressPojo1 = deliveryAddressPojo1;
  }

  public DeliveryAddressPojo getDeliveryAddressPojo2() {
    return deliveryAddressPojo2;
  }

  public void setDeliveryAddressPojo2(DeliveryAddressPojo deliveryAddressPojo2) {
    this.deliveryAddressPojo2 = deliveryAddressPojo2;
  }

  public String getProfileWeb() throws SQLException {
    ActionContext actionContext = ActionContext.getContext();
    new HashMap<String, Object>();
    SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
    if (logiPojo != null) {
      manufacturerPojo = manufacturerService.findManufacturerByUserId(logiPojo.getId());
      if (manufacturerPojo != null) {
        manufacturerPojo.setCreateDateStr(StringUtil.dateString(manufacturerPojo.getCreateDate()));
      }
    }
    return SUCCESS;
  }

  public ManufacturerPojo getManufacturerPojo() {
    return manufacturerPojo;
  }

  public void setManufacturerPojo(ManufacturerPojo manufacturerPojo) {
    this.manufacturerPojo = manufacturerPojo;
  }

  public List<ManufacturerPojo> getManufacturerPojos() {
    return manufacturerPojos;
  }

  public void setManufacturerPojos(List<ManufacturerPojo> manufacturerPojos) {
    this.manufacturerPojos = manufacturerPojos;
  }

  /**
   * 修改密码页面
   * 
   * @return
   */
  public String goPasswdWeb() {

    return SUCCESS;
  }

  /**
   * 保存修改的密码
   * 
   * @return
   * @throws SQLException
   */
  public String passChangeWeb() throws SQLException {
    try {
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      }
      uLoginname = sysLogin.getLoginname();
      SysLoginPojo sysLoginPojo = new SysLoginPojo();
      sysLoginPojo.setLoginname(uLoginname);
      oldPass = sysLoginService.sysLoginFindId(sysLoginPojo).getPassword();
      if (oldPasswd == null || "".equals(oldPasswd.trim())) {
        FileUtil.alertMessageBySkip("输入的旧密码不能为空！", "goPasswdWeb.do");
      } else if (oldPasswd != null && !"".equals(oldPasswd)
          && !MD5Util.MD5(oldPasswd).equals(oldPass)) {
        FileUtil.alertMessageBySkip("请输入正确的旧密码！", "goPasswdWeb.do");
      } else if (newPasswd == null || "".equals(newPasswd.trim())) {
        FileUtil.alertMessageBySkip("请输入新密码！", "goPasswdWeb.do");
      } else if (newPasswdRepeat == null || "".equals(newPasswdRepeat.trim())) {
        FileUtil.alertMessageBySkip("请输入两次新密码！", "goPasswdWeb.do");
      } else if (newPasswd != null && !"".equals(newPasswd.trim()) && newPasswdRepeat != null
          && !"".equals(newPasswdRepeat.trim()) && !newPasswd.equals(newPasswdRepeat)) {
        FileUtil.alertMessageBySkip("两次输入的新密码必须一致！", "goPasswdWeb.do");
      } else if (newPasswd != null && newPasswd.length() < 6) {
        FileUtil.alertMessageBySkip("新密码强度太弱，请换个！", "goPasswdWeb.do");
      } else if (newPasswd != null && oldPasswd != null && oldPasswd.equals(newPasswd)) {
        FileUtil.alertMessageBySkip("新密码与旧密码相同，请换个！", "goPasswdWeb.do");
      } else if (newPasswd != null && !"".equals(newPasswd.trim()) && newPasswdRepeat != null
          && !"".equals(newPasswdRepeat.trim()) && newPasswd.equals(newPasswdRepeat)) {
        sysLogin.setPassword(MD5Util.MD5(newPasswd));
        sysLoginService.updatePassword(sysLogin);
        FileUtil.alertMessageBySkip("修改成功！", "doSellerLogout.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 修改退货地址页面
   * 
   * @return
   * @throws SQLException
   */
  public String goAddressWeb() throws SQLException {
    try {
      if (page == null) {
        page = new Pager();
      }
      DeliveryAddressPojo deliveryAddressPojo = new DeliveryAddressPojo();
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      }
      uid = sysLogin.getId();
      deliveryAddressPojo.setUserId(uid);
      deliveryAddressPojo.setStatus(1);
      page.setRowCount(deliveryAddressService.deliveryAddressAllCount(deliveryAddressPojo));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 本ID的地址内容
   * 
   * @return
   * @throws SQLException
   */
  public String getAddressListWeb() throws SQLException {
    try {
      if (page == null) {
        page = new Pager();
      }
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      DeliveryAddressPojo deliveryAddressPojo = new DeliveryAddressPojo();
      uid = sysLogin.getId();
      deliveryAddressPojo.setUserId(uid);
      // deliveryAddressPojo.setStatus(1);
      List<DeliveryAddressPojo> list =
          deliveryAddressService.deliveryAddressAllList(deliveryAddressPojo, page);
      for (DeliveryAddressPojo deliveryAddressPojos : list) {
        deliveryAddressPojos.setRealarea(deliveryAddressPojos.getProvinceName() + "  "
            + deliveryAddressPojos.getCityName() + "  " + deliveryAddressPojos.getAreaName());
      }
      JSONArray json = JSONArray.fromObject(list);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 本ID的地址条数
   * 
   * @return
   * @throws SQLException
   */
  public String getAddressCountWeb() throws SQLException {
    try {
      if (page == null) {
        page = new Pager();
      }
      DeliveryAddressPojo deliveryAddressPojo = new DeliveryAddressPojo();
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      uid = sysLogin.getId();
      deliveryAddressPojo.setUserId(uid);
      // deliveryAddressPojo.setStatus(1);
      int i = deliveryAddressService.deliveryAddressAllCount(deliveryAddressPojo);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 删除本ID的对应地址
   * 
   * @return
   */
  public String delAddressWeb() {
    try {
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      }
      deliveryAddressService.delDeliveryAddressWeb(deliveryAddressPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "goAddressWeb.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "goAddressWeb.do");
    }
    return null;
  }

  /**
   * 新增本ID的地址
   * 
   * @return
   */
  public String addAddressWeb() {
    try {
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      }
      uid = sysLogin.getId();
      deliveryAddressPojo1.setUserId(uid);
      deliveryAddressPojo1.setStatus(1);
      deliveryAddressPojo1.setSorting(0);
      if (deliveryAddressPojo1.getProvince() == null || deliveryAddressPojo1.getCity() == null
          || deliveryAddressPojo1.getArea() == null || "".equals(deliveryAddressPojo1.getAddress())
          || "".equals(deliveryAddressPojo1.getConsignee())
          || "".equals(deliveryAddressPojo1.getConsigneePhone())) {
        FileUtil.alertMessageBySkip("亲，还有必要的信息没填完哦~请重新填写~", "goAddressWeb.do");
        return null;
      }
      if (deliveryAddressPojo1.getIsDefault() == null) {
        deliveryAddressPojo1.setIsDefault(0);
      }
      deliveryAddressService.addDeliveryAddress(deliveryAddressPojo1);
      if (deliveryAddressPojo1.getIsDefault() == 1) {
        DeliveryAddressPojo deliveryAddress = new DeliveryAddressPojo();
        deliveryAddress.setUserId(uid);
        deliveryAddress.setId(deliveryAddressPojo1.getId());
        deliveryAddress.setIsDefault(0);
        deliveryAddressService.updateAddressToNotDefault(deliveryAddress);
      }
      FileUtil.alertMessageBySkip("新增成功！", "goAddressWeb.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "goAddressWeb.do");
    }
    return null;
  }

  /**
   * 跳转编辑地址页面
   * 
   * @return
   * @throws SQLException
   */
  public String goUpdateAddressWeb() throws SQLException {
    if (deliveryAddressPojo != null) {
      deliveryAddressPojo1 =
          deliveryAddressService.findDeliveryAddressById(deliveryAddressPojo.getId());
    }
    return SUCCESS;
  }

  /**
   * 编辑地址页面
   * 
   * @return
   * @throws SQLException
   */
  public String updateAddressWeb() throws SQLException {
    try {
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录", "sellerLogin.do");
      }
      uid = sysLogin.getId();
      deliveryAddressPojo1.setUserId(uid);
      deliveryAddressPojo1.setStatus(1);
      deliveryAddressPojo1.setSorting(0);
      if (deliveryAddressPojo1.getProvince() == null || deliveryAddressPojo1.getCity() == null
          || deliveryAddressPojo1.getArea() == null || "".equals(deliveryAddressPojo1.getAddress())
          || "".equals(deliveryAddressPojo1.getConsignee())
          || "".equals(deliveryAddressPojo1.getConsigneePhone())) {
        t = deliveryAddressPojo1.getId();
        FileUtil.alertMessageBySkip("亲，还有必要的信息没填完哦~请重新填写~",
            "goUpdateAddressWeb.do?deliveryAddressPojo1.id=t");
        return null;
      }
      if (deliveryAddressPojo1.getIsDefault() == null) {
        deliveryAddressPojo1.setIsDefault(0);
      }
      deliveryAddressService.updateDeliveryAddress(deliveryAddressPojo1);
      if (deliveryAddressPojo1.getIsDefault() == 1) {
        DeliveryAddressPojo deliveryAddress = new DeliveryAddressPojo();
        deliveryAddress.setUserId(uid);
        deliveryAddress.setId(deliveryAddressPojo1.getId());
        deliveryAddress.setIsDefault(0);
        deliveryAddressService.updateAddressToNotDefault(deliveryAddress);
      }
      FileUtil.alertMessageBySkip("编辑成功！", "goAddressWeb.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("编辑失败！", "goAddressWeb.do");
    }
    return null;
  }

  /**
   * 忘记密码页面
   * 
   * @return
   */
  public String goForgetPasswordWeb() {

    return SUCCESS;
  }

  /**
   * 忘记密码操作
   * 
   * @return
   * @throws SQLException
   */
  public String doForgetPassword() throws SQLException {
    try {
      if (sysLoginPojo != null && sysLoginPojo.getLoginname2() != null
          && !"".equals(sysLoginPojo.getLoginname2().trim()) && phonecode != null
          && !"".equals(phonecode.trim())) {
        SysLoginPojo sysLogin =
            sysLoginService.getSysLoginByLoginName(sysLoginPojo.getLoginname2().trim());
        if (sysLogin != null) {
          UserVerifyPojo userVerify = new UserVerifyPojo();
          userVerify.setLoginname(sysLoginPojo.getLoginname2().trim());
          userVerify = userVerifyService.findNewestByPhone(userVerify);
          if (userVerify == null || userVerify.getCaptcha() == null
              || !phonecode.trim().equals(userVerify.getCaptcha())) {
            FileUtil.alertMessageBySkip("验证码错误，请重新输入！~", "goForgetPasswordWeb.do");
          } else {
            if (sysLoginPojo.getPassword() == null || "".equals(sysLoginPojo.getPassword())) {
              FileUtil.alertMessageBySkip("请输入新密码！~", "goForgetPasswordWeb.do");
            } else if (sysLoginPojo.getPassword() != null
                && sysLoginPojo.getPassword().length() < 6) {
              FileUtil.alertMessageBySkip("新密码强度太弱，请换个！~", "goForgetPasswordWeb.do");
            } else {
              SysLoginPojo sysLogin2 = new SysLoginPojo();
              sysLogin2.setPassword(MD5Util.MD5(sysLoginPojo.getPassword()));
              sysLogin2.setId(sysLogin.getId());
              sysLoginService.updatePassword(sysLogin2);
              FileUtil.alertMessageBySkip("修改成功！", "sellerLogin.do");
            }
          }
        } else {
          FileUtil.alertMessageBySkip("该手机号码尚未注册！~", "goForgetPasswordWeb.do");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
