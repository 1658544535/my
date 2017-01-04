package com.tzmb2c.web.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.DeliveryAddressPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.DeliveryAddressService;
import com.tzmb2c.web.service.SysDictService;

/**
 * 收货地址管理
 * 
 * @author creazylee
 * 
 */

public class DeliveryAddressAction extends SuperAction {

  private String[] tids;
  private String type;
  private String result;
  private String cidsStr;
  @Autowired
  private DeliveryAddressService deliveryAddressService;
  @Autowired
  private SysDictService sysDictService;
  private DeliveryAddressPojo deliveryAddressPojo;
  private List<DeliveryAddressPojo> deliveryAddressPojoList = null, deliveryAddressListWeb;
  private List<SysDictPojo> statusSysDictList = null;
  private int uid, province, city, area;
  private String tel, name, address, postCode;
  private int addId;// 选择的地址


  public int getAddId() {
    return addId;
  }

  public void setAddId(int addId) {
    this.addId = addId;
  }

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public int getProvince() {
    return province;
  }

  public void setProvince(int province) {
    this.province = province;
  }

  public int getCity() {
    return city;
  }

  public void setCity(int city) {
    this.city = city;
  }

  public int getArea() {
    return area;
  }

  public void setArea(int area) {
    this.area = area;
  }

  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public String getCidsStr() {
    return cidsStr;
  }

  public void setCidsStr(String cidsStr) {
    this.cidsStr = cidsStr;
  }

  public List<DeliveryAddressPojo> getDeliveryAddressListWeb() {
    return deliveryAddressListWeb;
  }

  public void setDeliveryAddressListWeb(List<DeliveryAddressPojo> deliveryAddressListWeb) {
    this.deliveryAddressListWeb = deliveryAddressListWeb;
  }

  public List<SysDictPojo> getStatusSysDictList() {
    return statusSysDictList;
  }

  public void setStatusSysDictList(List<SysDictPojo> statusSysDictList) {
    this.statusSysDictList = statusSysDictList;
  }

  public DeliveryAddressService getDeliveryAddressService() {
    return deliveryAddressService;
  }

  public void setDeliveryAddressService(DeliveryAddressService deliveryAddressService) {
    this.deliveryAddressService = deliveryAddressService;
  }

  public DeliveryAddressPojo getDeliveryAddressPojo() {
    return deliveryAddressPojo;
  }

  public void setDeliveryAddressPojo(DeliveryAddressPojo deliveryAddressPojo) {
    this.deliveryAddressPojo = deliveryAddressPojo;
  }

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

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  /***
   * 查找数据字典
   */
  private void getList() {
    try {
      statusSysDictList = sysDictService.getSysDictListByType("status");
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  /***
   * 查找所有收货地址信息
   * 
   * @return
   */
  public String deliveryAddressAllList() {
    getList();
    deliveryAddressPojoList =
        deliveryAddressService.deliveryAddressAllList(deliveryAddressPojo, page);
    JSONArray json = JSONArray.fromObject(deliveryAddressPojoList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /***
   * 获取所有收货地址信息数目
   * 
   * @return
   * @throws Exception
   */
  public String getDeliveryAddressCount() throws Exception {
    getList();
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(deliveryAddressService.deliveryAddressAllCount(deliveryAddressPojo));
    return SUCCESS;
  }

  /***
   * 跳转到增加收货地址信息页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddAttention() throws Exception {
    return SUCCESS;
  }

  /***
   * 添加单条收货地址信息
   * 
   * @return
   * @throws IOException
   */
  public String addAttention() throws IOException {
    try {
      deliveryAddressPojo.setCreateBy(8L);;
      deliveryAddressService.addDeliveryAddress(deliveryAddressPojo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    FileUtil.alertMessageBySkip("添加成功！", "purchaserAttentionManage.do");
    return null;
  }

  /***
   * 删除单条收货地址信息
   * 
   * @return
   * @throws SQLException
   */
  public String delDeliveryAddress() throws SQLException {
    try {
      deliveryAddressService.delDeliveryAddress(deliveryAddressPojo);
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  /*
   * 前台-删除收货地址
   */

  public String delAddress() throws SQLException {
    deliveryAddressService.delDeliveryAddress(deliveryAddressPojo);
    FileUtil.alertMessageBySkip("删除成功！", "deliveryAddressListWeb.do");
    return null;
  }

  /***
   * 删除全部收货地址信息
   * 
   * @return
   */
  public String delAllDeliveryAddressById() {
    deliveryAddressService.delAllDeliveryAddressById(tids);
    FileUtil.alertMessageBySkip("删除成功！", "deliveryAddressManage.do");
    return null;
  }

  /***
   * 查找单条收货地址信息
   * 
   * @return
   * @throws Exception
   */
  public String goFindDeliveryAddress() throws Exception {
    getList();
    deliveryAddressPojo =
        deliveryAddressService.findDeliveryAddressById(deliveryAddressPojo.getId());
    return SUCCESS;
  }

  /***
   * 跳转到更新页面
   * 
   * @return
   * @throws Exception
   */
  public String goFindAttention() throws Exception {
    deliveryAddressPojo =
        deliveryAddressService.findDeliveryAddressById(deliveryAddressPojo.getId());
    return SUCCESS;
  }

  /***
   * 更新单条收货地址信息
   * 
   * @return
   * @throws Exception
   */
  public String updateAttention() throws Exception {
    deliveryAddressPojo.setCreateBy(8L);;
    deliveryAddressService.updateDeliveryAddress(deliveryAddressPojo);
    FileUtil.alertMessageBySkip("修改成功！", "purchaserAttentionManage.do");
    return null;
  }

  /***
   * 审核单条收货地址信息
   * 
   * @return
   * @throws SQLException
   */
  public String verifyDeliveryAddress() throws SQLException {
    deliveryAddressService.verifyDeliveryAddress(deliveryAddressPojo);
    FileUtil.alertMessageBySkip("审核成功！", "deliveryAddressManage.do");
    return null;
  }

  /***
   * 审核全部收货地址信息
   * 
   * @return
   */
  public String checkAllDeliveryAddressById() {
    deliveryAddressService.checkAllDeliveryAddressById(tids);
    FileUtil.alertMessageBySkip("审核成功！", "deliveryAddressManage.do");
    return null;
  }

  /***
   * 获取所有收货地址信息 前台
   * 
   * @return
   */
  public String deliveryAddressListWeb() {
    // getList();
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    try {
      deliveryAddressListWeb = deliveryAddressService.getAddressByUser(sysLogin.getId());
    } catch (SQLException e) {

      e.printStackTrace();
    }
    return SUCCESS;
  }

  /***
   * 跳转到修改我的地址的页面 前台
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateDeliveryAddressWeb() throws Exception {
    // 查询单条我的收货地址
    deliveryAddressPojo =
        deliveryAddressService.findDeliveryAddressById(deliveryAddressPojo.getId());
    return SUCCESS;
  }

  /***
   * 修改单条：我的收货地址 前台
   * 
   * @return
   * @throws Exception
   */
  public String updateDeliveryAddressWeb() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    deliveryAddressPojo.setCreateBy(sysLogin.getId());
    deliveryAddressService.updateDeliveryAddress(deliveryAddressPojo);
    if (!cidsStr.equals("")) {
      FileUtil.alertMessageBySkip("修改地址成功！", "goSelectAddress.do?cidsStr=" + cidsStr);// 返回提交订单的页面
    } else {
      FileUtil.alertMessageBySkip("修改地址成功！", "deliveryAddressListWeb.do");// 返回我的地址列表页面
    }
    return null;
  }

  /***
   * 把对应id的地址修改成我的默认收货地址 前台
   * 
   * @return
   * @throws Exception
   */
  public String updateAddressToDefault() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    // deliveryAddressPojo.setCreateBy(sysLogin.getId());
    deliveryAddressPojo.setUserId(sysLogin.getId());
    deliveryAddressPojo.setIsDefault(1);
    deliveryAddressService.updateAddressToDefault(deliveryAddressPojo);// 设置为默认地址
    // 将其他id的地址设置成非默认状态
    deliveryAddressPojo.setUserId(sysLogin.getId());
    deliveryAddressPojo.setIsDefault(0);
    deliveryAddressService.updateAddressToNotDefault(deliveryAddressPojo);
    FileUtil.alertMessageBySkip("修改成功！", "deliveryAddressListWeb.do");
    return null;
  }

  /***
   * 取消对应id的地址修改成我的默认收货地址 前台
   * 
   * @return
   * @throws Exception
   */
  public String cancelAddressToDefault() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getWebUser();
    // deliveryAddressPojo.setCreateBy(sysLogin.getId());
    deliveryAddressPojo.setUserId(sysLogin.getId());
    deliveryAddressPojo.setIsDefault(0);
    deliveryAddressService.updateAddressToDefault(deliveryAddressPojo);// 取消默认地址
    FileUtil.alertMessageBySkip("修改成功！", "deliveryAddressListWeb.do");
    return null;
  }
}
