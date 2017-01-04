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

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.PopupPojo;
import com.tzmb2c.web.service.PopupService;

public class PopupAction extends SuperAction {
  @Autowired
  private PopupService popupService;
  private PopupPojo popupPojo;
  private List<PopupPojo> popupPojoList;
  private File upfile;

  public List<PopupPojo> getPopupList() {
    return popupPojoList;
  }

  public void setPopupList(List<PopupPojo> popupPojoList) {
    this.popupPojoList = popupPojoList;
  }

  public PopupPojo getPopupPojo() {
    return popupPojo;
  }

  public void setPopupPojo(PopupPojo popupPojo) {
    this.popupPojo = popupPojo;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  /**
   * 查询全部条数
   */
  public String popupListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (popupPojo != null) {
        map.put("startTimeStr", popupPojo.getStartTimeStr());
        map.put("endTimeStr", popupPojo.getEndTimeStr());
        map.put("parameterSize", popupPojo.getParameterSize());
        map.put("popupSize", popupPojo.getPopupSize());
        map.put("status", popupPojo.getStatus());
      }
      int i = popupService.findPopupCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String popupListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (popupPojo != null) {
        map.put("startTimeStr", popupPojo.getStartTimeStr());
        map.put("endTimeStr", popupPojo.getEndTimeStr());
        map.put("parameterSize", popupPojo.getParameterSize());
        map.put("popupSize", popupPojo.getPopupSize());
        map.put("status", popupPojo.getStatus());
      }
      popupPojoList = popupService.findPopupList(map);
      JSONArray json = JSONArray.fromObject(popupPojoList);
      page.setRowCount(popupPojoList.size());
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
  public String delPopup() throws Exception {
    try {
      popupService.delPopup(popupPojo);
      FileUtil.alertMessageBySkip("删除成功！", "popupList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "popupList.do");
    }
    return null;
  }

  /**
   * 根据id审核
   * 
   * @return
   */
  public String checkPopup() throws Exception {
    try {
      popupService.checkPopup(popupPojo.getId());
      FileUtil.alertMessageBySkip("审核成功！", "popupList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("审核失败！", "popupList.do");
    }
    return null;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheckPopup() throws Exception {
    try {
      popupService.uncheckPopup(popupPojo.getId());
      FileUtil.alertMessageBySkip("取审成功！", "popupList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("取审失败！", "popupList.do");
    }
    return null;
  }

  /**
   * 跳转编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdatePopup() throws Exception {
    popupPojo = popupService.findPopupById(popupPojo.getId());
    return SUCCESS;
  }

  /**
   * 编辑提交
   * 
   * @return
   * @throws Throwable
   */
  public String updatePopup() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/popup") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/popup/", upfile);
        popupPojo.setPopupPic(file_name);
      } else {
        popupPojo.setPopupPic("");
      }
      popupService.updatePopup(popupPojo);
      FileUtil.alertMessageBySkip("提交成功！", "popupList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "popupList.do");
    }
    return null;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String addPopup() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Throwable
   */
  public String addPopupOK() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/popup") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/popup/", upfile);
        popupPojo.setPopupPic(file_name);
      } else {
        popupPojo.setPopupPic("");
      }
      popupPojo.setCreateDate(new Date());
      popupService.insertPopup(popupPojo);
      FileUtil.alertMessageBySkip("新增成功！", "popupList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "popupList.do");
    }
    return null;
  }
}
