package com.tzmb2c.web.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ManufacturerPojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserMakerShopPojo;
import com.tzmb2c.web.service.ManufacturerService;
import com.tzmb2c.web.service.ShopService;
import com.tzmb2c.web.service.SysAreaService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserMakerShopService;

public class UserMakerShopAction extends SuperAction {
  @Autowired
  private UserMakerShopService userMakerShopService;
  @Autowired
  private ShopService shopService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private SysAreaService sysAreaService;
  @Autowired
  private ManufacturerService manufacturerService;
  private UserMakerShopPojo userMakerShopPojo;
  private List<UserMakerShopPojo> userMakerShopPojoList;
  private File upfile;
  private File upfile1;
  private File upfile2;
  private File upfile3;
  private File upfile4;
  private int panduan;

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public File getUpfile1() {
    return upfile1;
  }

  public void setUpfile1(File upfile1) {
    this.upfile1 = upfile1;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public File getUpfile3() {
    return upfile3;
  }

  public void setUpfile3(File upfile3) {
    this.upfile3 = upfile3;
  }

  public File getUpfile4() {
    return upfile4;
  }

  public void setUpfile4(File upfile4) {
    this.upfile4 = upfile4;
  }

  public UserMakerShopPojo getUserMakerShopPojo() {
    return userMakerShopPojo;
  }

  public void setUserMakerShopPojo(UserMakerShopPojo userMakerShopPojo) {
    this.userMakerShopPojo = userMakerShopPojo;
  }

  public List<UserMakerShopPojo> getUserMakerShopPojoList() {
    return userMakerShopPojoList;
  }

  public void setUserMakerShopPojoList(List<UserMakerShopPojo> userMakerShopPojoList) {
    this.userMakerShopPojoList = userMakerShopPojoList;
  }

  /**
   * 查询全部条数
   */
  public String userMakerShopListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (userMakerShopPojo != null) {
        map.put("userName", userMakerShopPojo.getUserName());
        map.put("shopName", userMakerShopPojo.getShopName());
        map.put("status", userMakerShopPojo.getStatus());
      }
      if (panduan == 1) {
        map.put("status", 1);
      }
      int i = userMakerShopService.findUserMakerShopCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    ActionContext ac = ActionContext.getContext();
    ac.put("panduan", panduan);
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String userMakerShopListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (userMakerShopPojo != null) {
        map.put("userName", userMakerShopPojo.getUserName());
        map.put("shopName", userMakerShopPojo.getShopName());
        map.put("status", userMakerShopPojo.getStatus());
      }
      if (panduan == 1) {
        map.put("status", 1);
      }
      userMakerShopPojoList = userMakerShopService.findUserMakerShopList(map);
      JSONArray json = JSONArray.fromObject(userMakerShopPojoList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  public String delUserMakerShop() throws Exception {
    try {
      userMakerShopService.delUserMakerShop(userMakerShopPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "userMakerShop.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "userMakerShop.do");
    }
    return null;
  }

  /**
   * 跳转查看详情
   * 
   * @return
   * @throws Throwable
   */
  public String goDetailedUserMakerShop() throws Exception {
    if (userMakerShopPojo != null) {
      userMakerShopPojo = userMakerShopService.findUserMakerShopById(userMakerShopPojo.getId());
      userMakerShopPojo.setAddress(userMakerShopPojo.getProvinceName()
          + userMakerShopPojo.getCityName() + userMakerShopPojo.getAreaName()
          + userMakerShopPojo.getDetailedAddress());
    }
    return SUCCESS;
  }

  /**
   * 根据id审核
   * 
   * @return
   */

  public String checkUserMakerShop() throws Exception {
    try {
      userMakerShopService.checkUserMakerShop(userMakerShopPojo.getId());
      shopService.checkShop(userMakerShopService.findUserMakerShopById(userMakerShopPojo.getId())
          .getShopId());
      FileUtil.alertMessageBySkip("此条状态已改为“审核成功”！", "userMakerShop.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("此条审核未能成功！", "userMakerShop.do");
    }
    return null;
  }

  /**
   * 根据id取审
   * 
   * @return
   */

  public String uncheckUserMakerShop() throws Exception {
    try {
      userMakerShopService.uncheckUserMakerShop(userMakerShopPojo.getId());
      shopService.uncheckShop(userMakerShopService.findUserMakerShopById(userMakerShopPojo.getId())
          .getShopId());
      FileUtil.alertMessageBySkip("此条状态已改为“审核失败”！", "userMakerShop.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("此条审核未能失败！", "userMakerShop.do");
    }
    return null;
  }

  /**
   * 设置创阁操作 前端用户将店铺的id与是否设置创阁的值传递到后台 后台获取后判断用户的操作（设置创阁or取消创阁）
   * 判断当前店铺创阁状态，当前店铺为创阁状态时无法进行设置创阁操作，当前店铺为非创阁状态时，无法进行取消创阁操作
   * 
   * @return
   * @throws Exception
   */
  public String setGenCabinetType() throws Exception {
    try {
      // 当用户点击设置创阁时
      if (userMakerShopPojo.getIsGenCabinet() == 1) {
        // 操作前判断该店铺是否为创阁状态
        if (userMakerShopService.findUserMakerShopById(userMakerShopPojo.getId()).getIsGenCabinet() == 1) {
          FileUtil.alertMessageBySkip("该店铺已是创阁，无需此项操作！", "userMakerShop.do");
        } else {
          userMakerShopService.updateUserMakerShop(userMakerShopPojo);
          FileUtil.alertMessageBySkip("操作成功！", "userMakerShop.do");
        }
      } else if (userMakerShopPojo.getIsGenCabinet() == 0) { // 用户点击取消创阁时
        // 操作前判断该店铺是否为创阁状态
        if (userMakerShopService.findUserMakerShopById(userMakerShopPojo.getId()).getIsGenCabinet() == 0) {
          FileUtil.alertMessageBySkip("该店铺不是创阁，无法此项操作！", "userMakerShop.do");
        } else {
          userMakerShopService.updateUserMakerShop(userMakerShopPojo);
          FileUtil.alertMessageBySkip("操作成功！", "userMakerShop.do");
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("操作失败！", "userMakerShop.do");
    }
    return null;
  }

  /**
   * 编辑详情页提交
   * 
   * @return
   */

  public String updateDetailedUserMakerShop() throws Exception {
    try {
      Date now = new Date();

      // 更新至用户表
      SysLoginPojo sysLoginPojo = new SysLoginPojo();
      sysLoginPojo.setName(userMakerShopPojo.getUserName());
      sysLoginPojo.setId(userMakerShopPojo.getUserId());
      sysLoginService.updateSysLogin(sysLoginPojo);
      // 更新user_shop表
      ShopPojo shopPojo = new ShopPojo();
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/shop") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/shop/", upfile);
        shopPojo.setImages(file_name);
      }
      if (upfile1 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/shop") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/shop/", upfile1);
        shopPojo.setTopImage(file_name);
      }
      shopPojo.setId(userMakerShopPojo.getShopId());
      shopPojo.setName(userMakerShopPojo.getShopName());
      shopPojo.setPhone(userMakerShopPojo.getPhone());
      shopPojo.setProvince(Integer.valueOf(Long.toString(userMakerShopPojo.getProvince())));
      shopPojo.setCity(Integer.valueOf(Long.toString(userMakerShopPojo.getCity())));
      shopPojo.setArea(Integer.valueOf(Long.toString(userMakerShopPojo.getArea())));
      shopPojo.setAddress(userMakerShopPojo.getDetailedAddress());
      shopPojo.setUpdateDate(now);
      shopService.updateShop(shopPojo);
      // 更新user_manufacturer表
      ManufacturerPojo manufacturer =
          manufacturerService.findManufacturerByUserId(userMakerShopPojo.getUserId());
      if (manufacturer != null) {
        ManufacturerPojo manufacturerPojo = new ManufacturerPojo();
        manufacturerPojo.setId(manufacturer.getId());
        manufacturerPojo.setUserId(userMakerShopPojo.getUserId());
        manufacturerPojo.setCompany(userMakerShopPojo.getShopName());
        String provinceName =
            sysAreaService.getNameById(
                Integer.valueOf(Long.toString(userMakerShopPojo.getProvince()))).getName();
        String cityName =
            sysAreaService.getNameById(Integer.valueOf(Long.toString(userMakerShopPojo.getCity())))
                .getName();
        String areaName =
            sysAreaService.getNameById(Integer.valueOf(Long.toString(userMakerShopPojo.getArea())))
                .getName();
        manufacturerPojo.setAddress(provinceName + cityName + areaName
            + userMakerShopPojo.getDetailedAddress());
        manufacturerPojo.setUpdateDate(now);
        manufacturerService.updateManufacturer(manufacturerPojo);
      }
      // 更新user_maker_shop表
      if (upfile2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerShop")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerShop/", upfile2);
        userMakerShopPojo.setImageOriginal(file_name);
      }
      if (upfile3 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerShop")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerShop/", upfile3);
        userMakerShopPojo.setImageCopy(file_name);
      }
      if (upfile4 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerShop")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerShop/", upfile4);
        userMakerShopPojo.setImageOriginal(file_name);
      }
      if (userMakerShopPojo.getShopTypeName1() != null) {
        userMakerShopPojo.setShopTypeName(userMakerShopPojo.getShopTypeName1());
      }
      if (userMakerShopPojo.getShopTypeName2() != null) {
        userMakerShopPojo.setShopTypeName(userMakerShopPojo.getShopTypeName2());
      }
      userMakerShopService.updateUserMakerShop(userMakerShopPojo);

      FileUtil.alertMessageBySkip("修改成功！", "userMakerShop.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("修改失败！", "goDetailedUserMakerShop.do?userMakerShopPojo.id="
          + userMakerShopPojo.getId());
    }
    return null;
  }


  public int getPanduan() {
    return panduan;
  }

  public void setPanduan(int panduan) {
    this.panduan = panduan;
  }

}
