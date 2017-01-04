package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.ExternalLinksPojo;
import com.tzmb2c.web.pojo.FocusSettingPojo;
import com.tzmb2c.web.pojo.PagePushPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.service.ExternalLinksService;
import com.tzmb2c.web.service.FocusSettingService;
import com.tzmb2c.web.service.PagePushService;
import com.tzmb2c.web.service.SysDictService;

/**
 * 推送综合管理
 * 
 * @author creazylee
 * 
 */
public class ExternalLinksAction extends SuperAction {

  private File upfile;
  private String upfileFileName;
  private String upfileContentType;
  private String[] tids;
  private String type;
  private String result;
  private int item;
  @Autowired
  private PagePushService pagePushService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private FocusSettingService focusSettingService;
  @Autowired
  private ExternalLinksService externalLinksService;
  private PagePushPojo pagePushPojo;
  private List<SysDictPojo> noticeSysDictList = null;
  private List<SysDictPojo> statusSysDictList = null;
  private FocusSettingPojo focusSettingPojo;
  private ExternalLinksPojo externalLinksPojo;

  public FocusSettingPojo getFocusSettingPojo() {
    return focusSettingPojo;
  }

  public void setFocusSettingPojo(FocusSettingPojo focusSettingPojo) {
    this.focusSettingPojo = focusSettingPojo;
  }

  public List<SysDictPojo> getStatusSysDictList() {
    return statusSysDictList;
  }

  public void setStatusSysDictList(List<SysDictPojo> statusSysDictList) {
    this.statusSysDictList = statusSysDictList;
  }

  public List<SysDictPojo> getNoticeSysDictList() {
    return noticeSysDictList;
  }

  public void setNoticeSysDictList(List<SysDictPojo> noticeSysDictList) {
    this.noticeSysDictList = noticeSysDictList;
  }

  public PagePushPojo getPagePushPojo() {
    return pagePushPojo;
  }

  public void setPagePushPojo(PagePushPojo pagePushPojo) {
    this.pagePushPojo = pagePushPojo;
  }

  public PagePushService getPagePushService() {
    return pagePushService;
  }

  public void setPagePushService(PagePushService pagePushService) {
    this.pagePushService = pagePushService;
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

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public String getUpfileContentType() {
    return upfileContentType;
  }

  public void setUpfileContentType(String upfileContentType) {
    this.upfileContentType = upfileContentType;
  }

  public String getUpfileFileName() {
    return upfileFileName;
  }

  public void setUpfileFileName(String upfileFileName) {
    this.upfileFileName = upfileFileName;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public int getItem() {
    return item;
  }

  public void setItem(int item) {
    this.item = item;
  }



  /***
   * 查找所有记录
   * 
   * @return
   */
  public String externalLinksAllList() {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    List<ExternalLinksPojo> externalLinksPojos = externalLinksService.listPage(map);
    JSONArray json = JSONArray.fromObject(externalLinksPojos);
    page.setResult(json.toString());
    return SUCCESS;

  }

  /***
   * 获取所有记录的数目
   * 
   * @return
   * @throws Exception
   */
  public String goExternalLinks() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    page.setRowCount(externalLinksService.countBy(map));
    return SUCCESS;
  }

  /***
   * 跳转到添加页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddExternalLinks() throws Exception {
    return SUCCESS;
  }

  /***
   * 添加单条
   * 
   * @return
   * @throws Throwable
   */
  public String addExternalLinks() throws Throwable {
    try {
      externalLinksPojo.setCreateDate(new Date());
      externalLinksPojo.setUpdateDate(new Date());
      externalLinksService.add(externalLinksPojo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    FileUtil.alertMessageBySkip("添加成功！", "goExternalLinks.do");
    return null;
  }

  /***
   * 删除单条
   * 
   * @return
   * @throws SQLException
   */
  public String delExternalLinks() throws SQLException {
    try {
      externalLinksService.delete(externalLinksPojo.getId());
      result = "1";
    } catch (Exception e) {
      result = "0";
    }
    return SUCCESS;
  }

  /***
   * 删除全部
   * 
   * @return
   */
  public String externalLinksDeleteId() {
    if (tids != null) {
      for (String tid : tids) {
        externalLinksService.delete(Long.parseLong(tid));
      }
    }
    FileUtil.alertMessageBySkip("删除成功！", "goExternalLinks.do");
    return null;
  }

  /***
   * 查找单条
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateExternalLinks() throws Exception {
    externalLinksPojo = externalLinksService.getById(externalLinksPojo.getId());
    return SUCCESS;
  }

  /***
   * 更新单条
   * 
   * @return
   * @throws Throwable
   */
  public String updateExternalLinks() throws Throwable {
    externalLinksPojo.setUpdateDate(new Date());
    externalLinksService.update(externalLinksPojo);
    FileUtil.alertMessageBySkip("修改成功！", "goExternalLinks.do");
    return null;
  }

  public ExternalLinksPojo getExternalLinksPojo() {
    return externalLinksPojo;
  }

  public void setExternalLinksPojo(ExternalLinksPojo externalLinksPojo) {
    this.externalLinksPojo = externalLinksPojo;
  }


}
