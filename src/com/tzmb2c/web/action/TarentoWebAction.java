package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.CompressPicture;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.MD5Util;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.LoginRecPojo;
import com.tzmb2c.web.pojo.SocialCirclePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.TemplatePageDataPojo;
import com.tzmb2c.web.pojo.UserCirclePostPojo;
import com.tzmb2c.web.service.LoginRecService;
import com.tzmb2c.web.service.LoginService;
import com.tzmb2c.web.service.SocialCircleService;
import com.tzmb2c.web.service.TemplatePageDataService;
import com.tzmb2c.web.service.UserCircleFollowService;
import com.tzmb2c.web.service.UserCirclePostService;


public class TarentoWebAction extends SuperAction {
  @Autowired
  private UserCirclePostService userCirclePostService;

  @Autowired
  private LoginRecService loginRecService;
  @Autowired
  private LoginService loginService;
  @Autowired
  private UserCircleFollowService userCircleFollowService;
  @Autowired
  private SocialCircleService socialCircleService;
  @Autowired
  private TemplatePageDataService templatePageDataService;


  private Integer userPostCount;
  private Integer userCircleFollowCount;
  private String username;// 用户名称
  private String passwd;// 用户密码
  private String url;// 前端传的返回连接
  private String msg;// 提示
  private List<UserCirclePostPojo> userCirclePostPojoList;
  private UserCirclePostPojo userCirclePostPojo;
  private File banner, image;
  private List<SocialCirclePojo> socialCirclePojoList;
  private String result;
  private TemplatePageDataPojo templatePageDataPojo;



  public TemplatePageDataPojo getTemplatePageDataPojo() {
    return templatePageDataPojo;
  }

  public void setTemplatePageDataPojo(TemplatePageDataPojo templatePageDataPojo) {
    this.templatePageDataPojo = templatePageDataPojo;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public Integer getUserPostCount() {
    return userPostCount;
  }

  public void setUserPostCount(Integer userPostCount) {
    this.userPostCount = userPostCount;
  }



  public Integer getUserCircleFollowCount() {
    return userCircleFollowCount;
  }

  public void setUserCircleFollowCount(Integer userCircleFollowCount) {
    this.userCircleFollowCount = userCircleFollowCount;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }



  public List<UserCirclePostPojo> getUserCirclePostPojoList() {
    return userCirclePostPojoList;
  }

  public void setUserCirclePostPojoList(List<UserCirclePostPojo> userCirclePostPojoList) {
    this.userCirclePostPojoList = userCirclePostPojoList;
  }



  public UserCirclePostPojo getUserCirclePostPojo() {
    return userCirclePostPojo;
  }

  public void setUserCirclePostPojo(UserCirclePostPojo userCirclePostPojo) {
    this.userCirclePostPojo = userCirclePostPojo;
  }

  public File getBanner() {
    return banner;
  }

  public void setBanner(File banner) {
    this.banner = banner;
  }

  public File getImage() {
    return image;
  }

  public void setImage(File image) {
    this.image = image;
  }


  public List<SocialCirclePojo> getSocialCirclePojoList() {
    return socialCirclePojoList;
  }

  public void setSocialCirclePojoList(List<SocialCirclePojo> socialCirclePojoList) {
    this.socialCirclePojoList = socialCirclePojoList;
  }

  /**
   * 达人中心登录前端显示
   * 
   * @return String
   */
  public String tarentoLogin() {
    return SUCCESS;
  }


  /**
   * 达人中心登陆验证
   * 
   * @throws Exception
   * @return String
   */
  public String getLoginValidate() throws Exception {
    // 创建一个上下文容器
    ActionContext actionContext = ActionContext.getContext();
    // 判断前端获取的参数
    if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(passwd)) {
      // 将获取到用户名密码保存到POJO中，用以查询数据库表中是否有该记录
      SysLoginPojo loginPojo = new SysLoginPojo();
      loginPojo.setLoginname(username);
      loginPojo.setPassword(MD5Util.MD5(passwd));
      // 设置用户类型为达人，对应值为11
      loginPojo.setUserType("11");
      // 检测数据库表中是否有该用户
      if (loginService.loginCheckWeb(loginPojo)) {
        // 将获取到的数据保存在上下文容器中
        // 上下文容器中的数据来源：LoginServiceImpl.java中的loginCheckWeb方法
        SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("tarentouser");
        // 判断数据库记录中的用户状态
        if (logiPojo != null && logiPojo.getStatus() == 1) {
          HttpServletRequest request = ServletActionContext.getRequest();
          // 构造一个用户登录日志对象，用来存放用户登录的信息
          LoginRecPojo loginRecPojo = new LoginRecPojo();
          loginRecPojo.setType(logiPojo.getType());// 设置日志中用户的类型
          loginRecPojo.setLoginDate(new Date());// 设置日志中用户登录时间
          loginRecPojo.setLoginIp(getIpAddr(request));// 设置日志中用户登录时所用的IP
          loginRecPojo.setUserId(logiPojo.getId());// 设置日志中用户的ID
          loginRecService.addLoginRec(loginRecPojo);// 将数据插入进日志表中，用以记录用户轨迹
          if (url != null && !url.equals("")) {
            FileUtil.alertMessageBySkip("登录成功", url);
            return null;
          } else {
            return SUCCESS;
          }

        } else {
          setMsg("您的帐号暂时不能登录，请联系管理员！");
          actionContext.put("loop", "false");
          return "gkloginweb";
        }
      }
      setMsg("用户名或密码错误！");
    }
    actionContext.put("loop", "false");
    return "gkloginweb";
  }



  /**
   * 达人退出 达人点击前端界面右上角退出按钮
   * 
   * @return
   */
  public String doTarentoLogoutWeb() {
    // 获取达人登录时的session数据
    Map<String, Object> session = ActionContext.getContext().getSession();
    // 清空session数据
    session.clear();
    // 返回登录页面
    return "gkloginweb";
  }

  /**
   * 达人首页信息
   * 
   * @return
   * @throws SQLException
   */
  public String tarentoWebHomePage() throws SQLException {
    // 获取session中达人信息
    SysLoginPojo sysLogin = UserUtil.getTarentoUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录", "tarentoLogin.do");
      return null;
    }
    Long uid = sysLogin.getId();
    // 创建一个map,用来存放笔记查询的相关参数
    Map<String, Object> map = new HashMap<String, Object>();
    // 设置用户ID
    map.put("userId", uid);
    map.put("isDelete", 0);
    // 设置状态
    // map.put("status", 1);
    // 查询数据库中用户的笔记数量
    userPostCount = userCirclePostService.userCirclePostCount(map);
    // 创建一个map,用来存放粉丝数量查询的相关参数
    //Map<String, Object> userCircleFollowMap = new HashMap<String, Object>();
    // 设置类型（1用户，2圈子）
    //userCircleFollowMap.put("type", 1);
    // 设置关注标识（1默认关注，0取消关注）
    //userCircleFollowMap.put("isFollow", 1);
    // 设置被关注用户ID
    //userCircleFollowMap.put("typeId", uid);
    // 查询数据库中达人的粉丝数量
    // userCircleFollowCount = userCircleFollowService.userCircleFollowCount(userCircleFollowMap);
    userCircleFollowCount = userCircleFollowService.getFollowNum(0l, uid, 0);
    return SUCCESS;
  }

  /**
   * 笔记列表前端显示
   * 
   * @return
   * @throws Exception
   */
  public String getUserCirclePostListWeb() throws Exception {
    ActionContext actionContext = ActionContext.getContext();
    Map<String, Object> map = new HashMap<String, Object>();
    SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("tarentouser");
    int count = 0;
    if (logiPojo != null) {
      map.put("userId", logiPojo.getId());
      map.put("isDelete", 0);
      count = userCirclePostService.userCirclePostCount(map);
    } else {
      FileUtil.alertMessageBySkip("请先登录", "tarentoLogin.do");
      return null;
    }
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(count);
    return SUCCESS;
  }

  /**
   * 笔记列表数据前端显示
   * 
   * @return
   * @throws SQLException
   */
  public String getUserCirclePostListData() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("isDelete", 0);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("tarentouser");
    if (logiPojo != null) {
      map.put("userId", logiPojo.getId());
      userCirclePostPojoList = userCirclePostService.userCirclePostList(map);
    }
    JSONArray json = JSONArray.fromObject(userCirclePostPojoList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 跳转到新增笔记页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddUCPost() throws Exception {
    // Map<String, Object> paramsMap = new HashMap<String, Object>();
    // 获取登录用户信息
    // SysLoginPojo logiPojo = UserUtil.getTarentoUser();
    // paramsMap.put("userId", logiPojo.getId());
    // paramsMap.put("type", 2);
    // paramsMap.put("isFollow", 1);
    // paramsMap.put("status", 1);

    // socialCirclePojoList = socialCircleService.findSocialCircleByTarentoId(paramsMap);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("status", 1);
    socialCirclePojoList = socialCircleService.findSocialCircleList(map);
    return SUCCESS;
  }

  /**
   * 新增笔记
   * 
   * @throws Exception
   * @return String
   */
  public String addUCPost() throws Exception {
    try {
      // 获取登录用户信息
      ActionContext actionContext = ActionContext.getContext();
      SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("tarentouser");
      if (logiPojo == null) {
        return "gkloginweb";
        // FileUtil.alertMessageBySkip("请先登录", "tarentoLogin.do");
        // return null;
      }
      if (userCirclePostPojo != null) {
        // banner图操作
        if (banner != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
                  + File.separator;
          // FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", banner);
          // // 图片压缩
          // CompressPicture cp = new CompressPicture();
          // String compressPath =
          // ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost/small")
          // + File.separator;
          // cp.compressPic(banner, compressPath, "upfiles/userCirclePost/small/", file_name, 300,
          // 300, true);

          // 图片压缩
          CompressPicture cp = new CompressPicture();
          cp.compressPic(banner, uploadPath, uploadPath, file_name, 0, 0, false);
          userCirclePostPojo.setBanner(file_name);
        } else {
          // FileUtil.alertMessageBySkip("图片不能为空！", "goAddUCPost.do");
          // return null;
        }
        // if (image != null) {
        // String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        // String uploadPath =
        // ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
        // + File.separator;
        // FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", image);
        // // 图片压缩
        // CompressPicture cp = new CompressPicture();
        // String compressPath =
        // ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost/small")
        // + File.separator;
        // cp.compressPic(image, compressPath, "upfiles/userCirclePost/small/", file_name, 300, 300,
        // true);
        // userCirclePostPojo.setImage(file_name);
        // } else {
        // userCirclePostPojo.setImage("");
        // }
        // if (userCirclePostPojo.getContent() == null || userCirclePostPojo.getContent() == "") {
        // FileUtil.alertMessageBySkip("内容不能为空！", "goAddUserCirclePost.do");
        // return null;
        // }
        // 设置新增时间
        // userCirclePostPojo.setCreateDate(new Date());
        // 设置用户id
        userCirclePostPojo.setUserId(logiPojo.getId());
        // 设置状态
        // userCirclePostPojo.setStatus(0);

        // 插入数据
        userCirclePostService.addUserCirclePost(userCirclePostPojo);
        // FileUtil.alertMessageBySkip("添加成功！", "getUserCirclePostListWeb.do");
        this.result = userCirclePostPojo.getId().toString();
      }
    } catch (Exception e) {
      e.printStackTrace();
      // FileUtil.alertMessageBySkip("添加失败！", "getUserCirclePostListWeb.do");
      this.result = null;
    }
    // return null;
    return SUCCESS;
  }

  /**
   * 跳转到编辑笔记页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateUCPost() throws Exception {
    try {
      // 获取登录用户信息
      SysLoginPojo logiPojo = UserUtil.getTarentoUser();
      if (logiPojo == null) {
        FileUtil.alertMessageBySkip("请先登录", "tarentoLogin.do");
        return null;
      }
      if (userCirclePostPojo != null) {
        userCirclePostPojo =
            userCirclePostService.getUserCirclePostById(userCirclePostPojo.getId());
        // Map<String, Object> paramsMap = new HashMap<String, Object>();
        // paramsMap.put("userId", logiPojo.getId());
        // paramsMap.put("type", 2);
        // paramsMap.put("isFollow", 1);
        // paramsMap.put("status", 1);

        // socialCirclePojoList = socialCircleService.findSocialCircleByTarentoId(paramsMap);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 1);
        socialCirclePojoList = socialCircleService.findSocialCircleList(map);
        if (userCirclePostPojo != null && userCirclePostPojo.getVersion() == 1) {
          // 查询自定义笔记详情页面数据
          Map<String, Object> params = new HashMap<String, Object>();
          if (userCirclePostPojo.getId() != null) {
            params.put("pageId", userCirclePostPojo.getId());
            params.put("type", 3);
            templatePageDataPojo = templatePageDataService.findByParams(params);
            if (templatePageDataPojo == null) {
              templatePageDataPojo = new TemplatePageDataPojo();
              templatePageDataPojo.setData("{}");
            }
          }
          return SUCCESS;
        } else {
          return "oldVersion";
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 编辑笔记
   * 
   * @throws Exception
   * @return String
   */
  public String updateUCPost() throws Exception {
    try {
      // 获取登录用户信息
      ActionContext actionContext = ActionContext.getContext();
      SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("tarentouser");
      if (logiPojo == null) {
        return "gkloginweb";
        // FileUtil.alertMessageBySkip("请先登录", "tarentoLogin.do");
        // return null;
      }
      if (userCirclePostPojo != null) {
        // banner图操作
        if (banner != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
                  + File.separator;
          // FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", banner);
          // // 图片压缩
          // CompressPicture cp = new CompressPicture();
          // String compressPath =
          // ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost/small")
          // + File.separator;
          // cp.compressPic(banner, compressPath, "upfiles/userCirclePost/small/", file_name, 300,
          // 300, true);

          // 图片压缩
          CompressPicture cp = new CompressPicture();
          cp.compressPic(banner, uploadPath, uploadPath, file_name, 0, 0, false);
          userCirclePostPojo.setBanner(file_name);
        }
        // if (image != null) {
        // String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        // String uploadPath =
        // ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
        // + File.separator;
        // FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", image);
        // // 图片压缩
        // CompressPicture cp = new CompressPicture();
        // String compressPath =
        // ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost/small")
        // + File.separator;
        // cp.compressPic(image, compressPath, "upfiles/userCirclePost/small/", file_name, 300, 300,
        // true);
        // userCirclePostPojo.setImage(file_name);
        // } else {
        // userCirclePostPojo.setImage("");
        // }
        // if (userCirclePostPojo.getContent() == null || userCirclePostPojo.getContent() == "") {
        // FileUtil.alertMessageBySkip("内容不能为空！", "goAddUserCirclePost.do");
        // return null;
        // }
        // 设置新增时间
        // userCirclePostPojo.setCreateDate(new Date());
        // 设置用户id
        // userCirclePostPojo.setUserId(logiPojo.getId());
        // 设置状态
        userCirclePostPojo.setStatus(0);

        // 插入数据
        userCirclePostService.updateUserCirclePost(userCirclePostPojo);
        // FileUtil.alertMessageBySkip("修改成功！", "getUserCirclePostListWeb.do");
        this.result = userCirclePostPojo.getId().toString();
      }
    } catch (Exception e) {
      e.printStackTrace();
      // FileUtil.alertMessageBySkip("修改失败！", "getUserCirclePostListWeb.do");
      this.result = null;
    }
    return SUCCESS;
  }


  /**
   * 删除笔记
   * 
   * @throws Exception
   * @return String
   */
  public String delUCPost() throws Exception {
    try {
      // 获取登录用户信息
      SysLoginPojo logiPojo = UserUtil.getTarentoUser();
      if (logiPojo == null) {
        FileUtil.alertMessageBySkip("请先登录", "tarentoLogin.do");
        return null;
      }
      if (userCirclePostPojo != null) {


        userCirclePostPojo.setIsDelete(1);// 设置为删除状态
        userCirclePostService.updateUserCirclePost(userCirclePostPojo);
        FileUtil.alertMessageBySkip("删除成功！", "getUserCirclePostListWeb.do");

      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("操作失败！", "getUserCirclePostListWeb.do");
    }
    return null;
  }

  /**
   * 获取用户登录时IP地址
   * 
   * @param request
   * @return String
   */
  public String getIpAddr(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }


}
