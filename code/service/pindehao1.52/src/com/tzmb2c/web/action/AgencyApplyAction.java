package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.AgencyApplyPojo;
import com.tzmb2c.web.pojo.ShopPojo;
import com.tzmb2c.web.service.AgencyApplyService;
import com.tzmb2c.web.service.SysAreaService;

public class AgencyApplyAction extends SuperAction {

  @Autowired
  private AgencyApplyService agencyApplyService;

  private AgencyApplyPojo agencyApplyPojo;
  private String result;
  private String[] tids;
  private File upfile;
  private String os;
  private ShopPojo shop;
  @Autowired
  private SysAreaService sysAreaService;

  public AgencyApplyService getAgencyApplyService() {
    return agencyApplyService;
  }

  public void setAgencyApplyService(AgencyApplyService agencyApplyService) {
    this.agencyApplyService = agencyApplyService;
  }

  public SysAreaService getSysAreaService() {
    return sysAreaService;
  }

  public void setSysAreaService(SysAreaService sysAreaService) {
    this.sysAreaService = sysAreaService;
  }

  public AgencyApplyPojo getAgencyApplyPojo() {
    return agencyApplyPojo;
  }

  public void setAgencyApplyPojo(AgencyApplyPojo agencyApplyPojo) {
    this.agencyApplyPojo = agencyApplyPojo;
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

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }

  public String agencyApplyAllList() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (agencyApplyPojo != null) {
      map.put("contact", agencyApplyPojo.getContact().trim());
      map.put("mainProduct", agencyApplyPojo.getMainProduct().trim());
    }
    List<AgencyApplyPojo> list = agencyApplyService.getAgencyApplyList(map);
    JSONArray json = JSONArray.fromObject(list);
    // page.setRowCount(agencyApplyService.getAgencyApplyCount(map));
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String agencyApplyCount() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    // if(agencyApplyPojo!=null){
    // map.put("id", agencyApplyPojo.getId());
    // map.put("contact", agencyApplyPojo.getContact());
    // map.put("tel", agencyApplyPojo.getTel());
    // map.put("phone",agencyApplyPojo.getPhone());
    // map.put("fax",agencyApplyPojo.getFax());
    // map.put("QQ",agencyApplyPojo.getQQ());
    // map.put("province",agencyApplyPojo.getProvince());
    // map.put("provinceId",agencyApplyPojo.getProvinceId());
    // map.put("city",agencyApplyPojo.getCity());
    // map.put("cityId",agencyApplyPojo.getCityId());
    //
    // map.put("area",agencyApplyPojo.getArea());
    // map.put("areaId",agencyApplyPojo.getAreaId());
    // map.put("address",agencyApplyPojo.getAddress());
    // map.put("mainProduct",agencyApplyPojo.getMainProduct());
    // map.put("status",agencyApplyPojo.getStatus());
    // map.put("createBy",agencyApplyPojo.getCreateBy());
    // map.put("createDate",agencyApplyPojo.getCreateDate());
    // map.put("updateBy",agencyApplyPojo.getUpdateBy());
    // map.put("updateDate",agencyApplyPojo.getUpdateDate());
    // }
    int i = agencyApplyService.getAgencyApplyCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  public String checkAgencyApply() throws SQLException {
    try {
      agencyApplyService.checkAgencyApply(agencyApplyPojo.getId());
      // this.result = "1";
      FileUtil.alertMessageBySkip("审核成功！", "agencyApplication.do");
    } catch (Exception e) {
      // this.result = "0";
      FileUtil.alertMessageBySkip("审核失败！", "agencyApplication.do");
    }
    // return SUCCESS;
    return null;
  }

  public String uncheckAgencyApply() throws SQLException {
    try {
      agencyApplyService.uncheckAgencyApply(agencyApplyPojo.getId());
      // this.result = "1";
      FileUtil.alertMessageBySkip("取消成功！", "agencyApplication.do");
    } catch (Exception e) {
      // this.result = "0";
      FileUtil.alertMessageBySkip("取消失败！", "agencyApplication.do");
    }
    // return SUCCESS;
    return null;
  }

  public String delAgencyApply() throws SQLException {

    agencyApplyService.delAgencyApply(agencyApplyPojo.getId());
    FileUtil.alertMessageBySkip("删除成功！", "agencyApplication.do");

    return null;
  }

  /**
   * 删除全部
   * 
   * @return
   * @throws SQLException
   */
  public String AgencyApplyDeleteAll() throws SQLException {
    if (tids != null) {
      for (String tid : tids) {
        agencyApplyService.delAgencyApply(Long.parseLong(tid));
      }
      FileUtil.alertMessageBySkip("删除成功！", "agencyApplication.do");
    } else {
      FileUtil.alertMessageBySkip("删除失败！", "agencyApplication.do");
    }
    return null;
  }

  /**
   * 审核全部
   * 
   * @return
   * @throws SQLException
   * @throws NumberFormatException
   */
  public String AgencyApplyCheckAll() throws SQLException {
    if (tids != null) {
      for (String tid : tids) {
        agencyApplyService.checkAgencyApply(Long.parseLong(tid));
      }
      FileUtil.alertMessageBySkip("审核成功！", "agencyApplication.do");
    } else {
      FileUtil.alertMessageBySkip("审核失败！", "agencyApplication.do");
    }
    return null;
  }

  public String AgencyApplyAdd() throws SQLException {
    return SUCCESS;
  }

  public String AgencyApplyAddOk() throws SQLException {
    try {
      agencyApplyService.insertAgencyApply(agencyApplyPojo);
      FileUtil.alertMessageBySkip("新增成功！", "agencyApplication.do");
    } catch (Exception e) {
      // TODO: handle exception
      FileUtil.alertMessageBySkip("新增失败！", "agencyApplication.do");
    }
    return null;
  }

  public String validAgencyApplyAdd() throws SQLException {
    try {
      // 根据手机号码判断是否注册过
      if (agencyApplyPojo != null) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contact", agencyApplyPojo.getContact());
        int count = agencyApplyService.getAgencyApplyCount(map);
        if (count != 0) {
          this.result = "0";
        } else {
          this.result = "1";
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  public ShopPojo getShop() {
    return shop;
  }

  public void setShop(ShopPojo shop) {
    this.shop = shop;
  }

  public String updateAgencyApply() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (agencyApplyPojo != null) {
      map.put("id", agencyApplyPojo.getId());
      agencyApplyPojo = agencyApplyService.getAgencyApplyById(map);
    }
    return SUCCESS;
  }

  public String updateAgencyApplyOk() throws SQLException {
    try {
      agencyApplyService.updateAgencyApply(agencyApplyPojo);
      FileUtil.alertMessageBySkip("修改成功！", "agencyApplication.do");
    } catch (Exception e) {
      // TODO: handle exception
      FileUtil.alertMessageBySkip("修改失败！", "agencyApplication.do");
    }
    return null;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  private int type;
}
