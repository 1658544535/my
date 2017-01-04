package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.PagePushPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.service.PagePushService;
import com.tzmb2c.web.service.SysDictService;

/**
 * 推送综合管理
 * 
 * @author creazylee
 * 
 */
public class PagePushAction extends SuperAction {

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
  private PagePushPojo pagePushPojo;
  private List<PagePushPojo> pushNoticeList = null;
  private List<SysDictPojo> noticeSysDictList = null;
  private List<SysDictPojo> statusSysDictList = null;

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

  public String queryNotice() throws Exception {
    ActionContext ac = ActionContext.getContext();
    try {
      ac.put("noticeList", pagePushService.queryNotice(pagePushPojo));
      ac.put("noticePojo", pagePushPojo);
    } catch (SQLException e) {

      e.printStackTrace();
    }
    return SUCCESS;
  }

  /***
   * 查找数据字典
   */
  private void getList() {
    try {
      noticeSysDictList = sysDictService.getSysDictListByType("ad_type");
      statusSysDictList = sysDictService.getSysDictListByType("status");
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  /***
   * 查找所有记录
   * 
   * @return
   */
  public String pushNoticeAllList() {
    getList();
    pushNoticeList = pagePushService.pushNoticeAllList(pagePushPojo, page, type);
    JSONArray json = JSONArray.fromObject(pushNoticeList);
    page.setResult(json.toString());
    return SUCCESS;

  }

  /***
   * 获取所有记录的数目
   * 
   * @return
   * @throws Exception
   */
  public String getPushNoticeCount() throws Exception {
    getList();
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(pagePushService.pushNoticeAllCount(pagePushPojo, type));
    return SUCCESS;
  }

  /***
   * 跳转到添加页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddnotice() throws Exception {
    getList();
    return SUCCESS;
  }

  /***
   * 添加单条
   * 
   * @return
   * @throws Throwable
   */
  public String addPushNotice() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/notice")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/notice/", upfile);
        pagePushPojo.setImages(file_name);
      } else {
        pagePushPojo.setImages("");
      }
      pagePushPojo.setVersion(0);
      pagePushPojo.setCreateDate(new Date());
      pagePushPojo.setUpdateDate(new Date());
      // pagePushPojo.setCreateBy(3109005889L);
      pagePushService.addPushNotice(pagePushPojo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    FileUtil.alertMessageBySkip("添加成功！", "pushNoticeManage.do");
    return null;
  }

  /***
   * 删除单条
   * 
   * @return
   * @throws SQLException
   */
  public String delPushNotice() throws SQLException {
    try {
      pagePushService.delNotice(pagePushPojo);
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  /***
   * 删除全部
   * 
   * @return
   */
  public String delAllPushNoticeById() {
    pagePushService.delAllNoticeById(tids);
    FileUtil.alertMessageBySkip("删除成功！", "pushNoticeManage.do");
    return null;
  }

  /***
   * 查找单条
   * 
   * @return
   * @throws Exception
   */
  public String goFindNotice() throws Exception {
    getList();
    pagePushPojo = pagePushService.findNoticeById(pagePushPojo.getId());
    return SUCCESS;
  }

  /***
   * 更新单条
   * 
   * @return
   * @throws Throwable
   */
  public String updateNotice() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/notice") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/notice/", upfile);
      pagePushPojo.setImages(file_name);
    }
    // pagePushPojo.setCreateBy(3109005889L);
    if (pagePushPojo.getType() == null) {
      pagePushPojo.setType(0);
    }
    pagePushPojo.setUpdateDate(new Date());
    pagePushService.updatePushNotice(pagePushPojo);
    FileUtil.alertMessageBySkip("修改成功！", "pushNoticeManage.do");
    return null;
  }

  /***
   * 审核单条
   * 
   * @return
   * @throws SQLException
   */
  public String verifyPushNotice() throws SQLException {
    try {
      pagePushService.verifyNotice(pagePushPojo);
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  /***
   * 批量审核
   * 
   * @return
   */
  public String verifyAllPushNotice() {
    pagePushService.checkAllNoticeById(tids);
    FileUtil.alertMessageBySkip("审核成功！", "pushNoticeManage.do");
    return null;
  }

}
