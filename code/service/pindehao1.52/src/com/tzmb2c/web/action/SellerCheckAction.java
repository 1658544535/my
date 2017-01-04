package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserCertificatesPhotoPojo;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserCertificatesPhotoService;

public class SellerCheckAction extends SuperAction {
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private ManufacturerService manufacturerService;
  @Autowired
  private UserCertificatesPhotoService userCertificatesPhotoService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private SellerService sellerService;
  private String returnContent;
  private SysLoginPojo sysLoginPojo;
  private List<SysLoginPojo> sysLoginPojos;
  private ManufacturerPojo manufacturerPojo;
  private List<ManufacturerPojo> manufacturerPojos;
  private String result;// 用于返回操作状态
  private UserCertificatesPhotoPojo userCertificatesPhotoPojo;
  private List<UserCertificatesPhotoPojo> userCertificatesPhotoPojos;
  private ShopPojo shopPojo;
  private List<ShopPojo> shopPojos;

  public String getReturnContent() {
    return returnContent;
  }

  public void setReturnContent(String returnContent) {
    this.returnContent = returnContent;
  }

  /**
   * 商家信息条数
   * 
   * @return
   */
  public String sellerCheckCount() {
    if (page == null) {
      page = new Pager();
    }
    /*
     * if (manufacturerPojo != null && manufacturerPojo.getCreateDateStr() != "") {
     * manufacturerPojo.setCreateDateStr(manufacturerPojo.getCreateDateStr()+" 00:00:00"); }
     */
    page.setRowCount(manufacturerService.manufacturerAllCount(manufacturerPojo));
    return SUCCESS;
  }

  /**
   * 商家信息列表
   * 
   * @return
   * @throws SQLException
   */
  public String sellerCheckList() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    /*
     * if (manufacturerPojo != null && manufacturerPojo.getCreateDateStr() != "") {
     * manufacturerPojo.setCreateDateStr(manufacturerPojo.getCreateDateStr()+" 00:00:00"); }
     */
    manufacturerPojos = manufacturerService.manufacturerAllList(manufacturerPojo, page);
    JSONArray json = JSONArray.fromObject(manufacturerPojos);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 商家信息审核页面
   * 
   * @return
   * @throws SQLException
   */
  public String sellerChecking() throws SQLException {
    if (manufacturerPojo != null) {
      manufacturerPojo = manufacturerService.findManufacturerByUserId(manufacturerPojo.getUserId());
      userCertificatesPhotoPojo =
          userCertificatesPhotoService.findUserCertificatesPhotoByUid(manufacturerPojo.getUserId());
    }
    return SUCCESS;
  }

  /**
   * 商家取消信息审核页面
   * 
   * @return
   * @throws SQLException
   */
  public String sellerUnchecking() throws SQLException {
    if (manufacturerPojo != null) {
      manufacturerPojo = manufacturerService.findManufacturerByUserId(manufacturerPojo.getUserId());
      userCertificatesPhotoPojo =
          userCertificatesPhotoService.findUserCertificatesPhotoByUid(manufacturerPojo.getUserId());
    }
    return SUCCESS;
  }


  /**
   * 商家信息审核提交
   * 
   * @return
   * @throws SQLException
   */
  public String sellerCheckSubmit() throws SQLException {
    try {
      if (manufacturerPojo != null) {
        ManufacturerPojo m = new ManufacturerPojo();
        m = manufacturerService.findManufacturerByUserId(manufacturerPojo.getUserId());
        if (m != null) {
          manufacturerService.checkManufacturer(m.getId());
        }
        ShopPojo s = new ShopPojo();
        s = shopService.getfindByIdShopWeb(manufacturerPojo.getUserId());
        if (s != null) {
          shopService.checkShop(s.getId());
        }
        UserCertificatesPhotoPojo u = new UserCertificatesPhotoPojo();
        u =
            userCertificatesPhotoService.findUserCertificatesPhotoByUid(manufacturerPojo
                .getUserId());
        if (u != null) {
          userCertificatesPhotoService.checkUserCertificatesPhoto(u.getId());
        }
      }
      FileUtil.alertMessageBySkip("审核成功！", "sellerCheckManage.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("审核失败！", "sellerChecking.do?manufacturerPojo.userId"
          + manufacturerPojo.getUserId());
    }
    return null;
  }

  /**
   * 商家信息取消审核提交
   * 
   * @return
   * @throws SQLException
   */
  public String sellerUncheckSubmit() throws SQLException {
    try {
      if (manufacturerPojo != null) {
        ManufacturerPojo m = new ManufacturerPojo();
        m = manufacturerService.findManufacturerByUserId(manufacturerPojo.getUserId());
        if (m != null) {
          manufacturerService.uncheckManufacturer(m.getId());
          manufacturerPojo.setId(m.getId());
          manufacturerService.updateManufacturer(manufacturerPojo);
        }
        ShopPojo s = new ShopPojo();
        s = shopService.getfindByIdShopWeb(manufacturerPojo.getUserId());
        if (s != null) {
          shopService.uncheckShop(s.getId());
        }
        UserCertificatesPhotoPojo u = new UserCertificatesPhotoPojo();
        u =
            userCertificatesPhotoService.findUserCertificatesPhotoByUid(manufacturerPojo
                .getUserId());
        if (u != null) {
          userCertificatesPhotoService.uncheckUserCertificatesPhoto(u.getId());
        }
      }
      FileUtil.alertMessageBySkip("取消审核成功！", "sellerCheckManage.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("取消审核失败！", "sellerChecking.do?manufacturerPojo.userId"
          + manufacturerPojo.getUserId());
    }
    return null;
  }

  /**
   * 商家信息退回修改
   * 
   * @return
   * @throws SQLException
   */
  public String sellerReturning() throws SQLException {
    try {
      if (manufacturerPojo != null) {
        manufacturerService.updateManufacturer(manufacturerPojo);
      }
      FileUtil.alertMessageBySkip("退回成功！", "sellerCheckManage.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("退回失败！", "sellerChecking.do?manufacturerPojo.userId"
          + manufacturerPojo.getUserId());
    }
    return null;
  }

  public SysLoginPojo getSysLoginPojo() {
    return sysLoginPojo;
  }

  public void setSysLoginPojo(SysLoginPojo sysLoginPojo) {
    this.sysLoginPojo = sysLoginPojo;
  }

  public List<SysLoginPojo> getSysLoginPojos() {
    return sysLoginPojos;
  }

  public void setSysLoginPojos(List<SysLoginPojo> sysLoginPojos) {
    this.sysLoginPojos = sysLoginPojos;
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

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public UserCertificatesPhotoPojo getUserCertificatesPhotoPojo() {
    return userCertificatesPhotoPojo;
  }

  public void setUserCertificatesPhotoPojo(UserCertificatesPhotoPojo userCertificatesPhotoPojo) {
    this.userCertificatesPhotoPojo = userCertificatesPhotoPojo;
  }

  public List<UserCertificatesPhotoPojo> getUserCertificatesPhotoPojos() {
    return userCertificatesPhotoPojos;
  }

  public void setUserCertificatesPhotoPojos(
      List<UserCertificatesPhotoPojo> userCertificatesPhotoPojos) {
    this.userCertificatesPhotoPojos = userCertificatesPhotoPojos;
  }

  public ShopPojo getShopPojo() {
    return shopPojo;
  }

  public void setShopPojo(ShopPojo shopPojo) {
    this.shopPojo = shopPojo;
  }

  public List<ShopPojo> getShopPojos() {
    return shopPojos;
  }

  public void setShopPojos(List<ShopPojo> shopPojos) {
    this.shopPojos = shopPojos;
  }
}
