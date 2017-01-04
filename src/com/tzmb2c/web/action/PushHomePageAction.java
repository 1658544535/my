package com.tzmb2c.web.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.PushHomePagePojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.PushHomePageService;
import com.tzmb2c.web.service.SysDictService;

/**
 * @author Administrator
 * 
 */
/**
 * @author Administrator
 * 
 */
public class PushHomePageAction extends SuperAction {
  @Autowired
  private PushHomePageService pushHomePageService;
  @Autowired
  private SysDictService sysDictService;

  private PushHomePagePojo pushHomePagePojo;
  private File upfile, upfile2;
  private String result;
  private String type;
  private List<SysDictPojo> statusSysDictList = null;
  private String[] tids;

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public List<SysDictPojo> getStatusSysDictList() {
    return statusSysDictList;
  }

  public void setStatusSysDictList(List<SysDictPojo> statusSysDictList) {
    this.statusSysDictList = statusSysDictList;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public PushHomePagePojo getPushHomePagePojo() {
    return pushHomePagePojo;
  }

  public void setPushHomePagePojo(PushHomePagePojo pushHomePagePojo) {
    this.pushHomePagePojo = pushHomePagePojo;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String pushHomePageAdd() {
    getList();
    return SUCCESS;
  }

  public String pushHomePageList() throws SQLException, DocumentException {
    getList();
    pushHomePageListCount();
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("type", type);
    if (pushHomePagePojo != null) {
      map.put("title", pushHomePagePojo.getTitle());
      map.put("status", pushHomePagePojo.getStatus());
    }
    List<PushHomePagePojo> list = pushHomePageService.findAll(map);
    for (PushHomePagePojo p : list) {
      p.setImage(p.getImage());
    }
    JSONArray json = JSONArray.fromObject(list);
    // int i = pushHomePageService.findAllCount(map);
    // page.setRowCount(i);
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String pushHomePageListCount() throws SQLException {
    // getList();
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("type", type);
    if (pushHomePagePojo != null) {
      map.put("title", pushHomePagePojo.getTitle());
      map.put("status", pushHomePagePojo.getStatus());
    }
    int i = pushHomePageService.findAllCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  public String uploadFile(File file, String s) throws Throwable {
    try {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/" + s) + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/" + s, file);
      return file_name;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return "";
  }

  public String addPushHomePage() throws Throwable {
    SysLoginPojo sysLoginPojo = findSessionUser();
    if (upfile2 != null) {
      String image = uploadFile(upfile2, "pushhomepg");
      pushHomePagePojo.setImage(image);
    }
    pushHomePagePojo.setCreateBy(sysLoginPojo.getId());
    pushHomePageService.insertPushHomePage(pushHomePagePojo);
    FileUtil
        .alertMessageBySkip("提交成功！", "pushHomePageManage.do?type=" + pushHomePagePojo.getType());
    return null;
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
   * 删除单条
   * 
   * @return
   * @throws SQLException
   */
  public String delPushHomePage() throws SQLException {
    try {
      pushHomePageService.delPushHomePage(pushHomePagePojo);
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  /***
   * 查找单条
   * 
   * @return
   * @throws Exception
   */
  public String goFindPushHomePage() throws Exception {
    getList();
    pushHomePagePojo = pushHomePageService.findPushHomePageById(pushHomePagePojo.getId());
    return SUCCESS;
  }

  /***
   * 更新单条
   * 
   * @return
   * @throws Throwable
   */
  public String updatePushHomePage() throws Throwable {

    if (upfile2 != null) {
      String file_name = uploadFile(upfile2, "pushhomepg");
      pushHomePagePojo.setImage(file_name);
    }
    pushHomePageService.updatePushHomePage(pushHomePagePojo);
    FileUtil
        .alertMessageBySkip("修改成功！", "pushHomePageManage.do?type=" + pushHomePagePojo.getType());
    return null;
  }

  /**
   * @return 审核通过
   * @throws SQLException
   */
  public String checkPushHomePage() throws SQLException {
    try {
      pushHomePageService.checkPushHomePage(pushHomePagePojo.getId());
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  /**
   * @return 取消审核
   * @throws SQLException
   */
  public String uncheckPushHomePage() throws SQLException {
    try {
      pushHomePageService.uncheckPushHomePage(pushHomePagePojo.getId());
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  /**
   * 删除全部
   * 
   * @return
   * @throws SQLException
   */
  public String PushHomePageDeleteId() throws SQLException {
    StringBuffer url = new StringBuffer("pushHomePageManage.do?&type=" + this.getType());
    if (pushHomePagePojo != null && pushHomePagePojo.getId() != null) {
      url.append("&pushHomePagePojo.id=" + pushHomePagePojo.getId());
    }
    if (tids != null) {
      pushHomePageService.pushHomePageDeleteId(tids);
      FileUtil.alertMessageBySkip("删除成功！", url.toString());
    } else {
      FileUtil.alertMessageBySkip("删除失败！", url.toString());
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
  public String PushHomePagecheckAll() throws NumberFormatException, SQLException {
    StringBuffer url = new StringBuffer("pushHomePageManage.do?&type=" + this.getType());
    if (pushHomePagePojo != null && pushHomePagePojo.getId() != null) {
      url.append("&pushHomePagePojo.id=" + pushHomePagePojo.getId());
    }
    if (tids != null) {
      for (String tid : tids) {
        pushHomePageService.checkPushHomePage(Long.parseLong(tid));
      }
      FileUtil.alertMessageBySkip("审核成功！", url.toString());
    } else {
      FileUtil.alertMessageBySkip("审核失败！", url.toString());
    }



    return null;
  }
}
