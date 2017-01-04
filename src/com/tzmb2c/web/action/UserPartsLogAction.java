package com.tzmb2c.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.UserPartsLogPojo;
import com.tzmb2c.web.service.UserPartsLogService;

public class UserPartsLogAction extends SuperAction {
  @Autowired
  private UserPartsLogService userPartsLogService;
  private UserPartsLogPojo userPartsLogPojo;
  private List<UserPartsLogPojo> userPartsLogPojoList;

  public List<UserPartsLogPojo> getUserPartsLogPojoList() {
    return userPartsLogPojoList;
  }

  public void setUserPartsLogPojoList(List<UserPartsLogPojo> userPartsLogPojoList) {
    this.userPartsLogPojoList = userPartsLogPojoList;
  }

  public UserPartsLogPojo getUserPartsLogPojo() {
    return userPartsLogPojo;
  }

  public void setUserPartsLogPojo(UserPartsLogPojo userPartsLogPojo) {
    this.userPartsLogPojo = userPartsLogPojo;
  }

  /**
   * 查询全部条数
   */
  public String userPartsLogCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (userPartsLogPojo != null) {
        map.put("loginname", userPartsLogPojo.getLoginname());
        map.put("toyName", userPartsLogPojo.getToyName());
        map.put("partsName", userPartsLogPojo.getPartsName());
        map.put("status", userPartsLogPojo.getStatus());
      }
      int i = userPartsLogService.findUserPartsLogCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String userPartsLogAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (userPartsLogPojo != null) {
        map.put("loginname", userPartsLogPojo.getLoginname());
        map.put("toyName", userPartsLogPojo.getToyName());
        map.put("partsName", userPartsLogPojo.getPartsName());
        map.put("status", userPartsLogPojo.getStatus());
      }
      userPartsLogPojoList = userPartsLogService.findUserPartsLogList(map);
      JSONArray json = JSONArray.fromObject(userPartsLogPojoList);
      page.setRowCount(userPartsLogService.findUserPartsLogCount(map));
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

  public String delUserPartsLog() throws Exception {
    try {
      userPartsLogService.delUserPartsLog(userPartsLogPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "userPartsLog.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "userPartsLog.do");
    }
    return null;
  }

  /**
   * 根据id批量删除
   * 
   * @return
   */
  /*
   * public String delNavigationAll(){ try { for (String id : tids) {
   * navigationService.delNavigation(Long.valueOf(id)); } FileUtil.alertMessageBySkip("全部删除成功！",
   * "navigationList.do"); } catch (Exception e) { e.printStackTrace();
   * FileUtil.alertMessageBySkip("全部删除失败！", "navigationList.do"); } return null; }
   */
  /**
   * 根据id审核
   * 
   * @return
   */
  /*
   * public String checkNavigation() throws Exception { try {
   * navigationService.checkNavigation(navigationPojo.getId()); FileUtil.alertMessageBySkip("审核成功！",
   * "navigationList.do"); } catch (Exception e) { FileUtil.alertMessageBySkip("审核失败！",
   * "navigationList.do"); } return null; }
   */
  /**
   * 根据id取审
   * 
   * @return
   */
  /*
   * public String uncheckNavigation() throws Exception { try {
   * navigationService.uncheckNavigation(navigationPojo.getId());
   * FileUtil.alertMessageBySkip("取审成功！", "navigationList.do"); } catch (Exception e) {
   * FileUtil.alertMessageBySkip("取审失败！", "navigationList.do"); } return null; }
   */
  /**
   * 根据id批量通过审核
   * 
   * @return
   */
  /*
   * public String checkNavigationAll(){ try { for (String id : tids) {
   * navigationService.checkNavigation(Long.valueOf(id)); } FileUtil.alertMessageBySkip("全部通过审核成功！",
   * "navigationList.do"); } catch (Exception e) { e.printStackTrace();
   * FileUtil.alertMessageBySkip("全部通过审核失败！", "navigationList.do"); } return null; }
   */
  /**
   * 跳转编辑页面
   * 
   * @return
   * @throws Exception
   */
  /*
   * public String goUpdateNavigation() throws Exception { try{ ProductTypePojo productTypePojo =
   * new ProductTypePojo(); navigationPojo =
   * navigationService.findNavigationById(navigationPojo.getId()); if(navigationPojo.getCategoryId()
   * == null || navigationPojo.getLevel() == null){ navigationPojo.setLevelAll("无"); }else
   * if(navigationPojo.getCategoryId() != null && navigationPojo.getLevel() != null){
   * if(navigationPojo.getLevel() == 1){
   * productTypePojo.setId(Long.valueOf(navigationPojo.getCategoryId()));
   * if(productTypeService.findProductType(productTypePojo).getName() !=null){ s =
   * productTypeService.findProductType(productTypePojo).getName();
   * navigationPojo.setLevelAll("一级类目"+"  "+s); }else{ navigationPojo.setLevelAll("无"); } }
   * if(navigationPojo.getLevel() == 2){
   * productTypePojo.setId(Long.valueOf(navigationPojo.getCategoryId()));
   * if(productTypeService.findProductType(productTypePojo).getName() !=null){ s =
   * productTypeService.findProductType(productTypePojo).getName();
   * navigationPojo.setLevelAll("二级类目"+"  "+s); }else{ navigationPojo.setLevelAll("无"); } } }else{
   * navigationPojo.setLevelAll("无"); } } catch (Exception e) { e.printStackTrace(); } return
   * SUCCESS; }
   */
  /**
   * 编辑提交
   * 
   * @return
   * @throws Exception
   */
  public String updateUserPartsLog() throws Exception {
    userPartsLogService.updateUserPartsLog(userPartsLogPojo);
    return null;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  /*
   * public String addNavigation() throws Exception { return SUCCESS; }
   */
  /**
   * 新增提交
   * 
   * @return
   * @throws Exception
   */
  public String addUserPartsLog() throws Exception {
    userPartsLogService.insertUserPartsLog(userPartsLogPojo);
    return null;
  }
}
