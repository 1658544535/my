package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
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
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.InfoPojo;
import com.tzmb2c.web.pojo.PagePushPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserMessageStatePojo;
import com.tzmb2c.web.service.InfoService;
import com.tzmb2c.web.service.PagePushService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.UserMessageStateService;

/**
 * 信息管理系统
 * 
 * @author creazylee
 * 
 */
public class InfoAction extends SuperAction {

  private File upfile;
  private String upfileFileName;
  private String upfileContentType;
  private String[] tids;
  private String type;
  private String result;
  private int item;
  private String marketTitleKeyWord;
  private String tradeTitleKeyWord;
  @Autowired
  private InfoService infoService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private UserMessageStateService userMsgStateService;
  private InfoPojo infoPojo, pageInfoPojo;
  private List<InfoPojo> infoList = null;
  private List<InfoPojo> pageInfoList = null;
  private List<InfoPojo> infoPojos;
  private List<InfoPojo> randInfoList;
  private List<SysDictPojo> infoTypeList = null;
  private List<SysDictPojo> statusSysDictList = null;
  @Autowired
  private PagePushService pagePushService;
  private List<PagePushPojo> pagePushPojos;

  private String tittle;
  private long id;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTittle() {
    return tittle;
  }

  public void setTittle(String tittle) {
    this.tittle = tittle;
  }

  public String getMarketTitleKeyWord() {
    return marketTitleKeyWord;
  }

  public void setMarketTitleKeyWord(String marketTitleKeyWord) {
    this.marketTitleKeyWord = marketTitleKeyWord;
  }

  public String getTradeTitleKeyWord() {
    return tradeTitleKeyWord;
  }

  public void setTradeTitleKeyWord(String tradeTitleKeyWord) {
    this.tradeTitleKeyWord = tradeTitleKeyWord;
  }

  public InfoPojo getPageInfoPojo() {
    return pageInfoPojo;
  }

  public void setPageInfoPojo(InfoPojo pageInfoPojo) {
    this.pageInfoPojo = pageInfoPojo;
  }

  public List<InfoPojo> getPageInfoList() {
    return pageInfoList;
  }

  public void setPageInfoList(List<InfoPojo> pageInfoList) {
    this.pageInfoList = pageInfoList;
  }

  public List<SysDictPojo> getStatusSysDictList() {
    return statusSysDictList;
  }

  public void setStatusSysDictList(List<SysDictPojo> statusSysDictList) {
    this.statusSysDictList = statusSysDictList;
  }

  public List<SysDictPojo> getInfoTypeList() {
    return infoTypeList;
  }

  public void setInfoTypeList(List<SysDictPojo> infoTypeList) {
    this.infoTypeList = infoTypeList;
  }

  public InfoService getInfoService() {
    return infoService;
  }

  public void setInfoService(InfoService infoService) {
    this.infoService = infoService;
  }

  public InfoPojo getInfoPojo() {
    return infoPojo;
  }

  public void setInfoPojo(InfoPojo infoPojo) {
    this.infoPojo = infoPojo;
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

  public String goAddInfo() throws Exception {
    getList();
    ActionContext ac = ActionContext.getContext();
    ac.put("infoPojo", infoPojo);
    return SUCCESS;
  }

  /***
   * 查找数据字典
   */
  private void getList() {
    try {
      infoTypeList = sysDictService.getSysDictListByType("info_type");
      statusSysDictList = sysDictService.getSysDictListByType("status");
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  /***
   * 查找所有资讯记录
   * 
   * @return
   */
  public String infoAllList() {
    getList();
    infoList = infoService.infoAllList(infoPojo, page, null);
    JSONArray json = JSONArray.fromObject(infoList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /***
   * Action入口
   * 
   * @return
   * @throws Exception
   */
  public String getInfoCount() throws Exception {
    getList();
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(infoService.infoAllCount(infoPojo));
    return SUCCESS;
  }

  public String InfoCount1() throws Exception {
    int s = 0;
    Map<String, Object> map = new HashMap<String, Object>();
    SysLoginPojo wuser = UserUtil.getWebUser();
    if (null != wuser) {
      map.put("user_id", wuser.getId());
      map.put("is_reader", "0");
      s = infoService.infoTypeCount(map);
    }
    result = s + "";
    return SUCCESS;
  }

  /***
   * 添加单条
   * 
   * @return
   * @throws Throwable
   */
  public String addInfo() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/info") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/info/", upfile);
        infoPojo.setImage(file_name);
      } else {
        infoPojo.setImage("");
      }
      infoPojo.setCreateDate(new Date());
      infoService.addInfo(infoPojo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    FileUtil.alertMessageBySkip("添加成功！", "infoManage.do?infoPojo.type=" + infoPojo.getType());
    return null;
  }

  /***
   * 删除单条
   * 
   * @return
   * @throws SQLException
   */
  public String delInfo() throws SQLException {
    try {
      infoService.delInfo(infoPojo);
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
  public String delAllInfoById() {
    if (tids != null) {
      infoService.delAllInfoById(tids);
    }
    FileUtil.alertMessageBySkip("删除成功！", "infoManage.do?infoPojo.type=" + infoPojo.getType());
    return null;
  }

  /***
   * 查找单条资讯
   * 
   * @return
   * @throws Exception
   */
  public String goFindInfo() throws Exception {
    getList();
    infoPojo = infoService.findInfoById(infoPojo.getId());
    return SUCCESS;
  }

  /***
   * 更新单条
   * 
   * @return
   * @throws Throwable
   */
  public String updateInfo() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/info") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/info/", upfile);
      infoPojo.setImage(file_name);
    } else {
      infoPojo.setImage("");
    }
    infoPojo.setUpdateDate(new Date());
    infoService.updateInfo(infoPojo);
    FileUtil.alertMessageBySkip("修改成功！", "infoManage.do?infoPojo.type=" + infoPojo.getType());
    return null;
  }

  /***
   * 跳转到审核页面
   * 
   * @return
   * @throws Exception
   */
  public String goFindCheckInfo() throws Exception {
    getList();
    infoPojo = infoService.findInfoById(infoPojo.getId());
    return SUCCESS;
  }

  /***
   * 审核单条
   * 
   * @return
   * @throws SQLException
   */
  public String checkInfo() throws SQLException {
    infoService.verifyInfo(infoPojo);
    FileUtil.alertMessageBySkip("审核成功！", "infoManage.do?infoPojo.type=" + infoPojo.getType());
    return null;
  }

  /**
   * 单条审核 fu
   * 
   * @return
   */
  public String updateinfo() {
    try {
      InfoPojo info = infoService.findInfoById(infoPojo.getId());
      if (null == info.getStatus() || 1 != info.getStatus().intValue()) {
        infoService.verifyInfo(infoPojo);
        userMsgStateService.addMsgToAllUser(infoPojo);
      }
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
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

    try {
      if (tids != null) {
        infoService.checkAllInfoById(tids);
      }
      FileUtil.alertMessageBySkip("审核成功！", "infoManage.do?infoPojo.type=" + infoPojo.getType());
      return null;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * @fu 单条未审核
   */
  public String uncheckinfo() throws SQLException {
    infoService.uncheckinfo(infoPojo);
    return SUCCESS;
  }

  /**
   * @fu 批量未审核
   */
  public String uncheckAllInfoById() {
    try {
      if (tids != null) {
        infoService.uncheckAllInfoById(tids);
      }
      FileUtil.alertMessageBySkip("审核成功！", "infoManage.do?infoPojo.type=" + infoPojo.getType());
      return null;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  // ------前端页面调用------ //
  /***
   * 查找所有资讯记录
   * 
   * @return
   */
  public String infoPageList() throws SQLException {
    // getList();
    // infoList = infoService.infoAllList(infoPojo, page);
    // JSONArray json = JSONArray.fromObject(infoList);
    // page.setResult(json.toString());
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("infoPojo", infoPojo);
    map.put("tradeTitleKeyWord", tradeTitleKeyWord);
    map.put("type", 1);
    map.put("size", 10);
    pageInfoList = infoService.infoPageList(map);

    Map<String, Object> map2 = new HashMap<String, Object>();
    map2.put("infoPojo", infoPojo);
    map2.put("marketTitleKeyWord", marketTitleKeyWord);
    map2.put("type", 2);
    map2.put("size", 10);
    infoList = infoService.infoPageList(map2);
    // 行业资讯广告图//
    ActionContext ac = ActionContext.getContext();
    ac.put("pageInfoList", pageInfoList);
    ac.put("infoList", infoList);
    ac.put("marketTitleKeyWord", marketTitleKeyWord);
    ac.put("tradeTitleKeyWord", tradeTitleKeyWord);

    pagePushPojos = pagePushService.findAdByType(4);
    for (int i = 0; i < pagePushPojos.size(); i++) {
      ac.put("adimages" + i, pagePushPojos.get(i).getImages());
      ac.put("backgroundColor" + i, pagePushPojos.get(i).getBackgroundColor());
    }

    return SUCCESS;
  }

  public String infoDetail() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      map.put("type", 1);
      map.put("size", 5);
      infoPojos = infoService.infoPageList(map);

      Map<String, Object> map2 = new HashMap<String, Object>();
      map2.put("type", 2);
      map2.put("size", 5);
      infoList = infoService.infoPageList(map2);

      randInfoList = infoService.getRandomList();

      Map<String, Object> map3 = new HashMap<String, Object>();
      pageInfoList = infoService.infoPageList(map3);
      infoPojo = infoService.findInfoById(infoPojo.getId());
      // 设置消息状态为已读
      SysLoginPojo userPojo = UserUtil.getWebUser();
      if (null != userPojo) {
        UserMessageStatePojo umsp = new UserMessageStatePojo();
        umsp.setMessageId(infoPojo.getId());
        umsp.setUserId(userPojo.getId());
        String msgState = userMsgStateService.getUserMsgReadState(umsp);
        if (null != msgState && msgState.equals("0")) {
          umsp.setIsReader("1");
          userMsgStateService.updateUserMsgState(umsp);
        }
      }
      String content = infoPojo.getContent();
      String contentss = content.replaceAll("</?[^>]+>", "");
      String contents = contentss.substring(0, contentss.length() > 40 ? 40 : contentss.length());
      for (int i = 0; i < pageInfoList.size(); i++) {
        if (infoPojo.getId() == pageInfoList.get(i).getId()) {
          if (i != 0) {
            infoPojo.setPreId(pageInfoList.get(i - 1).getId());
            infoPojo.setPreTittle(pageInfoList.get(i - 1).getTitle());
          }
          if (i < pageInfoList.size() - 1) {
            infoPojo.setNextId(pageInfoList.get(i + 1).getId());
            infoPojo.setNextTittle(pageInfoList.get(i + 1).getTitle());
          }
        }
      }
      ActionContext ac = ActionContext.getContext();
      ac.put("infoPojos", infoPojos);
      ac.put("infoList", infoList);
      ac.put("infoPojo", infoPojo);
      ac.put("contents", contents);
      ac.put("randInfoList", randInfoList);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
    return SUCCESS;
  }

  public String goInfoList() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(infoService.infoAllCount(infoPojo));
    return SUCCESS;
  }

  public String infoList() throws SQLException {

    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(15);
    if (infoPojo == null) {
      infoPojo = new InfoPojo();
    }
    infoPojo.setTitle(tittle);
    if (type != null) {
      Integer types = Integer.parseInt(type);
      infoPojo.setType(types);
    }
    if (tittle != null && !tittle.equals("") && !tittle.equals("搜索 资讯")) {

      page.setRowCount(infoService.infoAllCount(infoPojo));
      pageInfoList = infoService.infoAllList(infoPojo, page, 1);
    } else {
      page.setRowCount(infoService.infoAllCount(infoPojo));
      pageInfoList = infoService.infoAllList(infoPojo, page, 1);
    }

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("type", 1);
    map.put("size", 5);
    infoPojos = infoService.infoPageList(map);

    Map<String, Object> map2 = new HashMap<String, Object>();
    map2.put("type", 2);
    map2.put("size", 5);
    infoList = infoService.infoPageList(map2);

    // Map<String, Object> map3 = new HashMap<String, Object>();
    // map3.put("tittle",tittle);
    // pageInfoList = infoService.infoPageList(map3);

    ActionContext ac = ActionContext.getContext();
    ac.put("infoPojos", infoPojos);
    ac.put("infoList", infoList);
    ac.put("pageInfoList", pageInfoList);
    ac.put("type", type);
    return SUCCESS;
  }

  /*
   * 前端--系统消息 by wen 2015-01-26 20:54:47
   */
  public String systemInfoWeb() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(15);

    InfoPojo infoPojo = new InfoPojo();
    infoPojo.setType(3);
    infoPojo.setStatus(1);
    page.setRowCount(infoService.infoAllCount(infoPojo));
    List<InfoPojo> infolist = infoService.infoAllList(infoPojo, page, 1);
    userMsgStateService.updateInfoReadState(UserUtil.getWebUser().getId(), infolist);
    ActionContext ac = ActionContext.getContext();
    ac.put("systemInfolist", infolist);
    return SUCCESS;
  }

  /*
   * 页面 by zhu
   */
  public String imformationWeb() throws Exception {
    if (id == 0L) {
      return SUCCESS;
    }
    infoPojo = infoService.findInfoById(id);
    return SUCCESS;
  }
}
