package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.MD5Util;
import com.tzmb2c.web.pojo.FeedBackPojo;
import com.tzmb2c.web.pojo.HelpPojo;
import com.tzmb2c.web.pojo.HelpTypePojo;
import com.tzmb2c.web.pojo.PagePushPojo;
import com.tzmb2c.web.pojo.RetrievePojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserVerifyPojo;
import com.tzmb2c.web.pojo.VerificationPojo;
import com.tzmb2c.web.service.FeedbackService;
import com.tzmb2c.web.service.HelpService;
import com.tzmb2c.web.service.HelpTypeService;
import com.tzmb2c.web.service.PagePushService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserVerifyService;
import com.tzmb2c.web.service.VerificationService;

/**
 * 2014-12-06
 * 
 * @author creazylee 帮助/FAQ系统
 */
public class HelpAction extends SuperAction {

  @Autowired
  private HelpService helpService;
  @Autowired
  private HelpTypeService helpTypeService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private UserVerifyService userVerifyService;

  private Long pid;

  private HelpPojo helpPojo;
  private FeedBackPojo feedBackPojo;
  private VerificationPojo verificationPojo;
  private SysDictPojo sysDictPojo;
  private List<HelpPojo> helpList = null;
  private List<VerificationPojo> verification = null;
  private List<FeedBackPojo> feedBack = null;
  private List<HelpPojo> helpPageList = null;
  private List<SysDictPojo> helpTypeList = null;
  private String type;
  private String[] tids;
  private String typeStr;
  private String searchkey;
  private List<SysDictPojo> statusSysDictList = null;
  private List<SysDictPojo> yesornotSysDictList = null;
  private List<HelpTypePojo> helpTypePojoList = null;
  private List<HelpPojo> helpchildList = null;
  private List<HelpPojo> helpHotList = null;
  private List<HelpPojo> helpUsualList = null;
  private List<HelpTypePojo> helpTypeChildPojoList = null;
  private Long typeid, ptypeid;
  private RetrievePojo retrievePojo;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private PagePushService pagePushService;
  private List<PagePushPojo> pagePushPojos;
  @Autowired
  private VerificationService verificationService;
  @Autowired
  private FeedbackService feedbackService;
  private int uid;
  private String email, telephone, content;
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getUid() {
    return uid;
  }

  public void setUid(int uid) {
    this.uid = uid;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<FeedBackPojo> getFeedBack() {
    return feedBack;
  }

  public void setFeedBack(List<FeedBackPojo> feedBack) {
    this.feedBack = feedBack;
  }

  public FeedBackPojo getFeedBackPojo() {
    return feedBackPojo;
  }

  public void setFeedBackPojo(FeedBackPojo feedBackPojo) {
    this.feedBackPojo = feedBackPojo;
  }

  public List<VerificationPojo> getVerification() {
    return verification;
  }

  public void setVerification(List<VerificationPojo> verification) {
    this.verification = verification;
  }

  public VerificationPojo getVerificationPojo() {
    return verificationPojo;
  }

  public void setVerificationPojo(VerificationPojo verificationPojo) {
    this.verificationPojo = verificationPojo;
  }

  public String getSearchkey() {
    return searchkey;
  }

  public void setSearchkey(String searchkey) {
    this.searchkey = searchkey;
  }

  public RetrievePojo getRetrievePojo() {
    return retrievePojo;
  }

  public void setRetrievePojo(RetrievePojo retrievePojo) {
    this.retrievePojo = retrievePojo;
  }

  public Long getTypeid() {
    return typeid;
  }

  public void setTypeid(Long typeid) {
    this.typeid = typeid;
  }

  public Long getPtypeid() {
    return ptypeid;
  }

  public void setPtypeid(Long ptypeid) {
    this.ptypeid = ptypeid;
  }

  public List<HelpTypePojo> getHelpTypeChildPojoList() {
    return helpTypeChildPojoList;
  }

  public void setHelpTypeChildPojoList(List<HelpTypePojo> helpTypeChildPojoList) {
    this.helpTypeChildPojoList = helpTypeChildPojoList;
  }

  public List<HelpPojo> getHelpHotList() {
    return helpHotList;
  }

  public void setHelpHotList(List<HelpPojo> helpHotList) {
    this.helpHotList = helpHotList;
  }

  public List<HelpPojo> getHelpUsualList() {
    return helpUsualList;
  }

  public void setHelpUsualList(List<HelpPojo> helpUsualList) {
    this.helpUsualList = helpUsualList;
  }

  public List<HelpPojo> getHelpchildList() {
    return helpchildList;
  }

  public void setHelpchildList(List<HelpPojo> helpchildList) {
    this.helpchildList = helpchildList;
  }

  public Long getPid() {
    return pid;
  }

  public void setPid(Long pid) {
    this.pid = pid;
  }

  public List<HelpTypePojo> getHelpTypePojoList() {
    return helpTypePojoList;
  }

  public void setHelpTypePojoList(List<HelpTypePojo> helpTypePojoList) {
    this.helpTypePojoList = helpTypePojoList;
  }

  public List<SysDictPojo> getYesornotSysDictList() {
    return yesornotSysDictList;
  }

  public void setYesornotSysDictList(List<SysDictPojo> yesornotSysDictList) {
    this.yesornotSysDictList = yesornotSysDictList;
  }

  public List<SysDictPojo> getStatusSysDictList() {
    return statusSysDictList;
  }

  public void setStatusSysDictList(List<SysDictPojo> statusSysDictList) {
    this.statusSysDictList = statusSysDictList;
  }

  public String getTypeStr() {
    return typeStr;
  }

  public void setTypeStr(String typeStr) {
    this.typeStr = typeStr;
  }

  public List<HelpPojo> getHelpPageList() {
    return helpPageList;
  }

  public void setHelpPageList(List<HelpPojo> helpPageList) {
    this.helpPageList = helpPageList;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public HelpPojo getHelpPojo() {
    return helpPojo;
  }

  public void setHelpPojo(HelpPojo helpPojo) {
    this.helpPojo = helpPojo;
  }

  public SysDictPojo getSysDictPojo() {
    return sysDictPojo;
  }

  public void setSysDictPojo(SysDictPojo sysDictPojo) {
    this.sysDictPojo = sysDictPojo;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setHelpList(List<HelpPojo> helpList) {
    this.helpList = helpList;
  }

  public List<HelpPojo> getHelpList() {
    return helpList;
  }

  public List<SysDictPojo> getHelpTypeList() {
    return helpTypeList;
  }

  public void setHelpTypeList(List<SysDictPojo> helpTypeList) {
    this.helpTypeList = helpTypeList;
  }

  private void getList() {
    try {
      helpTypeList = sysDictService.getSysDictListByType("help_type");
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  public String helpAllList() {
    // getList();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("helpPojo", helpPojo);
    map.put("type", type);
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    helpList = helpService.helpAllList(map);
    JSONArray json = JSONArray.fromObject(helpList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String helpAllListCount() {
    // getList();
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("helpPojo", helpPojo);
    map.put("type", type);
    page.setRowCount(helpService.helpAllListCount(map));
    return SUCCESS;
  }

  /***
   * 跳转添加帮助信息页面
   * 
   * @return
   */
  public String goHelpAdd() {
    // getList();
    try {
      getHelpType(0l);
    } catch (Exception e) {

      e.printStackTrace();
    }// 查找帮助信息的所有分类
    return SUCCESS;
  }

  /***
   * 添加单条帮助信息
   * 
   * @return
   */
  public String helpAdd() throws SQLException {

    // long createBy = 8L;
    Date createDate = new Date();
    // helpPojo.setCreateBy(createBy);
    // helpPojo.setStatus(0);
    helpPojo.setCreateDate(createDate);
    helpService.helpAdd(helpPojo);
    FileUtil.alertMessageBySkip("添加成功！", "helpManage.do");
    return null;
  }

  /***
   * 跳转更新帮助信息页面
   * 
   * @return
   */
  public String goHelpUpdate() {
    // getList();
    helpPojo = helpService.goHelpUpdate(helpPojo);
    try {
      getHelpType(0l);
    } catch (Exception e) {

      e.printStackTrace();
    }// 查找帮助信息的所有分类
    return SUCCESS;
  }

  /***
   * 更新单条帮助信息
   * 
   * @return
   */
  public String helpUpdate() throws SQLException {
    helpService.helpUpdate(helpPojo);
    FileUtil.alertMessageBySkip("更新成功！", "helpManage.do");
    return null;
  }

  /***
   * 删除单条帮助信息
   * 
   * @return
   */
  public String helpDelete() throws SQLException {
    helpService.helpDelete(helpPojo);
    FileUtil.alertMessageBySkip("删除成功！", "helpManage.do");
    return null;
  }

  /***
   * 查询要审核的单条帮助信息
   * 
   * @return
   * @throws Exception
   */
  public String goCheckHelpInfo() {
    // getList();
    helpPojo = helpService.goHelpUpdate(helpPojo);
    return SUCCESS;
  }

  /***
   * 审核单条帮助信息
   * 
   * @return
   * @throws SQLException
   */
  public String checkHelpInfo() {
    try {
      helpService.checkHelpInfo(helpPojo);
    } catch (SQLException e) {

      e.printStackTrace();
    }
    FileUtil.alertMessageBySkip("审核成功！", "helpManage.do");
    return null;
  }

  /**
   * 批量审核帮助信息
   * 
   * @return
   */
  public String checkAllHelpInfo() {
    helpService.checkAllHelpInfoById(tids);
    FileUtil.alertMessageBySkip("审核成功！", "helpManage.do");
    return null;
  }

  /***
   * 查找所有的帮助信息类型分类
   * 
   * @param pid
   * @throws Exception
   */
  public void getHelpType(Long pid) throws Exception {
    HelpTypePojo fatherType = new HelpTypePojo();
    fatherType.setId(pid);
    fatherType = helpTypeService.findHelpType(fatherType);
    List<HelpTypePojo> childList = helpTypeService.getHelpTypeByPid(pid);
    statusSysDictList = sysDictService.getSysDictListByType("status");
    yesornotSysDictList = sysDictService.getSysDictListByType("yes_no");
    String typeTemp = fatherType == null ? "" : fatherType.getName() + "--";
    if (!childList.isEmpty()) {
      for (HelpTypePojo child : childList) {
        typeStr = typeStr + "<option value='" + child.getId() + "' ";
        if (helpPojo != null && helpPojo.getTypeId() != null
            && helpPojo.getTypeId() == child.getId()) {
          typeStr = typeStr + " selected='selected'";
        }
        typeStr = typeStr + ">" + typeTemp + child.getName() + "</option>";
        getHelpType(child.getId());
      }
    }
  }

  public String goHelpDetailed() {
    helpPojo = helpService.goHelpUpdate(helpPojo);
    return SUCCESS;
  }

  // ------前端页面调用------ //
  /***
   * 查找所有帮助记录信息
   * 
   * @return
   */
  public String helpPageList() {
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      helpPageList = helpService.helpPageList(map);
    } catch (SQLException e) {

      e.printStackTrace();
    }
    return SUCCESS;
  }

  public String goHelpWeb() throws SQLException {
    // 帮助中心广告图//
    ActionContext ac = ActionContext.getContext();
    pagePushPojos = pagePushService.findAdByType(5);
    // for (int i = 0; i < pagePushPojos.size(); i++) {
    ac.put("pagePushPojos", pagePushPojos);
    // }
    return SUCCESS;
  }

  /**
   * 自主服务
   * 
   * @return fu
   * @throws SQLException
   */
  public String goSelfServiceWeb() throws SQLException {


    helpTypePojoList = helpTypeService.getHelpTypeByPid(78L);
    if (pid == null) {
      helpTypeChildPojoList = helpTypeService.getHelpTypeByPid(helpTypePojoList.get(0).getId());
    } else {
      helpTypeChildPojoList = helpTypeService.getHelpTypeByPid(pid);
    }

    for (HelpTypePojo helpTypePojo : helpTypeChildPojoList) {
      helpchildList = helpService.getListByTypeid(helpTypePojo.getId());
      helpTypePojo.setHelpPojoList(helpchildList);
    }

    ActionContext ac = ActionContext.getContext();
    ac.put("helpTypePojoList", helpTypePojoList);
    ac.put("helpTypeChildPojoList", helpTypeChildPojoList);
    return SUCCESS;
  }

  /**
   * 常见问题
   * 
   * @return
   * @throws SQLException
   */
  public String goFaqWeb() throws SQLException {
    helpHotList = helpService.getHotList();
    helpUsualList = helpService.getUsualList();
    helpTypePojoList = helpTypeService.getHelpTypeByPid(2L);
    for (HelpTypePojo helpTypePojo : helpTypePojoList) {
      helpTypeChildPojoList = helpTypeService.getHelpTypeByPid(helpTypePojo.getId());
      helpTypePojo.setHelpTypeChildPojoList(helpTypeChildPojoList);

    }
    if (typeid == null) {
      helpchildList =
          helpService.getListByTypeid(helpTypePojoList.get(0).getHelpTypeChildPojoList().get(0)
              .getId());
    } else {
      helpchildList = helpService.getListByTypeid(typeid);
    }
    ActionContext ac = ActionContext.getContext();
    ac.put("helpHotList", helpHotList);
    ac.put("helpUsualList", helpUsualList);
    ac.put("helpTypePojoList", helpTypePojoList);
    ac.put("helpchildList", helpchildList);
    return SUCCESS;
  }

  public String goFaqTypeWeb() throws SQLException {
    helpTypePojoList = helpTypeService.getHelpTypeByPid(2L);
    for (HelpTypePojo helpTypePojo : helpTypePojoList) {
      helpTypeChildPojoList = helpTypeService.getHelpTypeByPid(helpTypePojo.getId());
      helpTypePojo.setHelpTypeChildPojoList(helpTypeChildPojoList);
      helpchildList = helpService.getListByTypeid(helpTypePojo.getId());
      helpTypePojo.setHelpPojoList(helpchildList);
    }

    return SUCCESS;
  }

  /**
   * 意见反馈
   * 
   * @fu
   */
  public String goFeedBackWeb() throws SQLException {
    return SUCCESS;
  }

  /**
   * 帮助详情 fu
   */
  public String helpDetail() throws SQLException {
    if (helpPojo.getHelpType() != null) {
      helpPojo = helpService.goHelpUpdate(helpPojo);

      return "goself";
    } else {
      helpPojo = helpService.goHelpUpdate(helpPojo);
      helpTypePojoList = helpTypeService.getHelpTypeByPid(2L);
      helpHotList = helpService.getHotList();
      helpUsualList = helpService.getUsualList();
      for (HelpTypePojo helpTypePojo : helpTypePojoList) {
        helpTypeChildPojoList = helpTypeService.getHelpTypeByPid(helpTypePojo.getId());
        helpTypePojo.setHelpTypeChildPojoList(helpTypeChildPojoList);
        ActionContext ac = ActionContext.getContext();
        ac.put("helpTypePojoList", helpTypePojoList);
        ac.put("helpPojo", helpPojo);
        ac.put("helpHotList", helpHotList);
        ac.put("helpUsualList", helpUsualList);
      }
      return "gofaq";
    }

  }

  /**
   * 找回密码 fu
   */
  public String goRetrieve() throws SQLException {

    return SUCCESS;
  }

  public String doRetrieve() throws SQLException {

    SysLoginPojo loginPojo = new SysLoginPojo();
    loginPojo = sysLoginService.getSysLoginByLoginName(retrievePojo.getLoginname());
    UserVerifyPojo userVerify = new UserVerifyPojo();
    userVerify.setLoginname(retrievePojo.getLoginname());
    userVerify = userVerifyService.findNewestByPhone(userVerify);
    if (loginPojo == null) {
      FileUtil.alertMessageBySkip("你的手机号尚未注册！", "goRetrieve.do");
    } else if (!retrievePojo.getAuthcode().equals(userVerify.getCaptcha())) {
      FileUtil.alertMessageBySkip("验证码输入错误，请重新输入！", "goRetrieve.do");
    } else if (!retrievePojo.getNewpass().equals(retrievePojo.getNewpassaffirm())) {
      FileUtil.alertMessageBySkip("请输入一致的新密码！", "goRetrieve.do");
    } else {
      loginPojo.setPassword(MD5Util.MD5(retrievePojo.getNewpass()));
      sysLoginService.updatePassword(loginPojo);
      FileUtil.alertMessageBySkip("修改密码成功！", "doLoginWeb.do");
    }
    return null;
  }

  /**
   * 问题搜索 fu
   */
  public String goSearch() throws SQLException {
    helpList = helpService.helpSearch(searchkey);
    helpHotList = helpService.getHotList();
    helpUsualList = helpService.getUsualList();
    helpTypePojoList = helpTypeService.getHelpTypeByPid(2L);
    for (HelpTypePojo helpTypePojo : helpTypePojoList) {
      helpTypeChildPojoList = helpTypeService.getHelpTypeByPid(helpTypePojo.getId());
      helpTypePojo.setHelpTypeChildPojoList(helpTypeChildPojoList);

    }
    ActionContext ac = ActionContext.getContext();
    ac.put("helpHotList", helpHotList);
    ac.put("helpUsualList", helpUsualList);
    ac.put("helpTypePojoList", helpTypePojoList);
    ac.put("helpList", helpList);
    ac.put("searchkey", searchkey);

    return SUCCESS;
  }

  /*
   * 关于我们
   */
  public String goaboutus() {
    helpPojo = helpService.goHelpUpdate(helpPojo);
    return SUCCESS;
  }

  /*
   * 翻动城介绍
   */
  public String gofdcWeb() {
    return SUCCESS;
  }

  /*
   * 见客app介绍
   */
  public String gojkapp() {
    return SUCCESS;
  }

  /*
   * 梁山电商学院
   */
  public String godsxy() {
    return SUCCESS;
  }

  /*
   * 企客端
   */
  public String goqkd() {
    return SUCCESS;
  }

  /*
   * 玩具总汇期刊
   */
  public String gowjzh() {
    return SUCCESS;
  }

  /*
   * 微信公众号
   */
  public String gowxgzh() {
    return SUCCESS;
  }

  /*
   * 舌客
   */
  public String goshike() {
    return SUCCESS;
  }

  /*
   * 淘竹马app
   */
  public String gotzmapp() {
    return SUCCESS;
  }

  /*
   * 淘竹马微商城
   */
  public String gotzmwsc() {
    return SUCCESS;
  }

  /*
   * 淘竹马分销平台
   */
  public String gotzmfxpt() {
    return SUCCESS;
  }

  /*
   * 网站导航
   */
  public String gowebguide() {
    return SUCCESS;
  }


  // 联系客服
  public String goContact() throws SQLException {
    helpTypePojoList = helpTypeService.getHelpTypeByPid(2L);
    for (HelpTypePojo helpTypePojo : helpTypePojoList) {
      helpTypeChildPojoList = helpTypeService.getHelpTypeByPid(helpTypePojo.getId());
      helpTypePojo.setHelpTypeChildPojoList(helpTypeChildPojoList);

    }
    helpchildList = helpService.getListByTypeid(typeid);
    ActionContext ac = ActionContext.getContext();
    ac.put("helpTypePojoList", helpTypePojoList);
    ac.put("helpchildList", helpchildList);
    return SUCCESS;
  }

  /**
   * 后台验证码查看
   * 
   * @return
   * @throws SQLException
   */
  public String verification() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("verificationPojo", verificationPojo);
    map.put("type", type);
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    verification = verificationService.VerificationAll(map);
    JSONArray json = JSONArray.fromObject(verification);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 后台验证码查看分页
   * 
   * @return
   */
  public String verificationCount() {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("verificationPojo", verificationPojo);
    map.put("type", type);
    page.setRowCount(verificationService.VerificationAllCount(map));
    return SUCCESS;
  }

  /**
   * 后台意见反馈查看
   * 
   * @return
   * @throws SQLException
   */
  public String feedBackAll() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("feedBackPojo", feedBackPojo);
    if (page != null) {
      map.put("pageSize", page.getPageSize());
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    }
    feedBack = feedbackService.getFeedBackAll(map);
    JSONArray json = JSONArray.fromObject(feedBack);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 后台意见反馈分页
   * 
   * @return
   */
  public String feedBackCount() {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("feedBackPojo", feedBackPojo);
    page.setRowCount(feedbackService.FeedBackListCount(map));
    return SUCCESS;
  }

  /**
   * 后台意见反馈编辑页面
   * 
   * @return
   */
  public String updateFeedBack() {
    try {
      feedBackPojo = feedbackService.findFeedBackById(id);

    } catch (Exception e) {
      e.printStackTrace();

    }
    return SUCCESS;

  }

  /**
   * 后台意见反馈编辑提交
   * 
   * @return
   */
  public String updateFeedBackOk() throws Exception {
    try {
      feedbackService.updateFeedBackOk(feedBackPojo);
      FileUtil.alertMessageBySkip("修改成功！", "feedBack.do?");
    } catch (Exception e) {
      e.printStackTrace();

    }
    return null;


  }



}
